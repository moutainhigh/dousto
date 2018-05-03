package com.ibm.oms.dao.report;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.dao.BaseDao;

/**
 * OrderMain Data Access Object (DAO) interface. Creation date:2014-03-14
 * 04:20:47
 * 
 * @author:Yong Hong Luo
 */
public interface ToDoListDao extends BaseDao<OrderReport, Long> {

	public OrderReport findToDoList(OrderReport orderReport);
}
