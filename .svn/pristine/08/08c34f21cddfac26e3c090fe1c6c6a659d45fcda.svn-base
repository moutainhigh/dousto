package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.service.report.BaseOrderReportService;
import com.ibm.sc.model.shipping.SelfTakePoint;

/**
 * @author liucy
 * 
 */
@ParentPackage("admin")
public class BaseOrderReportExportExcelAction extends ExportExcelAction {
	
	protected TransportArea transportArea = new TransportArea();

	protected SelfTakePoint selfTakePoint = new SelfTakePoint();

	protected DistributeAddress distributeAddress = new DistributeAddress();
	
	private OrderReport orderReport;

	private static final long serialVersionUID = 713657284207289329L;

	@Resource
	private BaseOrderReportService baseOrderReportService;

	public String[] getTitiles(){
		// 写头
		String[] title = new String[] {"下单日期", "订单类型", "子订单号", "收件人", "会员账号",
				"手机号码", "配送地址", "订单总重量", "订单总金额", "审核状态", "支付状态", "物流状态",
				"处理状态", "订单来源", "支付方式", "配送方式", "自提点 ", "物流商"};
		return title;
	}
	
	public List<String[]> getDataList() {
		
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
		
		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
		List<OrderReport> orderReportList = (List<OrderReport>) baseOrderReportService.findByOrderReport(orderReport);
		// 使用条件
		for (OrderReport c : orderReportList) {
			rec = new String[18];
			rec[0] = c.getOrderTime() + "";
			rec[1] = c.getOrderType();
			rec[2] = c.getOrderSubNo();
			rec[3] = c.getUserName();
			rec[4] = c.getMemberNo();
			rec[5] = c.getMobPhoneNum();
			rec[6] = c.getAreaName();
			if(c.getWeight() == null){
				rec[7] = "0";
			}else{
				rec[7] = c.getWeight().toString();	
			}
			if(c.getTotalPayAmount() == null){
				rec[8] = "0";
			} else {
				rec[8] = c.getTotalPayAmount().toString();
			}
			rec[9] = c.getStatusConfirm();
			rec[10] = c.getStatusPay();
			rec[11] = c.getLogisticsStatus();
			rec[12] = c.getStatusTotal();
			rec[13] = c.getOrderSource();
			if(c.getIfPayOnArrival() == null || c.getIfPayOnArrival() == 0){
				rec[14] = "在线支付";
			} else {
				rec[14] = "货到付款";
			}
			rec[15] = c.getDistributeType();
			if(c.getSelfFetchAddress() == null){
				rec[16] = "总仓";
			} else {
				rec[16] = c.getSelfFetchAddress();
			}
			rec[17] = c.getDeliveryMerchantName();
			dataList.add(rec);
		}
		return dataList;
	}

	public String getFileName() {
		String filename = "baseOrderReport.xls";
		return filename;
	}

	public TransportArea getTransportArea() {
		return transportArea;
	}

	public void setTransportArea(TransportArea transportArea) {
		this.transportArea = transportArea;
	}

	public SelfTakePoint getSelfTakePoint() {
		return selfTakePoint;
	}

	public void setSelfTakePoint(SelfTakePoint selfTakePoint) {
		this.selfTakePoint = selfTakePoint;
	}

	public DistributeAddress getDistributeAddress() {
		return distributeAddress;
	}

	public void setDistributeAddress(DistributeAddress distributeAddress) {
		this.distributeAddress = distributeAddress;
	}

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(OrderReport orderReport) {
		this.orderReport = orderReport;
	}
}
