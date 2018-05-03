package com.ibm.oms.service.pay.intf;

import com.ibm.oms.service.pay.dto.MiyaPayOnLineInputDto;

public interface PayDataService {
	/**
	 * 获取订单信息
	 * @param orderNo 订单号
	 * @param payType 支付方式 微信/支付宝/其他 
	 * @return
	 * @throws Exception
	 */
	public MiyaPayOnLineInputDto getOrderInfo(String orderNo,String payType) throws Exception;
	/**
	 * 支付返回消息写入日志
	 * @param respXml  返回xml
	 * @throws Exception
	 */
	public String writeOrderLogs(String respXml) throws Exception;
	
}
