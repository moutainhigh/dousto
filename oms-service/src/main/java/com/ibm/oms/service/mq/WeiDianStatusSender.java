/**
 * 
 */
package com.ibm.oms.service.mq;

import java.io.Serializable;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.service.IntfSentService;

/**
 * 订单中台发送订单状态给微店
 * @author xiaonanxiang
 *
 */
//@Component
public class WeiDianStatusSender implements SendMsg {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    IntfSentService intfSentService;
    private JmsTemplate jmsTemplate;
    private String queue;

    public void send(final Serializable dto) throws RuntimeException {
        jmsTemplate.send(queue, new MessageCreator() {
            public Message createMessage(Session s) throws JMSException {
                ObjectMessage msg = s.createObjectMessage(dto);
                return msg;
            }
        });
    }

    @Override
    public void send(final String str) {
        jmsTemplate.send(queue, new MessageCreator() {
            public Message createMessage(Session s) throws JMSException {
                TextMessage msg = s.createTextMessage(str);
                return msg;
            }
        });
    }

    @Override
    public void sendWithTrack(final String str, String orderSubNo, String intfCode) {
        IntfSent intfSent = new IntfSent();
        intfSent.setIntfCode(IntfSentConst.OMS_STATUS_SENDTO_WEIDIAN.getCode());
        intfSent.setSucceedFlag(1l);
        intfSent.setMsg(str);
        intfSent.setOrderSubNo(orderSubNo);
        intfSent.setCreateTime(new Date());
        intfSentService.save(intfSent);
        try {
            send(str);
        } catch (Exception e) {
            logger.info("{}",e);
            intfSent.setSucceedFlag(-1l);
            intfSentService.update(intfSent);
        }
    }

    @Override
    public void sendWithTrack(final String str, String orderNo,String orderSubNo, String intfCode) {
        IntfSent intfSent = new IntfSent();
        intfSent.setIntfCode(intfCode);
        intfSent.setSucceedFlag(1l);
        intfSent.setMsg(str);
        intfSent.setOrderNo(orderNo);
        intfSent.setOrderSubNo(orderSubNo);
        intfSent.setCreateTime(new Date());
        intfSentService.save(intfSent);
        try {
            send(str);
        } catch (Exception e) {
            logger.info("{}",e);
            intfSent.setSucceedFlag(-1l);
            intfSentService.update(intfSent);
        }
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String getQueue() {
        return queue;
    }



    public void setQueue(String queue) {
        this.queue = queue;
    }

}
