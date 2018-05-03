package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderSplitTransferReturnClientDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//发货单号 第三方的原始单号
	private String orderCode;
	//DELIVERED
	private String orderStatus;
	//操作人
	private String operator;
	//操作时间（Y-m-d H:i:s）
	private Date operatorTime;
	//物流公司代码
	private String logisticsProviderCode;
	//运单号
	private String shippingOrderNo;				
	//"发货异常编码：INSUFFICIENT_INVENTORY-库存不足 OTHER-其他异常"
	private String exceptionCode;				
	//备注或失败原因
	private String note;						
	//称重重量
	private Integer weight;						
	//称重体积
	private String volume;						
	//回调的方法（WMS原样返回E3通知过去的字段内容）
	private String callback;					
	//回传明细信息
	private List<OrderSplitTransferReturnDetailClientDTO> transferReturnDetails;
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	public String getLogisticsProviderCode() {
		return logisticsProviderCode;
	}
	public void setLogisticsProviderCode(String logisticsProviderCode) {
		this.logisticsProviderCode = logisticsProviderCode;
	}
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}
	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public List<OrderSplitTransferReturnDetailClientDTO> getTransferReturnDetails() {
		return transferReturnDetails;
	}
	public void setTransferReturnDetails(List<OrderSplitTransferReturnDetailClientDTO> transferReturnDetails) {
		this.transferReturnDetails = transferReturnDetails;
	}
}
