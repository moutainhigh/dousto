package com.ibm.sc.oms.service.sale.impl;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.business.impl.WmsOmsLogisticsStatusServiceImpl;
import com.ibm.oms.service.business.trans.WmsOmsCostPriceTransService;
import com.ibm.oms.service.business.trans.WmsOmsInspectStatusTransService;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.oms.service.intf.impl.ThirdTmsLogListener;
import com.ibm.sc.oms.service.intf.impl.TmsPayToOmsListener;
import com.ibm.sc.oms.service.intf.impl.TmsStatusListener;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;
import com.ibm.sc.oms.service.test.TmsTextMsg;

/**
 * @author pjsong
 *
 */
public class TmsOmsServiceImplTest extends BaseTest{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    WmsOmsLogisticsStatusServiceImpl wmsOmsLogisticsStatusService;
    @Autowired
    WmsOmsInspectStatusTransService wmsOmsInspectStatusTransService;
    @Autowired
    WmsOmsCostPriceTransService wmsOmsCostPriceTransService;
    @Autowired
    TmsStatusListener tmsStatusListener;
    @Autowired
    ThirdTmsLogListener thirdTmsLogListener;
    @Autowired
    TmsPayToOmsListener tmsPayToOmsListener;
    @Autowired
    XMLConverter xmlConverterStatus;
//    @Autowired
//    XMLConverter xmlConverterPay;
    
    @Test
    public final void testTmsStatus() {
        logger.info("xx");
        logger.info("ffdd");
        tmsStatusListener.onMessage(new TmsTextMsg(){
            @Override
            public String getText() throws JMSException {
                TmsStatusDTO dto;
                try {
                    dto = (TmsStatusDTO) xmlConverterStatus.convertFromXMLToObject(CommonTestConst.fpTmsStatusXml);
                    String output = xmlConverterStatus.convertFromObjectToXMLString(dto);
                    return output;
                } catch (IOException e) {
                    logger.info("{}", e);
                }
                return null;
            }});
    }
//    @Test
    public final void testTmsStatusUpdateTopic() {
        thirdTmsLogListener.onMessage(new TmsTextMsg(){
            @Override
            public String getText() throws JMSException {
                TmsStatusDTO dto;
                ObjectMapper om = new ObjectMapper();
                try {
                    dto = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpTmsStatusUpdate, TmsStatusDTO.class);
                    String output = om.writeValueAsString(dto);
                    return output;
                } catch (IOException e) {
                    logger.info("{}", e);
                }
                return null;
            }});
    }
//    @Test
    public final void testTmsPay() {
        logger.info("xx");
        logger.info("ffdd");
        tmsPayToOmsListener.onMessage(new TmsTextMsg(){
            @Override
            public String getText() throws JMSException {
                TmsPayDTO dto;
                try {
                    ObjectMapper om = new ObjectMapper();
                    try {
                        dto = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpTmsPayJson, TmsPayDTO.class);
                    } catch (Exception e1) {
                        logger.info("{}", e1);
                        return "";
                    } 
                    String output = om.writeValueAsString(dto);
                    return output;
                } catch (IOException e) {
                    logger.info("{}", e);
                }
                return null;
            }});
    }
}
