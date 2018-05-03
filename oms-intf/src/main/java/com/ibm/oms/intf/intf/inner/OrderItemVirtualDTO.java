package com.ibm.oms.intf.intf.inner;


import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author pjsong
 *
 */
public class OrderItemVirtualDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private java.lang.String orderItemClass;
    private java.lang.String productCode;
    private java.lang.String saleNum;
    private java.lang.String unitPrice;
    @NotBlank(message = "OrderItemVirtualDTO.saleAmount is compulsory")
    @DecimalMin(value = "0.0", message = "saleAmount should bigger than 0.0")
    private java.lang.String saleAmount;
    private java.lang.String receiveName;
    @NotBlank(message = "OrderItemVirtualDTO.receiveMobile is compulsory")
    private java.lang.String receiveMobile;
    private java.lang.String receiveStatus;
    private java.lang.String remark;
    private String aliasOrderNo;
    private String aliasOrderSubNo;
    private String aliasOrderItemNo;
    private String skuNo;
    String itemSnapshot;
    @NotBlank(message = "OrderItemVirtualDTO.productName is compulsory")
    private String productName;
    /** 库区ID **/
    @Length(max = 32, message = "stockNo: length must be less than 32")
    String stockNo;
    /** 促销类型,库存用，商品类型, 1:普通商品，2：活动商品，3：积分商品 **/
    @NotBlank(message = "promotionType is compulsory")
    @Length(max = 32, message = "promotionType: length must be less than 32")
    String promotionType;

    /** 促销编码 **/
    @Length(max = 32, message = "promotionCode: length must be less than 32")
    String promotionCode;
//  @NotBlank(message = "productPoint is compulsory, please use '0' if no point to add")
  /** 商品赠送积分 **/
    String productPoint;
    /** 
     * default constructor 
     */
    public OrderItemVirtualDTO() {
    }


    public java.lang.String getOrderItemClass() {
        return orderItemClass;
    }


    public void setOrderItemClass(java.lang.String orderItemClass) {
        this.orderItemClass = orderItemClass;
    }


    public java.lang.String getProductCode() {
        return productCode;
    }


    public void setProductCode(java.lang.String productCode) {
        this.productCode = productCode;
    }

//    public Long getSaleNumAsLong(){
//        return NumberUtils.isNumber(saleNum)?Long.parseLong(saleNum):0l;
//    }
    public java.lang.String getSaleNum() {
        return saleNum;
    }


    public void setSaleNum(java.lang.String saleNum) {
        this.saleNum = saleNum;
    }

//
//    public BigDecimal getUnitPriceAsBigDecimal(){
//        return NumberUtils.isNumber(unitPrice)? new BigDecimal(unitPrice):null;
//    }
    public java.lang.String getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(java.lang.String unitPrice) {
        this.unitPrice = unitPrice;
    }

//    public BigDecimal getSaleAmountAsBigDecimal(){
//        return NumberUtils.isNumber(saleAmount)? new BigDecimal(saleAmount):null;
//    }
    public java.lang.String getSaleAmount() {
        return saleAmount;
    }


    public void setSaleAmount(java.lang.String saleAmount) {
        this.saleAmount = saleAmount;
    }


    public java.lang.String getReceiveName() {
        return receiveName;
    }


    public void setReceiveName(java.lang.String receiveName) {
        this.receiveName = receiveName;
    }


    public java.lang.String getReceiveMobile() {
        return receiveMobile;
    }


    public void setReceiveMobile(java.lang.String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }


//    public Long getReceiveStatusAsLong(){
//        return NumberUtils.isNumber(receiveStatus)?Long.parseLong(receiveStatus):0l;
//    }
    public java.lang.String getReceiveStatus() {
        return receiveStatus;
    }


    public void setReceiveStatus(java.lang.String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }


    public java.lang.String getRemark() {
        return remark;
    }


    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }


    public String getAliasOrderNo() {
        return aliasOrderNo;
    }


    public void setAliasOrderNo(String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }


    public String getAliasOrderItemNo() {
        return aliasOrderItemNo;
    }


    public void setAliasOrderItemNo(String aliasOrderItemNo) {
        this.aliasOrderItemNo = aliasOrderItemNo;
    }


    public String getSkuNo() {
        return skuNo;
    }


    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }


    public String getPromotionType() {
        return promotionType;
    }


    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }


    public String getPromotionCode() {
        return promotionCode;
    }


    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }


    public String getAliasOrderSubNo() {
        return aliasOrderSubNo;
    }


    public void setAliasOrderSubNo(String aliasOrderSubNo) {
        this.aliasOrderSubNo = aliasOrderSubNo;
    }


	public String getStockNo() {
		return stockNo;
	}


	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getItemSnapshot() {
        return itemSnapshot;
    }


    public void setItemSnapshot(String itemSnapshot) {
        this.itemSnapshot = itemSnapshot;
    }


    public String getProductPoint() {
        return productPoint;
    }


    public void setProductPoint(String productPoint) {
        this.productPoint = productPoint;
    }
    
    
}
