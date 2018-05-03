package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.util.SpringContextUtil;

/**
 * @author pjsong
 * 12小时仍未支付的订单短信提醒
 */
public class SupportResend24HoursJobDetail extends QuartzJobBean {
	
	private static Logger log = LoggerFactory.getLogger(SupportResend24HoursJobDetail.class);

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		Object otargetObject = SpringContextUtil.getBean("cancelNoPayOrderService");

		Method m = null;
		try {
			m = otargetObject.getClass().getMethod("remindMsgSent");
			m.invoke(otargetObject);
		} catch (Exception e) {
			log.error("SupportResend24HoursJobDetail-->executeInternal", e);
		}
	}

}
