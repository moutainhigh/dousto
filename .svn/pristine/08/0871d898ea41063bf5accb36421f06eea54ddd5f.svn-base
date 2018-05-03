package com.ibm.oms.rs.service.hessian.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.MerchantBalanceDateDto;
import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;
import com.ibm.oms.client.intf.MerchantBalanceDateClient;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.oms.service.MerchantBalanceDateService;

/**
 * Description: 店铺结算日期实现
 * @author YanYanZhang
 * Date:   2018年3月9日 
 */
@Service("merchantBalanceDateClient")
public class MerchantBalanceDateClientImpl implements MerchantBalanceDateClient{

	@Autowired
	private MerchantBalanceDateService merchantBalanceDateService;
	
	@Override
	public Boolean updateBalanceDateByMerchantCode(String merchantCode) {
		MerchantBalanceDate balanceDate = merchantBalanceDateService.updateBalanceDateByMerchantCode(merchantCode);
		return null != balanceDate;
	}

	@Override
	public MerchantBalanceDateDto getBalanceDateByMerchantCode(String merchantCode) {
		MerchantBalanceDateDto result = new MerchantBalanceDateDto();
		BeanUtils.copyProperties(merchantBalanceDateService.getByMerchantCode(merchantCode), result);
		return result;
	}

	@Override
	public List<MerchantBalanceDateLogDto> findBalanceLogs(String merchantCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return merchantBalanceDateService.findBalanceLogs(merchantCode, startDate, endDate);
	}
}
