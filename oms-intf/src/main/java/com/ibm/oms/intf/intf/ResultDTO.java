package com.ibm.oms.intf.intf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResultDTO implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4531858503304426782L;

    private int result = 1;
    private String message = null;
    private Object resultObj = null;
    private String orderNo;
    private String orderSubNo;

    public void setResult(int value) {
        this.result = value;
    }

    public int getResult() {
        return this.result;
    }

    public void setResultMessage(String value) {
        this.message = value;
    }

    public String getResultMessage() {
        return this.message;
    }

    public void setResultObj(Object value) {
        this.resultObj = value;
    }

    public Object getResultObj() {
        return this.resultObj;
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

    public void setErrorMessage(String value) {
        this.result = -1;
        this.message = value;
    }

    public void setCollectMessage(String value) {
        this.result = 1;
        this.message = value;
    }

}