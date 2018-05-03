package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.report.CMBPaymentReportDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateStringUtils;
import com.ibm.sc.util.DateUtils;

@Repository("cMBPaymentReportDao")
public class CMBPaymentReportDaoImpl extends BaseDaoImpl<OrderReport, Long> implements CMBPaymentReportDao {

	@Override
	public Pager findByCMBPaymentReport(OrderReport orderReport, Pager pager) {
		StringBuffer queryHqlHeader = new StringBuffer();
		StringBuffer totalHqlHeader = new StringBuffer();
		queryHqlHeader.append("select os.order_sub_no,om.order_time,op.pay_amount,op.pay_time,op.serial_no,po.id");
		totalHqlHeader.append("select count(1) ");

		StringBuffer searchConditions = setSearchConditions(orderReport);
		SQLQuery totalQuery = getSession().createSQLQuery(totalHqlHeader.append(searchConditions).toString());
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());

		// 查总数
		List totalList = totalQuery.list();
		Object totalObj = (Object) totalList.get(0);
		pager.setTotalCount(Integer.parseInt(totalObj.toString()));
		// 查分页记录
		recordQuery.setFirstResult(pager.getStart()).setMaxResults(pager.getPageSize());

		List<OrderReport> orderReportLists = initOrderReportLists(recordQuery.list());
		
		pager.setList(orderReportLists);
		return pager;
	}

	@Override
	public List<OrderReport> findByCMBPaymentReport(OrderReport orderReport) {
		StringBuffer queryHqlHeader = new StringBuffer();
		queryHqlHeader.append("select os.order_sub_no,om.order_time,op.pay_amount,op.pay_time,op.serial_no,po.id");
		StringBuffer searchConditions = setSearchConditions(orderReport);
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());
		
		List<OrderReport> orderReportLists = initOrderReportLists(recordQuery.list());
		
		return orderReportLists;
	}
	
	private StringBuffer setSearchConditions(OrderReport orderReport) {
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		from.append(" from order_main om,order_sub os,order_pay op,pm_online_payment_order po");
		where.append(" where om.id = os.id_order and om.id = op.id_order and po.reference_no = op.serial_no and op.pay_code = '50400'");
		orderBy.append(" order by om.order_time desc, os.order_sub_no desc");
	
		// 下单开始日期和下单结束日期
		Date orderTimeFrom = orderReport.getOrderTimeFrom();
		Date orderTimeTo = orderReport.getOrderTimeTo();
		if (null != orderTimeFrom && null != orderTimeTo) {
			where.append(" and om.order_time >= " + "to_date('" + DateUtils.formatGeneralDate(orderTimeFrom) + "','YYYY-MM-DD')");
			where.append(" and om.order_time < " + "to_date('" + DateUtils.formatGeneralDate(orderTimeTo) + "' ,'YYYY-MM-DD') + 1");
		} else if (null != orderTimeFrom) {
			where.append(" and om.order_time >= " + "to_date('" + DateUtils.formatGeneralDate(orderTimeFrom) + "','YYYY-MM-DD')");
			where.append(" and om.order_time < " + "to_date('" + DateUtils.formatGeneralDate(orderTimeFrom) + "' ,'YYYY-MM-DD') + 1");
		} else if (null != orderTimeTo) {
			where.append(" and om.order_time >= " + "to_date('" + DateUtils.formatGeneralDate(orderTimeTo) + "','YYYY-MM-DD')");
			where.append(" and om.order_time < " + "to_date('" + DateUtils.formatGeneralDate(orderTimeTo) + "' ,'YYYY-MM-DD') + 1");
		}
		return from.append(where).append(orderBy);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<OrderReport> initOrderReportLists(List list) {

		List<OrderReport> orderReportLists = new ArrayList<OrderReport>();

		for (Object objArray : list) {
			OrderReport orderReport = new OrderReport();
			Object[] obj = (Object[]) objArray;
			orderReport.setOrderSubNo(DateStringUtils.encodeHTML(obj[0]));
			if (null != obj[1] && !DateStringUtils.isEmpty(obj[1].toString())) {
				orderReport.setOrderTime(DateStringUtils.parseDate(obj[1].toString()));
			}			
			if (null != obj[2] && !DateStringUtils.isEmpty(obj[2].toString())) {
				orderReport.setPayAmount(DateStringUtils.strToBigDecimal(obj[2].toString()));
			}
			if (null != obj[3] && !DateStringUtils.isEmpty(obj[3].toString())) {
				orderReport.setPayTime(DateStringUtils.parseDate(obj[3].toString()));
			}	
			orderReport.setSerialNo(DateStringUtils.encodeHTML(obj[4]));
			orderReport.setCmbOrderId(Long.parseLong(DateStringUtils.encodeHTML(obj[5])));
			orderReportLists.add(orderReport);
		}
		return orderReportLists;
	}

}
