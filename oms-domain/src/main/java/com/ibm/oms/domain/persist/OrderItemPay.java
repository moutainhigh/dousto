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
 * This class is used to represent available ORDER_ITEM_PAY in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_ITEM_PAY")
public class OrderItemPay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_item_pay", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_item_pay_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item_pay")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER_ITEM")
    private java.lang.Long idOrderItem;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "PAY_TYPE")
    private java.lang.String payType;
    @Column(name = "PAY_CODE")
    private java.lang.String payCode;
    @Column(name = "PAY_NAME")
    private java.lang.String payName;
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;
    @Column(name = "BANK_TYPE_CODE")
    private java.lang.String bankTypeCode;
    @Column(name = "BANK_TYPE_NAME")
    private java.lang.String bankTypeName;
    @Column(name = "CARD_NO")
    private java.lang.String cardNo;
    @Column(name = "PAY_TIME")
    private java.util.Date payTime;
    @Column(name = "PAY_AMOUNT_TOTAL")
    private BigDecimal payAmountTotal;
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;
    @Column(name = "POLICY_FLAG")
    private java.lang.String policyFlag;
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
    @Column(name = "IS_PRE_PAY")
    private java.lang.String isPrePay;

    /**
     * default constructor
     */
    public OrderItemPay() {
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
     * The value of the payType association.
     * 
     * @return java.lang.String Return the value of the payType column.
     */
    public java.lang.String getPayType() {
        return payType;
    }

    /**
     * Set the value of the payType.
     * 
     * @param payType
     */
    public void setPayType(java.lang.String newPayType) {
        this.payType = newPayType;
    }

    /**
     * The value of the payCode association.
     * 
     * @return java.lang.String Return the value of the payCode column.
     */
    public java.lang.String getPayCode() {
        return payCode;
    }

    /**
     * Set the value of the payCode.
     * 
     * @param payCode
     */
    public void setPayCode(java.lang.String newPayCode) {
        this.payCode = newPayCode;
    }

    /**
     * The value of the payName association.
     * 
     * @return java.lang.String Return the value of the payName column.
     */
    public java.lang.String getPayName() {
        return payName;
    }

    /**
     * Set the value of the payName.
     * 
     * @param payName
     */
    public void setPayName(java.lang.String newPayName) {
        this.payName = newPayName;
    }

    /**
     * The value of the bankTypeCode association.
     * 
     * @return java.lang.String Return the value of the bankTypeCode column.
     */
    public java.lang.String getBankTypeCode() {
        return bankTypeCode;
    }

    /**
     * Set the value of the bankTypeCode.
     * 
     * @param bankTypeCode
     */
    public void setBankTypeCode(java.lang.String newBankTypeCode) {
        this.bankTypeCode = newBankTypeCode;
    }

    /**
     * The value of the bankTypeName association.
     * 
     * @return java.lang.String Return the value of the bankTypeName column.
     */
    public java.lang.String getBankTypeName() {
        return bankTypeName;
    }

    /**
     * Set the value of the bankTypeName.
     * 
     * @param bankTypeName
     */
    public void setBankTypeName(java.lang.String newBankTypeName) {
        this.bankTypeName = newBankTypeName;
    }

    /**
     * The value of the cardNo association.
     * 
     * @return java.lang.String Return the value of the cardNo column.
     */
    public java.lang.String getCardNo() {
        return cardNo;
    }

    /**
     * Set the value of the cardNo.
     * 
     * @param cardNo
     */
    public void setCardNo(java.lang.String newCardNo) {
        this.cardNo = newCardNo;
    }

    /**
     * The value of the payTime association.
     * 
     * @return java.util.Date Return the value of the payTime column.
     */
    public java.util.Date getPayTime() {
        return payTime;
    }

    /**
     * Set the value of the payTime.
     * 
     * @param payTime
     */
    public void setPayTime(java.util.Date newPayTime) {
        this.payTime = newPayTime;
    }

    /**
     * The value of the billType association.
     * 
     * @return java.lang.Long Return the value of the billType column.
     */
    public java.lang.Long getBillType() {
        return billType;
    }

    /**
     * Set the value of the billType.
     * 
     * @param billType
     */
    public void setBillType(java.lang.Long newBillType) {
        this.billType = newBillType;
    }

    /**
     * The value of the policyFlag association.
     * 
     * @return java.lang.String Return the value of the policyFlag column.
     */
    public java.lang.String getPolicyFlag() {
        return policyFlag;
    }

    /**
     * Set the value of the policyFlag.
     * 
     * @param policyFlag
     */
    public void setPolicyFlag(java.lang.String newPolicyFlag) {
        this.policyFlag = newPolicyFlag;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmountTotal() {
        return payAmountTotal;
    }

    public void setPayAmountTotal(BigDecimal payAmountTotal) {
        this.payAmountTotal = payAmountTotal;
    }

    public java.lang.String getIsPrePay() {
        return isPrePay;
    }

    public void setIsPrePay(java.lang.String isPrePay) {
        this.isPrePay = isPrePay;
    }

    public java.lang.String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }

}
