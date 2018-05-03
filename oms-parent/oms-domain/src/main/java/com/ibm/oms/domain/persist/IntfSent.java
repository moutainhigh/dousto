package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * This class is used to represent available INTF_SENT in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "INTF_SENT")
public class IntfSent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "intf_sent", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "intf_sent_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "intf_sent")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER_ITEM")
    private java.lang.Long idOrderItem;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "BTC_ORDER_ITEM_NO")
    private java.lang.String btcOrderItemNo;
    @Column(name = "BTC_ORDER_NO")
    private java.lang.String btcOrderNo;
    @Column(name = "INTF_CODE")
    private java.lang.String intfCode;
    @Column(name = "MSG")
    private java.lang.String msg;
    @Column(name = "CREATE_TIME")
    private java.util.Date createTime;
    @Column(name = "SUCCEED_FLAG")
    private java.lang.Long succeedFlag;
    @Column(name = "RETRY_COUNT")
    private java.lang.Long retryCount;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;

    /**
     * default constructor
     */
    public IntfSent() {
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

    public java.lang.String getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(java.lang.String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public java.lang.String getBtcOrderItemNo() {
        return btcOrderItemNo;
    }

    public void setBtcOrderItemNo(java.lang.String btcOrderItemNo) {
        this.btcOrderItemNo = btcOrderItemNo;
    }

    /**
     * The value of the intfCode association.
     * 
     * @return java.lang.String Return the value of the intfCode column.
     */
    public java.lang.String getIntfCode() {
        return intfCode;
    }

    /**
     * Set the value of the intfCode.
     * 
     * @param intfCode
     */
    public void setIntfCode(java.lang.String newIntfCode) {
        this.intfCode = newIntfCode;
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
     * The value of the msg association.
     * 
     * @return java.lang.String Return the value of the msg column.
     */
    public java.lang.String getMsg() {
        return msg;
    }

    /**
     * Set the value of the msg.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String newMsg) {
        this.msg = newMsg;
    }

    /**
     * The value of the createTime association.
     * 
     * @return java.util.Date Return the value of the createTime column.
     */
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * Set the value of the createTime.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Date newCreateTime) {
        this.createTime = newCreateTime;
    }

    /**
     * The value of the succeedFlag association.
     * 
     * @return java.lang.Long Return the value of the succeedFlag column.
     */
    public java.lang.Long getSucceedFlag() {
        return succeedFlag;
    }

    /**
     * Set the value of the succeedFlag.
     * 
     * @param succeedFlag
     */
    public void setSucceedFlag(java.lang.Long newSucceedFlag) {
        this.succeedFlag = newSucceedFlag;
    }

    public java.lang.Long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(java.lang.Long retryCount) {
        this.retryCount = retryCount;
    }

    public java.lang.String getBtcOrderNo() {
        return btcOrderNo;
    }

    public void setBtcOrderNo(java.lang.String btcOrderNo) {
        this.btcOrderNo = btcOrderNo;
    }
    
}
