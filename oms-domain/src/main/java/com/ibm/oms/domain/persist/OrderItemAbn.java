
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
 * This class is used to represent available ORDER_ITEM_ABN in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_ITEM_ABN")
public class OrderItemAbn implements Serializable{
    private static final long serialVersionUID = 1L;
    
	@Id
	@TableGenerator(name = "order_item_abn", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_item_abn_id",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item_abn")
	@Column(unique=true, nullable=false, precision=22)
	private java.lang.Long id;
	@Column(name = "ID_ORDER")
	private java.lang.Long idOrder;
	@Column(name = "ORDER_ID")
	private java.lang.String orderId;
	@Column(name = "BTC_ORDER_ID")
	private java.lang.Long btcOrderId;
	@Column(name = "BTCORDER_ITME_ID")
	private java.lang.Long btcorderItmeId;
	@Column(name = "ORDERI_ABN_CODE")
	private java.lang.String orderiAbnCode;
	@Column(name = "ABN_DESCRIB")
	private java.lang.String abnDescrib;
	@Column(name = "ABN_RESOLVE_TYPE")
	private java.lang.String abnResolveType;
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
    public OrderItemAbn() {
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
	 * The value of the orderId association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderId column.
     */
    public java.lang.String getOrderId() {
    	return orderId;
    }
    /**
     * Set the value of the orderId.
     * @param orderId
     */
    public void setOrderId(java.lang.String newOrderId){
    	this.orderId=newOrderId;
    }

    /** 
	 * The value of the btcOrderId association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the btcOrderId column.
     */
    public java.lang.Long getBtcOrderId() {
    	return btcOrderId;
    }
    /**
     * Set the value of the btcOrderId.
     * @param btcOrderId
     */
    public void setBtcOrderId(java.lang.Long newBtcOrderId){
    	this.btcOrderId=newBtcOrderId;
    }

    /** 
	 * The value of the btcorderItmeId association. 
	 * 
     * @return  java.lang.Long
     * Return the value of the btcorderItmeId column.
     */
    public java.lang.Long getBtcorderItmeId() {
    	return btcorderItmeId;
    }
    /**
     * Set the value of the btcorderItmeId.
     * @param btcorderItmeId
     */
    public void setBtcorderItmeId(java.lang.Long newBtcorderItmeId){
    	this.btcorderItmeId=newBtcorderItmeId;
    }

    /** 
	 * The value of the orderiAbnCode association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderiAbnCode column.
     */
    public java.lang.String getOrderiAbnCode() {
    	return orderiAbnCode;
    }
    /**
     * Set the value of the orderiAbnCode.
     * @param orderiAbnCode
     */
    public void setOrderiAbnCode(java.lang.String newOrderiAbnCode){
    	this.orderiAbnCode=newOrderiAbnCode;
    }

    /** 
	 * The value of the abnDescrib association. 
	 * 
     * @return  java.lang.String
     * Return the value of the abnDescrib column.
     */
    public java.lang.String getAbnDescrib() {
    	return abnDescrib;
    }
    /**
     * Set the value of the abnDescrib.
     * @param abnDescrib
     */
    public void setAbnDescrib(java.lang.String newAbnDescrib){
    	this.abnDescrib=newAbnDescrib;
    }

    /** 
	 * The value of the abnResolveType association. 
	 * 
     * @return  java.lang.String
     * Return the value of the abnResolveType column.
     */
    public java.lang.String getAbnResolveType() {
    	return abnResolveType;
    }
    /**
     * Set the value of the abnResolveType.
     * @param abnResolveType
     */
    public void setAbnResolveType(java.lang.String newAbnResolveType){
    	this.abnResolveType=newAbnResolveType;
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
