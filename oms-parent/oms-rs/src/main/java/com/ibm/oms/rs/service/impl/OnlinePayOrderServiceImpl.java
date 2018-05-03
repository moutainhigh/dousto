package com.ibm.oms.rs.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.client.intf.PayOrderService;
import com.ibm.oms.rs.service.OnlinePayOrderService;
import com.ibm.oms.service.pay.intf.OnlinePayService;
@Component("onlinePayOrderService")
public class OnlinePayOrderServiceImpl implements PayOrderService{
	@Autowired
    private OnlinePayService onlinePayService;
	@Override
	public String sendImprestPay(String jsonObj) throws Exception {
		String sendImprestPay = onlinePayService.sendImprestPay(jsonObj);
		return sendImprestPay;
	}
	@Override
	public String sendPay(String jsonObj) throws Exception {
		String sendPay = onlinePayService.sendPay(jsonObj);
		return sendPay;
	}
	@Override
	public String sendPayQuery(String jsonObj) throws Exception {
		String sendPayQuery = onlinePayService.sendPayQuery(jsonObj);
		return sendPayQuery;
	}
	@Override
	public String refundPay(String jsonObj) throws Exception {
		String refundPay = onlinePayService.refundPay(jsonObj);
		return refundPay;
	}

	@Override
	public String refundQuery(String jsonObj) throws Exception {
		String refundQuery = onlinePayService.refundQuery(jsonObj);
		return refundQuery;
	}

	@Override
	public String cancelOrder(String jsonObj) throws Exception {
		String cancelOrder = onlinePayService.cancelOrder(jsonObj);
		return cancelOrder;
	}
	@Override
	public String sendCreatePay(String jsonObj) throws Exception {
		String sendCreatePay = onlinePayService.sendCreatePay(jsonObj);
		return sendCreatePay;
	}
	@Override
	public String payOrderCallbackInfo(String xmlObj) throws Exception {
		String payOrderCallbackInfo = onlinePayService.payOrderCallbackInfo(xmlObj);
		return payOrderCallbackInfo;
	}

}
