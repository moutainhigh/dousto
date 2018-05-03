package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liucy
 * 
 * 商品行商品出库成本价
 */
public class InventoryPriceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 子订单行号 **/
	private String orderItemNo;
	
	/** 出库成本价 **/
    private BigDecimal inventoryPrice;
    
    private List<CombinePriceDTO> compDTOs; 

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<CombinePriceDTO> getCompDTOs() {
		return compDTOs;
	}

	public void setCompDTOs(List<CombinePriceDTO> compDTOs) {
		this.compDTOs = compDTOs;
	}
	
	
}
