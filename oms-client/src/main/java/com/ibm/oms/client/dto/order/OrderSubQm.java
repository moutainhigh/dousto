package com.ibm.oms.client.dto.order;

import java.io.Serializable;

public class OrderSubQm implements Serializable {
    private String zip;//邮政编码 有
    private String country;//国家
    private String shippingCode;//快递编码 有
    private String shippingName;//快递名称 有
    private String payTime;//支付时间 有
    private String city;//城市 有
    private String idCard;//身份证号
    private String mobile;//电话 有
    private String remark;//备注 有
    private String receiverAddress;//接收地址 有
    private String shippingFee;//运费 无
    private String province;//省份 有
    private String createTime;//创建时间 有
    private String district;//区县 有
    private String name;//接收人姓名
    private String tel;//固定电话 有
    private String shippingTime;//发货时间 有
    private String payCode;//支付方式代码 有
    private String payName;//支付方式名称 有
    private String account;//支付账号 有

    public OrderSubQm() {
    }

    public void setZip (String zip) {
        this.zip  = zip ;
    }
    public String getZip () {
        return zip ;
    }

    public void setCountry (String country ) {
        this.country  = country ;
    }
    public String getCountry () {
        return country ;
    }

    public void setShippingCode (String shippingCode) {
        this.shippingCode  = shippingCode ;
    }
    public String getShippingCode () {
        return shippingCode ;
    }

    public void setShippingName (String shippingName ) {
        this.shippingName  = shippingName ;
    }
    public String getShippingName () {
        return shippingName ;
    }

    public void setPayTime (String payTime ) {
        this.payTime  = payTime ;
    }
    public String getPayTime () {
        return payTime ;
    }

    public void setCity (String city ) {
        this.city  = city ;
    }
    public String getCity () {
        return city ;
    }

    public void setIdCard (String idCard ) {
        this.idCard  = idCard ;
    }
    public String getIdCard () {
        return idCard ;
    }

    public void setMobile (String mobile ) {
        this.mobile  = mobile ;
    }
    public String getMobile () {
        return mobile ;
    }

    public void setRemark (String remark ) {
        this.remark  = remark ;
    }
    public String getRemark () {
        return remark ;
    }

    public void setReceiverAddress (String receiverAddress ) {
        this.receiverAddress  = receiverAddress ;
    }
    public String getReceiverAddress () {
        return receiverAddress ;
    }

    public void setShippingFee (String shippingFee ) {
        this.shippingFee  = shippingFee ;
    }
    public String getShippingFee () {
        return shippingFee ;
    }

    public void setProvince (String province ) {
        this.province  = province ;
    }
    public String getProvince () {
        return province ;
    }

    public void setCreateTime (String createTime ) {
        this.createTime  = createTime ;
    }
    public String getCreateTime () {
        return createTime ;
    }

    public void setDistrict (String district ) {
        this.district  = district ;
    }
    public String getDistrict () {
        return district ;
    }

    public void setName (String name ) {
        this.name  = name ;
    }
    public String getName () {
        return name ;
    }

    public void setTel (String tel ) {
        this.tel  = tel ;
    }
    public String getTel () {
        return tel ;
    }

    public void setShippingTime (String shippingTime ) {
        this.shippingTime  = shippingTime ;
    }
    public String getShippingTime () {
        return shippingTime ;
    }

    public void setPayCode (String payCode ) {
        this.payCode  = payCode ;
    }
    public String getPayCode () {
        return payCode ;
    }

    public void setPayName (String payName ) {
        this.payName  = payName ;
    }
    public String getPayName () {
        return payName ;
    }

    public void setAccount (String account ) {
        this.account  = account ;
    }
    public String getAccount () {
        return account ;
    }
}