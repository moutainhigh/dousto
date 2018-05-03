package com.ibm.sc.oms.service.sale.rs;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.beans.stock.StockUnLockByOms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgOutputDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * @author pjsong
 * 
 */
public class InventoryServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    ImsOmsTransService imsOmsTransService;
    
    @Test
    public final void testInventoryDeductBuilder() throws JsonProcessingException {
        OmsSOInfo output = imsOmsTransService.queryInventoryDeduct("1025001183");
        ObjectMapper om = new ObjectMapper();
        String testStr = om.writeValueAsString(output);
        logger.info("{}", testStr);

    }
    
    
//     @Test
    public final void testInventoryLock() throws JsonProcessingException {
         OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl)orderCreateService;
         String testInventoryLock = orderCreateServiceImpl.getSim01();//
        StockLockByOms rsInput = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpRelInvLockJson,
                StockLockByOms.class);
        InventoryLockOutputDTO output = null;
        boolean exceptionThrown = false;
        try {
            output = commonUtilService.jsonPost(testInventoryLock,
                    rsInput, InventoryLockOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }
        ObjectMapper om = new ObjectMapper();
        String outputStr = om.writeValueAsString(output);
        logger.info("{}", outputStr);
    }

//     @Test
    public final void testInventoryUnLock() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl)orderCreateService;
        String testInventoryUnLock = orderCreateServiceImpl.getSim02();//

        StockUnLockByOms rsInput = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpRelInvUnLockJson,
                StockUnLockByOms.class);
        InventoryLockOutputDTO output = null;
        boolean exceptionThrown = false;
        try {
            Long t1 = System.currentTimeMillis();
            output = commonUtilService.jsonPost(testInventoryUnLock,
                    rsInput, InventoryLockOutputDTO.class, null);
            logger.info("{}", System.currentTimeMillis() - t1);
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }
        ObjectMapper om = new ObjectMapper();
        String outputStr = om.writeValueAsString(output);
        logger.info("{}", outputStr);
    }

//    @Test
    public final void testInventoryDeduct() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl)orderCreateService;
        String testInventoryDeduct = orderCreateServiceImpl.getSim03();//
        OmsSOInfo dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpRelInvDeductJson, OmsSOInfo.class);
        ObjectMapper om = new ObjectMapper();
        logger.info(om.writeValueAsString(dto1));
        InventoryLockOutputDTO output2 = null;
        try {
            output2 = commonUtilService.jsonPost(testInventoryDeduct, dto1, InventoryLockOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            return;
        }
        String outputStr;
        try {
            outputStr = om.writeValueAsString(output2);
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//     @Test
    public final void testInventoryUnDeduct() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl)orderCreateService;
        String testInventoryUnDeduct = orderCreateServiceImpl.getSim04();
        OmsSOInfo dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpRelInvDeductJson, OmsSOInfo.class);
        ObjectMapper om = new ObjectMapper();
        logger.info(om.writeValueAsString(dto1));
        InventoryLockOutputDTO output2 = null;
        try {
            output2 = commonUtilService.jsonPost(testInventoryUnDeduct, dto1, InventoryLockOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            return;
        }
        String outputStr;
        try {
            outputStr = om.writeValueAsString(output2);
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public final void testInventoryResend() throws JsonProcessingException {
        OrderCreateServiceImpl orderCreateServiceImpl = (OrderCreateServiceImpl)orderCreateService;
        String testInvResend = orderCreateServiceImpl.getSim05();//
        InventoryResendMsgDTO dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpInvResend,
                InventoryResendMsgDTO.class);
        ObjectMapper om = new ObjectMapper();
        logger.info(om.writeValueAsString(dto1));
        InventoryResendMsgOutputDTO output2 = null;
        try {
            output2 = commonUtilService.jsonPost(testInvResend, dto1, InventoryResendMsgOutputDTO.class, null);
        } catch (Exception e) {
            logger.info("{}", e);
            return;
        }
        String outputStr;
        try {
            outputStr = om.writeValueAsString(output2);
            logger.info(outputStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
