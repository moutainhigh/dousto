package com.ibm.oms.service.business;


public interface DrawbackService {
	
	/**
	 * 退款
	 * @param orderNo
	 * @return
	 */
	void returnPayment(String orderNo, String operator);
}
