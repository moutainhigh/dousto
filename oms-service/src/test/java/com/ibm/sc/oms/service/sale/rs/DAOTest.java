package com.ibm.sc.oms.service.sale.rs;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.CancelNoPayOrderService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.err.impl.OrderProcessResumer;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTXTest;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * @author pjsong
 *
 */
public class DAOTest extends BaseTXTest{
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderProcessResumer orderProcessResumer;
    @Autowired
    OrderCreateServiceImpl orderCreateServiceImpl;
    @Autowired
    TmsOmsLogisticsStatusTransService tmsOmsLogisticsStatusTransService;
    @Autowired
    CancelNoPayOrderService cancelNoPayOrderService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderCreateTrans orderCreateTrans;
    

    @Test
    public void testPromoDeduct(){
        
        OrderMain om = orderMainService.get(10000335l);
        List<OrderPay> ops = om.getOrderPays();
        List<OrderSub> oss = om.getOrderSubs();
        logger.info(ops.size());
        logger.info(oss.size());
    }

}
