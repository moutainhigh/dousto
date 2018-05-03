package com.ibm.oms.service.business;


public  interface CancelNoPayOrderService {
	void cancelNoPayOrder(int row);

    void remindMsgSent();
}
