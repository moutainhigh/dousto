package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.util.SpringContextUtil;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年1月31日 
 */
public class orderCommentJobDetail extends QuartzJobBean{

	private static Logger log = LoggerFactory.getLogger(orderCommentJobDetail.class);
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub

		Object otargetObject = SpringContextUtil.getBean("orderCommentService");

		Method m = null;
		try {
			m = otargetObject.getClass().getMethod("orderComment",
					new Class[] { int.class });
			m.invoke(otargetObject, new Object[] { 50 });
		} catch (Exception e) {
			log.error("orderCommentJobDetail-->executeInternal", e);
		}
	
		// TODO Auto-generated method stub
		
	
		
	}

}
