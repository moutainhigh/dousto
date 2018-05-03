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
 * This class is used to represent available INTF_VERIFIED in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "INTF_VERIFIED")
public class IntfVerified implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "intf_verified", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "intf_verified_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "intf_verified")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "BTC_ORDER_ITEM_NO")
    private java.lang.String btcOrderItemNo;
    @Column(name = "BTC_ORDER_NO")
    private java.lang.String btcOrderNo;
    @Column(name = "INTF_CODE")
    private java.lang.String intfCode;
    @Column(name = "MSG")
    private java.lang.String msg;
    @Column(name = "PROCESS_FLAG")
    private java.lang.String processFlag;

    @Column(name = "CREATE_TIME")
    private java.util.Date createTime;

    /**
     * default constructor
     */
    public IntfVerified() {
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

    public java.lang.String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(java.lang.String processFlag) {
        this.processFlag = processFlag;
    }

    public java.lang.String getBtcOrderNo() {
        return btcOrderNo;
    }

    public void setBtcOrderNo(java.lang.String btcOrderNo) {
        this.btcOrderNo = btcOrderNo;
    }

}
