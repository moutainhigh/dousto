package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.util.SpringContextUtil;

/**
 * Description: 订单审核定时任务  
 * @author YanYanZhang
 * Date:   2018年3月10日 
 */
public class OrderAuditJobDetail extends QuartzJobBean{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderAuditJobDetail.class); 
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("excuteInternal->>>>>automatic audit order");
		
		Object otargetObject = SpringContextUtil.getBean("orderMainService");

		Method m = null;
		try {
			m = otargetObject.getClass().getMethod("automaticAuditOrder");
			m.invoke(otargetObject);
		} catch (Exception e) {
			LOGGER.error("OrderAuditJobDetail error", e);
		}
	}

}
