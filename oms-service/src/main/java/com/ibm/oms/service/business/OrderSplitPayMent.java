package com.ibm.oms.service.business;

import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderPay;

/**
 * 均摊订单行级目前包括 优惠券和积分支付的均摊
 * @author wangchao
 *
 */
public interface OrderSplitPayMent {
	
	/**均摊订单行级目前包括 优惠券和积分支付的均摊
	 * 在pay_order保存<br>
	 * 在均摊信息保存在orderingitem中
	 * @param orderItems
	 * @param op
	 * @return
	 */
	public Map<String, OrderItemPay> splitPayMent(List<OrderItem> orderItems, OrderPay op);
}
