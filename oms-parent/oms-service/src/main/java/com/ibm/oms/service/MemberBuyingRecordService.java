package com.ibm.oms.service;

import java.util.Date;

import com.ibm.sc.service.BaseService;
/**
 * CouponLog Data Access Object (DAO) interface.
 * Creation date:2014-05-30 02:00:04
 * @author:Yong Hong Luo
 */
public interface MemberBuyingRecordService extends BaseService{
	
	//查询购买数量
	int getNumberByMemberIdActivityCodeSkuIds(Long memberId,String activityCode,String skuIds);
	
	//查询购买次数
	int getTimesByMemberIdActivityCodeSkuId(Long memberId,String activityCode,Long skuId);
	
	//查询某商品在团购活动中已卖出的数量
	int getNumberByActivityCodeSkuIds(String activityCode,String skuIds);
	/**
	 * 有效时间内的团购商品已卖出数量查询
	 * @param activityCode 活动编号
	 * @param skuIds       sku码
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @return retrunNum
	 */
	public Long getGroupBuyingProductSaleNum(String activityCode,String skuIds,Date startDate,Date endDate);
	
}
