package com.ibm.sc.oms.service.sale.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.impl.WmsOmsLogisticsStatusServiceImpl;
import com.ibm.sc.oms.service.test.BaseTest;

/**
 * @author pjsong
 *
 */
public class OrderCreateScheduleTest extends BaseTest{
    private static final Logger logger = LoggerFactory.getLogger(OrderCreateScheduleTest.class);
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    WmsOmsLogisticsStatusServiceImpl wmsOmsLogisticsStatusServiceImpl;
    
    @Test
    public final void testBtcOmsReceive() {
        orderCreateService.processPostBTC(5);
    }

//    @Test
    public final void callSingleProcess() {
        orderCreateService.callSingleProcess("1031003821");
    }
}
