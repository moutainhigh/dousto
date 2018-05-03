package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderCodeDictDao;
import com.ibm.oms.domain.persist.OrderCodeDict;
import com.ibm.oms.service.OrderCodeDictService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:19:04
 * @author:Yong Hong Luo
 */
@Service("orderCodeDictService")
public class OrderCodeDictServiceImpl extends BaseServiceImpl<OrderCodeDict,Long> implements
		OrderCodeDictService{
    
	private OrderCodeDictDao orderCodeDictDao;
    
	@Autowired
	public void setOrderCodeDictDao(OrderCodeDictDao orderCodeDictDao) {
	    super.setBaseDao(orderCodeDictDao);
		this.orderCodeDictDao = orderCodeDictDao;
	}
}
