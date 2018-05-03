package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.sc.rs.bean.Msg;
/**
 * @author pjsong
 * 
 */
@XmlType
@XmlRootElement(name = "CommonOutputDTO")
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommonOutputDTO extends Msg implements Serializable {
    /** 处理成功标志 **/
    String msg;
    String idOrder;
    String orderNo;
    String orderSubNo;
    String orderItemNo;
    boolean isPayOnArrival;
    String leftAmount; // 备用
    Boolean blackListIncluded;
    Boolean priviledgedMember;
    Boolean success;
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

    public boolean isPayOnArrival() {
        return isPayOnArrival;
    }

    public void setPayOnArrival(boolean isPayOnArrival) {
        this.isPayOnArrival = isPayOnArrival;
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

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getBlackListIncluded() {
        return blackListIncluded;
    }

    public void setBlackListIncluded(Boolean blackListIncluded) {
        this.blackListIncluded = blackListIncluded;
    }

    public Boolean getPriviledgedMember() {
        return priviledgedMember;
    }

    public void setPriviledgedMember(Boolean priviledgedMember) {
        this.priviledgedMember = priviledgedMember;
    }

}
