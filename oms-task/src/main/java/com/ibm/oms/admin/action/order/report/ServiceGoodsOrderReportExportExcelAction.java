package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.ServiceGoodsReportService;
import com.ibm.sc.util.DateUtils;

public class ServiceGoodsOrderReportExportExcelAction extends ExportExcelAction {

	private static final long serialVersionUID = 1L;
	private OrderReport orderReport;
	@Resource
	ServiceGoodsReportService serviceGoodsReportService;
	
	@Override
	public String[] getTitiles() {

		String[] title = new String[] {"下单日期", "子订单号", "订单来源", "支付方式", "配送方式", "商品品类",
					"供应商编码", "审核状态", "支付状态", "物流状态", "处理状态", "数量", "单位佣金", "佣金小计"};
		return title;

	}

	@Override
	public List<String[]> getDataList() {

		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
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
		
		List<OrderReport> orderReportList = serviceGoodsReportService.findByServiceOrderReport(orderReport);
		
		// 使用条件
		for (OrderReport c : orderReportList) {
		rec = new String[14];
		if(null == c.getOrderTime()){
			rec[0] = "";
		} else {
			rec[0] = DateUtils.formatGeneralDateTimeFormat(c.getOrderTime());
		}
		rec[1] = c.getOrderSubNo();			
		rec[2] = c.getOrderSource();		
		if(c.getIfPayOnArrival() == null || c.getIfPayOnArrival() == 0){
			rec[3] = "在线支付";
		} else {
			rec[3] = "货到付款";
		}
		rec[4] = c.getDistributeType();
		rec[5] = c.getProductCategoryName();
		rec[6] = c.getSupplierCode();
		rec[7] = c.getStatusConfirm();	
		rec[8] = c.getStatusPay();	
		rec[9] = c.getLogisticsStatus();
		rec[10] = c.getStatusTotal();
		if(null != c.getSaleNum()){
			rec[11] = c.getSaleNum().toString();		
		}
		if(null != c.getUnitDeductedPrice()){
			rec[12] = c.getUnitDeductedPrice().toString();		
		}
		if(null != c.getPayAmount()){
			rec[13] = c.getPayAmount().toString();		
		}
		dataList.add(rec);
		}	
		return dataList;
	}

	@Override
	public String getFileName() {
		String filename = "serviceGoodsOrderReport.xls";
		return filename;
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}

}
