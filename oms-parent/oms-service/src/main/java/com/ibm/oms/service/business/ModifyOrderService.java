package com.ibm.oms.service.business;

import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.CommonOutputDTO;

/**
 * @author liucy
 *
 */
public interface ModifyOrderService {
	
	/**
	 * 
	 * 功能描述: 修改子订单信息
	 * 
	 * @param OrderSub
	 * @return CommonOutputDTO
	 */
	public CommonOutputDTO updateOrderSub(OrderSub orderSub);
}
