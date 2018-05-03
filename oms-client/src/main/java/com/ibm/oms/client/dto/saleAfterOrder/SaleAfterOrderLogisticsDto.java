package com.ibm.oms.client.dto.saleAfterOrder;

/**
 * 退换货单的物流信息，前端传送的数据
 */
public class SaleAfterOrderLogisticsDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195606702989954097L;

	//退换货单号
	private String returnNo;
	//退换货人的联系电话	
	private String phoneNum;
	//退货方式，线上/线下
	private String returnType;
	//线上，退货详细地址
	private String returnAddr;
	//线上，退货接收人姓名
	private String receiverName;
	//线上，退货接收人电话
	private String receiverPhone;
	//线上，快递类型
	private String expressType;
	//线上，快递单号
	private String expressNo;
	//线下，退货门店编号
	private String returnShopNo;
	//线下，退货预约开始时间
	private String returnStartTime;
	//线下，退货预约结束时间
	private String returnEndTime;
	//换货，线上，收货人详细地址
	private String chgReceiveAddr;
	//换货，线上，收货人姓名
	private String chgReceiveName;
	//换货，线上，收货人电话
	private String chgReceivePhone;
	//退换货物流上传凭证
	private String logisticsFileUrl;
	//退款方式编码
	
	//退款方式名称
	
	//退款金额 合计
	
	//扣减优惠券
	
	//扣减赠送的积分数
	
	//备注
	
	public String getReturnNo() {
		return returnNo;
	}
	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getReturnAddr() {
		return returnAddr;
	}
	public void setReturnAddr(String returnAddr) {
		this.returnAddr = returnAddr;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getExpressType() {
		return expressType;
	}
	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getReturnShopNo() {
		return returnShopNo;
	}
	public void setReturnShopNo(String returnShopNo) {
		this.returnShopNo = returnShopNo;
	}
	public String getReturnStartTime() {
		return returnStartTime;
	}
	public void setReturnStartTime(String returnStartTime) {
		this.returnStartTime = returnStartTime;
	}
	public String getReturnEndTime() {
		return returnEndTime;
	}
	public void setReturnEndTime(String returnEndTime) {
		this.returnEndTime = returnEndTime;
	}
	public String getChgReceiveAddr() {
		return chgReceiveAddr;
	}
	public void setChgReceiveAddr(String chgReceiveAddr) {
		this.chgReceiveAddr = chgReceiveAddr;
	}
	public String getChgReceiveName() {
		return chgReceiveName;
	}
	public void setChgReceiveName(String chgReceiveName) {
		this.chgReceiveName = chgReceiveName;
	}
	public String getChgReceivePhone() {
		return chgReceivePhone;
	}
	public void setChgReceivePhone(String chgReceivePhone) {
		this.chgReceivePhone = chgReceivePhone;
	}
	public String getLogisticsFileUrl() {
		return logisticsFileUrl;
	}
	public void setLogisticsFileUrl(String logisticsFileUrl) {
		this.logisticsFileUrl = logisticsFileUrl;
	}
}