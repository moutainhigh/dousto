package com.ibm.oms.intf.intf;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liucy
 * 
 * 商品行商品出库成本价
 */
public class InventoryUnLockDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 子订单行号 **/
	private String orderItemNo;
	/**商品编码**/
	private String commodityCode;
	/**供应商**/
	private String supplierCode;
	/**库位**/
	private String stockArea;
	/**数量**/
	private String saleNum;
	/**备注**/
	private String remarks;


	public String getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}


	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getStockArea() {
		return stockArea;
	}

	public void setStockArea(String stockArea) {
		this.stockArea = stockArea;
	}

	public String getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
}
