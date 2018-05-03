package com.ibm.oms.service.pay.intf;
/**
 * 线上支付（导购App,在线微信商城）
 * @author GangYang
 *
 */
public interface OnlinePayService {
	/**
	 * 预下单支付(导购APP)
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendImprestPay(String jsonObj) throws Exception;
	/**
	 * 下单支付(微信在线商城)
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendCreatePay(String jsonObj) throws Exception;
	/**
	 * 下单支付（门店pos）
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
	 * 回调异步通知接口 
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String callback(String jsonObj) throws Exception;
	 
}
