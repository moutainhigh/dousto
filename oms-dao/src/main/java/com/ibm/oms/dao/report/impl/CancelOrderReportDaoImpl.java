package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.dao.report.CancelOrderReportDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateStringUtils;
import com.ibm.sc.util.DateUtils;

@Repository("cancelOrderReportDao")
public class CancelOrderReportDaoImpl extends BaseDaoImpl<OrderReport, Long> implements CancelOrderReportDao {

	private static final Long MAX_AREA_LEVEL = 3L;

	private static final Long BASE_AREA_ID = 888888L;
	
	/**
	 * 顶级区域级别，如：中国 0L
	 */
    public static final Long BASE_AREA_LEVEL = 0L;// 地址级别：顶级（中国）

	@Autowired
	TransportAreaDao transportAreaDao;
	
	@Override
	public Pager findByOrderReport(OrderReport orderReport, Pager pager) {

		StringBuffer queryHqlHeader = new StringBuffer();
		StringBuffer totalHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time, os.order_sub_no,os.address_code, om.order_source, om.order_type, om.customer_name,mmv.vip_Level, om.status_pay, om.status_confirm, om.total_pay_amount,os.self_fetch_address,om.member_no");
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
		setTransportAreaName(orderReportLists);
		
		pager.setList(orderReportLists);
		return pager;
	}

	@Override
	public List<OrderReport> findByOrderReport(OrderReport orderReport) {
		StringBuffer queryHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time, os.order_sub_no,os.address_code, om.order_source, om.order_type, om.customer_name,mmv.vip_Level, om.status_pay, om.status_confirm, om.total_pay_amount,os.self_fetch_address,om.member_no");

		StringBuffer searchConditions = setSearchConditions(orderReport);
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());
		List<OrderReport> orderReportLists = initOrderReportLists(recordQuery.list());	
		setTransportAreaName(orderReportLists);

