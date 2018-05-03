package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * This class is used to represent available ORDER_COMBINE_RELATION in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_COMBINE_RELATION")
public class OrderCombineRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_combine_relation", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_combine_relation_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_combine_relation")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER_ITEM")
    private java.lang.Long idOrderItem;
    @Column(name = "COMBINE_NO")
    private java.lang.String combineNo;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "COMBINE_NAME")
    private java.lang.String combineName;
    @Column(name = "COMBINE_RULE")
    private java.lang.String combineRule;
    @Column(name = "REMARK")
    private java.lang.String remark;
    @Column(name = "IS_DELETED")
    private java.lang.Long isDeleted;
    @Column(name = "CREATED_BY")
    private java.lang.String createdBy;
    @Column(name = "UPDATED_BY")
    private java.lang.String updatedBy;
    @Column(name = "DATE_CREATED")
    private java.util.Date dateCreated;
    @Column(name = "DATE_UPDATED")
    private java.util.Date dateUpdated;

    @Column(name = "COMMODITY_CODE")
    private java.lang.String commodityCode;
    @Column(name = "SALE_NUM")
    private java.lang.Long saleNum;
    @Column(name = "SALE_UNIT")
    private java.lang.String saleUnit;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "PRODUCT_GROUP")
    private java.lang.String productGroup;
    @Column(name = "COMMODITY_NAME")
    private java.lang.String commodityName;
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Column(name = "BAR_CODE")
    private java.lang.String barCode;
    @Column(name = "SKU_NO")
    private java.lang.String skuNo;
    @Column(name = "PRODUCT_PROPERTY_FLAG")
    private java.lang.Long productPropertyFlag;
    @Column(name = "SUPPLIER_CODE")
    private java.lang.String supplierCode;
    @Column(name = "MERCHANT_NO")
    private java.lang.String merchantNo;
    @Column(name = "STOCK_NO")
    private java.lang.String stockNo;
    @Column(name = "WAREHOUSE_NO")
    private java.lang.String warehouseNo;
    @Column(name = "INVENTORY_PRICE")
    private BigDecimal inventoryPrice;
    @Column(name = "BRAND")
    private java.lang.String brand;
    @Column(name = "BRAND_NAME")
    private java.lang.String brandName;
    @Column(name = "PRODUCT_CATEGORY")
    private java.lang.String productCategory;
    @Column(name = "PRODUCT_CATEGORY_NAME")
    private java.lang.String productCategoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER_ITEM", insertable = false, updatable = false)
    private OrderItem orderItem;

    /**
     * default constructor
     */
    public OrderCombineRelation() {
    }

    /**
     * The value of the id association.
     * 
     * @return java.lang.Long Return the value of the id column.
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * Set the value of the id.
     * 
     * @param id
     */
    public void setId(java.lang.Long newId) {
        this.id = newId;
    }

    /**
     * The value of the combineNo association.
     * 
     * @return java.lang.String Return the value of the combineNo column.
     */
    public java.lang.String getCombineNo() {
        return combineNo;
    }

    /**
     * Set the value of the combineNo.
     * 
     * @param combineNo
     */
    public void setCombineNo(java.lang.String newCombineNo) {
        this.combineNo = newCombineNo;
    }

    /**
     * The value of the orderItemNo association.
     * 
     * @return java.lang.String Return the value of the orderItemNo column.
     */
    public java.lang.String getOrderItemNo() {
        return orderItemNo;
    }

    /**
     * Set the value of the orderItemNo.
     * 
     * @param orderItemNo
     */
    public void setOrderItemNo(java.lang.String newOrderItemNo) {
        this.orderItemNo = newOrderItemNo;
    }


    /**
     * The value of the combineName association.
     * 
     * @return java.lang.String Return the value of the combineName column.
     */
    public java.lang.String getCombineName() {
        return combineName;
    }

    /**
     * Set the value of the combineName.
     * 
     * @param combineName
     */
    public void setCombineName(java.lang.String newCombineName) {
        this.combineName = newCombineName;
    }

    /**
     * The value of the combineRule association.
     * 
     * @return java.lang.String Return the value of the combineRule column.
     */
    public java.lang.String getCombineRule() {
        return combineRule;
    }

    /**
     * Set the value of the combineRule.
     * 
     * @param combineRule
     */
    public void setCombineRule(java.lang.String newCombineRule) {
        this.combineRule = newCombineRule;
    }

    /**
     * The value of the remark association.
     * 
     * @return java.lang.String Return the value of the remark column.
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * Set the value of the remark.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String newRemark) {
        this.remark = newRemark;
    }

    /**
     * The value of the isDeleted association.
     * 
     * @return java.lang.Long Return the value of the isDeleted column.
     */
    public java.lang.Long getIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of the isDeleted.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(java.lang.Long newIsDeleted) {
        this.isDeleted = newIsDeleted;
    }

    /**
     * The value of the createdBy association.
     * 
     * @return java.lang.String Return the value of the createdBy column.
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of the createdBy.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    /**
     * The value of the updatedBy association.
     * 
     * @return java.lang.String Return the value of the updatedBy column.
     */
    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the value of the updatedBy.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
    }

    /**
     * The value of the dateCreated association.
     * 
     * @return java.util.Date Return the value of the dateCreated column.
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the value of the dateCreated.
     * 
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date newDateCreated) {
        this.dateCreated = newDateCreated;
    }

    /**
     * The value of the dateUpdated association.
     * 
     * @return java.util.Date Return the value of the dateUpdated column.
     */
    public java.util.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Set the value of the dateUpdated.
     * 
     * @param dateUpdated
     */
    public void setDateUpdated(java.util.Date newDateUpdated) {
        this.dateUpdated = newDateUpdated;
    }

    public java.lang.Long getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(java.lang.Long idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public java.lang.String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(java.lang.String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public java.lang.Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(java.lang.Long saleNum) {
        this.saleNum = saleNum;
    }

    public java.lang.String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(java.lang.String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.lang.String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(java.lang.String productGroup) {
        this.productGroup = productGroup;
    }

    public java.lang.String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(java.lang.String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public java.lang.String getBarCode() {
        return barCode;
    }

    public void setBarCode(java.lang.String barCode) {
        this.barCode = barCode;
    }

    public java.lang.String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(java.lang.String skuNo) {
        this.skuNo = skuNo;
    }

    public java.lang.Long getProductPropertyFlag() {
        return productPropertyFlag;
    }

    public void setProductPropertyFlag(java.lang.Long productPropertyFlag) {
        this.productPropertyFlag = productPropertyFlag;
    }

    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public java.lang.String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(java.lang.String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public java.lang.String getStockNo() {
        return stockNo;
    }

    public void setStockNo(java.lang.String stockNo) {
        this.stockNo = stockNo;
    }

    public java.lang.String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(java.lang.String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public BigDecimal getInventoryPrice() {
        return inventoryPrice;
    }

    public void setInventoryPrice(BigDecimal inventoryPrice) {
        this.inventoryPrice = inventoryPrice;
    }

    public java.lang.String getBrand() {
        return brand;
    }

    public void setBrand(java.lang.String brand) {
        this.brand = brand;
    }

    public java.lang.String getBrandName() {
        return brandName;
    }

    public void setBrandName(java.lang.String brandName) {
        this.brandName = brandName;
    }

    public java.lang.String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(java.lang.String productCategory) {
        this.productCategory = productCategory;
    }

    public java.lang.String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(java.lang.String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

}
