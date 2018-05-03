package com.ibm.sc.oms.service.intf.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.intf.intf.inner.OrderItemTms;
import com.ibm.oms.intf.intf.inner.TmsOrderItemsDTO;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.oms.service.test.BaseTest;

/**
 * @author pjsong
 * 
 */
public class MqObjectTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderCreateServiceImpl orderCreateService;
    @Autowired
    XMLConverter xmlConverterOrder;
//    @Autowired
//    XMLConverter xmlConverterPay;
    @Autowired
    XMLConverter xmlConverterStatus;
    @Autowired
    CommonUtilService commonUtilService;
    String tmsOrderDTO = "src/test/resources/intf/TmsOrderDTO.xml";
    String tmsPayDTO = "src/test/resources/intf/tmsPayDTO.xml";
    String tmsPayDTOTmp = "src/test/resources/intf/tmsPayDTOTmp.xml";
    String tmsStatusDTO = "src/test/resources/intf/tmsStatusDTO.xml";
    

    
//    @Test
//    public void tmsPayTmpDTO() throws IOException {
//        TmsPayDTO tpDTO = new TmsPayDTO();
//        if (!new File(tmsPayDTOTmp).exists()) {
//            try {
//                xmlConverterPay.convertFromObjectToXML(tpDTO, tmsPayDTOTmp);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        TmsPayDTO tpDTONew = (TmsPayDTO) xmlConverterPay.convertFromXMLToObject(tmsPayDTOTmp);
//        String output = xmlConverterPay.convertFromObjectToXMLString(tpDTONew);
//        System.out.println(output);
//        System.out.println(tpDTONew.getMoney());
//    }
    
    
//    @Test
//    public void tmsPayDTO() throws IOException {
//        TmsPayDTO tpDTO = new TmsPayDTO();
//        tpDTO.setLogisticCompanyId("11");
//        tpDTO.setMoney(11d);
//        Date date = new Date();
//        SimpleDateFormat sdf = commonUtilService.format24Hours();
//        tpDTO.setOccurtime(sdf.format(date));
//        tpDTO.setOperator("11");
//        tpDTO.setPaymode(11);
//        tpDTO.setRemark("11");
//        tpDTO.setTxLogisticID("11");
//        if (!new File(tmsPayDTO).exists()) {
//            try {
//                xmlConverterPay.convertFromObjectToXML(tpDTO, tmsPayDTO);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        String output = xmlConverterPay.convertFromObjectToXMLString(tpDTO);
//        System.out.println(output);
//        TmsPayDTO tpDTONew = (TmsPayDTO)xmlConverterPay.convertFromXMLStringToObject(output);
//        System.out.println(tpDTONew.getMoney());
//    }
//    @Test
    public void tmsStatusDTO() throws IOException {
        TmsStatusDTO tsDTO = new TmsStatusDTO();
        Date date = new Date();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tsDTO.setAcceptTime(sdf.format(date));
        tsDTO.setAcceptTime("11");
        tsDTO.setInfoType("11");
        tsDTO.setLogisticCompanyId("11");
        tsDTO.setMailNo("11");
        tsDTO.setName("11");
        tsDTO.setRemark("11");
        tsDTO.setTxLogisticID("11");
        try {
            xmlConverterStatus.convertFromObjectToXML(tsDTO, tmsStatusDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @throws IOException
     */
//    @Test
    public void tmsOrderDTO() throws IOException {
        Date date = new Date();
        TmsOrderDTO tDTO = new TmsOrderDTO();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tDTO.setCreateTime(sdf.format(date));

        tDTO.setAddress("11");
        tDTO.setAddresscode("11");
        tDTO.setArea("11");
        tDTO.setCity("11");
        tDTO.setDeliverymode(11);
        tDTO.setFlag(11);
        tDTO.setGoodsValue(1.1d);
        tDTO.setInsuranceValue(1.1d);
        tDTO.setItemsValue(1.1d);
        tDTO.setLogisticCompanyId("11");
        tDTO.setMobile("11");
        tDTO.setName("11");
        tDTO.setNeedInvoice(11);
        tDTO.setOrderid("11");
        tDTO.setPayMode("11");
        tDTO.setPhone("11");
        tDTO.setPostCode("11");
        tDTO.setProv("11");
        tDTO.setRemark("11");
        tDTO.setToid("11");
        tDTO.setTotalPCS(11);
        tDTO.setTotalWeight(1.1d);
        tDTO.setTxLogisticID("11");
        tDTO.setType("11");
        tDTO.setWmsOrderType(11);
        tDTO.setOuthousetime(sdf.format(date));
        tDTO.setReviewtime(sdf.format(date));
        tDTO.setSrcOrderNo("123");
        
        TmsOrderItemsDTO toi = new TmsOrderItemsDTO();
        tDTO.setItems(toi);
        
        OrderItemTms oit = new OrderItemTms();
        oit.setBarCode("11");
        oit.setItemId("11");
        oit.setItemName("11");
        oit.setItemValue("11");
        oit.setNumber("11");
        oit.setSkuId("11");
        oit.setSpecial("11");
        OrderItemTms oit2 = new OrderItemTms();
        oit2.setBarCode("22");
        oit2.setItemId("22");
        oit2.setItemName("22");
        oit2.setItemValue("22");
        oit2.setNumber("22");
        oit2.setSkuId("22");
        oit2.setSpecial("22");

        ArrayList<OrderItemTms> oitList = new ArrayList<OrderItemTms>();
        oitList.add(oit);
        oitList.add(oit2);
        toi.setItem(oitList);
        try {
            xmlConverterOrder.convertFromObjectToXML(tDTO, tmsOrderDTO);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
