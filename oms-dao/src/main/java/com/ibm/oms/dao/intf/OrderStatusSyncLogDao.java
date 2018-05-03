package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.sc.dao.BaseDao;

import java.util.Collection;
import java.util.List;
/**
 * OrderStatusSyncLog Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:52
 * @author:Yong Hong Luo
 */
public interface OrderStatusSyncLogDao extends BaseDao<OrderStatusSyncLog,Long>{
	public void update(Collection<OrderStatusSyncLog> objs);

    /**
     * 通过指定条件获取OrderStatusSyncLog
     *
     * @param syncScene 同步场景
     * @param strStartDate log生成时间
     * @param strEndDate　log生成时间
     * @param orderNo 订单号
     * @param syncFlag 同步状态
     * @param size 记录数
     * @return
     */
    List<OrderStatusSyncLog> getPager(String syncScene, String strStartDate, String strEndDate, String orderNo, String syncFlag, Integer size);
	
}
