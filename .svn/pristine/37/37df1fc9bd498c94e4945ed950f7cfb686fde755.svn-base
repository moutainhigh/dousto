package com.ibm.oms.service.business;

import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;

/**
 * 
 * 订单创建的配置
 * T为创建订单的不同DTO的泛型
 * @author ChaoWang
 *
 */
public interface OrderCreateServiceConfigService<T>{
	
	/**
	 * 订单创建的时候配置默认值-创建之前设置
	 */
	public T conifigDefaultValueBefore(T t,BtcOmsReceiveOrderOutputDTO output);
	
	/**订单创建之后处理
	 * @param t
	 * @param output
	 * @return
	 */
	public void handleCreateOrderAfter(ContextBtcOmsReceiveDTO context,BtcOmsReceiveOrderOutputDTO output);
	
}
