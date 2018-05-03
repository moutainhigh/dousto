package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;

public class CouponDTO implements Serializable {
    String orderSubNo;
    String couponNo;
    BigDecimal couponAmount;
    public String getCouponNo() {
        return couponNo;
    }
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }
    public String getOrderSubNo() {
        return orderSubNo;
    }
    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }
    
    
}
