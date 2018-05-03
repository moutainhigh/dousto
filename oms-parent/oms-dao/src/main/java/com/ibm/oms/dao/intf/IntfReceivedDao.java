package com.ibm.oms.dao.intf;

import java.util.Map;

import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * IntfReceived Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
public interface IntfReceivedDao extends BaseDao<IntfReceived,Long>{
	
	public Pager getPagerByMap(Map<String, String> map, Pager pager) ;

}
