package com.ibm.oms.client.dto.order;

import java.util.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 王琦琛 on 2018/1/26.
 * 订单抬头
 */
public class OrderHeaderClientDTO implements Serializable{
    private String orderNo;//订单编号
    private String aliasOrderNo;//外部渠道订单编号
    private String orderType;//订单类型
    private String orderSource;//订单来源
    private String orderStatus;//订单状态
    private String memberNo;//会员ID
    private String customerName;//会员名称
    private String customerPhone;//会员电话
    private String customerEmail;//会员邮件
    private int totalProductCount;//商品数量总计
    private BigDecimal totalProductPrice;//商品金额总计
    private BigDecimal discountTotal;//折扣总额
    private BigDecimal totalPromo;//优惠券总额
    private BigDecimal totalOrderAmount;//订单金额总计
    private BigDecimal discountTransport;//运费
    private BigDecimal pointDiscountAmount;//积分抵现金额
    private BigDecimal totalPayAmount;//订单实际应付金额总计
    private BigDecimal prepaidAmount;//订单预付金额
    private String wmsID;//库存ID
    private String couponsNo;//优惠券号
    private String merchantType;//商家类型
    private String merchantNo;//家商编号
    private String shopNo;//门店编号
    private String salespersonNo;//营业员编号
    private int totalGivePoints;//赠送总积分
    private String needInvoince;//是否有发票
    
    //add 2818-3-15 
    private String memberVipCardLevel;
    private String ifPayOnArrival;
    
    private String orderTime;//订单产生时间
    private String ip;//下单IP地址
    private String orderAttribute;//订单属性
    
    private List<OrderSubClientDTO> orderSubDTOS;//子订单列表
    private List<OrderPayClientDTO> orderPayDTOS;//订单支付信息
    private List<OrderPromotionClientDTO> orderPromotionDTOS; //订单促销信息
    

   	public String getMemberVipCardLevel() {
		return memberVipCardLevel;
	}

	public void setMemberVipCardLevel(String memberVipCardLevel) {
		this.memberVipCardLevel = memberVipCardLevel;
	}

	public String getIfPayOnArrival() {
		return ifPayOnArrival;
	}

	public void setIfPayOnArrival(String ifPayOnArrival) {
		this.ifPayOnArrival = ifPayOnArrival;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getTotalPromo() {
        return totalPromo;
    }

    public void setTotalPromo(BigDecimal totalPromo) {
        this.totalPromo = totalPromo;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BigDecimal getDiscountTransport() {
        return discountTransport;
    }

    public void setDiscountTransport(BigDecimal discountTransport) {
        this.discountTransport = discountTransport;
    }

    public BigDecimal getPointDiscountAmount() {
        return pointDiscountAmount;
    }

    public void setPointDiscountAmount(BigDecimal pointDiscountAmount) {
        this.pointDiscountAmount = pointDiscountAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(BigDecimal prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public String getWmsID() {
        return wmsID;
    }

    public void setWmsID(String wmsID) {
        this.wmsID = wmsID;
    }

    public String getCouponsNo() {
        return couponsNo;
    }

    public void setCouponsNo(String couponsNo) {
        this.couponsNo = couponsNo;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getSalespersonNo() {
        return salespersonNo;
    }

    public void setSalespersonNo(String salespersonNo) {
        this.salespersonNo = salespersonNo;
    }

    public int getTotalGivePoints() {
        return totalGivePoints;
    }

    public void setTotalGivePoints(int totalGivePoints) {
        this.totalGivePoints = totalGivePoints;
    }

    public String getNeedInvoince() {
        return needInvoince;
    }

    public void setNeedInvoince(String needInvoince) {
        this.needInvoince = needInvoince;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOrderAttribute() {
        return orderAttribute;
    }

    public void setOrderAttribute(String orderAttribute) {
        this.orderAttribute = orderAttribute;
    }

    public List<OrderSubClientDTO> getOrderSubDTOS() {
        return orderSubDTOS;
    }

    public void setOrderSubDTOS(List<OrderSubClientDTO> orderSubDTOS) {
        this.orderSubDTOS = orderSubDTOS;
    }

    public List<OrderPayClientDTO> getOrderPayDTOS() {
        return orderPayDTOS;
    }

    public void setOrderPayDTOS(List<OrderPayClientDTO> orderPayDTOS) {
        this.orderPayDTOS = orderPayDTOS;
    }

    public List<OrderPromotionClientDTO> getOrderPromotionDTOS() {
        return orderPromotionDTOS;
    }

    public void setOrderPromotionDTOS(List<OrderPromotionClientDTO> orderPromotionDTOS) {
        this.orderPromotionDTOS = orderPromotionDTOS;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "orderNo='" + orderNo + '\'' +
                ", aliasOrderNo='" + aliasOrderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", totalProductCount=" + totalProductCount +
                ", totalProductPrice=" + totalProductPrice +
                ", discountTotal=" + discountTotal +
                ", totalPromo=" + totalPromo +
                ", totalOrderAmount=" + totalOrderAmount +
                ", discountTransport=" + discountTransport +
                ", pointDiscountAmount=" + pointDiscountAmount +
                ", totalPayAmount=" + totalPayAmount +
                ", prepaidAmount=" + prepaidAmount +
                ", wmsID='" + wmsID + '\'' +
                ", couponsNo='" + couponsNo + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", shopNo='" + shopNo + '\'' +
                ", salespersonNo='" + salespersonNo + '\'' +
                ", totalGivePoints=" + totalGivePoints +
                ", needInvoince='" + needInvoince + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", ip='" + ip + '\'' +
                ", orderAttribute='" + orderAttribute + '\'' +
                ", orderSubDTOS=" + orderSubDTOS +
                ", orderPayDTOS=" + orderPayDTOS +
                ", orderPromotionDTOS=" + orderPromotionDTOS +
                '}';
    }
}
