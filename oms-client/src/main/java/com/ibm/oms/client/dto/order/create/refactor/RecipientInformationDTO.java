package com.ibm.oms.client.dto.order.create.refactor;

import java.io.Serializable;

/**
 * 收货相关DTO
 * @author wangchao
 *
 */
public class RecipientInformationDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8785153444496450002L;
	/** 收货人姓名 **/
	public String userName;
	/** 收货人电话1（固话） **/
	public String phoneNum;
	/** 收货人电话2（移动） **/
	public String mobPhoneNum;
	/** 收货人邮编 **/
	public String postCode;
	/** 收货人邮箱 **/
	public String email;
	/** 收货人地址信息编码 国标码记录到区code **/
	public String addressCode;
	/** 收货人具体地址 **/
	public String addressDetail;
	/** 省Code **/
	public String provinceCode;
	/** 省名称 **/
	public String provinceName;
	/** 市Code **/
	public String cityCode;
	/** 市名称 **/
	public String cityName;
	/** 区Code **/
	public String districtCode;
	/** 区名称 **/
	public String districtName;
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMobPhoneNum() {
		return mobPhoneNum;
	}
	public void setMobPhoneNum(String mobPhoneNum) {
		this.mobPhoneNum = mobPhoneNum;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
	
}
