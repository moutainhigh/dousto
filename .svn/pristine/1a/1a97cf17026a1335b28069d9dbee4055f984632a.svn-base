package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.inner.PaymentDTO;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.BtcOrderPaymentTransService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.util.CommonConstService;

@Service("btcOrderPaymentTransService")
public class BtcOrderPaymentTransServiceImpl  implements BtcOrderPaymentTransService {

	@Autowired
	OrderMainService orderMainService;
	
	@Autowired
	OrderSubService orderSubService;
	
	@Autowired
	OrderStatusService orderStatusService;
	
	@Autowired
	OrderItemPayService orderItemPayService;
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	OrderItemVirtualService orderItemVirtualService;
	
	@Autowired
	OrderPayService orderPayService;
	
    @Autowired
    OrderNoService orderNoService;
    
    @Autowired 
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderCreateTrans orderCreateTrans;
    
    @Override 
    public boolean updateStatusPay(BtcPayDTO payDto, CommonOutputDTO commonDto){
        OrderMain om = orderMainService.findByOrderNo(payDto.getOrderNo());
        if(om==null){
            // 校验失败，保存接口调用日志
            commonDto.setCode(CommonConstService.FAILED);
            commonDto.setMsg("无法找到订单：" + payDto.getOrderNo());
            return false;
        }
        OrderSub os = null;
        List<OrderSub> subList = orderSubService.getByOrderMainNo(payDto.getOrderNo());
        if(subList!=null && !subList.isEmpty()){
            os = subList.get(0);
        }else{
            // 校验失败，保存接口调用日志
            commonDto.setCode(CommonConstService.FAILED);
            commonDto.setMsg("无法找到子订单：" + payDto.getOrderNo());
            return false;
        }
        String orderSubNo = os.getOrderSubNo();
        boolean flag = false;
        if(CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival())){
            //门店货到付款
            flag = orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S017180,
                    payDto.getOperator(), null, null);
            //写带同步表
            if(flag){
                orderCreateTrans.saveOrderToR3(om, os);
            }
        }
        else{
            //在线支付
           flag = orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S013020, payDto.getOperator(), null, null); 
        }
        if(!flag){
            // 校验失败，保存接口调用日志
            commonDto.setCode(CommonConstService.FAILED);
            commonDto.setMsg("检查订单状态失败：订单非支付中状态：" + payDto.getOrderNo()+"当前状态:"+om.getStatusTotal());
        }
        return flag;
    }
	@Override
	public CommonOutputDTO updateBtcOrderPayment(BtcPayDTO payDto){
		CommonOutputDTO commonDto = new CommonOutputDTO();
		btcOrderPayment(payDto,commonDto);
		return commonDto;
	}
	
	private void btcOrderPayment(BtcPayDTO payDto, CommonOutputDTO commonDto){
		OrderMain om = orderMainService.findByOrderNo(payDto.getOrderNo());
		OrderSub os = null;
        List<OrderSub> subList = orderSubService.getByOrderMainNo(payDto.getOrderNo());
        if(subList!=null && !subList.isEmpty()){
            os = subList.get(0);
        }
 		if(CommonConst.OrderMain_OrderType_VIRTUAL.getCode().equals(om.getOrderType())){
			handlerVirtualOrderPay(payDto,commonDto,om);
		}else{
			handlerOrderPay(payDto,commonDto,om,os);
		}
		if(!CommonConstService.OK.equals(commonDto.getCode())){
		    return;
		}
		
		//预付款待同步R3,如果不是bbc则同步预付款
		boolean isBBC = CommonConst.OrderPay_DateSource_Bbc.getCode().equals(payDto.getDataSource());
        if (!isBBC) {
            //不是bbc，就是预付款
            orderStatusSyncLogService.saveAndcreate(om,os,
                    CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
        }
	}
	
	private void handlerVirtualOrderPay(BtcPayDTO payDto,CommonOutputDTO commonDto,OrderMain om ){
			List<OrderItemVirtual> orderItemList = orderItemVirtualService.getByOrdeNo(om.getOrderNo());
			List<PaymentDTO> ipDTOs = payDto.getPaymentDTOs();
			for (PaymentDTO paymentDTO : ipDTOs) {
				OrderPay op = new OrderPay();
				BeanUtils.copyProperties(paymentDTO, op);
				op.setIdOrder(om.getId());
				op.setOrderNo(payDto.getOrderNo());
				op.setRemark(payDto.getRemark());
				op.setOperatorName(payDto.getOperator());						
				op.setBillType(om.getBillType());
				op.setDateCreated(new Date());
				op.setDateUpdated(new Date());
				op.setIsDeleted(0l);
				op.setPayNo(payDto.getPayNo());
                if(paymentDTO.getPayTime() == null){
                    op.setPayTime(new Date());
                }
				//写入ORDER_PAY
				orderPayService.save(op);
				//写入ORDER_ITEM_PAY
				saveVirtualOrderItemPayFromIntf(om,orderItemList, paymentDTO);
				/*Boolean ret = orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S014050);	
				if(!ret){
					throw new BusinessException("自动审核失败");
				}*/
			}
			// 保存接口调用日志
			commonDto.setCode(CommonConstService.OK);
			commonDto.setMsg("接口调用成功：" + payDto.getOrderNo());
	}
	
	
	private void handlerOrderPay(BtcPayDTO payDto,CommonOutputDTO commonDto,OrderMain om ,OrderSub os ){
			List<OrderItem> orderItemList = orderItemService.getByOrdeSubNo(os.getOrderSubNo());
			List<PaymentDTO> ipDTOs = payDto.getPaymentDTOs();
			for (PaymentDTO paymentDTO : ipDTOs) {
				OrderPay op = new OrderPay();
				BeanUtils.copyProperties(paymentDTO, op);
				op.setIdOrder(os.getIdOrder());
				op.setOrderNo(payDto.getOrderNo());
				op.setRemark(payDto.getRemark());
				op.setOperatorName(payDto.getOperator());						
				op.setBillType(os.getBillType());
				op.setDateCreated(new Date());
				op.setDateUpdated(new Date());
				op.setIsDeleted(0l);
				op.setPayNo(payDto.getPayNo());
				if(paymentDTO.getPayTime() == null){
				    op.setPayTime(new Date());
				}
				//写入ORDER_PAY
				orderPayService.save(op);
				//写入ORDER_ITEM_PAY
				saveOrderItemPayFromIntf(om, os, orderItemList, paymentDTO);
				
				/*Boolean ret = orderStatusService.saveProcess(os.getOrderSubNo(), OrderStatusAction.S014050);	
				if(!ret){
					throw new BusinessException("自动审核失败");
				}*/
			}
			// 保存接口调用日志
			commonDto.setCode(CommonConstService.OK);
			commonDto.setMsg("接口调用成功：" + payDto.getOrderNo());
	}
	
	// 按照所有明细行平摊订单级支付明细
	private boolean saveOrderItemPayFromIntf(OrderMain orderMain, OrderSub orderSub, List<OrderItem> orderItems, PaymentDTO paymentDTO) {

		// 订单行应付金额:折后总价
		BigDecimal itemTotalAmount = new BigDecimal(0.0);
		for (OrderItem oi : orderItems) {
			itemTotalAmount = itemTotalAmount.add(oi.getPayAmount());
		}
		// 累计分摊金额
		BigDecimal addedPayAmountDTOTotal = new BigDecimal(0.0);
		// 现金or银行卡or天虹卡的支付金额
		BigDecimal dtoPayAmount = paymentDTO.getPayAmount();
		int size = orderItems.size();
		for (int i = 0; i < size; i++) {
			OrderItem oi = orderItems.get(i);
			OrderItemPay orderItemPay = new OrderItemPay();
			BeanUtils.copyProperties(paymentDTO, orderItemPay);
			if (i == (size - 1)) {
				// 订单商品行最后一行（做减法）分摊的支付金额
				BigDecimal payAmountDTODivided = dtoPayAmount.subtract(addedPayAmountDTOTotal);
				orderItemPay.setPayAmount(payAmountDTODivided);
			} else {
				// 订单商品行的分摊的支付金额
				BigDecimal payAmountDTODivided = dtoPayAmount.multiply(oi.getPayAmount()).divide(itemTotalAmount ,2, RoundingMode.HALF_UP);
				orderItemPay.setPayAmount(payAmountDTODivided);
				addedPayAmountDTOTotal = addedPayAmountDTOTotal.add(payAmountDTODivided);
			}
			orderItemPay.setOrderNo(orderMain.getOrderNo());
			orderItemPay.setIdOrderItem(oi.getId());
			orderItemPay.setOrderItemNo(oi.getOrderItemNo());
			orderItemPay.setPayType(orderMain.getPayOnArrivalPayType());		
			orderItemPay.setBillType(orderSub.getBillType());
			orderItemPay.setPayAmountTotal(dtoPayAmount);
			orderItemPay.setDateUpdated(new Date());
			orderItemPay.setDateCreated(new Date());
			orderItemPay.setIsDeleted(0L);
			orderItemPayService.save(orderItemPay);
		}
		return true;
	}
	
	// 按照所有明细行平摊订单级支付明细
	private boolean saveVirtualOrderItemPayFromIntf(OrderMain orderMain, List<OrderItemVirtual> orderItems, PaymentDTO paymentDTO) {

		// 订单行应付金额:折后总价
		BigDecimal itemTotalAmount = new BigDecimal(0.0);
		for (OrderItemVirtual oi : orderItems) {
			itemTotalAmount = itemTotalAmount.add(oi.getSaleAmount());
		}
		// 累计分摊金额
		BigDecimal addedPayAmountDTOTotal = new BigDecimal(0.0);
		// 现金or银行卡or天虹卡的支付金额
		BigDecimal dtoPayAmount = paymentDTO.getPayAmount();
		int size = orderItems.size();
		for (int i = 0; i < size; i++) {
			OrderItemVirtual oi = orderItems.get(i);
			OrderItemPay orderItemPay = new OrderItemPay();
			BeanUtils.copyProperties(paymentDTO, orderItemPay);
			if (i == (size - 1)) {
				// 订单商品行最后一行（做减法）分摊的支付金额
				BigDecimal payAmountDTODivided = dtoPayAmount.subtract(addedPayAmountDTOTotal);
				orderItemPay.setPayAmount(payAmountDTODivided);
			} else {
				// 订单商品行的分摊的支付金额
				BigDecimal payAmountDTODivided = dtoPayAmount.multiply(oi.getSaleAmount()).divide(itemTotalAmount);
				orderItemPay.setPayAmount(payAmountDTODivided);
				addedPayAmountDTOTotal = addedPayAmountDTOTotal.add(payAmountDTODivided);
			}
			orderItemPay.setOrderNo(orderMain.getOrderNo());
			orderItemPay.setIdOrderItem(oi.getId());
			orderItemPay.setOrderItemNo(oi.getOrderItemNo());
			orderItemPay.setPayType(orderMain.getPayOnArrivalPayType());		
			orderItemPay.setBillType(orderMain.getBillType());
			orderItemPay.setPayAmountTotal(dtoPayAmount);
			orderItemPay.setDateUpdated(new Date());
			orderItemPay.setDateCreated(new Date());
			orderItemPay.setIsDeleted(0L);
			orderItemPayService.save(orderItemPay);
		}
		return true;
	}
}
