package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * This class is used to represent available STATUS_DICT in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "STATUS_DICT")
public class StatusDict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @TableGenerator(name = "status_dict", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "status_dict_id",allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "status_dict")
    @Column(name = "ID", unique=true, nullable=false, precision=22)
    private java.lang.Long id;
    @Column(name = "STATUS_TYPE_CODE")
    private java.lang.String statusTypeCode;
    @Column(name = "STATUS_TYPE_NAME")
    private java.lang.String statusTypeName;
    @Column(name = "STATUS_CODE")
    private java.lang.String statusCode;
    @Column(name = "STATUS_NAME")
    private java.lang.String statusName;
    @Column(name = "DISPLAY_NAME")
    private java.lang.String displayName;
    //STATUS_PRIORITY
    @Column(name = "STATUS_PRIORITY")
    private java.lang.Integer statusPriority;

    /**
     * default constructor
     */
    public StatusDict() {
    }
    
    public StatusDict(String statusCode, String statusName){
    	this.statusCode = statusCode;
    	this.statusName = statusName;
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
     * The value of the statusTypeCode association.
     * 
     * @return java.lang.String Return the value of the statusTypeCode column.
     */
    public java.lang.String getStatusTypeCode() {
        return statusTypeCode;
    }

    /**
     * Set the value of the statusTypeCode.
     * 
     * @param statusTypeCode
     */
    public void setStatusTypeCode(java.lang.String newStatusTypeCode) {
        this.statusTypeCode = newStatusTypeCode;
    }

    /**
     * The value of the statusTypeName association.
     * 
     * @return java.lang.String Return the value of the statusTypeName column.
     */
    public java.lang.String getStatusTypeName() {
        return statusTypeName;
    }

    /**
     * Set the value of the statusTypeName.
     * 
     * @param statusTypeName
     */
    public void setStatusTypeName(java.lang.String newStatusTypeName) {
        this.statusTypeName = newStatusTypeName;
    }

    /**
     * The value of the statusCode association.
     * 
     * @return java.lang.String Return the value of the statusCode column.
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }

    /**
     * Set the value of the statusCode.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String newStatusCode) {
        this.statusCode = newStatusCode;
    }

    /**
     * The value of the statusName association.
     * 
     * @return java.lang.String Return the value of the statusName column.
     */
    public java.lang.String getStatusName() {
        return statusName;
    }

    /**
     * Set the value of the statusName.
     * 
     * @param statusName
     */
    public void setStatusName(java.lang.String newStatusName) {
        this.statusName = newStatusName;
    }

	public java.lang.String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(java.lang.String displayName) {
		this.displayName = displayName;
	}

	public java.lang.Integer getStatusPriority() {
		return statusPriority;
	}

	public void setStatusPriority(java.lang.Integer statusPriority) {
		this.statusPriority = statusPriority;
	}
	
}
