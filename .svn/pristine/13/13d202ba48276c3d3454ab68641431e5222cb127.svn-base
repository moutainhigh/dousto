package com.ibm.oms.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;
import com.ibm.oms.dao.intf.MerchantBalanceDateDao;
import com.ibm.oms.dao.intf.MerchantBalanceDateLogDao;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.oms.domain.persist.MerchantBalanceDateLog;
import com.ibm.oms.service.MerchantBalanceDateService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
@Service("merchantBalanceDateService")
public class MerchantBalanceDateServiceImpl extends BaseServiceImpl<MerchantBalanceDate, Integer> implements MerchantBalanceDateService{
	private MerchantBalanceDateDao merchantBalanceDateDao;
	
	@Autowired
	private MerchantBalanceDateLogDao merchantBalanceDateLogDao;
	
	@Autowired
	public void setMerchantBalanceDateDao(MerchantBalanceDateDao merchantBalanceDateDao) {
		this.merchantBalanceDateDao = merchantBalanceDateDao;
		super.setBaseDao(merchantBalanceDateDao);
	}

	@Override
	public MerchantBalanceDate getByMerchantCode(String merchantCode) {
		MerchantBalanceDate merchantBalanceDate = merchantBalanceDateDao.getByField("merchantCode", merchantCode);
		if (null == merchantBalanceDate) {
			merchantBalanceDate = this.createDefaultMerchantBalanceDate(merchantCode);
		}
		return  merchantBalanceDate;
	}

	@Override
	public MerchantBalanceDate updateBalanceDateByMerchantCode(String merchantCode) {
		MerchantBalanceDate merchantBalanceDate = null;
		if (StringUtils.isEmpty(merchantCode)) {
		} else {
			merchantBalanceDate = this.getByMerchantCode(merchantCode);
			Date now = new Date();
			if (null == merchantBalanceDate) {
				merchantBalanceDate = this.createDefaultMerchantBalanceDate(merchantCode);
			} else {
				MerchantBalanceDateLog balanceDateLog = new MerchantBalanceDateLog();
				balanceDateLog.setBalanceDate(merchantBalanceDate.getSetDate());
				balanceDateLog.setMerchantCode(merchantCode);
				balanceDateLog.setOutCreatedTime(merchantBalanceDate.getCreatedTime());
				balanceDateLog.setCreatedTime(now);
				merchantBalanceDateLogDao.save(balanceDateLog);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(merchantBalanceDate.getSetDate());
				calendar.add(Calendar.DATE, 1);
				merchantBalanceDate.setSetDate(calendar.getTime());
				merchantBalanceDate.setUpdatedTime(now);
				merchantBalanceDateDao.update(merchantBalanceDate);
			}
		}
		
		return merchantBalanceDate;
	}

	@Override
	public List<MerchantBalanceDateLogDto> findBalanceLogs(String merchantCode, Date startDate, Date endDate) {
		return merchantBalanceDateLogDao.findBalanceLogs(merchantCode, startDate, endDate);
	}
	
	private MerchantBalanceDate createDefaultMerchantBalanceDate(String merchantCode){
		MerchantBalanceDate merchantBalanceDate = new MerchantBalanceDate();
		Date now = new Date();
		merchantBalanceDate.setSetDate(now);
		merchantBalanceDate.setMerchantCode(merchantCode);
		merchantBalanceDate.setCreatedTime(now);
		
		merchantBalanceDateDao.save(merchantBalanceDate);
		
		return merchantBalanceDate;
	}
	
	
}
