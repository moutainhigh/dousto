package com.ibm.oms.service.business;

import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.intf.intf.CommonOutputDTO;

public interface OrderPaymentService {
	
	/**
	 * 创建订单货到付款的支付信息(TMS上传)接口编号
	 */
	String Tms_Order_Payment = "TMS_ORDER_PAYMENT";
	
	/**
	 * 创建订单货到付款的支付信息(BTC上传)接口编号
	 */
	String Btc_Order_Payment = "BTC_ORDER_PAYMENT";

	/**
	 * 
	 * 功能描述: 创建订单货到付款的支付信息(人工修改支付明细)
	 * 
	 * @param orderPay
	 * @return CommonOutputDTO
	 */
	public boolean saveOrderPaymentFromPage(OrderPay orderPay);
	
	/**
	 * 
	 * 功能描述: 删除订单支付信息(人工删除支付明细)
	 * 
	 * @param id
	 * @return CommonOutputDTO
	 */
	public boolean deleteOrderPaymentFromPage(String id);

}
