package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderStatusLog Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:51
 * @author:Yong Hong Luo
 */
public interface OrderStatusLogDao extends BaseDao<OrderStatusLog,Long>{
	
	Pager findOrderStatusLog(String orderNo , int statusType, Pager pager);
	

	/**
	 * Description:
	 * @param orderNo
	 * @return
	 */
	OrderStatusLog findSuspensionByOrderNo(String orderNo);
	
	/**
	 * 退款历史记录信息 (退换货单状态流转日志)
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderStatusLog> findByOrderNo(String orderNo);
}
