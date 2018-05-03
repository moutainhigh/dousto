package com.ibm.oms.admin.action.order.maintenance;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * 后台运维Action类
 */

@ParentPackage("admin")
public class MaintenanceViewAction extends BaseAdminAction {
	
	private OrdiErrOptLog ordiErrOptLog;

	@Resource
	private OrdiErrOptLogService ordiErrOptLogService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846588470340142948L;
	
	public String execute() {
		String id = this.getParameter("id");
		
		ordiErrOptLog = ordiErrOptLogService.get(Long.valueOf(id));
		
		return "maintenance_view";
	}

	public OrdiErrOptLog getOrdiErrOptLog() {
		return ordiErrOptLog;
	}

	public void setOrdiErrOptLog(OrdiErrOptLog ordiErrOptLog) {
		this.ordiErrOptLog = ordiErrOptLog;
	}
}
