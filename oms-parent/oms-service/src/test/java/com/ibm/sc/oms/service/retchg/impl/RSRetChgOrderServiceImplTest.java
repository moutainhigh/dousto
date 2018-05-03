package com.ibm.sc.oms.service.retchg.impl;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;

/**
 * @author pjsong
 *
 */
public class RSRetChgOrderServiceImplTest extends BaseTest{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    
    String filePath = "src/test/resources/intf/retchg/BtcOmsReturnDTO.json";
    String testBtcOmsReceive = "http://localhost:8080/oms-rs/oms-retchg/b2c-oms-receive-return-order";
    String operateGet = "http://localhost:8080/oms-rs/btcoms/operate";
    @Test
    public final void testBtcOmsReceive() {
        BtcOmsReturnChangeDTO dto1 = null;
        ObjectMapper mapper = new ObjectMapper();
        String param = genSampleJson();
        try {
            dto1 = mapper.readValue(param, BtcOmsReturnChangeDTO.class);
            BtcOmsReturnChangeDTO returnChangeDTO = commonUtilService.jsonPost(testBtcOmsReceive, dto1, BtcOmsReturnChangeDTO.class, null);
    		logger.info(returnChangeDTO.getOrderRelatedOrigin());
    		returnChangeOrderService.writeReturnOrder(returnChangeDTO);
    		
//    		CommonOutputDTO output3 = CommonUtilService.jsonGet(operateGet+"?orderNo=11111&operate_code=btcFinish", CommonOutputDTO.class );
//    		logger.info(output3.getMsg());
        } catch (Exception e) {
            logger.error("",e);
            return;
        }
    }
    
    

    /** read from file to Object, then to Json String **/
    private String genSampleJson() {
        ObjectMapper mapper = new ObjectMapper();
        File f = new File(filePath);
        BtcOmsReturnChangeDTO dto1 = null;
        try {
            dto1 = mapper.readValue(f, BtcOmsReturnChangeDTO.class);
            System.out.println("yy");
        } catch (Exception e) {
            System.out.println("xx");
        }

        try {
            return mapper.writeValueAsString(dto1);
        } catch (JsonProcessingException e) {
            logger.error("",e);
            throw new Error("json exception");
        }
    }
}
