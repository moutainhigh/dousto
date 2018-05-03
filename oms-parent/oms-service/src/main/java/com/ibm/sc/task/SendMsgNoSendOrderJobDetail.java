package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.util.SpringContextUtil;

/**
 * @author xiaohl
 * 1）前一日20：30后——当日11点前产生的有效订单，若在当日中午13：00时订单状态仍未更改为“订单已出库”时，则系统在当日14：00 发送短信
 * 2）当日11：00后——当日20：30前产生的有效订单，若在当日晚上23：00时订单状态仍未更改为“订单已出库”时，则系统在次日早上9：00 发送短信
 */
public class SendMsgNoSendOrderJobDetail extends QuartzJobBean {
	
	private static Logger logger = LoggerFactory.getLogger(SendMsgNoSendOrderJobDetail.class);

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		Object otargetObject = SpringContextUtil.getBean("orderSendMsgService");
		Method m = null;
		logger.info("未按时出库订单发送短信job启动......");
		try {
			m = otargetObject.getClass().getMethod("sendMsgInStoreOrder");
			m.invoke(otargetObject);
		} catch (Exception e) {
		    logger.error("SendMsgNoSendOrderJobDetail-->executeInternal", e);
		}
		logger.info("未按时出库订单发送短信job结束......");
	}

}
