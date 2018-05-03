package com.ibm.oms.domain.query;

import java.io.Serializable;
import java.util.Date;


public class OrderQueryModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//导购编号
	private String salesclerkNo;
    //订单来源 导购APP 线上商城
	private String orderType;
	//订单状态
	private String orderStatus;
	//会员编号
	private String memberNo;
	//会员编号
	private Date orderTimeFrom;
	//下单日期起始
	private Date orderTimeTO;
	//下单日期结束
    private Integer isSuspension;
    //是否产生提成
	public String getSalesclerkNo() {
		return salesclerkNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setSalesclerkNo(String salesclerkNo) {
		this.salesclerkNo = salesclerkNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public Date getOrderTimeFrom() {
		return orderTimeFrom;
	}
	public void setOrderTimeFrom(Date orderTimeFrom) {
		this.orderTimeFrom = orderTimeFrom;
	}
	public Date getOrderTimeTO() {
		return orderTimeTO;
	}
	public void setOrderTimeTO(Date orderTimeTO) {
		this.orderTimeTO = orderTimeTO;
	}
	public Integer getIsSuspension() {
		return isSuspension;
	}
	public void setIsSuspension(Integer isSuspension) {
		this.isSuspension = isSuspension;
	}
	
	
}
