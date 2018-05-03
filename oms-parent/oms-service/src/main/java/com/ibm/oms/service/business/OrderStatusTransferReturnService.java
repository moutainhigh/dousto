package com.ibm.oms.service.business;

import com.ibm.oms.client.dto.order.OrderSplitTransferReturnClientDTO;

/**
 * 
 * @author ChaoWang
 * 订单状态回传 
 *
 */
public interface OrderStatusTransferReturnService {
	
	
	/**收到WMS确定发货通知以后需要回传订单状态 
	 * 1、百胜
	 * @return
	 */
	public OrderSplitTransferReturnClientDTO  handleOrderStatusTransferReturn(String orderSubNo);
	
	
}
