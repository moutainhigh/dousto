package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.dao.report.OrderCategoryDao;
import com.ibm.oms.dao.report.ServiceGoodsOrderReportDao;
import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateStringUtils;
import com.ibm.sc.util.DateUtils;

@Repository("serviceGoodsOrderReportDao")
public class ServiceGoodsOrderReportDaoImpl extends BaseDaoImpl<OrderReport, Long> implements ServiceGoodsOrderReportDao  {

	private static final Long MAX_CATEGORY_LEVEL = 4L;
	
	@Autowired
	TransportAreaDao transportAreaDao;
	@Autowired
	OrderCategoryDao orderCategoryDao;
	
	@Override
	public Pager findByServiceOrderReport(OrderReport orderReport, Pager pager) {
		StringBuffer queryHqlHeader = new StringBuffer();
		StringBuffer totalHqlHeader = new StringBuffer();
		queryHqlHeader.append("select os.order_sub_no, om.order_time, oi.product_category, oi.product_category_name, oi.supplier_code, oi.commodity_name, oi.sale_num, oi.unit_deducted_price, oi.pay_amount, om.order_source, om.status_pay, om.status_confirm, os.logistics_status, om.status_total, os.user_name, os.distribute_type");
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
	public List<OrderReport> findByServiceOrderReport(OrderReport orderReport) {
		StringBuffer queryHqlHeader = new StringBuffer();
		queryHqlHeader.append("select os.order_sub_no, om.order_time, oi.product_category, oi.product_category_name, oi.supplier_code, oi.commodity_name, oi.sale_num, oi.unit_deducted_price, oi.pay_amount, om.order_source, om.status_pay, om.status_confirm, os.logistics_status, om.status_total, os.user_name, os.distribute_type");
		StringBuffer searchConditions = setSearchConditions(orderReport);
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());
		
		List<OrderReport> orderReportLists = initOrderReportLists(recordQuery.list());
		
		return orderReportLists;
	}
	
