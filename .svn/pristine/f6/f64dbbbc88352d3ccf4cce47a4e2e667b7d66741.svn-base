package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author liucy
 * 
 * WMS将商品出库成本传输至OMS
 */
public class WmsReceiveCostPriceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 子订单号 **/
	@NotBlank(message = "orderSubNo is compulsory")
	@Length(max = 20,message = "orderSubNo: length must be less than 20")
	private String orderSubNo;
	
	/** 子订单行号 **/
	@NotBlank(message = "orderItemNo is compulsory")
	private String orderItemNo;
	
	/** 出库成本价 **/
	@NotNull(message = "inventoryPrice is compulsory")
    private BigDecimal inventoryPrice;
    
    private List<CombinePriceDTO> compDTOs; 
    
    /**
     * 产品编码【1、非组合商品：条形码—barCode，2、组合商品：skuCode】
     */
    private String commodityCode;
	
	/** 订单操作时间 **/
	private String operateTime;
	
	/** 订单操作人 **/
	private String operator;
	
	/** 备注1 **/
	private String backup1;
	
	/** 备注2 **/
	private String backup2;

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}

	public BigDecimal getInventoryPrice() {
		return inventoryPrice;
	}

	public void setInventoryPrice(BigDecimal inventoryPrice) {
		this.inventoryPrice = inventoryPrice;
	}

	public List<CombinePriceDTO> getCompDTOs() {
		return compDTOs;
	}

	public void setCompDTOs(List<CombinePriceDTO> compDTOs) {
		this.compDTOs = compDTOs;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	
}
