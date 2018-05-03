package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.*;

/**
 * Created by 王琦琛 on 2018/1/26.
 * 子订单抬头
 */
public class OrderSubClientDTO implements Serializable{
    private String orderNo;//订单编号
    private String orderSubNo;//子订单编号
    private String deliveryMerchantNo;//物流商ID
    private String distributeType;//配送方式
    private String logisticsOutNo;//物流订单外部编号
    private String hopeArrivalTime;//期望送达日期
    private String deliveryPriority;//配送优先级
    private String transportFee;//运费
    private String provideAddress;//供应地点
    private String selfFetchAddress;//自提点
    private String pickTime;//自提时间
    private String storeNo;//门店代码
    private String deliveryRemark;//送货备注
    private String aliasOrderSubNo;//外部系统订单号
    private String userName;//收货人姓名
    private String phoneNum;//收货人电话1（固话）
    private String mobPhoneNum;//收货人电话2（移动）
    private String postCode;//收货人邮编
    private String email;//收货人邮箱
    private String addressCode;//收货人地址信息编码
    private String addressDetail;//收货人具体地址
    private List<OrderItemClientDTO> orderItemDTO;//订单行项目
    private OrderInvoiceClientDTO orderInvoiceDTO;//发票信息实体

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

    public String getDeliveryMerchantNo() {
        return deliveryMerchantNo;
    }

    public void setDeliveryMerchantNo(String deliveryMerchantNo) {
        this.deliveryMerchantNo = deliveryMerchantNo;
    }

    public String getDistributeType() {
        return distributeType;
    }

    public void setDistributeType(String distributeType) {
        this.distributeType = distributeType;
    }

    public String getLogisticsOutNo() {
        return logisticsOutNo;
    }

    public void setLogisticsOutNo(String logisticsOutNo) {
        this.logisticsOutNo = logisticsOutNo;
    }

    public String getHopeArrivalTime() {
        return hopeArrivalTime;
    }

    public void setHopeArrivalTime(String hopeArrivalTime) {
        this.hopeArrivalTime = hopeArrivalTime;
    }

    public String getDeliveryPriority() {
        return deliveryPriority;
    }

    public void setDeliveryPriority(String deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public String getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(String transportFee) {
        this.transportFee = transportFee;
    }

    public String getProvideAddress() {
        return provideAddress;
    }

    public void setProvideAddress(String provideAddress) {
        this.provideAddress = provideAddress;
    }

    public String getSelfFetchAddress() {
        return selfFetchAddress;
    }

    public void setSelfFetchAddress(String selfFetchAddress) {
        this.selfFetchAddress = selfFetchAddress;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getDeliveryRemark() {
        return deliveryRemark;
    }

    public void setDeliveryRemark(String deliveryRemark) {
        this.deliveryRemark = deliveryRemark;
    }

    public String getAliasOrderSubNo() {
        return aliasOrderSubNo;
    }

    public void setAliasOrderSubNo(String aliasOrderSubNo) {
        this.aliasOrderSubNo = aliasOrderSubNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMobPhoneNum() {
        return mobPhoneNum;
    }

    public void setMobPhoneNum(String mobPhoneNum) {
        this.mobPhoneNum = mobPhoneNum;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public List<OrderItemClientDTO> getOrderItemDTO() {
        return orderItemDTO;
    }

    public void setOrderItemDTO(List<OrderItemClientDTO> orderItemDTO) {
        this.orderItemDTO = orderItemDTO;
    }

    public OrderInvoiceClientDTO getOrderInvoiceDTO() {
        return orderInvoiceDTO;
    }

    public void setOrderInvoiceDTO(OrderInvoiceClientDTO orderInvoiceDTO) {
        this.orderInvoiceDTO = orderInvoiceDTO;
    }

    @Override
    public String toString() {
        return "OrderSubDTO{" +
                "orderNo='" + orderNo + '\'' +
                ", orderSubNo='" + orderSubNo + '\'' +
                ", deliveryMerchantNo='" + deliveryMerchantNo + '\'' +
                ", distributeType='" + distributeType + '\'' +
                ", logisticsOutNo='" + logisticsOutNo + '\'' +
                ", hopeArrivalTime='" + hopeArrivalTime + '\'' +
                ", deliveryPriority='" + deliveryPriority + '\'' +
                ", transportFee='" + transportFee + '\'' +
                ", provideAddress='" + provideAddress + '\'' +
                ", selfFetchAddress='" + selfFetchAddress + '\'' +
                ", pickTime='" + pickTime + '\'' +
                ", storeNo='" + storeNo + '\'' +
                ", deliveryRemark='" + deliveryRemark + '\'' +
                ", aliasOrderSubNo='" + aliasOrderSubNo + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", mobPhoneNum='" + mobPhoneNum + '\'' +
                ", postCode='" + postCode + '\'' +
                ", email='" + email + '\'' +
                ", addressCode='" + addressCode + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", orderItemDTO=" + orderItemDTO +
                ", orderInvoiceDTO=" + orderInvoiceDTO +
                '}';
    }
}
