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
 * This class is used to represent available ORDER_ITEM_VIRTUAL in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_ITEM_VIRTUAL")
public class OrderItemVirtual implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_item_virtual", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_item_virtual_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item_virtual")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ID_ORDER_SUB")
    private java.lang.Long idOrderSub;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.Long aliasOrderNo;
    @Column(name = "ALIAS_ORDER_ITEM_NO")
    private java.lang.String aliasOrderItemNo;
    @Column(name = "ORDER_ITEM_CLASS")
    private java.lang.String orderItemClass;
    @Column(name = "PRODUCT_CODE")
    private java.lang.String productCode;
    @Column(name = "PRODUCT_NAME")
    private java.lang.String productName;
    @Column(name = "PRODUCT_POINT")
    private BigDecimal productPoint;
    @Column(name = "SKU_NO")
    private java.lang.String skuNo;
    @Column(name = "STOCK_NO")
    private java.lang.String stockNo;
    @Column(name = "SALE_NUM")
    private java.lang.Long saleNum;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;
    @Column(name = "RECEIVE_NAME")
    private java.lang.String receiveName;
    @Column(name = "RECEIVE_MOBILE")
    private java.lang.String receiveMobile;
    @Column(name = "RECEIVE_STATUS")
    private java.lang.Long receiveStatus;
    @Column(name = "CHECK_CODE")
    private java.lang.String checkCode;
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
    @Column(name = "PROMOTION_TYPE")
    private java.lang.String promotionType;
    @Column(name = "PROMOTION_CODE")
    private java.lang.String promotionCode;
    @Column(name = "SUPPLIER_CODE")
    private java.lang.String supplierCode;
    @Column(name = "BAR_CODE")
    private java.lang.String barCode;
    @Column(name = "SKU_ID")
    private java.lang.String skuId;
    @Column(name = "IS_UNION_BIZ")
    private java.lang.String isUnionBiz;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;

    /**
     * default constructor
     */
    public OrderItemVirtual() {
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
     * The value of the idOrder association.
     * 
     * @return java.lang.Long Return the value of the idOrder column.
     */
    public java.lang.Long getIdOrder() {
        return idOrder;
    }

    /**
     * Set the value of the idOrder.
     * 
     * @param idOrder
     */
    public void setIdOrder(java.lang.Long newIdOrder) {
        this.idOrder = newIdOrder;
    }

    /**
     * The value of the orderNo association.
     * 
     * @return java.lang.String Return the value of the orderNo column.
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value of the orderNo.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String newOrderNo) {
        this.orderNo = newOrderNo;
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
     * The value of the orderItemClass association.
     * 
     * @return java.lang.String Return the value of the orderItemClass column.
     */
    public java.lang.String getOrderItemClass() {
        return orderItemClass;
    }

    /**
     * Set the value of the orderItemClass.
     * 
     * @param orderItemClass
     */
    public void setOrderItemClass(java.lang.String newOrderItemClass) {
        this.orderItemClass = newOrderItemClass;
    }

    /**
     * The value of the productCode association.
     * 
     * @return java.lang.String Return the value of the productCode column.
     */
    public java.lang.String getProductCode() {
        return productCode;
    }

    /**
     * Set the value of the productCode.
     * 
     * @param productCode
     */
    public void setProductCode(java.lang.String newProductCode) {
        this.productCode = newProductCode;
    }

    /**
     * The value of the saleNum association.
     * 
     * @return java.lang.Long Return the value of the saleNum column.
     */
    public java.lang.Long getSaleNum() {
        return saleNum;
    }

    /**
     * Set the value of the saleNum.
     * 
     * @param saleNum
     */
    public void setSaleNum(java.lang.Long newSaleNum) {
        this.saleNum = newSaleNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * The value of the saleAmount association.
     * 
     * @return java.lang.Long Return the value of the saleAmount column.
     */
    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    /**
     * Set the value of the saleAmount.
     * 
     * @param saleAmount
     */
    public void setSaleAmount(BigDecimal newSaleAmount) {
        this.saleAmount = newSaleAmount;
    }

    /**
     * The value of the receiveName association.
     * 
     * @return java.lang.String Return the value of the receiveName column.
     */
    public java.lang.String getReceiveName() {
        return receiveName;
    }

    /**
     * Set the value of the receiveName.
     * 
     * @param receiveName
     */
    public void setReceiveName(java.lang.String newReceiveName) {
        this.receiveName = newReceiveName;
    }

    /**
     * The value of the receiveMobile association.
     * 
     * @return java.lang.String Return the value of the receiveMobile column.
     */
    public java.lang.String getReceiveMobile() {
        return receiveMobile;
    }

    /**
     * Set the value of the receiveMobile.
     * 
     * @param receiveMobile
     */
    public void setReceiveMobile(java.lang.String newReceiveMobile) {
        this.receiveMobile = newReceiveMobile;
    }

    /**
     * The value of the receiveStatus association.
     * 
     * @return java.lang.Long Return the value of the receiveStatus column.
     */
    public java.lang.Long getReceiveStatus() {
        return receiveStatus;
    }

    /**
     * Set the value of the receiveStatus.
     * 
     * @param receiveStatus
     */
    public void setReceiveStatus(java.lang.Long newReceiveStatus) {
        this.receiveStatus = newReceiveStatus;
    }

    /**
     * The value of the checkCode association.
     * 
     * @return java.lang.String Return the value of the checkCode column.
     */
    public java.lang.String getCheckCode() {
        return checkCode;
    }

    /**
     * Set the value of the checkCode.
     * 
     * @param checkCode
     */
    public void setCheckCode(java.lang.String newCheckCode) {
        this.checkCode = newCheckCode;
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

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public java.lang.Long getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(java.lang.Long aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public java.lang.String getAliasOrderItemNo() {
        return aliasOrderItemNo;
    }

    public void setAliasOrderItemNo(java.lang.String aliasOrderItemNo) {
        this.aliasOrderItemNo = aliasOrderItemNo;
    }

    public java.lang.String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(java.lang.String skuNo) {
        this.skuNo = skuNo;
    }

    public java.lang.Long getIdOrderSub() {
        return idOrderSub;
    }

    public void setIdOrderSub(java.lang.Long idOrderSub) {
        this.idOrderSub = idOrderSub;
    }

    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(java.lang.String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public java.lang.String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(java.lang.String promotionType) {
        this.promotionType = promotionType;
    }

    public java.lang.String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(java.lang.String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public java.lang.String getStockNo() {
        return stockNo;
    }

    public void setStockNo(java.lang.String stockNo) {
        this.stockNo = stockNo;
    }

    public java.lang.String getProductName() {
        return productName;
    }

    public void setProductName(java.lang.String productName) {
        this.productName = productName;
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

    public BigDecimal getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(BigDecimal productPoint) {
        this.productPoint = productPoint;
    }

    public java.lang.String getIsUnionBiz() {
        return isUnionBiz;
    }

    public void setIsUnionBiz(java.lang.String isUnionBiz) {
        this.isUnionBiz = isUnionBiz;
    }

    
}
