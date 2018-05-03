package com.ibm.oms.intf.intf;

import java.io.Serializable;

public class TransportCompParam implements Serializable {
    private String storageId;// 发货仓库ID
    private String areaId;// 送货区域ID
    private String catagoryId;// 运输分类ID 生鲜，大家电，其他
    private String deliverTypeCode;// 配送方式
    private String paymentModeId;// 支付方式
    private String weight;// 重量
    private String orderHour;//
    private String selfTakePointId;
    private String orderSubNo;
    private String mechantId; // 商家id

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getDeliverTypeCode() {
        return deliverTypeCode;
    }

    public void setDeliverTypeCode(String deliverTypeCode) {
        this.deliverTypeCode = deliverTypeCode;
    }

    public String getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(String paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrderHour() {
        return orderHour;
    }

    public void setOrderHour(String orderHour) {
        this.orderHour = orderHour;
    }

    public String getSelfTakePointId() {
        return selfTakePointId;
    }

    public void setSelfTakePointId(String selfTakePointId) {
        this.selfTakePointId = selfTakePointId;
    }

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public String getMechantId() {
        return mechantId;
    }

    public void setMechantId(String mechantId) {
        this.mechantId = mechantId;
    }

}
