package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.ibm.sc.util.DateUtil;

/**
 * 
 * This class is used to represent available ORDER_MAIN in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_MAIN")
public class OrderMain implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "BATCH_NUM")
    private java.lang.String batchNum;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.String aliasOrderNo;
    @Column(name = "ORDER_SOURCE")
    private java.lang.String orderSource;
    @Column(name = "ORDER_TYPE")
    private java.lang.String orderType;
    @Column(name = "MERCHANT_TYPE")
    private java.lang.String merchantType;
    @Column(name = "MERCHANT_NO")
    private java.lang.String merchantNo;
    @Column(name = "IF_SHOW_PRICE")
    private java.lang.Long ifShowPrice;
    @Column(name = "NEED_CONFIRM")
    private java.lang.Long needConfirm;
    @Column(name = "FINISH_USER_NO")
    private java.lang.String finishUserNo;
    @Column(name = "FINISH_TIME")
    private java.util.Date finishTime;
    @Column(name = "MEMBER_NO")
    private java.lang.String memberNo;
    @Column(name = "MEMBER_CARD_LEVEL")
    private java.lang.String memberCardLevel;
    @Column(name = "CUSTOMER_NAME")
    private java.lang.String customerName;
    @Column(name = "CUSTOMER_PHONE")
    private java.lang.String customerPhone;
    @Column(name = "DELIVERY_TIME_FLAG")
    private java.lang.String deliveryTimeFlag;
    @Column(name = "DELIVERY_DATE_FLAG")
    private java.lang.String deliveryDateFlag;
    @Column(name = "RECEIVE_AREA_ID")
    private java.lang.String receiveAreaId;
    @Column(name = "ORDER_TIME")
    private java.util.Date orderTime;
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Column(name = "TRANSPORT_FEE")
    private BigDecimal transportFee;
    @Column(name = "TOTAL_PRODUCT_PRICE")
    private BigDecimal totalProductPrice;
    @Column(name = "DISCOUNT_TRANSPORT")
    private BigDecimal discountTransport;
    @Column(name = "DISCOUNT_TOTAL")
    private BigDecimal discountTotal;
    @Column(name = "TOTAL_PROMO")
    private BigDecimal totalPromo;
    @Column(name = "TOTAL_PAY_AMOUNT")
    private BigDecimal totalPayAmount;
    @Column(name = "TOTAL_GIVE_POINTS")
    private BigDecimal totalGivePoints;
    @Column(name = "TOTAL_PIONT_AMOUNT")
    private BigDecimal totalPointAmount;//订单用积分抵扣金额
    @Column(name = "TOTAL_PIONT")
    private BigDecimal totalPoint;//订单用积分总量
    @Column(name = "NEED_INVOICE")
    private java.lang.Long needInvoice;
    @Column(name = "CONFIRMER_NO")
    private java.lang.String confirmerNo;
    @Column(name = "CONFIRMER_NAME")
    private java.lang.String confirmerName;
    @Column(name = "CONFIRM_TIME")
    private java.util.Date confirmTime;
    @Column(name = "STATUS_CONFIRM")
    private java.lang.String statusConfirm; // 审核状态
    @Column(name = "STATUS_PAY")
    private java.lang.String statusPay; // 支付状态
    @Column(name = "STATUS_TOTAL")
    private java.lang.String statusTotal; // 总状态
    @Column(name = "CLIENT_SERVICE_REMARK")
    private java.lang.String clientServiceRemark;
    @Column(name = "CLIENT_SERVICE_REMARK_FLAG")
    private Integer clientServiceRemarkFlag;
    @Column(name = "CLIENT_REMARK")
    private java.lang.String clientRemark;
    @Column(name = "REMARK")
    private java.lang.String remark;
    @Column(name = "IF_PAY_ON_ARRIVAL")
    private java.lang.Long ifPayOnArrival;
    @Column(name = "PAY_ON_ARRIVAL_PAY_TYPE")
    private java.lang.String payOnArrivalPayType;
    @Column(name = "ORDER_CATEGORY")
    private java.lang.String orderCategory;// 退换货类型
    @Column(name = "ORDER_RELATED_ORIGIN")
    private java.lang.String orderRelatedOrigin;
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;// 单据类型 1：正向订单 ； -1：逆向订单
    @Column(name = "IF_NEED_REFUND")
    private java.lang.Long ifNeedRefund;// 逆向订单：是否需退款
    @Column(name = "IF_BLACKLIST_MEMBER")
    private java.lang.Long ifBlackListMember;// 是否黑名单会员
    @Column(name = "IF_PRIVILEDGED_MEMBER")
    private java.lang.Long ifPriviledgedMember;// 是否大客户会员
    @Column(name = "IF_WARN_ORDER")
    private java.lang.Long ifWarnOrder;// 是否预警订单
    @Column(name = "CHGOUT_ORDER_NO")
    private java.lang.String chgOurOrderNo;// 换货意向单所产生的出库单
    @Column(name = "IP")
    private java.lang.String ip;// IP地址
    @Column(name = "IMMIGRATION_VERSION")
    private java.lang.String immigrationVersion;// 迁移版本号
   
    @Column(name = "IS_COMMISSION")
    private Integer  isCommission;// 是否有产生提成

    @Column(name = "SALESCLERK_PERFORM")
    private BigDecimal  salesclerkPerform;// 销售员业绩（提成）
    
    @Column(name = "REGION_CODE")
    private String regionCode;//区域编码

    @Column(name = "BALANCE_DATE")
    private Date  balanceDate;//结算日期
    //配送方式 
    
    @Column(name = "DELIVERY_TYPE")
    private String deliveryType;
    
    //退款方式
    @Column(name = "REFUND_TYPE")
    private String refundType;
    
    //卖家留言
    @Column(name = "SELLER_MESSAGE")
    private String sellerMessage;
    
    //是否挂起
    @Column(name = "IS_SUSPENSION")
    private Integer isSuspension;
    
    //是否合并单
    @Column(name = "IS_MERGE")
    private Integer isMerge;
    
    //导购编号
    @Column(name = "SALESCLERK_NO")
    private String salesclerkNo;
    //是否拆单
    @Column(name = "IS_SPLIT")
    private Integer isSplit;
    //是否拆单
    @Column(name = "IS_BARTER")
    private Integer isBarter;
    
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

    @Column(name = "SALE_STORE_CODE" )
    private java.lang.String saleStoreCode;

    @Column(name = "SALE_COMPANY_CODE" )
    private java.lang.String saleCompanyCode;

    @Column(name = "SALE_FRANCHISEE_CODE" )
    private java.lang.String saleFranchiseeCode;
    
    @Column(name = "SHIP_STORE_CODE" )
    private java.lang.String shipStoreCode;
    @Column(name = "SHIP_STORE_NAME" )
    private java.lang.String shipStoreName;
    @Column(name = "PERFORM_STORE_CODE" )
    private java.lang.String performStoreCode;
    // 提醒短信是否已发送
    @Column(name = "REMIND_SENT")
    private java.lang.Long remindSent;
    @Column(name = "CUSTOMER_EMAIL")
    private java.lang.String customerEmail;
    // 业务逻辑中使用:退换货用到
    @Transient
    private String refOrderNo; // 原OMS销售订单NO
    @Transient
    private long refOrderId; // 原OMS销售订单ID

    // 订单开始日期和订单结束日期 搜索时用到
    @Transient
    private java.util.Date orderTimeFrom;
    @Transient
    private java.util.Date orderTimeTo;
    // 订单完成日期 搜索时用到
    @Transient
    private java.util.Date finishTimeFrom;
    @Transient
    private java.util.Date finishTimeTo;
    /**
     * 原支付方式
     */
    @Transient
    private List<OrderPay> originalOrderPayList;

    /**
     * 订单金额上限
     */
    @Transient
    private BigDecimal amountUp;
    /**
     * 订单金额下限
     */
    @Transient
    private BigDecimal amountDown;
    // 完成状态 搜索时候用到
    @Transient
    private String finishStatus;
    // 用于能否退货
    @Transient
    private boolean canReturn;
    @Transient
    private boolean hadCreateRefuse;
    // 账户积分
    @Transient
    private BigDecimal integral;
    // 渠道订单号
    @Transient
    private String sourceOrderNo;// 渠道订单号(外部订单通过B2C对接到中台)

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderCooperator> orderCooperators = new ArrayList<OrderCooperator>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderInvoice> orderInvoices = new ArrayList<OrderInvoice>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderInvoicePrint> orderInvoicePrints = new ArrayList<OrderInvoicePrint>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderPromotion> orderPromotions = new ArrayList<OrderPromotion>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderItemAbn> orderItemAbns = new ArrayList<OrderItemAbn>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderItemVirtual> orderItemVirtuals = new ArrayList<OrderItemVirtual>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderPay> orderPays = new ArrayList<OrderPay>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderRetChgItem> orderRetChgItems = new ArrayList<OrderRetChgItem>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderSub> orderSubs = new ArrayList<OrderSub>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderPayMode> orderPayModes = new ArrayList<OrderPayMode>();

    // 取消订单原因编号
    @Column(name = "CANCEL_REASON_NO")
    private java.lang.String cancelReasonNo;

    // 会员信息
    @Transient
    private java.lang.String memberInfo;
    
    @Column(name = "ORDER_NO_P")
    private String orderNoP;
    @Column(name = "ID_ORDER_P")
    private Long idOrderP;
    //商品总数
    @Column(name = "TOTAL_PRODUCT_COUNT")
    private Integer totalProductCount;

    public String getShipStoreCode() {
        return shipStoreCode;
    }

    public void setShipStoreCode(String shipStoreCode) {
        this.shipStoreCode = shipStoreCode;
    }

    /**
     * default constructor
     */
    public OrderMain() {
        this.dateCreated = DateUtil.getNowDate();
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
     * The value of the aliasOrderNo association.
     * 
     * @return java.lang.Long Return the value of the aliasOrderNo column.
     */
    public java.lang.String getAliasOrderNo() {
        return aliasOrderNo;
    }

    /**
     * Set the value of the aliasOrderNo.
     * 
     * @param aliasOrderNo
     */
    public void setAliasOrderNo(java.lang.String newAliasOrderNo) {
        this.aliasOrderNo = newAliasOrderNo;
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
     * The value of the orderType association.
     * 
     * @return java.lang.String Return the value of the orderType column.
     */
    public java.lang.String getOrderType() {
        return orderType;
    }

    /**
     * Set the value of the orderType.
     * 
     * @param orderType
     */
    public void setOrderType(java.lang.String newOrderType) {
        this.orderType = newOrderType;
    }

    /**
     * The value of the merchantType association.
     * 
     * @return java.lang.String Return the value of the merchantType column.
     */
    public java.lang.String getMerchantType() {
        return merchantType;
    }

    /**
     * Set the value of the merchantType.
     * 
     * @param merchantType
     */
    public void setMerchantType(java.lang.String newMerchantType) {
        this.merchantType = newMerchantType;
    }

    /**
     * The value of the merchantNo association.
     * 
     * @return java.lang.String Return the value of the merchantNo column.
     */
    public java.lang.String getMerchantNo() {
        return merchantNo;
    }

    /**
     * Set the value of the merchantNo.
     * 
     * @param merchantNo
     */
    public void setMerchantNo(java.lang.String newMerchantNo) {
        this.merchantNo = newMerchantNo;
    }

    /**
     * The value of the ifShowPrice association.
     * 
     * @return java.lang.Long Return the value of the ifShowPrice column.
     */
    public java.lang.Long getIfShowPrice() {
        return ifShowPrice;
    }

    /**
     * Set the value of the ifShowPrice.
     * 
     * @param ifShowPrice
     */
    public void setIfShowPrice(java.lang.Long newIfShowPrice) {
        this.ifShowPrice = newIfShowPrice;
    }

    /**
     * The value of the needConfirm association.
     * 
     * @return java.lang.Long Return the value of the needConfirm column.
     */
    public java.lang.Long getNeedConfirm() {
        return needConfirm;
    }

    /**
     * Set the value of the needConfirm.
     * 
     * @param needConfirm
     */
    public void setNeedConfirm(java.lang.Long newNeedConfirm) {
        this.needConfirm = newNeedConfirm;
    }

    /**
     * The value of the finishUserNo association.
     * 
     * @return java.lang.String Return the value of the finishUserNo column.
     */
    public java.lang.String getFinishUserNo() {
        return finishUserNo;
    }

    /**
     * Set the value of the finishUserNo.
     * 
     * @param finishUserNo
     */
    public void setFinishUserNo(java.lang.String newFinishUserNo) {
        this.finishUserNo = newFinishUserNo;
    }

    /**
     * The value of the finishTime association.
     * 
     * @return java.util.Date Return the value of the finishTime column.
     */
    public java.util.Date getFinishTime() {
        return finishTime;
    }

    /**
     * Set the value of the finishTime.
     * 
     * @param finishTime
     */
    public void setFinishTime(java.util.Date newFinishTime) {
        this.finishTime = newFinishTime;
    }

    /**
     * The value of the memberNo association.
     * 
     * @return java.lang.String Return the value of the memberNo column.
     */
    public java.lang.String getMemberNo() {
        return memberNo;
    }

    /**
     * Set the value of the memberNo.
     * 
     * @param memberNo
     */
    public void setMemberNo(java.lang.String newMemberNo) {
        this.memberNo = newMemberNo;
    }

    /**
     * The value of the customerName association.
     * 
     * @return java.lang.String Return the value of the customerName column.
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }

    /**
     * Set the value of the customerName.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String newCustomerName) {
        this.customerName = newCustomerName;
    }

    /**
     * The value of the customerPhone association.
     * 
     * @return java.lang.String Return the value of the customerPhone column.
     */
    public java.lang.String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Set the value of the customerPhone.
     * 
     * @param customerPhone
     */
    public void setCustomerPhone(java.lang.String newCustomerPhone) {
        this.customerPhone = newCustomerPhone;
    }

    /**
     * The value of the deliveryTimeFlag association.
     * 
     * @return java.lang.String Return the value of the deliveryTimeFlag column.
     */
    public java.lang.String getDeliveryTimeFlag() {
        return deliveryTimeFlag;
    }

    /**
     * Set the value of the deliveryTimeFlag.
     * 
     * @param deliveryTimeFlag
     */
    public void setDeliveryTimeFlag(java.lang.String newDeliveryTimeFlag) {
        this.deliveryTimeFlag = newDeliveryTimeFlag;
    }

    /**
     * The value of the deliveryDateFlag association.
     * 
     * @return java.lang.String Return the value of the deliveryDateFlag column.
     */
    public java.lang.String getDeliveryDateFlag() {
        return deliveryDateFlag;
    }

    /**
     * Set the value of the deliveryDateFlag.
     * 
     * @param deliveryDateFlag
     */
    public void setDeliveryDateFlag(java.lang.String newDeliveryDateFlag) {
        this.deliveryDateFlag = newDeliveryDateFlag;
    }

    /**
     * The value of the receiveAreaId association.
     * 
     * @return java.lang.String Return the value of the receiveAreaId column.
     */
    public java.lang.String getReceiveAreaId() {
        return receiveAreaId;
    }

    /**
     * Set the value of the receiveAreaId.
     * 
     * @param receiveAreaId
     */
    public void setReceiveAreaId(java.lang.String newReceiveAreaId) {
        this.receiveAreaId = newReceiveAreaId;
    }

    /**
     * The value of the orderTime association.
     * 
     * @return java.util.Date Return the value of the orderTime column.
     */
    public java.util.Date getOrderTime() {
        return orderTime;
    }

    /**
     * Set the value of the orderTime.
     * 
     * @param orderTime
     */
    public void setOrderTime(java.util.Date newOrderTime) {
        this.orderTime = newOrderTime;
    }

    /**
     * The value of the needInvoice association.
     * 
     * @return java.lang.Long Return the value of the needInvoice column.
     */
    public java.lang.Long getNeedInvoice() {
        return needInvoice;
    }

    /**
     * Set the value of the needInvoice.
     * 
     * @param needInvoice
     */
    public void setNeedInvoice(java.lang.Long newNeedInvoice) {
        this.needInvoice = newNeedInvoice;
    }

    /**
     * The value of the confirmerNo association.
     * 
     * @return java.lang.String Return the value of the confirmerNo column.
     */
    public java.lang.String getConfirmerNo() {
        return confirmerNo;
    }

    /**
     * Set the value of the confirmerNo.
     * 
     * @param confirmerNo
     */
    public void setConfirmerNo(java.lang.String newConfirmerNo) {
        this.confirmerNo = newConfirmerNo;
    }

    /**
     * The value of the confirmerName association.
     * 
     * @return java.lang.String Return the value of the confirmerName column.
     */
    public java.lang.String getConfirmerName() {
        return confirmerName;
    }

    /**
     * Set the value of the confirmerName.
     * 
     * @param confirmerName
     */
    public void setConfirmerName(java.lang.String newConfirmerName) {
        this.confirmerName = newConfirmerName;
    }

    /**
     * The value of the confirmTime association.
     * 
     * @return java.util.Date Return the value of the confirmTime column.
     */
    public java.util.Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * Set the value of the confirmTime.
     * 
     * @param confirmTime
     */
    public void setConfirmTime(java.util.Date newConfirmTime) {
        this.confirmTime = newConfirmTime;
    }

    /**
     * The value of the statusConfirm association.
     * 
     * @return java.lang.String Return the value of the statusConfirm column.
     */
    public java.lang.String getStatusConfirm() {
        return statusConfirm;
    }

    /**
     * Set the value of the statusConfirm.
     * 
     * @param statusConfirm
     */
    public void setStatusConfirm(java.lang.String newStatusConfirm) {
        this.statusConfirm = newStatusConfirm;
    }

    /**
     * The value of the statusPay association.
     * 
     * @return java.lang.String Return the value of the statusPay column.
     */
    public java.lang.String getStatusPay() {
        return statusPay;
    }

    /**
     * Set the value of the statusPay.
     * 
     * @param statusPay
     */
    public void setStatusPay(java.lang.String newStatusPay) {
        this.statusPay = newStatusPay;
    }

    /**
     * The value of the statusTotal association.
     * 
     * @return java.lang.String Return the value of the statusTotal column.
     */
    public java.lang.String getStatusTotal() {
        return statusTotal;
    }

    /**
     * Set the value of the statusTotal.
     * 
     * @param statusTotal
     */
    public void setStatusTotal(java.lang.String newStatusTotal) {
        this.statusTotal = newStatusTotal;
    }

    /**
     * The value of the clientServiceRemark association.
     * 
     * @return java.lang.String Return the value of the clientServiceRemark column.
     */
    public java.lang.String getClientServiceRemark() {
        return clientServiceRemark;
    }

    /**
     * Set the value of the clientServiceRemark.
     * 
     * @param clientServiceRemark
     */
    public void setClientServiceRemark(java.lang.String newClientServiceRemark) {
        this.clientServiceRemark = newClientServiceRemark;
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
     * The value of the ifPayOnArrival association.
     * 
     * @return java.lang.Long Return the value of the ifPayOnArrival column.
     */
    public java.lang.Long getIfPayOnArrival() {
        return ifPayOnArrival;
    }
    
    /**
     * Set the value of the ifPayOnArrival.
     * 
     * @param ifPayOnArrival
     */
    public void setIfPayOnArrival(java.lang.Long newIfPayOnArrival) {
        this.ifPayOnArrival = newIfPayOnArrival;
    }

    /**
     * The value of the payOnArrivalPayType association.
     * 
     * @return java.lang.String Return the value of the payOnArrivalPayType column.
     */
    public java.lang.String getPayOnArrivalPayType() {
        return payOnArrivalPayType;
    }

    /**
     * Set the value of the payOnArrivalPayType.
     * 
     * @param payOnArrivalPayType
     */
    public void setPayOnArrivalPayType(java.lang.String newPayOnArrivalPayType) {
        this.payOnArrivalPayType = newPayOnArrivalPayType;
    }

    /**
     * The value of the orderCategory association.
     * 
     * @return java.lang.String Return the value of the orderCategory column.
     */
    public java.lang.String getOrderCategory() {
        return orderCategory;
    }

    /**
     * Set the value of the orderCategory.
     * 
     * @param orderCategory
     */
    public void setOrderCategory(java.lang.String newOrderCategory) {
        this.orderCategory = newOrderCategory;
    }

    /**
     * The value of the orderRelatedOrigin association.
     * 
     * @return java.lang.String Return the value of the orderRelatedOrigin column.
     */
    public java.lang.String getOrderRelatedOrigin() {
        return orderRelatedOrigin;
    }

    /**
     * Set the value of the orderRelatedOrigin.
     * 
     * @param orderRelatedOrigin
     */
    public void setOrderRelatedOrigin(java.lang.String newOrderRelatedOrigin) {
        this.orderRelatedOrigin = newOrderRelatedOrigin;
    }

    /**
     * The value of the billType association.
     * 
     * @return java.lang.Long Return the value of the billType column.
     */
    public java.lang.Long getBillType() {
        return billType;
    }

    /**
     * Set the value of the billType.
     * 
     * @param billType
     */
    public void setBillType(java.lang.Long newBillType) {
        this.billType = newBillType;
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

    /**
     * The value of the orderCooperators association.
     * 
     * @return List<OrderCooperator> Return the value of the orderCooperators column.
     */
    public List<OrderCooperator> getOrderCooperators() {
        return orderCooperators;
    }

    /**
     * Set the value of the orderCooperators.
     * 
     * @param orderCooperators
     */
    public void setOrderCooperators(List<OrderCooperator> newOrderCooperators) {
        this.orderCooperators = newOrderCooperators;
    }

    /**
     * The value of the orderInvoices association.
     * 
     * @return List<OrderInvoice> Return the value of the orderInvoices column.
     */
    public List<OrderInvoice> getOrderInvoices() {
        return orderInvoices;
    }

    /**
     * Set the value of the orderInvoices.
     * 
     * @param orderInvoices
     */
    public void setOrderInvoices(List<OrderInvoice> newOrderInvoices) {
        this.orderInvoices = newOrderInvoices;
    }

    /**
     * The value of the orderInvoicePrints association.
     * 
     * @return List<OrderInvoicePrint> Return the value of the orderInvoicePrints column.
     */
    public List<OrderInvoicePrint> getOrderInvoicePrints() {
        return orderInvoicePrints;
    }

    /**
     * Set the value of the orderInvoicePrints.
     * 
     * @param orderInvoicePrints
     */
    public void setOrderInvoicePrints(List<OrderInvoicePrint> newOrderInvoicePrints) {
        this.orderInvoicePrints = newOrderInvoicePrints;
    }

    /**
     * The value of the orderItems association.
     * 
     * @return List<OrderItem> Return the value of the orderItems column.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Set the value of the orderItems.
     * 
     * @param orderItems
     */
    public void setOrderItems(List<OrderItem> newOrderItems) {
        this.orderItems = newOrderItems;
    }

    /**
     * The value of the orderItemAbns association.
     * 
     * @return List<OrderItemAbn> Return the value of the orderItemAbns column.
     */
    public List<OrderItemAbn> getOrderItemAbns() {
        return orderItemAbns;
    }

    /**
     * Set the value of the orderItemAbns.
     * 
     * @param orderItemAbns
     */
    public void setOrderItemAbns(List<OrderItemAbn> newOrderItemAbns) {
        this.orderItemAbns = newOrderItemAbns;
    }

    /**
     * The value of the orderItemVirtuals association.
     * 
     * @return List<OrderItemVirtual> Return the value of the orderItemVirtuals column.
     */
    public List<OrderItemVirtual> getOrderItemVirtuals() {
        return orderItemVirtuals;
    }

    /**
     * Set the value of the orderItemVirtuals.
     * 
     * @param orderItemVirtuals
     */
    public void setOrderItemVirtuals(List<OrderItemVirtual> newOrderItemVirtuals) {
        this.orderItemVirtuals = newOrderItemVirtuals;
    }

    /**
     * The value of the orderPays association.
     * 
     * @return List<OrderPay> Return the value of the orderPays column.
     */
    public List<OrderPay> getOrderPays() {
        return orderPays;
    }

    /**
     * Set the value of the orderPays.
     * 
     * @param orderPays
     */
    public void setOrderPays(List<OrderPay> newOrderPays) {
        this.orderPays = newOrderPays;
    }

    public String getRefOrderNo() {
        return refOrderNo;
    }

    public void setRefOrderNo(String refOrderNo) {
        this.refOrderNo = refOrderNo;
    }

    public long getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(long refOrderId) {
        this.refOrderId = refOrderId;
    }

    /**
     * The value of the orderRetChgItems association.
     * 
     * @return List<OrderRetChgItem> Return the value of the orderRetChgItems column.
     */
    public List<OrderRetChgItem> getOrderRetChgItems() {
        return orderRetChgItems;
    }

    /**
     * Set the value of the orderRetChgItems.
     * 
     * @param orderRetChgItems
     */
    public void setOrderRetChgItems(List<OrderRetChgItem> newOrderRetChgItems) {
        this.orderRetChgItems = newOrderRetChgItems;
    }

    /**
     * The value of the orderStatusLogs association.
     * 
     * @return List<OrderStatusLog> Return the value of the orderStatusLogs column.
     */
    public List<OrderStatusLog> getOrderStatusLogs() {
        return orderStatusLogs;
    }

    /**
     * Set the value of the orderStatusLogs.
     * 
     * @param orderStatusLogs
     */
    public void setOrderStatusLogs(List<OrderStatusLog> newOrderStatusLogs) {
        this.orderStatusLogs = newOrderStatusLogs;
    }

    /**
     * The value of the orderSubs association.
     * 
     * @return List<OrderSub> Return the value of the orderSubs column.
     */
    public List<OrderSub> getOrderSubs() {
        return orderSubs;
    }

    /**
     * Set the value of the orderSubs.
     * 
     * @param orderSubs
     */
    public void setOrderSubs(List<OrderSub> newOrderSubs) {
        this.orderSubs = newOrderSubs;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getDiscountTransport() {
        return discountTransport;
    }

    public void setDiscountTransport(BigDecimal discountTransport) {
        this.discountTransport = discountTransport;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getTotalPromo() {
        return totalPromo;
    }

    public void setTotalPromo(BigDecimal totalPromo) {
        this.totalPromo = totalPromo;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalGivePoints() {
        return totalGivePoints;
    }

    public void setTotalGivePoints(BigDecimal totalGivePoints) {
        this.totalGivePoints = totalGivePoints;
    }

    public java.lang.Long getIfNeedRefund() {
        return ifNeedRefund;
    }

    public void setIfNeedRefund(java.lang.Long ifNeedRefund) {
        this.ifNeedRefund = ifNeedRefund;
    }

    public java.util.Date getOrderTimeFrom() {
        return orderTimeFrom;
    }

    public void setOrderTimeFrom(java.util.Date orderTimeFrom) {
        this.orderTimeFrom = orderTimeFrom;
    }

    public java.util.Date getOrderTimeTo() {
        return orderTimeTo;
    }

    public void setOrderTimeTo(java.util.Date orderTimeTo) {
        this.orderTimeTo = orderTimeTo;
    }

    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus;
    }

    public java.lang.Long getIfBlackListMember() {
        return ifBlackListMember;
    }

    public void setIfBlackListMember(java.lang.Long ifBlackListMember) {
        this.ifBlackListMember = ifBlackListMember;
    }

    public java.lang.Long getIfPriviledgedMember() {
        return ifPriviledgedMember;
    }

    public void setIfPriviledgedMember(java.lang.Long ifPriviledgedMember) {
        this.ifPriviledgedMember = ifPriviledgedMember;
    }

    public java.lang.Long getIfWarnOrder() {
        return ifWarnOrder;
    }

    public void setIfWarnOrder(java.lang.Long ifWarnOrder) {
        this.ifWarnOrder = ifWarnOrder;
    }

    public boolean isCanReturn() {
        return canReturn;
    }

    public void setCanReturn(boolean canReturn) {
        this.canReturn = canReturn;
    }

    public java.lang.String getClientRemark() {
        return clientRemark;
    }

    public void setClientRemark(java.lang.String clientRemark) {
        this.clientRemark = clientRemark;
    }

    public BigDecimal getAmountUp() {
        return amountUp;
    }

    public void setAmountUp(BigDecimal amountUp) {
        this.amountUp = amountUp;
    }

    public BigDecimal getAmountDown() {
        return amountDown;
    }

    public void setAmountDown(BigDecimal amountDown) {
        this.amountDown = amountDown;
    }

    public java.lang.String getChgOurOrderNo() {
        return chgOurOrderNo;
    }

    public void setChgOurOrderNo(java.lang.String chgOurOrderNo) {
        this.chgOurOrderNo = chgOurOrderNo;
    }

    public List<OrderPayMode> getOrderPayModes() {
        return orderPayModes;
    }

    public void setOrderPayModes(List<OrderPayMode> orderPayModes) {
        this.orderPayModes = orderPayModes;
    }

    public java.lang.String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(java.lang.String batchNum) {
        this.batchNum = batchNum;
    }

    public boolean isHadCreateRefuse() {
        return hadCreateRefuse;
    }

    public void setHadCreateRefuse(boolean hadCreateRefuse) {
        this.hadCreateRefuse = hadCreateRefuse;
    }

    public java.lang.Long getRemindSent() {
        return remindSent;
    }

    public void setRemindSent(java.lang.Long remindSent) {
        this.remindSent = remindSent;
    }

    public java.lang.String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(java.lang.String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public java.lang.String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    public java.lang.String getMemberCardLevel() {
        return memberCardLevel;
    }

    public void setMemberCardLevel(java.lang.String memberCardLevel) {
        this.memberCardLevel = memberCardLevel;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public List<OrderPay> getOriginalOrderPayList() {
        return originalOrderPayList;
    }

    public void setOriginalOrderPayList(List<OrderPay> originalOrderPayList) {
        this.originalOrderPayList = originalOrderPayList;
    }

    public java.lang.String getImmigrationVersion() {
        return immigrationVersion;
    }

    public void setImmigrationVersion(java.lang.String immigrationVersion) {
        this.immigrationVersion = immigrationVersion;
    }

    public java.util.Date getFinishTimeFrom() {
        return finishTimeFrom;
    }

    public void setFinishTimeFrom(java.util.Date finishTimeFrom) {
        this.finishTimeFrom = finishTimeFrom;
    }

    public java.util.Date getFinishTimeTo() {
        return finishTimeTo;
    }

    public void setFinishTimeTo(java.util.Date finishTimeTo) {
        this.finishTimeTo = finishTimeTo;
    }

    public java.lang.String getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(java.lang.String memberInfo) {
        this.memberInfo = memberInfo;
    }

    public java.lang.String getCancelReasonNo() {
        return cancelReasonNo;
    }

    public void setCancelReasonNo(java.lang.String cancelReasonNo) {
        this.cancelReasonNo = cancelReasonNo;
    }

    public String getSourceOrderNo() {
        return sourceOrderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo;
    }

	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	/**
	 * @return the refundType
	 */
	public String getRefundType() {
		return refundType;
	}

	/**
	 * @param refundType the refundType to set
	 */
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	/**
	 * @return the sellerMessage
	 */
	public String getSellerMessage() {
		return sellerMessage;
	}

	/**
	 * @param sellerMessage the sellerMessage to set
	 */
	public void setSellerMessage(String sellerMessage) {
		this.sellerMessage = sellerMessage;
	}

	/**
	 * @return the isSuspension
	 */
	public Integer getIsSuspension() {
		return isSuspension;
	}

	/**
	 * @param isSuspension the isSuspension to set
	 */
	public void setIsSuspension(Integer isSuspension) {
		this.isSuspension = isSuspension;
	}
	
	/**
	 * @return the isMerge
	 */
	public Integer getIsMerge() {
		return isMerge;
	}

	/**
	 * @param isMerge the isMerge to set
	 */
	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}
	
	/**
	 * @return the isSplit
	 */
	public Integer getIsSplit() {
		return isSplit;
	}

	/**
	 * @param isSplit the isSplit to set
	 */
	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}
	
	/**
	 * @return the salesclerkNo
	 */
	public String getSalesclerkNo() {
		return salesclerkNo;
	}

	/**
	 * @param salesclerkNo the salesclerkNo to set
	 */
	public void setSalesclerkNo(String salesclerkNo) {
		this.salesclerkNo = salesclerkNo;
	}

	/**
	 * @return the isBarter
	 */
	public Integer getIsBarter() {
		return isBarter;
	}

	/**
	 * @param isBarter the isBarter to set
	 */
	public void setIsBarter(Integer isBarter) {
		this.isBarter = isBarter;
	}

	/**
	 * @return the orderPromotions
	 */
	public List<OrderPromotion> getOrderPromotions() {
		return orderPromotions;
	}

	/**
	 * @param orderPromotions the orderPromotions to set
	 */
	public void setOrderPromotions(List<OrderPromotion> orderPromotions) {
		this.orderPromotions = orderPromotions;
	}

	public BigDecimal getTotalPointAmount() {
		return totalPointAmount;
	}

	public void setTotalPointAmount(BigDecimal totalPointAmount) {
		this.totalPointAmount = totalPointAmount;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getIsCommission() {
		return isCommission;
	}

	public void setIsCommission(Integer isCommission) {
		this.isCommission = isCommission;
	}


	public String getOrderNoP() {
		return orderNoP;
	}

	public void setOrderNoP(String orderNoP) {
		this.orderNoP = orderNoP;
	}

	public Long getIdOrderP() {
		return idOrderP;
	}

	public void setIdOrderP(Long idOrderP) {
		this.idOrderP = idOrderP;
	}

    public String getShipStoreName() {
        return shipStoreName;
    }

    public void setShipStoreName(String shipStoreName) {
        this.shipStoreName = shipStoreName;
    }

	public java.lang.String getPerformStoreCode() {
		return performStoreCode;
	}

	public void setPerformStoreCode(java.lang.String performStoreCode) {
		this.performStoreCode = performStoreCode;
	}

	public BigDecimal getSalesclerkPerform() {
		return salesclerkPerform;
	}

	public void setSalesclerkPerform(BigDecimal salesclerkPerform) {
		this.salesclerkPerform = salesclerkPerform;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public java.lang.String getSaleStoreCode() {
		return saleStoreCode;
	}

	public void setSaleStoreCode(java.lang.String saleStoreCode) {
		this.saleStoreCode = saleStoreCode;
	}

	public java.lang.String getSaleCompanyCode() {
		return saleCompanyCode;
	}

	public void setSaleCompanyCode(java.lang.String saleCompanyCode) {
		this.saleCompanyCode = saleCompanyCode;
	}

	public java.lang.String getSaleFranchiseeCode() {
		return saleFranchiseeCode;
	}

	public void setSaleFranchiseeCode(java.lang.String saleFranchiseeCode) {
		this.saleFranchiseeCode = saleFranchiseeCode;
	}

	public Integer getTotalProductCount() {
		return totalProductCount;
	}

	public void setTotalProductCount(Integer totalProductCount) {
		this.totalProductCount = totalProductCount;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * @return the clientServiceRemarkFlag
	 */
	public Integer getClientServiceRemarkFlag() {
		return clientServiceRemarkFlag;
	}

	/**
	 * @param clientServiceRemarkFlag the clientServiceRemarkFlag to set
	 */
	public void setClientServiceRemarkFlag(Integer clientServiceRemarkFlag) {
		this.clientServiceRemarkFlag = clientServiceRemarkFlag;
	}
}