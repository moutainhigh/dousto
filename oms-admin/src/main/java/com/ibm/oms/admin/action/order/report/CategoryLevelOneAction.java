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
public class CategoryLevelOneAction extends BaseAdminAction {

	private static final long serialVersionUID = 1L;
	
	// 运营目录商品一级分类
    public static final Long Treelevel_One = 1L;
	// 运营目录商品二级分类
    public static final Long Treelevel_Two = 2L;
	// 运营目录商品三级分类
    public static final Long Treelevel_Three = 3L;
	// 运营目录商品四级分类
    public static final Long Treelevel_Four = 4L;
    
	private OrderReport orderReport;
    
	@Resource
	private OrderCategoryService orderCategoryService;

	private String selectId;

	private List<OrderCategory> list = new ArrayList<OrderCategory>();

	public String execute() {

		list = orderCategoryService.findCategoryLevelOneList(Treelevel_One);		
		for (OrderCategory orderCategory : list) {
			if (String.valueOf(orderCategory.getId()).equals(selectId)) {
				orderCategory.setChecked(true);
			} else {
				orderCategory.setChecked(false);
			}
		}		
		return SUCCESS;
	}

	public List<OrderCategory> getList() {
		return list;
	}
	
	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public void setList(List<OrderCategory> list) {
		this.list = list;
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
			
}
