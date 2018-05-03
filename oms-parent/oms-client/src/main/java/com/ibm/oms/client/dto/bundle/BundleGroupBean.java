package com.ibm.oms.client.dto.bundle;

import java.io.Serializable;
import java.math.BigDecimal;


public class BundleGroupBean implements Serializable{
	private static final long serialVersionUID = -2111241485743183724L;
	
	protected String message;

	private Long productId;
	private BigDecimal amount;
	private String BundleEndDate;// 结束时间
	private String panicBuyPrice;// 抢购价

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBundleEndDate() {
		return BundleEndDate;
	}

	public void setBundleEndDate(String bundleEndDate) {
		BundleEndDate = bundleEndDate;
	}

	public String getPanicBuyPrice() {
		return panicBuyPrice;
	}

	public void setPanicBuyPrice(String panicBuyPrice) {
		this.panicBuyPrice = panicBuyPrice;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
