package com.ibm.oms.rs.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ibm.oms.rs.service.OfflinePayOrderService;
import com.ibm.oms.service.pay.intf.OfflinePayService;
@Component("offlinePayOrderService")
public class OfflinePayOrderServiceImpl implements OfflinePayOrderService{
	@Autowired
    private OfflinePayService offlinePayService;
	@Override
	public String sendPay(String jsonObj) throws Exception {
		String sendPay = offlinePayService.sendPay(jsonObj);
		return sendPay;
	}

	@Override
	public String sendPayQuery(String jsonObj) throws Exception {
		String sendPayQuery = offlinePayService.sendPayQuery(jsonObj);
		return sendPayQuery;
	}

	@Override
	public String refundPay(String jsonObj) throws Exception {
		String refundPay = offlinePayService.refundPay(jsonObj);
		return refundPay;
	}

	@Override
	public String refundQuery(String jsonObj) throws Exception {
		String refundQuery = offlinePayService.refundQuery(jsonObj);
		return refundQuery;
	}

	@Override
	public String cancelOrder(String jsonObj) throws Exception {
		String cancelOrder = offlinePayService.cancelOrder(jsonObj);
		return cancelOrder;
	}

}
