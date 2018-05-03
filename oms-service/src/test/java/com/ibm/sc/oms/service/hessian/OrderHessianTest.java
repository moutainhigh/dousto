package com.ibm.sc.oms.service.hessian;

import java.net.MalformedURLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.order.create.OmsReceiveOrderOutputClientDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.client.dto.result.HessianResult;
import com.ibm.oms.client.intf.IOrderClient;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * Description: //模块目的、功能描述  
 * @author lvzhijun
 * Date:   2018年1月11日 
 */
public class OrderHessianTest { 
	Logger logger = LoggerFactory.getLogger(OrderHessianTest.class);
	@Test
    public void testHangOrderCreate(){
    	CommonTestUtil commonTestUtil = new CommonTestUtil();
    	String url = "http://localhost:8080/oms-rs/remoting/IOrderClient";  
    	HessianProxyFactory factory = new HessianProxyFactory();  
        try {
          IOrderClient bundleService = (IOrderClient) factory.create(IOrderClient.class, url);
          //ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile(CommonTestConst.subbmitReceiveOrderDTO,ReceiveOrderMainDTO.class);
         ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile("C:/Users/wangchao/Desktop/subbmit-order-test/hungCreate/Test.txt",ReceiveOrderMainDTO.class);
          HessianResult<OmsReceiveOrderOutputClientDTO> result = bundleService.tempHangOrderCreateNew(receiveOrderMainDTO);
          logInfoObjectToJson("result", logger, result);
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } 
    }
	
    
	@Test
    public void testOrderCreate(){
    	CommonTestUtil commonTestUtil = new CommonTestUtil();
    	String url = "http://localhost:8080/oms-rs/remoting/IOrderClient";  
    	HessianProxyFactory factory = new HessianProxyFactory();  
        try {
          IOrderClient bundleService = (IOrderClient) factory.create(IOrderClient.class, url);
          //ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile(CommonTestConst.subbmitReceiveOrderDTO,ReceiveOrderMainDTO.class);
          //接口传过来的参数
          ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromInterFile("C:/Users/wangchao/Desktop/subbmit-order-test/interface/wx-createOrder/WX.json",ReceiveOrderMainDTO.class,"request_data");
         //ReceiveOrderMainDTO receiveOrderMainDTO = commonTestUtil.genJsonObjFromFile("C:/Users/wangchao/Desktop/ordercreate-3.txt",ReceiveOrderMainDTO.class);
          HessianResult<OmsReceiveOrderOutputClientDTO> result = bundleService.CreateOrder(receiveOrderMainDTO);
          logInfoObjectToJson("result", logger, result);
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } 
    }
	
	@Test
	public void testOrderCreateBs(){
		CommonTestUtil commonTestUtil = new CommonTestUtil();
    	String url = "http://localhost:8080/oms-rs/remoting/IOrderClient";  
    	HessianProxyFactory factory = new HessianProxyFactory();  
        try {
          IOrderClient bundleService = (IOrderClient) factory.create(IOrderClient.class, url);
          String params = commonTestUtil.genJsonStrFromFile(CommonTestConst.btcOmsReceiveOrderDTOBS, BtcOmsReceiveOrderDTO.class);
          String  result = bundleService.createOrder(params);
          logInfoObjectToJson("result", logger, result);
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
	}
	
	
	
	
	
    
    
    
    public void logInfoObjectToJson(String tip, Logger logger, Object o) {
		ObjectMapper om = new ObjectMapper();

		try {
			String outputStr = om.writeValueAsString(o);
			logger.info("[{}:{}]", tip, outputStr);
		} catch (JsonProcessingException arg6) {
			logger.info("{}", arg6);
		}

	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
}
