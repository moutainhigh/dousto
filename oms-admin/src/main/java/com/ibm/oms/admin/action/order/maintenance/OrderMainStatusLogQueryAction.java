package com.ibm.oms.admin.action.order.maintenance;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.sc.admin.action.BaseAdminAction;

@ParentPackage("admin")
public class OrderMainStatusLogQueryAction extends BaseAdminAction {

	private static final int status_type_main = 1;
	
	@Resource
	private OrderStatusLogService orderStatusLogService;

	private OrderStatusLog log;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		pager = orderStatusLogService.findOrderStstusLogByOrderNo(log.getOrderNo(), status_type_main, pager);
		return "order_main_status_log";
	}

	public OrderStatusLog getLog() {
		return log;
	}

	public void setLog(OrderStatusLog log) {
		this.log = log;
	}
	
}
