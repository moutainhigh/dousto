
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
 * This class is used to represent available ORDER_COOPERATOR in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_COOPERATOR")
public class OrderCooperator implements Serializable{
    private static final long serialVersionUID = 1L;
    
	@Id
	@TableGenerator(name = "order_cooperator", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_cooperator_id",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_cooperator")
	@Column(unique=true, nullable=false, precision=22)
	private java.lang.Long id;
	@Column(name = "ID_ORDER")
	private java.lang.Long idOrder;
	@Column(name = "ID_ORDER_SUB")
	private java.lang.Long idOrderSub;
	@Column(name = "ORDER_NO")
	private java.lang.String orderNo;
	@Column(name = "ORDER_SUB_NO")
	private java.lang.String orderSubNo;
	@Column(name = "PARTNER")
	private java.lang.String PARTNER;
	@Column(name = "LOGISTICS_NO")
	private java.lang.Long logisticsNo;
	@Column(name = "LOGISTICS_ORDER_NO")
	private java.lang.Long logisticsOrderNo;
	@Column(name = "PHONE_NUM")
	private java.lang.String phoneNum;
	@Column(name = "MOB_PHONE_NUM")
	private java.lang.String mobPhoneNum;
	@Column(name = "POST_CODE")
	private java.lang.String postCode;
	@Column(name = "ADDRESS_CODE")
	private java.lang.String addressCode;
	@Column(name = "ADDRESS_DETAIL")
	private java.lang.String addressDetail;
	@Column(name = "BILL_TYPE")
	private java.lang.Long billType;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORDER_SUB", insertable = false, updatable = false)
	private OrderSub orderSub;




	/** 
	 * default constructor 
	 */
    public OrderCooperator() {
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


	public java.lang.Long getIdOrder() {
		return idOrder;
	}


	public void setIdOrder(java.lang.Long idOrder) {
		this.idOrder = idOrder;
	}


	public java.lang.Long getIdOrderSub() {
		return idOrderSub;
	}


	public void setIdOrderSub(java.lang.Long idOrderSub) {
		this.idOrderSub = idOrderSub;
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


	public java.lang.String getPARTNER() {
		return PARTNER;
	}


	public void setPARTNER(java.lang.String pARTNER) {
		PARTNER = pARTNER;
	}


	public java.lang.Long getLogisticsNo() {
		return logisticsNo;
	}


	public void setLogisticsNo(java.lang.Long logisticsNo) {
		this.logisticsNo = logisticsNo;
	}


	public java.lang.Long getLogisticsOrderNo() {
		return logisticsOrderNo;
	}


	public void setLogisticsOrderNo(java.lang.Long logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}


	public java.lang.String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(java.lang.String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public java.lang.String getMobPhoneNum() {
		return mobPhoneNum;
	}


	public void setMobPhoneNum(java.lang.String mobPhoneNum) {
		this.mobPhoneNum = mobPhoneNum;
	}


	public java.lang.String getPostCode() {
		return postCode;
	}


	public void setPostCode(java.lang.String postCode) {
		this.postCode = postCode;
	}


	public java.lang.String getAddressCode() {
		return addressCode;
	}


	public void setAddressCode(java.lang.String addressCode) {
		this.addressCode = addressCode;
	}


	public java.lang.String getAddressDetail() {
		return addressDetail;
	}


	public void setAddressDetail(java.lang.String addressDetail) {
		this.addressDetail = addressDetail;
	}


	public java.lang.Long getBillType() {
		return billType;
	}


	public void setBillType(java.lang.Long billType) {
		this.billType = billType;
	}



	public java.lang.String getRemark() {
		return remark;
	}


	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public java.lang.Long getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(java.lang.Long isDeleted) {
		this.isDeleted = isDeleted;
	}


	public java.lang.String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}


	public java.lang.String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public java.util.Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public java.util.Date getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(java.util.Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}


	public OrderMain getOrderMain() {
		return orderMain;
	}


	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}


	public OrderSub getOrderSub() {
		return orderSub;
	}


	public void setOrderSub(OrderSub orderSub) {
		this.orderSub = orderSub;
	}



}
