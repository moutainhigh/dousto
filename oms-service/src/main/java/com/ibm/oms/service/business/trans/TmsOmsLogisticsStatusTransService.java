package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;

/**
 * @author liucy
 * 
 */
public interface TmsOmsLogisticsStatusTransService {

	/**
	 * 
	 * 功能描述: 创建TMS状态信息
	 * 
	 * @param tmsStatusDTO
	 * @return CommonOutputDTO
	 */
	public CommonOutputDTO updateOrderLogisticsStatus(TmsStatusDTO tmsStatusDTO);

    String queryToTmsStr(String orderSubNo, String orderType);

    void saveThirdTmsLog(TmsStatusDTO tmsStatusDTO);
}
