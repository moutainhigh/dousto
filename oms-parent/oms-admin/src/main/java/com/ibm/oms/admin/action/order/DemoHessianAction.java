package com.ibm.oms.admin.action.order;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.client.dto.bundle.BundleGroupBean;
import com.ibm.oms.client.intf.DemoService;

@ParentPackage("admin")
public class DemoHessianAction extends AbstractOrderAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1684103605025650204L;
	@Autowired
	DemoService demoService;
	
	public String execute() throws Exception {
		BundleGroupBean entity = new BundleGroupBean();
		demoService.query(entity);
		return "success";
	}
	
}
