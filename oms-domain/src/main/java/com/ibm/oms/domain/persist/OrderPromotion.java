
package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * 
 * This class is used to represent available ORDER_PROMOTION in the database.</p>
 *
 * 
 */
@Entity
@Table(name="ORDER_PROMOTION")
public class OrderPromotion implements Serializable{
    private static final long serialVersionUID = 1L;
    
	@Id
	@TableGenerator(name = "order_promo", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_promo_id",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_promo")
	@Column(unique=true, nullable=false, precision=22)
	private java.lang.Long id;
	@Column(name = "ID_ORDER")
	private java.lang.Long idOrder;
	@Column(name = "ID_ORDER_ITEM")
	private java.lang.Long idOrderItem;
	@Column(name = "ORDER_NO")
	private java.lang.String orderNo;
	@Column(name = "ORDER_ITEM_NO")
	private java.lang.String orderItemNo;
	@Column(name = "PROMO_NO")
	private java.lang.String promoNo;
	@Column(name = "PROMO_NAME")
	private java.lang.String promoName;
	@Column(name = "PROMO_LEVEL")
	private java.lang.String promoLevel;
	@Column(name = "PROMO_TYPE")
	private java.lang.String promoType;
	@Column(name = "TICKET_BUNDLE_NO")
	private java.lang.String ticketBundleNo;
	@Column(name = "TICKET_NO")
	private java.lang.String ticketNo;
	@Column(name = "TICKET_AMOUNT")
	private BigDecimal ticketAmount;
	@Column(name = "MEMBER_NO")
	private java.lang.String memberNo;
	@Column(name = "POINT_COUNT")
	private BigDecimal pointCount;
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
	@Column(name = "REMARK")
	private java.lang.String remark;
	@Column(name = "FINANCIAL_SUPPLIER_CODE")
	private java.lang.String financialSupplierCode;







	/** 
	 * default constructor 
	 */
    public OrderPromotion() {
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
	 * The value of the orderItemNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the orderItemNo column.
     */
    public java.lang.String getOrderItemNo() {
    	return orderItemNo;
    }
    /**
     * Set the value of the orderItemNo.
     * @param orderItemNo
     */
    public void setOrderItemNo(java.lang.String newOrderItemNo){
    	this.orderItemNo=newOrderItemNo;
    }


    /** 
	 * The value of the promoNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the promoNo column.
     */
    public java.lang.String getPromoNo() {
    	return promoNo;
    }
    /**
     * Set the value of the promoNo.
     * @param promoNo
     */
    public void setPromoNo(java.lang.String newPromoNo){
    	this.promoNo=newPromoNo;
    }

    /** 
	 * The value of the promoName association. 
	 * 
     * @return  java.lang.String
     * Return the value of the promoName column.
     */
    public java.lang.String getPromoName() {
    	return promoName;
    }
    /**
     * Set the value of the promoName.
     * @param promoName
     */
    public void setPromoName(java.lang.String newPromoName){
    	this.promoName=newPromoName;
    }

    /** 
	 * The value of the promoLevel association. 
	 * 
     * @return  java.lang.String
     * Return the value of the promoLevel column.
     */
    public java.lang.String getPromoLevel() {
    	return promoLevel;
    }
    /**
     * Set the value of the promoLevel.
     * @param promoLevel
     */
    public void setPromoLevel(java.lang.String newPromoLevel){
    	this.promoLevel=newPromoLevel;
    }

    /** 
	 * The value of the promoType association. 
	 * 
     * @return  java.lang.String
     * Return the value of the promoType column.
     */
    public java.lang.String getPromoType() {
    	return promoType;
    }
    /**
     * Set the value of the promoType.
     * @param promoType
     */
    public void setPromoType(java.lang.String newPromoType){
    	this.promoType=newPromoType;
    }

    /** 
	 * The value of the ticketBundleNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the ticketBundleNo column.
     */
    public java.lang.String getTicketBundleNo() {
    	return ticketBundleNo;
    }
    /**
     * Set the value of the ticketBundleNo.
     * @param ticketBundleNo
     */
    public void setTicketBundleNo(java.lang.String newTicketBundleNo){
    	this.ticketBundleNo=newTicketBundleNo;
    }

    /** 
	 * The value of the ticketNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the ticketNo column.
     */
    public java.lang.String getTicketNo() {
    	return ticketNo;
    }
    /**
     * Set the value of the ticketNo.
     * @param ticketNo
     */
    public void setTicketNo(java.lang.String newTicketNo){
    	this.ticketNo=newTicketNo;
    }


    /** 
	 * The value of the memberNo association. 
	 * 
     * @return  java.lang.String
     * Return the value of the memberNo column.
     */
    public java.lang.String getMemberNo() {
    	return memberNo;
    }
    /**
     * Set the value of the memberNo.
     * @param memberNo
     */
    public void setMemberNo(java.lang.String newMemberNo){
    	this.memberNo=newMemberNo;
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
	 * The value of the financialSupplierCode association. 
	 * 
     * @return  java.lang.String
     * Return the value of the financialSupplierCode column.
     */
    public java.lang.String getFinancialSupplierCode() {
    	return financialSupplierCode;
    }
    /**
     * Set the value of the financialSupplierCode.
     * @param financialSupplierCode
     */
    public void setFinancialSupplierCode(java.lang.String newFinancialSupplierCode){
    	this.financialSupplierCode=newFinancialSupplierCode;
    }


    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }


    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }


    public BigDecimal getPointCount() {
        return pointCount;
    }


    public void setPointCount(BigDecimal pointCount) {
        this.pointCount = pointCount;
    }


}
