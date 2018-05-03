package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderItemAbnDao;
import com.ibm.oms.domain.persist.OrderItemAbn;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:12
 * @author:Yong Hong Luo
 */
@Repository("orderItemAbnDao")
public class OrderItemAbnDaoImpl extends BaseDaoImpl<OrderItemAbn,Long> implements OrderItemAbnDao{
       
	
}
