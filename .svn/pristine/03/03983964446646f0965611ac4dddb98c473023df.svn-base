package com.ibm.sc.task;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ibm.oms.service.util.SpringContextUtil;

/**
 * @author xiaohl 处理传输WMS失败的售后意向单
 */
public class SaleAfterOrderToWMSJobDetail extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(SaleAfterOrderToWMSJobDetail.class);
    private int operateCount_max = 3; // 重复执行最大的次数

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        Method m = null;
        try {
            logger.info("处理传输WMS失败的售后意向单job启动......");
            Object otargetObject = SpringContextUtil.getBean("ordiErrOptLogService");
            m = otargetObject.getClass().getMethod("processSaleAfterOrderToWms", new Class[] { int.class });
            m.invoke(otargetObject, new Object[] { operateCount_max });
        } catch (Exception e) {
            logger.error("SaleAfterOrderToWMSJobDetail-->executeInternal", e);
        }
        logger.info("处理传输WMS失败的售后意向单job结束......");
    }

}
