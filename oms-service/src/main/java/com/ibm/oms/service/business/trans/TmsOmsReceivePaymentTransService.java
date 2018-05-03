package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;

/**
 * @author liucy
 * 
 */
public interface TmsOmsReceivePaymentTransService {
	
	/**
	 * 
	 * 功能描述: 创建TMS支付信息
	 * 
	 * @param tmsPayDTO
	 * @return CommonOutputDTO
	 */
	public CommonOutputDTO updateTmsOmsPayment(TmsPayDTO tmsPayDTO);

}
