package com.ibm.oms.service.business;

/**
 * 
 * 订单创建的配置
 * T为创建订单的不同DTO的泛型
 * @author ChaoWang
 *
 */
public interface OrderCreateServiceConfigService<T>{
	
	/**
	 * 订单创建的时候配置默认值
	 */
	public T conifigDefaultValue(T t);
}
