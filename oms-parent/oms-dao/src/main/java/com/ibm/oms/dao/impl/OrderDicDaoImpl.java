package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderDicDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-04-29 01:49:04
 * 
 * @author:Yong Hong Luo
 */
@Repository("orderDicDao")
public class OrderDicDaoImpl extends BaseDaoImpl<OrderDic, Long> implements OrderDicDao {

}
