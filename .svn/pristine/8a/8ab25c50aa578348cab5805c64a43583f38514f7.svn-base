package com.ibm.sc.oms.service.intf.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.mq.QueueListenerAbstract;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.XMLConverter;

/**
 * @author pjsong
 *
 */
@Component
public class TmsOmsSendOrderListener extends  QueueListenerAbstract{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    XMLConverter xmlConverterOrder;
    @Autowired
    IntfReceivedService intfReceivedService;
	@Override
	protected boolean doProcess(String reqXml) {
		System.out.println(reqXml);
		TmsOrderDTO ret =  (TmsOrderDTO) xmlConverterOrder.convertFromXMLStringToObject(reqXml);
		return false;
	}

	@Override
	protected boolean doProcess(Object reqXml) {
	    System.out.println(xmlConverterOrder.convertFromObjectToXMLString(reqXml));
		return false;
	}

    @Override
    protected <T> IntfReceived saveTrack(T reqObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected IntfReceived saveTrack(String reqXml) {
        IntfReceived ir = new IntfReceived();
        ir.setIntfCode(IntfSentConst.OMS_TMS_ORDER.getCode());
        ir.setMsg(reqXml);
        ir.setSucceed(1l);
        intfReceivedService.save(ir);
        return ir;
    }

    @Override
    protected void updateTrack(IntfReceived intfReceived) {
        intfReceivedService.update(intfReceived);
    }

}
