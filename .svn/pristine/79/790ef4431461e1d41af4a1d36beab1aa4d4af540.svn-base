package com.ibm.oms.intf.intf.inner;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author pjsong
 */
public class OrderItemSnapShotDTO {

    @Length(max = 32, message = "invoice_type: length must be less than 32")
    String categoryCode;

    @Length(max = 128, message = "invoice_head: length must be less than 128")
    String categoryName;

    @Length(max = 32, message = "invoice_content: length must be less than 128")
    String brandCode;

    @Length(max = 128, message = "invoice_addition_info: length must be less than 255")
    String brandName;

    @Length(max = 32, message = "isLowGross: length must be less than 32")
    String lowGross;

    @NotBlank(message = "shipCat is compulsory, logistics merchant selection needed,物流商选择需要")
    @Length(max = 32, message = "shipCat: length must be less than 32")
    String shipCat;
    private java.lang.String supplierCode;
    private java.lang.String barCode;
    private java.lang.String skuId;
    /** TianHong Joint BBC **/
    // @NotBlank(message = "自营联营isUnionBiz is compulsory,")
    private java.lang.String isUnionBiz;
    @NotNull(message = "商品积分item point is compulsory,")
    BigDecimal point;
    @NotNull(message = "normal price is compulsory,")
    BigDecimal normalPrice;
    String storeType; // 集货类型（'0','电商仓库','1','总仓','2','集货','3','商家自发货'）

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLowGross() {
        return lowGross;
    }

    public void setLowGross(String lowGross) {
        this.lowGross = lowGross;
    }

    public String getShipCat() {
        return shipCat;
    }

    public void setShipCat(String shipCat) {
        this.shipCat = shipCat;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public java.lang.String getBarCode() {
        return barCode;
    }

    public void setBarCode(java.lang.String barCode) {
        this.barCode = barCode;
    }

    public java.lang.String getSkuId() {
        return skuId;
    }

    public void setSkuId(java.lang.String skuId) {
        this.skuId = skuId;
    }

    public java.lang.String getIsUnionBiz() {
        return isUnionBiz;
    }

    public void setIsUnionBiz(java.lang.String isUnionBiz) {
        this.isUnionBiz = isUnionBiz;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

}
