package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.business.abstracts.OrderCreateServiceAbstract;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;


/**
 * 第三方平台订单的创建 
 * 1、E3系统订单传递过来的订单-主要包括京东，淘宝
 * 2、E3订单无会员资源扣减
 * 3、E3订单无促销资源扣减
 * 4、E3定有活动付款情况
 * 第三方订单数据在E3中审核完成后同步过来，第三方订单暂时不在中台做操作 
 * 此服务暂时不提供
 * @author wch
 */
@Deprecated
@Service("orderCreateThirdPartyPlatform")
public class OrderCreateThirdPartyPlatformServiceImpl extends OrderCreateServiceAbstract{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderCreateTrans orderCreateTrans;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    IntfVerifiedService intfVerifiedService;
    
    @Override
    public BtcOmsReceiveOrderOutputDTO createOrder(BtcOmsReceiveOrderDTO orderReceiveDIO) {
        BtcOmsReceiveOrderOutputDTO output = new BtcOmsReceiveOrderOutputDTO();
        ContextBtcOmsReceiveDTO context = new ContextBtcOmsReceiveDTO();
        Long t1 = System.currentTimeMillis();
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
        IntfVerified iv = orderCreateTrans.saveVerified(orderReceiveDIO, context);
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
                inventoryUnLock(orderMainList);
            }

            // 返回
            output.setSucceed(CommonConstService.FAILED);
            output.setMessage(context.getMsg());
            return output;
        }
        
        boolean statusProcess = orderCreateTrans.savePostInventory(orderReceiveDIO, context);
        //没有货到付款-- E3的货到付款，客户收货支付以后E3不会继续通知平台
        // 更新待处理表，让定时任务捞取
//        if (statusProcess) {
//            iv.setProcessFlag(CommonConstService.PROCESS_WAIT);
//            intfVerifiedService.update(iv);
//        }
        
        // 写r3同步数据
        orderCreateTrans.saveStatusSyncLog(context.getOmMap());
        Long t7 = System.currentTimeMillis();
        logger.info("总共耗时:{}", (t7 - t1));
        output.setSucceed(CommonConstService.OK);
        output.setMessage(CommonConstService.BTC_CREATE_OK);
        return output;
    }

}
