package com.ibm.oms.admin.action.order.maintenance;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.sc.admin.action.BaseAdminAction;


@ParentPackage("admin")
public class OrderLogAction extends BaseAdminAction {
	
	public String execute() {
		return "order_log";
	}
		
}
