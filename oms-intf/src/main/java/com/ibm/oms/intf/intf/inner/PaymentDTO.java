package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


public class PaymentDTO implements Serializable {

	/** 支付方式编码 **/
    @NotBlank(message = "payCode is compulsory")
    @Length(max = 32,message = "payCode: length must be less than 32")
	private String payCode;

	/** 支付方式名称 **/
    @NotBlank(message = "payName is compulsory")
    @Length(max = 64,message = "payCode: length must be less than 64")
	private String payName;

	/** 支付金额 **/
    @NotNull(message = "payAmount is compulsory")
	private BigDecimal payAmount;

	/** 支付时间 **/
	private java.util.Date payTime;

	/** 银行卡编码 **/
    @Length(max = 32,message = "bankTypeCode: length must be less than 32")
	String bankTypeCode;
    
	/** 银行卡名称 **/
    @Length(max = 128,message = "bankTypeName: length must be less than 128")
	String bankTypeName;
    
	/** 是否预付款 **/
    @Range(min = 0, max = 1, message = "isPrePay: value must be between 0 and 1")
    String isPrePay;
    
	/** 卡号 **/
    @Length(max = 32,message = "cardNo: length must be less than 32")
    String cardNo;
    
	/** 交易流水号 **/
    @Length(max = 32,message = "serialNo: length must be less than 32")
    String serialNo;
	
    
	/** 交易流水号 **/
    @Length(max = 32,message = "payMode: length must be less than 32")
    String payMode;
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

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public String getBankTypeCode() {
		return bankTypeCode;
	}

	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	
	

}
