package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderPayModeDao;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-05-08 09:28:37
 * 
 * @author:Yong Hong Luo
 */
@Repository("orderPayModeDao")
public class OrderPayModeDaoImpl extends BaseDaoImpl<OrderPayMode, Long> implements OrderPayModeDao {

}
