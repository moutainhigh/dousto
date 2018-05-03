package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderInvoicePrintDao;
import com.ibm.oms.domain.persist.OrderInvoicePrint;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:19:49
 * @author:Yong Hong Luo
 */
@Repository("orderInvoicePrintDao")
public class OrderInvoicePrintDaoImpl extends BaseDaoImpl<OrderInvoicePrint,Long> implements OrderInvoicePrintDao{
       
	
}
