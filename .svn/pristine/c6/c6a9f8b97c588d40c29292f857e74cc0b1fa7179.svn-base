package com.ibm.sc.oms.service.intf.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.TmsOmsReceivePaymentTransService;
import com.ibm.oms.service.mq.QueueListenerAbstract;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * @author pjsong
 * 
 */
@Component
public class TmsPayToOmsListener extends QueueListenerAbstract {
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
	@Override
	protected boolean doProcess(String reqXml) {
	    
        ObjectMapper om = new ObjectMapper();
        TmsPayDTO dto;
        try {
            dto = om.readValue(reqXml, TmsPayDTO.class);
        } catch (Exception e1) {
            logger.info("{}", e1);
            return false;
        } 
        
        String msg = CommonUtilService.createOrderValidate(dto);
        if (!msg.equals(CommonConstService.OK)) {
            logger.info(msg);
            return false;
        }
        try {
            CommonOutputDTO output = tmsOmsReceivePaymentTransService.updateTmsOmsPayment(dto);
            if(!CommonConstService.OK.equals(output.getCode())){
                logger.info(output.getMsg());
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error("TmsStatusListener  --> {}", e);
            return false;
        }
	}

	@Override
	protected boolean doProcess(Object reqObj) {
		return false;
	}



    @Override
    protected <T> IntfReceived saveTrack(T reqObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected IntfReceived saveTrack(String reqXml) {
        ObjectMapper om = new ObjectMapper();
        TmsPayDTO dto = null;
        try {
            dto = om.readValue(reqXml, TmsPayDTO.class);
        } catch (Exception e1) {
            logger.info("{}", e1);
        } 
        IntfReceived ir = new IntfReceived();
        if(dto != null){
            ir.setOrderSubNo(dto.getTxLogisticID());
        }
        ir.setIntfCode(IntfReceiveConst.Tms_Pay_to_Oms.getCode());
        ir.setMsg(reqXml);
        ir.setSucceed(1l);
        ir.setCreateTime(new Date());
        intfReceivedService.save(ir);
        return ir;
    }

    @Override
    protected void updateTrack(IntfReceived intfReceived) {
        intfReceivedService.update(intfReceived);
    }
}

