package com.ibm.oms.dao.intf;

import java.util.Date;
import java.util.List;

import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;
import com.ibm.oms.domain.persist.MerchantBalanceDateLog;
import com.ibm.sc.dao.BaseDao;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
public interface MerchantBalanceDateLogDao extends BaseDao<MerchantBalanceDateLog, Integer>{
	/**
	 * 统计结算详情
	 * Description:
	 * @param merchantCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<MerchantBalanceDateLogDto> findBalanceLogs(String merchantCode, Date startDate, Date endDate);
}
