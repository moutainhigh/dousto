package com.ibm.oms.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.MemberBuyingRecordDao;
import com.ibm.oms.service.MemberBuyingRecordService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2011-04-13 11:45:41
 * 
 * @author��Yong Hong Luo
 */
@Service("memberBuyingRecordService")
public class MemberBuyingRecordServiceImpl extends BaseServiceImpl implements MemberBuyingRecordService {

	
	private MemberBuyingRecordDao memberBuyingRecordDao;
	
	@Autowired
	public void setMemberBuyingRecordDao(MemberBuyingRecordDao memberBuyingRecordDao) {
	    super.setBaseDao(memberBuyingRecordDao);
		this.memberBuyingRecordDao = memberBuyingRecordDao;
	}
	
	/*
	 * 会员已购买某商品数量
	 */
	@Override
	public int getNumberByMemberIdActivityCodeSkuIds(Long memberId,
			String activityCode, String skuIds) {
		return memberBuyingRecordDao.getNumberByMemberIdActivityCodeSkuIds(memberId, activityCode, skuIds);
	}

	/*
	 * 会员已购买某商品次数
	 */
	@Override
	public int getTimesByMemberIdActivityCodeSkuId(Long memberId,
			String activityCode, Long skuId) {
		return memberBuyingRecordDao.getTimesByMemberIdActivityCodeSkuId(memberId, activityCode, skuId);
	}

	/*
	 * 查询某商品在团购活动中已卖出的数量
	 */
	@Override
	public int getNumberByActivityCodeSkuIds(String activityCode,
			String skuIds) {
		return memberBuyingRecordDao.getNumberByActivityCodeSkuIds(activityCode, skuIds);
	}

	@Override
	public Long getGroupBuyingProductSaleNum(String activityCode, String skuIds, Date startDate, Date endDate) {
		return memberBuyingRecordDao.getGroupBuyingProductSaleNum(activityCode, skuIds, startDate, endDate);
	}
	

}