	private StringBuffer setSearchConditions(OrderReport orderReport) {
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		from.append(" from order_main om, order_sub os, order_item oi");
		where.append(" where om.id = os.id_order(+) and om.id = oi.id_order(+) and om.order_category = 'sale'");
		orderBy.append(" order by om.order_time desc");
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
		// 子订单号
		if (!StringUtils.isBlank(orderReport.getOrderSubNo())) {
			where.append(" and os.order_sub_no = '" + orderReport.getOrderSubNo() + "'");
		}		
		// 收件人
		if (!StringUtils.isBlank(orderReport.getUserName())) {
			where.append(" and os.user_name = '" + orderReport.getUserName() + "'");
		}
		// 审核状态
		if (!StringUtils.isBlank(orderReport.getStatusConfirm())) {
			where.append(" and om.status_confirm = '" + orderReport.getStatusConfirm() + "'");
		}		
		// 支付状态
		if (!StringUtils.isBlank(orderReport.getStatusPay())) {
			where.append(" and om.status_pay = '" + orderReport.getStatusPay() + "'");
		}
		// 物流状态
		if (!StringUtils.isBlank(orderReport.getLogisticsStatus())) {
			where.append(" and os.logistics_status = '" + orderReport.getLogisticsStatus() + "'");
		}		
		// 处理状态
		if (!StringUtils.isBlank(orderReport.getStatusTotal())) {
			where.append(" and om.status_total = '" + orderReport.getStatusTotal() + "'");
		}		
		// 订单来源
		if (!StringUtils.isBlank(orderReport.getOrderSource())) {
			where.append(" and om.order_source = '" + orderReport.getOrderSource() + "'");
		}
		// 支付方式
		if (null != orderReport.getIfPayOnArrival()) {
			where.append(" and om.if_pay_on_arrival = " + orderReport.getIfPayOnArrival());
		}		
		// 配送方式
		if (!StringUtils.isBlank(orderReport.getDistributeType())) {
			where.append(" and os.distribute_type = '" + orderReport.getDistributeType() + "'");
		}
		// 供应商编码
		if (!StringUtils.isBlank(orderReport.getSupplierCode())) {
			where.append(" and oi.supplier_code = '" + orderReport.getSupplierCode() + "'");
		}
		// 商品运营品类
		List<String> subCategoryIdList = setSubCategoryId(orderReport);
		if(null != subCategoryIdList && !subCategoryIdList.isEmpty()){
			StringBuffer inCategoryIds = new StringBuffer();
			for(int i = 0; i < subCategoryIdList.size(); i++){
				if(i == subCategoryIdList.size()-1){
					inCategoryIds.append("'").append(subCategoryIdList.get(i)).append("'");
				} else {
					inCategoryIds.append("'").append(subCategoryIdList.get(i)).append("',");
				}
			}
			where.append(" and oi.product_category in (').append(inCategoryIds).append(')");
		}
		return from.append(where).append(orderBy);
	}
	
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
			orderReport.setProductCategory(DateStringUtils.encodeHTML(obj[2]));
			orderReport.setProductCategoryName(DateStringUtils.encodeHTML(obj[3]));
			orderReport.setSupplierCode(DateStringUtils.encodeHTML(obj[4]));
			orderReport.setCommodityName(DateStringUtils.encodeHTML(obj[5]));
			if (null != obj[6] && !DateStringUtils.isEmpty(obj[6].toString())) {
				orderReport.setSaleNum(DateStringUtils.strToDouble(obj[6].toString()).longValue());
			}
			if (null != obj[7] && !DateStringUtils.isEmpty(obj[7].toString())) {
				orderReport.setUnitDeductedPrice(DateStringUtils.strToDouble(obj[7].toString()).longValue());
			}
			if (null != obj[8] && !DateStringUtils.isEmpty(obj[8].toString())) {
				orderReport.setTotalPayAmount(DateStringUtils.strToBigDecimal(obj[8].toString()));
			}
			orderReport.setOrderSource(DateStringUtils.encodeHTML(obj[9]));		
			orderReport.setStatusPay(DateStringUtils.encodeHTML(obj[10]));
			orderReport.setStatusConfirm(DateStringUtils.encodeHTML(obj[11]));
			orderReport.setLogisticsStatus(DateStringUtils.encodeHTML(obj[12]));
			orderReport.setStatusTotal(DateStringUtils.encodeHTML(obj[13]));	
			orderReport.setUserName(DateStringUtils.encodeHTML(obj[14]));	
			orderReport.setDistributeType(DateStringUtils.encodeHTML(obj[15]));
			orderReportLists.add(orderReport);
		}
		return orderReportLists;
	}

	/**
	 * 根据CategoryId来递归寻找叶子节点。
	 * 
	 * @param orderReport
	 * @return
	 */
	private List<String> setSubCategoryId(OrderReport orderReport) {
		Long categoryId = null;
		List<String> subCategoryIdList = new ArrayList<String>();
		if (!StringUtils.isBlank(orderReport.getProductCategory())) {
			subCategoryIdList.add(orderReport.getProductCategory());
			categoryId = Long.valueOf(orderReport.getProductCategory());
			addSubCategoryId(categoryId, subCategoryIdList);
		}
		return subCategoryIdList;	
	}
	
	private void addSubCategoryId(Long categoryId,  List<String> subCategoryIdList) {
		List<OrderCategory> subCategoryList = orderCategoryDao.findByParentId(categoryId);
		if (null == subCategoryList)
			return;

		for (OrderCategory orderCategory : subCategoryList) {
			subCategoryIdList.add(orderCategory.getId().toString());
			if (!this.MAX_CATEGORY_LEVEL.equals(orderCategory.getTreeLevel())) {
				categoryId = orderCategory.getId();
				addSubCategoryId(categoryId, subCategoryIdList);
			}
		}
	}
}
