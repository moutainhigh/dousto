package com.ibm.sc.oms.service.sale.rs;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.business.CancelNoPayOrderService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.err.impl.OrderProcessResumer;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author pjsong
 *
 */
public class OtherRSOrderCreateServiceImplTest extends BaseTest{
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
    
    String testlocalBtcOmsReceive = "http://192.168.163.225:9080/sup-rs/message/sendMessage";
    String testRemoteBtcOmsReceive = "http://192.168.163.234:8081/oms-rs/btcoms/b2-oms-receive-order";

    
    String operateGet = "http://localhost:8080/oms-rs/btcoms/operate?orderNo=%s&operate_code=%s";
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired 
    OrderStatusSyncLogService orderStatusSyncLogService;
    
    @Test
    public void testPromoDeduct(){
        String orderNo="1029010645";
        String orderSubNo="102901064501";
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        OrderSub os = om.getOrderSubs().get(0);
//        orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(orderSubNo),
//                OrderStatusAction.S011030, null, null, null);
        orderStatusService.saveProcess(orderSubNo,
                OrderStatusAction.S013020, null, null, null);
        //bbc货到付款，同步R3
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNo);
        if (opList != null && !opList.isEmpty()) {
            //("I-OMS-R3-01","订单明细"),
            orderStatusSyncLogService.saveAndcreate(om, os,
                    CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
            //"I-OMS-R3-02","支付明细"
            /*orderStatusSyncLogService.saveAndcreate(om, os, 
                    CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());*/
        }
        orderCreateService.callSingleProcess(orderNo);
    }
    //    @Test
    public final void testBtcOmsReceive() {
        Object dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpBtcPay, BtcPayDTO.class);
        String[] testStr = commonUtilService.getOrdeNoFromFCObject(dto1);
        try {
            Object output2 = commonUtilService.jsonPost(testlocalBtcOmsReceive, dto1, Object.class, null);
//            logger.info(output2.toString());
        } catch (Exception e0) {
            logger.info("{}", e0);
        }
    }
    
//  @Test
  public final void testTemp() {
      String toSendStr = tmsOmsLogisticsStatusTransService.queryToTmsStr("140614000201301", CommonConstService.TMS_TYPE_OS);
      logger.info(toSendStr);
  }
  
    @Test
    public final void testResume() {
        orderCreateServiceImpl.callSingleProcess("1407100003622");
    }
    
//    @Test
    public final void testQuartzMethods() {
        cancelNoPayOrderService.remindMsgSent();
    }
//    @Test
    public final void testSendEmail() {
        OrderMain om = orderMainService.findByOrderNo("1405290001613");
        String orderNo = om.getOrderNo();
        String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
        SupportResend rsInput = orderCreateTrans.querySupportSendEmail(orderSubNo);
        
        StringWriter out = new StringWriter();
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper( new DefaultObjectWrapper() );
        Map<String, Object> map = rsInput.getData();
        Template temp;
        try {
            temp = cfg.getTemplate( CommonTestConst.fpOrderGeneratedEmail );
            temp.process( map, out );
        } catch (Exception e) {
            logger.info("{}", e);
        }
        System.out.println( out.getBuffer().toString() );
//        orderCreateService.supportSendEmail(om);
    }
//    @Test
    public final void testSendEmailByIntf() {
        String orderNo = "1405290001613";
        orderCreateService.supportSendEmail(orderNo);
    }
    
}
