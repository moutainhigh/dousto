package com.ibm.oms.service.business;

import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;

public interface BtcOrderPaymentService {
	
	CommonOutputDTO handlerBtcOrderPayment(BtcPayDTO payDto);
}
