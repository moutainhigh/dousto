package com.ibm.oms.intf.intf;

import java.io.Serializable;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年4月25日 
 */
public class E3ResultDTO implements Serializable{

	/* 属性说明 */
	private static final long serialVersionUID = 6933208576940109784L;
	
	private String status;
	
	private Object data;
	
	private String message;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isSuccesss(){
		if("SUCCESS".equals(status)) {
			return true;
		} else {
			return false;
		}
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

}
