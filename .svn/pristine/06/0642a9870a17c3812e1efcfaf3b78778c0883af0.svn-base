package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author pjsong
 * 订单支付信息
 */
public class OrderPayDTO implements Serializable{
	
	/** OMS订单号 **/
    @Length(max = 20,message = "orderNo: length must be less than 20")
	String orderNo;
	
	/** 货到付款支付方式 **/
    @Length(max = 32,message = "payOnArrivalPayType: length must be less than 32")
	String payOnArrivalPayType;
	
	/** 支付方式编码 **/
    @NotBlank(message = "payCode is compulsory")
    @Length(max = 32,message = "payCode: length must be less than 32")
	String payCode;
	
	/** 支付方式名称 **/
    @NotBlank(message = "payName is compulsory")
    @Length(max = 42,message = "payName: length must be less than 128， 汉字长度42")
	String payName;
	
	/** 支付金额 **/
    @NotBlank(message = "payAmount is compulsory")
    @DecimalMin(value="0.0", message = "payAmount should bigger than 0.0")
	String payAmount;
	
	/** 支付时间 **/
	String payTime;
	
	/** 银行卡名称 **/
    @Length(max = 128,message = "bankTypeName: length must be less than 128")
	String bankTypeName;
	
	/** 银行卡编码 **/
    @Length(max = 32,message = "bankTypeCode: length must be less than 32")
	String bankTypeCode;
	
    String cardNo;
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
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
	
}
