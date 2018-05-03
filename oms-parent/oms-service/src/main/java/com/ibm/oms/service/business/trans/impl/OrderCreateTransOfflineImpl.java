package com.ibm.oms.service.business.trans.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.constant.OrderMainConstClient;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.WmsOmsService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.business.trans.abstracts.OrderCreateTransAbstract;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Service("orderCreateTransOffline")
public class OrderCreateTransOfflineImpl extends OrderCreateTransAbstract {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String errorMsgOrderMainDuplicate = "OrderCreateTransImpl.saveOrder 订单已存在";
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderPromotionService orderPromotionService;
    @Autowired
    OrderItemPayService orderItemPayService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderItemVirtualService orderItemVirtualService;
    @Autowired
    OrderPayModeService orderPayModeService;
    @Autowired
    OrderInvoiceService orderInvoiceService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    WmsOmsService oms2WmsService; 
	@Resource(name = "prePayModeMap")
	private Map<String, String> prePayModeMap;
    /**
     * @param orderMainDao
     */
    @Autowired
    public void setOrderMainDao(OrderMainDao orderMainDao) {
        super.setBaseDao(orderMainDao);
    }

    @Autowired
    private OrderPayService orderPayService;
    @Autowired
    private OrderSubService orderSubService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    IntfVerifiedService intfVerifiedService;
    @Autowired
    CommonUtilService commonUtilService;

    public OrderPayService getOrderPayService() {
        return orderPayService;
    }

    @Autowired
    public void setOrderPayService(OrderPayService orderPayService) {
        this.orderPayService = orderPayService;
    }

    @Autowired
    public void setOrderSubService(OrderSubService orderSubService) {
        this.orderSubService = orderSubService;
    }

    @Override
    public boolean saveOrder(BtcOmsReceiveOrderDTO orderReceiveDIO,
            ContextBtcOmsReceiveDTO context) {
        if(!saveOrderMainStart(orderReceiveDIO, context)){
            //订单已经存在
            context.setCreateSuccessFlag(false);
            context.setMsg(errorMsgOrderMainDuplicate);
            return false;
        };
        context.setCreateSuccessFlag(true);
        return true;
    }
    
    /**是否重复订单**/
    protected boolean isDuplicate(String batchNum, OrderMainDTO om){
       return super.isDuplicate(batchNum, om);
    }

    /**
     * 写主订单表，子订单，订单级促销
     * **/
    protected boolean saveOrderMainStart(BtcOmsReceiveOrderDTO orderReceiveDIO, BtcOmsReceiveOrderOutputDTO output,
            ContextBtcOmsReceiveDTO context) {
        return super.saveOrderMainStart(orderReceiveDIO, context);
    }

    /**
     * 写主订单，子订单，订单级促销
     * **/
    protected BtcOmsReceiveOutputDTO saveOrderMainSingle(OrderMainDTO omDTO, ContextBtcOmsReceiveDTO context) {
      return super.saveOrderMainSingle(omDTO, context);
    }
    
    @Deprecated
    protected void saveOrderItemVirtual(OrderSubDTO osDTO, OrderSub os, OrderMain om, ContextBtcOmsReceiveDTO context) {
    	
    }

    /** 写my卡，券，积分支付等支付信息 **/
    protected void saveOrderPay(OrderMainDTO omDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
       super.saveOrderPay(omDTO, om, context);
    }

    /** 按照所有明细行平摊订单级优惠 **/
    protected Map<String, OrderItemPay> splitPayMent(List<OrderItem> orderItems, OrderPay op) {
    	return super.splitPayMent(orderItems, op);
    }

    /** 写子订单表 **/
    protected List<OrderSub> saveOrderSub(OrderMainDTO orderMainDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
        return super.saveOrderSub(orderMainDTO, om, context);
    }

    /** 写订单行表 **/
    protected void saveOrderItem(OrderSubDTO osDTO, OrderSub os, OrderMain om, ContextBtcOmsReceiveDTO context) {
    	 super.saveOrderItem(osDTO, os, om, context);

    }

