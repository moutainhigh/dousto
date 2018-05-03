package com.ibm.oms.dao.report.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.report.ToDoListDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.dao.impl.BaseDaoImpl;

@Repository("toDoListDao")
public class ToDoListDaoImpl extends BaseDaoImpl<OrderReport, Long> implements ToDoListDao {

	@Override
	public OrderReport findToDoList(OrderReport orderReport) {
		
		// 质检失败		
		StringBuffer inspectFailedStr = new StringBuffer();	
		inspectFailedStr.append("select count(*) from order_main WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 and  ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') AND ID IN (select ID_ORDER from order_status_log osl where CURRENT_STATUS = '0260') ");	
		SQLQuery totalQueryInspectFailed = getSession().createSQLQuery(inspectFailedStr.toString());
		Object totalObjInspectFailedStr = (Object) totalQueryInspectFailed.list().get(0);
		orderReport.setInspectFailedOrderCount(Long.parseLong(totalObjInspectFailedStr.toString()));
		
		// 	退货单
		StringBuffer toReturnOrderCountStr = new StringBuffer();
		toReturnOrderCountStr
				.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE order_category = 'ret' and STATUS_CONFIRM = '0801'");
		SQLQuery totalQueryToReturnOrderCountStr = getSession().createSQLQuery(
				toReturnOrderCountStr.toString());
		Object totalObjToReturnOrderCountStr = (Object) totalQueryToReturnOrderCountStr.list()
				.get(0);
		orderReport.setToReturnOrderCount(Long.parseLong(totalObjToReturnOrderCountStr.toString()));
		
		// 	换货单
		StringBuffer toChangeOrderCountStr = new StringBuffer();
		toChangeOrderCountStr
				.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE order_category = 'chg' and STATUS_CONFIRM = '0801'");
		SQLQuery totalQueryToChangeOrderCountStr = getSession().createSQLQuery(
				toChangeOrderCountStr.toString());
		Object totalObjToChangeOrderCountStr = (Object) totalQueryToChangeOrderCountStr.list()
				.get(0);
		orderReport.setToChangeOrderCount(Long.parseLong(totalObjToChangeOrderCountStr.toString()));
		
		// 	拒收单
		StringBuffer toRefundOrderCountStr = new StringBuffer();
		toRefundOrderCountStr
				.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE order_category = 'rej' and STATUS_CONFIRM = '0801'");
		SQLQuery totalQueryToRefundOrderCountStr = getSession().createSQLQuery(
				toRefundOrderCountStr.toString());
		Object totalObjToRefundOrderCountStr = (Object) totalQueryToRefundOrderCountStr.list()
				.get(0);
		orderReport.setToRefundOrderCount(Long.parseLong(totalObjToRefundOrderCountStr.toString()));
		
		// 待审核订单
		StringBuffer toAuditOrderCountStr = new StringBuffer();
		toAuditOrderCountStr.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE BILL_TYPE = '1' and STATUS_CONFIRM = '0801'");
		SQLQuery totalQueryToAuditOrderCountStr = getSession().createSQLQuery(toAuditOrderCountStr.toString());
		Object totalObjToAuditOrderCountStr = (Object) totalQueryToAuditOrderCountStr.list().get(0);
		orderReport.setToAuditOrderCount(Long.parseLong(totalObjToAuditOrderCountStr.toString()));
				
		// 预警订单
		StringBuffer warnOrderCountStr = new StringBuffer();
		warnOrderCountStr.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 AND ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') AND BILL_TYPE = '1' and if_warn_order = 1");
		SQLQuery totalQueryWarnOrderCountStr = getSession().createSQLQuery(warnOrderCountStr.toString());
		Object totalObjWarnOrderCountStr = (Object) totalQueryWarnOrderCountStr.list().get(0);
		orderReport.setWarnOrderCount(Long.parseLong(totalObjWarnOrderCountStr.toString()));
		
		// 昨日订单总量
		StringBuffer orderCountStr = new StringBuffer();
		orderCountStr.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 AND ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') AND BILL_TYPE = '1'");
		SQLQuery totalQueryOrderCountStr = getSession().createSQLQuery(orderCountStr.toString());
		Object totalObjOrderCountStr = (Object) totalQueryOrderCountStr.list().get(0);
		orderReport.setOrderCount(Long.parseLong(totalObjOrderCountStr.toString()));
		
		// 	昨日退货单
		StringBuffer returnOrderCountStr = new StringBuffer();
		returnOrderCountStr
				.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 AND ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') and order_category = 'ret'");
		SQLQuery totalQueryReturnOrderCountStr = getSession().createSQLQuery(
				returnOrderCountStr.toString());
		Object totalObjReturnOrderCountStr = (Object) totalQueryReturnOrderCountStr.list()
				.get(0);
		orderReport.setReturnOrderCount(Long.parseLong(totalObjReturnOrderCountStr.toString()));
		
		// 	昨日退货单
		StringBuffer changeOrderCountStr = new StringBuffer();
		changeOrderCountStr.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 AND ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') and order_category = 'chg'");
		SQLQuery totalQueryChangeOrderCountStr = getSession().createSQLQuery(
				changeOrderCountStr.toString());
		Object totalObjChangeOrderCountStr = (Object) totalQueryChangeOrderCountStr.list().get(0);
		orderReport.setChangeOrderCount(Long.parseLong(totalObjChangeOrderCountStr.toString()));
		
		// 	昨日退货单
		StringBuffer refundOrderCountStr = new StringBuffer();
		refundOrderCountStr.append("SELECT COUNT(*) FROM ORDER_MAIN WHERE ORDER_TIME >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') - 1 AND ORDER_TIME < TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD HH:MI:SS') and order_category = 'rej'");
		SQLQuery totalQueryRefundOrderCountStr = getSession().createSQLQuery(
				refundOrderCountStr.toString());
		Object totalObjRefundOrderCountStr = (Object) totalQueryRefundOrderCountStr.list().get(0);
		orderReport.setRefundOrderCount(Long.parseLong(totalObjRefundOrderCountStr.toString()));

		return orderReport;
	}

}
