package com.ibm.oms.rs.service.hessian.impl.member;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.client.intf.IMemberBuyingRecordServiceRpc;
import com.ibm.oms.service.MemberBuyingRecordService;

@Repository("memberBuyingRecordServiceRpc")
public class MemberBuyingRecordServiceRpcImpl implements IMemberBuyingRecordServiceRpc {
	
	@Autowired
	MemberBuyingRecordService memberBuyingRecordService;
	
	@Override
	public int getNumberByMemberIdActivityCodeSkuIds(Long memberId, String activityCode, String skuIds) {
		
		return memberBuyingRecordService.getNumberByMemberIdActivityCodeSkuIds(memberId, activityCode, skuIds);
	}

	@Override
	public int getTimesByMemberIdActivityCodeSkuId(Long memberId, String activityCode, Long skuId) {
		
		return memberBuyingRecordService.getTimesByMemberIdActivityCodeSkuId(memberId, activityCode, skuId);
	}

	@Override
	public int getNumberByActivityCodeSkuIds(String activityCode, String skuIds) {
		
		return memberBuyingRecordService.getNumberByActivityCodeSkuIds(activityCode, skuIds) ;
	}

	@Override
	public Long getGroupBuyingProductSaleNum(String activityCode, String skuIds, Date startDate, Date endDate) {
		
		return memberBuyingRecordService.getGroupBuyingProductSaleNum(activityCode, skuIds, startDate, endDate);
	}
	
}
