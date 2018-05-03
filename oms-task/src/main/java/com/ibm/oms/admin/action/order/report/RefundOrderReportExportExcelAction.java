package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.dao.constant.OrderReportColumn;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.report.RefundOrderReportService;
import com.ibm.sc.util.DateUtils;

public class RefundOrderReportExportExcelAction extends ExportExcelAction {

	private static final long serialVersionUID = 1L;
	
	private OrderReport orderReport;	
	private DistributeAddress distributeAddress;
	protected int column;
	
	@Resource
	protected RefundOrderReportService refundOrderReportService;

	@Override
	public String[] getTitiles() {
		
		if(OrderReportColumn.ORDER_RETURN == column){
			String[] title = new String[] {"创建日期", "退货单号", "关联原单号", "原订单支付方式", "原订单封闭时间", "审核状态",
					"配送区域", "入库物流方式", "供应商", "退货商品品类", "退货商品编码", "退货商品名称", "退货商品数量", "退货商品金额", "售后原因", "问题描述","退款状态","退款方式","退款金额","封闭时间","质检状态","质检时间","会员等级"};
			return title;
		}else if (OrderReportColumn.ORDER_CHANGE == column) {
			String[] title = new String[] {"创建日期", "换货单号", "关联原单号", "原订单支付方式", "原订单封闭时间", "审核状态",
					"配送区域", "入库物流方式", "供应商", "换货商品品类", "换货商品编码", "换货商品名称", "换货商品数量", "换货商品金额", "售后原因", "问题描述","封闭时间","质检状态","质检时间","会员等级"};
			return title;
		} else {
			String[] title = new String[] {"创建日期", "拒收单号", "关联原单号", "原订单支付方式", "原订单封闭时间", "审核状态",
					"配送区域", "入库物流方式", "供应商", "拒收商品品类", "拒收商品编码", "拒收商品名称", "拒收商品数量", "拒收商品金额","售后原因", "问题描述","退款状态","退款方式","退款金额","封闭时间","质检状态","质检时间","会员等级"};
			return title;
		}
	}

