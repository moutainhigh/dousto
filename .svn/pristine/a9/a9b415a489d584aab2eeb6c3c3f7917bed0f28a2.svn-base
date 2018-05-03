package com.ibm.oms.dao.intf;

import java.util.Map;

import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderOperateLog Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:48
 * @author:Yong Hong Luo
 */
public interface OrderOperateLogDao extends BaseDao<OrderOperateLog,Long>{
	Pager getPagerByMap(Map<String, String> map, Pager pager);
	Pager findPageOrderOperateLog(OrderOperateLog log ,Map<String, String> map,Pager pager);
}
