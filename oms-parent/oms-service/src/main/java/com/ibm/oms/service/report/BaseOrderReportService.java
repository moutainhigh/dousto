package com.ibm.oms.service.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer. Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
public interface BaseOrderReportService extends BaseService<OrderReport, Long> {

	/**
	 * 分页查询
	 * 
	 * @param orderReport 查询对象
	 * @param pager 分页对象
	 * @return Pager
	 */
	public Pager<?> findByOrderReport(OrderReport orderReport,Pager<?> pager);

	/**
	 * 非分页查询
	 * 
	 * @param orderReport 查询对象
	 * @return list
	 */
	public List<OrderReport> findByOrderReport(OrderReport orderReport);
}
