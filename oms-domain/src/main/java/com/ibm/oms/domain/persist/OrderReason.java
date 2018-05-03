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
 * This class is used to represent available ORDER_REASON in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_REASON")
public class OrderReason implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_reason", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_reason_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_reason")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "REASON_NO")
    private java.lang.String reasonNo;
    @Column(name = "REASON_NAME")
    private java.lang.String reasonName;
    @Column(name = "ISEFFECTED")
    private java.lang.Long iseffected;
    @Column(name = "RESPONSIBLE_PARTY")
    private java.lang.Long responsibleParty;
    @Column(name = "PARENT_REASON_NO")
    private java.lang.String parentReasonNo;

    /**
     * default constructor
     */
    public OrderReason() {
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
     * The value of the reasonNo association.
     * 
     * @return java.lang.String Return the value of the reasonNo column.
     */
    public java.lang.String getReasonNo() {
        return reasonNo;
    }

    /**
     * Set the value of the reasonNo.
     * 
     * @param reasonNo
     */
    public void setReasonNo(java.lang.String newReasonNo) {
        this.reasonNo = newReasonNo;
    }

    /**
     * The value of the reasonName association.
     * 
     * @return java.lang.String Return the value of the reasonName column.
     */
    public java.lang.String getReasonName() {
        return reasonName;
    }

    /**
     * Set the value of the reasonName.
     * 
     * @param reasonName
     */
    public void setReasonName(java.lang.String newReasonName) {
        this.reasonName = newReasonName;
    }

    /**
     * The value of the iseffected association.
     * 
     * @return java.lang.Long Return the value of the iseffected column.
     */
    public java.lang.Long getIseffected() {
        return iseffected;
    }

    /**
     * Set the value of the iseffected.
     * 
     * @param iseffected
     */
    public void setIseffected(java.lang.Long newIseffected) {
        this.iseffected = newIseffected;
    }

    /**
     * The value of the responsiblePartt association.
     * 
     * @return java.lang.Long Return the value of the responsiblePartt column.
     */
    public java.lang.Long getResponsibleParty() {
        return responsibleParty;
    }

    /**
     * Set the value of the responsiblePartt.
     * 
     * @param responsiblePartt
     */
    public void setResponsiblePartt(java.lang.Long newResponsibleParty) {
        this.responsibleParty = newResponsibleParty;
    }

    public java.lang.String getParentReasonNo() {
        return parentReasonNo;
    }

    public void setParentReasonNo(java.lang.String parentReasonNo) {
        this.parentReasonNo = parentReasonNo;
    }



}
