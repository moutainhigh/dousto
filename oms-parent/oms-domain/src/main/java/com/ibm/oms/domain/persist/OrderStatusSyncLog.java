
package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * This class is used to represent available ORDER_STATUS_SYNC_LOG in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_STATUS_SYNC_LOG")
public class OrderStatusSyncLog implements Serializable{
    private static final long serialVersionUID = 1L;
    
	@Id
	@TableGenerator(name = "order_status_sync_log", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_status_sync_log_id",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_status_sync_log")
	@Column(unique=true, nullable=false, precision=22)
	private java.lang.Long id;
	@Column(name = "ID_ORDER_SUB")
	private java.lang.Long idOrderSub;
	@Column(name = "ID_ORDER")
	private java.lang.Long idOrder;
	@Column(name = "ORDER_NO")
	private java.lang.String orderNo;
	@Column(name = "ORDER_SUB_NO")
	private java.lang.String orderSubNo;
	@Column(name = "SYNC_SCENE")
	private java.lang.String syncScene;
	@Column(name = "TARGET_SYS")
	private java.lang.String targetSys;
	@Column(name = "SYNC_FLAG")
	private java.lang.String syncFlag;
	@Column(name = "SYNC_NUM")
	private java.lang.Long syncNum;
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



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
	private OrderMain orderMain;




	/** 
	 * default constructor 
	 */
    public OrderStatusSyncLog() {
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
	 * The value of the orderNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderNo column.
     */
    public java.lang.String getOrderNo() {
    	return orderNo;
    }
    /**
     * Set the value of the orderNo.
     * @param orderNo
     */
    public void setOrderNo(java.lang.String newOrderNo){
    	this.orderNo=newOrderNo;
    }

    /** 
	 * The value of the orderSubNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderSubNo column.
     */
    public java.lang.String getOrderSubNo() {
    	return orderSubNo;
    }
    /**
     * Set the value of the orderSubNo.
     * @param orderSubNo
     */
    public void setOrderSubNo(java.lang.String newOrderSubNo){
    	this.orderSubNo=newOrderSubNo;
    }

    /** 
	 * The value of the syncScene association. 
	 * 
     * @return  java.lang.String
     * Return the value of the syncScene column.
     */
    public java.lang.String getSyncScene() {
    	return syncScene;
    }
    /**
     * Set the value of the syncScene.
     * @param syncScene
     */
    public void setSyncScene(java.lang.String newSyncScene){
    	this.syncScene=newSyncScene;
    }

    /** 
	 * The value of the targetSys association. 
	 * 
     * @return  java.lang.String
     * Return the value of the targetSys column.
     */
    public java.lang.String getTargetSys() {
    	return targetSys;
    }
    /**
     * Set the value of the targetSys.
     * @param targetSys
     */
    public void setTargetSys(java.lang.String newTargetSys){
    	this.targetSys=newTargetSys;
    }

    /** 
	 * The value of the syncFlag association. 
	 * 
     * @return  java.lang.String
     * Return the value of the syncFlag column.
     */
    public java.lang.String getSyncFlag() {
    	return syncFlag;
    }
    /**
     * Set the value of the syncFlag.
     * @param syncFlag
     */
    public void setSyncFlag(java.lang.String newSyncFlag){
    	this.syncFlag=newSyncFlag;
    }

    /** 
	 * The value of the syncNum association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the syncNum column.
     */
    public java.lang.Long getSyncNum() {
    	return syncNum;
    }
    /**
     * Set the value of the syncNum.
     * @param syncNum
     */
    public void setSyncNum(java.lang.Long newSyncNum){
    	this.syncNum=newSyncNum;
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


	public OrderMain getOrderMain() {
		return orderMain;
	}


	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}




}
