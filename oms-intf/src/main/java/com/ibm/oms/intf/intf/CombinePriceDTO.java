package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liucy
 * 
 * 商品行商品出库成本价
 */
public class CombinePriceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 出库成本价 **/
    private BigDecimal inventoryPrice;
    
    /**
     * 产品编码
     */
    private String skuNo;

	public BigDecimal getInventoryPrice() {
		return inventoryPrice;
	}

	public void setInventoryPrice(BigDecimal inventoryPrice) {
		this.inventoryPrice = inventoryPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	
}
