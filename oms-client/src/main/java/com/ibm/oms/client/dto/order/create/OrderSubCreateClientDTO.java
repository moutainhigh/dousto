package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

/**
 * @author pjsong
 * 子订单
 */
public class OrderSubCreateClientDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2068217036156174241L;

	/** 物流商ID，自提点 **/
	String deliveryMerchantNo;
	
	/** 配送方式 **/
	String distributeType;
	
	/** 物流订单外部编号 **/
	String logisticsOutNo;
    
	/** 期望送达日期 **/
	String hopeArrivalTime;
	
	/** 配送优先级 **/
    @Length(max = 32,message = "deliveryPriority: length must be less than 32")
	String deliveryPriority;
	
	/** 运费 **/
	String transportFee;
	
	/** 供应地点 **/
	String provideAddress;
	
	/** 自提点 **/
	String selfFetchAddress;
	
	/** 自提时间  yyyy-MM-dd**/
	String pickTime;
	
	/** 门店代码 **/
	String storeNo;
	
	/** 送货备注 **/
	String deliveryRemark;
	
	/** 装箱清单文本 **/
	String packageDetail;
    String aliasOrderNo;
    
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
    OrderInvoiceCreateClientDTO orderInvoice;
    @Valid
   // @NotEmpty(message="At least one orderItem is required")
	List<OrderItemCreateClientDTO> oiDTOs;
    
    /** 运单号20180307 add **/
    String shippingOrderNo;
    
    
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

    public List<OrderItemCreateClientDTO> getOiDTOs() {
        return oiDTOs;
    }
    public void setOiDTOs(List<OrderItemCreateClientDTO> oiDTOs) {
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
	public OrderInvoiceCreateClientDTO getOrderInvoice() {
		return orderInvoice;
	}
	public void setOrderInvoice(OrderInvoiceCreateClientDTO orderInvoice) {
		this.orderInvoice = orderInvoice;
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
