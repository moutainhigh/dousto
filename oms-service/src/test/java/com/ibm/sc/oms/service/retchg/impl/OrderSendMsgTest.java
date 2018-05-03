package com.ibm.sc.oms.service.retchg.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderSendMsgService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;

@SuppressWarnings("javadoc")
public class OrderSendMsgTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    SaleAfterOrderService saleAfterOrderService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrderMainService orderMainService; 
    @Autowired
    OrderSubService orderSubService; 
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderPayService orderPayService; 
    @Autowired
    OrderPayModeService orderPayModeService;
    @Autowired
    private ImsOmsTransService imsOmsTransService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    OrderSendMsgService orderSendMsgService;
    
    public static void main(String[]args){
        String str = "a,b,c,";
        System.out.println(str.substring(0, str.length()-1));
    }
    
    @Test
    public final void sendMsgNoSendOrder(){ //未出库订单发送短信
        orderSendMsgService.sendMsgInStoreOrder();
    }
    
}
