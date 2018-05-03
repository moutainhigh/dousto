package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.constant.Global;
import com.ibm.oms.dao.intf.OrderPayDao;
import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.dao.report.BaseOrderReportDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateStringUtils;
import com.ibm.sc.util.DateUtils;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Repository("baseOrderReportDaoImpl")
public class BaseOrderReportDaoImpl extends BaseDaoImpl<OrderReport, Long> implements BaseOrderReportDao {

	private static final Long MAX_AREA_LEVEL = 3L;

	@Autowired
	TransportAreaDao transportAreaDao;
	@Autowired
	OrderPayDao orderPayDao;

	@Override
	public Pager findByOrderReport(OrderReport orderReport, Pager pager) {
		
		StringBuffer queryHqlHeader = new StringBuffer();
		StringBuffer totalHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time,om.order_type,os.order_sub_no,os.user_name,om.customer_name,om.customer_phone,mmv.vip_Level,os.address_code,om.status_pay,om.status_confirm,os.logistics_status,om.status_total,om.order_source,om.total_pay_amount,om.weight,os.self_fetch_address,om.member_no,om.if_pay_on_arrival,os.distribute_type,os.delivery_merchant_name");
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
	public List<OrderReport> findByOrderReport(OrderReport orderReport) {
		
		StringBuffer queryHqlHeader = new StringBuffer();
		queryHqlHeader.append("select om.order_time,om.order_type,os.order_sub_no,os.user_name,om.customer_name,om.customer_phone,mmv.vip_Level,os.address_code,om.status_pay,om.status_confirm,os.logistics_status,om.status_total,om.order_source,om.total_pay_amount,om.weight,os.self_fetch_address,om.member_no,om.if_pay_on_arrival,os.distribute_type,os.delivery_merchant_name");

		StringBuffer searchConditions = setSearchConditions(orderReport);
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());

		List<OrderReport> orderReportLists = initOrderReportLists(recordQuery.list());
		
		return orderReportLists;
	}
	
