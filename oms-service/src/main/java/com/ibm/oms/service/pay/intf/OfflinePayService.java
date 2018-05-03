package com.ibm.oms.service.pay.intf;
/**
 * 线下支付（门店）
 * @author GangYang
 *
 */
public interface OfflinePayService {
	/**
	 * 下单支付
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
}
