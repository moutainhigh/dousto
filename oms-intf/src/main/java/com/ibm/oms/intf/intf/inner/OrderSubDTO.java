package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author pjsong
 * 子订单
 */
public class OrderSubDTO implements Serializable{
	
	/** 物流商ID，自提点 **/
    @Length(max = 32,message = "deliveryMerchantNo: length must be less than 32")
	String deliveryMerchantNo;
	
	/** 配送方式 **/
    //@NotNull(message = "distributeType is compulsory")
    @NotBlank(message = "distributeType is compulsory, logistics merchant selection needed,物流商选择需要")
    //@Pattern(regexp = "[0127]", message="distribute type must be 0, 1, 2, 7")
	String distributeType;
	
	/** 物流订单外部编号 **/
    @Length(max = 64,message = "logisticsOutNo: length must be less than 64")
	String logisticsOutNo;
    
	/** 期望送达日期 **/
	String hopeArrivalTime;
	
	/** 配送优先级 **/
    @Length(max = 32,message = "deliveryPriority: length must be less than 32")
	String deliveryPriority;
	
	/** 运费 **/
	String transportFee;
	
	/** 供应地点 **/
    @Length(max = 32,message = "provideAddress: length must be less than 32")
	String provideAddress;
	
	/** 自提点 **/
    @Length(max = 32,message = "selfFetchAddress: length must be less than 32")
	String selfFetchAddress;
	
	/** 自提时间  yyyy-MM-dd**/
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "pickTime formatted as yyyy-MM-dd")
	String pickTime;
	
	/** 门店代码 **/
    @NotBlank(message = "storeNo is compulsory, logistics merchant selection needed,物流商选择需要")
    @Length(max = 32,message = "storeNo: length must be less than 32")
	String storeNo;
	
	/** 送货备注 **/
    @Length(max = 255,message = "deliveryRemark: length must be less than 255")
	String deliveryRemark;
	
	/** 装箱清单文本 **/
    @Length(max = 255,message = "packageDetail: length must be less than 255")
	String packageDetail;
    String aliasOrderNo;
    
    //@NotBlank(message = "aliasOrderSubNo is compulsory")
    String aliasOrderSubNo;
    //收货人姓名
    String userName;
    //收货人电话1（固话）
    String phoneNum;
    //收货人电话2（移动）
    String mobPhoneNum;
    //收货人邮编
    String postCode;
    //收货人邮箱
    String email;
    //虚拟商品没有
    //收货人地址信息编码
    //@NotBlank(message = "addressCode is compulsory, logistics merchant selection needed， 物流商选择必填", )
    String addressCode;
    //收货人具体地址
    String addressDetail;
    @Valid
    OrderInvoiceDTO orderInvoice;
    @Valid
   // @NotEmpty(message="At least one orderItem is required")
	List<OrderItemDTO> oiDTOs;
    
    /** 运单号20180307 add **/
    String shippingOrderNo;
    
    
    /**虚拟，实体卡订单**/
    @Valid
    List<OrderItemVirtualDTO> oivDTOs;
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
	public String getPackageDetail() {
		return packageDetail;
	}
	public void setPackageDetail(String packageDetail) {
		this.packageDetail = packageDetail;
	}

    public List<OrderItemDTO> getOiDTOs() {
        return oiDTOs;
    }
    public void setOiDTOs(List<OrderItemDTO> oiDTOs) {
        this.oiDTOs = oiDTOs;
    }
    public String getAliasOrderNo() {
        return aliasOrderNo;
    }
    public void setAliasOrderNo(String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
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
	public OrderInvoiceDTO getOrderInvoice() {
		return orderInvoice;
	}
	public void setOrderInvoice(OrderInvoiceDTO orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

    public List<OrderItemVirtualDTO> getOivDTOs() {
        return oivDTOs;
    }

    public void setOivDTOs(List<OrderItemVirtualDTO> oivDTOs) {
        this.oivDTOs = oivDTOs;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}
	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}
    
}
