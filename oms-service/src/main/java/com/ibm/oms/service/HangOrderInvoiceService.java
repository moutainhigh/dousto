package com.ibm.oms.service;

import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.sc.service.BaseService;
/**
 * @author wangqc
 * @date 2018年2月5日 下午2:59:08
 * 
 */
public interface HangOrderInvoiceService extends BaseService<HangOrderInvoice, Long>{
	void deleteByIdOrder(long orderId);
}
