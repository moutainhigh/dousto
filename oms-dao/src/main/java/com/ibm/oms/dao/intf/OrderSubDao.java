package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderSub Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:53
 * @author:Yong Hong Luo
 */
public interface OrderSubDao extends BaseDao<OrderSub,Long>{
	
    public List<OrderSub> getByOrderMainNo(String orderMainNo);
    
    public OrderSub getByOrderSubNo(String orderSubNo);
}
