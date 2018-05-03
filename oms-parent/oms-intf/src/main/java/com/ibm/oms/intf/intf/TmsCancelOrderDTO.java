package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.ibm.oms.intf.intf.inner.OrderItemTms;
import com.ibm.oms.intf.intf.inner.TmsOrderItemsDTO;

/**
 * TMS订单取消信息
 * 
 */
public class TmsCancelOrderDTO implements Serializable {

    private String orderid;
    private String txLogisticID;
    private String type;
    private String remark;
    /** formatted as yyyy-MM-dd HH:mm:ss **/
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
    private String cancelTime;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

}
