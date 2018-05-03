package com.ibm.oms.dao.intf;

import java.util.Map;

import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * IntfSent Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:18:20
 * @author:Yong Hong Luo
 */
public interface IntfSentDao extends BaseDao<IntfSent,Long>{
	public Pager getPagerByMap(Map<String, String> map, Pager pager);
}
