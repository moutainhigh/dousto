package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author wangqc
 * @date 2018年2月28日 下午4:12:35
 * 
 */
public class ReceiveHangOrderOutputDTO implements Serializable{
	
	 String succeed;
	 String message;
	public String getSucceed() {
		return succeed;
	}
	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	 
	 
}
