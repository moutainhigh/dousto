package com.ibm.oms.service.pay.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PayJsonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String orderNo;//订单号
    private String payType;//支付渠道
    private String paymentCode;//支付码
    private String payMode;//支付方式;
    private String payIp;//支付用户IP
    private String openId;//微信 openid
    private String appid;//商户微信/支付宝 APPID
    private String sub_openid;//sub_openid
    private String start_time;// 订单开始时间，格式 yyyy-MM-dd HH:mm:ss 
    private String expire;//订单过期时间，格式 yyyy-MM-dd HH:mm:ss
    
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getSub_openid() {
		return sub_openid;
	}
	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPayIp() {
		return payIp;
	}
	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
       
}
