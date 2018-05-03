package com.ibm.sc.task;

import com.ibm.oms.service.util.SpringContextUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * @author: mr.kai
 * @Description: 每天同步销售实收汇总定时任务类
 * @create: 2018-03-19 11:01
 **/
public class SynSalesReceiptsOrderJobDetail  extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(SynSalesReceiptsOrderJobDetail.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Object otargetObject = SpringContextUtil.getBean("synSalesReceptsOrderService");
        logger.info("向sap推送订单数据job启动......");
        Method m = null;
        try {
            m = otargetObject.getClass().getMethod("synSalesReceptsOrder");
            m.invoke(otargetObject);
        } catch (Exception e) {
            logger.error("SynSalesReceiptsOrderJobDetail-->executeInternal", e);
        }
    }
}
