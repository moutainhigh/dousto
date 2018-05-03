package com.ibm.oms.service.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

public interface CMBPaymentReportService extends BaseService<OrderReport, Long> {

	/**
	* 分页查询
	* 
	* @param OrderReport 查询对象
	* @param pager 分页对象
	* @return pager
	*/
	public Pager<?> findByCMBPaymentReport(OrderReport orderReport,Pager<?> pager);
	
	/**
	 * 非分页查询
	 * 
	 * @param OrderReport 查询对象
	 * @return list
	 */
	public List<OrderReport> findByCMBPaymentReport(OrderReport orderReport);
}
