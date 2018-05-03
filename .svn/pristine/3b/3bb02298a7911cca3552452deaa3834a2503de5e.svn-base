package com.ibm.oms.domain.persist;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * Description: 订单审核店铺配置表
 * @author YanYanZhang
 * Date:   2018年2月1日
 */
@Entity
@Table(name="order_audit_merchant_config")
@NamedQuery(name="OrderAuditMerchantConfig.findAll", query="SELECT o FROM OrderAuditMerchantConfig o")
public class OrderAuditMerchantConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name = "enabled", columnDefinition = "TINYINT(1) default 0")
	private Boolean enabled;

	@Column(name="is_approved_order_split", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedOrderSplit;

	@Column(name="is_ignored_client_remark", columnDefinition = "TINYINT(1) default 0")
	private Boolean isIgnoredClientRemark;

	@Column(name="is_ignored_client_service_remark", columnDefinition = "TINYINT(1) default 0")
	private Boolean isIgnoredClientServiceRemark;

	@Column(name="max_amount")
	private BigDecimal maxAmount;

	@Column(name="merchant_code")
	private String merchantCode;
	
	@Column(name="merchant_name")
	private String merchantName;

	@Column(name="min_amount")
	private BigDecimal minAmount;

	@Column(name="is_delay", columnDefinition = "TINYINT(1) default 0")
	private Boolean isDelay;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	public OrderAuditMerchantConfig() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public BigDecimal getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getMerchantCode() {
		return this.merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the isApprovedOrderSplit
	 */
	public Boolean getIsApprovedOrderSplit() {
		return isApprovedOrderSplit;
	}

	/**
	 * @param isApprovedOrderSplit the isApprovedOrderSplit to set
	 */
	public void setIsApprovedOrderSplit(Boolean isApprovedOrderSplit) {
		this.isApprovedOrderSplit = isApprovedOrderSplit;
	}

	/**
	 * @return the isIgnoredClientRemark
	 */
	public Boolean getIsIgnoredClientRemark() {
		return isIgnoredClientRemark;
	}

	/**
	 * @param isIgnoredClientRemark the isIgnoredClientRemark to set
	 */
	public void setIsIgnoredClientRemark(Boolean isIgnoredClientRemark) {
		this.isIgnoredClientRemark = isIgnoredClientRemark;
	}

	/**
	 * @return the isIgnoredClientServiceRemark
	 */
	public Boolean getIsIgnoredClientServiceRemark() {
		return isIgnoredClientServiceRemark;
	}

	/**
	 * @param isIgnoredClientServiceRemark the isIgnoredClientServiceRemark to set
	 */
	public void setIsIgnoredClientServiceRemark(Boolean isIgnoredClientServiceRemark) {
		this.isIgnoredClientServiceRemark = isIgnoredClientServiceRemark;
	}

	/**
	 * @return the isDelay
	 */
	public Boolean getIsDelay() {
		return isDelay;
	}

	/**
	 * @param isDelay the isDelay to set
	 */
	public void setIsDelay(Boolean isDelay) {
		this.isDelay = isDelay;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}