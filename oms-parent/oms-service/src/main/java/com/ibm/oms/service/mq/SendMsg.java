package com.ibm.oms.service.mq;

import java.io.Serializable;

/**
 * @author pjsong
 *
 */
public interface SendMsg {
	/**
	 * @param dto
	 */
	public void send(final Serializable dto);
	/**
	 * @param str
	 */
	public void send(final String str);
	
    void sendWithTrack(String str, String orderNo, String intfCode);
    
    void sendWithTrack(String str, String orderNo, String orderSubNo, String intfCode);
}
