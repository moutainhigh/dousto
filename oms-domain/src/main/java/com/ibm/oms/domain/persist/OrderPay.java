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
import javax.persistence.Transient;

/**
 * 
 * This class is used to represent available ORDER_PAY in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_PAY")
public class OrderPay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_pay", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_pay_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_pay")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "PAY_CODE")
    private java.lang.String payCode;
    @Column(name = "PAY_NAME")
    private java.lang.String payName;
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;
    @Column(name = "PAY_TIME")
    private java.util.Date payTime;
    @Column(name = "BANK_TYPE_NAME")
    private java.lang.String bankTypeName;
    @Column(name = "BANK_TYPE_CODE")
    private java.lang.String bankTypeCode;
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
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;
    @Column(name = "OPERATOR_NAME")
    private java.lang.String operatorName;
    @Column(name = "CARD_NO")
    private java.lang.String cardNo;
    @Column(name = "SERIAL_NO")
    private java.lang.String serialNo;
    @Column(name = "PAY_NO")
    private java.lang.String payNo;//支付号
    @Column(name = "PAY_STATUS")
    private java.lang.Long payStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;

    // 支付完成日期 搜索时用到 
    @Transient
    private java.util.Date payTimeFrom;
    @Transient
    private java.util.Date payTimeTo;
    //END YUSL 1/12
    /**
     * default constructor
     */
    public OrderPay() {
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

    public java.lang.Long getBillType() {
        return billType;
    }

    public void setBillType(java.lang.Long billType) {
        this.billType = billType;
    }

    public java.lang.String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(java.lang.String operatorName) {
        this.operatorName = operatorName;
    }
    public java.lang.String getCardNo() {
        return cardNo;
    }

    public void setCardNo(java.lang.String cardNo) {
        this.cardNo = cardNo;
    }

    public java.lang.String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(java.lang.String serialNo) {
        this.serialNo = serialNo;
    }

	public java.lang.String getPayNo() {
		return payNo;
	}

	public void setPayNo(java.lang.String payNo) {
		this.payNo = payNo;
	}
//支付完成日期 搜索时用到
	public java.util.Date getPayTimeFrom() {
		return payTimeFrom;
	}

	public void setPayTimeFrom(java.util.Date payTimeFrom) {
		this.payTimeFrom = payTimeFrom;
	}
//
	public java.util.Date getPayTimeTo() {
		return payTimeTo;
	}

	public void setPayTimeTo(java.util.Date payTimeTo) {
		this.payTimeTo = payTimeTo;
	}

    public Long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }
}
