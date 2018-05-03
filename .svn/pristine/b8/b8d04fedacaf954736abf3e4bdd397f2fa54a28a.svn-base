package com.ibm.oms.client.intf;

import java.util.Date;
import java.util.List;

import com.ibm.oms.client.dto.MerchantBalanceDateDto;
import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;

/**
 * Description: 店铺结算日期
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
public interface MerchantBalanceDateClient {
	/**
	 * 更新店铺结算时间
	 * 当前日期想前滚动一天
	 * 如果当前日期为空，则新增一条当前日期的数据
	 * Description:
	 * @param merchantCode
	 * @return 操作结果标识
	 */
	Boolean updateBalanceDateByMerchantCode(String merchantCode);
	
	/**
	 * 
	 * Description:
	 * @param merchantCode  店铺编码
	 * @return
	 */
	MerchantBalanceDateDto getBalanceDateByMerchantCode(String merchantCode);
	
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
