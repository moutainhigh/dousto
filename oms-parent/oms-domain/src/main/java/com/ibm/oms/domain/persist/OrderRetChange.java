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
 * This class is used to represent available ORDER_RET_CHANGE in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_RET_CHANGE")
public class OrderRetChange implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @TableGenerator(name = "order_ret_chg", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_ret_chg_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_ret_chg")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_OLD_ORDER")
    private java.lang.Long idOldOrder; // OMS原销售订单id
    @Column(name = "ID_OLD_ORDER_SUB")
    private java.lang.Long idOldOrderSub; // OMS原销售子订单id
    @Column(name = "ID_OLD_ORDER_ITEM")
    private java.lang.Long idOldOrderItem; // OMS原销售订单明细id
    @Column(name = "ID_RET_ORDER")
    private java.lang.Long idRetOrder; // 意向单单id
    @Column(name = "ID_RET_ORDER_SUB")
    private java.lang.Long idRetOrderSub; // 子意向单id
    @Column(name = "ID_RET_ORDER_ITEM")
    private java.lang.Long idRetOrderItem; // 意向单明细id
    @Column(name = "ID_NEW_ORDER")
    private java.lang.Long idNewOrder; // OMS新销售订单id
    @Column(name = "ID_NEW_ORDER_SUB")
    private java.lang.Long idNewOrderSub; // OMS新子销售订单id
    @Column(name = "ID_NEW_ORDER_ITEM")
    private java.lang.Long idNewOrderItem; // OMS新销售订单明细id
    @Column(name = "OLD_ORDER_NO")
    private java.lang.String oldOrderNo; // OMS原销售订单no
    @Column(name = "OLD_ORDER_SUB_NO")
    private java.lang.String oldOrderSubNo; // OMS原销售子订单no
    @Column(name = "OLD_ORDER_ITEM_NO")
    private java.lang.String oldOrderItemNo; // OMS原销售订单明细no

    @Column(name = "RET_ORDER_NO")
    private java.lang.String retOrderNo; // 主单no
    @Column(name = "RET_ORDER_SUB_NO")
    private java.lang.String retOrderSubNo; // 子订单no
    @Column(name = "RET_ORDER_ITEM_NO")
    private java.lang.String retOrderItemNo; // 意向单明细no
    @Column(name = "NEW_ORDER_NO")
    private java.lang.String newOrderNo; // 换货出库单no
    @Column(name = "NEW_ORDER_SUB_NO")
    private java.lang.String newOrderSubNo; // 换货出库子单no
    @Column(name = "NEW_ORDER_ITEM_NO")
    private java.lang.String newOrderItemNo; // 换货出库单明细no

    @Column(name = "ORDER_SOURCE")
    private java.lang.String orderSource; // 订单来源
    @Column(name = "ALIAS_RET_ORDER_NO")
    private java.lang.String aliasRetOrderNo; // 外部退货订单号
    @Column(name = "ALIAS_OLD_ORDER_NO")
    private java.lang.String aliasOldOrderNo; // 外部原销售订单号
    @Column(name = "ALIAS_OLD_ORDER_ITEM_NO")
    private java.lang.String aliasOldOrderItemNo; // 外部原销售订单明细号
    @Column(name = "APPLY_SOURCE")
    private java.lang.String applySource; // B2C,订单客服
    @Column(name = "APPLY_TYPE")
    private java.lang.String applyType; // 退货，换货,拒收
    @Column(name = "ORDER_COMMENTS")
    private java.lang.String orderComments; // 备注
    @Column(name = "RET_CHG_TIME")
    private java.util.Date retChgTime; // 创建时间
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
    @JoinColumn(name = "ID_NEW_ORDER", insertable = false, updatable = false)
    private OrderMain idNewOrderMain;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OLD_ORDER", insertable = false, updatable = false)
    private OrderMain idOldOrderMain;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RET_ORDER", insertable = false, updatable = false)
    private OrderMain idRetOrderMain;

    /**
     * default constructor
     */
    public OrderRetChange() {
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
     * The value of the idOldOrder association.
     * 
     * @return java.lang.Long Return the value of the idOldOrder column.
     */
    public java.lang.Long getIdOldOrder() {
        return idOldOrder;
    }

    /**
     * Set the value of the idOldOrder.
     * 
     * @param idOldOrder
     */
    public void setIdOldOrder(java.lang.Long newIdOldOrder) {
        this.idOldOrder = newIdOldOrder;
    }

    /**
     * The value of the idOldOrderItem association.
     * 
     * @return java.lang.Long Return the value of the idOldOrderItem column.
     */
    public java.lang.Long getIdOldOrderItem() {
        return idOldOrderItem;
    }

    /**
     * Set the value of the idOldOrderItem.
     * 
     * @param idOldOrderItem
     */
    public void setIdOldOrderItem(java.lang.Long newIdOldOrderItem) {
        this.idOldOrderItem = newIdOldOrderItem;
    }

    /**
     * The value of the idRetOrder association.
     * 
     * @return java.lang.Long Return the value of the idRetOrder column.
     */
    public java.lang.Long getIdRetOrder() {
        return idRetOrder;
    }

    /**
     * Set the value of the idRetOrder.
     * 
     * @param idRetOrder
     */
    public void setIdRetOrder(java.lang.Long newIdRetOrder) {
        this.idRetOrder = newIdRetOrder;
    }

    /**
     * The value of the idRetOrderItem association.
     * 
     * @return java.lang.Long Return the value of the idRetOrderItem column.
     */
    public java.lang.Long getIdRetOrderItem() {
        return idRetOrderItem;
    }

    /**
     * Set the value of the idRetOrderItem.
     * 
     * @param idRetOrderItem
     */
    public void setIdRetOrderItem(java.lang.Long newIdRetOrderItem) {
        this.idRetOrderItem = newIdRetOrderItem;
    }

    /**
     * The value of the idNewOrder association.
     * 
     * @return java.lang.Long Return the value of the idNewOrder column.
     */
    public java.lang.Long getIdNewOrder() {
        return idNewOrder;
    }

    /**
     * Set the value of the idNewOrder.
     * 
     * @param idNewOrder
     */
    public void setIdNewOrder(java.lang.Long newIdNewOrder) {
        this.idNewOrder = newIdNewOrder;
    }

    /**
     * The value of the idNewOrderItem association.
     * 
     * @return java.lang.Long Return the value of the idNewOrderItem column.
     */
    public java.lang.Long getIdNewOrderItem() {
        return idNewOrderItem;
    }

    /**
     * Set the value of the idNewOrderItem.
     * 
     * @param idNewOrderItem
     */
    public void setIdNewOrderItem(java.lang.Long newIdNewOrderItem) {
        this.idNewOrderItem = newIdNewOrderItem;
    }

    /**
     * The value of the oldOrderNo association.
     * 
     * @return java.lang.String Return the value of the oldOrderNo column.
     */
    public java.lang.String getOldOrderNo() {
        return oldOrderNo;
    }

    /**
     * Set the value of the oldOrderNo.
     * 
     * @param oldOrderNo
     */
    public void setOldOrderNo(java.lang.String newOldOrderNo) {
        this.oldOrderNo = newOldOrderNo;
    }

    /**
     * The value of the oldOrderItemNo association.
     * 
     * @return java.lang.String Return the value of the oldOrderItemNo column.
     */
    public java.lang.String getOldOrderItemNo() {
        return oldOrderItemNo;
    }

    /**
     * Set the value of the oldOrderItemNo.
     * 
     * @param oldOrderItemNo
     */
    public void setOldOrderItemNo(java.lang.String newOldOrderItemNo) {
        this.oldOrderItemNo = newOldOrderItemNo;
    }

    /**
     * The value of the retOrderNo association.
     * 
     * @return java.lang.String Return the value of the retOrderNo column.
     */
    public java.lang.String getRetOrderNo() {
        return retOrderNo;
    }

    /**
     * Set the value of the retOrderNo.
     * 
     * @param retOrderNo
     */
    public void setRetOrderNo(java.lang.String newRetOrderNo) {
        this.retOrderNo = newRetOrderNo;
    }

    /**
     * The value of the retOrderItemNo association.
     * 
     * @return java.lang.String Return the value of the retOrderItemNo column.
     */
    public java.lang.String getRetOrderItemNo() {
        return retOrderItemNo;
    }

    /**
     * Set the value of the retOrderItemNo.
     * 
     * @param retOrderItemNo
     */
    public void setRetOrderItemNo(java.lang.String newRetOrderItemNo) {
        this.retOrderItemNo = newRetOrderItemNo;
    }

    /**
     * The value of the newOrderNo association.
     * 
     * @return java.lang.String Return the value of the newOrderNo column.
     */
    public java.lang.String getNewOrderNo() {
        return newOrderNo;
    }

    /**
     * Set the value of the newOrderNo.
     * 
     * @param newOrderNo
     */
    public void setNewOrderNo(java.lang.String newNewOrderNo) {
        this.newOrderNo = newNewOrderNo;
    }

    /**
     * The value of the newOrderItemNo association.
     * 
     * @return java.lang.String Return the value of the newOrderItemNo column.
     */
    public java.lang.String getNewOrderItemNo() {
        return newOrderItemNo;
    }

    /**
     * Set the value of the newOrderItemNo.
     * 
     * @param newOrderItemNo
     */
    public void setNewOrderItemNo(java.lang.String newNewOrderItemNo) {
        this.newOrderItemNo = newNewOrderItemNo;
    }

    /**
     * The value of the orderSource association.
     * 
     * @return java.lang.String Return the value of the orderSource column.
     */
    public java.lang.String getOrderSource() {
        return orderSource;
    }

    /**
     * Set the value of the orderSource.
     * 
     * @param orderSource
     */
    public void setOrderSource(java.lang.String newOrderSource) {
        this.orderSource = newOrderSource;
    }

    /**
     * The value of the aliasRetOrderNo association.
     * 
     * @return java.lang.String Return the value of the aliasRetOrderNo column.
     */
    public java.lang.String getAliasRetOrderNo() {
        return aliasRetOrderNo;
    }

    /**
     * Set the value of the aliasRetOrderNo.
     * 
     * @param aliasRetOrderNo
     */
    public void setAliasRetOrderNo(java.lang.String newAliasRetOrderNo) {
        this.aliasRetOrderNo = newAliasRetOrderNo;
    }

    /**
     * The value of the aliasOldOrderNo association.
     * 
     * @return java.lang.String Return the value of the aliasOldOrderNo column.
     */
    public java.lang.String getAliasOldOrderNo() {
        return aliasOldOrderNo;
    }

    /**
     * Set the value of the aliasOldOrderNo.
     * 
     * @param aliasOldOrderNo
     */
    public void setAliasOldOrderNo(java.lang.String newAliasOldOrderNo) {
        this.aliasOldOrderNo = newAliasOldOrderNo;
    }

    /**
     * The value of the aliasOldOrderItemNo association.
     * 
     * @return java.lang.String Return the value of the aliasOldOrderItemNo column.
     */
    public java.lang.String getAliasOldOrderItemNo() {
        return aliasOldOrderItemNo;
    }

    /**
     * Set the value of the aliasOldOrderItemNo.
     * 
     * @param aliasOldOrderItemNo
     */
    public void setAliasOldOrderItemNo(java.lang.String newAliasOldOrderItemNo) {
        this.aliasOldOrderItemNo = newAliasOldOrderItemNo;
    }

    /**
     * The value of the applySource association.
     * 
     * @return java.lang.String Return the value of the applySource column.
     */
    public java.lang.String getApplySource() {
        return applySource;
    }

    /**
     * Set the value of the applySource.
     * 
     * @param applySource
     */
    public void setApplySource(java.lang.String newApplySource) {
        this.applySource = newApplySource;
    }

    /**
     * The value of the applyType association.
     * 
     * @return java.lang.String Return the value of the applyType column.
     */
    public java.lang.String getApplyType() {
        return applyType;
    }

    /**
     * Set the value of the applyType.
     * 
     * @param applyType
     */
    public void setApplyType(java.lang.String newApplyType) {
        this.applyType = newApplyType;
    }

    /**
     * The value of the orderComments association.
     * 
     * @return java.lang.String Return the value of the orderComments column.
     */
    public java.lang.String getOrderComments() {
        return orderComments;
    }

    /**
     * Set the value of the orderComments.
     * 
     * @param orderComments
     */
    public void setOrderComments(java.lang.String newOrderComments) {
        this.orderComments = newOrderComments;
    }

    /**
     * The value of the retChgTime association.
     * 
     * @return java.util.Date Return the value of the retChgTime column.
     */
    public java.util.Date getRetChgTime() {
        return retChgTime;
    }

    /**
     * Set the value of the retChgTime.
     * 
     * @param retChgTime
     */
    public void setRetChgTime(java.util.Date newRetChgTime) {
        this.retChgTime = newRetChgTime;
    }

    /**
     * The value of the remark association.
     * 
     * @return java.lang.String Return the value of the remark column.
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * Set the value of the remark.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String newRemark) {
        this.remark = newRemark;
    }

    /**
     * The value of the isDeleted association.
     * 
     * @return java.lang.Long Return the value of the isDeleted column.
     */
    public java.lang.Long getIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of the isDeleted.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(java.lang.Long newIsDeleted) {
        this.isDeleted = newIsDeleted;
    }

    /**
     * The value of the createdBy association.
     * 
     * @return java.lang.String Return the value of the createdBy column.
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of the createdBy.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    /**
     * The value of the updatedBy association.
     * 
     * @return java.lang.String Return the value of the updatedBy column.
     */
    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the value of the updatedBy.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
    }

    /**
     * The value of the dateCreated association.
     * 
     * @return java.util.Date Return the value of the dateCreated column.
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the value of the dateCreated.
     * 
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date newDateCreated) {
        this.dateCreated = newDateCreated;
    }

    /**
     * The value of the dateUpdated association.
     * 
     * @return java.util.Date Return the value of the dateUpdated column.
     */
    public java.util.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Set the value of the dateUpdated.
     * 
     * @param dateUpdated
     */
    public void setDateUpdated(java.util.Date newDateUpdated) {
        this.dateUpdated = newDateUpdated;
    }

    public OrderMain getIdNewOrderMain() {
        return idNewOrderMain;
    }

    public void setIdNewOrderMain(OrderMain idNewOrderMain) {
        this.idNewOrderMain = idNewOrderMain;
    }

    public OrderMain getIdOldOrderMain() {
        return idOldOrderMain;
    }

    public void setIdOldOrderMain(OrderMain idOldOrderMain) {
        this.idOldOrderMain = idOldOrderMain;
    }

    public OrderMain getIdRetOrderMain() {
        return idRetOrderMain;
    }

    public void setIdRetOrderMain(OrderMain idRetOrderMain) {
        this.idRetOrderMain = idRetOrderMain;
    }

    public java.lang.Long getIdOldOrderSub() {
        return idOldOrderSub;
    }

    public void setIdOldOrderSub(java.lang.Long idOldOrderSub) {
        this.idOldOrderSub = idOldOrderSub;
    }

    public java.lang.Long getIdRetOrderSub() {
        return idRetOrderSub;
    }

    public void setIdRetOrderSub(java.lang.Long idRetOrderSub) {
        this.idRetOrderSub = idRetOrderSub;
    }

    public java.lang.Long getIdNewOrderSub() {
        return idNewOrderSub;
    }

    public void setIdNewOrderSub(java.lang.Long idNewOrderSub) {
        this.idNewOrderSub = idNewOrderSub;
    }

    public java.lang.String getOldOrderSubNo() {
        return oldOrderSubNo;
    }

    public void setOldOrderSubNo(java.lang.String oldOrderSubNo) {
        this.oldOrderSubNo = oldOrderSubNo;
    }

    public java.lang.String getRetOrderSubNo() {
        return retOrderSubNo;
    }

    public void setRetOrderSubNo(java.lang.String retOrderSubNo) {
        this.retOrderSubNo = retOrderSubNo;
    }

    public java.lang.String getNewOrderSubNo() {
        return newOrderSubNo;
    }

    public void setNewOrderSubNo(java.lang.String newOrderSubNo) {
        this.newOrderSubNo = newOrderSubNo;
    }
}