package com.ibm.sc.oms.service.sale.impl;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.SubbmitOrderService;
import com.ibm.oms.service.business.SubbmitOrderValidateService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

public class SubbmitOrderCreateServiceTest extends BaseTest {
	@Autowired
	CommonTestUtil commonTestUtil;
	@Autowired
	SubbmitOrderService  subbmitOrderService;
	@Autowired
	SubbmitOrderValidateService orderValidateService;
	@Autowired
	@Qualifier("orderCreateService")
	OrderCreateService orderCreateService;
	
	@Test
	public void testSubbmitOrder(){
		ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile(CommonTestConst.subbmitReceiveOrderDTO,ReceiveOrderMainDTO.class);
		
		Map<String,Object> output1 = subbmitOrderService.handleSubbmitOrder(receiveOrderMainDTO);
		 
		//BtcOmsReceiveOrderOutputDTO output1 = orderCreateService.createOrder(dto1);
	      ObjectMapper om = new ObjectMapper();
	      String outputStr;
	      try {
	          outputStr = om.writeValueAsString(output1);
	          logger.info(outputStr);
	      } catch (JsonProcessingException e) {
	          logger.info("{}", e);
	      }
	     
	}
	
	@Test
	public void btcOmsReceiveOrderDTO(){
		BtcOmsReceiveOrderDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile(CommonTestConst.BtcOmsReceiveOrderDTO4Test,BtcOmsReceiveOrderDTO.class);
		BtcOmsReceiveOrderOutputDTO output1 = orderCreateService.createOrder(receiveOrderMainDTO);
		//BtcOmsReceiveOrderOutputDTO output1 = orderCreateService.createOrder(dto1);
	      ObjectMapper om = new ObjectMapper();
	      String outputStr;
	      try {
	          outputStr = om.writeValueAsString(output1);
	          logger.info(outputStr);
	      } catch (JsonProcessingException e) {
	          logger.info("{}", e);
	      }
	     
	}
	
	
	
	
	 
}
