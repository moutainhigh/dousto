package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.CMBPaymentReportService;
import com.ibm.sc.util.DateUtils;

public class PaymentOrderReportExportExcelAction extends ExportExcelAction {

	private static final long serialVersionUID = 1L;
	
	private OrderReport orderReport;	
	
	@Resource
	protected CMBPaymentReportService cMBPaymentReportService;
	
	@Override
	public String[] getTitiles() {
		String[] title = new String[] {"创建日期", "订单号", "招行订单号", "交易流水号", "金额", "交易时间", "支付状态"};
		return title;
	}

	@Override
	public List<String[]> getDataList() {
		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
		if(null == orderReport){
			orderReport	= new OrderReport();
		}
		 
		List<OrderReport> orderReportList = cMBPaymentReportService.findByCMBPaymentReport(orderReport);
		
		// 使用条件
		for (OrderReport c : orderReportList) {
		rec = new String[7];
		if(null == c.getOrderTime()){
			rec[0] = "";
		} else {
			rec[0] = DateUtils.formatGeneralDateTimeFormat(c.getOrderTime());
		}
		rec[1] = c.getOrderSubNo();	
		rec[2] = "0" + c.getCmbOrderId().toString();		
		rec[3] = c.getSerialNo();
		if(null != c.getPayAmount()){
			rec[4] = c.getPayAmount().toString();		
		}
		if(null == c.getPayTime()){
			rec[5] = "";
		} else {
			rec[5] = DateUtils.formatGeneralDateTimeFormat(c.getPayTime());
		}
		rec[6] = "成功";
		dataList.add(rec);
		}	
		return dataList;
	}

	@Override
	public String getFileName() {
		
		String filename = "paymentOrderReport.xls";
		return filename;
	}

}
