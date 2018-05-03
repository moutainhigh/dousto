package com.ibm.oms.admin.action.order.report;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderReport;

@ParentPackage("admin")
@SuppressWarnings("all")
public class ServiceGoodsOrderReportAction extends AbstractOrderReportAction {

	private OrderReport orderReport;
	
	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception {		
		//this.search();	
		return "list";
	}
	
	/**
	 * 分页查询
	 */
	public String search(){
		
		if(null == orderReport){
			orderReport	= new OrderReport();
		}
		if (!StringUtils.isBlank(orderReport.getCategoryLevelFour())) {
			orderReport.setProductCategory(orderReport.getCategoryLevelFour());
		} else if (!StringUtils.isBlank(orderReport.getCategoryLevelThree())) {
			orderReport.setProductCategory(orderReport.getCategoryLevelThree());
		} else if (!StringUtils.isBlank(orderReport.getCategoryLevelTwo())) {
			orderReport.setProductCategory(orderReport.getCategoryLevelTwo());
		} else {
			orderReport.setProductCategory(orderReport.getCategoryLevelOne());
		}
		pager = serviceGoodsReportService.findByServiceOrderReport(orderReport, pager);
		return "list";
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}	
}
