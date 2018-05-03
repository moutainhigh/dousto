/**
 * 
 */
package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaonanxiang
 *
 */
public class CommodityStockInfoOutPutDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CommodityStockInfoDTO> commodityStockInfo;

	public List<CommodityStockInfoDTO> getCommodityStockInfo() {
		return commodityStockInfo;
	}

	public void setCommodityStockInfo(List<CommodityStockInfoDTO> commodityStockInfo) {
		this.commodityStockInfo = commodityStockInfo;
	}
}
