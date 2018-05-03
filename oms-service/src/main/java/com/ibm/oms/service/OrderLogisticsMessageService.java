package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.client.dto.order.OrderLogisticsMessageClientDTO;
import com.ibm.oms.domain.persist.OrderLogisticsMessage;
import com.ibm.sc.service.BaseService;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月1日 
 */
public interface OrderLogisticsMessageService extends BaseService<OrderLogisticsMessage, Long>{
	

	/**
	 * Description:查询物流信息
	 * @param OrderNo
	 * @return
	 */
	public List<OrderLogisticsMessageClientDTO> findOrderLogisticsMessageByOrderSubNo(String OrderNo);

	/**
	 * Description:增加物流信息
	 * @param param
	 */
	public void saveOrderLogisticsMessage(String param);
	
	public List<OrderLogisticsMessage> findOrderLogisticsMessageByTeackingNumber(String TeackingNumber);
}
