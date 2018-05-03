package com.ibm.oms.domain.persist;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the merchant_balance_date_log database table.
 * 
 */
@Entity
@Table(name="merchant_balance_date_log")
@NamedQuery(name="MerchantBalanceDateLog.findAll", query="SELECT m FROM MerchantBalanceDateLog m")
public class MerchantBalanceDateLog implements Serializable {

	/* 属性说明 */
	private static final long serialVersionUID = -400619590351596772L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="balance_date")
	private Date balanceDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="merchant_code")
	private String merchantCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="out_created_time")
	private Date outCreatedTime;

	public MerchantBalanceDateLog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBalanceDate() {
		return this.balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMerchantCode() {
		return this.merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public Date getOutCreatedTime() {
		return this.outCreatedTime;
	}

	public void setOutCreatedTime(Date outCreatedTime) {
		this.outCreatedTime = outCreatedTime;
	}

}