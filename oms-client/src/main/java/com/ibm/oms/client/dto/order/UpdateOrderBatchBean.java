package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS-->中台 批发退货单状态更新通知接口实体类
 * @create: 2018-03-15 15:21
 **/
public class UpdateOrderBatchBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String wmsBillCode;//WMS关联单号，保证唯一
    private String warehouseCode;//仓库代码
    private String asnCode;//批发退货通知单单号
    private String asnStatus;//补货单状态：//WMS_ACCEPT-接单成功（异步接口回传）//WMS_REJECT-接单失败（异步接口回传）//INPROCESS-处理中//FULFILLED-收货完成//CANCELED-取消//CLOSED-关闭//EXCEPTION-异常
    private String isAsnFinished;//通知单完成0 –未完成1 –完成
    private String note;//备注或失败原因
    private String operator;//操作人
    private String operatorTime;//操作时间（Y-m-d H:i:s）
    private String exceptionCode;//入库异常编码
    private String callback;//回调的方法（WMS原样返回E3通知过去的字段内容）
    private String LogisticsProviderCode;
    private String ShippingOrderNo;
    private Products products;//商品明细（可多选）
    private Cases cases;//配码明细（可多选）
    private Items items;

    public String getLogisticsProviderCode() {
        return LogisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode) {
        LogisticsProviderCode = logisticsProviderCode;
    }

    public String getShippingOrderNo() {
        return ShippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        ShippingOrderNo = shippingOrderNo;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWmsBillCode() {
        return wmsBillCode;
    }

    public void setWmsBillCode(String wmsBillCode) {
        this.wmsBillCode = wmsBillCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public String getAsnStatus() {
        return asnStatus;
    }

    public void setAsnStatus(String asnStatus) {
        this.asnStatus = asnStatus;
    }

    public String getIsAsnFinished() {
        return isAsnFinished;
    }

    public void setIsAsnFinished(String isAsnFinished) {
        this.isAsnFinished = isAsnFinished;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
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

    public Cases getCases() {
        return cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

}
