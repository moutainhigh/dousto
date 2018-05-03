package com.ibm.oms.dao.intf;


import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderLogisticsMessage;
import com.ibm.sc.dao.BaseDao;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月1日 
 */
public interface OrderLogisticsMessageDao extends BaseDao<OrderLogisticsMessage,Long>{

	

	/**
	 * Description:通过子订单号查询物流信息		
	 * @param OrderNo
	 * @return
	 */
	List<OrderLogisticsMessage> findOrderLogisticsMessageByOrderSubNo(String OrderNo);

	/**
	 * Description:储存快递100返回物流数据
	 * @param map
	 */
	void saveOrderLogisticsMessage(Map map);

	/**通过
	 * Description:通过单号按时间顺序查询物流信息
	 */
	List<OrderLogisticsMessage> findOrderLogisticsMessageByTeackingNumber(String teackingNumber);

	
	
}
