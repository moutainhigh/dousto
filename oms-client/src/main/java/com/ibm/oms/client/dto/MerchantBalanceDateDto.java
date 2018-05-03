package com.ibm.oms.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 店铺结算日期
 * @author YanYanZhang
 * Date:   2018年3月28日 
 */
public class MerchantBalanceDateDto implements Serializable{

	/* 属性说明 */
	private static final long serialVersionUID = -3785203020551383248L;
	
	
	private Integer id;

	/**
	 * 结算日期
	 */
	private Date setDate;

	/**
	 * 店铺编码
	 */
	private String merchantCode;

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

	public Date getSetDate() {
		return setDate;
	}

	public void setSetDate(Date setDate) {
		this.setDate = setDate;
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


}
