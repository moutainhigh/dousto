package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.promo.dto.promotion.InfCart;

/**
 * @author ChaoWang 主订单扩展字段 不需要验证的字段
 *
 */
public class OrderMainExtDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2711636612416035622L;

	/** 导购编号 **/
	String salesclerkNo;
	/** 销售门店 **/
	String saleStorecode;
	/** 销售门店归属公司 **/
	String saleCompanyCode;
	/** 销售门店归属加盟商 **/
	String saleFranchiseeCode;
	/** （发货）门店 **/
	String shipStorecode;
	/** （发货）门店归属公司 **/
	String shipCompanyCode;
	/** （发货）门店归属加盟商 **/
	String shipFranchiseeCode;
	/** 是否挂起 0否 1是 **/
	Integer isSuspension;
	/** 是否合并 0否 1是 **/
	Integer isMerge;
	/** 是否拆单 0否  1是  2拆分单产生的新订单拆分单 **/
	Integer isSplit;
	/** 是否换货 0否 1是 **/
	Integer isBarter;
	/** 退款申请 0全部退款,1部分退款 **/
	Integer refundType;
	/** 拆单的母orderNo **/
	String orderNoP;
	/** 拆单的母orderID **/
	Long idOrderP;
	
	
	   //###拆单使用的属性记录被拆分单状态
    //审核状态
	String statusConfirm;
    //支付状态
    String statusPay;
    //总状态
    String statusTotal;
    //业绩归属门店
    String performStoreCode;
	//结算日期
    Date balanceDate;
	//计算后购物车参数--用来计算总积分和订单行积分 -0409
    @JsonIgnoreProperties
    InfCart infCart;
	
	public String getSalesclerkNo() {
		return salesclerkNo;
	}

	public void setSalesclerkNo(String salesclerkNo) {
		this.salesclerkNo = salesclerkNo;
	}

	public String getSaleStorecode() {
		return saleStorecode;
	}

	public void setSaleStorecode(String saleStorecode) {
		this.saleStorecode = saleStorecode;
	}

	public String getSaleCompanyCode() {
		return saleCompanyCode;
	}

	public void setSaleCompanyCode(String saleCompanyCode) {
		this.saleCompanyCode = saleCompanyCode;
	}

	public String getSaleFranchiseeCode() {
		return saleFranchiseeCode;
	}

	public void setSaleFranchiseeCode(String saleFranchiseeCode) {
		this.saleFranchiseeCode = saleFranchiseeCode;
	}

	public String getShipStorecode() {
		return shipStorecode;
	}

	public void setShipStorecode(String shipStorecode) {
		this.shipStorecode = shipStorecode;
	}

	public String getShipCompanyCode() {
		return shipCompanyCode;
	}

	public void setShipCompanyCode(String shipCompanyCode) {
		this.shipCompanyCode = shipCompanyCode;
	}

	public String getShipFranchiseeCode() {
		return shipFranchiseeCode;
	}

	public void setShipFranchiseeCode(String shipFranchiseeCode) {
		this.shipFranchiseeCode = shipFranchiseeCode;
	}

	public Integer getIsSuspension() {
		return isSuspension;
	}

	public void setIsSuspension(Integer isSuspension) {
		this.isSuspension = isSuspension;
	}

	public Integer getIsMerge() {
		return isMerge;
	}

	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}

	public Integer getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}

	public Integer getIsBarter() {
		return isBarter;
	}

	public void setIsBarter(Integer isBarter) {
		this.isBarter = isBarter;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
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

	public String getStatusConfirm() {
		return statusConfirm;
	}

	public void setStatusConfirm(String statusConfirm) {
		this.statusConfirm = statusConfirm;
	}

	public String getStatusPay() {
		return statusPay;
	}

	public void setStatusPay(String statusPay) {
		this.statusPay = statusPay;
	}

	public String getStatusTotal() {
		return statusTotal;
	}

	public void setStatusTotal(String statusTotal) {
		this.statusTotal = statusTotal;
	}

	public String getPerformStoreCode() {
		return performStoreCode;
	}

	public void setPerformStoreCode(String performStoreCode) {
		this.performStoreCode = performStoreCode;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public InfCart getInfCart() {
		return infCart;
	}

	public void setInfCart(InfCart infCart) {
		this.infCart = infCart;
	}
	
}
