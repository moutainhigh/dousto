package com.ibm.sc.oms.service.retchg.impl;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.business.trans.TmsOmsReceivePaymentTransService;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.oms.service.test.BaseTest;

/**
 * @author xiaohl
 * 
 */
@Component
public class TmsPayTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IntfReceivedService intfReceivedService;
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	OrderPayService orderPayService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired
	OrderItemPayService orderItemPayService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderMainService orderMainService;
//	@Autowired
//    XMLConverter xmlConverterPay;
	@Autowired
	TmsOmsReceivePaymentTransService tmsOmsReceivePaymentTransService;
	@Autowired
	TmsOmsLogisticsStatusTransService tmsOmsLogisticsStatusTransService;
	@Autowired
    XMLConverter xmlConverterStatus;
	
	String filePath_pay = "src/test/resources/intf/TmsPayDTO.json";
	String filePath_status = "src/test/resources/intf/TmsStatusDTO.xml";
	
//	@Test
	public void testPay() { //TMS支付信息回传
	    ObjectMapper mapper = new ObjectMapper();
	    TmsPayDTO dto = null;
        File f = new File(filePath_pay);
        if (!f.exists()) {
            try {
                mapper.writeValue(new File(filePath_pay), dto);
                System.out.println("yy");
            } catch (Exception e) {
                System.out.println("xx");
            }
        }
        TmsPayDTO dto1;
        try {
            dto1 = mapper.readValue(new File(filePath_pay), TmsPayDTO.class);
            CommonOutputDTO output = tmsOmsReceivePaymentTransService.updateTmsOmsPayment(dto1);
            System.out.println("yy");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("xx");
        }
        
	}
	
	@Test
    public void testSign() {  //TMS签收状态回传
	    try{
	      //TmsStatusDTO tmsStatusDTO = (TmsStatusDTO) xmlConverterStatus.convertFromXMLStringToObject(filePath_status);
	        TmsStatusDTO tmsStatusDTO = (TmsStatusDTO)xmlConverterStatus.convertFromXMLToObject(filePath_status);
	        tmsOmsLogisticsStatusTransService.updateOrderLogisticsStatus(tmsStatusDTO);
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

}

