package com.ibm.sc.oms.service.sale.rs;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.CombineProductDTOList;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.CouponPayDTO;
import com.ibm.oms.intf.intf.LogisticsDTO;
import com.ibm.oms.intf.intf.MemberPayDTO;
import com.ibm.oms.intf.intf.OmsMemberAuditOutputDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.intf.intf.SupportResendOutputDTO;
import com.ibm.oms.intf.intf.TransportCompParam;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;
import com.ibm.sc.oms.service.test.Env;
import com.ibm.sc.oms.service.test.OmsTestDataBuilder;

/**
 * @author pjsong
 * 
 */
public class RSOrderCreateServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    static String memberNo = "700";
    static String memberNo_false = "900";
    @Autowired
    OrderCreateServiceImpl orderCreateService;
    @Autowired
    Env env;
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OmsTestDataBuilder omsTestDataBuilder;

//     @Test
    public final void testBtcOmsReceive() {
        BtcOmsReceiveOrderDTO dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpBtcOmsReceiveOrder,
                BtcOmsReceiveOrderDTO.class);
        BtcOmsReceiveOrderOutputDTO output2 = commonUtilService.jsonPost(env.getBtc01(), dto1,
                BtcOmsReceiveOrderOutputDTO.class, null);
        ObjectMapper om = new ObjectMapper();
        String outputStr;
        try {
            outputStr = om.writeValueAsString(output2);
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(output2.getMapList().toString());
    }

//    @Test
    public final void testMem03MyCard() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl) orderCreateService;
        String urlMem03 = orderCreateServiceImpl.getMem03();//
        MemberPayDTO rsInput = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpMem03, MemberPayDTO.class);
        CommonOutputDTO output = null;
        boolean exceptionThrown = false;
        try {
            output = commonUtilService.jsonPost(urlMem03, rsInput, CommonOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }
        ObjectMapper om = new ObjectMapper();
        String outputStr = om.writeValueAsString(output);
        logger.info("{}", outputStr);
    }

//    @Test
    public final void testPromo01() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl) orderCreateService;
        String urlPromo01 = orderCreateServiceImpl.getPromo01();//
        CouponPayDTO rsInput = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpPromo01, CouponPayDTO.class);
        CommonOutputDTO output = null;
        boolean exceptionThrown = false;
        
        try {
            output = commonUtilService.jsonPost(urlPromo01, rsInput, CommonOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }
        ObjectMapper om = new ObjectMapper();
        String callStr = om.writeValueAsString(rsInput);
        logger.info(callStr);
        String outputStr = om.writeValueAsString(output);
        logger.info("{}", outputStr);
    }

//    @Test
    public final void testSupportResend() {
        SupportResend dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpSupportResend, SupportResend.class);
        ObjectMapper om = new ObjectMapper();
        String outputStr;
        try {
            String callStr = om.writeValueAsString(dto1);
            // {"type":"sms","code":"CM-BBC-ZITI","data":{"recipients":"11","checkCode":null}}
            logger.info(callStr);
            SupportResendOutputDTO output2 = commonUtilService.jsonPost(orderCreateService.getSup01(), dto1,
                    SupportResendOutputDTO.class, null);
            outputStr = om.writeValueAsString(output2);
            // {"code":"success","message":"ok"}
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            logger.info("{}", e);
        }
    }

