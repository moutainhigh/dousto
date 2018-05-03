package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;

/**
 * @author liucy
 *
 */
public interface WmsOmsCostPriceTransService {

	/**
	 * 
	 * 功能描述: WMS将订单商品的出库成本上传到OMS
	 * 
	 * @param receiveCostPriceDTO
	 * @return CommonOutputDTO
	 */
	public CommonOutputDTO updateOrderCostPrice(WmsReceiveCostPriceDTO receiveCostPriceDTO);
	
}
