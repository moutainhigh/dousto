package com.ibm.sc.oms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.service.business.WmsOmsLogisticsStatusService;
import com.ibm.sc.oms.service.test.BaseTest;

//在线察看编辑：
//http://www.jsoneditoronline.org/

@SuppressWarnings("javadoc")
public class WmsOmsLogisticsStatusServiceImplTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WmsOmsLogisticsStatusService wmsOmsLogisticsStatusService;
    
    String filePath = "src/test/resources/intf/WmsOmsReceiveLogisticsDTO.json";
    
    @Test
    public final void test() { 
    	WmsOmsReceiveLogisticsDTO wmsOmsReceiveLogisticsDTO = null;
        ObjectMapper mapper = new ObjectMapper();
        String param = genSampleJson();
        try {
        	wmsOmsReceiveLogisticsDTO = mapper.readValue(param, WmsOmsReceiveLogisticsDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<WmsOmsReceiveLogisticsDTO> list = new ArrayList<WmsOmsReceiveLogisticsDTO>();
        list.add(wmsOmsReceiveLogisticsDTO);
        ResponseObjectDTO output = wmsOmsLogisticsStatusService.handlerUpdateOrderLogisticsStatus(list);
        logger.debug(output.getResponseHeader().getErrorMsg());
        
    }

    /** Object to Json String **/
    private String genSampleJson() {
        ObjectMapper mapper = new ObjectMapper();
//        File f = new File(filePath);
        WmsOmsReceiveLogisticsDTO wmsOmsReceiveLogisticsDTO = null;
        try {
        	wmsOmsReceiveLogisticsDTO = mapper.readValue(new File(filePath), WmsOmsReceiveLogisticsDTO.class);
        } catch (Exception e) {
            System.out.println("xx");
        }
        try {
            return mapper.writeValueAsString(wmsOmsReceiveLogisticsDTO);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

}
