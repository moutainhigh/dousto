package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;

/**
 * @author liucy
 * 
 */
public interface WmsOmsLogisticsStatusTransService {

	/**
	 * 
	 * 功能描述: 创建WMS或者TMS状态信息
	 * 
	 * @param wmsOmsReceiveLogisticsDTO
	 * @return CommonOutputDTO
	 */
	public CommonOutputDTO updateOrderLogisticsStatus(WmsOmsReceiveLogisticsDTO wmsOmsReceiveLogisticsDTO);

//    String queryToTmsStr(String orderSubNo);
}
