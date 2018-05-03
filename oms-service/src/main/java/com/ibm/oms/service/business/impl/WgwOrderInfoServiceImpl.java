/**
 * 
 */
package com.ibm.oms.service.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.OrderMsg;
import com.ibm.oms.service.IntfSentService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.WgwOrderInfoService;
import com.ibm.oms.service.mq.TmsOrderInfoSender;
import com.ibm.oms.service.util.JaxbUtil;
import com.ibm.oms.service.util.XMLConverter;

/**
 * @author xiaonanxiang
 * 
 */
//@Component("wgwOrderInfoService")
public class WgwOrderInfoServiceImpl implements WgwOrderInfoService {

    @Autowired
    XMLConverter xmlConverterOrder;

    @Autowired
    IntfSentService intfSentService;

    @Autowired
    TmsOrderInfoSender omsOrderInfoSender;

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    TransportAreaCacheService transportAreaCacheService;


    @Override
    public void sendOrderInfo(String orderNo, String orderSubNo) {
        
        long sendResult = 1l;
        
        boolean flag = true;
        OrderMsg dto = new OrderMsg();
        
        //OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        //OrderSub orderSub = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
        
        dto.setArriveDays("2");
        dto.setChannelId("423");
        // dto.setCloseReasonDesc(null);
        // dto.setFailContent(null);
        dto.setIfPayOnArrival("1");
        // dto.setiStatus(null);
        dto.setOrderOuterNo("5235");
        dto.setOrderSubNo("4656");
        dto.setRecvAddr("深圳");
        dto.setStatusConfirm("21");
        dto.setStatusPay("434");
        dto.setStatusTotal("346");
        dto.setStatusWuliu("646");
        // dto.setTaskType(null);
        dto.setWuliuCode("646");
        dto.setWuliuCompany("45656");
        
        String outputMsg = null;
        
        try {
            /*outputMsg = xmlConverterOrder.convertFromObjectToXMLString(dto);
            System.out.println(outputMsg);*/
            //omsOrderInfoSender.send(outputMsg);
            /*JSONObject jsonObject = JSONObject.fromObject(dto);
            System.out.println(jsonObject.toString());
            omsOrderInfoSender.sendWithTrack(jsonObject.toString(), orderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());*/
            
            outputMsg = JaxbUtil.convertToXml(dto);
            System.out.println(outputMsg);
            omsOrderInfoSender.send(outputMsg);
        } catch (Exception ex) {
            flag = false;
            sendResult = -1l;
            ex.printStackTrace();
        }
        
        
        IntfSent msg = new IntfSent();
        msg.setIntfCode(IntfSentConst.OMS_TMS_SaleAfterOrder.getCode());
        msg.setSucceedFlag(sendResult);
        msg.setMsg(outputMsg);// 返回参数描述
        msg.setOrderNo(null);
        msg.setOrderSubNo(orderSubNo);
        msg.setCreateTime(new Date());
        this.intfSentService.save(msg);
    }
    /*@Override
    public void sendOrderInfo(String orderNo, String orderSubNo) {
        
        long sendResult = 1l;
        
        boolean flag = true;
        OrderMsg dto = new OrderMsg();
        
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        OrderSub orderSub = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
        
        dto.setArriveDays("2");
        dto.setChannelId(orderMain.getOrderSource());
        // dto.setCloseReasonDesc(null);
        // dto.setFailContent(null);
        dto.setIfPayOnArrival(String.valueOf(orderMain.getIfPayOnArrival()));
        // dto.setiStatus(null);
        dto.setOrderOuterNo(orderMain.getAliasOrderNo());
        dto.setOrderSubNo(orderSubNo);
        dto.setRecvAddr(getRecvAddr(orderSub));
        dto.setStatusConfirm(orderMain.getStatusConfirm());
        dto.setStatusPay(orderMain.getStatusPay());
        dto.setStatusTotal(orderMain.getStatusTotal());
        dto.setStatusWuliu(orderSub.getLogisticsStatus());
        // dto.setTaskType(null);
        dto.setWuliuCode(orderSub.getLogisticsOutNo());
        dto.setWuliuCompany(orderSub.getExpressType());
        
        String outputMsg = null;
        
        try {
            outputMsg = xmlConverterOrder.convertFromObjectToXMLString(dto);
            System.out.println(outputMsg);
            //omsOrderInfoSender.send(outputMsg);
            JSONObject jsonObject = JSONObject.fromObject(dto);
            System.out.println(jsonObject.toString());
            omsOrderInfoSender.sendWithTrack(jsonObject.toString(), orderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
            
            outputMsg = JaxbUtil.convertToXml(dto);
            System.out.println(outputMsg);
            omsOrderInfoSender.send(outputMsg);
        } catch (Exception ex) {
            flag = false;
            sendResult = -1l;
            ex.printStackTrace();
        }
        
        
        IntfSent msg = new IntfSent();
        msg.setIntfCode(IntfSentConst.OMS_TMS_SaleAfterOrder.getCode());
        msg.setSucceedFlag(sendResult);
        msg.setMsg(outputMsg);// 返回参数描述
        msg.setOrderNo(null);
        msg.setOrderSubNo(orderSubNo);
        msg.setCreateTime(new Date());
        this.intfSentService.save(msg);
    }*/

    /**
     * 收货地址【出库发货时必填】 格式例如：雷小米，0755-86010000，13800138000，广东 深圳市 宝安区 雷布斯研发部
     * 
     * @return
     */
    public String getRecvAddr(OrderSub orderSub) {
        String userName = orderSub.getUserName();
        String phoneNum = orderSub.getPhoneNum();
        String mobPhoneNum = orderSub.getMobPhoneNum();
        String combinedAddress = transportAreaCacheService.getAllByByAreaCode(orderSub.getAddressCode());
        String addressDetail = orderSub.getAddressDetail();
        return userName + "," + phoneNum + "," + mobPhoneNum + "," + combinedAddress + addressDetail;
    }

}
