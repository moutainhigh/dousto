package com.ibm.oms.service;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.E3ResultDTO;

/**
 * Description: E3接口对接类
 * @author YanYanZhang
 * Date:   2018年4月27日 
 */
public interface E3OrderService {
	/**
	 * orderMain orderItems必填
	 * Description:锁定库存
	 * @param orderMain
	 * @return
	 */
	E3ResultDTO lockStock(OrderMain orderMain);
	
	/**
	 *  orderMain orderItem必填
	 * Description:释放库存
	 * @param orderMain
	 * @return
	 */
	E3ResultDTO resumeStock(OrderMain orderMain);
	
	/**
	 * OrderMain orderItems 必填
	 * Description:订单发货
	 * @param orderMain
	 * @return
	 */
	E3ResultDTO delivery(OrderMain orderMain);
	
	/**
	 * 
	 * Description:取消发货
	 * @param orderNo 订单号
	 * @return
	 */
	E3ResultDTO cancelDelivery(String orderNo);
}
