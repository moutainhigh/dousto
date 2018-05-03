
package com.ibm.oms.service;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.sc.service.BaseService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:52
 * @author:Yong Hong Luo
 */
public interface OrderStatusSyncLogService extends BaseService<OrderStatusSyncLog,Long>{
	
	List<OrderStatusSyncLog> getLogListByMap(Map<String,Object> map);
	
    void update(Collection<OrderStatusSyncLog> objs) ;
    
    void saveAndcreate(OrderMain om, OrderSub os, String syncScene);
    
    List<OrderStatusSyncLog> getLogListByDate(String syncScene, String strStartDate, String strEndDate, String orderNo, String syncFlag, Integer size);

    String checkDataValid(OrderMain om, String syncScene);

    String getOrderSource(OrderMain orderMain);
	
}
