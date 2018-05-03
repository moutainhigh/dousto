package com.ibm.oms.client.dto.order;

import java.io.Serializable;

public class OrderSplitTransferReturnDetailClientDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商品编码
	private String skuCode;		
	//数量
	private Integer normalQuantity;
	//数量预留字段
	private Integer defectiveQuantity;
	//物流公司代码（暂时只支持传入不做处理以主单为准）
	private String logisticsProviderCode;	
	//运单号（暂时只支持传入不做处理以主单为准）
	private String shippingOrderNo;	
	//称重重量（暂时只支持传入不做处理以主单为准）
	private Integer weight;	
	//称重体积（暂时只支持传入不做处理以主单为准）
	private String volume;
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public Integer getNormalQuantity() {
		return normalQuantity;
	}
	public void setNormalQuantity(Integer normalQuantity) {
		this.normalQuantity = normalQuantity;
	}
	public Integer getDefectiveQuantity() {
		return defectiveQuantity;
	}
	public void setDefectiveQuantity(Integer defectiveQuantity) {
		this.defectiveQuantity = defectiveQuantity;
	}
	public String getLogisticsProviderCode() {
		return logisticsProviderCode;
	}
	public void setLogisticsProviderCode(String logisticsProviderCode) {
		this.logisticsProviderCode = logisticsProviderCode;
	}
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}
	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}					
	
	

}
