package com.ibm.sc.oms.service.impl;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.service.business.WmsOmsCostPriceService;
import com.ibm.sc.oms.service.test.BaseTest;

//在线察看编辑：
//http://www.jsoneditoronline.org/

@SuppressWarnings("javadoc")
public class WmsOmsCostPriceServiceImplTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    WmsOmsCostPriceService wmsOmsCostPriceService;
    
    String filePath = "src/test/resources/intf/ReceiveCostPriceDTO.json";
    
    @Test
    public final void test() { 
    	WmsReceiveCostPriceDTO receiveCostPriceDTO = null;
        ObjectMapper mapper = new ObjectMapper();
        String param = genSampleJson();
        try {
        	receiveCostPriceDTO = mapper.readValue(param, WmsReceiveCostPriceDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        CommonOutputDTO output = wmsOmsCostPriceService.handlerUpdateSingleOrderCostPrice(receiveCostPriceDTO);
        logger.debug(output.getMsg());
        logger.debug(output.getCode());
    }

    /** Object to Json String **/
    private String genSampleJson() {
        ObjectMapper mapper = new ObjectMapper();
//        File f = new File(filePath);
        WmsReceiveCostPriceDTO receiveCostPriceDTO = null;
        try {
        	receiveCostPriceDTO = mapper.readValue(new File(filePath), WmsReceiveCostPriceDTO.class);
        } catch (Exception e) {
            System.out.println("xx");
        }
        try {
            return mapper.writeValueAsString(receiveCostPriceDTO);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
}
