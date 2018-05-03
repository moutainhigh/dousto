package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderItemAbnDao;
import com.ibm.oms.domain.persist.OrderItemAbn;
import com.ibm.oms.service.OrderItemAbnService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:13
 * @author:Yong Hong Luo
 */
@Service("orderItemAbnService")
public class OrderItemAbnServiceImpl extends BaseServiceImpl<OrderItemAbn,Long> implements
		OrderItemAbnService{
    
	private OrderItemAbnDao orderItemAbnDao;
    
	@Autowired
	public void setOrderItemAbnDao(OrderItemAbnDao orderItemAbnDao) {
	    super.setBaseDao(orderItemAbnDao);
		this.orderItemAbnDao = orderItemAbnDao;
	}
}
