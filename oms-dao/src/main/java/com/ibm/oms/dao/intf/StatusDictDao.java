package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.sc.dao.BaseDao;
/**
 * StatusDict Data Access Object (DAO) interface.
 * Creation date:2014-04-04 10:47:20
 * @author:Yong Hong Luo
 */
public interface StatusDictDao extends BaseDao<StatusDict,Long>{
	/**
	 * 根据statusTypeCode查询(根据statusCode升序排列)
	 * @param statusTypeCode
	 * @return
	 */
	List<StatusDict> findStatusDictByStatusTypeCode(String statusTypeCode);
}
