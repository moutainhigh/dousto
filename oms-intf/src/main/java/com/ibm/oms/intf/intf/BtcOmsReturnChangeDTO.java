package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ibm.oms.intf.intf.inner.ChangeOrderReciveInfoDTO;
import com.ibm.oms.intf.intf.inner.RcOrderSubDTO;

/**
 * @author xiaohl B2C的退货订单传输到OMS
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BtcOmsReturnChangeDTO implements Serializable {

    /** 原OMS订单号 **/
    @NotBlank(message = "order_no is compulsory")
    @Length(max = 16, message = "order_no: length must be less than 20")
    String orderRelatedOrigin;

    /** 外部订单号 **/
//    @NotBlank(message = "alias_order_no is compulsory")
//    @Length(max = 16, message = "alias_order_no: length must be less than 32")
//    String alias_order_no;

    /** 外部原订单号 **/
//    @NotBlank(message = "alias_old_order_no is compulsory")
//    @Length(max = 16, message = "alias_old_order_no: length must be less than 32")
//    String alias_old_order_no;

    /** 退换货业务类型 **/
    @NotBlank(message = "order_category is compulsory")
    @Length(max = 16, message = "order_category: length must be less than 32")
    @Pattern(regexp="(ret)|(rej)|(chg)")
    String orderCategory;

    /** 物流费用 **/
    @NotBlank(message = "transport_fee is compulsory")
    String transportFee;

    /** 问题描述 **/
    @NotBlank(message = "remark is compulsory")
    @Length(max = 255, message = "remark: length must be less than 255")
    String remark;

    /** 来源系统 **/
    @NotBlank(message = "order_source is compulsory")
    @Length(max = 32, message = "order_source: length must be less than 32")
    String orderSource;

    /** 订单产生时间 **/
//    @NotBlank(message = "order_time is compulsory")
//    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[1-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
//    String order_time;

    /** 退款方式 **/
    @Length(max = 32, message = "pay_code: length must be less than 32")
    String payCode;
    /** 退款方式名称 **/
    @Length(max = 128, message = "pay_name: length must be less than 128")
    String payName;

    /** 子订单 **/
    public RcOrderSubDTO orderSub;
    /** 换货(出库单)的收获地址 **/
    public ChangeOrderReciveInfoDTO reciveinfo;



//    public String getAlias_old_order_no() {
//        return alias_old_order_no;
//    }
//
//    public void setAlias_old_order_no(String alias_old_order_no) {
//        this.alias_old_order_no = alias_old_order_no;
//    }



//    public String getAlias_order_no() {
//        return alias_order_no;
//    }
//
//    public void setAlias_order_no(String alias_order_no) {
//        this.alias_order_no = alias_order_no;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderRelatedOrigin() {
		return orderRelatedOrigin;
	}

	public void setOrderRelatedOrigin(String orderRelatedOrigin) {
		this.orderRelatedOrigin = orderRelatedOrigin;
	}

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
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

	public RcOrderSubDTO getOrderSub() {
        return orderSub;
    }

    public void setOrderSub(RcOrderSubDTO orderSub) {
        this.orderSub = orderSub;
    }

    public ChangeOrderReciveInfoDTO getReciveinfo() {
        return reciveinfo;
    }

    public void setReciveinfo(ChangeOrderReciveInfoDTO reciveinfo) {
        this.reciveinfo = reciveinfo;
    }

//    public String getOrder_time() {
//        return order_time;
//    }
//
//    public void setOrder_time(String order_time) {
//        this.order_time = order_time;
//    }

}
