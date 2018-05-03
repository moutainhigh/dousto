package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderDicDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.service.OrderDicService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-04-29 01:49:04
 * @author:Yong Hong Luo
 */
@Service("orderDicService")
public class OrderDicServiceImpl extends BaseServiceImpl<OrderDic,Long> implements
		OrderDicService{
    
	private OrderDicDao orderDicDao;
    
	@Autowired
	public void setOrderDicDao(OrderDicDao orderDicDao) {
	    super.setBaseDao(orderDicDao);
		this.orderDicDao = orderDicDao;
	}
}
