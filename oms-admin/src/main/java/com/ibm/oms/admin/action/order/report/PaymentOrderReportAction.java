package com.ibm.oms.admin.action.order.report;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.OrderReport;

@ParentPackage("json-default")
@Result(type = "json")
public class PaymentOrderReportAction extends AbstractOrderReportAction {

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

		pager = cMBPaymentReportService.findByCMBPaymentReport(orderReport, pager);
		return "list";
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
}
