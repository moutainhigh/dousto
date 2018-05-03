package com.ibm.oms.admin.action.order.report;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderReport;

@ParentPackage("admin")
@SuppressWarnings("all")
public class ToDoListAction extends AbstractOrderReportAction {

	/**
	 * 订单基础数据报表
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderReport orderReport;
	
	public String execute() throws Exception {		
		this.search();	
		return "list";
	}
	
	/**
	 * 分页查询
	 */
	public String search(){
		
		if(null == orderReport){
			orderReport	= new OrderReport();
		}
		
		orderReport = toDoListService.findToDoList(orderReport);
		return "list";
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}	
}
