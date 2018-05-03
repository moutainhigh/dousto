package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class WmsOmsReceiveLogisticsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 销售订单号 **/
    @NotBlank(message = "orderSubNo is compulsory")
    @Length(max = 20, message = "orderSubNo: length must be less than 20")
    String orderSubNo;
    /** 物流商ID **/
    String deliveryMerchantNo;
    /** 物流外部订单号(承运单号) **/
    String logisticsOutNo;
    /** 订单库存状态码 **/
    @NotBlank(message = "logisticsStatus is compulsory")
    @Length(max = 32, message = "logisticsStatus: length must be less than 32")
    String logisticsStatus;
    /** 订单库存状态信息描述 **/
    @NotBlank(message = "logisticsDesc is compulsory")
    @Length(max = 255, message = "logisticsDesc: length must be less than 255")
    String logisticsDesc;
    /** 操作时间 **/
    @NotBlank(message = "operate_time is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
    String operateTime;
    /** 操作人 **/
    @NotBlank(message = "operator is compulsory")
    String operator;
    /** 箱号 **/
    String bolNo;
    /** 备注 **/
    String remark;

    public String getDeliveryMerchantNo() {
        return deliveryMerchantNo;
    }

    public void setDeliveryMerchantNo(String deliveryMerchantNo) {
        this.deliveryMerchantNo = deliveryMerchantNo;
    }

    public String getLogisticsOutNo() {
		return logisticsOutNo;
	}

	public void setLogisticsOutNo(String logisticsOutNo) {
		this.logisticsOutNo = logisticsOutNo;
	}

	public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getLogisticsDesc() {
        return logisticsDesc;
    }

    public void setLogisticsDesc(String logisticsDesc) {
        this.logisticsDesc = logisticsDesc;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBolNo() {
        return bolNo;
    }

    public void setBolNo(String bolNo) {
        this.bolNo = bolNo;
    }

}

