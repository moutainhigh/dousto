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
 * This class is used to represent available STATUS_ACTION_DICT in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "STATUS_ACTION_DICT")
public class StatusActionDict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @TableGenerator(name = "status_action_dict", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "status_action_dict_id",allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "status_action_dict")
    @Column(unique=true, nullable=false, precision=22)
    private java.lang.Long id;
    @Column(name = "ACTION_CODE")
    private java.lang.String actionCode;
    @Column(name = "ACTION_NAME")
    private java.lang.String actionName;

    /**
     * default constructor
     */
    public StatusActionDict() {
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
     * The value of the actionCode association.
     * 
     * @return java.lang.String Return the value of the actionCode column.
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }

    /**
     * Set the value of the actionCode.
     * 
     * @param actionCode
     */
    public void setActionCode(java.lang.String newActionCode) {
        this.actionCode = newActionCode;
    }

    /**
     * The value of the actionName association.
     * 
     * @return java.lang.String Return the value of the actionName column.
     */
    public java.lang.String getActionName() {
        return actionName;
    }

    /**
     * Set the value of the actionName.
     * 
     * @param actionName
     */
    public void setActionName(java.lang.String newActionName) {
        this.actionName = newActionName;
    }

}
