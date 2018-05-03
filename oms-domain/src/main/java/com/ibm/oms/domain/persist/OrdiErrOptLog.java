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
 * This class is used to represent available ORDI_ERR_OPT_LOG in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDI_ERR_OPT_LOG")
public class OrdiErrOptLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "ordi_err_opt_log", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "ordi_err_opt_log_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ordi_err_opt_log")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    @Column(name = "ORDER_SOURCE")
    private java.lang.String orderSource;
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.String aliasOrderNo;
    @Column(name = "ALIAS_ORDER_ITEM_No")
    private java.lang.String aliasOrderItemNo;
    @Column(name = "ORDER_ITEM_CLASS")
    private java.lang.String orderItemClass;
    @Column(name = "ORDER_STATUS")
    private java.lang.String orderStatus;
    @Column(name = "ERROR_TYPE")
    private java.lang.String errorType;
    @Column(name = "ERROR_CODE")
    private java.lang.String errorCode;
    @Column(name = "ERROR_DESC")
    private java.lang.String errorDesc;
    @Column(name = "RESULT_CODE")
    private java.lang.Long resultCode;
    @Column(name = "RESULT_DESC")
    private java.lang.String resultDesc;
    @Column(name = "OPERATOR")
    private java.lang.String operator;
    @Column(name = "OPERATE_TIME")
    private java.util.Date operateTime;
    @Column(name = "OPERATE_IP")
    private java.lang.String operateIp;
    @Column(name = "OPERATE_TYPE")
    private java.lang.String operateType;
    @Column(name = "OPERATE_REMARK")
    private java.lang.String operateRemark;
    @Column(name = "OPERATE_COUNT")
    private BigDecimal operateCount; //操作的次数

    /**
     * default constructor
     */
    public OrdiErrOptLog() {
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

    public java.lang.String getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(java.lang.String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public java.lang.String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }

    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(java.lang.String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    /**
     * The value of the orderSource association.
     * 
     * @return java.lang.String Return the value of the orderSource column.
     */
    public java.lang.String getOrderSource() {
        return orderSource;
    }

    /**
     * Set the value of the orderSource.
     * 
     * @param orderSource
     */
    public void setOrderSource(java.lang.String newOrderSource) {
        this.orderSource = newOrderSource;
    }

    public java.lang.String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(java.lang.String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public java.lang.String getAliasOrderItemNo() {
        return aliasOrderItemNo;
    }

    public void setAliasOrderItemNo(java.lang.String aliasOrderItemNo) {
        this.aliasOrderItemNo = aliasOrderItemNo;
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
     * The value of the orderStatus association.
     * 
     * @return java.lang.String Return the value of the orderStatus column.
     */
    public java.lang.String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set the value of the orderStatus.
     * 
     * @param orderStatus
     */
    public void setOrderStatus(java.lang.String newOrderStatus) {
        this.orderStatus = newOrderStatus;
    }

    /**
     * The value of the errorType association.
     * 
     * @return java.lang.String Return the value of the errorType column.
     */
    public java.lang.String getErrorType() {
        return errorType;
    }

    /**
     * Set the value of the errorType.
     * 
     * @param errorType
     */
    public void setErrorType(java.lang.String newErrorType) {
        this.errorType = newErrorType;
    }

    /**
     * The value of the errorCode association.
     * 
     * @return java.lang.String Return the value of the errorCode column.
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }

    /**
     * Set the value of the errorCode.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String newErrorCode) {
        this.errorCode = newErrorCode;
    }

    /**
     * The value of the errorDesc association.
     * 
     * @return java.lang.String Return the value of the errorDesc column.
     */
    public java.lang.String getErrorDesc() {
        return errorDesc;
    }

    /**
     * Set the value of the errorDesc.
     * 
     * @param errorDesc
     */
    public void setErrorDesc(java.lang.String newErrorDesc) {
        this.errorDesc = newErrorDesc;
    }

    /**
     * The value of the resultCode association.
     * 
     * @return java.lang.Long Return the value of the resultCode column.
     */
    public java.lang.Long getResultCode() {
        return resultCode;
    }

    /**
     * Set the value of the resultCode.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.Long newResultCode) {
        this.resultCode = newResultCode;
    }

    /**
     * The value of the resultDesc association.
     * 
     * @return java.lang.String Return the value of the resultDesc column.
     */
    public java.lang.String getResultDesc() {
        return resultDesc;
    }

    /**
     * Set the value of the resultDesc.
     * 
     * @param resultDesc
     */
    public void setResultDesc(java.lang.String newResultDesc) {
        this.resultDesc = newResultDesc;
    }

    /**
     * The value of the operator association.
     * 
     * @return java.lang.String Return the value of the operator column.
     */
    public java.lang.String getOperator() {
        return operator;
    }

    /**
     * Set the value of the operator.
     * 
     * @param operator
     */
    public void setOperator(java.lang.String newOperator) {
        this.operator = newOperator;
    }

    /**
     * The value of the operateTime association.
     * 
     * @return java.util.Date Return the value of the operateTime column.
     */
    public java.util.Date getOperateTime() {
        return operateTime;
    }

    /**
     * Set the value of the operateTime.
     * 
     * @param operateTime
     */
    public void setOperateTime(java.util.Date newOperateTime) {
        this.operateTime = newOperateTime;
    }

    /**
     * The value of the operateIp association.
     * 
     * @return java.lang.String Return the value of the operateIp column.
     */
    public java.lang.String getOperateIp() {
        return operateIp;
    }

    /**
     * Set the value of the operateIp.
     * 
     * @param operateIp
     */
    public void setOperateIp(java.lang.String newOperateIp) {
        this.operateIp = newOperateIp;
    }

    /**
     * The value of the operateType association.
     * 
     * @return java.lang.String Return the value of the operateType column.
     */
    public java.lang.String getOperateType() {
        return operateType;
    }

    /**
     * Set the value of the operateType.
     * 
     * @param operateType
     */
    public void setOperateType(java.lang.String newOperateType) {
        this.operateType = newOperateType;
    }

    /**
     * The value of the operateRemark association.
     * 
     * @return java.lang.String Return the value of the operateRemark column.
     */
    public java.lang.String getOperateRemark() {
        return operateRemark;
    }

    /**
     * Set the value of the operateRemark.
     * 
     * @param operateRemark
     */
    public void setOperateRemark(java.lang.String newOperateRemark) {
        this.operateRemark = newOperateRemark;
    }

    public BigDecimal getOperateCount() {
        return operateCount;
    }

    public void setOperateCount(BigDecimal operateCount) {
        this.operateCount = operateCount;
    }

}
