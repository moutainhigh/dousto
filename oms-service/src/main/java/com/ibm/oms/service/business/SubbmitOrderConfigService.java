package com.ibm.oms.service.business;



/**
 * 
 * 提交订单的时候
 * @author wangchao
 *
 * @param <T>
 */
public interface SubbmitOrderConfigService<T> {
	/**
	 * 提交订单的时候配置默认值
	 */
	public T conifigDefaultValue(T t);
}
