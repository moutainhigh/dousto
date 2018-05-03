package com.ibm.oms.service.report;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer. Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
public interface ToDoListService extends BaseService<OrderReport, Long> {

	/**
	 * 非分页查询
	 * 
	 * @param OrderReport 查询对象
	 * @return list
	 */
	public OrderReport findToDoList(OrderReport orderReport);
	
}
