package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author wangqc 主订单(挂单)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HangOrderMainDTO implements Serializable {
    // @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    // @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    // @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    // @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    // @Size(max, min) 被注释的元素的大小必须在指定的范围内
    // @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
    // @Past 被注释的元素必须是一个过去的日期
    // @Future 被注释的元素必须是一个将来的日期
    // @Pattern(value) 被注释的元素必须符合指定的正则表达式
    // @NotNull/@Null 被注释的元素必须是非空或空
    // @Email 被注释的元素必须是电子邮箱地址
    // @Length 被注释的字符串的大小必须在指定的范围内
    // @NotEmpty 被注释的字符串的必须非空
    // @Range 被注释的元素必须在合适的范围内
	
	/** 订单编号 **/
    @NotBlank(message = "orderNo is compulsory")
    @Length(max = 32, message = "orderNo: length must be less than 32")
    String orderNo;

    /** 订单类型 **/
    @NotBlank(message = "order_type is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String orderType;

    /** 订单来源系统 **/
    @NotBlank(message = "order_source is compulsory")
    @Length(max = 32, message = "order_source: length must be less than 32")
    @Pattern(regexp = "(PC)|(APP)|(WAP)|(WGW)|(THXD)|(JD)")
    String orderSource;

    /** 外部订单号 **/
    @Length(max = 32, message = "alias_order_no: length must be less than 32")
    String aliasOrderNo;
    
    /** 订单状态 **/
    @NotBlank(message = "is_suspension is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String isSuspension;
    
    /** 会员ID **/
    @NotBlank(message = "member_no is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String memberNo;
    
    /** 会员姓名 **/
    @Length(max = 32, message = "customer_name: length must be less than 32")
    String customerName;

    /** 会员手机号码 **/
    @Length(max = 16, message = "customer_phone: length must be less than 16")
    String customerPhone;

    String customerEmail;
    
    /**商品数量总计**/
    @NotBlank(message = "totalProductCount is compulsory")
    String totalProductCount;
    
    /** 商品总价,折前 **/
    @NotBlank(message = "total_product_price is compulsory")
    @DecimalMin(value = "0.0", message = "totalProductPrice should bigger than 0.0")
    String totalProductPrice;
    
    /** 总价折扣优惠总金额，订单折扣优惠 **/
    @DecimalMin(value = "0.0", message = "discountTotal should bigger than 0.0")
    String discountTotal;
    
    /** 订单金额总计**/
    @NotBlank(message = "totalOrderAmount is compulsory")
    @DecimalMin(value = "0.0", message = "totalPayAmount should bigger than 0.01")
    String totalOrderAmount;
    
    /** 优惠券总额 **/
    @NotBlank(message = "total_promo is compulsory")
    @DecimalMin(value = "0.0", message = "totalPayAmount should bigger than 0.01")
    String totalPromo;
    
    /** 运费总额 | 物流费用 **/
    @DecimalMin(value = "0.0", message = "transportFee should bigger than 0.0")
    String transportFee;
    
    /** 积分抵现金额 **/
    @NotBlank(message = "pointDiscountAmount is compulsory")
    @DecimalMin(value = "0.0", message = "totalPayAmount should bigger than 0.01")
    String pointDiscountAmount;
    
    /** 订单实际应付金额总计 **/
    @NotBlank(message = "total_pay_amount is compulsory")
    @DecimalMin(value = "0.0", message = "totalPayAmount should bigger than 0.01")
    String totalPayAmount;
    
    /** 订单预付金额**/
    @NotBlank(message = "prepaidAmount is compulsory")
    @DecimalMin(value = "0.0", message = "prepaidAmount should bigger than 0.0")
    String prepaidAmount;
    
    /** 库存ID **/
    @NotBlank(message = "wmsID is compulsory")    
    @Length(max = 32, message = "wmsID: length must be less than 32")
    String wmsID;
    
    /** 优惠券号 **/
    @Length(max = 32, message = "order_type: length must be less than 32")
    String couponsNo;
    
    /** 商家类型 **/
    @Length(max = 32, message = "merchant_type: length must be less than 32")
    String merchantType;
    
    /** 商家编号 **/
    @Length(max = 32, message = "merchant_no: length must be less than 32")
    String merchantNo;
    
    /** 门店编号 **/
    @NotBlank(message = "shopNo is compulsory")  
    @Length(max = 32, message = "shopNo: length must be less than 32")
    String shopNo;
    
    /** 营业员编号 **/
    @NotBlank(message = "salespersonNo is compulsory")  
    @Length(max = 32, message = "salespersonNo: length must be less than 32")
    String salespersonNo;
    
    /** 赠送总积分 **/
    String totalGivePoints;
    
    /** 是否需要发票 **/
    String needInvoice;
    
    /** 订单产生时间 dateTime formatted as yyyy-MM-dd HH:mm:ss **/
    @NotBlank(message = "order_time is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
    String orderTime;
    
    String ip;
    
    /** 订单属性 **/
    @NotBlank(message = "BILL_TYPE is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String billType;
    
    
    


    /** 子订单 **/
    @Valid
    List<HangOrderSubDTO> osDTOs;

    /** 订单级促销 **/
    @Valid
    List<HangOrderPromotionDTO> opDTOs;

    /** 订单支付 **/
    @Valid
    List<HangOrderPayDTO> orderPayDTOs;

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

	public String getAliasOrderNo() {
		return aliasOrderNo;
	}

	public void setAliasOrderNo(String aliasOrderNo) {
		this.aliasOrderNo = aliasOrderNo;
	}

	public String getIsSuspension() {
		return isSuspension;
	}

	public void setIsSuspension(String isSuspension) {
		this.isSuspension = isSuspension;
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

	public String getTotalProductCount() {
		return totalProductCount;
	}

	public void setTotalProductCount(String totalProductCount) {
		this.totalProductCount = totalProductCount;
	}

	public String getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(String totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public String getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(String discountTotal) {
		this.discountTotal = discountTotal;
	}

	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public String getTotalPromo() {
		return totalPromo;
	}

	public void setTotalPromo(String totalPromo) {
		this.totalPromo = totalPromo;
	}

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getPointDiscountAmount() {
		return pointDiscountAmount;
	}

	public void setPointDiscountAmount(String pointDiscountAmount) {
		this.pointDiscountAmount = pointDiscountAmount;
	}

	public String getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(String totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public String getPrepaidAmount() {
		return prepaidAmount;
	}

	public void setPrepaidAmount(String prepaidAmount) {
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

	public String getTotalGivePoints() {
		return totalGivePoints;
	}

	public void setTotalGivePoints(String totalGivePoints) {
		this.totalGivePoints = totalGivePoints;
	}

	public String getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(String needInvoice) {
		this.needInvoice = needInvoice;
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

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public List<HangOrderSubDTO> getOsDTOs() {
		return osDTOs;
	}

	public void setOsDTOs(List<HangOrderSubDTO> osDTOs) {
		this.osDTOs = osDTOs;
	}

	public List<HangOrderPromotionDTO> getOpDTOs() {
		return opDTOs;
	}

	public void setOpDTOs(List<HangOrderPromotionDTO> opDTOs) {
		this.opDTOs = opDTOs;
	}

	public List<HangOrderPayDTO> getOrderPayDTOs() {
		return orderPayDTOs;
	}

	public void setOrderPayDTOs(List<HangOrderPayDTO> orderPayDTOs) {
		this.orderPayDTOs = orderPayDTOs;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

   

}
