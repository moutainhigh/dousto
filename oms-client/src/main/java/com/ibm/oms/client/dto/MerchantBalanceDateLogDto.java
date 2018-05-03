package com.ibm.oms.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 店铺结算日期明细
 * @author YanYanZhang
 * Date:   2018年3月28日 
 */
public class MerchantBalanceDateLogDto implements Serializable{

	/* 属性说明 */
	private static final long serialVersionUID = -1582771938470428782L;
	
	private Integer id;

	/**
	 * 结算日期
	 */
	private Date balanceDate;

	/**
	 * 店铺编码
	 */
	private String merchantCode;
	
	/**
	 * 门店销售金额
	 */
	private BigDecimal saleAmount;
	
	/**
	 * 门店销售数量
	 */
	private Integer saleNum;
	
	/**
	 * 门店退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 门店退款数量
	 */
	private Integer returnNum;
	
	/**
	 * 线上销售金额
	 */
	private BigDecimal onlineSaleAmount;
	
	/**
	 * 线上销售数量
	 */
	private Integer onlineSaleNum;
	
	/**
	 * 线上退款金额
	 */
	private BigDecimal onlineRefundAmount;
	
	/**
	 * 线上退款数量
	 */
	private Integer onlineRefundNum;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the balanceDate
	 */
	public Date getBalanceDate() {
		return balanceDate;
	}

	/**
	 * @param balanceDate the balanceDate to set
	 */
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	/**
	 * @return the merchantCode
	 */
	public String getMerchantCode() {
		return merchantCode;
	}

	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	/**
	 * @return the saleAmount
	 */
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	/**
	 * @param saleAmount the saleAmount to set
	 */
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	/**
	 * @return the saleNum
	 */
	public Integer getSaleNum() {
		return saleNum;
	}

	/**
	 * @param saleNum the saleNum to set
	 */
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	/**
	 * @return the refundAmount
	 */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	/**
	 * @param refundAmount the refundAmount to set
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
	 * @return the returnNum
	 */
	public Integer getReturnNum() {
		return returnNum;
	}

	/**
	 * @param returnNum the returnNum to set
	 */
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}

	/**
	 * @return the onlineSaleAmount
	 */
	public BigDecimal getOnlineSaleAmount() {
		return onlineSaleAmount;
	}

	/**
	 * @param onlineSaleAmount the onlineSaleAmount to set
	 */
	public void setOnlineSaleAmount(BigDecimal onlineSaleAmount) {
		this.onlineSaleAmount = onlineSaleAmount;
	}

	/**
	 * @return the onlineSaleNum
	 */
	public Integer getOnlineSaleNum() {
		return onlineSaleNum;
	}

	/**
	 * @param onlineSaleNum the onlineSaleNum to set
	 */
	public void setOnlineSaleNum(Integer onlineSaleNum) {
		this.onlineSaleNum = onlineSaleNum;
	}

	/**
	 * @return the onlineRefundAmount
	 */
	public BigDecimal getOnlineRefundAmount() {
		return onlineRefundAmount;
	}

	/**
	 * @param onlineRefundAmount the onlineRefundAmount to set
	 */
	public void setOnlineRefundAmount(BigDecimal onlineRefundAmount) {
		this.onlineRefundAmount = onlineRefundAmount;
	}

	/**
	 * @return the onlineRefundNum
	 */
	public Integer getOnlineRefundNum() {
		return onlineRefundNum;
	}

	/**
	 * @param onlineRefundNum the onlineRefundNum to set
	 */
	public void setOnlineRefundNum(Integer onlineRefundNum) {
		this.onlineRefundNum = onlineRefundNum;
	}
	
	
}
