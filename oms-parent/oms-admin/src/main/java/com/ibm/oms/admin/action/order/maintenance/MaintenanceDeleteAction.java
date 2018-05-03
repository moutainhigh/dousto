package com.ibm.oms.admin.action.order.maintenance;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * 后台运维删除异常Action类
 */

@ParentPackage("admin")
public class MaintenanceDeleteAction extends BaseAdminAction {
	
	private OrdiErrOptLog ordiErrOptLog;

	@Resource
	private OrderCreateService orderCreateService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846588470340142948L;
	
	public String execute() {
		String msg = orderCreateService.deleteErr(ordiErrOptLog.getId());
		setActionMessages(Arrays.asList(msg));
		redirectionUrl = "maintenance.action";
		return SUCCESS;
	}

	public OrdiErrOptLog getOrdiErrOptLog() {
		return ordiErrOptLog;
	}

	public void setOrdiErrOptLog(OrdiErrOptLog ordiErrOptLog) {
		this.ordiErrOptLog = ordiErrOptLog;
	}
}
