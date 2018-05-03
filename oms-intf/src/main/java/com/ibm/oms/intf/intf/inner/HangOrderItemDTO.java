package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong 订单行项目(挂单)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HangOrderItemDTO implements Serializable {

	/** 订单编号 **/
    @NotBlank(message = "orderNo is compulsory")
    String orderNo;
    
    /** 行项目编号 **/
    @NotBlank(message = "orderItemNo is compulsory")
    String orderItemNo;
    
    /** 子订单号 **/
    String orderSubNo;
    
    /** 订单行项目类型 **/
    @NotBlank(message = "orderItemType is compulsory")
    String orderItemType;
	
    /** 外部订单行号 **/
    String aliasOrderItemNo;
    String aliasOrderNo;
    String aliasOrderSubNo;
    
    /** 积分**/
    String point;
    
    /** 商品编码  **/
    @NotBlank(message = "commodityCode is compulsory")
    String commodityCode;
    
    /** SKUNO(物料编号) **/
    @NotBlank(message = "skuNo is compulsory")
    String skuNo;
    
    /** 条形码 **/
    @NotBlank(message = "barCode is compulsory")
    String barCode;
    
    /** 商品名称 **/
    @NotBlank(message = "commodityName is compulsory")
    String commodityName;
    
    /** 商品分类 **/
    @NotBlank(message = "productCategory is compulsory")
    String productCategory;
    
    /** 供应商编码 **/
    @NotBlank(message = "supplierCode is compulsory")
    String supplierCode;
    
    /** 销售数量 **/
    @NotBlank(message = "saleNum is compulsory")
    String saleNum;
    
    /** 销售单位 **/
    /*@NotBlank(message = "saleUnit is compulsory")
    String saleUnit;*/

    /** 销售单价:折前价 **/
    @NotBlank(message = "unitPrice is compulsory")
    @DecimalMin(value = "0.0", message = "unitPrice should bigger than 0.0")
    String unitPrice;
    
    /** 折后单价 **/
    @NotBlank(message = "unitDeductedPrice is compulsory")
    @DecimalMin(value = "0.0")
    String unitDeductedPrice;
    
    /** 单件购物券金额 **/
    @NotBlank(message = "couponAmount is compulsory")
    @DecimalMin(value = "0.0")
    String couponAmount;
    
    /** 销售单件折扣 **/
    @NotBlank(message = "unitDiscount is compulsory")
    @DecimalMin(value = "0.0")
    String unitDiscount;
    
    /** 折后总价（行项目应付金额） **/
    @NotBlank(message = "payAmount is compulsory")
    @DecimalMin(value = "0.0")
    String payAmount;
    
    /** 库区ID **/
    @NotBlank(message = "stockNo is compulsory")
    @Length(max = 32, message = "stockNo: length must be less than 32")
    String stockNo;
    
    /** 仓库库位编码 **/
    @NotBlank(message = "warehouseNo is compulsory")
    @Length(max = 32, message = "warehouseNo: length must be less than 32")
    String warehouseNo;
    
    /** 外部商品编码 **/
    @NotBlank(message = "allasProductNo is compulsory")
    @Length(max = 32, message = "allasProductNo: length must be less than 32")
    String allasProductNo;
    
    /** 订单行是否有赠品 **/
    @NotBlank(message = "hasGift is compulsory")
    Integer hasGift;
    
    /** 促销类型,库存用，商品类型, 1:普通商品，2：活动商品，3：积分商品 **/
    @NotBlank(message = "promotionType is compulsory")
    @Length(max = 60, message = "promotionType: length must be less than 32")
    String promotionType;
    
    /** 促销编码 **/
    @Length(max = 32, message = "promotionCode: length must be less than 32")
    String promotionCode;
    
    /** 商品赠送积分 **/
    String productPoint;
    
    /** 信息来源 **/
    @Length(max = 32, message = "infoSource: length must be less than 32")
    String infoSource;

    
    /** 广告页 **/
    @Length(max = 32, message = "adsPage: length must be less than 32")
    String adsPage;

    /** 订单行备注 **/
    @Length(max = 255, message = "orderItemRemark: length must be less than 255")
    String orderItemRemark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public String getOrderItemType() {
		return orderItemType;
	}

	public void setOrderItemType(String orderItemType) {
		this.orderItemType = orderItemType;
	}

	public String getAliasOrderItemNo() {
		return aliasOrderItemNo;
	}

	public void setAliasOrderItemNo(String aliasOrderItemNo) {
		this.aliasOrderItemNo = aliasOrderItemNo;
	}

	public String getAliasOrderNo() {
		return aliasOrderNo;
	}

	public void setAliasOrderNo(String aliasOrderNo) {
		this.aliasOrderNo = aliasOrderNo;
	}

	public String getAliasOrderSubNo() {
		return aliasOrderSubNo;
	}

	public void setAliasOrderSubNo(String aliasOrderSubNo) {
		this.aliasOrderSubNo = aliasOrderSubNo;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitDeductedPrice() {
		return unitDeductedPrice;
	}

	public void setUnitDeductedPrice(String unitDeductedPrice) {
		this.unitDeductedPrice = unitDeductedPrice;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getUnitDiscount() {
		return unitDiscount;
	}

	public void setUnitDiscount(String unitDiscount) {
		this.unitDiscount = unitDiscount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getAllasProductNo() {
		return allasProductNo;
	}

	public void setAllasProductNo(String allasProductNo) {
		this.allasProductNo = allasProductNo;
	}

	public Integer getHasGift() {
		return hasGift;
	}

	public void setHasGift(Integer hasGift) {
		this.hasGift = hasGift;
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

	public String getProductPoint() {
		return productPoint;
	}

	public void setProductPoint(String productPoint) {
		this.productPoint = productPoint;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public String getAdsPage() {
		return adsPage;
	}

	public void setAdsPage(String adsPage) {
		this.adsPage = adsPage;
	}

	public String getOrderItemRemark() {
		return orderItemRemark;
	}

	public void setOrderItemRemark(String orderItemRemark) {
		this.orderItemRemark = orderItemRemark;
	}
  
   
}
