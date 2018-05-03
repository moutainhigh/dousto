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
 * @author pjsong
 *
 */
//@Component
public class OmsStatusUpdateTopicSender implements SendMsg {
    private final Logger logger = LoggerFactory.getLogger(getClass());
	private JmsTemplate jmsTemplate;
	private String topic;
	@Autowired
	IntfSentService intfSentService;
	public void send(final Serializable dto) throws RuntimeException {
		jmsTemplate.send(topic, new MessageCreator() {
			public Message createMessage(Session s) throws JMSException {
				ObjectMessage msg = s.createObjectMessage(dto);
				return msg;
			}
		});
	}

	@Override
	public void send(final String str) {
		jmsTemplate.send(topic, new MessageCreator() {
			public Message createMessage(Session s) throws JMSException {
				TextMessage msg = s.createTextMessage(str);
				return msg;
			}
		});
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

    public String getQueue() {
        return topic;
    }



    public void setQueue(String queue) {
        this.topic = queue;
    }

    @Override
    public void sendWithTrack(String str, String orderNo, String intfCode) {
        IntfSent intfSent = new IntfSent();
        intfSent.setIntfCode(IntfSentConst.OMS_STATUS_UPDATE.getCode());
        intfSent.setSucceedFlag(1l);
        intfSent.setMsg(str);
        intfSent.setOrderNo(orderNo);
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
    public void sendWithTrack(String str, String orderNo, String orderSubNo, String intfCode) {
        IntfSent intfSent = new IntfSent();
        intfSent.setIntfCode(IntfSentConst.OMS_STATUS_UPDATE.getCode());
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


}

