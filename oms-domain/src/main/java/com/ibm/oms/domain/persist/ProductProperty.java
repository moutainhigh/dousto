package com.ibm.oms.domain.persist;

import java.io.Serializable;

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
 * This class is used to represent available PRODUCT_PROPERTY in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "PRODUCT_PROPERTY")
public class ProductProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ID_ORDER_ITEM")
    private java.lang.Long idOrderItem;
    @Id
    @TableGenerator(name = "product_property", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "product_property_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "product_property")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "SKU_NO")
    private java.lang.String skuNo;
    @Column(name = "PROPERTY_NAME")
    private java.lang.String propertyName;
    @Column(name = "PROPERTY_VALUE")
    private java.lang.String propertyValue;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;

    /**
     * default constructor
     */
    public ProductProperty() {
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
     * The value of the idOrderItem association.
     * 
     * @return java.lang.Long Return the value of the idOrderItem column.
     */
    public java.lang.Long getIdOrderItem() {
        return idOrderItem;
    }

    /**
     * Set the value of the idOrderItem.
     * 
     * @param idOrderItem
     */
    public void setIdOrderItem(java.lang.Long newIdOrderItem) {
        this.idOrderItem = newIdOrderItem;
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
     * @return java.lang.Long Return the value of the orderItemNo column.
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

    public java.lang.String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(java.lang.String skuNo) {
        this.skuNo = skuNo;
    }

    /**
     * The value of the propertyName association.
     * 
     * @return java.lang.String Return the value of the propertyName column.
     */
    public java.lang.String getPropertyName() {
        return propertyName;
    }

    /**
     * Set the value of the propertyName.
     * 
     * @param propertyName
     */
    public void setPropertyName(java.lang.String newPropertyName) {
        this.propertyName = newPropertyName;
    }

    /**
     * The value of the propertyValue association.
     * 
     * @return java.lang.String Return the value of the propertyValue column.
     */
    public java.lang.String getPropertyValue() {
        return propertyValue;
    }

    /**
     * Set the value of the propertyValue.
     * 
     * @param propertyValue
     */
    public void setPropertyValue(java.lang.String newPropertyValue) {
        this.propertyValue = newPropertyValue;
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

}