    /**保存订单行级促销信息
     * @param opDTOs
     * @param oi
     */
    protected void saveOrderItemPromo(List<OrderPromotionDTO> opDTOs, OrderItem oi) {
    	super.saveOrderItemPromo(opDTOs, oi);
    }
    
    /**保存订单级促销信息
     * @param opDTOs
     * @param oi
     */
    protected void saveOrderPromo(OrderMainDTO omDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
    	super.saveOrderPromo(omDTO, om, context);
    }
    
    /**订单完成赠送积分**/
    protected void promAddTotalPoint(OrderMain om, OrderPromotionDTO op){
    	super.promAddTotalPoint(om, op);
    }
    
    
    /**订单完成送券**/
    protected void promAddTotalTicketAmount(OrderMain om, OrderPromotionDTO op){
    	super.promAddTotalTicketAmount(om, op);
    }
    
    
    protected OrderMain saveOrderMain(OrderMainDTO omDTO, ContextBtcOmsReceiveDTO context) {
    	return super.saveOrderMain(omDTO, context)	;
    }
   
    @Override
    //导购APP 待客下单  下单1、线下下单 ，线下发货 -->已支付， 确认收货   2、线下下单，线上发货 -->已支付，待收货   -->线下订单无需审核
    public boolean savePostInventory(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context) {
    	//导购APP 代客下单 ，下单都是以支付状态
    	context.setPayOnArrival(true);
    	context.setTotalPaid(true);
    	//获取到mainOrder
    	OrderMainDTO  orderMainDTO = getsingleOrderMainDTO(orderReceiveDIO);
    	String distributeType =  getDistributeType(orderMainDTO);
    	//1、线下下单 ，线下发货 -->已支付， 确认收货 
		if (OrderMainConstClient.OrderSub_DistributeType_Self_Store.getCode().equals(distributeType)) {
			// 已经完全支付
			for (OrderMain om : context.getOmMap().values()) {
				//线下订单-已支付
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline01100420, null, null, null);
				//线下订单-已审核
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline04200807, null, null, null);
				//线下订单-已完成
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline08070180, null, null, null);
			}
			return true;
		}
		
		// 2、线下下单，线上发货 -->已支付，待收货 -->线下订单无需审核
		if (OrderMainConstClient.OrderSub_DistributeType_Assign_Store.getCode().equals(distributeType)) {
			// 通知wms发货
			// TODO
			Object o = new Object();
			oms2WmsService.oms2WmsShipNotify(o.toString());
			//通知成功以后调用修改状态
			//如果失败 变为异常订单 定时任务统一处理
			//wms 缺货流程 -- 
			// 已经完全支付
			for (OrderMain om : context.getOmMap().values()) {
				// 线下订单-已支付
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline01100420, null, null, null);
				// 线下订单-已审核
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline04200807, null, null, null);
				// 线下订单-待发货
				orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
						OrderStatusAction.Offline08070160, null, null, null);
			}

			return true;
		}
		return false;
      
    }
    
	private String getDistributeType(OrderMainDTO orderMainDTO) {
		List<OrderSubDTO> orderSubDTOs = orderMainDTO.getOsDTOs();
		if(orderSubDTOs.size()==1){
			return orderSubDTOs.get(0).getDistributeType();
		}
		logger.info(String.format("%s 接受订单OrderMainDTO 下的 OrderSubDTO 大于 1或者为 0", getClass()));
		return new OrderSubDTO().getDistributeType() ;
	}

	private OrderMainDTO getsingleOrderMainDTO(BtcOmsReceiveOrderDTO orderReceiveDIO) {
		List<OrderMainDTO> orderMainDTOs = orderReceiveDIO.getOmDTO();
		if(orderMainDTOs.size() == 1){
			return orderMainDTOs.get(0);
		}
		logger.info(String.format("%s 接受订单List<OrderMainDTO> 大于 1或者为 0", getClass()));
		return new OrderMainDTO();
	}

}
