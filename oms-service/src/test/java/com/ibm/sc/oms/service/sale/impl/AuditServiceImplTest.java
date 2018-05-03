package com.ibm.sc.oms.service.sale.impl;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.sc.oms.service.test.BaseTest;

public class AuditServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    OrderCreateService orderCreateService;
    @Test
    public void intfReceivedTest() {
//        int count = orderMainService.getOMListByMemberNoAndPeriod("11", new Date().getTime() - 10*24*3600*1000, new Date().getTime());
//        System.out.println(count);
//        orderCreateService.autoAudit(new OrderMain(), new OrderSub());
        Calendar calendar7DaysBefore = Calendar.getInstance();
        calendar7DaysBefore.add(Calendar.DATE, -7);
        Long t = calendar7DaysBefore.getTimeInMillis();
        Long t2 = new Date().getTime()-7*24*3600*1000;
        System.out.println(t+","+t2);
    }
}