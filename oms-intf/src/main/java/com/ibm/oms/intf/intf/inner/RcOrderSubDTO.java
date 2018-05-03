package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiaohl 子订单
 */
public class RcOrderSubDTO implements Serializable {
    /** 原OMS子订单号 **/
    @NotBlank(message = "order_no is compulsory")
    @Length(max = 22, message = "order_no: length must be less than 22")
    String orderSubRelatedOrigin;

    /** 入库方式 **/
    @NotBlank(message = "distribute_Type is compulsory")
    @Length(max = 32, message = "distribute_Type: length must be less than 32")
    String distributeType;

    /** 快递类型 **/
    @Length(max = 32, message = "express_type: length must be less than 32")
    String expressType;

    /** 门店代退门店号 **/
    @Length(max = 32, message = "store_no: length must be less than 32")
    String storeNo;
    
    /** 快递单号 **/
    @Length(max = 64, message = "logisticsOutNo: length must be less than 64")
    String logisticsOutNo;

    /** 取货联系人姓名 **/
    @NotBlank(message = "user_name is compulsory")
    @Length(max = 128, message = "user_name: length must be less than 128")
    String userName;

    /** 取货联系人电话1（固话） **/
    @Length(max = 32, message = "phone_num: length must be less than 32")
    String phoneNum;

    /** 取货联系人电话2（移动） **/
    @NotBlank(message = "mob_phone_num is compulsory")
    @Length(max = 32, message = "mob_phone_num: length must be less than 32")
    String mobPhoneNum;

    /** 取货地址信息编码 **/
    @NotBlank(message = "address_code is compulsory")
    @Length(max = 255, message = "address_code: length must be less than 255")
    String addressCode;

    /** 取货具体地址 **/
    @NotBlank(message = "address_detail is compulsory")
    @Length(max = 255, message = "address_detail: length must be less than 255")
    String addressDetail;

    /** 是否含发票 **/
    String invoicePrinted;
    
    /**备用字段1**/
    String default1;
    
    /**备用字段2**/
    String default2;
    
    /**备用字段3**/
    String default3;

    @Valid
    List<RcOrderItemDTO> orderItems; // 退换货明细

    public List<RcOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<RcOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

	public String getOrderSubRelatedOrigin() {
		return orderSubRelatedOrigin;
	}

	public void setOrderSubRelatedOrigin(String orderSubRelatedOrigin) {
		this.orderSubRelatedOrigin = orderSubRelatedOrigin;
	}

	public String getDistributeType() {
		return distributeType;
	}

	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
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

	public String getInvoicePrinted() {
		return invoicePrinted;
	}

	public void setInvoicePrinted(String invoicePrinted) {
		this.invoicePrinted = invoicePrinted;
	}

    public String getLogisticsOutNo() {
        return logisticsOutNo;
    }

    public void setLogisticsOutNo(String logisticsOutNo) {
        this.logisticsOutNo = logisticsOutNo;
    }

    public String getDefault1() {
        return default1;
    }

    public void setDefault1(String default1) {
        this.default1 = default1;
    }

    public String getDefault2() {
        return default2;
    }

    public void setDefault2(String default2) {
        this.default2 = default2;
    }

    public String getDefault3() {
        return default3;
    }

    public void setDefault3(String default3) {
        this.default3 = default3;
    }
	
	
}
