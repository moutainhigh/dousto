
package com.ibm.oms.service;

import java.util.Date;
import java.util.List;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:51
 * @author:Yong Hong Luo
 */
public interface OrderStatusLogService extends BaseService<OrderStatusLog,Long>{
	
	Pager findOrderStstusLogByOrderNo(String orderNo , int statusType , Pager pager);
     
	void writeStatusLog(OrderMain om, OrderSub os, String previousStatus,String targetStatus, String operator, Date operateTime, String remark);
	
	/**
	 * 退款历史记录信息 (退换货单状态流转日志)
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderStatusLog> findByOrderNo(String orderNo);
}
