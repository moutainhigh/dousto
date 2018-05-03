package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: //查询订单入参
 * @author Yusl
 * Date:   2018年2月2日 
 */
public class OrderQueryClientDTO implements Serializable{

	//导购编号
	private String salesclerkNo;
    //订单来源 导购APP 线上商城
    private String orderSource;
	//订单状态
	private String orderStatus;
	//会员编号
	private String memberNo;
	//下单日期起始
	private Date orderTimeFrom;
	//下单日期结束
	private Date orderTimeTO;
    //商家编号
    private String shopNo;

	//是否产生提成
    private Integer isBonus;
    
	public String getSalesclerkNo() {
		return salesclerkNo;
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
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public Integer getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(Integer isBonus) {
		this.isBonus = isBonus;
	}
	
	
}
