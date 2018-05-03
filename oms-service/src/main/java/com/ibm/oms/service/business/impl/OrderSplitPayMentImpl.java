package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.business.OrderSplitPayMent;


@Service
public class OrderSplitPayMentImpl implements OrderSplitPayMent{
	@Autowired
	OrderItemPayService orderItemPayService;
	@Autowired
	OrderItemService orderItemService;
	@Override
	public Map<String, OrderItemPay> splitPayMent(List<OrderItem> orderItems, OrderPay op) {
	      BigDecimal total = new BigDecimal(0.0);
	        for (OrderItem oi : orderItems) {
	            // 订单行应付金额:折后总价
	           // total = total.add(oi.getPayAmount());
	        	  total = total.add(oi.getSaleTotalMoneyDiscount());
	        }
	        BigDecimal addedPayment = new BigDecimal(0.0);
	        BigDecimal payAmount = op.getPayAmount();
	        int size = orderItems.size();
	        Map<String, OrderItemPay> ret = new HashMap<String, OrderItemPay>();
	        for (int i = 0; i < size; i++) {
	            OrderItem oi = orderItems.get(i);
	            OrderItemPay orderItemPay = new OrderItemPay();
	            BeanUtils.copyProperties(op, orderItemPay);
	            // last item
	            if (i == (size - 1)) {
	                BigDecimal payment = payAmount.subtract(addedPayment);
	                orderItemPay.setPayAmount(payment);
	                saveSpiteItem(oi, op, payment);
	            } else {
	                BigDecimal payment = payAmount.multiply(oi.getSaleTotalMoneyDiscount()).divide(total ,2, RoundingMode.HALF_UP);
	                addedPayment = addedPayment.add(payment);
	                orderItemPay.setPayAmount(payment);
	                saveSpiteItem(oi, op, payment);
	            }
	            Date date = new Date();
	            orderItemPay.setIdOrderItem(oi.getIdOrder());
	            orderItemPay.setIdOrderItem(oi.getId());
	            orderItemPay.setDateUpdated(date);
	            orderItemPay.setDateCreated(date);
	            orderItemPay.setPayCode(op.getPayCode());
	            orderItemPay.setPayName(op.getPayName());
	            orderItemPay.setOrderItemNo(oi.getOrderItemNo());
	            orderItemPayService.save(orderItemPay);
	            ret.put(oi.getOrderItemNo(), orderItemPay);
	        }
	        return ret;
	}
	
	
	private void saveSpiteItem(OrderItem oi, OrderPay op, BigDecimal payment) {
		// 均摊订单支付总价 最后均摊
		// 均摊订单支付的总价
		settingPayAmount(oi, op, payment);
		settingCouponPayment(oi, op, payment);
		settingIntegralPayment(oi, op, payment);
		settingPoints(oi, op, payment);
		orderItemService.save(oi);
	}

	private void settingPayAmount(OrderItem oi, OrderPay op, BigDecimal payment) {
		if(PayMode.WAIT.getCode().equals(op.getPayCode())){
			oi.setPayAmount(payment);
		}
		
	}


	private void settingPoints(OrderItem oi, OrderPay op, BigDecimal payment) {
		if(PayMode.POINT.getCode().equals(op.getPayCode())){
			oi.setSharePoint(payment);
		}
		
	}


	private void settingIntegralPayment(OrderItem oi, OrderPay op, BigDecimal payment) {
		if(PayMode.INTEGRAL.getCode().equals(op.getPayCode())){
			oi.setSharePointAmount(payment);
		}
		
	}


	private void settingCouponPayment(OrderItem oi, OrderPay op, BigDecimal payment) {
		if(PayMode.COUPON.getCode().equals(op.getPayCode())){
			oi.setCouponAmount(payment);
		}
		
	}





}
