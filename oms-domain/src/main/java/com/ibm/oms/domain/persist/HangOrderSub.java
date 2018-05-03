package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * 
 * This class is used to represent available ORDER_SUB in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "TEMP_ORDER_SUB")
public class HangOrderSub implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "temp_order_sub", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "temp_order_sub_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "temp_order_sub")
    @Column(name = "ID", unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.String aliasOrderNo;
    @Column(name = "ALIAS_ORDER_SUB_NO")
    private java.lang.String aliasOrderSubNo;
    @Column(name = "DELIVERY_MERCHANT_NO")
    private java.lang.String deliveryMerchantNo;
    @Column(name = "DELIVERY_MERCHANT_NAME")
    private java.lang.String deliveryMerchantName;
    @Column(name = "DISTRIBUTE_TYPE")
    private java.lang.String distributeType;
    @Column(name = "LOGISTICS_OUT_NO")
    private java.lang.String logisticsOutNo; // 物流订单外部编号(运单号)
    @Column(name = "HOPE_ARRIVAL_TIME")
    private Date hopeArrivalTime;
    @Column(name = "DELIVERY_PRIORITY")
    private java.lang.String deliveryPriority;
    @Column(name = "SHIP_CAT")
    private java.lang.String shipCat;
    @Column(name = "PACKAGE_DETAIL")
    private java.lang.String packageDetail;
    @Column(name = "TRANSPORT_FEE")
    private BigDecimal transportFee;
    @Column(name = "LOGISTICS_STATUS")
    private java.lang.String logisticsStatus;
    @Column(name = "PROVIDE_ADDRESS")
    private java.lang.String provideAddress;
    @Column(name = "SELF_FETCH_ADDRESS")
    private java.lang.String selfFetchAddress;
    @Column(name = "PICK_TIME")
    private java.util.Date pickTime;
    @Column(name = "CHECK_CODE")
    private java.lang.String checkCode;
    @Column(name = "STORE_NO")
    private java.lang.String storeNo;
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
    @Column(name = "USER_NAME")
    private java.lang.String userName;
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
    @Column(name = "DELIVERY_REMARK")
    private java.lang.String deliveryRemark;
    @Column(name = "EXPRESS_TYPE")
    private java.lang.String expressType;
    @Column(name = "SIGN_OFF_TIME")
    private java.util.Date signOffTime;
    @Column(name = "LOGISTICS_DESCRIPTION")
    private java.lang.String logistics_description;
    @Column(name = "SIGNER_NAME")
    private java.lang.String signer_name;
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;// 单据类型 1：正向订单 ； -1：逆向订单
    @Column(name = "INVOICE_PRINTED")
    private java.lang.Long invoicePrinted;
    @Column(name = "ORDER_SUB_RELATED_ORIGIN")
    private java.lang.String orderSubRelatedOrigin;
    @Column(name = "EMAIL")
    private java.lang.String email;
    @Column(name = "BOL_NO")
    private java.lang.String bolNo;// 箱号
    @Column(name = "OUT_STORE_TIME")
    private java.util.Date outStoreTime; // 出库时间

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private HangOrderMain hangOrderMain;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER_SUB")
    private List<HangOrderItem> hangOrderItems = new ArrayList<HangOrderItem>();
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER_SUB")
    private List<HangOrderInvoice> hangOrderInvoices = new ArrayList<HangOrderInvoice>();
    

    /**
     * default constructor
     */
    public HangOrderSub() {
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
     * The value of the idOrder association.
     * 
     * @return java.lang.Long Return the value of the idOrder column.
     */
    public java.lang.Long getIdOrder() {
        return idOrder;
    }

    /**
     * Set the value of the idOrder.
     * 
     * @param idOrder
     */
    public void setIdOrder(java.lang.Long newIdOrder) {
        this.idOrder = newIdOrder;
    }

    /**
     * The value of the orderNo association.
     * 
     * @return java.lang.String Return the value of the orderNo column.
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value of the orderNo.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String newOrderNo) {
        this.orderNo = newOrderNo;
    }

    /**
     * The value of the orderSubNo association.
     * 
     * @return java.lang.String Return the value of the orderSubNo column.
     */
    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    /**
     * Set the value of the orderSubNo.
     * 
     * @param orderSubNo
     */
    public void setOrderSubNo(java.lang.String newOrderSubNo) {
        this.orderSubNo = newOrderSubNo;
    }

    /**
     * The value of the deliveryMerchantNo association.
     * 
     * @return java.lang.String Return the value of the deliveryMerchantNo column.
     */
    public java.lang.String getDeliveryMerchantNo() {
        return deliveryMerchantNo;
    }

    /**
     * Set the value of the deliveryMerchantNo.
     * 
     * @param deliveryMerchantNo
     */
    public void setDeliveryMerchantNo(java.lang.String newDeliveryMerchantNo) {
        this.deliveryMerchantNo = newDeliveryMerchantNo;
    }

    /**
     * The value of the distributeType association.
     * 
     * @return java.lang.String Return the value of the distributeType column.
     */
    public java.lang.String getDistributeType() {
        return distributeType;
    }

    /**
     * Set the value of the distributeType.
     * 
     * @param distributeType
     */
    public void setDistributeType(java.lang.String newDistributeType) {
        this.distributeType = newDistributeType;
    }

    /**
     * The value of the logisticsOutNo association.
     * 
     * @return java.lang.String Return the value of the logisticsOutNo column.
     */
    public java.lang.String getLogisticsOutNo() {
        return logisticsOutNo;
    }

    /**
     * Set the value of the logisticsOutNo.
     * 
     * @param logisticsOutNo
     */
    public void setLogisticsOutNo(java.lang.String newLogisticsOutNo) {
        this.logisticsOutNo = newLogisticsOutNo;
    }

    public Date getHopeArrivalTime() {
		return hopeArrivalTime;
	}


	public void setHopeArrivalTime(Date hopeArrivalTime) {
		this.hopeArrivalTime = hopeArrivalTime;
	}


	/**
     * The value of the deliveryPriority association.
     * 
     * @return java.lang.String Return the value of the deliveryPriority column.
     */
    public java.lang.String getDeliveryPriority() {
        return deliveryPriority;
    }

    /**
     * Set the value of the deliveryPriority.
     * 
     * @param deliveryPriority
     */
    public void setDeliveryPriority(java.lang.String newDeliveryPriority) {
        this.deliveryPriority = newDeliveryPriority;
    }

    /**
     * The value of the logisticsStatus association.
     * 
     * @return java.lang.String Return the value of the logisticsStatus column.
     */
    public java.lang.String getLogisticsStatus() {
        return logisticsStatus;
    }

    /**
     * Set the value of the logisticsStatus.
     * 
     * @param logisticsStatus
     */
    public void setLogisticsStatus(java.lang.String newLogisticsStatus) {
        this.logisticsStatus = newLogisticsStatus;
    }

    /**
     * The value of the signOffTime association.
     * 
     * @return java.util.Date Return the value of the signOffTime column.
     */
    public java.util.Date getSignOffTime() {
        return signOffTime;
    }

    /**
     * Set the value of the signOffTime.
     * 
     * @param signOffTime
     */
    public void setSignOffTime(java.util.Date newSignOffTime) {
        this.signOffTime = newSignOffTime;
    }

    /**
     * The value of the provideAddress association.
     * 
     * @return java.lang.String Return the value of the provideAddress column.
     */
    public java.lang.String getProvideAddress() {
        return provideAddress;
    }

    /**
     * Set the value of the provideAddress.
     * 
     * @param provideAddress
     */
    public void setProvideAddress(java.lang.String newProvideAddress) {
        this.provideAddress = newProvideAddress;
    }

    /**
     * The value of the selfFetchAddress association.
     * 
     * @return java.lang.String Return the value of the selfFetchAddress column.
     */
    public java.lang.String getSelfFetchAddress() {
        return selfFetchAddress;
    }

    /**
     * Set the value of the selfFetchAddress.
     * 
     * @param selfFetchAddress
     */
    public void setSelfFetchAddress(java.lang.String newSelfFetchAddress) {
        this.selfFetchAddress = newSelfFetchAddress;
    }

    /**
     * The value of the pickTime association.
     * 
     * @return java.util.Date Return the value of the pickTime column.
     */
    public java.util.Date getPickTime() {
        return pickTime;
    }

    /**
     * Set the value of the pickTime.
     * 
     * @param pickTime
     */
    public void setPickTime(java.util.Date newPickTime) {
        this.pickTime = newPickTime;
    }

    /**
     * The value of the checkCode association.
     * 
     * @return java.lang.String Return the value of the checkCode column.
     */
    public java.lang.String getCheckCode() {
        return checkCode;
    }

    /**
     * Set the value of the checkCode.
     * 
     * @param checkCode
     */
    public void setCheckCode(java.lang.String newCheckCode) {
        this.checkCode = newCheckCode;
    }

    /**
     * The value of the expressType association.
     * 
     * @return java.lang.String Return the value of the expressType column.
     */
    public java.lang.String getExpressType() {
        return expressType;
    }

    /**
     * Set the value of the expressType.
     * 
     * @param expressType
     */
    public void setExpressType(java.lang.String newExpressType) {
        this.expressType = newExpressType;
    }

    /**
     * The value of the storeNo association.
     * 
     * @return java.lang.String Return the value of the storeNo column.
     */
    public java.lang.String getStoreNo() {
        return storeNo;
    }

    /**
     * Set the value of the storeNo.
     * 
     * @param storeNo
     */
    public void setStoreNo(java.lang.String newStoreNo) {
        this.storeNo = newStoreNo;
    }

    /**
     * The value of the userName association.
     * 
     * @return java.lang.String Return the value of the userName column.
     */
    public java.lang.String getUserName() {
        return userName;
    }

    /**
     * Set the value of the userName.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String newUserName) {
        this.userName = newUserName;
    }

    /**
     * The value of the phoneNum association.
     * 
     * @return java.lang.String Return the value of the phoneNum column.
     */
    public java.lang.String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Set the value of the phoneNum.
     * 
     * @param phoneNum
     */
    public void setPhoneNum(java.lang.String newPhoneNum) {
        this.phoneNum = newPhoneNum;
    }

    /**
     * The value of the mobPhoneNum association.
     * 
     * @return java.lang.String Return the value of the mobPhoneNum column.
     */
    public java.lang.String getMobPhoneNum() {
        return mobPhoneNum;
    }

    /**
     * Set the value of the mobPhoneNum.
     * 
     * @param mobPhoneNum
     */
    public void setMobPhoneNum(java.lang.String newMobPhoneNum) {
        this.mobPhoneNum = newMobPhoneNum;
    }

    /**
     * The value of the postCode association.
     * 
     * @return java.lang.String Return the value of the postCode column.
     */
    public java.lang.String getPostCode() {
        return postCode;
    }

    /**
     * Set the value of the postCode.
     * 
     * @param postCode
     */
    public void setPostCode(java.lang.String newPostCode) {
        this.postCode = newPostCode;
    }

    /**
     * The value of the addressCode association.
     * 
     * @return java.lang.String Return the value of the addressCode column.
     */
    public java.lang.String getAddressCode() {
        return addressCode;
    }

    /**
     * Set the value of the addressCode.
     * 
     * @param addressCode
     */
    public void setAddressCode(java.lang.String newAddressCode) {
        this.addressCode = newAddressCode;
    }

    /**
     * The value of the addressDetail association.
     * 
     * @return java.lang.String Return the value of the addressDetail column.
     */
    public java.lang.String getAddressDetail() {
        return addressDetail;
    }

    /**
     * Set the value of the addressDetail.
     * 
     * @param addressDetail
     */
    public void setAddressDetail(java.lang.String newAddressDetail) {
        this.addressDetail = newAddressDetail;
    }

    /**
     * The value of the deliveryRemark association.
     * 
     * @return java.lang.String Return the value of the deliveryRemark column.
     */
    public java.lang.String getDeliveryRemark() {
        return deliveryRemark;
    }

    /**
     * Set the value of the deliveryRemark.
     * 
     * @param deliveryRemark
     */
    public void setDeliveryRemark(java.lang.String newDeliveryRemark) {
        this.deliveryRemark = newDeliveryRemark;
    }

    /**
     * The value of the packageDetail association.
     * 
     * @return java.lang.String Return the value of the packageDetail column.
     */
    public java.lang.String getPackageDetail() {
        return packageDetail;
    }

    /**
     * Set the value of the packageDetail.
     * 
     * @param packageDetail
     */
    public void setPackageDetail(java.lang.String newPackageDetail) {
        this.packageDetail = newPackageDetail;
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

    public java.lang.String getLogistics_description() {
        return logistics_description;
    }

    public void setLogistics_description(java.lang.String logistics_description) {
        this.logistics_description = logistics_description;
    }

    public java.lang.String getSigner_name() {
        return signer_name;
    }

    public void setSigner_name(java.lang.String signer_name) {
        this.signer_name = signer_name;
    }

    public HangOrderMain getHangOrderMain() {
		return hangOrderMain;
	}


	public void setHangOrderMain(HangOrderMain hangOrderMain) {
		this.hangOrderMain = hangOrderMain;
	}


	public java.lang.Long getBillType() {
        return billType;
    }

    public void setBillType(java.lang.Long billType) {
        this.billType = billType;
    }

    public java.lang.Long getInvoicePrinted() {
        return invoicePrinted;
    }

    public void setInvoicePrinted(java.lang.Long newInvoicePrinted) {
        this.invoicePrinted = newInvoicePrinted;
    }


    public BigDecimal getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    public java.lang.String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(java.lang.String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public java.lang.String getAliasOrderSubNo() {
        return aliasOrderSubNo;
    }

    public void setAliasOrderSubNo(java.lang.String aliasOrderSubNo) {
        this.aliasOrderSubNo = aliasOrderSubNo;
    }

    public java.lang.String getOrderSubRelatedOrigin() {
        return orderSubRelatedOrigin;
    }

    public void setOrderSubRelatedOrigin(java.lang.String orderSubRelatedOrigin) {
        this.orderSubRelatedOrigin = orderSubRelatedOrigin;
    }

    public java.lang.String getShipCat() {
        return shipCat;
    }

    public void setShipCat(java.lang.String shipCat) {
        this.shipCat = shipCat;
    }

    public java.lang.String getDeliveryMerchantName() {
        return deliveryMerchantName;
    }

    public void setDeliveryMerchantName(java.lang.String deliveryMerchantName) {
        this.deliveryMerchantName = deliveryMerchantName;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getBolNo() {
        return bolNo;
    }

    public void setBolNo(java.lang.String bolNo) {
        this.bolNo = bolNo;
    }

    public java.util.Date getOutStoreTime() {
        return outStoreTime;
    }

    public void setOutStoreTime(java.util.Date outStoreTime) {
        this.outStoreTime = outStoreTime;
    }


	public List<HangOrderItem> getHangOrderItems() {
		return hangOrderItems;
	}


	public void setHangOrderItems(List<HangOrderItem> hangOrderItems) {
		this.hangOrderItems = hangOrderItems;
	}


	public List<HangOrderInvoice> getHangOrderInvoices() {
		return hangOrderInvoices;
	}


	public void setHangOrderInvoices(List<HangOrderInvoice> hangOrderInvoices) {
		this.hangOrderInvoices = hangOrderInvoices;
	}
    
    

}
