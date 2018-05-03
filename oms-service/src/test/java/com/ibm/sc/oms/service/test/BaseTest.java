package com.ibm.sc.oms.service.test;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {
    protected static String invPrefix = "http://192.168.163.234:8082/pdm-rs";
    
	@Autowired
	ApplicationContext context;
	protected static Log log = LogFactory.getLog(BaseTest.class);
	@Test
	public void testMessageSender() {
		Calendar now = Calendar.getInstance();
		log.error("开始时间："+now.getTime().toLocaleString());
		log.error("结束时间："+Calendar.getInstance().getTime().toLocaleString());
	}
	
	
	public static void logInfoObjectToJson(String tip,Logger logger,Object o){
		 ObjectMapper om = new ObjectMapper();
	     String outputStr;
	     try {
	         outputStr = om.writeValueAsString(o);
	         logger.info("[{}:{}]",tip,outputStr);
	     } catch (JsonProcessingException e) {
	         logger.info("{}", e);
	     }
	}
	
}
