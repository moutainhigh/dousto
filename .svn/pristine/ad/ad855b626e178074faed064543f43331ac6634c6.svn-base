package com.ibm.oms.service.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

public interface ServiceGoodsReportService extends BaseService<OrderReport, Long> {

	/**
	* 分页查询
	* 
	* @param OrderReport 查询对象
	* @param pager 分页对象
	* @return
	*/
	public Pager<?> findByServiceOrderReport(OrderReport orderReport,Pager<?> pager);
	
	/**
	 * 非分页查询
	 * 
	 * @param OrderReport 查询对象
	 * @return
	 */
	public List<OrderReport> findByServiceOrderReport(OrderReport orderReport);
}
