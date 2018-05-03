package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ibm.sc.model.shipping.SelfTakePoint;

/**
 * 
 * This class is used to represent available cl_category in the database.</p>
 * @author bu zhi na wei da ye 
 * 
 */
@Entity
@Table(name = "ORDER_MAIN")
@SecondaryTables({ @SecondaryTable(name = "ORDER_SUB", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "ID_ORDER") }),
        @SecondaryTable(name = "ORDER_ITEM", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "ID_ORDER") }),
        @SecondaryTable(name = "ORDER_PAY", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "ID_ORDER") }) }

)
public class OrderSearch implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public OrderSearch() {

    }

    /**
     * 7参构造器
     * 
     * @param orderMain
     * @param orderSub
     * @param orderItem
     * @param orderPay
     * @param transportArea 不用时可传入null
     * @param distributeAddress 不用时可传入null
     * @param selfTakePoint 不用时可传入null
     */
    public OrderSearch(OrderMain orderMain, OrderSub orderSub, OrderItem orderItem, OrderPay orderPay,
            TransportArea transportArea, DistributeAddress distributeAddress, SelfTakePoint selfTakePoint,IncludeOrNot includeOrNot,OrderPayMode orderPayMode) {

        // 调用4参构造器
        this(orderMain, orderSub, orderItem, orderPay, orderPayMode);

        // transportArea
        if (null != transportArea) {
            this.transportAreaId = transportArea.getId();
            this.areaName = transportArea.getAreaName();
        }

        // distributeAddress
        if (null != distributeAddress) {
            this.state = distributeAddress.getState();
            this.city = distributeAddress.getCity();
            this.county = distributeAddress.getCounty();
            this.street = distributeAddress.getStreet();
        }

        // selfTakePoint
        if (null != selfTakePoint) {
            this.selfTakePointId = selfTakePoint.getId();
            this.pointDeliverPartnerId = selfTakePoint.getPointDeliverPartnerId();
            this.pointName = selfTakePoint.getPointName();
        }

        // includeOrNot
        if (null != includeOrNot) {
        	this.isCommodityCode =includeOrNot.getIsCommodityCode();
            this.isSkuNo = includeOrNot.getIsSkuNo();
            this.isRemark =includeOrNot.getIsRemark();
            this.isClientRemark=includeOrNot.getIsClientRemark();
            this.isCommodityName =includeOrNot.getIsCommodityName();
            this.isSellerMessage=includeOrNot.getIsSellerMessage();
            this.isAddressDetail=includeOrNot.getIsAddressDetail();
            this.skuNumMin =includeOrNot.getSkuNumMin();
            this.skuNumMax =includeOrNot.getSkuNumMax();
        }
    }

    /**
     * 4参构造器
     * 
     * @param orderMain
     * @param orderSub
     * @param orderItem
     * @param orderPay
     */
    public OrderSearch(OrderMain orderMain, OrderSub orderSub, OrderItem orderItem, OrderPay orderPay,OrderPayMode orderPayMode) {

        this.id = orderMain.getId();
        this.orderNo = orderMain.getOrderNo();
        this.memberNo = orderMain.getMemberNo();
        this.orderTime = orderMain.getOrderTime();
        this.finishTime = orderMain.getFinishTime();
        this.orderSource = orderMain.getOrderSource();
        this.orderType = orderMain.getOrderType();
        this.merchantNo = orderMain.getMerchantNo();
        this.weight = orderMain.getWeight();
        this.ifPayOnArrival = orderMain.getIfPayOnArrival();
        this.refundType = orderMain.getRefundType();
        this.deliveryType = orderMain.getDeliveryType();
        this.sellerMessage = orderMain.getSellerMessage();
        this.isSuspension = orderMain.getIsSuspension();
        this.isMerge = orderMain.getIsMerge();
        this.isSplit = orderMain.getIsSplit();
        this.isBarter = orderMain.getIsBarter();
        this.salesClerkNo = orderMain.getSalesclerkNo();
        
        this.orderRelatedOrigin = orderMain.getOrderRelatedOrigin();
        this.confirmerName = orderMain.getConfirmerName();
        this.orderTimeFrom = orderMain.getOrderTimeFrom();
        this.orderTimeTo = orderMain.getOrderTimeTo();
        this.finishTimeFrom = orderMain.getFinishTimeFrom();
        this.finishTimeTo = orderMain.getFinishTimeTo();

        this.finishStatus = orderMain.getFinishStatus();

        this.orderCategory = orderMain.getOrderCategory();
        this.aliasOrderNo = orderMain.getAliasOrderNo();
        this.confirmTime = orderMain.getConfirmTime();
        this.remark = orderMain.getRemark();
        this.updatedBy = orderMain.getUpdatedBy();
        this.memberInfo = orderMain.getMemberInfo();
        this.merchantType = orderMain.getMerchantType();
        this.clientRemark =orderMain.getClientRemark();
        // order Sub
        this.distributeType = orderSub.getDistributeType();
        this.addressCode = orderSub.getAddressCode();
        this.addressDetail = orderSub.getAddressDetail();
        this.deliveryMerchantNo = orderSub.getDeliveryMerchantNo();
        this.deliveryMerchantName = orderSub.getDeliveryMerchantName();
        this.orderSubRelatedOrigin = orderSub.getOrderSubRelatedOrigin();
        this.deliveredCity = orderSub.getDeliveredCity();
        this.deliveredProvice = orderSub.getDeliveredProvince();
        this.deliveredCounty = orderSub.getDeliveredCounty();
       
        this.logisticsStatus = orderSub.getLogisticsStatus();
        this.selfFetchAddress = orderSub.getSelfFetchAddress();
        this.mobPhoneNum = orderSub.getMobPhoneNum();
        this.phoneNum = orderSub.getPhoneNum();
        this.invoicePrinted = orderSub.getInvoicePrinted();
        this.orderSubNo = orderSub.getOrderSubNo();
        this.orderSubId = orderSub.getId();
        this.receiverInfo = orderSub.getReceiverInfo();
        this.outStoreTime = orderSub.getOutStoreTime();
        // order Item
        this.productCategory = orderItem.getProductCategory();
        this.commodityName = orderItem.getCommodityName();
        this.commodityCode = orderItem.getCommodityCode();
        this.skuNo = orderItem.getSkuNo();
        this.supplierCode = orderItem.getSupplierCode();
        this.productYearStart = orderItem.getProductYearStart();
        this.productYearEnd = orderItem.getProductYearEnd();

        // order Pay
        this.payCode = orderPay.getPayCode();
        this.payNo = orderPay.getPayNo();

        //支付时间注入
        this.payTimeFrom=orderPay.getPayTimeFrom();
        this.payTimeTo=orderPay.getPayTimeTo();
        
        this.payModeCode =orderPayMode.getPayModeCode();
        this.dateUpdated =orderPayMode.getDateUpdated();
        this.payStatus =orderPayMode.getPayStatus();
        //END YUSL 1/12
        this.totalProductPrice = orderMain.getTotalProductPrice();
        this.discountTotal = orderMain.getDiscountTotal();
        this.transportFee = orderMain.getTransportFee();
        this.discountTransport = orderMain.getDiscountTransport();

        this.payName = orderPay.getPayName();
        this.payAmount = orderPay.getPayAmount();

        this.userName = orderSub.getUserName();

        this.customerName = orderMain.getCustomerName();
        this.ifPriviledgedMember = orderMain.getIfPriviledgedMember(); // 是否是大客户
        this.ifWarnOrder = orderMain.getIfWarnOrder(); // 是否是预警告订单
        this.ifNeedRefund = orderMain.getIfNeedRefund(); // 是否需退款
        this.ifBlackListMember = orderMain.getIfBlackListMember(); // 是否是黑名单

        // 支付方式列表
        this.payNameList = new ArrayList<String>();

        // 支付金额列表
        this.payAmountList = new ArrayList<BigDecimal>();
        this.customerPhone = orderMain.getCustomerPhone();
        this.createdBy = orderMain.getCreatedBy();
        this.checkCode = orderSub.getCheckCode();
        this.statusConfirm = orderMain.getStatusConfirm();
        this.statusPay = orderMain.getStatusPay();
        this.statusTotal = orderMain.getStatusTotal();
        this.logisticsOutNo = orderSub.getLogisticsOutNo();
        this.retPreStartTime = orderSub.getRetPreStartTime();
        this.retPreEndTime = orderSub.getRetPreEndTime();
        this.amountDown = orderMain.getAmountDown();
        this.amountUp = orderMain.getAmountUp();
        this.chgOurOrderNo = orderMain.getChgOurOrderNo();
        this.warehouseNo =orderItem.getWarehouseNo();
    }
    private String isCommodityCode;
    private String isSkuNo;
    private String isRemark;
	private String isCommodityName;
	private String isClientRemark;
	private String isSellerMessage;
	private String isAddressDetail;

	private Integer skuNumMin;
	private Integer skuNumMax;
	// OrderMain
    @Id
    @Column(table = "ORDER_MAIN", name = "id")
    private java.lang.Long id;
    @Column(table = "ORDER_MAIN", name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(table = "ORDER_MAIN", name = "MEMBER_NO")
    private java.lang.String memberNo;
    @Column(table = "ORDER_MAIN", name = "ORDER_TIME")
    private java.util.Date orderTime;
    @Column(table = "ORDER_MAIN", name = "FINISH_TIME")
    private java.util.Date finishTime;
    @Column(table = "ORDER_MAIN", name = "ORDER_SOURCE")
    private java.lang.String orderSource;
    @Column(table = "ORDER_MAIN", name = "ORDER_TYPE")
    private java.lang.String orderType;
    @Column(table = "ORDER_MAIN", name = "MERCHANT_NO")
    private java.lang.String merchantNo;

    @Column(table = "ORDER_MAIN", name = "FINISH_USER_NO")
    private java.lang.String finishUserNo;
    
    //配送方式
    @Column(table = "ORDER_MAIN", name = "DELIVERY_TYPE")
    private String deliveryType;
    
    //退款方式
    @Column(table = "ORDER_MAIN", name = "REFUND_TYPE")
    private String refundType;
    
    //卖家留言
    @Column(table = "ORDER_MAIN", name = "SELLER_MESSAGE")
    private String sellerMessage;
    
    //是否挂起
    @Column(table = "ORDER_MAIN", name = "IS_SUSPENSION")
    private Integer isSuspension;
    
    //是否合并单
    @Column(table = "ORDER_MAIN", name = "IS_MERGE")
    private Integer isMerge;
    
    //是否拆单
    @Column(table = "ORDER_MAIN", name = "IS_SPLIT")
    private Integer isSplit;
    
    //是否换货单
    @Column(table = "ORDER_MAIN", name = "IS_BARTER")
    private Integer isBarter;
    
    //下单营业员编号
    @Column(table = "ORDER_MAIN", name = "SALESCLERK_NO")
    private java.lang.String salesClerkNo;

	@Column(table = "ORDER_MAIN", name = "ORDER_RELATED_ORIGIN")
    private java.lang.String orderRelatedOrigin;

    @Column(table = "ORDER_MAIN", name = "CONFIRMER_NAME")
    private java.lang.String confirmerName;

    @Column(table = "ORDER_MAIN", name = "TOTAL_PRODUCT_PRICE")
    private BigDecimal totalProductPrice;
    @Column(table = "ORDER_MAIN", name = "DISCOUNT_TOTAL")
    private BigDecimal discountTotal;
    @Column(table = "ORDER_MAIN", name = "TRANSPORT_FEE")
    private BigDecimal transportFee;
    @Column(table = "ORDER_MAIN", name = "DISCOUNT_TRANSPORT")
    private BigDecimal discountTransport;
    @Column(table = "ORDER_MAIN", name = "UPDATED_BY")
    private java.lang.String updatedBy;

    // add for search status condition
    @Column(table = "ORDER_MAIN", name = "BILL_TYPE")
    private java.lang.Long billType;// 单据类型 1：正向订单 ； -1：逆向订单

    // added for list show
    @Column(table = "ORDER_MAIN", name = "DATE_CREATED")
    private java.util.Date dateCreated;
    @Column(table = "ORDER_MAIN", name = "TOTAL_PAY_AMOUNT")
    private BigDecimal totalPayAmount;
    @Column(table = "ORDER_MAIN", name = "CUSTOMER_NAME")
    private java.lang.String customerName;

    @Column(table = "ORDER_MAIN", name = "CUSTOMER_PHONE")
    private java.lang.String customerPhone;

    @Column(table = "ORDER_MAIN", name = "ORDER_CATEGORY")
    private java.lang.String orderCategory;// 退换货类型

    @Column(table = "ORDER_MAIN", name = "CLIENT_SERVICE_REMARK")
    private java.lang.String clientServiceRemark;
    @Column(table = "ORDER_MAIN", name = "CLIENT_REMARK")
    private java.lang.String clientRemark;
    @Column(table = "ORDER_MAIN", name = "REMARK")
    private java.lang.String remark;

    @Column(table = "ORDER_MAIN", name = "IF_PRIVILEDGED_MEMBER")
    private java.lang.Long ifPriviledgedMember;
    @Column(table = "ORDER_MAIN", name = "IF_WARN_ORDER")
    private java.lang.Long ifWarnOrder;
    @Column(table = "ORDER_MAIN", name = "IF_BLACKLIST_MEMBER")
    private java.lang.Long ifBlackListMember;// 是否黑名单会员
    @Column(table = "ORDER_MAIN", name = "ALIAS_ORDER_NO")
    private java.lang.String aliasOrderNo;

    @Column(table = "ORDER_MAIN", name = "IF_NEED_REFUND")
    private java.lang.Long ifNeedRefund;// 逆向订单：是否需退款
    @Column(table = "ORDER_MAIN", name = "CREATED_BY")
    private java.lang.String createdBy;
    @Column(table = "ORDER_MAIN", name = "IF_PAY_ON_ARRIVAL")
    private Long ifPayOnArrival;
    @Column(table = "ORDER_MAIN", name = "WEIGHT")
    private BigDecimal weight;
    @Column(table = "ORDER_MAIN", name = "CONFIRM_TIME")
    private java.util.Date confirmTime;
    @Column(table = "ORDER_MAIN", name = "CHGOUT_ORDER_NO")
    private java.lang.String chgOurOrderNo;// 换货意向单所产生的出库单

    // OrderSub
    @Column(table = "ORDER_SUB", name = "ID")
    private java.lang.Long orderSubId;
    @Column(table = "ORDER_SUB", name = "DISTRIBUTE_TYPE")
    private java.lang.String distributeType;
    @Column(table = "ORDER_SUB", name = "ADDRESS_CODE")
    private java.lang.String addressCode;
    @Column(table = "ORDER_SUB", name = "ADDRESS_DETAIL")
    private java.lang.String addressDetail;
    @Column(table = "ORDER_SUB", name = "ORDER_SUB_RELATED_ORIGIN")
    private java.lang.String orderSubRelatedOrigin;
    @Column(table = "ORDER_SUB", name = "DELIVERED_PROVINCE")
    private java.lang.String deliveredProvice;
    @Column(table = "ORDER_SUB", name = "DELIVERED_CITY")
    private java.lang.String deliveredCity;
    @Column(table = "ORDER_SUB", name = "DELIVERED_COUNTY")
    private java.lang.String deliveredCounty;

    @Column(table = "ORDER_SUB", name = "LOGISTICS_STATUS")
    private java.lang.String logisticsStatus;

    @Column(table = "ORDER_SUB", name = "USER_NAME")
    private java.lang.String userName;

    @Column(table = "ORDER_SUB", name = "SELF_FETCH_ADDRESS")
    private java.lang.String selfFetchAddress;

    @Column(table = "ORDER_SUB", name = "MOB_PHONE_NUM")
    private java.lang.String mobPhoneNum;

    @Column(table = "ORDER_SUB", name = "PHONE_NUM")
    private java.lang.String phoneNum;

    @Column(table = "ORDER_SUB", name = "INVOICE_PRINTED")
    private java.lang.Long invoicePrinted;

    @Column(table = "ORDER_SUB", name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;

    @Column(table = "ORDER_SUB", name = "OUT_STORE_TIME")
    private java.util.Date outStoreTime; // 出库时间

    @Column(table = "ORDER_SUB", name = "DELIVERY_MERCHANT_NO")
    private java.lang.String deliveryMerchantNo;

    @Column(table = "ORDER_SUB", name = "DELIVERY_MERCHANT_NAME")
    private java.lang.String deliveryMerchantName;

    @Column(table = "ORDER_SUB", name = "CHECK_CODE")
    private java.lang.String checkCode;
    @Column(table = "ORDER_MAIN", name = "STATUS_TOTAL")
    private java.lang.String statusTotal;
    @Column(table = "ORDER_MAIN", name = "STATUS_PAY")
    private java.lang.String statusPay;
    @Column(table = "ORDER_MAIN", name = "STATUS_CONFIRM")
    private java.lang.String statusConfirm; // 审核状态
    @Column(table = "ORDER_MAIN",name = "MERCHANT_TYPE")
    private java.lang.String merchantType;

    @Column(table = "ORDER_SUB", name = "LOGISTICS_OUT_NO")
    private java.lang.String logisticsOutNo; // 物流订单外部编号(运单号)
    
    @Column(table = "ORDER_SUB", name = "RET_PRE_START_TIME")
    private java.util.Date retPreStartTime; // 退货预约开始时间
    
    @Column(table = "ORDER_SUB", name = "RET_PRE_END_TIME")
    private java.util.Date retPreEndTime; // 退货预约结束时间

    // OrderItem
    @Column(table = "ORDER_ITEM", name = "PRODUCT_CATEGORY")
    private java.lang.String productCategory;
    @Column(table = "ORDER_ITEM", name = "COMMODITY_NAME")
    private java.lang.String commodityName;
    @Column(table = "ORDER_ITEM", name = "SUPPLIER_CODE")
    private java.lang.String supplierCode;
    @Column(table = "ORDER_ITEM", name = "SKU_NO")
    private java.lang.String skuNo;
    @Column(table = "ORDER_ITEM", name = "COMMODITY_CODE")
    private java.lang.String commodityCode;
    @Column(table = "ORDER_ITEM", name = "PRODUCT_YEAR")
    private Date productYear;
    @Column(table = "ORDER_ITEM", name = "WAREHOUSE_NO")
    private java.lang.String warehouseNo;

    @Transient
    private Date productYearStart;
    @Transient
    private Date productYearEnd;

    /*
     * //OrderItem
     * @Transient private java.lang.String productCategory;
     * @Transient private java.lang.String commodityName;
     * @Transient private java.lang.String supplierCode;
     */
    @Transient
    private List<String> orderNoList4OrderItem;
    @Transient
    private List<String> orderNoList4OrderPay;

    // OrderPay
    @Column(table = "ORDER_PAY", name = "PAY_CODE")
    private java.lang.String payCode;
    @Column(table = "ORDER_PAY", name = "PAY_NO")
    private java.lang.String payNo; //支付

    @Column(table = "ORDER_PAY", name = "PAY_Time")
    private java.util.Date payTime; //支付
    private java.lang.String payModeCode; //支付
    private java.util.Date dateUpdated; //支付
    private java.lang.Long  payStatus; //支付
    private java.lang.String  payModeName; //支付
    private BigDecimal  payAmount; //支付
    @Transient
    private java.lang.String payName;
    @Transient
    private BigDecimal payAmount2;

    // 订单开始日期和订单结束日期 搜索时用到
    @Transient
    private java.util.Date orderTimeFrom;
    @Transient
    private java.util.Date orderTimeTo;
    // 订单完成日期 搜索时用到 搜索时用到
    @Transient
    private java.util.Date finishTimeFrom;
    @Transient
    private java.util.Date finishTimeTo;
    //审核时间：搜索用到
    @Transient
    private java.util.Date confirmTimeFrom;
    @Transient
    private java.util.Date confirmTimeTo;

    // 支付完成日期 搜索时用到 
    @Transient
    private java.util.Date payTimeFrom;
    @Transient
    private java.util.Date payTimeTo;
    //END YUSL 1/12
    
    // 收货人信息
    @Transient
    private java.lang.String receiverInfo;
    // 会员信息
    @Transient
    private java.lang.String memberInfo;
    // 用来搜索时添加多个需退款判断条件
    @Transient
    private List<Long> ifNeedRefundList;

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

    // SM_TRANSPORT_AREA表中的AREA_NAME
    @Transient
    private String areaName;

    // distributeAddress类的属性
    @Transient
    private Long state;
    @Transient
    private Long city;
    @Transient
    private Long county;
    @Transient
    private Long street;

    @Transient
    private Long transportAreaId;

    /**
     * 配送类型名称
     */
    @Transient
    private String distributeTypeName;
    @Transient
    private String orderSourceName;
    @Transient
    private String statusTotalName;
    @Transient
    private String statusConfirmName;
    @Transient
    private String statusPayName;
    @Transient
    private String logisticsStatusName;
    
    @Transient
    private List<String> flags;

    
    

	public java.lang.String getPayModeName() {
		return payModeName;
	}

	public void setPayModeName(java.lang.String payModeName) {
		this.payModeName = payModeName;
	}


	public BigDecimal getPayAmount2() {
		return payAmount2;
	}

	public void setPayAmount2(BigDecimal payAmount2) {
		this.payAmount2 = payAmount2;
	}

	public java.lang.String getPayModeCode() {
		return payModeCode;
	}

	public void setPayModeCode(java.lang.String payModeCode) {
		this.payModeCode = payModeCode;
	}

	public java.util.Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(java.util.Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public java.lang.Long getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(java.lang.Long payStatus) {
		this.payStatus = payStatus;
	}

	public List<Long> getIfNeedRefundList() {
        return ifNeedRefundList;
    }

    public void setIfNeedRefundList(List<Long> ifNeedRefundList) {
        this.ifNeedRefundList = ifNeedRefundList;
    }

    public java.lang.String getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(java.lang.String receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public String getLogisticsStatusName() {
        return logisticsStatusName;
    }

    public void setLogisticsStatusName(String logisticsStatusName) {
        this.logisticsStatusName = logisticsStatusName;
    }

    public String getStatusPayName() {
        return statusPayName;
    }

    public void setStatusPayName(String statusPayName) {
        this.statusPayName = statusPayName;
    }

    public String getStatusConfirmName() {
        return statusConfirmName;
    }

    public void setStatusConfirmName(String statusConfirmName) {
        this.statusConfirmName = statusConfirmName;
    }

    public String getStatusTotalName() {
        return statusTotalName;
    }

    public void setStatusTotalName(String statusTotalName) {
        this.statusTotalName = statusTotalName;
    }

    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    // SelfTakePoint
    @Transient
    private Long selfTakePointId;
    @Transient
    private List<String> selfTakePointIdList;
    @Transient
    private Long pointDeliverPartnerId;
    @Transient
    private String pointName;

    @Transient
    private List<String> payNameList;

    @Transient
    private List<BigDecimal> payAmountList;

    @Transient
    private Map<String, BigDecimal> payNameAmountMap;

    @Transient
    private List<OrderPayInfo> orderPayInfoList;

    // 退款单关联原订单的退款完成时间
    @Transient
    private Date relatedFinishTime;

    /**
     * 处理状态的code
     */
    @Transient
    private String statusTotalCode;

    /**
     * 处理状态的code
     */
    @Transient
    private String statusConfirmCode;

    /**
     * 采购
     */
    @Transient
    private String purchase;

    /**
     * 订单明细集合
     */
    @Transient
    private List<OrderItem> orderItems;

    /**
     * 虚拟订单明细集合
     */
    @Transient
    private List<OrderItemVirtual> orderItemVirtuals;

    /**
     * 待支付时判断某些状态是否作不等条件查询 true: 当做不等条件查询 false:不当做不等条件查询
     */
    @Transient
    private Boolean isNotEqual4OrderNeedPay = false;

    /**
     * 供查询时需要根据多个statusTotal查询使用
     */
    @Transient
    private List<String> statusTotalList;

    /**
     * 供查询时需要根据多个logisticsStatus(物流状态)查询使用
     */
    @Transient
    private List<String> logisticsStatusList;
    /**
     * 供查询时需要根据多个payCode(支付方式)查询使用
     */
    @Transient
    private List<String> payCodeList;

    @Transient
    private List<String> orderCategoryList;

    // getters and setters
    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }

    public java.lang.String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(java.lang.String memberNo) {
        this.memberNo = memberNo;
    }

    public java.util.Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
    }

    public java.util.Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(java.util.Date finishTime) {
        this.finishTime = finishTime;
    }

    public java.lang.String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(java.lang.String orderSource) {
        this.orderSource = orderSource;
    }

    public java.lang.String getOrderType() {
        return orderType;
    }

    public void setOrderType(java.lang.String orderType) {
        this.orderType = orderType;
    }

    public java.lang.String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(java.lang.String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public java.lang.String getDistributeType() {
        return distributeType;
    }

    public void setDistributeType(java.lang.String distributeType) {
        this.distributeType = distributeType;
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

    public java.lang.String getPayCode() {
        return payCode;
    }

    public void setPayCode(java.lang.String payCode) {
        this.payCode = payCode;
    }

    public java.lang.String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(java.lang.String statusPay) {
        this.statusPay = statusPay;
    }

    public java.lang.String getStatusTotal() {
        return statusTotal;
    }

    public void setStatusTotal(java.lang.String statusTotal) {
        this.statusTotal = statusTotal;
    }

    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public java.lang.String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }

    public java.lang.Long getBillType() {
        return billType;
    }

    public void setBillType(java.lang.Long billType) {
        this.billType = billType;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getCounty() {
        return county;
    }

    public void setCounty(Long county) {
        this.county = county;
    }

    public Long getStreet() {
        return street;
    }

    public void setStreet(Long street) {
        this.street = street;
    }

    public Long getTransportAreaId() {
        return transportAreaId;
    }

    public void setTransportAreaId(Long transportAreaId) {
        this.transportAreaId = transportAreaId;
    }

    public java.lang.String getOrderRelatedOrigin() {
        return orderRelatedOrigin;
    }

    public void setOrderRelatedOrigin(java.lang.String orderRelatedOrigin) {
        this.orderRelatedOrigin = orderRelatedOrigin;
    }

    public java.lang.String getStatusConfirm() {
        return statusConfirm;
    }

    public void setStatusConfirm(java.lang.String statusConfirm) {
        this.statusConfirm = statusConfirm;
    }

    public java.lang.String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(java.lang.String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public java.lang.String getConfirmerName() {
        return confirmerName;
    }

    public void setConfirmerName(java.lang.String confirmerName) {
        this.confirmerName = confirmerName;
    }

    public java.lang.String getFinishUserNo() {
        return finishUserNo;
    }

    public void setFinishUserNo(java.lang.String finishUserNo) {
        this.finishUserNo = finishUserNo;
    }

    public java.lang.String getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(java.lang.String orderCategory) {
        this.orderCategory = orderCategory;
    }

    public java.lang.String getClientServiceRemark() {
        return clientServiceRemark;
    }

    public void setClientServiceRemark(java.lang.String clientServiceRemark) {
        this.clientServiceRemark = clientServiceRemark;
    }

    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    public BigDecimal getDiscountTransport() {
        return discountTransport;
    }

    public void setDiscountTransport(BigDecimal discountTransport) {
        this.discountTransport = discountTransport;
    }

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getPayName() {
        return payName;
    }

    public void setPayName(java.lang.String payName) {
        this.payName = payName;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    public java.lang.String getSelfFetchAddress() {
        return selfFetchAddress;
    }

    public void setSelfFetchAddress(java.lang.String selfFetchAddress) {
        this.selfFetchAddress = selfFetchAddress;
    }

    public java.lang.String getMobPhoneNum() {
        return mobPhoneNum;
    }

    public void setMobPhoneNum(java.lang.String mobPhoneNum) {
        this.mobPhoneNum = mobPhoneNum;
    }

    public java.lang.String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(java.lang.String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public java.lang.Long getInvoicePrinted() {
        return invoicePrinted;
    }

    public void setInvoicePrinted(java.lang.Long invoicePrinted) {
        this.invoicePrinted = invoicePrinted;
    }

    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(java.lang.String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public java.lang.String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(java.lang.String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public java.lang.Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(java.lang.Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public Long getSelfTakePointId() {
        return selfTakePointId;
    }

    public void setSelfTakePointId(Long selfTakePointId) {
        this.selfTakePointId = selfTakePointId;
    }

    public Long getPointDeliverPartnerId() {
        return pointDeliverPartnerId;
    }

    public void setPointDeliverPartnerId(Long pointDeliverPartnerId) {
        this.pointDeliverPartnerId = pointDeliverPartnerId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public List<String> getPayNameList() {
        return payNameList;
    }

    public void setPayNameList(List<String> payNameList) {
        this.payNameList = payNameList;
    }

    public List<BigDecimal> getPayAmountList() {
        return payAmountList;
    }

    public void setPayAmountList(List<BigDecimal> payAmountList) {
        this.payAmountList = payAmountList;
    }

    public Map<String, BigDecimal> getPayNameAmountMap() {
        return payNameAmountMap;
    }

    public void setPayNameAmountMap(Map<String, BigDecimal> payNameAmountMap) {
        this.payNameAmountMap = payNameAmountMap;
    }

    public java.lang.Long getIfNeedRefund() {
        return ifNeedRefund;
    }

    public void setIfNeedRefund(java.lang.Long ifNeedRefund) {
        this.ifNeedRefund = ifNeedRefund;
    }

    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getIfPayOnArrival() {
        return ifPayOnArrival;
    }

    public void setIfPayOnArrival(Long ifPayOnArrival) {
        this.ifPayOnArrival = ifPayOnArrival;
    }

    public java.lang.String getDeliveryMerchantNo() {
        return deliveryMerchantNo;
    }

    public void setDeliveryMerchantNo(java.lang.String deliveryMerchantNo) {
        this.deliveryMerchantNo = deliveryMerchantNo;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public java.lang.String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(java.lang.String checkCode) {
        this.checkCode = checkCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public java.lang.String getDeliveryMerchantName() {
        return deliveryMerchantName;
    }

    public void setDeliveryMerchantName(java.lang.String deliveryMerchantName) {
        this.deliveryMerchantName = deliveryMerchantName;
    }

    public String getStatusTotalCode() {
        return statusTotalCode;
    }

    public void setStatusTotalCode(String statusTotalCode) {
        this.statusTotalCode = statusTotalCode;
    }

    public java.lang.String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(java.lang.String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public java.lang.String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(java.lang.String productCategory) {
        this.productCategory = productCategory;
    }

    public java.lang.String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(java.lang.String commodityName) {
        this.commodityName = commodityName;
    }

    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public List<String> getOrderNoList4OrderItem() {
        return orderNoList4OrderItem;
    }

    public void setOrderNoList4OrderItem(List<String> orderNoList) {
        this.orderNoList4OrderItem = orderNoList;
    }

    public java.lang.String getLogisticsOutNo() {
        return logisticsOutNo;
    }

    public void setLogisticsOutNo(java.lang.String logisticsOutNo) {
        this.logisticsOutNo = logisticsOutNo;
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

    public List<String> getOrderNoList4OrderPay() {
        return orderNoList4OrderPay;
    }

    public void setOrderNoList4OrderPay(List<String> orderNoList4OrderPay) {
        this.orderNoList4OrderPay = orderNoList4OrderPay;
    }

    public List<OrderItemVirtual> getOrderItemVirtuals() {
        return orderItemVirtuals;
    }

    public void setOrderItemVirtuals(List<OrderItemVirtual> orderItemVirtuals) {
        this.orderItemVirtuals = orderItemVirtuals;
    }

    public Boolean getIsNotEqual4OrderNeedPay() {
        return isNotEqual4OrderNeedPay;
    }

    public void setIsNotEqual4OrderNeedPay(Boolean isNotEqual4OrderNeedPay) {
        this.isNotEqual4OrderNeedPay = isNotEqual4OrderNeedPay;
    }

    public java.util.Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(java.util.Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public java.lang.String getChgOurOrderNo() {
        return chgOurOrderNo;
    }

    public void setChgOurOrderNo(java.lang.String chgOurOrderNo) {
        this.chgOurOrderNo = chgOurOrderNo;
    }

    public List<String> getStatusTotalList() {
        return statusTotalList;
    }

    public void setStatusTotalList(List<String> statusTotalList) {
        this.statusTotalList = statusTotalList;
    }

    public String getStatusConfirmCode() {
        return statusConfirmCode;
    }

    public void setStatusConfirmCode(String statusConfirmCode) {
        this.statusConfirmCode = statusConfirmCode;
    }

    public List<String> getOrderCategoryList() {
        return orderCategoryList;
    }

    public void setOrderCategoryList(List<String> orderCategoryList) {
        this.orderCategoryList = orderCategoryList;
    }

    public Date getRelatedFinishTime() {
        return relatedFinishTime;
    }

    public void setRelatedFinishTime(Date relatedFinishTime) {
        this.relatedFinishTime = relatedFinishTime;
    }

    public String getDistributeTypeName() {
        return distributeTypeName;
    }

    public void setDistributeTypeName(String distributeTypeName) {
        this.distributeTypeName = distributeTypeName;
    }

    public java.lang.String getOrderSubRelatedOrigin() {
        return orderSubRelatedOrigin;
    }

    public void setOrderSubRelatedOrigin(java.lang.String orderSubRelatedOrigin) {
        this.orderSubRelatedOrigin = orderSubRelatedOrigin;
    }

    public List<String> getSelfTakePointIdList() {
        return selfTakePointIdList;
    }

    public void setSelfTakePointIdList(List<String> selfTakePointIdList) {
        this.selfTakePointIdList = selfTakePointIdList;
    }

    public java.lang.Long getIfBlackListMember() {
        return ifBlackListMember;
    }

    public void setIfBlackListMember(java.lang.Long ifBlackListMember) {
        this.ifBlackListMember = ifBlackListMember;
    }

    public List<OrderPayInfo> getOrderPayInfoList() {
        return orderPayInfoList;
    }

    public void setOrderPayInfoList(List<OrderPayInfo> orderPayInfoList) {
        this.orderPayInfoList = orderPayInfoList;
    }

    public List<String> getLogisticsStatusList() {
        return logisticsStatusList;
    }

    public void setLogisticsStatusList(List<String> logisticsStatusList) {
        this.logisticsStatusList = logisticsStatusList;
    }

    public List<String> getPayCodeList() {
        return payCodeList;
    }

    public void setPayCodeList(List<String> payCodeList) {
        this.payCodeList = payCodeList;
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

    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(java.lang.String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public java.lang.String getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(java.lang.String memberInfo) {
        this.memberInfo = memberInfo;
    }
    
    public java.lang.String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(java.lang.String merchantType) {
        this.merchantType = merchantType;
    }
    
    public java.util.Date getOutStoreTime() {
        return outStoreTime;
    }

    public void setOutStoreTime(java.util.Date outStoreTime) {
        this.outStoreTime = outStoreTime;
    }

    public java.lang.String getPayNo() {
        return payNo;
    }

    public void setPayNo(java.lang.String payNo) {
        this.payNo = payNo;
    }

    public java.util.Date getConfirmTimeFrom() {
        return confirmTimeFrom;
    }

    public void setConfirmTimeFrom(java.util.Date confirmTimeFrom) {
        this.confirmTimeFrom = confirmTimeFrom;
    }

    public java.util.Date getConfirmTimeTo() {
        return confirmTimeTo;
    }

    public void setConfirmTimeTo(java.util.Date confirmTimeTo) {
        this.confirmTimeTo = confirmTimeTo;
    }
	/**
	 * @return the clientRemark
	 */
	public java.lang.String getClientRemark() {
		return clientRemark;
	}

	/**
	 * @param clientRemark the clientRemark to set
	 */
	public void setClientRemark(java.lang.String clientRemark) {
		this.clientRemark = clientRemark;
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

	public java.util.Date getPayTimeFrom() {
		return payTimeFrom;
	}

	public void setPayTimeFrom(java.util.Date payTimeFrom) {
		this.payTimeFrom = payTimeFrom;
	}

	public java.util.Date getPayTimeTo() {
		return payTimeTo;
	}

	public void setPayTimeTo(java.util.Date payTimeTo) {
		this.payTimeTo = payTimeTo;
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
		 * @return the skuNo
		 */
		public java.lang.String getSkuNo() {
			return skuNo;
		}

		/**
		 * @param skuNo the skuNo to set
		 */
		public void setSkuNo(java.lang.String skuNo) {
			this.skuNo = skuNo;
		}

		/**
		 * @return the commodityCode
		 */
		public java.lang.String getCommodityCode() {
			return commodityCode;
		}

		/**
		 * @param commodityCode the commodityCode to set
		 */
		public void setCommodityCode(java.lang.String commodityCode) {
			this.commodityCode = commodityCode;
		}

		/**
		 * @return the productYear
		 */
		public Date getProductYear() {
			return productYear;
		}

		/**
		 * @param productYear the productYear to set
		 */
		public void setProductYear(Date productYear) {
			this.productYear = productYear;
		}

		/**
		 * @return the productYearStart
		 */
		public Date getProductYearStart() {
			return productYearStart;
		}

		/**
		 * @param productYearStart the productYearStart to set
		 */
		public void setProductYearStart(Date productYearStart) {
			this.productYearStart = productYearStart;
		}

		/**
		 * @return the productYearEnd
		 */
		public Date getProductYearEnd() {
			return productYearEnd;
		}

		/**
		 * @param productYearEnd the productYearEnd to set
		 */
		public void setProductYearEnd(Date productYearEnd) {
			this.productYearEnd = productYearEnd;
		}

		public java.lang.String getSalesClerkNo() {
			return salesClerkNo;
		}

		public void setSalesClerkNo(java.lang.String salesClerkNo) {
			this.salesClerkNo = salesClerkNo;
		}

		public java.util.Date getRetPreStartTime() {
			return retPreStartTime;
		}

		public void setRetPreStartTime(java.util.Date retPreStartTime) {
			this.retPreStartTime = retPreStartTime;
		}

		public java.util.Date getRetPreEndTime() {
			return retPreEndTime;
		}

		public void setRetPreEndTime(java.util.Date retPreEndTime) {
			this.retPreEndTime = retPreEndTime;
		}
		/**
		 * @return the flags
		 */
		public List<String> getFlags() {
			return flags;
		}

		/**
		 * @param flags the flags to set
		 */
		public void setFlags(List<String> flags) {
			this.flags = flags;
		}
		public java.lang.String getWarehouseNo() {
			return warehouseNo;
		}

		public void setWarehouseNo(java.lang.String warehouseNo) {
			this.warehouseNo = warehouseNo;
		}

		public java.util.Date getPayTime() {
			return payTime;
		}

		public void setPayTime(java.util.Date payTime) {
			this.payTime = payTime;
		}



		public String getIsCommodityCode() {
			return isCommodityCode;
		}

		public void setIsCommodityCode(String isCommodityCode) {
			this.isCommodityCode = isCommodityCode;
		}

		public String getIsSkuNo() {
			return isSkuNo;
		}

		public void setIsSkuNo(String isSkuNo) {
			this.isSkuNo = isSkuNo;
		}

		public String getIsRemark() {
			return isRemark;
		}

		public void setIsRemark(String isRemark) {
			this.isRemark = isRemark;
		}

		public String getIsCommodityName() {
			return isCommodityName;
		}

		public void setIsCommodityName(String isCommodityName) {
			this.isCommodityName = isCommodityName;
		}

		public String getIsClientRemark() {
			return isClientRemark;
		}

		public void setIsClientRemark(String isClientRemark) {
			this.isClientRemark = isClientRemark;
		}

		public String getIsSellerMessage() {
			return isSellerMessage;
		}

		public void setIsSellerMessage(String isSellerMessage) {
			this.isSellerMessage = isSellerMessage;
		}

		public String getIsAddressDetail() {
			return isAddressDetail;
		}

		public void setIsAddressDetail(String isAddressDetail) {
			this.isAddressDetail = isAddressDetail;
		}
		


		/**
		 * @return the deliveredCounty
		 */
		public java.lang.String getDeliveredCounty() {
			return deliveredCounty;
		}

		/**
		 * @param deliveredCounty the deliveredCounty to set
		 */
		public void setDeliveredCounty(java.lang.String deliveredCounty) {
			this.deliveredCounty = deliveredCounty;
		}

		/**
		 * @return the deliveredCity
		 */
		public java.lang.String getDeliveredCity() {
			return deliveredCity;
		}

		/**
		 * @param deliveredCity the deliveredCity to set
		 */
		public void setDeliveredCity(java.lang.String deliveredCity) {
			this.deliveredCity = deliveredCity;
		}

		/**
		 * @return the deliveredProvice
		 */
		public java.lang.String getDeliveredProvice() {
			return deliveredProvice;
		}

		/**
		 * @param deliveredProvice the deliveredProvice to set
		 */
		public void setDeliveredProvice(java.lang.String deliveredProvice) {
			this.deliveredProvice = deliveredProvice;
		}

		public Integer getSkuNumMin() {
			return skuNumMin;
		}

		public void setSkuNumMin(Integer skuNumMin) {
			this.skuNumMin = skuNumMin;
		}

		public Integer getSkuNumMax() {
			return skuNumMax;
		}

		public void setSkuNumMax(Integer skuNumMax) {
			this.skuNumMax = skuNumMax;
		}
		
}