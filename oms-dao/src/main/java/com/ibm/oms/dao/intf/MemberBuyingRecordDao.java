package com.ibm.oms.dao.intf;

import java.util.Date;

import com.ibm.sc.dao.BaseDao;
/**
 * CouponLog Data Access Object (DAO) interface.
 * Creation date:2014-05-30 02:00:04
 * @author:Yong Hong Luo
 */
public interface MemberBuyingRecordDao extends BaseDao{
	
	int getNumberByMemberIdActivityCodeSkuIds(Long memberId,String activityCode,String skuIds);

	int getTimesByMemberIdActivityCodeSkuId(Long memberId,String activityCode, Long skuId);

	int getNumberByActivityCodeSkuIds(String activityCode, String skuIds);
	
	Long getGroupBuyingProductSaleNum(String activityCode,String skuIds,Date startDate,Date endDate);
}
