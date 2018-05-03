//package com.ibm.sc.oms.service.intf.impl;
//
//
//import java.io.Serializable;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Component;
//
//import com.ibm.sc.service.oms.mq.SendMsg;
//
///**
// * @author pjsong
// *
// */
////@Component
//public class BbcPaySender implements SendMsg {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//	private JmsTemplate jmsTemplate;
//	private String queue;
//
//	public void send(final Serializable dto) throws RuntimeException {
//		jmsTemplate.send(queue, new MessageCreator() {
//			public Message createMessage(Session s) throws JMSException {
//				ObjectMessage msg = s.createObjectMessage(dto);
//				return msg;
//			}
//		});
//	}
//
//	@Override
//	public void send(final String str) {
//		jmsTemplate.send(queue, new MessageCreator() {
//			public Message createMessage(Session s) throws JMSException {
//				TextMessage msg = s.createTextMessage(str);
//				return msg;
//			}
//		});
//	}
//	
//	public void setJmsTemplate(JmsTemplate jmsTemplate) {
//		this.jmsTemplate = jmsTemplate;
//	}
//
//    public String getQueue() {
//        return queue;
//    }
//
//
//
//    public void setQueue(String queue) {
//        this.queue = queue;
//    }
//
//
//}
//
