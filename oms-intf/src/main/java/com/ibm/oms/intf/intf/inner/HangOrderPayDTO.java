package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong
 * 订单支付信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HangOrderPayDTO implements Serializable{
	
	/** OMS订单号 **/
	String orderNo;
	
	/** 货到付款支付方式 **/
	String payOnArrivalPayType;
	
	/** 支付方式编码 **/
	String payCode;
	
	/** 支付方式名称 **/
	String payName;
	
	/** 支付金额 **/
	String payAmount;
	
	/** 支付时间 **/
	String payTime;
	
	/** 银行卡名称 **/
	String bankTypeName;
	
	/** 银行卡编码 **/
	String bankTypeCode;

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayOnArrivalPayType() {
		return payOnArrivalPayType;
	}
	public void setPayOnArrivalPayType(String payOnArrivalPayType) {
		this.payOnArrivalPayType = payOnArrivalPayType;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getBankTypeName() {
		return bankTypeName;
	}
	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}
	public String getBankTypeCode() {
		return bankTypeCode;
	}
	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}
	
}
