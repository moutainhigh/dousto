package com.ibm.oms.service;


import com.ibm.oms.domain.persist.OrderLogistics;
import com.ibm.sc.service.BaseService;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月15日 
 */

public interface OrderLogisticsService extends BaseService<OrderLogistics, Long>{

	/**
	 * Description:
	 * @param param
	 */
	void saveOrderLogistic(String param);
	
}
