package com.ibm.oms.domain.persist;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Description: 自动审核配置表
 * @author YanYanZhang
 * Date:   2018年2月1日
 */
@Entity
@Table(name="order_audit_config")
@NamedQuery(name="OrderAuditConfig.findAll", query="SELECT o FROM OrderAuditConfig o")
public class OrderAuditConfig implements Serializable {

	/* 属性说明 */
	private static final long serialVersionUID = -6857231067032020219L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="audit_count")
	private int auditCount;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name = "enabled", columnDefinition = "TINYINT(1) default 0")
	private Boolean enabled;

	@Column(name="intercept_skus")
	private String interceptSkus;

	@Column(name="is_approved_order_merge", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedOrderMerge;

	@Column(name="is_approved_order_promotion", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedOrderPromotion;

	@Column(name="is_approved_order_split", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedOrderSplit;
	
	@Column(name="is_approved_order_barter", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedOrderBarter;

	@Column(name="is_approved_pay_on_arrival", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedPayOnArrival;

	@Column(name="is_approved_single_product", columnDefinition = "TINYINT(1) default 0")
	private Boolean isApprovedSingleProduct;

	@Column(name="is_ignored_client_remark", columnDefinition = "TINYINT(1) default 0")
	private Boolean isIgnoredClientRemark;

	@Column(name="is_ignored_client_service_remark", columnDefinition = "TINYINT(1) default 0")
	private Boolean isIgnoredClientServiceRemark;

	@Column(name="max_amount")
	private BigDecimal maxAmount;

	@Column(name="min_amount")
	private BigDecimal minAmount;

	@Column(name="minutes_delay")
	private Integer minutesDelay;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	public OrderAuditConfig() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAuditCount() {
		return this.auditCount;
	}

	public void setAuditCount(int auditCount) {
		this.auditCount = auditCount;
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

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getInterceptSkus() {
		return this.interceptSkus;
	}

	public void setInterceptSkus(String interceptSkus) {
		this.interceptSkus = interceptSkus;
	}

	/**
	 * @return the isApprovedOrderMerge
	 */
	public Boolean getIsApprovedOrderMerge() {
		return isApprovedOrderMerge;
	}

	/**
	 * @param isApprovedOrderMerge the isApprovedOrderMerge to set
	 */
	public void setIsApprovedOrderMerge(Boolean isApprovedOrderMerge) {
		this.isApprovedOrderMerge = isApprovedOrderMerge;
	}

	/**
	 * @return the isApprovedOrderPromotion
	 */
	public Boolean getIsApprovedOrderPromotion() {
		return isApprovedOrderPromotion;
	}

	/**
	 * @param isApprovedOrderPromotion the isApprovedOrderPromotion to set
	 */
	public void setIsApprovedOrderPromotion(Boolean isApprovedOrderPromotion) {
		this.isApprovedOrderPromotion = isApprovedOrderPromotion;
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
	 * @return the isApprovedOrderBarter
	 */
	public Boolean getIsApprovedOrderBarter() {
		return isApprovedOrderBarter;
	}

	/**
	 * @param isApprovedOrderBarter the isApprovedOrderBarter to set
	 */
	public void setIsApprovedOrderBarter(Boolean isApprovedOrderBarter) {
		this.isApprovedOrderBarter = isApprovedOrderBarter;
	}

	/**
	 * @return the isApprovedPayOnArrival
	 */
	public Boolean getIsApprovedPayOnArrival() {
		return isApprovedPayOnArrival;
	}

	/**
	 * @param isApprovedPayOnArrival the isApprovedPayOnArrival to set
	 */
	public void setIsApprovedPayOnArrival(Boolean isApprovedPayOnArrival) {
		this.isApprovedPayOnArrival = isApprovedPayOnArrival;
	}

	/**
	 * @return the isApprovedSingleProduct
	 */
	public Boolean getIsApprovedSingleProduct() {
		return isApprovedSingleProduct;
	}

	/**
	 * @param isApprovedSingleProduct the isApprovedSingleProduct to set
	 */
	public void setIsApprovedSingleProduct(Boolean isApprovedSingleProduct) {
		this.isApprovedSingleProduct = isApprovedSingleProduct;
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
	 * @return the maxAmount
	 */
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount the maxAmount to set
	 */
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @return the minAmount
	 */
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	/**
	 * @param minAmount the minAmount to set
	 */
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @return the minutesDelay
	 */
	public Integer getMinutesDelay() {
		return minutesDelay;
	}

	/**
	 * @param minutesDelay the minutesDelay to set
	 */
	public void setMinutesDelay(Integer minutesDelay) {
		this.minutesDelay = minutesDelay;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}