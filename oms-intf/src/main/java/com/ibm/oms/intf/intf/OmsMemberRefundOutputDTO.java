package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author pjsong
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OmsMemberRefundOutputDTO implements Serializable {

    // "code": "SUCCESS",
    String code;// "SUCCESS"、"FAILED"
    // "msg": "退款成功",
    String msg;
    String idOrder;
    String orderNo;
    String orderSubNo;
    String orderItemNo;
    // "payOnArrival": false
    boolean payOnArrival;
    String leftAmount; // 金额(积分)不够扣减时，剩余可退积分

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

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

    public String getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public boolean isPayOnArrival() {
        return payOnArrival;
    }

    public void setPayOnArrival(boolean payOnArrival) {
        this.payOnArrival = payOnArrival;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

}