//     @Test
    public final void testBbcOperate() {
        String orderSubNo = CommonTestConst.targetOrderSubNo;
        Long timeStart = System.currentTimeMillis();
        String operateGet = env.getBbc01();//
        CommonOutputDTO output1 = null;
        try {
            output1 = commonUtilService.jsonGet(String.format(operateGet, orderSubNo, "1", null),
                    CommonOutputDTO.class, null);
            logger.info(output1.getMsg());
        } catch (Exception e) {
            logger.info("{}", e);
        }
        logger.info((System.currentTimeMillis() - timeStart) + "");

        CommonOutputDTO output2 = commonUtilService.jsonGet(String.format(operateGet, orderSubNo, "2"),
                CommonOutputDTO.class, null);
        logger.info(output1==null ? "": output1.getMsg());
        CommonOutputDTO output3 = commonUtilService.jsonGet(String.format(operateGet, orderSubNo, "3"),
                CommonOutputDTO.class, null);
        logger.info(output3 == null? "" : output1.getMsg());
    }

    // @Test
    public final void testCombineProduct() {
        // 开始调用接口
        CombineProductDTOList output = null;
        boolean exceptionThrown = false;
        ObjectMapper om = new ObjectMapper();
        long timeStart = System.currentTimeMillis();
        try {
            List<String> input = omsTestDataBuilder.buildCombineNoStr();
            String callStr = om.writeValueAsString(input);
            logger.info(callStr);
            output = commonUtilService.jsonPost(orderCreateService.getProduct01(), input, CombineProductDTOList.class,
                    null);
        } catch (Exception e) {
            logger.info("time elapsed {}, {}", (System.currentTimeMillis() - timeStart) / 1000, e);
            exceptionThrown = true;
        }
        logger.info("time elapsed {}", (System.currentTimeMillis() - timeStart));
        String callStr = null;
        ;
        try {
            callStr = om.writeValueAsString(output);
        } catch (JsonProcessingException e) {
            logger.info(callStr);
        }
    }

    // @Test
    public final void testMember01() {
        // 开始调用接口
        String mem01 = orderCreateService.getMem01();//
        OmsMemberAuditOutputDTO output = null;
        Long startTime = System.currentTimeMillis();
        try {
            output = commonUtilService.jsonGet(String.format(mem01, memberNo), OmsMemberAuditOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("time elaspe:{}, {}", (System.currentTimeMillis() - startTime) / 1000, e);
            return;
        }
        try {
            ObjectMapper omapper = new ObjectMapper();
            logger.info(omapper.writeValueAsString(output));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//     @Test
    public final void testMember04() {
        // spiteRise("炒货"), 
//        spiteIndent("恶意下单拒收"),
//        spiteComment("恶评")
//         String orderNo = CommonTestConst.targetOrderNo;
         String orderNo  = "201404110000205";
        String mem01 = orderCreateService.addBlackList(orderNo, "spiteRise");
        logger.info(mem01);
    }
    
    // @Test
    public final void testLogistics01() {
        // 开始调用接口
        String logistics01 = orderCreateService.getLogistics01();//
        String storageId = "1";
        String areaId = "120629";
        String catagoryId = "992";
        String deliverTypeCode = "2";
        String paymentModeId = "2";

        TransportCompParam tcParam = new TransportCompParam();
        tcParam.setStorageId(storageId);
        tcParam.setAreaId(areaId);
        tcParam.setCatagoryId(catagoryId);
        tcParam.setDeliverTypeCode(deliverTypeCode);// 正向 ：前台页面选，天虹配送, 门店自提等等 .逆向 ：上门取货，客户寄回，门店代退

        // 非货到付款，写在线支付id
        tcParam.setPaymentModeId(paymentModeId);
        // String url = String.format(logistics01, storageId, areaId, catagoryId,deliverTypeCode, paymentModeId);
        LogisticsDTO rsData = null;
        boolean ExceptionThrown = false;
        Long startTime = System.currentTimeMillis();
        ObjectMapper omapper = new ObjectMapper();
        try {
            logger.info(omapper.writeValueAsString(tcParam));
            rsData = commonUtilService.jsonPost(logistics01, tcParam, LogisticsDTO.class, null);
        } catch (Exception e) {
            logger.info("{},{}", logistics01, e);
            ExceptionThrown = true;
        }
        logger.info("{}", (System.currentTimeMillis() - startTime) / 1000 + "");
        try {
            logger.info(omapper.writeValueAsString(rsData));
        } catch (JsonProcessingException e) {
            logger.info("{}", e);
        }
    }
     @Test
    public final void testDealErr() {
        // 开始调用接口
        orderCreateService.dealErr(856l);//
    }
}
