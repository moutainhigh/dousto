package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.util.SpringContextUtil;

public class CancelNoPayOrderJobDetail extends QuartzJobBean {
	
	private static Logger log = LoggerFactory.getLogger(CancelNoPayOrderJobDetail.class);

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		Object otargetObject = SpringContextUtil.getBean("cancelNoPayOrderService");

		Method m = null;
		try {
			m = otargetObject.getClass().getMethod("cancelNoPayOrder",
					new Class[] { int.class });
			m.invoke(otargetObject, new Object[] { 50 });
		} catch (Exception e) {
			log.error("CancelNoPayOrderJobDetail-->executeInternal", e);
		}
	}

}
