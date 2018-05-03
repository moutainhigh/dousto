package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.MerchantBalanceDateLogDao;
import com.ibm.oms.domain.persist.MerchantBalanceDateLog;
import com.ibm.oms.service.MerchantBalanceDateLogService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
@Service("merchantBalanceDateLogService")
public class MerchantBalanceDateLogServiceImpl extends BaseServiceImpl<MerchantBalanceDateLog, Integer> implements MerchantBalanceDateLogService{
	private MerchantBalanceDateLogDao merchantBalanceDateLogDao;

	@Autowired
	public void setMerchantBalanceDateLogDao(MerchantBalanceDateLogDao merchantBalanceDateLogDao) {
		this.merchantBalanceDateLogDao = merchantBalanceDateLogDao;
		super.setBaseDao(merchantBalanceDateLogDao);
	}
	
	
}
