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
 * This class is used to represent available ORDER_DIC in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_DIC")
public class OrderDic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_dic", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_dic_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_dic")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "DIC_CODE")
    private java.lang.String dicCode;
    @Column(name = "DIC_NAME")
    private java.lang.String dicName;
    @Column(name = "DIC_DESC")
    private java.lang.String dicDesc;
    @Column(name = "DIC_TYPE_NAME")
    private java.lang.String dicTypeName;
    @Column(name = "DIC_TYPE")
    private java.lang.String dicType;

    /**
     * default constructor
     */
    public OrderDic() {
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
     * The value of the dicCode association.
     * 
     * @return java.lang.String Return the value of the dicCode column.
     */
    public java.lang.String getDicCode() {
        return dicCode;
    }

    /**
     * Set the value of the dicCode.
     * 
     * @param dicCode
     */
    public void setDicCode(java.lang.String newDicCode) {
        this.dicCode = newDicCode;
    }

    /**
     * The value of the dicName association.
     * 
     * @return java.lang.String Return the value of the dicName column.
     */
    public java.lang.String getDicName() {
        return dicName;
    }

    /**
     * Set the value of the dicName.
     * 
     * @param dicName
     */
    public void setDicName(java.lang.String newDicName) {
        this.dicName = newDicName;
    }

    /**
     * The value of the dicDesc association.
     * 
     * @return java.lang.String Return the value of the dicDesc column.
     */
    public java.lang.String getDicDesc() {
        return dicDesc;
    }

    /**
     * Set the value of the dicDesc.
     * 
     * @param dicDesc
     */
    public void setDicDesc(java.lang.String newDicDesc) {
        this.dicDesc = newDicDesc;
    }

    /**
     * The value of the dicTypeName association.
     * 
     * @return java.lang.String Return the value of the dicTypeName column.
     */
    public java.lang.String getDicTypeName() {
        return dicTypeName;
    }

    /**
     * Set the value of the dicTypeName.
     * 
     * @param dicTypeName
     */
    public void setDicTypeName(java.lang.String newDicTypeName) {
        this.dicTypeName = newDicTypeName;
    }

    /**
     * The value of the dicType association.
     * 
     * @return java.lang.String Return the value of the dicType column.
     */
    public java.lang.String getDicType() {
        return dicType;
    }

    /**
     * Set the value of the dicType.
     * 
     * @param dicType
     */
    public void setDicType(java.lang.String newDicType) {
        this.dicType = newDicType;
    }

}
