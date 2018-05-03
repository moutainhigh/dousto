
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
 * This class is used to represent available ORDER_CODE_DICT in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_CODE_DICT")
public class OrderCodeDict implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @TableGenerator(name = "order_code_dict", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_code_dict_id",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_code_dict")
    @Column(unique=true, nullable=false, precision=22)
    private java.lang.Long id;
    @Column(name = "CATE_NO")
    private java.lang.String cateNo;
    @Column(name = "LOV_NAME")
    private java.lang.String lovName;
    @Column(name = "LOV_NAME_ENG")
    private java.lang.String lovNameEng;
    @Column(name = "CODE_NAME")
    private java.lang.String codeName;
    @Column(name = "CODE_NAME_ENG")
    private java.lang.String codeNameEng;
    @Column(name = "SEQ_NUM")
    private java.lang.Long seqNum;
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





	/** 
	 * default constructor 
	 */
    public OrderCodeDict() {
    }
    
													
    /** 
	 * The value of the id association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the id column.
     */
    public java.lang.Long getId() {
    	return id;
    }
    /**
     * Set the value of the id.
     * @param id
     */
    public void setId(java.lang.Long newId){
    	this.id=newId;
    }

    /** 
	 * The value of the cateNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the cateNo column.
     */
    public java.lang.String getCateNo() {
    	return cateNo;
    }
    /**
     * Set the value of the cateNo.
     * @param cateNo
     */
    public void setCateNo(java.lang.String newCateNo){
    	this.cateNo=newCateNo;
    }

    /** 
	 * The value of the lovName association. 
	 * 
     * @return  java.lang.String
     * Return the value of the lovName column.
     */
    public java.lang.String getLovName() {
    	return lovName;
    }
    /**
     * Set the value of the lovName.
     * @param lovName
     */
    public void setLovName(java.lang.String newLovName){
    	this.lovName=newLovName;
    }

    /** 
	 * The value of the lovNameEng association. 
	 * 
     * @return  java.lang.String
     * Return the value of the lovNameEng column.
     */
    public java.lang.String getLovNameEng() {
    	return lovNameEng;
    }
    /**
     * Set the value of the lovNameEng.
     * @param lovNameEng
     */
    public void setLovNameEng(java.lang.String newLovNameEng){
    	this.lovNameEng=newLovNameEng;
    }

    /** 
	 * The value of the codeName association. 
	 * 
     * @return  java.lang.String
     * Return the value of the codeName column.
     */
    public java.lang.String getCodeName() {
    	return codeName;
    }
    /**
     * Set the value of the codeName.
     * @param codeName
     */
    public void setCodeName(java.lang.String newCodeName){
    	this.codeName=newCodeName;
    }

    /** 
	 * The value of the codeNameEng association. 
	 * 
     * @return  java.lang.String
     * Return the value of the codeNameEng column.
     */
    public java.lang.String getCodeNameEng() {
    	return codeNameEng;
    }
    /**
     * Set the value of the codeNameEng.
     * @param codeNameEng
     */
    public void setCodeNameEng(java.lang.String newCodeNameEng){
    	this.codeNameEng=newCodeNameEng;
    }

    /** 
	 * The value of the seqNum association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the seqNum column.
     */
    public java.lang.Long getSeqNum() {
    	return seqNum;
    }
    /**
     * Set the value of the seqNum.
     * @param seqNum
     */
    public void setSeqNum(java.lang.Long newSeqNum){
    	this.seqNum=newSeqNum;
    }

    /** 
	 * The value of the remark association. 
	 * 
     * @return  java.lang.String
     * Return the value of the remark column.
     */
    public java.lang.String getRemark() {
    	return remark;
    }
    /**
     * Set the value of the remark.
     * @param remark
     */
    public void setRemark(java.lang.String newRemark){
    	this.remark=newRemark;
    }

    /** 
	 * The value of the isDeleted association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the isDeleted column.
     */
    public java.lang.Long getIsDeleted() {
    	return isDeleted;
    }
    /**
     * Set the value of the isDeleted.
     * @param isDeleted
     */
    public void setIsDeleted(java.lang.Long newIsDeleted){
    	this.isDeleted=newIsDeleted;
    }

    /** 
	 * The value of the createdBy association. 
	 * 
     * @return  java.lang.String
     * Return the value of the createdBy column.
     */
    public java.lang.String getCreatedBy() {
    	return createdBy;
    }
    /**
     * Set the value of the createdBy.
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String newCreatedBy){
    	this.createdBy=newCreatedBy;
    }

    /** 
	 * The value of the updatedBy association. 
	 * 
     * @return  java.lang.String
     * Return the value of the updatedBy column.
     */
    public java.lang.String getUpdatedBy() {
    	return updatedBy;
    }
    /**
     * Set the value of the updatedBy.
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String newUpdatedBy){
    	this.updatedBy=newUpdatedBy;
    }

    /** 
	 * The value of the dateCreated association. 
	 * 
     * @return  java.util.Date
     * Return the value of the dateCreated column.
     */
    public java.util.Date getDateCreated() {
    	return dateCreated;
    }
    /**
     * Set the value of the dateCreated.
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date newDateCreated){
    	this.dateCreated=newDateCreated;
    }

    /** 
	 * The value of the dateUpdated association. 
	 * 
     * @return  java.util.Date
     * Return the value of the dateUpdated column.
     */
    public java.util.Date getDateUpdated() {
    	return dateUpdated;
    }
    /**
     * Set the value of the dateUpdated.
     * @param dateUpdated
     */
    public void setDateUpdated(java.util.Date newDateUpdated){
    	this.dateUpdated=newDateUpdated;
    }


}
