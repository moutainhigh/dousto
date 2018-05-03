package com.ibm.oms.client.intf;

public interface PayOrderService {
	/**
	 * 预下单支付（导购APP）
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendImprestPay(String jsonObj) throws Exception;
	/**
	 * 下单支付（微信在线商城）
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendCreatePay(String jsonObj) throws Exception;
	/**
	 * 下单支付(门店pos)
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendPay(String jsonObj) throws Exception;
	 /**
	 * 下单支付查询
	 * @param jsonObj
	 * @throws Exception
	 */
	
	public  String sendPayQuery(String jsonObj) throws Exception;
	/**
	 * 退款
	 * @param jsonObj
	 * @throws Exception
	 */
	
	public  String refundPay(String jsonObj) throws Exception;
	/**
	 * 退款查询
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String refundQuery(String jsonObj) throws Exception;
	/**
	 * 撤销订单
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String cancelOrder(String jsonObj) throws Exception;
	/**
	 * 支付接收异步通知回调地址
	 * @param xmlObj
	 * @return
	 * @throws Exception
	 */
	public  String payOrderCallbackInfo(String xmlObj) throws Exception;
	
}
