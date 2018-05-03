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
 * This class is used to represent available ORDER_PAY_MODE in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_PAY_MODE")
public class OrderPayMode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_pay_mode", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_pay_mode_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_pay_mode")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "PAY_MODE_CODE")
    private java.lang.String payModeCode;
    @Column(name = "PAY_MODE_NAME")
    private java.lang.String payModeName;
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;
    @Column(name = "PAY_STATUS")
    private java.lang.Long payStatus;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;

    /**
     * default constructor
     */
    public OrderPayMode() {
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
     * The value of the payModeCode association.
     * 
     * @return java.lang.String Return the value of the payModeCode column.
     */
    public java.lang.String getPayModeCode() {
        return payModeCode;
    }

    /**
     * Set the value of the payModeCode.
     * 
     * @param payModeCode
     */
    public void setPayModeCode(java.lang.String newPayModeCode) {
        this.payModeCode = newPayModeCode;
    }

    /**
     * The value of the payModeName association.
     * 
     * @return java.lang.String Return the value of the payModeName column.
     */
    public java.lang.String getPayModeName() {
        return payModeName;
    }

    /**
     * Set the value of the payModeName.
     * 
     * @param payModeName
     */
    public void setPayModeName(java.lang.String newPayModeName) {
        this.payModeName = newPayModeName;
    }

    /**
     * The value of the payAmount association.
     * 
     * @return java.lang.Long Return the value of the payAmount column.
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * Set the value of the payAmount.
     * 
     * @param payAmount
     */
    public void setPayAmount(BigDecimal newPayAmount) {
        this.payAmount = newPayAmount;
    }

    /**
     * The value of the payStatus association.
     * 
     * @return java.lang.Long Return the value of the payStatus column.
     */
    public java.lang.Long getPayStatus() {
        return payStatus;
    }

    /**
     * Set the value of the payStatus.
     * 
     * @param payStatus
     */
    public void setPayStatus(java.lang.Long newPayStatus) {
        this.payStatus = newPayStatus;
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

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public java.lang.Long getIdOrder() {
        return idOrder;
    }

}
