package com.ibm.sc.oms.service.sale.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BBCLogiDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberAuditOutputDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.impl.BtcOrderPaymentServiceImpl;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * @author pjsong
 * 
 */
public class OrderCreateServiceImplTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderCreateServiceImplTest.class);
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    BtcOrderPaymentServiceImpl btcOrderPaymentServiceImpl;
    @Autowired
    OrderNoService orderNoService;
    @Test
    public final void testBtcOmsReceive() {
        BtcOmsReceiveOrderDTO dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.btcOmsReceiveOrderDTO3,
                BtcOmsReceiveOrderDTO.class);
        BtcOmsReceiveOrderOutputDTO output1 = orderCreateService.createOrder(dto1);
        ObjectMapper om = new ObjectMapper();
        String outputStr;
        try {
            outputStr = om.writeValueAsString(output1);
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            logger.info("{}", e);
        }
        logger.info(output1 == null ? "" : output1.getMapList().toString());
        if (output1 != null) {
            logger.info(output1.getSucceed());
        }
    }
//    @Test
    public final void autoAudit(){
        String orderNo = "1016003720";
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        orderCreateService.autoAudit(orderNo);
    }

//    @Test
    public void returnCoupon(){
        boolean dealCoupon = orderCreateService.returnCouponForCancel(orderNoService.getOrderNoBySubNo("20140424000032501"));

    }
    
     @Test
    public final void btcPay() {
        BtcPayDTO payDto = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpBtcPay, BtcPayDTO.class);
        CommonOutputDTO output = btcOrderPaymentServiceImpl.handlerBtcOrderPayment(payDto);
        logger.debug(output.getCode());
    }
//     @Test
    public final void mem01Pay() {
         OmsMemberAuditOutputDTO output = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpMem01, OmsMemberAuditOutputDTO.class);
         boolean priviledagedMember = output != null && output.getPriviledgedMember() != null && output.getPriviledgedMember();
         if (priviledagedMember) {
//             om.setIfPriviledgedMember(CommonConstService.BOOLEAN_TRUE);
         }else{
//             om.setIfPriviledgedMember(CommonConstService.BOOLEAN_FALSE);
         }
         
         boolean blackListIncluded = output != null && output.getBlackListIncluded() != null && output.getBlackListIncluded();
         if (blackListIncluded) {
//             om.setIfBlackListMember(CommonConstService.BOOLEAN_TRUE);
         }else{
//             om.setIfBlackListMember(CommonConstService.BOOLEAN_FALSE);
         }
//        logger.debug(output.getCode());
    }
//     @Test
    public final void bbcOperate() {
        orderCreateService.bbcOperateToOms("20140428000038501", "1", "bbc");
        logger.info("om");
    }

//    @Test
    public final void promoResourceAdd() {
        orderCreateService.promoResourceAdd("SHO140410037994", "1024003807");
    }

    // @Test
    public final void bbcLogistics() {
        BBCLogiDTO bbcLogiDTO = new BBCLogiDTO();
        bbcLogiDTO.setOrderSubNo(CommonTestConst.targetOrderSubNo);
        bbcLogiDTO.setLogisticsOutNo("xx");
        bbcLogiDTO.setDeliveryMerchantName("xx");
        orderCreateService.bbcLogisticsVerified(bbcLogiDTO);
    }
}
