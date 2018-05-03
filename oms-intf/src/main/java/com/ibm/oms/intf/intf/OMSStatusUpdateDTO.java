package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong oms订单状态更新需要同步到其他系统，发送主题
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OMSStatusUpdateDTO implements Serializable {
	private String orderNo;
	@NotBlank(message = "orderSubNo is compulsory")
	private String orderSubNo;
	private String statusType;
	private String oldStatus;
	private String oldStatusName;
	private String newStatus;
	private String newStatusName;
	/** 状态更新时间  dateTime formatted as yyyy-MM-dd HH:mm:ss**/
    //@NotBlank(message = "order_time is compulsory")
    //@Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
    
    /**
     * 返回结果code
     */
    private String returnCode;
    /**
     * 返回结果信息
     */
    private String returnMsg;
	
	
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
    public String getStatusType() {
        return statusType;
    }
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getOldStatus() {
        return oldStatus;
    }
    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }
    public String getOldStatusName() {
        return oldStatusName;
    }
    public void setOldStatusName(String oldStatusName) {
        this.oldStatusName = oldStatusName;
    }
    public String getNewStatusName() {
        return newStatusName;
    }
    public void setNewStatusName(String newStatusName) {
        this.newStatusName = newStatusName;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
	
}
