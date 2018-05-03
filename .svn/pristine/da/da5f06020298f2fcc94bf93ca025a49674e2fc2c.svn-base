
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
 * This class is used to represent available ORDER_OPERATE_LOG in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_OPERATE_LOG")
public class OrderOperateLog implements Serializable{
    private static final long serialVersionUID = 1L;
    

	@Id
	@TableGenerator(name = "order_operator_log", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_operator_log_id",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_operator_log")
	@Column(unique=true, nullable=false, precision=22)
	private java.lang.Long id;
	@Column(name = "ID_ORDER")
	private java.lang.Long idOrder;
	@Column(name = "ID_ORDER_SUB")
	private java.lang.Long idOrderSub;
	@Column(name = "ID_ORDER_ITEM")
	private java.lang.Long idOrderItem;
	@Column(name = "ORDER_NO")
	private java.lang.String orderNo;
	@Column(name = "ORDER_SUB_NO")
	private java.lang.String orderSubNo;
	@Column(name = "ORDER_ITEM_NO")
	private java.lang.String orderItemNo;
	@Column(name = "ALIAS_ORDER_ID")
	private java.lang.Long aliasOrderId;
	@Column(name = "ORDER_SOURCE")
	private java.lang.String orderSource;
	@Column(name = "CONTENT")
	private java.lang.String content;
	@Column(name = "OLD_DATA")
	private java.lang.String oldData;
	@Column(name = "NEW_DATA")
	private java.lang.String newData;
	@Column(name = "REASON")
	private java.lang.String reason;
	@Column(name = "OPERATOR")
	private java.lang.String operator;
	@Column(name = "BILL_TYPE")
	private java.lang.Long billType;
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
	@Column(name = "IP")
	private java.lang.String IP;







	/** 
	 * default constructor 
	 */
    public OrderOperateLog() {
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
	 * The value of the idOrder association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the idOrder column.
     */
    public java.lang.Long getIdOrder() {
    	return idOrder;
    }
    /**
     * Set the value of the idOrder.
     * @param idOrder
     */
    public void setIdOrder(java.lang.Long newIdOrder){
    	this.idOrder=newIdOrder;
    }

    /** 
	 * The value of the idOrderSub association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the idOrderSub column.
     */
    public java.lang.Long getIdOrderSub() {
    	return idOrderSub;
    }
    /**
     * Set the value of the idOrderSub.
     * @param idOrderSub
     */
    public void setIdOrderSub(java.lang.Long newIdOrderSub){
    	this.idOrderSub=newIdOrderSub;
    }

    /** 
	 * The value of the idOrderItem association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the idOrderItem column.
     */
    public java.lang.Long getIdOrderItem() {
    	return idOrderItem;
    }
    /**
     * Set the value of the idOrderItem.
     * @param idOrderItem
     */
    public void setIdOrderItem(java.lang.Long newIdOrderItem){
    	this.idOrderItem=newIdOrderItem;
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


	public java.lang.String getOrderItemNo() {
		return orderItemNo;
	}


	public void setOrderItemNo(java.lang.String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}


	/** 
	 * The value of the aliasOrderId association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the aliasOrderId column.
     */
    public java.lang.Long getAliasOrderId() {
    	return aliasOrderId;
    }
    /**
     * Set the value of the aliasOrderId.
     * @param aliasOrderId
     */
    public void setAliasOrderId(java.lang.Long newAliasOrderId){
    	this.aliasOrderId=newAliasOrderId;
    }

    /** 
	 * The value of the orderSource association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderSource column.
     */
    public java.lang.String getOrderSource() {
    	return orderSource;
    }
    /**
     * Set the value of the orderSource.
     * @param orderSource
     */
    public void setOrderSource(java.lang.String newOrderSource){
    	this.orderSource=newOrderSource;
    }

    /** 
	 * The value of the content association. 
	 * 
     * @return  java.lang.String
     * Return the value of the content column.
     */
    public java.lang.String getContent() {
    	return content;
    }
    /**
     * Set the value of the content.
     * @param content
     */
    public void setContent(java.lang.String newContent){
    	this.content=newContent;
    }

    /** 
	 * The value of the oldData association. 
	 * 
     * @return  java.lang.String
     * Return the value of the oldData column.
     */
    public java.lang.String getOldData() {
    	return oldData;
    }
    /**
     * Set the value of the oldData.
     * @param oldData
     */
    public void setOldData(java.lang.String newOldData){
    	this.oldData=newOldData;
    }

    /** 
	 * The value of the newData association. 
	 * 
     * @return  java.lang.String
     * Return the value of the newData column.
     */
    public java.lang.String getNewData() {
    	return newData;
    }
    /**
     * Set the value of the newData.
     * @param newData
     */
    public void setNewData(java.lang.String newNewData){
    	this.newData=newNewData;
    }

    /** 
	 * The value of the reason association. 
	 * 
     * @return  java.lang.String
     * Return the value of the reason column.
     */
    public java.lang.String getReason() {
    	return reason;
    }
    /**
     * Set the value of the reason.
     * @param reason
     */
    public void setReason(java.lang.String newReason){
    	this.reason=newReason;
    }

    /** 
	 * The value of the operator association. 
	 * 
     * @return  java.lang.String
     * Return the value of the operator column.
     */
    public java.lang.String getOperator() {
    	return operator;
    }
    /**
     * Set the value of the operator.
     * @param operator
     */
    public void setOperator(java.lang.String newOperator){
    	this.operator=newOperator;
    }

    /** 
	 * The value of the billType association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the billType column.
     */
    public java.lang.Long getBillType() {
    	return billType;
    }
    /**
     * Set the value of the billType.
     * @param billType
     */
    public void setBillType(java.lang.Long newBillType){
    	this.billType=newBillType;
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


	public java.lang.String getIP() {
		return IP;
	}


	public void setIP(java.lang.String iP) {
		IP = iP;
	}


}
