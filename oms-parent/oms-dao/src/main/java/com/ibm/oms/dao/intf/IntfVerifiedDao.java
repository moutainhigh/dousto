package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.sc.dao.BaseDao;
/**
 * IntfVerified Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:18:41
 * @author:Yong Hong Luo
 */
public interface IntfVerifiedDao extends BaseDao<IntfVerified,Long>{

    List<IntfVerified> findByIntfCodeAndCount(String intfCode, int count, String processFlag);
	
}
