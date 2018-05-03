package com.ibm.oms.admin.action.order.report;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrderReport;

@ParentPackage("admin")
@SuppressWarnings("all")
public class RefundOrderReportAction extends AbstractOrderReportAction {

	private OrderReport orderReport;
	
	/**
	 * 退货、换货、拒收单报表
	 */
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
		// 配送地址
		orderReport.setDistributeAddress(this.distributeAddress);
		orderReport.setState(this.distributeAddress.getState());
		orderReport.setCity(this.distributeAddress.getCity());
		orderReport.setCounty(this.distributeAddress.getCounty());
		orderReport.setStreet(this.distributeAddress.getStreet());
		orderReport.setColumnTitle(column);
		// 设置订单页面标题
		super.setOrderColumnTitle(column);
		pager = refundOrderReportService.findByRefundOrderReport(orderReport, pager);
		return "list";
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
}