		return orderReportLists;
	}

	private StringBuffer setSearchConditions(OrderReport orderReport) {
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		from.append(" from order_main om, order_sub os, mb_member_vipcard mmv");
		where.append(" where om.id = os.id_order(+) and to_number(om.member_no) = mmv.member_id(+) and om.status_total in ('0131','0142','0153') ");
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
		// 订单类型
		if (!StringUtils.isBlank(orderReport.getOrderType())) {
			where.append(" and om.order_type = '" + orderReport.getOrderType() + "'");
		}
		// 订单来源
		if (!StringUtils.isBlank(orderReport.getOrderSource())) {
			where.append(" and om.order_source = '" + orderReport.getOrderSource() + "'");
		}
		// 自提点
		if (null != orderReport.getSelfTakePointId()) {
			where.append(" and os.self_fetch_address = '" + orderReport.getSelfTakePointId() + "'");
		}
		List<String> addressCodes = setAddressCodeByTransportAreaId(orderReport);
		// 配送地址
		if (null != addressCodes && !addressCodes.isEmpty()) {
			StringBuffer inAddressCode = new StringBuffer();
			for(int i = 0; i < addressCodes.size(); i++){
				if(i == addressCodes.size()-1){
					inAddressCode.append("'").append(addressCodes.get(i)).append("'");
				} else {
					inAddressCode.append("'").append(addressCodes.get(i)).append("',");
				}
			}
			where.append(" and os.address_code in (").append(inAddressCode).append(")");
		}
		return from.append(where).append(orderBy);
	}

	/**
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<OrderReport> initOrderReportLists(List list) {

		List<OrderReport> orderReportLists = new ArrayList<OrderReport>();

		for (Object objArray : list) {
			OrderReport orderReport = new OrderReport();
			Object[] obj = (Object[]) objArray;
			if (null != obj[0] && !DateStringUtils.isEmpty(obj[0].toString())) {
				orderReport.setOrderTime(DateStringUtils.parseDate(obj[0].toString()));
			}
			orderReport.setOrderSubNo(DateStringUtils.encodeHTML(obj[1]));
			orderReport.setAddressCode(DateStringUtils.encodeHTML(obj[2]));
			orderReport.setOrderSource(DateStringUtils.encodeHTML(obj[3]));
			orderReport.setOrderType(DateStringUtils.encodeHTML(obj[4]));
			orderReport.setCustomerName(DateStringUtils.encodeHTML(obj[5]));
			orderReport.setVipLevel(DateStringUtils.encodeHTML(obj[6]));
			orderReport.setStatusPay(DateStringUtils.encodeHTML(obj[7]));
			orderReport.setStatusConfirm(DateStringUtils.encodeHTML(obj[8]));
			if (null != obj[9] && !DateStringUtils.isEmpty(obj[9].toString())) {
				orderReport.setTotalPayAmount(DateStringUtils.strToBigDecimal(obj[9].toString()));
			}
			if (null != obj[10] && !DateStringUtils.isEmpty(obj[10].toString())) {
				orderReport.setSelfTakePointId(DateStringUtils.strToDouble(obj[10].toString()).longValue());
			}
			orderReport.setMemberNo(DateStringUtils.encodeHTML(obj[11]));
			orderReportLists.add(orderReport);
		}
		return orderReportLists;
	}

	/**
	 * 根据transportAreaId来设置addressCode。递归寻找叶子节点。
	 * 
	 * @param orderReport
	 * @return
	 */
	private List<String> setAddressCodeByTransportAreaId(OrderReport orderReport) {
		Long transportAreaId = null;
		List<String> addressCodes = new ArrayList<String>();
		List<TransportArea> transportAreas = null;
		transportAreaId = orderReport.getTransportAreaId();
		if (null != transportAreaId) {
			transportAreas = transportAreaDao.findById(transportAreaId);
			addressCodes.add(transportAreas.get(0).getAreaCode());

			addSubAreaCode(transportAreaId, addressCodes);
		}
		return addressCodes;
	
	}
	
	private void addSubAreaCode(Long transportAreaId,  List<String> addressCodes) {
		List<TransportArea> subTransportAreas = transportAreaDao.findByParentId(transportAreaId);
		if (null == subTransportAreas)
			return;

		for (TransportArea transportArea : subTransportAreas) {
			addressCodes.add(transportArea.getAreaCode());
			if (!this.MAX_AREA_LEVEL.equals(transportArea.getAreaLevel())) {
			transportAreaId = transportArea.getId();
			addSubAreaCode(transportAreaId, addressCodes);
			}
		}
	}

	/**
	 * 根据addressCode取areaName
	 * 
	 * @param resultList
	 */
	public void setTransportAreaName(List<OrderReport> resultList) {
		String addressCode = "";
		List<TransportArea> transportAreas;
		TransportArea transportArea;
		String transportAreaName = "";
		Long parentId = null;

		for (OrderReport orderReport : resultList) {
			addressCode = orderReport.getAddressCode();
			transportAreas = transportAreaDao.findByCode(addressCode);
			if (null == transportAreas || transportAreas.isEmpty() || StringUtils.isBlank(addressCode))
				continue;
			transportArea = transportAreas.get(0);
			transportAreaName = transportArea.getAreaName();
			orderReport.setAreaName(transportAreaName);
			parentId = transportArea.getParentId();

			setParentTransportAreaName(transportAreaName, parentId, orderReport, transportArea);
		}
	}

	/**
	 * 根据父id查找父区域，将查询出的areaName设置到OrderReport中
	 * 
	 * @param transportAreaName
	 * @param parentId
	 * @param OrderReportSearch
	 * @param transportArea
	 */
	public void setParentTransportAreaName(String transportAreaName, Long parentId, OrderReport orderReport, TransportArea transportArea) {

		TransportArea parentTransportArea;
		Long newtransportAreaId;

		if (null == parentId)
			return;
		/*if (this.BASE_AREA_ID.equals(parentId)) {
			return;
		}*/
		List<TransportArea> transportAreas = transportAreaDao.findById(parentId);
		if((null == transportAreas) || transportAreas.size() <= 0)
		{
			return;
		}
		for (TransportArea newtransportArea : transportAreas) {
			if((null == newtransportArea) || this.BASE_AREA_LEVEL.equals(newtransportArea.getAreaLevel()))
			{
				break;
			}
			
			newtransportAreaId = newtransportArea.getId();
			parentTransportArea = newtransportArea;
			transportAreaName = parentTransportArea.getAreaName() + transportAreaName;
			orderReport.setAreaName(transportAreaName);
			parentId = newtransportArea.getParentId();
			setParentTransportAreaName(transportAreaName, parentId, orderReport, newtransportArea);
		}
	}
}
