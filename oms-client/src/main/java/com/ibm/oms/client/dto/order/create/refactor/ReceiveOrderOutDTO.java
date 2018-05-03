package com.ibm.oms.client.dto.order.create.refactor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pjsong
 * 
 */
public class ReceiveOrderOutDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 处理成功标志 **/
    Map<String,Object> mapList;
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
	public Map<String, Object> getMapList() {
		return mapList;
	}
	public void setMapList(Map<String, Object> mapList) {
		this.mapList = mapList;
	}
	
    
}
