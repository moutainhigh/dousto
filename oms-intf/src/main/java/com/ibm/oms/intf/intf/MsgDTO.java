package com.ibm.oms.intf.intf;

import java.io.Serializable;


public class MsgDTO implements Serializable {
    
    private String code;
    private String message;
    
    private String orderNo;
    private String orderSubNo;
    private String orderItemNo;
    
    public MsgDTO() {
    
    }
    
    
    public MsgDTO(String msg) {
    
        message = msg;
        
    }
    
    
    public MsgDTO(String code, String msg) {
    
        this.code = code;
        this.message = msg;
    }
    
    
    public String getCode() {
    
        return code;
    }
    
    
    public String getMessage() {
    
        return message;
    }
    
    
    public void setCode(String code) {
    
        this.code = code;
    }
    
    
    public void setMessage(String message) {
    
        this.message = message;
    }


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getOrderSubNo() {
		return orderSubNo;
	}


	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}


	public String getOrderItemNo() {
		return orderItemNo;
	}


	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
    
}
