package com.ibm.oms.client.dto.order.create.refactor;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 接收到得OrderMainDTO类
 * @author wangchao
 *
 */
public class ReceiveOrderMainDTO implements Serializable {
	
	private static final long serialVersionUID = -8968714519762778789L;
	/**外部渠道订单编号 第三方平台 **/
	public String aliasOrderNo;
	/**订单类型 **/
	public String orderType;
	/**订单来源 **/
	public String orderSource;
	/** 会员No**/
	public String memberNo;
	/** 会员名称**/
	public String customerName;
	/** 会员电话**/
	public String customerPhone;
	/** 会员邮件**/
	public String customerEmail;
	/** 会员等级**/
	public String memberVipCardLevel;
	/** 会员归属导购编号**/
	public String memberSalesclerkNo;
	
	
	/** 商家类型 商家类型,第三方平台（3），自有在线（1），自有线下（2）**/
	public String merchantType;
	/** 商家编号**/
	public String merchantNo;
	/**门店编号 **/
	public String shopNo;
	/** 营业员编号/门店导购编号**/
	public String salespersonNo;
	
	/**是否有发票 **/
	public String needInvoice;
	
	/** 是否货到付款**/
	public String ifPayOnArrival;
	/** 下单IP地址**/
	public String ip;
	/**订单产生时间**/
	public String orderTime;
	/** 销售门店code **/
	public String saleStoreCode;
	/** （发货）（收货）门店 **/
	public String shipStoreCode;
	/**  **/
	List<ReceiveOrderItemDTO> receiveOrderItemDTOs;
	/** 收货相关属性 **/
	public RecipientInformationDTO recipientInformationDTO;
	/** 发票信息 **/
	public InvoiceDTO invoiceDTO ;
	
	//******************订单总计**** 前台获取//
	/** 折扣总额 **/
	public String discountTotal;
	/** 订单金额总计 **/
	public String totalOrderAmount;
	/** 订单实际应付金额总计= 订单金额+运费-运费优惠-折扣优惠-积分优惠 **/
	public String totalPayAmount;
	
	//商品
	/** 商品数量总计 **/
	public String totalProductCount;
	/** 商品金额总计 -优惠前**/
	public String totalProductPrice;
	
	//优惠券
	/** 优惠券号 **/
	public String couponsNo;
	/** 优惠券总额 **/
	public String totalPromo;
	
	//积分
	/** 用的总积分  **/
	public String totalPoint;
	/** 积分抵现金额**/
	public String totalPointAmount;
	
	
	//******************价格计算--组成购物车InfCart用**** //
	/** 会员ID **/
	public String memberId;
	/** 门店ID **/
	public String storeId;
	/** 渠道 **/
	public String channelId;
	/** 区域编码 导购APP必传 **/
	public String zoneId;
	//******************价格计算--组成购物车InfCart用 end**** //
	 /** 运费总额 | 物流费用 **/
    public String transportFee;
    /** 运费优惠 **/
    public String discountTransport;
    /**配送方式  奇门：配送方式 都是第三方配送（3），门店自配送（1），非门店自配送（2）  **/
    public String distributeType;
    
	public String getAliasOrderNo() {
		return aliasOrderNo;
	}

	public void setAliasOrderNo(String aliasOrderNo) {
		this.aliasOrderNo = aliasOrderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public List<ReceiveOrderItemDTO> getReceiveOrderItemDTOs() {
		return receiveOrderItemDTOs;
	}

	public void setReceiveOrderItemDTOs(List<ReceiveOrderItemDTO> receiveOrderItemDTOs) {
		this.receiveOrderItemDTOs = receiveOrderItemDTOs;
	}

	public RecipientInformationDTO getRecipientInformationDTO() {
		return recipientInformationDTO;
	}

	public void setRecipientInformationDTO(RecipientInformationDTO recipientInformationDTO) {
		this.recipientInformationDTO = recipientInformationDTO;
	}

	public String getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(String discountTotal) {
		this.discountTotal = discountTotal;
	}

	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public String getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(String totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public String getTotalProductCount() {
		return totalProductCount;
	}

	public void setTotalProductCount(String totalProductCount) {
		this.totalProductCount = totalProductCount;
	}

	public String getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(String totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public String getCouponsNo() {
		return couponsNo;
	}

	public void setCouponsNo(String couponsNo) {
		this.couponsNo = couponsNo;
	}

	public String getTotalPromo() {
		return totalPromo;
	}

	public void setTotalPromo(String totalPromo) {
		this.totalPromo = totalPromo;
	}

	public String getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getTotalPointAmount() {
		return totalPointAmount;
	}

	public void setTotalPointAmount(String totalPointAmount) {
		this.totalPointAmount = totalPointAmount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

//	public String getShopNo() {
//		return shopNo;
//	}
//
//	public void setShopNo(String shopNo) {
//		this.shopNo = shopNo;
//	}

	public String getSalespersonNo() {
		return salespersonNo;
	}

	public void setSalespersonNo(String salespersonNo) {
		this.salespersonNo = salespersonNo;
	}

	public String getMemberVipCardLevel() {
		return memberVipCardLevel;
	}

	public void setMemberVipCardLevel(String memberVipCardLevel) {
		this.memberVipCardLevel = memberVipCardLevel;
	}

	public String getIfPayOnArrival() {
		return ifPayOnArrival;
	}

	public void setIfPayOnArrival(String ifPayOnArrival) {
		this.ifPayOnArrival = ifPayOnArrival;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSaleStoreCode() {
		return saleStoreCode;
	}

	public void setSaleStoreCode(String saleStoreCode) {
		this.saleStoreCode = saleStoreCode;
	}

	public String getShipStoreCode() {
		return shipStoreCode;
	}

	public void setShipStoreCode(String shipStoreCode) {
		this.shipStoreCode = shipStoreCode;
	}

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getDiscountTransport() {
		return discountTransport;
	}

	public void setDiscountTransport(String discountTransport) {
		this.discountTransport = discountTransport;
	}

	public InvoiceDTO getInvoiceDTO() {
		return invoiceDTO;
	}

	public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
		this.invoiceDTO = invoiceDTO;
	}

	public String getDistributeType() {
		return distributeType;
	}

	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getMemberSalesclerkNo() {
		return memberSalesclerkNo;
	}

	public void setMemberSalesclerkNo(String memberSalesclerkNo) {
		this.memberSalesclerkNo = memberSalesclerkNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * @return the needInvoice
	 */
	public String getNeedInvoice() {
		return needInvoice;
	}

	/**
	 * @param needInvoice the needInvoice to set
	 */
	public void setNeedInvoice(String needInvoice) {
		this.needInvoice = needInvoice;
	}
	
}
