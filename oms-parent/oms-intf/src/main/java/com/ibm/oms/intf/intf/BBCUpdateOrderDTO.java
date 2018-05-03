package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * @author JJL
 * 
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BBCUpdateOrderDTO implements Serializable {

	@NotBlank(message = "orderSubNo is compulsory")
    String orderSubNo;//订单号

    String userName;//姓名

    String mobPhoneNum;//手机
    
    String phoneNum;//电话
    
    String addressDetail;//地址
    
    String postCode;// 邮编
    
    String logisticsOutNo;// 运单号
    
    String operateName; //操作人姓名
    
    String ip;//ip
    
    String merchantNo;//商家编号
   
    String deliveryMerchantName;//物流商
    
    String combinedAddress;//配送区域code
    
    String remark;//客服备注
        

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobPhoneNum() {
		return mobPhoneNum;
	}

	public void setMobPhoneNum(String mobPhoneNum) {
		this.mobPhoneNum = mobPhoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getDeliveryMerchantName() {
		return deliveryMerchantName;
	}

	public void setDeliveryMerchantName(String deliveryMerchantName) {
		this.deliveryMerchantName = deliveryMerchantName;
	}

	public String getCombinedAddress() {
		return combinedAddress;
	}

	public void setCombinedAddress(String combinedAddress) {
		this.combinedAddress = combinedAddress;
	}

	public String getLogisticsOutNo() {
		return logisticsOutNo;
	}

	public void setLogisticsOutNo(String logisticsOutNo) {
		this.logisticsOutNo = logisticsOutNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
     
	
}
