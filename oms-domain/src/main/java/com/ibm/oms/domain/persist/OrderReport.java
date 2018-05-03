package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.ibm.sc.model.shipping.SelfTakePoint;

public class OrderReport implements Serializable {

	private static final long serialVersionUID = 1L;

	// 单据类型 1：正向订单 ； -1：逆向订单
	private Long billType;
	// 订单开始日期	
    private Date orderTimeFrom;
   	// 订单结束日期
    private Date orderTimeTo;
    // 订单创建日期
	private Date orderTime;
	// 子订单号
	private String orderSubNo;
	// 外部订单号
	private String aliasOrderNo;
	// 收件人地址
	private String addressCode;
    private Long state;  
    private Long city;
    private Long county;   
    private Long street;   
    // 订单来源
    private String orderSource;   
    // 订单类型
    private String orderType;
    // 会员名
    private String customerName;	
    // 会员账号
    private String memberNo;	
	// 会员等级
    private String vipLevel;
    // 支付状态
	private String statusPay;	
    // 审核状态
	private String statusConfirm;
	// 订单实付金额
	private BigDecimal totalPayAmount;
	// 自提门店ID
	private Long selfTakePointId;
	// 自提门店名称
  	private String pointName;
	// 配送地址ID
	private Long transportAreaId;
	// 配送地址
	private DistributeAddress distributeAddress;
	// 自提点
	private SelfTakePoint selfTakePoint;
	// 配送地址
  	private String areaName;
  	// 会员名
	private String userName;
	// 会员手机号码
	private String mobPhoneNum;
	// 物流状态
	private String logisticsStatus;
  	// 单据总状态
	private String statusTotal;
	// 物流商
	private String deliveryMerchantName;
	// 物流商编码
	private String supplierCode;
	// 订单总重量
	private BigDecimal weight;
	// 自提点
	private String selfFetchAddress;
	// 配送地址
	private TransportArea transportArea = new TransportArea();
	
  	// 意向单关联原单据号
  	private String orderSubRelatedOrigin;
    // 关联原单据封闭时间
  	private Date relatedSignOffTime;
    // 订单封闭时间
  	private Date signOffTime;
  	// 关联原单据支付方式
  	private Long ifPayOnArrival;
  	// 入库物流方式
	private String distributeType;
	// 商品品类
    private String productCategory;
	// 商品skuNo
	private String skuNo;
	// 商品名称
	private String commodityName;
	// 退换货拒收数量
	private Long saleNum;
	// 退换货拒收总金额
    private BigDecimal payAmount; 
	// 订单报表标识
    private int columnTitle;  
	// 退换货原因（一级）
	private String preRefundReason;
	// 退换货原因（二级）
	private String refundReason;	
	// 商品一级品类
	private String categoryLevelOne;
	// 商品二级品类
	private String categoryLevelTwo;
	// 商品三级品类
	private String categoryLevelThree;
	// 商品四级品类
	private String categoryLevelFour;  
	// 商品品类名称
	private String productCategoryName;
	// 商品折后价钱
	private Long unitDeductedPrice;
	// 交易流水号
	private String serialNo;
    // 交易时间
  	private Date payTime;
	// 当前状态
	private String currentStatus;
    // 操作时间
  	private Date operateTime;
  	// 支付名称
  	String payName; 	
  	// 退款总金额
    private BigDecimal refundAmount; 
	// 招商银行订单ID
	private Long cmbOrderId;
	
	//----待办事项中字段
	// 质检失败
	private Long inspectFailedOrderCount;
	// 退货
	private Long toReturnOrderCount;
	// 换货
	private Long toChangeOrderCount;
	// 拒收
	private Long toRefundOrderCount;
	// 待审核
	private Long toAuditOrderCount;
	// 预警
	private Long warnOrderCount;
	// 昨日总订单量
	private Long orderCount;
	// 退货订单总量
	private Long returnOrderCount;
	// 换货订单总量
	private Long changeOrderCount;
	// 拒收订单总量
	private Long refundOrderCount;
	
