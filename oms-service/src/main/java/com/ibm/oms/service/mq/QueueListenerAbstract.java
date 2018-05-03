package com.ibm.oms.service.mq;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.oms.domain.persist.IntfReceived;
 
/**
 * @author pjsong
 *
 */
public abstract class QueueListenerAbstract implements MessageListener
{
	
	String txtMsgErr = "An error occurred extracting txt message";
	String objMsgErr = "An error occurred extracting obj message";
	private final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract boolean doProcess(String reqXml) ;
    
    protected abstract boolean doProcess(Object reqObject) ;
    
    protected abstract <T> IntfReceived saveTrack(T reqObject) ;
    protected abstract IntfReceived saveTrack(String reqXml) ;
    protected abstract void updateTrack(IntfReceived intfReceived) ;
    
    public void onMessage( final Message message )
    {
        if ( message instanceof TextMessage )
        {
            try  
            {  
                 String msgText = ((TextMessage) message).getText(); 
                 IntfReceived ir = saveTrack(msgText);
                 if(!doProcess(msgText)){
                	 //处理失败
                     updateTrack(ir);
                 }
            }  
            catch (JMSException jmsEx_p)  
            {  
                 String errMsg = txtMsgErr;  
                 logger.error(errMsg+"\t\r\n{}", jmsEx_p);  
            } 
        }
        if ( message instanceof ObjectMessage )
        {
            try  
            {  
                 Object msgObject = ((ObjectMessage) message).getObject();
                 IntfReceived ir = saveTrack(msgObject);
                 if(!doProcess(msgObject)){
                   //失败
                     updateTrack(ir);
                 }
            }  
            catch (JMSException jmsEx_p)  
            {  
                 String errMsg = objMsgErr;  
                 logger.error(errMsg+"\t\r\n{}", jmsEx_p);  
            } 
        }
    }
}