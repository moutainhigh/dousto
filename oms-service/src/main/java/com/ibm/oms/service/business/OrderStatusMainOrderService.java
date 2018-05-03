package com.ibm.oms.service.business;

public interface OrderStatusMainOrderService {
	/**更新主订单状态
	 * @return
	 */
	boolean updateMainOrderStatus(String splitOrderNo);

	/**是否是拆分单
	 * @param orderNo
	 * @return
	 */
	boolean isSplitOrder(String orderNo);
}
