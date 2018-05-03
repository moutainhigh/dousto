package com.ibm.sc.oms.service.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test.xml" })
public class BaseTXTest extends AbstractTransactionalJUnit4SpringContextTests {
    protected static String invPrefix = "http://192.168.163.234:8082/pdm-rs";
    
	@Autowired
	ApplicationContext context;
	@Autowired
	OrderMainService orderMainService;
	protected static Log log = LogFactory.getLog(BaseTXTest.class);
	@Test
	public void testMessageSender() {
		OrderMain om = orderMainService.findByOrderNo("1406070000335");
		om.setOrderCategory("sale");
		logger.debug("om");
	}

}
