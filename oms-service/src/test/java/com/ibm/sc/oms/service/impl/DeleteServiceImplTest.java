package com.ibm.sc.oms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.sc.oms.service.test.BaseTest;

public class DeleteServiceImplTest extends BaseTest{
	@Autowired
//	@Qualifier ( "orderMainService" )  
	OrderMainService orderMainService;
	
	@Test
	public void testInsertOrderMain() {
		OrderMain om = new OrderMain();
		om.setOrderNo("221");
		om.setConfirmerName("xxxuy");
		om.setBillType(new Long("1"));
		orderMainService.save(om);
		log.info("success");
	}
	
	@Test
	public void testListOrderMain(){
		List<OrderMain> ret = orderMainService.getAll();
		System.out.println(ret.size());
	}

}
