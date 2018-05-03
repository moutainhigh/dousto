package com.ibm.oms.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.MemberBuyingRecordDao;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-05-30 02:00:04
 * @author:Yong Hong Luo
 */
@Repository("memberBuyingRecordDao")
public class MemberBuyingRecordDaoImpl extends BaseDaoImpl implements MemberBuyingRecordDao{

	private final String removeStatus = " om.status_total!='0131' and om.status_total!='0153' and om.status_total!='0142' and om.status_total!='0111' and om.status_total!='0112' and om.status_total!='0113' ";
	
	/*
	 * 会员已购买某商品数量
	 */
	@Override
	public int getNumberByMemberIdActivityCodeSkuIds(Long memberId,
			String activityCode, String skuIds) {
		int retrunNum = 0;
		String sql = "select ifnull(sum(oi.sale_num),0) from ORDER_ITEM oi left join Order_Main om on oi.id_order = om.id "
				+ " where "+removeStatus
				+ " and oi.sku_id in ("+skuIds+") "
				+ " and om.member_no='"+memberId+"' and oi.promotion_code='"+activityCode+"' ";
		Query query = getEntityManager().createNativeQuery(sql);
		
		retrunNum = ((BigDecimal)query.getSingleResult()).intValue();
		
		return retrunNum;
	}

	/*
	 * 会员已购买某商品或某活动次数
	 */
	@Override
	public int getTimesByMemberIdActivityCodeSkuId(Long memberId,
			String activityCode, Long skuId) {
		int retrunNum = 0;
		String sql;
		if(null != skuId){
			sql = "select count(*) from order_item oi where oi.sku_id='"+skuId+"' and oi.promotion_code='"+activityCode+"' "
					+ "and oi.id_order in (select id from order_main om where om.member_no = '"+memberId+"' "
					+ "and "+removeStatus+") ";	
		}else{
			sql = "select count(*) from ORDER_ITEM oi left join Order_Main om on oi.id_order = om.id "
					+ " where "+removeStatus
					+ " and om.member_no='"+memberId+"' and oi.promotion_code='"+activityCode+"'";
		}
		Query query = getEntityManager().createNativeQuery(sql);
		Object i = query.getSingleResult();
		retrunNum = Integer.valueOf(i.toString());
		return retrunNum;
	}

	/*
	 * 查询某商品在某个活动中已卖出的数量
	 */
	@Override
	public int getNumberByActivityCodeSkuIds(String activityCode,
			String skuIds) {
		int retrunNum = 0;

		String sql = "select ifnull(sum(oi.sale_num),0) from ORDER_ITEM oi left join Order_Main om on oi.id_order = om.id "
				+ " where "+removeStatus
				+ " and oi.sku_id in ("+skuIds+") and oi.promotion_code='"+activityCode+"'";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		retrunNum = ((BigDecimal)query.getSingleResult()).intValue();
		
		return retrunNum;
  	} 
	
	/**
	 * 有效时间内的团购商品已卖出数量查询
	 * @param activityCode 活动编号
	 * @param skuIds       sku码
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @return retrunNum
	 */
	public Long getGroupBuyingProductSaleNum(String activityCode,String skuIds,Date startDate,Date endDate){
		Long retrunNum;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer("select ifnull(sum(oi.sale_num),0) from ORDER_ITEM oi left join Order_Main om on oi.id_order = om.id "
				+ " where "+removeStatus
				+ " and oi.sku_id in (:skuIds) and oi.promotion_code=:activityCode and om.date_created <=to_date('" + sdf.format(endDate) + "','yyyy-mm-dd hh24:mi:ss') ");
		if(startDate!=null){
			sql.append(" and om.date_created >=to_date('" + sdf.format(startDate) + "','yyyy-mm-dd hh24:mi:ss')");
		}
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter("skuIds", skuIds);
		query.setParameter("activityCode", activityCode);
		retrunNum = ((BigDecimal)query.getSingleResult()).longValue();
		return retrunNum;
	}
	
}
