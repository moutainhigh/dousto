package com.ibm.oms.service;

import java.util.Date;
import java.util.List;

import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.sc.service.BaseService;

/**
 * Description: 店铺结算日期类
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
public interface MerchantBalanceDateService extends BaseService<MerchantBalanceDate, Integer>{
	/**
	 * 根据店铺编码查询
	 * Description:
	 * @param merchantCode
	 * @return
	 */
	MerchantBalanceDate getByMerchantCode(String merchantCode);
	
	/**
	 * 更新店铺结算时间
	 * 当前日期想前滚动一天
	 * 如果当前日期为空，则新增一条当前日期的数据
	 * Description:
	 * @param merchantCode
	 * @return 更新后對象
	 */
	MerchantBalanceDate updateBalanceDateByMerchantCode(String merchantCode);
	
	/**
	 * 查询结算记录
	 * Description:
	 * @param merchantCode 店铺编码
	 * @param startDate 开始日期 yyyy-MM-dd
	 * @param endDate  结束日期 yyyy-MM-dd
	 * @return
	 */
	List<MerchantBalanceDateLogDto> findBalanceLogs(String merchantCode, Date startDate, Date endDate);
}
