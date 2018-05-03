package com.ibm.oms.dao.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;

public interface ServiceGoodsOrderReportDao extends BaseDao<OrderReport, Long> {

	public Pager<?> findByServiceOrderReport(OrderReport orderReport, Pager<?> pager);
	
	public List<OrderReport> findByServiceOrderReport(OrderReport orderReport);
}
