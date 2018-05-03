package com.ibm.oms.intf.intf;
//package com.ibm.sc.service.oms.intf;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotBlank;
//
///**
// * @author pjsong TMS传输订单的物流状态信息至OMS
// */
//public class OmsTmsSendOrderDTO implements Serializable {
//	/**
//	 * 物流商家ID
//	 * **/
//	private String logisticCompanyId;
//	/**
//	 * 物流订单号(外部编号)
//	 **/
//	private String txLogisticID;
//	/** 订单编号(内部单号) **/
//	private String orderid;
//	/** os：销售单，hh：换货单，th：退货单，js：拒收单 **/
//	private String type;
//	/** 订单标识 0：正常订单 1：电器订单 **/
//	private int flag;
//	/** 用户姓名 **/
//	private String name;
//	/** 用户邮编 **/
//	private String postCode;
//	/** 用户电话，包括区号、电话号码及分机号，中间用“-”分隔； **/
//	private String phone;
//	/** 用户移动电话 **/
//	private String mobile;
//	/** 用户所在省 **/
//	private String prov;
//	/** 用户所在城市 **/
//	private String city;
//	/** 用户所在县（区） **/
//	private String area;
//	/** 用户详细地址编号（对应的最末尾的层级栏目编号） **/
//	private String addresscode;
//	/** 用户详细地址 **/
//	private String address;
//	/** 商品金额 **/
//	private BigDecimal goodsValue;
//	/** 代收货款金额 **/
//	private BigDecimal itemsValue;
//	/** 总件数 **/
//	private int totalPCS;
//	/** 商品总重量 **/
//	private BigDecimal totalWeight;
//	/** 备注 **/
//	private String remark;
//	/** 保值金额（暂时没有使用，默认为0.0） **/
//	private BigDecimal insuranceValue;
//	/** 订单创建时间 **/
//	private String createTime;
//	/** 是否已开发票，0未开 1已开 **/
//	private int needInvoice;
//	/** 支付方式,包含:天虹卡，现金，银行卡，0(代表已支付)。 **/
//	private String payMode;
//	/** 箱签号，用逗号隔开多个 **/
//	private String toid;
//	/** 配送方式 **/
//	private int deliverymode;
//	/** 订单出库时间 **/
//	private String outhousetime;
//	/** 订单复核时间 **/
//	private String reviewtime;
//	/** 物流订单类型，0为正常订单，1为大电器订单，2为生鲜订单 **/
//	private int wmsOrderType;
//	/**
//	 * 意向单(换货,退货)需要物流商带回的商品详细,为所有商品明细的XML格式信息，销售订单是不需要传递的，格式见下面的items的xml格式数据说明，
//	 * 如果不是带回的信息不需要（需要带回的为退货商品，换货单返回天虹的商品，拒收单返回天虹的商品）
//	 **/
//	private String items;
//	public String getLogisticCompanyId() {
//		return logisticCompanyId;
//	}
//	public void setLogisticCompanyId(String logisticCompanyId) {
//		this.logisticCompanyId = logisticCompanyId;
//	}
//	public String getTxLogisticID() {
//		return txLogisticID;
//	}
//	public void setTxLogisticID(String txLogisticID) {
//		this.txLogisticID = txLogisticID;
//	}
//	public String getOrderid() {
//		return orderid;
//	}
//	public void setOrderid(String orderid) {
//		this.orderid = orderid;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public int getFlag() {
//		return flag;
//	}
//	public void setFlag(int flag) {
//		this.flag = flag;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPostCode() {
//		return postCode;
//	}
//	public void setPostCode(String postCode) {
//		this.postCode = postCode;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public String getProv() {
//		return prov;
//	}
//	public void setProv(String prov) {
//		this.prov = prov;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getArea() {
//		return area;
//	}
//	public void setArea(String area) {
//		this.area = area;
//	}
//	public String getAddresscode() {
//		return addresscode;
//	}
//	public void setAddresscode(String addresscode) {
//		this.addresscode = addresscode;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public BigDecimal getGoodsValue() {
//		return goodsValue;
//	}
//	public void setGoodsValue(BigDecimal goodsValue) {
//		this.goodsValue = goodsValue;
//	}
//	public BigDecimal getItemsValue() {
//		return itemsValue;
//	}
//	public void setItemsValue(BigDecimal itemsValue) {
//		this.itemsValue = itemsValue;
//	}
//	public int getTotalPCS() {
//		return totalPCS;
//	}
//	public void setTotalPCS(int totalPCS) {
//		this.totalPCS = totalPCS;
//	}
//	public BigDecimal getTotalWeight() {
//		return totalWeight;
//	}
//	public void setTotalWeight(BigDecimal totalWeight) {
//		this.totalWeight = totalWeight;
//	}
//	public String getRemark() {
//		return remark;
//	}
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//	public BigDecimal getInsuranceValue() {
//		return insuranceValue;
//	}
//	public void setInsuranceValue(BigDecimal insuranceValue) {
//		this.insuranceValue = insuranceValue;
//	}
//	public String getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(String createTime) {
//		this.createTime = createTime;
//	}
//	public int getNeedInvoice() {
//		return needInvoice;
//	}
//	public void setNeedInvoice(int needInvoice) {
//		this.needInvoice = needInvoice;
//	}
//	public String getPayMode() {
//		return payMode;
//	}
//	public void setPayMode(String payMode) {
//		this.payMode = payMode;
//	}
//	public String getToid() {
//		return toid;
//	}
//	public void setToid(String toid) {
//		this.toid = toid;
//	}
//	public int getDeliverymode() {
//		return deliverymode;
//	}
//	public void setDeliverymode(int deliverymode) {
//		this.deliverymode = deliverymode;
//	}
//	public String getOuthousetime() {
//		return outhousetime;
//	}
//	public void setOuthousetime(String outhousetime) {
//		this.outhousetime = outhousetime;
//	}
//	public String getReviewtime() {
//		return reviewtime;
//	}
//	public void setReviewtime(String reviewtime) {
//		this.reviewtime = reviewtime;
//	}
//	public int getWmsOrderType() {
//		return wmsOrderType;
//	}
//	public void setWmsOrderType(int wmsOrderType) {
//		this.wmsOrderType = wmsOrderType;
//	}
//	public String getItems() {
//		return items;
//	}
//	public void setItems(String items) {
//		this.items = items;
//	}
//
//	
//}
