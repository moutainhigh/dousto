package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.OrderCategoryService;
import com.ibm.sc.admin.action.BaseAdminAction;

@ParentPackage("json-default")
@Result(type = "json")
public class CategoryLevelFourAction extends BaseAdminAction {

	private static final long serialVersionUID = 1L;
	
	private OrderReport orderReport;
    
	@Resource
	private OrderCategoryService ordercategoryService;

	private String selectId;
	
	private String upLevel;

	private List<OrderCategory> list = new ArrayList<OrderCategory>();

	public String execute() {

		if(null != upLevel && !upLevel.equals("")){
			list = ordercategoryService.findCategoryByParentId(Long.valueOf(upLevel));		
			for (OrderCategory orderCategory : list) {
				if (String.valueOf(orderCategory.getId()).equals(selectId)) {
					orderCategory.setChecked(true);
				} else {
					orderCategory.setChecked(false);
				}
			}		
		}
		return SUCCESS;
		
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getUpLevel() {
		return upLevel;
	}

	public void setUpLevel(String upLevel) {
		this.upLevel = upLevel;
	}

	public List<OrderCategory> getList() {
		return list;
	}

	public void setList(List<OrderCategory> list) {
		this.list = list;
	}
}
