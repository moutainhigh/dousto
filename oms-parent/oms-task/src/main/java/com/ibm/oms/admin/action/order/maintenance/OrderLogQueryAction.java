package com.ibm.oms.admin.action.order.maintenance;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.sc.admin.action.BaseAdminAction;


@ParentPackage("admin")
public class OrderLogQueryAction extends BaseAdminAction {

	
	
	@Resource
	private OrderOperateLogService orderOperateLogService;

	private OrderOperateLog log;
	
	private Map<String, String> map;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		pager =orderOperateLogService.findPagerOrderOperateLog(log,map, pager);
		return "order_log";
	}

	public OrderOperateLog getLog() {
		return log;
	}

	public void setLog(OrderOperateLog log) {
		this.log = log;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
