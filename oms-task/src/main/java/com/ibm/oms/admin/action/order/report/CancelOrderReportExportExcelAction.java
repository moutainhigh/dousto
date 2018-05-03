package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.CancelOrderReportService;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.util.DateUtils;

/**
 * @author liucy
 * 
 */
public class CancelOrderReportExportExcelAction extends ExportExcelAction {

	private static final long serialVersionUID = 1L;
	
	private OrderReport orderReport;	
	private DistributeAddress distributeAddress;
	protected SelfTakePoint selfTakePoint;
	
	@Resource
	protected CancelOrderReportService cancelOrderReportService;
	
	@Override
	public String[] getTitiles() {
		// 写头
		String[] title = new String[] {"下单日期", "子订单号", "订单类型", "订单来源", "审核状态", "支付状态",
				"会员账号", "会员级别", "订单总金额", "配送地址", "自提点"};
		return title;
	}

	@Override
	public List<String[]> getDataList() {
				
		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
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
		
		List<OrderReport> orderReportList = cancelOrderReportService.findByCancelOrderReport(orderReport);
		
		// 使用条件
		for (OrderReport c : orderReportList) {
		rec = new String[11];
		if(null == c.getOrderTime()){
			rec[0] = "";
		} else {
			rec[0] = DateUtils.formatGeneralDateTimeFormat(c.getOrderTime());
		}
		rec[1] = c.getOrderSubNo();
		rec[2] = c.getOrderType();
		rec[3] = c.getOrderSource();
		rec[4] = c.getStatusConfirm();
		rec[5] = c.getStatusPay();
		rec[6] = c.getMemberNo();
		rec[7] = c.getVipLevel();
		if(c.getTotalPayAmount() == null){
			rec[8] = "0";
		}else{
			rec[8] = c.getTotalPayAmount().toString();
		}
		rec[9] = c.getAreaName();	
		rec[10] = c.getPointName();	
		dataList.add(rec);
		}
		return dataList;
	}
	
	public String getFileName() {
		String filename = "cancelOrderReport.xls";
		return filename;
	}
	
	public OrderReport getOrderReport() {
		return orderReport;
	}
	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
	public DistributeAddress getDistributeAddress() {
		return distributeAddress;
	}
	public void setDistributeAddress(DistributeAddress distributeAddress) {
		this.distributeAddress = distributeAddress;
	}
	public SelfTakePoint getSelfTakePoint() {
		return selfTakePoint;
	}
	public void setSelfTakePoint(SelfTakePoint selfTakePoint) {
		this.selfTakePoint = selfTakePoint;
	}
}
