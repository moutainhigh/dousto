package com.ibm.sc.oms.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.service.StatusDictService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.sc.oms.service.test.BaseTest;

public class OrderStatusServiceImplTest extends BaseTest{
    @Autowired
    OrderStatusService oss;
    @Autowired
    StatusDictService sds;
    @Test
    public final void testProcess() {
         List<StatusDict> retList = sds.getAll();
         for(StatusDict d:retList){
             System.out.println(d.getStatusCode());
         }
         oss.saveProcess("1001", OrderStatusAction.S011020, null, null, null);
//        oss.process("xxx", OrderStatusType.Order_Status_Positive, OrderStatusPositive.ORDER_CREATING);
    }

}
