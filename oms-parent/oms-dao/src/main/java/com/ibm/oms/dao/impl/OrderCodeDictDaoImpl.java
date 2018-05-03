package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderCodeDictDao;
import com.ibm.oms.domain.persist.OrderCodeDict;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:19:04
 * @author:Yong Hong Luo
 */
@Repository("orderCodeDictDao")
public class OrderCodeDictDaoImpl extends BaseDaoImpl<OrderCodeDict,Long> implements OrderCodeDictDao{
       
	
}
