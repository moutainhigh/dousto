package com.ibm.sc.oms.service.intf.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.mq.QueueListenerAbstract;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.XMLConverter;

/**
 * @author pjsong
 * 
 */
@Component
public class TmsStatusListener extends QueueListenerAbstract {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IntfReceivedService intfReceivedService;
	@Autowired
	XMLConverter xmlConverterStatus;
    @Autowired
    TmsOmsLogisticsStatusTransService tmsOmsLogisticsStatusTransService;
	@Autowired
	OrdiErrOptLogService ordiErrOptLogService;

	@Override
	protected boolean doProcess(String reqXml) {
        TmsStatusDTO tmsStatusDTO = (TmsStatusDTO) xmlConverterStatus.convertFromXMLStringToObject(reqXml);
        String msg = CommonUtilService.createOrderValidate(tmsStatusDTO);
        if (!msg.equals(CommonConstService.OK)) {
            return false;
        }
        try {
            tmsOmsLogisticsStatusTransService.updateOrderLogisticsStatus(tmsStatusDTO);
            return true;
        } catch (Exception e) {
            logger.error("TmsStatusListener  --> {}", e);
            return false;
        }
	}

	@Override
	protected boolean doProcess(Object reqObject) {
		return false;
	}

    @Override
    protected <T> IntfReceived saveTrack(T reqObject) {
        return null;
    }
    
    @Override
    protected IntfReceived saveTrack(String reqXml) {
         TmsStatusDTO tmsStatusDTO = (TmsStatusDTO) xmlConverterStatus.convertFromXMLStringToObject(reqXml);
        IntfReceived ir = new IntfReceived();
        ir.setOrderSubNo(tmsStatusDTO.getTxLogisticID());
        ir.setIntfCode(IntfReceiveConst.TMS_STATUS_TO_OMS.getCode());
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
