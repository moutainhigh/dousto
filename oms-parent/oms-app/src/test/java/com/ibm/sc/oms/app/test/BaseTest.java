package com.ibm.sc.oms.app.test;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//AbstractTransactionalJUnit4SpringContextTests
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test.xml" })
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class BaseTest extends AbstractJUnit4SpringContextTests {
	protected static Log log = LogFactory.getLog(BaseTest.class);
	@Test
	public void testMessageSender() {
		Calendar now = Calendar.getInstance();
		log.error("开始时间："+now.getTime().toLocaleString());
		log.error("结束时间："+Calendar.getInstance().getTime().toLocaleString());
	}

}
