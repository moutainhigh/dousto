package com.ibm.sc.oms.service.retchg.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.sc.oms.service.test.BaseTest;

@SuppressWarnings("javadoc")
public class OrderReasonServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderReasonService orderReasonService;

    @Test
    public final void test() {
        Map<OrderReason, List<OrderReason>> result = orderReasonService.getReasonMap();
        Iterator it = result.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            OrderReason parentR = (OrderReason) entry.getKey();
            List<OrderReason> childR = (List<OrderReason>) entry.getValue();
            System.out.println(parentR.getReasonName());

        }

    }

}
