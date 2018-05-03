package com.ibm.oms.service.business;

import java.util.List;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;

/**
 * @author liucy
 *
 */
@Deprecated
public interface WmsOmsCostPriceService {
	/**
	 * 
	 * 功能描述: WMS将订单商品的出库成本上传到OMS
	 * 
	 * @param receiveCostPriceDTO
	 * @return CommonOutputDTO
	 */
	@Deprecated
	public CommonOutputDTO handlerUpdateSingleOrderCostPrice(WmsReceiveCostPriceDTO receiveCostPriceDTO);
	@Deprecated
	public ResponseObjectDTO handlerUpdateOrderCostPrice(List<WmsReceiveCostPriceDTO> receive);


/*	public CommonOutputDTO updateOrderCostPrice(WmsReceiveCostPriceDTO receiveCostPriceDTO);*/
	
}
