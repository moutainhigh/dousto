package com.ibm.oms.service.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.domain.persist.IntfSent_;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;



/**
 * @author pjsong
 *
 */
public class MessageServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private static final Long FAIL_STATUS = 0l;
    
    private static final Long SUCCESS_STATUS = 1l;
    private static final int retry_count = 3;

    @Autowired
    private IntfReceivedService intfReceivedService;
    @Autowired
    private IntfSentService intfSentService;
    
    public void saveMessage(String btcOrderNo, String intfCode, String message) {
        IntfReceived msg = new IntfReceived();
        msg.setBtcOrderNo(btcOrderNo);
        msg.setIntfCode(intfCode);
        msg.setMsg(message);
        intfReceivedService.save(msg);
    }


    public void saveTaskMessage(String orderItemNo, String intfCode, String message) {
        IntfSent msg = new IntfSent();
        msg.setOrderItemNo(orderItemNo);
        msg.setIntfCode(intfCode);
        msg.setMsg(message);
        intfSentService.save(msg);
    }
    
    public void resendMessage(String intfCode, int fetchNum) {
        List<IntfSent> messages = intfSentService.findByField(IntfSent_.intfCode, intfCode);

        List<Long> sIds = new ArrayList<Long>();
        List<Long> fIds = new ArrayList<Long>();
        for (IntfSent message : messages) {
            try {
//
//                // 重发成功
//                *****
                // 重发成功状态更新为成功
                message.setSucceedFlag(SUCCESS_STATUS);
                intfSentService.save(message);
                
            } catch (Exception e) {
                // 重发失败
                fIds.add(message.getId());
                if (message.getRetryCount() == retry_count) {
                    // 重发失败状态更新为失败
                    message.setSucceedFlag(FAIL_STATUS);
                    intfSentService.save(message);
                }
            }
        }

        logger.info("success ids: {}, fail ids: {}", new Object[] {sIds.toString(), fIds.toString()});
    }
}