	private StringBuffer setSearchConditions(OrderReport orderReport) {
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		from.append(" from order_main om, order_sub os, mb_member_vipcard mmv");
		where.append(" where om.id = os.id_order(+) and om.member_no = to_char(mmv.member_id(+))");
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
		// 子订单号
		if (!StringUtils.isBlank(orderReport.getOrderSubNo())) {
			where.append(" and os.order_sub_no = '" + orderReport.getOrderSubNo() + "'");
		}		
		// 外部订单号
		if (!StringUtils.isBlank(orderReport.getAliasOrderNo())) {
			where.append(" and om.alias_order_no = '" + orderReport.getAliasOrderNo() + "'");
		}		
		// 收件人
		if (!StringUtils.isBlank(orderReport.getUserName())) {
			where.append(" and os.user_name = '" + orderReport.getUserName() + "'");
		}
		// 会员账号
		if (!StringUtils.isBlank(orderReport.getMemberNo())) {
			where.append(" and om.member_no = '" + orderReport.getMemberNo() + "'");
		}		
		// 会员手机号码
		if (!StringUtils.isBlank(orderReport.getMobPhoneNum())) {
			where.append(" and om.customer_phone = '" + orderReport.getMobPhoneNum() + "'");
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
		// 订单大类
		if (null != orderReport.getBillType()) {
		    where.append(" and om.BILL_TYPE = " + orderReport.getBillType());
		}		
		// 配送方式
		if (!StringUtils.isBlank(orderReport.getDistributeType())) {
			where.append(" and os.distribute_type = '" + orderReport.getDistributeType() + "'");
		}
		// 物流商
		if (!StringUtils.isBlank(orderReport.getDeliveryMerchantName())) {
			where.append(" and os.delivery_merchant_name = '" + orderReport.getDeliveryMerchantName() + "'");
		}
		// 供应商编码
		//if (!StringUtils.isBlank(orderReport.getSupplierCode())) {
			//where.append(" and oi.supplier_code = '" + orderReport.getSupplierCode() + "'");
		//}
		
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
		// 会员等级
		if (!StringUtils.isBlank(orderReport.getVipLevel())) {
			where.append(" and mmv.vip_Level = '" + orderReport.getVipLevel()+ "'");
		}
		
		// 自提点id
		if (isNotAllInt(orderReport.getSelfTakePointId())) {
		    where.append(" and os.SELF_FETCH_ADDRESS = '").append(orderReport.getSelfTakePointId().toString()).append("'");
        }
        // 商户（多个自提点id）
        if(null != orderReport.getSelfTakePointIdList() && orderReport.getSelfTakePointIdList().size() > 0)
        {
            List<String> selfTakePointIdList = orderReport.getSelfTakePointIdList();
            where.append(" and os.SELF_FETCH_ADDRESS in (");
       
            for (int i = 0; i < selfTakePointIdList.size(); i++) {
                if(i ==  selfTakePointIdList.size()-1){
                    where.append("'").append( selfTakePointIdList.get(i)).append("'");
                }else{
                    where.append("'").append( selfTakePointIdList.get(i)).append("' ,");
                }
            }
            where.append(") ");
        }
		
		return from.append(where).append(orderBy);
	}

	private boolean isNotAllInt(java.lang.Long value) {
        boolean flag = false;
        if (null != value && value != Global.ALL_INT) {
            flag = true;
        }
        return flag;
    }

    private boolean isNotAllString(String value) {
        boolean flag = false;
        if (StringUtils.isNotEmpty(value) && !value.equals(Global.ALL_STRING)) {
            flag = true;
        }
        return flag;
    }
	
	@SuppressWarnings("rawtypes")
	private List<OrderReport> initOrderReportLists(List list) {

		List<OrderReport> orderReportLists = new ArrayList<OrderReport>();

		for (Object objArray : list) {
			OrderReport orderReport = new OrderReport();
			Object[] obj = (Object[]) objArray;
			if (null != obj[0] && !DateStringUtils.isEmpty(obj[0].toString())) {
				orderReport.setOrderTime(DateStringUtils.parseDate(obj[0].toString()));
			}
			orderReport.setOrderType(DateStringUtils.encodeHTML(obj[1]));
			orderReport.setOrderSubNo(DateStringUtils.encodeHTML(obj[2]));
			orderReport.setUserName(DateStringUtils.encodeHTML(obj[3]));
			orderReport.setCustomerName(DateStringUtils.encodeHTML(obj[4]));
			orderReport.setMobPhoneNum(DateStringUtils.encodeHTML(obj[5]));
			orderReport.setVipLevel(DateStringUtils.encodeHTML(obj[6]));			
			orderReport.setAddressCode(DateStringUtils.encodeHTML(obj[7]));
			orderReport.setStatusPay(DateStringUtils.encodeHTML(obj[8]));
			orderReport.setStatusConfirm(DateStringUtils.encodeHTML(obj[9]));
			orderReport.setLogisticsStatus(DateStringUtils.encodeHTML(obj[10]));
			orderReport.setStatusTotal(DateStringUtils.encodeHTML(obj[11]));		
			orderReport.setOrderSource(DateStringUtils.encodeHTML(obj[12]));
			if (null != obj[13] && !DateStringUtils.isEmpty(obj[13].toString())) {
				orderReport.setTotalPayAmount(DateStringUtils.strToBigDecimal(obj[13].toString()));
			}
			if (null != obj[14] && !DateStringUtils.isEmpty(obj[14].toString())) {
				orderReport.setWeight(DateStringUtils.strToBigDecimal(obj[14].toString()));
			}
			orderReport.setSelfFetchAddress(DateStringUtils.encodeHTML(obj[15]));
			orderReport.setMemberNo(DateStringUtils.encodeHTML(obj[16]));
			if (null != obj[17] && !DateStringUtils.isEmpty(obj[17].toString())) {
				orderReport.setIfPayOnArrival(DateStringUtils.strToDouble(obj[17].toString()).longValue());
			}
			orderReport.setDistributeType(DateStringUtils.encodeHTML(obj[18]));
			orderReport.setDeliveryMerchantName(DateStringUtils.encodeHTML(obj[19]));
			orderReportLists.add(orderReport);
		}
		return orderReportLists;
	}

	/**
	 * 根据transportAreaId来设置addressCode。递归寻找叶子节点。
	 * 
	 * @param order
	 * @return
	 */
	private List<String> setAddressCodeByTransportAreaId(OrderReport order) {
		
		Long transportAreaId = null;
		List<String> addressCodes = new ArrayList<String>();
		List<TransportArea> transportAreas = null;

		transportAreaId = order.getTransportAreaId();
		if (null != transportAreaId) {
			transportAreas = transportAreaDao.findById(transportAreaId);
			addressCodes.add(transportAreas.get(0).getAreaCode());
			addSubAreaCode(transportAreaId, addressCodes);
		}
		return addressCodes;
	}

	/**
	 * 查找子areaCode
	 * 
	 * @param transportAreaId
	 * @param addressCodes
	 */
	private void addSubAreaCode(Long transportAreaId, List<String> addressCodes) {
		List<TransportArea> subTransportAreas = transportAreaDao.findByParentId(transportAreaId);

		if (null == subTransportAreas)
			return;
		for (TransportArea transportArea : subTransportAreas) {
			if (this.MAX_AREA_LEVEL.equals(transportArea.getAreaLevel())) {
				addressCodes.add(transportArea.getAreaCode());
			}
			transportAreaId = transportArea.getId();
			// 递归查询子areaCode
			addSubAreaCode(transportAreaId, addressCodes);
		}
	}
}

