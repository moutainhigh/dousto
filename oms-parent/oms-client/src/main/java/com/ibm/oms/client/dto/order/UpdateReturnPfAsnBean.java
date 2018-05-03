package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口）主订单接收实体类
 * @create: 2018-03-12 14:25
 **/
public class UpdateReturnPfAsnBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderCode;//发货单号
    private String orderStatus;//发货单物流状态：//WMS_ACCEPT-接单成功（异步接口回传）//WMS_REJECT-接单失败（异步接口回传）
    //INPROCESS-处理中//FULFILLED-收货完成//CANCELED-取消//CLOSED-关闭//EXCEPTION-异常
    private String operator;//操作人
    private String operatorTime;//操作时间（Y-m-d H:i:s）
    private String logisticsProviderCode;//物流公司代码
    private String shippingOrderNo;//运单号
    private String note;//备注或失败原因
    private String weight;//称重重量
    private String volume;//称重体积
    private String callback;//回调的方法（WMS原样返回E3通知过去的字段内容）
    private String exceptionCode;//发货异常编码：INSUFFICIENT_INVENTORY-库存不足 OTHER-其他异常
    private Products products;//商品明细（可多选）

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getLogisticsProviderCode() {
        return logisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode) {
        this.logisticsProviderCode = logisticsProviderCode;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
