package com.ibm.oms.dao.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;

/**
 * OrderMain Data Access Object (DAO) interface. Creation date:2014-03-14
 * 04:20:47
 * 
 * @author:Yong Hong Luo
 */
public interface BaseOrderReportDao extends BaseDao<OrderReport, Long> {

	public Pager findByOrderReport(OrderReport orderReport, Pager pager);

	public List<OrderReport> findByOrderReport(OrderReport orderReport);

}
