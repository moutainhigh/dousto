package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;

public interface BtcOrderPaymentTransService {
	
	CommonOutputDTO updateBtcOrderPayment(BtcPayDTO payDto);
	/**btc同时给多条消息过来，为了最大可能防止并发，首先更新支付状态**/
    boolean updateStatusPay(BtcPayDTO payDto, CommonOutputDTO commonDto);
}
