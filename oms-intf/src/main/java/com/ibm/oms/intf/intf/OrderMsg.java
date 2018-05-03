package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author xiaonanxiang
 */
@XmlType
@XmlRootElement(name = "OrderMsg")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OrderMsg implements Serializable {
    private static final long serialVersionUID = 12352434L;

    /**
     * 渠道
     */
    private java.lang.String channelId;
    /**
     * 外部主订单号
     */
    private java.lang.String orderOuterNo;
    /**
     * 子订单号
     */
    private java.lang.String orderSubNo;
    /**
     * 审核状态
     */
    private java.lang.String statusConfirm;
    /**
     * 物流状态
     */
    private java.lang.String statusWuliu;
    /**
     * 是否货到付款
     */
    private java.lang.String ifPayOnArrival;
    /**
     * 支付状态
     */
    private java.lang.String statusPay;
    /**
     * 总状态
     */
    private java.lang.String statusTotal;
    /**
     * 收货地址【出库发货时必填】 格式例如：雷小米，0755-86010000，13800138000，广东 深圳市 宝安区 雷布斯研发部
     */
    private java.lang.String recvAddr;
    /**
     * 物流公司名称
     */
    private java.lang.String wuliuCompany;
    /**
     * 发货单号(运单号)
     */
    private java.lang.String wuliuCode;
    /**
     * 预计几天后到货 , 取值范围:0到30 （必填）
     */
    private java.lang.String arriveDays = "2";
    /**
     * 订单关闭原因
     */
    private java.lang.String closeReasonDesc;
    private java.lang.String failContent;
    private java.lang.String taskType;
    private java.lang.Integer iStatus;

    public java.lang.String getChannelId() {
        return channelId;
    }

    public void setChannelId(java.lang.String channelId) {
        this.channelId = channelId;
    }

    public java.lang.String getOrderOuterNo() {
        return orderOuterNo;
    }

    public void setOrderOuterNo(java.lang.String orderOuterNo) {
        this.orderOuterNo = orderOuterNo;
    }

    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(java.lang.String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public java.lang.String getStatusConfirm() {
        return statusConfirm;
    }

    public void setStatusConfirm(java.lang.String statusConfirm) {
        this.statusConfirm = statusConfirm;
    }

    public java.lang.String getIfPayOnArrival() {
        return ifPayOnArrival;
    }

    public void setIfPayOnArrival(java.lang.String ifPayOnArrival) {
        this.ifPayOnArrival = ifPayOnArrival;
    }

    public java.lang.String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(java.lang.String statusPay) {
        this.statusPay = statusPay;
    }

    public java.lang.String getStatusTotal() {
        return statusTotal;
    }

    public void setStatusTotal(java.lang.String statusTotal) {
        this.statusTotal = statusTotal;
    }

    public java.lang.String getRecvAddr() {
        return recvAddr;
    }

    public void setRecvAddr(java.lang.String recvAddr) {
        this.recvAddr = recvAddr;
    }

    public java.lang.String getWuliuCompany() {
        return wuliuCompany;
    }

    public void setWuliuCompany(java.lang.String wuliuCompany) {
        this.wuliuCompany = wuliuCompany;
    }

    public java.lang.String getWuliuCode() {
        return wuliuCode;
    }

    public void setWuliuCode(java.lang.String wuliuCode) {
        this.wuliuCode = wuliuCode;
    }

    public java.lang.String getArriveDays() {
        return arriveDays;
    }

    public void setArriveDays(java.lang.String arriveDays) {
        this.arriveDays = arriveDays;
    }

    public java.lang.String getCloseReasonDesc() {
        return closeReasonDesc;
    }

    public void setCloseReasonDesc(java.lang.String closeReasonDesc) {
        this.closeReasonDesc = closeReasonDesc;
    }

    public java.lang.String getStatusWuliu() {
        return statusWuliu;
    }

    public void setStatusWuliu(java.lang.String statusWuliu) {
        this.statusWuliu = statusWuliu;
    }

    public java.lang.String getFailContent() {
        return failContent;
    }

    public void setFailContent(java.lang.String failContent) {
        this.failContent = failContent;
    }

    public java.lang.String getTaskType() {
        return taskType;
    }

    public void setTaskType(java.lang.String taskType) {
        this.taskType = taskType;
    }

    public java.lang.Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(java.lang.Integer iStatus) {
        this.iStatus = iStatus;
    }

}
