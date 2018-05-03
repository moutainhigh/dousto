package com.ibm.sc.oms.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.service.business.OrderStatusTransferReturnService;
import com.ibm.sc.oms.service.test.BaseTest;

public class OrderStatusTransferReturnServiceImplTest extends BaseTest{
	@Autowired
	OrderStatusTransferReturnService orderStatusTransferReturnService;
	
	@Test
	public void  handleOrderStatusTransferReturnTest(){
		String orderSubNo = "540700005601";
		orderStatusTransferReturnService.handleOrderStatusTransferReturn(orderSubNo) ;
	}
}
