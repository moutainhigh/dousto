package com.ibm.oms.admin.action.order.report;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderReport;

@ParentPackage("admin")
@SuppressWarnings("all")
public class CancelOrderReportAction extends AbstractOrderReportAction {

	private OrderReport orderReport;
	
	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception {		
		//this.search();
		return "list";
	}
	
	public String search(){
		if(null == orderReport){
			orderReport	= new OrderReport();
		}
		// 配送地址
		orderReport.setDistributeAddress(this.distributeAddress);
		orderReport.setState(this.distributeAddress.getState());
		orderReport.setCity(this.distributeAddress.getCity());
		orderReport.setCounty(this.distributeAddress.getCounty());
		orderReport.setStreet(this.distributeAddress.getStreet());
		// 自提点
		orderReport.setSelfTakePoint(this.selfTakePoint);
		orderReport.setSelfTakePointId(this.selfTakePoint.getId());
		orderReport.setPointName(this.selfTakePoint.getPointName());
		pager = cancelOrderReportService.findByCancelOrderReport(orderReport, pager);
		return "list";
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
}
