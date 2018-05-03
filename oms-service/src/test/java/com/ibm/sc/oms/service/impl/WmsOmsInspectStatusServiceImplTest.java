package com.ibm.sc.oms.service.impl;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;
import com.ibm.oms.service.business.WmsOmsInspectStatusService;
import com.ibm.sc.oms.service.test.BaseTest;

public class WmsOmsInspectStatusServiceImplTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WmsOmsInspectStatusService wmsOmsInspectStatusService;
    
    String filePath = "src/test/resources/intf/WmsOmsReceiveInspectionResultDTO.json";
    
    @Test
    public final void test() { 
    	WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO = null;
        ObjectMapper mapper = new ObjectMapper();
        String param = genSampleJson();
        try {
        	wmsOmsReceiveInspectionResultDTO = mapper.readValue(new File(filePath), WmsOmsReceiveInspectionResultDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        CommonOutputDTO output = wmsOmsInspectStatusService.handlerUpdateOrderInspectStatus(wmsOmsReceiveInspectionResultDTO);
        logger.debug(output.getMsg());
        logger.debug(output.getCode());
    }

    /** Object to Json String **/
    private String genSampleJson() {
        ObjectMapper mapper = new ObjectMapper();
//        File f = new File(filePath);
        WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO = null;
        try {
        	wmsOmsReceiveInspectionResultDTO = mapper.readValue(new File(filePath), WmsOmsReceiveInspectionResultDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("xx");
        }
        try {
            return mapper.writeValueAsString(wmsOmsReceiveInspectionResultDTO);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
}
