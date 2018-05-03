package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.business.OmsWarehouseService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.abstracts.OrderCreateServiceAbstract;
import com.ibm.oms.service.business.abstracts.OrderCreateServiceCommon;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.service.sys.OptionService;

/**
 * @author pjsong
 * 
 */
//OrderCreateService 默认是btc 线上商城订单创建 ,其他订单方式创建继承改方法，改写实现
//@Service
public class OrderCreateServiceImpl extends OrderCreateServiceAbstract {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderCreateTrans orderCreateTrans;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    IntfVerifiedService intfVerifiedService;
    @Autowired
    OmsWarehouseService warehouseService;
    //
    @Autowired
    OptionService optionService;
    //默认物流公司
    @Value("#{settings['logistics.code.default']}")
    public String logisticsCode;
    
    OrderCreateServiceCommon serviceCommon;

    public OrderCreateServiceCommon getServiceCommon() {
		return serviceCommon;
    }

    @Resource(name = "orderCreateServiceCommon")
    public void setServiceCommon(OrderCreateServiceCommon serviceCommon) {
  	  this.serviceCommon = serviceCommon;
    }
    
    
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderNoService orderNoService;
  
    @Autowired
    OrderMainService orderMainService;
    
    @Override
    public BtcOmsReceiveOrderOutputDTO createOrder(BtcOmsReceiveOrderDTO orderReceiveDIO) {
        BtcOmsReceiveOrderOutputDTO output = new BtcOmsReceiveOrderOutputDTO();
        ContextBtcOmsReceiveDTO context = new ContextBtcOmsReceiveDTO();
        Long t1 = System.currentTimeMillis();
        //配置默认参数-创建订单之前的配置
        conifigDefaultValueBefore(orderReceiveDIO,output);
        
        if(CommonConstService.FAILED.equals(output.getSucceed())){
        	 return output;
        }
        
        // 记录接口数据,之后校验失败
        if (!intfReceivedService.saveBtcReceived(orderReceiveDIO, context)) {
            output.setSucceed(CommonConstService.FAILED);
            output.setMessage(context.getMsg());
            return output;
        }
        Long t2 = System.currentTimeMillis();
        logger.info("接口数据校验保存耗时:{}", (t2 - t1));
        // 保存订单,失败
        if (!orderCreateTrans.saveOrder(orderReceiveDIO, context)) {
            output.setSucceed(CommonConstService.FAILED);
            output.setMessage(context.getMsg());
            return output;
        }
        Long t3 = System.currentTimeMillis();
        logger.info("订单存储耗时:{}", (t3 - t2));
        // 货到付款写入verified表待定时任务，初始写入失败
       // IntfVerified iv = orderCreateTrans.saveVerified(orderReceiveDIO, context);
        // 执行信息写入返回
        initOutputDTO(output, context);

        boolean intfCallSucceed = true;// 接口调用成功标识
        boolean inventoryLockSucceed = true; // 锁库成功标识,若后续接口失败则解锁用到

        /*** 库存锁定 ***/
        if (!inventoryLockBatch(context)) {
            // 改状态0110到0111
            orderCreateTrans.saveBatchProcess(context.getOmMap(), OrderStatusAction.S011011);
            intfCallSucceed = false;
            inventoryLockSucceed = false;
        }
        Long t4 = System.currentTimeMillis();
        logger.info("锁库耗时:{}", (t4 - t3));
        /*** 会员资源扣减 ***/
        if (intfCallSucceed && !memberResourceDeduct(context,true)) {
            // 改状态0110到0112
            orderCreateTrans.saveBatchProcess(context.getOmMap(), OrderStatusAction.S011012);
            intfCallSucceed = false;
        }
        Long t5 = System.currentTimeMillis();
        logger.info("会员资源扣减耗时:{}", (t5 - t4));

        /*** 促销资源扣减 ***/
        if (intfCallSucceed && !promoResourceDeduct(orderReceiveDIO, context)) {
            // 改状态0110到0113
            orderCreateTrans.saveBatchProcess(context.getOmMap(), OrderStatusAction.S011013);
            intfCallSucceed = false;
        }
        Long t6 = System.currentTimeMillis();
        logger.info("促销资源资源扣减耗时:{}", (t6 - t5));
        
        if (!intfCallSucceed) {
            logger.info("创建订单失败:{" + orderReceiveDIO.getBatchNum() + "},库存锁定结果：" + inventoryLockSucceed);
            // 如果锁库成功，则需进行解锁
            if (inventoryLockSucceed) {
                logger.info("创建订单失败且已锁库,库存解锁开始:{}", orderReceiveDIO.getBatchNum());
                List<OrderMain> orderMainList = new ArrayList<OrderMain>();
                Map<String, OrderMain> orderMap = context.getOmMap();// 已创建的订单
                Iterator<Map.Entry<String, OrderMain>> it = orderMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, OrderMain> entry = it.next();
                    orderMainList.add(entry.getValue());
                }
                inventoryUnLock(orderMainList,context);
            }
            // 返回
            output.setSucceed(CommonConstService.FAILED);
            output.setMessage(context.getMsg());
            return output;
        }
        // 改状态为货到付款0120或在线支付0130
        boolean statusProcess = orderCreateTrans.savePostInventory(orderReceiveDIO, context);
       
        // 更新待处理表，让定时任务捞取
//        if (statusProcess) {
//            iv.setProcessFlag(CommonConstService.PROCESS_WAIT);
//            intfVerifiedService.update(iv);
//        }
        // 写r3同步数据
        orderCreateTrans.saveStatusSyncLog(context.getOmMap());
        Long t7 = System.currentTimeMillis();
        logger.info("处理已经完全支付订单耗时:{}", (t7 - t6));
        logger.info("总共耗时:{}", (t7 - t1));
        buidingOutMsg(output);
        output.setSucceed(CommonConstService.OK);
        output.setMessage(CommonConstService.BTC_CREATE_OK);
        return output;
    }

	private void buidingOutMsg(BtcOmsReceiveOrderOutputDTO output) {
		List<BtcOmsReceiveOutputDTO> bots=  output.getMapList();
        for(BtcOmsReceiveOutputDTO bot : bots){
        	String orderNo = bot.getOrderNo();
        	OrderMain om  = orderMainService.findByOrderNo(orderNo);
        	bot.setMsg(buildingOrderMainOutMsg(om)); ;
        }
	}
	

}
