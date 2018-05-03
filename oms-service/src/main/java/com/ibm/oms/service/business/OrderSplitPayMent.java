package com.ibm.oms.service.business;

import java.math.BigDecimal;
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
	/**积分支付均摊 = （商品折后金额%订单支付金额）*积分支付金额  
	优惠券均摊 = （商品折后金额%订单支付金额）*优惠券金额
	订单支付均摊 = （商品折后金额%订单支付金额）*订单支付金额
	 */
	public Map<String, OrderItemPay> splitPayMent(List<OrderItem> orderItems, OrderPay op);
	
}
