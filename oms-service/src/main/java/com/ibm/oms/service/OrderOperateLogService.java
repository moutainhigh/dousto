
package com.ibm.oms.service;

import java.util.Map;

import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:48
 * @author:Yong Hong Luo
 */
public interface OrderOperateLogService extends BaseService<OrderOperateLog,Long>{
	Pager getPagerByMap(Map<String, String> map ,Pager pager);
	
	Pager findPagerOrderOperateLog(OrderOperateLog log ,Map<String, String> map,Pager pager);
}
