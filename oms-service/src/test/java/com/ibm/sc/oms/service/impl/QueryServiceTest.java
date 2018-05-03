package com.ibm.sc.oms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.impl.OrderMainDaoImpl;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.sc.oms.service.test.BaseTest;

public class QueryServiceTest extends BaseTest{
	@Autowired
//	@Qualifier ( "orderMainService" )  
	OrderMainService orderMainService;
	
	@Test
	public void QueryTest() {
		orderMainService.updateWithSql();
		log.info("success");
	}
	
	@Test
	public void testListOrderMain(){
		List<OrderMain> ret = orderMainService.getAll();
		System.out.println(ret.size());
	}

}
