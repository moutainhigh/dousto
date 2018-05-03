package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderItemVirtual Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:46
 * @author:Yong Hong Luo
 */
public interface OrderItemVirtualDao extends BaseDao<OrderItemVirtual,Long>{
	
	List<OrderItemVirtual> getByOrdeNo(String orderNo);
	
}
