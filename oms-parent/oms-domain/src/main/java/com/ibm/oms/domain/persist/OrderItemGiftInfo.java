package com.ibm.oms.domain.persist;
//
//package com.ibm.sc.oms.persist;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.TableGenerator;
//
///**
// * 
// * This class is used to represent available ORDER_ITEM_GIFT_INFO in the database.</p>
// *
// * 
// */
//@Entity
//@Table(name="ORDER_ITEM_GIFT_INFO")
//public class OrderItemGiftInfo implements Serializable{
//    private static final long serialVersionUID = 1L;
//    
//
//	@Id
//	@TableGenerator(name = "order_item_gift_info", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_item_gift_info_id",allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item_gift_info")
//	@Column(unique=true, nullable=false, precision=22)
//	private java.lang.Long id;
//	@Column(name = "ID_ORDER")
//	private java.lang.Long idOrder;
//	@Column(name = "ORDER_NO")
//	private java.lang.String orderNo;
//	@Column(name = "ORDER_ITEM_NO")
//	private java.lang.String orderItemNo;
//    @Column(name = "ALIAS_ORDER_NO")
//    private java.lang.Long aliasOrderNo;
//    @Column(name = "ALIAS_ORDER_ITEM_NO")
//    private java.lang.Long aliasOrderItemNo;
//	@Column(name = "CARD_NO")
//	private java.lang.String cardNo;
//	@Column(name = "CARD_PASSWORD")
//	private java.lang.String cardPassword;
//	@Column(name = "CARD_TYPE")
//	private java.lang.String cardType;
//	@Column(name = "CARD_AMOUNT")
//	private BigDecimal cardAmount;
//	
//    @Column(name = "SALE_NUM")
//    private BigDecimal saleNum;
//    @Column(name = "UNIT_PRICE")
//    private BigDecimal unitPrice;
//    @Column(name = "TOTAL_PRICE")
//    private BigDecimal totalPrice;
//    
//	@Column(name = "REMARK")
//	private java.lang.String remark;
//	@Column(name = "IS_DELETED")
//	private java.lang.Long isDeleted;
//	@Column(name = "CREATED_BY")
//	private java.lang.String createdBy;
//	@Column(name = "UPDATED_BY")
//	private java.lang.String updatedBy;
//	@Column(name = "DATE_CREATED")
//	private java.util.Date dateCreated;
//	@Column(name = "DATE_UPDATED")
//	private java.util.Date dateUpdated;
//
//
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
//	private OrderMain orderMain;
//
//
//
//
//	/** 
//	 * default constructor 
//	 */
//    public OrderItemGiftInfo() {
//    }
//    
//																
//    /** 
//	 * The value of the id association. 
//	 * 
//     * @return  java.lang.Long
//     * Return the value of the id column.
//     */
//    public java.lang.Long getId() {
//    	return id;
//    }
//    /**
//     * Set the value of the id.
//     * @param id
//     */
//    public void setId(java.lang.Long newId){
//    	this.id=newId;
//    }
//
//    /** 
//	 * The value of the idOrder association. 
//	 * 
//     * @return  java.lang.Long
//     * Return the value of the idOrder column.
//     */
//    public java.lang.Long getIdOrder() {
//    	return idOrder;
//    }
//    /**
//     * Set the value of the idOrder.
//     * @param idOrder
//     */
//    public void setIdOrder(java.lang.Long newIdOrder){
//    	this.idOrder=newIdOrder;
//    }
//
//    /** 
//	 * The value of the orderNo association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the orderNo column.
//     */
//    public java.lang.String getOrderNo() {
//    	return orderNo;
//    }
//    /**
//     * Set the value of the orderNo.
//     * @param orderNo
//     */
//    public void setOrderNo(java.lang.String newOrderNo){
//    	this.orderNo=newOrderNo;
//    }
//
//    /** 
//	 * The value of the orderItemNo association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the orderItemNo column.
//     */
//    public java.lang.String getOrderItemNo() {
//    	return orderItemNo;
//    }
//    /**
//     * Set the value of the orderItemNo.
//     * @param orderItemNo
//     */
//    public void setOrderItemNo(java.lang.String newOrderItemNo){
//    	this.orderItemNo=newOrderItemNo;
//    }
//
//    /** 
//	 * The value of the cardNo association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the cardNo column.
//     */
//    public java.lang.String getCardNo() {
//    	return cardNo;
//    }
//    /**
//     * Set the value of the cardNo.
//     * @param cardNo
//     */
//    public void setCardNo(java.lang.String newCardNo){
//    	this.cardNo=newCardNo;
//    }
//
//    /** 
//	 * The value of the cardPassword association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the cardPassword column.
//     */
//    public java.lang.String getCardPassword() {
//    	return cardPassword;
//    }
//    /**
//     * Set the value of the cardPassword.
//     * @param cardPassword
//     */
//    public void setCardPassword(java.lang.String newCardPassword){
//    	this.cardPassword=newCardPassword;
//    }
//
//    /** 
//	 * The value of the cardType association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the cardType column.
//     */
//    public java.lang.String getCardType() {
//    	return cardType;
//    }
//    /**
//     * Set the value of the cardType.
//     * @param cardType
//     */
//    public void setCardType(java.lang.String newCardType){
//    	this.cardType=newCardType;
//    }
//
//
//
//    /** 
//	 * The value of the remark association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the remark column.
//     */
//    public java.lang.String getRemark() {
//    	return remark;
//    }
//    /**
//     * Set the value of the remark.
//     * @param remark
//     */
//    public void setRemark(java.lang.String newRemark){
//    	this.remark=newRemark;
//    }
//
//    /** 
//	 * The value of the isDeleted association. 
//	 * 
//     * @return  java.lang.Long
//     * Return the value of the isDeleted column.
//     */
//    public java.lang.Long getIsDeleted() {
//    	return isDeleted;
//    }
//    /**
//     * Set the value of the isDeleted.
//     * @param isDeleted
//     */
//    public void setIsDeleted(java.lang.Long newIsDeleted){
//    	this.isDeleted=newIsDeleted;
//    }
//
//    /** 
//	 * The value of the createdBy association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the createdBy column.
//     */
//    public java.lang.String getCreatedBy() {
//    	return createdBy;
//    }
//    /**
//     * Set the value of the createdBy.
//     * @param createdBy
//     */
//    public void setCreatedBy(java.lang.String newCreatedBy){
//    	this.createdBy=newCreatedBy;
//    }
//
//    /** 
//	 * The value of the updatedBy association. 
//	 * 
//     * @return  java.lang.String
//     * Return the value of the updatedBy column.
//     */
//    public java.lang.String getUpdatedBy() {
//    	return updatedBy;
//    }
//    /**
//     * Set the value of the updatedBy.
//     * @param updatedBy
//     */
//    public void setUpdatedBy(java.lang.String newUpdatedBy){
//    	this.updatedBy=newUpdatedBy;
//    }
//
//    /** 
//	 * The value of the dateCreated association. 
//	 * 
//     * @return  java.util.Date
//     * Return the value of the dateCreated column.
//     */
//    public java.util.Date getDateCreated() {
//    	return dateCreated;
//    }
//    /**
//     * Set the value of the dateCreated.
//     * @param dateCreated
//     */
//    public void setDateCreated(java.util.Date newDateCreated){
//    	this.dateCreated=newDateCreated;
//    }
//
//    /** 
//	 * The value of the dateUpdated association. 
//	 * 
//     * @return  java.util.Date
//     * Return the value of the dateUpdated column.
//     */
//    public java.util.Date getDateUpdated() {
//    	return dateUpdated;
//    }
//    /**
//     * Set the value of the dateUpdated.
//     * @param dateUpdated
//     */
//    public void setDateUpdated(java.util.Date newDateUpdated){
//    	this.dateUpdated=newDateUpdated;
//    }
//
//
//	public OrderMain getOrderMain() {
//		return orderMain;
//	}
//
//
//	public void setOrderMain(OrderMain orderMain) {
//		this.orderMain = orderMain;
//	}
//
//
//    public BigDecimal getCardAmount() {
//        return cardAmount;
//    }
//
//
//    public void setCardAmount(BigDecimal cardAmount) {
//        this.cardAmount = cardAmount;
//    }
//
//
//    public BigDecimal getSaleNum() {
//        return saleNum;
//    }
//
//
//    public void setSaleNum(BigDecimal saleNum) {
//        this.saleNum = saleNum;
//    }
//
//
//    public BigDecimal getUnitPrice() {
//        return unitPrice;
//    }
//
//
//    public void setUnitPrice(BigDecimal unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//
//    public BigDecimal getTotalPrice() {
//        return totalPrice;
//    }
//
//
//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//
//    public java.lang.Long getAliasOrderNo() {
//        return aliasOrderNo;
//    }
//
//
//    public void setAliasOrderNo(java.lang.Long aliasOrderNo) {
//        this.aliasOrderNo = aliasOrderNo;
//    }
//
//
//    public java.lang.Long getAliasOrderItemNo() {
//        return aliasOrderItemNo;
//    }
//
//
//    public void setAliasOrderItemNo(java.lang.Long aliasOrderItemNo) {
//        this.aliasOrderItemNo = aliasOrderItemNo;
//    }
//
//
//
//}
