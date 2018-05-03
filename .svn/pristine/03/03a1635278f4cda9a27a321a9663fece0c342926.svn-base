package com.ibm.oms.service.pay.intf;

/**
 * 线上支付（导购App,在线微信商城）
 * @author GangYang
 *
 */
public interface OnlinePayService {
	/**
	 * 预下单支付(导购APP) MY-C-01
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendImprestPay(String jsonObj) throws Exception;
	/**
	 * 下单支付(微信在线商城)MY-C-02
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendCreatePay(String jsonObj) throws Exception;
	/**
	 * 下单支付（门店pos） MY-C-03
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendPay(String jsonObj) throws Exception;
	/**
	 * 下单支付查询 MY-C-04
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String sendPayQuery(String jsonObj) throws Exception;
	/**
	 * 退款 MY-C-05
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String refundPay(String jsonObj) throws Exception;
	/**
	 * 退款查询 MY-C-06
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String refundQuery(String jsonObj) throws Exception;
	/**
	 * 撤销订单 MY-C-07
	 * @param jsonObj
	 * @throws Exception
	 */
	public  String cancelOrder(String jsonObj) throws Exception;
	/**
	 * 支付接收异步通知回调地址 MY-C-08
	 * @param xmlObj
	 * @return
	 * @throws Exception
	 */
	public  String payOrderCallbackInfo(String xmlObj) throws Exception;
}
