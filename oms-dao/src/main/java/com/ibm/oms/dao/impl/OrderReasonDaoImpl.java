package com.ibm.oms.dao.impl;


import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderReasonDao;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-04-16 03:07:06
 * @author:Yong Hong Luo
 */
@Repository("orderReasonDao")
public class OrderReasonDaoImpl extends BaseDaoImpl<OrderReason,Long> implements OrderReasonDao{
       
	
}
