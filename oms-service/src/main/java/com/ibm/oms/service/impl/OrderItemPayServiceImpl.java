package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderItemPayDao;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:35
 * @author:Yong Hong Luo
 */
@Service("orderItemPayService")
public class OrderItemPayServiceImpl extends BaseServiceImpl<OrderItemPay,Long> implements
		OrderItemPayService{
    
	private OrderItemPayDao orderItemPayDao;
    
	@Autowired
	public void setOrderItemPayDao(OrderItemPayDao orderItemPayDao) {
	    super.setBaseDao(orderItemPayDao);
		this.orderItemPayDao = orderItemPayDao;
	}

	@Override
	public boolean deleteByOrderNoAndPayCode(String orderNo, String payCode) {
		return orderItemPayDao.deleteByOrderNoAndPayCode(orderNo,payCode) >0;
	}

}