	//SelfTakePoint
    @Transient
    private List<String> selfTakePointIdList; 
    @Transient
    private Long pointDeliverPartnerId;
  	
	public java.util.Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
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
	public java.lang.String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(java.lang.String addressCode) {
		this.addressCode = addressCode;
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
	public java.lang.String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(java.lang.String customerName) {
		this.customerName = customerName;
	}
	public java.lang.String getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(java.lang.String vipLevel) {
		this.vipLevel = vipLevel;
	}
	public java.lang.String getStatusPay() {
		return statusPay;
	}
	public void setStatusPay(java.lang.String statusPay) {
		this.statusPay = statusPay;
	}
	public java.lang.String getStatusConfirm() {
		return statusConfirm;
	}
	public void setStatusConfirm(java.lang.String statusConfirm) {
		this.statusConfirm = statusConfirm;
	}
	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	public Long getSelfTakePointId() {
		return selfTakePointId;
	}
	public void setSelfTakePointId(Long selfTakePointId) {
		this.selfTakePointId = selfTakePointId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getTransportAreaId() {
		return transportAreaId;
	}
	public void setTransportAreaId(Long transportAreaId) {
		this.transportAreaId = transportAreaId;
	}
	public DistributeAddress getDistributeAddress() {
		return distributeAddress;
	}
	public void setDistributeAddress(DistributeAddress distributeAddress) {
		this.distributeAddress = distributeAddress;
	}
	public SelfTakePoint getSelfTakePoint() {
		return selfTakePoint;
	}
	public void setSelfTakePoint(SelfTakePoint selfTakePoint) {
		this.selfTakePoint = selfTakePoint;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public java.lang.String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(java.lang.String memberNo) {
		this.memberNo = memberNo;
	}
	public String getOrderSubRelatedOrigin() {
		return orderSubRelatedOrigin;
	}
	public void setOrderSubRelatedOrigin(String orderSubRelatedOrigin) {
		this.orderSubRelatedOrigin = orderSubRelatedOrigin;
	}
	public Date getSignOffTime() {
		return signOffTime;
	}
	public void setSignOffTime(Date signOffTime) {
		this.signOffTime = signOffTime;
	}
	public Long getIfPayOnArrival() {
		return ifPayOnArrival;
	}
	public void setIfPayOnArrival(Long ifPayOnArrival) {
		this.ifPayOnArrival = ifPayOnArrival;
	}
	public String getDistributeType() {
		return distributeType;
	}
	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Long getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public int getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(int columnTitle) {
		this.columnTitle = columnTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobPhoneNum() {
		return mobPhoneNum;
	}
	public void setMobPhoneNum(String mobPhoneNum) {
		this.mobPhoneNum = mobPhoneNum;
	}
	public String getLogisticsStatus() {
		return logisticsStatus;
	}
	public void setLogisticsStatus(String logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	public String getStatusTotal() {
		return statusTotal;
	}
	public void setStatusTotal(String statusTotal) {
		this.statusTotal = statusTotal;
	}
	public String getDeliveryMerchantName() {
		return deliveryMerchantName;
	}
	public void setDeliveryMerchantName(String deliveryMerchantName) {
		this.deliveryMerchantName = deliveryMerchantName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getSelfFetchAddress() {
		return selfFetchAddress;
	}
	public void setSelfFetchAddress(String selfFetchAddress) {
		this.selfFetchAddress = selfFetchAddress;
	}
	public TransportArea getTransportArea() {
		return transportArea;
	}
	public void setTransportArea(TransportArea transportArea) {
		this.transportArea = transportArea;
	}
	public String getPreRefundReason() {
		return preRefundReason;
	}
	public void setPreRefundReason(String preRefundReason) {
		this.preRefundReason = preRefundReason;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public Date getRelatedSignOffTime() {
		return relatedSignOffTime;
	}
	public void setRelatedSignOffTime(Date relatedSignOffTime) {
		this.relatedSignOffTime = relatedSignOffTime;
	}
	public String getCategoryLevelOne() {
		return categoryLevelOne;
	}
	public void setCategoryLevelOne(String categoryLevelOne) {
		this.categoryLevelOne = categoryLevelOne;
	}
	public String getCategoryLevelTwo() {
		return categoryLevelTwo;
	}
	public void setCategoryLevelTwo(String categoryLevelTwo) {
		this.categoryLevelTwo = categoryLevelTwo;
	}
	public String getCategoryLevelThree() {
		return categoryLevelThree;
	}
	public void setCategoryLevelThree(String categoryLevelThree) {
		this.categoryLevelThree = categoryLevelThree;
	}
	public String getCategoryLevelFour() {
		return categoryLevelFour;
	}
	public void setCategoryLevelFour(String categoryLevelFour) {
		this.categoryLevelFour = categoryLevelFour;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Long getUnitDeductedPrice() {
		return unitDeductedPrice;
	}
	public void setUnitDeductedPrice(Long unitDeductedPrice) {
		this.unitDeductedPrice = unitDeductedPrice;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Long getStreet() {
		return street;
	}
	public void setStreet(Long street) {
		this.street = street;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Long getCmbOrderId() {
		return cmbOrderId;
	}
	public void setCmbOrderId(Long cmbOrderId) {
		this.cmbOrderId = cmbOrderId;
	}
	public Long getInspectFailedOrderCount() {
		return inspectFailedOrderCount;
	}
	public void setInspectFailedOrderCount(Long inspectFailedOrderCount) {
		this.inspectFailedOrderCount = inspectFailedOrderCount;
	}
	public Long getToReturnOrderCount() {
		return toReturnOrderCount;
	}
	public void setToReturnOrderCount(Long toReturnOrderCount) {
		this.toReturnOrderCount = toReturnOrderCount;
	}
	public Long getToChangeOrderCount() {
		return toChangeOrderCount;
	}
	public void setToChangeOrderCount(Long toChangeOrderCount) {
		this.toChangeOrderCount = toChangeOrderCount;
	}
	public Long getToRefundOrderCount() {
		return toRefundOrderCount;
	}
	public void setToRefundOrderCount(Long toRefundOrderCount) {
		this.toRefundOrderCount = toRefundOrderCount;
	}
	public Long getToAuditOrderCount() {
		return toAuditOrderCount;
	}
	public void setToAuditOrderCount(Long toAuditOrderCount) {
		this.toAuditOrderCount = toAuditOrderCount;
	}
	public Long getWarnOrderCount() {
		return warnOrderCount;
	}
	public void setWarnOrderCount(Long warnOrderCount) {
		this.warnOrderCount = warnOrderCount;
	}
	public Long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}
	public Long getReturnOrderCount() {
		return returnOrderCount;
	}
	public void setReturnOrderCount(Long returnOrderCount) {
		this.returnOrderCount = returnOrderCount;
	}
	public Long getChangeOrderCount() {
		return changeOrderCount;
	}
	public void setChangeOrderCount(Long changeOrderCount) {
		this.changeOrderCount = changeOrderCount;
	}
	public Long getRefundOrderCount() {
		return refundOrderCount;
	}
	public void setRefundOrderCount(Long refundOrderCount) {
		this.refundOrderCount = refundOrderCount;
	}
    public Long getBillType() {
        return billType;
    }
    public void setBillType(Long billType) {
        this.billType = billType;
    }
    public List<String> getSelfTakePointIdList() {
        return selfTakePointIdList;
    }
    public void setSelfTakePointIdList(List<String> selfTakePointIdList) {
        this.selfTakePointIdList = selfTakePointIdList;
    }
    public Long getPointDeliverPartnerId() {
        return pointDeliverPartnerId;
    }
    public void setPointDeliverPartnerId(Long pointDeliverPartnerId) {
        this.pointDeliverPartnerId = pointDeliverPartnerId;
    }
	
}
