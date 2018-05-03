package com.ibm.oms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.intf.HangOrderSubDao;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.sc.service.BaseService;

/**
 * @author wangqc
 * @date 2018年2月5日 下午2:57:43
 * 
 */
public interface HangOrderSubService extends BaseService<HangOrderSub, Long>{

	void deleteByIdOrder(long orderId);

}
