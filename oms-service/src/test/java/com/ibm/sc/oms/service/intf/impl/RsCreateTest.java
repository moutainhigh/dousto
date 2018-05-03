package com.ibm.sc.oms.service.intf.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.inner.CombineProductDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
//在线察看编辑：
//http://www.jsoneditoronline.org/
public class RsCreateTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderCreateServiceImpl orderCreateService;
    @Autowired
    CommonUtilService commonUtilService;
    @Test
    public void callInventoryLock() {
        String orderNo = "140711000363301";
        orderCreateService.inventoryLock(orderNo);
    }
    
    @Test
    public void callMemberBlackList() {
        OrderMain om = new OrderMain();
        om.setMemberNo("700");//black
        om.setMemberNo("900");//white
        orderCreateService.autoAudit(CommonTestConst.targetOrderNo);
    }
    
//    @Test
    public void callInventoryDeduct() {
        
    }

    public void callCombineProduct(){
//        1396962681402
//        ZH1397187791203
//        ZH1397047936226
//        ZH1397048181569

        CombineProductDTO pcDTO = commonUtilService.jsonGet(orderCreateService.getProduct01(), CombineProductDTO.class, null);
    }
}
