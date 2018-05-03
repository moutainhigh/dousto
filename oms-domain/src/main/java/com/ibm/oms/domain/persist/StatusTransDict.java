package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * This class is used to represent available STATUS_TRANS_DICT in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "STATUS_TRANS_DICT")
public class StatusTransDict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @TableGenerator(name = "status_trans_dict", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "status_trans_dict_id",allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "status_trans_dict")
    @Column(name = "ID", unique=true, nullable=false, precision=22)
    private java.lang.Long id;
    @Column(name = "STATUS_NO")
    private java.lang.String statusNo;
    @Column(name = "ACTION_NO")
    private java.lang.String actionNo;
    @Column(name = "TARGET_STATUS_NO")
    private java.lang.String targetStatusNo;

    /**
     * default constructor
     */
    public StatusTransDict() {
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
     * The value of the statusNo association.
     * 
     * @return java.lang.String Return the value of the statusNo column.
     */
    public java.lang.String getStatusNo() {
        return statusNo;
    }

    /**
     * Set the value of the statusNo.
     * 
     * @param statusNo
     */
    public void setStatusNo(java.lang.String newStatusNo) {
        this.statusNo = newStatusNo;
    }

    /**
     * The value of the actionNo association.
     * 
     * @return java.lang.String Return the value of the actionNo column.
     */
    public java.lang.String getActionNo() {
        return actionNo;
    }

    /**
     * Set the value of the actionNo.
     * 
     * @param actionNo
     */
    public void setActionNo(java.lang.String newActionNo) {
        this.actionNo = newActionNo;
    }

    /**
     * The value of the targetStatusNo association.
     * 
     * @return java.lang.String Return the value of the targetStatusNo column.
     */
    public java.lang.String getTargetStatusNo() {
        return targetStatusNo;
    }

    /**
     * Set the value of the targetStatusNo.
     * 
     * @param targetStatusNo
     */
    public void setTargetStatusNo(java.lang.String newTargetStatusNo) {
        this.targetStatusNo = newTargetStatusNo;
    }

}
