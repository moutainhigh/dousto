package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.constant.OrderReportColumn;
import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.dao.report.OrderCategoryDao;
import com.ibm.oms.dao.report.RefundOrderReportDao;
import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateStringUtils;
import com.ibm.sc.util.DateUtils;

@Repository("refundOrderReportDao")
public class RefundOrderReportDaoImpl extends BaseDaoImpl<OrderReport, Long> implements RefundOrderReportDao {

	private static final Long MAX_AREA_LEVEL = 3L;
	
	private static final Long MAX_CATEGORY_LEVEL = 4L;

	private static final Long BASE_AREA_ID = 888888L;
	
	/**
	 * 顶级区域级别，如：中国 0L
	 */
    public static final Long BASE_AREA_LEVEL = 0L;// 地址级别：顶级（中国）

	@Autowired
	TransportAreaDao transportAreaDao;
	@Autowired
	OrderCategoryDao orderCategoryDao;
	
	public Pager findByRefundOrderReport(OrderReport orderReport, Pager pager) {
		StringBuffer queryHqlHeader = new StringBuffer();
		StringBuffer totalHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time,os.order_sub_no,os.order_sub_related_origin,rom.if_pay_on_arrival,ros.sign_off_time as related_sign_off_time,os.address_code,om.status_confirm, orci.product_category, orci.sku_no, orci.commodity_name, orci.sale_num, orci.pay_amount,os.distribute_Type,orci.reason,os.sign_off_time,osl.current_status,osl.operate_time,op.pay_name,op.pay_amount as amount, orci.supplier_code, mmv.vip_Level");
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
	public List<OrderReport> findByRefundOrderReport(OrderReport orderReport) {
		
		StringBuffer queryHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time,os.order_sub_no,os.order_sub_related_origin,rom.if_pay_on_arrival,ros.sign_off_time as related_sign_off_time,os.address_code,om.status_confirm, orci.product_category, orci.sku_no, orci.commodity_name, orci.sale_num, orci.pay_amount,os.distribute_Type,orci.reason,os.sign_off_time,osl.current_status,osl.operate_time,op.pay_name,op.pay_amount as amount, orci.supplier_code, mmv.vip_Level");
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
		from.append(" from order_main om, order_sub os, order_ret_chg_item orci, order_main rom, order_sub ros, (select * from order_status_log where PREVIOUS_STATUS = '0240' AND (CURRENT_STATUS = '0250' OR CURRENT_STATUS = '0160')) osl, order_pay op, mb_member_vipcard mmv");
		where.append("  where om.id = os.id_order and om.id = orci.id_order and om.order_related_origin = rom.order_no and os.order_sub_related_origin = ros.order_sub_no and os.order_sub_no = osl.order_sub_no(+) and om.order_no = op.order_no(+) and om.member_no = to_char(mmv.member_id(+))");
		orderBy.append(" order by om.order_time desc, os.order_sub_no desc");

		switch (orderReport.getColumnTitle()) {
		case OrderReportColumn.ORDER_RETURN:
			where.append(" and om.order_category = 'ret'");
			break;
		case OrderReportColumn.ORDER_CHANGE:
			where.append(" and om.order_category = 'chg'");
			break;
		case OrderReportColumn.ORDER_REJECT:
			where.append(" and om.order_category = 'rej'");
			break;
		}
		
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
			where.append(" and orci.product_category in (").append(inCategoryIds).append(")");
		}
		// 供应商编码
		if (!StringUtils.isBlank(orderReport.getSupplierCode())) {
			where.append(" and orci.supplier_code = '" + orderReport.getSupplierCode() + "'");
		}
		// 会员等级
		if (!StringUtils.isBlank(orderReport.getVipLevel())) {
			where.append(" and mmv.vip_Level = '" + orderReport.getVipLevel()+ "'");
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
			if (null != obj[0] && !DateStringUtils.isEmpty(obj[0].toString())) {
				orderReport.setOrderTime(DateStringUtils.parseDate(obj[0].toString()));
			}
			orderReport.setOrderSubNo(DateStringUtils.encodeHTML(obj[1]));
			orderReport.setOrderSubRelatedOrigin(DateStringUtils.encodeHTML(obj[2]));
			if (null != obj[3] && !DateStringUtils.isEmpty(obj[3].toString())) {
				orderReport.setIfPayOnArrival(DateStringUtils.strToDouble(obj[3].toString()).longValue());
			}
			if (null != obj[4] && !DateStringUtils.isEmpty(obj[4].toString())) {
				orderReport.setRelatedSignOffTime(DateStringUtils.parseDate(obj[4].toString()));
			}
			orderReport.setAddressCode(DateStringUtils.encodeHTML(obj[5]));
			orderReport.setStatusConfirm(DateStringUtils.encodeHTML(obj[6]));
			orderReport.setProductCategory(DateStringUtils.encodeHTML(obj[7]));
			orderReport.setSkuNo(DateStringUtils.encodeHTML(obj[8]));
			orderReport.setCommodityName(DateStringUtils.encodeHTML(obj[9]));
			if (null != obj[10] && !DateStringUtils.isEmpty(obj[10].toString())) {
				orderReport.setSaleNum(DateStringUtils.strToDouble(obj[10].toString()).longValue());
			}
			if (null != obj[11] && !DateStringUtils.isEmpty(obj[11].toString())) {
				orderReport.setPayAmount(DateStringUtils.strToBigDecimal(obj[11].toString()));
			}
			orderReport.setDistributeType(DateStringUtils.encodeHTML(obj[12]));
			orderReport.setRefundReason(DateStringUtils.encodeHTML(obj[13]));
			if (null != obj[14] && !DateStringUtils.isEmpty(obj[14].toString())) {
				orderReport.setSignOffTime(DateStringUtils.parseDate(obj[14].toString()));
			}
			orderReport.setCurrentStatus(DateStringUtils.encodeHTML(obj[15]));
			if (null != obj[16] && !DateStringUtils.isEmpty(obj[16].toString())) {
				orderReport.setOperateTime(DateStringUtils.parseDate(obj[16].toString()));
			}
			orderReport.setPayName(DateStringUtils.encodeHTML(obj[17]));
			if (null != obj[18] && !DateStringUtils.isEmpty(obj[18].toString())) {
				orderReport.setRefundAmount(DateStringUtils.strToBigDecimal(obj[18].toString()));
			}
			orderReport.setSupplierCode(DateStringUtils.encodeHTML(obj[19]));
			orderReport.setVipLevel(DateStringUtils.encodeHTML(obj[20]));	
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
}
