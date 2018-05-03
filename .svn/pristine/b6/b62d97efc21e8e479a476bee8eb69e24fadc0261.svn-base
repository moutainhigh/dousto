package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderPaymentService;
import com.ibm.oms.service.business.OrderStatusService;

@Service("orderPaymentService")
public class OrderPaymentServiceImpl implements OrderPaymentService {

	@Autowired
	IntfReceivedService intfReceivedService;
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	OrderPayService orderPayService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired
	OrderItemPayService orderItemPayService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderMainService orderMainService;
	@Autowired
	OrderNoService orderNoService;
	@Autowired
	OrderPayModeService orderPayModeService;

	// 按照所有明细行平摊订单级支付明细
	private boolean saveOrderItemPayFromIntf(OrderMain orderMain, OrderSub orderSub, List<OrderItem> orderItems, OrderPay orderPay) {

		// 订单行应付金额:折后总价
		BigDecimal itemTotalAmount = new BigDecimal(0.0);
		for (OrderItem oi : orderItems) {
			itemTotalAmount = itemTotalAmount.add(oi.getPayAmount());
		}
		// 累计分摊金额
		BigDecimal addedPayAmountDTOTotal = new BigDecimal(0.0);
		// 现金or银行卡or天虹卡的支付金额
		BigDecimal dtoPayAmount = orderPay.getPayAmount();
		int size = orderItems.size();
		for (int i = 0; i < size; i++) {
			OrderItem oi = orderItems.get(i);
			OrderItemPay orderItemPay = new OrderItemPay();
			orderItemPay.setPayCode(orderPay.getPayCode());
			orderItemPay.setPayName(orderPay.getPayName());
			//BeanUtils.copyProperties(orderPay, orderItemPay);
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
			orderItemPay.setOrderNo(orderPay.getOrderNo());
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

	@Override
	public boolean saveOrderPaymentFromPage(OrderPay orderPay) {
		
		String orderSubNo = orderNoService.getOrderSubNoByOrderNo(orderPay.getOrderNo(), 1);
		
		OrderSub os = orderSubService.getByOrderSubNo(orderSubNo);
		
		List<OrderItem> orderItems = orderItemService.findByField(OrderItem_.orderNo, orderPay.getOrderNo());
		
		OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderPay.getOrderNo());
		
		// 新增加支付信息
		if(orderPay.getId() == null){
			OrderPay op = new OrderPay();
			BeanUtils.copyProperties(orderPay, op);
			op.setIdOrder(os.getIdOrder());					
			op.setBillType(os.getBillType());
			op.setPayTime(new Date());
			op.setDateCreated(new Date());
			op.setDateUpdated(new Date());
			op.setIsDeleted(0l);
			orderPayService.save(op);
			this.saveOrderItemPayFromIntf(orderMain, os, orderItems, op);
			
			// 更新ORDER_PAY_MODE的支付状态标识
			OrderPayMode orderPayMode = orderPayModeService.getByField(OrderPayMode_.orderNo, orderPay.getOrderNo());
			if(null != orderPayMode)
			{
				orderPayMode.setPayStatus(Long.valueOf(CommonConst.OrderPayMode_PayStatus_HadPaid.getCode()));
				orderPayModeService.update(orderPayMode);
			}
			
		
		// 更新支付信息
		} else {
			OrderPay op = orderPayService.get(orderPay.getId());
			op.setPayCode(orderPay.getPayCode());
			op.setPayName(orderPay.getPayName());
			op.setPayAmount(orderPay.getPayAmount());
			op.setSerialNo(orderPay.getSerialNo());
			op.setDateUpdated(new Date());
			orderPayService.update(op);
			//先删除旧的支付行{orderItemPay(IsDeleted==1) }，在添加支付行
			
			orderItemPayService.deleteByOrderNoAndPayCode(orderPay.getPayCode(),orderPay.getOrderNo());
			this.saveOrderItemPayFromIntf(orderMain, os, orderItems, op);
		}
	
		return true;
	}

	@Override
	public boolean deleteOrderPaymentFromPage(String id) {
		
		OrderPay op = orderPayService.get(NumberUtils.toLong(id));
		op.setDateUpdated(new Date());
		op.setIsDeleted(1L);
		orderItemPayService.deleteByOrderNoAndPayCode(op.getOrderNo(),op.getPayCode());
		orderPayService.save(op);
		return true;
	}

}
