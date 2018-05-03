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
 * 订单处理调度
 */
public class OrderHandlerJobDetail extends QuartzJobBean {
	
	private static Logger logger = LoggerFactory.getLogger(OrderHandlerJobDetail.class);
	private String numPerMinute = "10";
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		Object otargetObject = SpringContextUtil.getBean("orderCreateService");

		Method m = null;
		try {
		    logger.info("订单处理定时任务启动");
		    m = otargetObject.getClass().getMethod("processPostBTC",
					new Class[] { int.class });
			m.invoke(otargetObject, new Object[] { Integer.parseInt(numPerMinute) });
		} catch (Exception e) {
		    logger.error("OrderHandlerJobDetail-->executeInternal", e);
		}
		logger.info("订单处理定时任务结束");
	}
    public String getNumPerMinute() {
        return numPerMinute;
    }
    public void setNumPerMinute(String numPerMinute) {
        this.numPerMinute = numPerMinute;
    }

}
