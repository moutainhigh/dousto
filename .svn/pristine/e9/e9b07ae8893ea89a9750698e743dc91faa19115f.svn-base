package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.oms.intf.intf.inner.TmsOrderItemsDTO;

/**
 * 订单信息
 * 
 * 2014-4-28 上午11:58:03
 */
@JsonIgnoreProperties(ignoreUnknown=true) 
public class TmsOrderDTO implements Serializable {
    private String logisticCompanyId; //物流商id
    private String txLogisticID;
    private String orderid;
    private String srcOrderNo;
    private String type;
    private int flag;
    private String name;
    private String postCode;
    private String phone;
    private String mobile;
    private String prov;
    private String city;
    private String area;
    private String addresscode;
    private String address;
    private double goodsValue;
    private double itemsValue; //（逆向：退款金额）
    private int totalPCS; //商品总金额
    private double totalWeight;
    private String remark;
    private double insuranceValue;
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
    private String createTime;
    private int needInvoice;
    private String payMode; //支付方式（逆向：退款方式id）
    private String toid;  //箱号（逆向：入库物流方式id）
    private int deliverymode;
    /** formatted as yyyy-MM-dd HH:mm:ss **/
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
    private String outhousetime;
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
    private String reviewtime;
    private int wmsOrderType;
    //自提点id
    private String selfFetchPointId;
    //自提商户id
    private String selfFetchMerchantId;
    
    private TmsOrderItemsDTO items;// 这是一个XML

    public String getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(String logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddresscode() {
        return addresscode;
    }

    public void setAddresscode(String addresscode) {
        this.addresscode = addresscode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(double goodsValue) {
        this.goodsValue = goodsValue;
    }

    public double getItemsValue() {
        return itemsValue;
    }

    public void setItemsValue(double itemsValue) {
        this.itemsValue = itemsValue;
    }

    public int getTotalPCS() {
        return totalPCS;
    }

    public void setTotalPCS(int totalPCS) {
        this.totalPCS = totalPCS;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(int needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public int getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(int deliverymode) {
        this.deliverymode = deliverymode;
    }

    public int getWmsOrderType() {
        return wmsOrderType;
    }

    public void setWmsOrderType(int wmsOrderType) {
        this.wmsOrderType = wmsOrderType;
    }

    public TmsOrderItemsDTO getItems() {
        return items;
    }

    public void setItems(TmsOrderItemsDTO items) {
        this.items = items;
    }

    public String getOuthousetime() {
        return outhousetime;
    }

    public void setOuthousetime(String outhousetime) {
        this.outhousetime = outhousetime;
    }

    public String getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(String reviewtime) {
        this.reviewtime = reviewtime;
    }

    public String getSrcOrderNo() {
        return srcOrderNo;
    }

    public void setSrcOrderNo(String srcOrderNo) {
        this.srcOrderNo = srcOrderNo;
    }

    public String getSelfFetchPointId() {
        return selfFetchPointId;
    }

    public void setSelfFetchPointId(String selfFetchPointId) {
        this.selfFetchPointId = selfFetchPointId;
    }

    public String getSelfFetchMerchantId() {
        return selfFetchMerchantId;
    }

    public void setSelfFetchMerchantId(String selfFetchMerchantId) {
        this.selfFetchMerchantId = selfFetchMerchantId;
    }

    
    
}
