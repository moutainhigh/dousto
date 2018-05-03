package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * This class is used to represent available ORDER_RET_ADD in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_RET_ADD")
public class OrderRetAdd implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_ret_add", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_ret_add_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_ret_add")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ID_ORDER_ITEM")
    private java.lang.Long idOrderItem;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "RETURNED_NUM")
    private java.lang.Long returnedNum;
    @Column(name = "REMAIN_NUM")
    private java.lang.Long remainNum;
    @Column(name = "RETURNED_MONEY")
    private BigDecimal returnedMoney;
    @Column(name = "REMAIN_MONEY")
    private BigDecimal remainMoney;
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

    /**
     * default constructor
     */
    public OrderRetAdd() {
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
     * The value of the orderNo association.
     * 
     * @return java.lang.Long Return the value of the orderNo column.
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

    /**
     * The value of the returnedNum association.
     * 
     * @return java.lang.Long Return the value of the returnedNum column.
     */
    public java.lang.Long getReturnedNum() {
        return returnedNum;
    }

    /**
     * Set the value of the returnedNum.
     * 
     * @param returnedNum
     */
    public void setReturnedNum(java.lang.Long newReturnedNum) {
        this.returnedNum = newReturnedNum;
    }

    /**
     * The value of the remainNum association.
     * 
     * @return java.lang.Long Return the value of the remainNum column.
     */
    public java.lang.Long getRemainNum() {
        return remainNum;
    }

    /**
     * Set the value of the remainNum.
     * 
     * @param remainNum
     */
    public void setRemainNum(java.lang.Long newRemainNum) {
        this.remainNum = newRemainNum;
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

    public BigDecimal getReturnedMoney() {
        return returnedMoney;
    }

    public void setReturnedMoney(BigDecimal returnedMoney) {
        this.returnedMoney = returnedMoney;
    }

    public BigDecimal getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }

}
