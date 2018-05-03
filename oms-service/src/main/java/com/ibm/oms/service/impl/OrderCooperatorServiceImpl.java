package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderCooperatorDao;
import com.ibm.oms.domain.persist.OrderCooperator;
import com.ibm.oms.service.OrderCooperatorService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:19:27
 * @author:Yong Hong Luo
 */
@Service("orderCooperatorService")
public class OrderCooperatorServiceImpl extends BaseServiceImpl<OrderCooperator,Long> implements
		OrderCooperatorService{
    
	private OrderCooperatorDao orderCooperatorDao;
    
	@Autowired
	public void setOrderCooperatorDao(OrderCooperatorDao orderCooperatorDao) {
	    super.setBaseDao(orderCooperatorDao);
		this.orderCooperatorDao = orderCooperatorDao;
	}
}
