package com.ibm.oms.service.business;

import java.text.ParseException;

public  interface CancelNoPayOrderService {
	void cancelNoPayOrder(int row) throws ParseException;

    void remindMsgSent();
}
