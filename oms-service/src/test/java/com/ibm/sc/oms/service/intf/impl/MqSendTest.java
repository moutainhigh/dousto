package com.ibm.sc.oms.service.intf.impl;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.mq.OmsStatusUpdateTopicSender;
import com.ibm.oms.service.mq.TmsOrderInfoSender;
import com.ibm.oms.service.mq.WeiDianStatusSender;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * @author pjsong
 * 
 */
public class MqSendTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderCreateServiceImpl orderCreateService;

    @Autowired
    TmsOrderInfoSender tmsOrderInfoSender;
    @Autowired
    TmsOrderInfoSender tmsRetChgOrderInfoSender;
    @Autowired
    OmsStatusUpdateTopicSender omsStatusUpdateTopicSender;
    @Autowired
    WeiDianStatusSender weiDianStatusSender;
    @Autowired
    TmsPaySender tmsPaySender;
    @Autowired
    TmsStatusSender tmsStatusSender;
    @Autowired
    XMLConverter xmlConverterOrder;
//    @Autowired
//    XMLConverter xmlConverterPay;
    @Autowired
    XMLConverter xmlConverterStatus;
    @Autowired
    CommonTestUtil commonTestUtil;
    String tmsOrderDTO = "src/test/resources/intf/TmsOrderDTO.xml";
    String tmsPayDTO = "src/test/resources/intf/tmsPayDTO.xml";
    String tmsStatusDTO = "src/test/resources/intf/tmsStatusDTO.xml";
    
    
  //@Test
  public void sendToTopic() throws IOException {
      File f = new File(CommonTestConst.fpOmsStatusUpdate);
      if (!f.exists()) {
          commonTestUtil.genSampleJsonAll();
      }
      String output = commonTestUtil.genJsonStrFromFile(CommonTestConst.fpOmsStatusUpdate, OMSStatusUpdateDTO.class);
      logger.debug(output);
      omsStatusUpdateTopicSender.sendWithTrack(output, CommonTestConst.targetOrderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
  }

    
//    @Test
    public void sendToQueueOrder() throws IOException {
        File f = new File(CommonTestConst.fpTmsStatusXml);
        if (!f.exists()) {
            commonTestUtil.genSampleJsonAll();
        }
        TmsOrderDTO dto = (TmsOrderDTO) xmlConverterOrder.convertFromXMLToObject(CommonTestConst.fpTmsOrderXml);
        String output = xmlConverterOrder.convertFromObjectToXMLString(dto);
        tmsOrderInfoSender.sendWithTrack(output, CommonTestConst.targetOrderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
    }

//      @Test
    public void sendToQueueRetChgOrder() throws IOException {
        TmsOrderDTO dto = (TmsOrderDTO) xmlConverterOrder.convertFromXMLToObject(CommonTestConst.fpTmsOrderXml);
        String output = xmlConverterOrder.convertFromObjectToXMLString(dto);
        tmsRetChgOrderInfoSender.send(output);
    }
    
    /**
     * 测试发送订单状态给微店
     * @throws IOException
     */
    @Test
    public void sendStatusToWeiDianQueue() throws IOException {
        File f = new File(CommonTestConst.fpOmsStatusUpdate);
        if (!f.exists()) {
            commonTestUtil.genSampleJsonAll();
        }
        String output = commonTestUtil.genJsonStrFromFile(CommonTestConst.fpOmsStatusUpdate, OMSStatusUpdateDTO.class);
        logger.debug(output);
        //WeiDianStatusSender weiDianStatusSender = new WeiDianStatusSender();
        weiDianStatusSender.send(output);
    }
    
    //@Test
    public void sendToQueuePay() throws IOException {
        File f = new File(CommonTestConst.fpTmsPayJson);
        if (!f.exists()) {
            commonTestUtil.genSampleJsonAll();
        }
        TmsPayDTO dto;
        ObjectMapper om = new ObjectMapper();
        try {
            dto = om.readValue(CommonTestConst.fpTmsPayJson, TmsPayDTO.class);
        } catch (Exception e1) {
            logger.info("{}", e1);
            return;
        }
        String output = om.writeValueAsString(dto);
        tmsPaySender.send(output);
    }
//  @Test
  public void sendToQueueStatus() throws IOException {
      File f = new File(CommonTestConst.fpTmsStatusXml);
      if (!f.exists()) {
          commonTestUtil.genSampleJsonAll();
      }
      TmsStatusDTO dto = (TmsStatusDTO) xmlConverterStatus.convertFromXMLToObject(CommonTestConst.fpTmsStatusXml);
      String output = xmlConverterStatus.convertFromObjectToXMLString(dto);
      tmsStatusSender.send(output);
  }

//    
//    
//    @Test
//    public void sendRetChgToQueue() throws IOException {
//        TmsOrderDTO dto = (TmsOrderDTO) xmlConverterOrder.convertFromXMLToObject(tmsOrderDTO);
//        String output = xmlConverterOrder.convertFromObjectToXMLString(dto);
//        tmsRetChgOrderInfoSender.send(output);
//    }
}
