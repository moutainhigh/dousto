package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderCombineRelation Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:19:15
 * @author:Yong Hong Luo
 */
public interface OrderCombineRelationDao extends BaseDao<OrderCombineRelation,Long>{

    List<OrderCombineRelation> findByOrderItemNoAndCombineNo(String orderItemNo, String combineNo);
	
}
