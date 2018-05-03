package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author wangqc
 * 子订单(挂单)
 */
@JsonIgnoreProperties(ignoreUnknown = true)  
public class HangOrderSubDTO implements Serializable{
	
	/* 属性说明 */
	private static final long serialVersionUID = -5316819370273247087L;

	/** 订单编号 **/
    @NotBlank(message = "orderNo is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String orderNo;
    
    /** 子编号 **/
    String orderSubNo;
    
	/** 物流商ID，自提点 **/
    @NotBlank(message = "deliveryMerchantNo is compulsory")
    @Length(max = 32,message = "deliveryMerchantNo: length must be less than 32")
	String deliveryMerchantNo;
	
	/** 配送方式 **/
	String distributeType;
	
	/** 物流订单外部编号 **/
    @Length(max = 64,message = "logisticsOutNo: length must be less than 64")
	String logisticsOutNo;
    
	/** 期望送达日期 **/
    @JsonIgnoreProperties(ignoreUnknown = true)
	String hopeArrivalTime;
	
	/** 配送优先级 **/
	@NotBlank(message = "deliveryPriority is compulsory")
    @Length(max = 32,message = "deliveryPriority: length must be less than 32")
	String deliveryPriority;
	
	/** 运费 **/
	@NotBlank(message = "transportFee is compulsory")
    @Length(max = 32,message = "transportFee: length must be less than 32")
	String transportFee;
	
	/** 供应地点 **/
	@NotBlank(message = "provideAddress is compulsory")
    @Length(max = 32,message = "provideAddress: length must be less than 32")
	@JsonIgnoreProperties(ignoreUnknown = true)
	String provideAddress;
	
	/** 自提点 **/
	@NotBlank(message = "selfFetchAddress is compulsory")
    @Length(max = 32,message = "selfFetchAddress: length must be less than 32")
	@JsonIgnoreProperties(ignoreUnknown = true)
	String selfFetchAddress;
	
	/** 自提时间  yyyy-MM-dd**/
	@NotBlank(message = "pickTime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "pickTime formatted as yyyy-MM-dd")
	@JsonIgnoreProperties(ignoreUnknown = true)
	String pickTime;
	
	/** 门店代码 **/
    @NotBlank(message = "storeNo is compulsory, logistics merchant selection needed,物流商选择需要")
    @Length(max = 32,message = "storeNo: length must be less than 32")
    @JsonIgnoreProperties(ignoreUnknown = true)
	String storeNo;
	
	/** 送货备注 **/
    @NotBlank(message = "deliveryRemark is compulsory")
    @Length(max = 500,message = "deliveryRemark: length must be less than 255")
    @JsonIgnoreProperties(ignoreUnknown = true)
	String deliveryRemark;
    
    /** 外部系统订单号 **/
    @NotBlank(message = "aliasOrderSubNo is compulsory")
	String aliasOrderSubNo;
    
    
    //收货人姓名
    String userName;
    //收货人电话1（固话）
    String phoneNum;
    //收货人电话2（移动）
    String mobPhoneNum;
    
    //收货人邮编
    @JsonIgnoreProperties(ignoreUnknown = true)
    String postCode;
    //收货人邮箱
    @JsonIgnoreProperties(ignoreUnknown = true)
    String email;
    
    //收货人地址信息编码
    @JsonIgnoreProperties(ignoreUnknown = true)
    String addressCode;
    
    //收货人具体地址
    @JsonIgnoreProperties(ignoreUnknown = true)
    String addressDetail;
    

    @Valid
    @JsonIgnoreProperties(ignoreUnknown = true)
    HangOrderInvoiceDTO hangOrderInvoice;
    @Valid
    @NotEmpty(message="At least one orderItem is required")
	List<HangOrderItemDTO> oiDTOs;
    /***冗余json字段   ****/
    
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
	public String getDeliveryMerchantNo() {
		return deliveryMerchantNo;
	}
	public void setDeliveryMerchantNo(String deliveryMerchantNo) {
		this.deliveryMerchantNo = deliveryMerchantNo;
	}
	public String getDistributeType() {
		return distributeType;
	}
	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
	public String getLogisticsOutNo() {
		return logisticsOutNo;
	}
	public void setLogisticsOutNo(String logisticsOutNo) {
		this.logisticsOutNo = logisticsOutNo;
	}
	public String getHopeArrivalTime() {
		return hopeArrivalTime;
	}
	public void setHopeArrivalTime(String hopeArrivalTime) {
		this.hopeArrivalTime = hopeArrivalTime;
	}
	public String getDeliveryPriority() {
		return deliveryPriority;
	}
	public void setDeliveryPriority(String deliveryPriority) {
		this.deliveryPriority = deliveryPriority;
	}
	public String getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}
	public String getProvideAddress() {
		return provideAddress;
	}
	public void setProvideAddress(String provideAddress) {
		this.provideAddress = provideAddress;
	}
	public String getSelfFetchAddress() {
		return selfFetchAddress;
	}
	public void setSelfFetchAddress(String selfFetchAddress) {
		this.selfFetchAddress = selfFetchAddress;
	}
	public String getPickTime() {
		return pickTime;
	}
	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getDeliveryRemark() {
		return deliveryRemark;
	}
	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}
	public String getAliasOrderSubNo() {
		return aliasOrderSubNo;
	}
	public void setAliasOrderSubNo(String aliasOrderSubNo) {
		this.aliasOrderSubNo = aliasOrderSubNo;
	}
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
	public HangOrderInvoiceDTO getHangOrderInvoice() {
		return hangOrderInvoice;
	}
	public void setHangOrderInvoice(HangOrderInvoiceDTO hangOrderInvoice) {
		this.hangOrderInvoice = hangOrderInvoice;
	}
	public List<HangOrderItemDTO> getOiDTOs() {
		return oiDTOs;
	}
	public void setOiDTOs(List<HangOrderItemDTO> oiDTOs) {
		this.oiDTOs = oiDTOs;
	}

    
    
}
