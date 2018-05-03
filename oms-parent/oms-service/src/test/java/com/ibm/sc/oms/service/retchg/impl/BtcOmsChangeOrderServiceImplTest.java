package com.ibm.sc.oms.service.retchg.impl;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.StatusDictService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.impl.SaleAfterOrderServiceImpl;
import com.ibm.sc.oms.service.test.BaseTest;

@SuppressWarnings("javadoc")
public class BtcOmsChangeOrderServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    StatusDictService statusDictService;

    //String filePath = "src/test/resources/intf/retchg/BtcOmsChangeDTO.json";
    String filePath = "src/test/resources/intf/BtcOmsChangeDTO.json";

    @Test
    public final void test() { 
        
        BtcOmsReturnChangeDTO rcDTO = null;
        ObjectMapper mapper = new ObjectMapper();
        String param = genSampleJson();
        try {
            rcDTO = mapper.readValue(param, BtcOmsReturnChangeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
//        List<StatusDict> list = statusDictService.findByField(StatusDict_.statusTypeCode, OrderStatus.StatusTypeCode_SaleOrder_StatusTotal.getCode());
//        System.out.println(list.size());

        
        CommonOutputDTO output = returnChangeOrderService.writeChangeOrder(rcDTO);
        logger.debug(output.getMsg());
        logger.debug(output.getCode());
    }

    /** Object to Json String **/
    private String genSampleJson() {
        ObjectMapper mapper = new ObjectMapper();
//        File f = new File(filePath);
        BtcOmsReturnChangeDTO rcDTO = null;
        try {
            rcDTO = mapper.readValue(new File(filePath), BtcOmsReturnChangeDTO.class);
            System.out.println("yy");
        } catch (Exception e) {
            System.out.println("xx");
        }
        try {
            return mapper.writeValueAsString(rcDTO);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

}
