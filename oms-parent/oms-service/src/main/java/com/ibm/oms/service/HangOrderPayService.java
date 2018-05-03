package com.ibm.oms.service;

import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.oms.domain.persist.HangOrderPay;
import com.ibm.sc.service.BaseService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * @author wangqc
 * @date 2018年2月5日 下午3:00:19
 * 
 */
public interface HangOrderPayService extends BaseService<HangOrderPay, Long>{
	void deleteByIdOrder(long orderId);
}
