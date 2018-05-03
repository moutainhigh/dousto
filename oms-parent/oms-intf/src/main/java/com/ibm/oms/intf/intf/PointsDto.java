package com.ibm.oms.intf.intf;

import java.io.Serializable;

public class PointsDto implements Serializable{

	/* 属性说明 */
	private static final long serialVersionUID = -1941593255818597373L;
	//会员账号
	private String memberNo;
	//订单sub
	private String orderNo;
	//0 表示+  1是-
	private String directionOpt;
	//具体+—的积分数
	private int points;
	//1 交易后增加的积分   2 交易减掉的积分    3积分兑换消耗的           
	private String transactionType;
	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}
	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the directionOpt
	 */
	public String getDirectionOpt() {
		return directionOpt;
	}
	/**
	 * @param directionOpt the directionOpt to set
	 */
	public void setDirectionOpt(String directionOpt) {
		this.directionOpt = directionOpt;
	}
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
}