	@Override
	public List<String[]> getDataList() {
		
		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
		if(null == orderReport){
			orderReport	= new OrderReport();
		}
		orderReport.setColumnTitle(column);
		// 配送地址
		orderReport.setDistributeAddress(this.distributeAddress);
		orderReport.setState(this.distributeAddress.getState());
		orderReport.setCity(this.distributeAddress.getCity());
		orderReport.setCounty(this.distributeAddress.getCounty());
		orderReport.setStreet(this.distributeAddress.getStreet());
		
		Map<String,OrderMainConst> distributeTypeMap = getDistributeTypeMap();
		 
		List<OrderReport> orderReportList = refundOrderReportService.findByRefundOrderReport(orderReport);
		
		// 使用条件
		for (OrderReport c : orderReportList) {
			if(OrderReportColumn.ORDER_RETURN == column){
				rec = new String[23];
			}else if (OrderReportColumn.ORDER_CHANGE == column) {
				rec = new String[20];
			} else {
				rec = new String[23];
			}
		if(null == c.getOrderTime()){
			rec[0] = "";
		} else {
			rec[0] = DateUtils.formatGeneralDateTimeFormat(c.getOrderTime());
		}
		rec[1] = c.getOrderSubNo();		
		rec[2] = c.getOrderSubRelatedOrigin();
		if(c.getIfPayOnArrival() == null || c.getIfPayOnArrival() == 0){
			rec[3] = "在线支付";
		} else {
			rec[3] = "货到付款";
		}
		if(null == c.getRelatedSignOffTime()){
			rec[4] = "";
		} else {
			rec[4] = DateUtils.formatGeneralDateTimeFormat(c.getRelatedSignOffTime());
		}
		rec[5] = c.getStatusConfirm();	
		rec[6] = c.getAreaName();
		if(null != distributeTypeMap.get(c.getDistributeType())){
			rec[7] = distributeTypeMap.get(c.getDistributeType()).getDesc();
		}
		rec[8] = c.getSupplierCode();
		rec[9] = c.getProductCategory();
		rec[10] = c.getSkuNo();			
		rec[11] = c.getCommodityName();	
		if(null != c.getSaleNum()){
			rec[12] = c.getSaleNum().toString();		
		}
		if(null != c.getPayAmount()){
			rec[13] = c.getPayAmount().toString();	
		}
		rec[14] = c.getPreRefundReason();
		rec[15] = c.getRefundReason();
		if(OrderReportColumn.ORDER_RETURN == column){
			if(StringUtils.isEmpty(c.getPayName())){
				rec[16] = "已退款";
			}else{
				rec[16] = "未退款";
			}
			rec[17] = c.getPayName();
			if(null != c.getRefundAmount()){
				rec[18] = c.getRefundAmount().toString();	
			}
			if(null == c.getSignOffTime()){
				rec[19] = "";
			} else {
				rec[19] = DateUtils.formatGeneralDateTimeFormat(c.getSignOffTime());
			}
			if(c.getCurrentStatus().equals("0250")){
				rec[20] = "质检通过";
			}else if (c.getCurrentStatus().equals("0160")){
				rec[20] = "质检失败";
			}
			if(null == c.getOperateTime()){
				rec[21] = "";
			} else {
				rec[21] = DateUtils.formatGeneralDateTimeFormat(c.getOperateTime());
			}
			if(c.getVipLevel().equals("1")){
				rec[22] = "微卡";
			} else if(c.getVipLevel().equals("2")) {
				rec[22] = "银卡";
			} else if(c.getVipLevel().equals("3")){
				rec[22] = "金卡";
			} else {
				rec[22] = "";
			}
		}else if (OrderReportColumn.ORDER_CHANGE == column) {
			if(null == c.getSignOffTime()){
				rec[16] = "";
			} else {
				rec[16] = DateUtils.formatGeneralDateTimeFormat(c.getSignOffTime());
			}
			if(c.getCurrentStatus().equals("0250")){
				rec[17] = "质检通过";
			}else if (c.getCurrentStatus().equals("0160")){
				rec[17] = "质检失败";
			}
			if(null == c.getOperateTime()){
				rec[18] = "";
			} else {
				rec[18] = DateUtils.formatGeneralDateTimeFormat(c.getOperateTime());
			}
			if(c.getVipLevel().equals("1")){
				rec[19] = "微卡";
			} else if(c.getVipLevel().equals("2")) {
				rec[19] = "银卡";
			} else if(c.getVipLevel().equals("3")){
				rec[19] = "金卡";
			} else {
				rec[19] = "";
			}
		} else {
			if(StringUtils.isEmpty(c.getPayName())){
				rec[16] = "已退款";
			}else{
				rec[16] = "未退款";
			}
			rec[17] = c.getPayName();
			if(null != c.getRefundAmount()){
				rec[18] = c.getRefundAmount().toString();	
			}
			if(null == c.getSignOffTime()){
				rec[19] = "";
			} else {
				rec[19] = DateUtils.formatGeneralDateTimeFormat(c.getSignOffTime());
			}
			if(c.getCurrentStatus().equals("0250")){
				rec[20] = "质检通过";
			}else if (c.getCurrentStatus().equals("0160")){
				rec[20] = "质检失败";
			}
			if(null == c.getOperateTime()){
				rec[21] = "";
			} else {
				rec[21] = DateUtils.formatGeneralDateTimeFormat(c.getOperateTime());
			}
			if(c.getVipLevel().equals("1")){
				rec[22] = "微卡";
			} else if(c.getVipLevel().equals("2")) {
				rec[22] = "银卡";
			} else if(c.getVipLevel().equals("3")){
				rec[22] = "金卡";
			} else {
				rec[22] = "";
			}
		}

		dataList.add(rec);
		}	
		return dataList;
	}

	@Override
	public String getFileName() {
		
		String filename;		
		if(OrderReportColumn.ORDER_RETURN == column){
			filename = "returnOrderReport.xls";
		}else if (OrderReportColumn.ORDER_CHANGE == column) {
			filename = "changeOrderReport.xls";
		} else {
			filename = "rejectOrderReport.xls";
		}
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

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
    private Map<String, OrderMainConst> getDistributeTypeMap() {
        Map<String,OrderMainConst> distributeTypeMap = new HashMap<String,OrderMainConst>();
//        distributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_PickFromDoor.getCode(), OrderMainConst.OrderSub_DistributeType_PickFromDoor);
        distributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_CustomerSend.getCode(), OrderMainConst.OrderSub_DistributeType_CustomerSend);
//        distributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode(), OrderMainConst.OrderSub_DistributeType_ReturnStore);
        return distributeTypeMap;

    }
	
}
