package com.ibm.oms.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.report.RefundOrderReportDao;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.RefundOrderReportService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("refundOrderReportService")
public class RefundOrderReportServiceImpl extends BaseServiceImpl<OrderReport, Long> implements RefundOrderReportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	protected RefundOrderReportDao refundOrderReportDao;
	@Autowired
	CommonReportUtilService commonReportUtilService;
	
	@Override
	public Pager<?> findByRefundOrderReport(OrderReport orderReport,Pager<?> pager) {

		try {
			// 获取用来搜索的transportAreaId
			getTransportAreaQueryId(orderReport);
			pager = refundOrderReportDao.findByRefundOrderReport(orderReport, pager);
			List<OrderReport> list = (List<OrderReport>) pager.getList();
			changeOrderStatusCodeToName(list);
			changeCodeToName(list);
		} catch (Exception e) {
			logger.info("{}", e);
		}

		return pager;
	}

	@Override
	public List<OrderReport> findByRefundOrderReport(OrderReport orderReport) {
		
		List<OrderReport> list = new ArrayList<OrderReport>();
		try {
			// 获取用来搜索的transportAreaId
			getTransportAreaQueryId(orderReport);
			list = refundOrderReportDao.findByRefundOrderReport(orderReport);
			changeOrderStatusCodeToName(list);
			changeCodeToName(list);
			return list;
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return list;
	}

	/**
	 * 获取用来搜索的transportAreaId
	 * 
	 * @param orderReport
	 */
	public void getTransportAreaQueryId(OrderReport orderReport) {
		// 省/直辖市对应的下拉值
		Long state = orderReport.getState();
		// 城市对应的下拉值
		Long city = orderReport.getCity();
		// 县区对应的下拉值
		Long county = orderReport.getCounty();
		// 街道
		Long street = orderReport.getStreet();
		// 获取优先级：县区 -> 城市 -> 省/直辖市，即省/直辖市、城市、县区都选择时，则使用县区的code去搜索
		if (null != state) {
			orderReport.setTransportAreaId(state);

			if (null != city) {
				orderReport.setTransportAreaId(city);
				if (null != county) {
					orderReport.setTransportAreaId(county);
					if(null != street){
						orderReport.setTransportAreaId(street);
					}
				}
			}
		}
	}

	public void changeOrderStatusCodeToName(List<OrderReport> resultList) {

		Map<String, OrderStatus> orderStatusMap = OrderStatus.getStatusMap();
		String statusConfirm = null;
		for (OrderReport orderReport : resultList) {
			statusConfirm = orderReport.getStatusConfirm();
			if (null != orderStatusMap.get(statusConfirm)) {
				orderReport.setStatusConfirm(orderStatusMap.get(statusConfirm).getDesc());
			}
		}
	}
	
	public void changeCodeToName(List<OrderReport> resultList) {
		Map<String, OrderReason> orderReasonMap = commonReportUtilService.getRefundReasonMap();
		String reason = null;
		
		for (OrderReport orderReport : resultList) {
			// 获取商品退换拒收原因
			reason = orderReport.getRefundReason();
			if(null != orderReasonMap.get(reason)){
				orderReport.setRefundReason(orderReasonMap.get(reason).getReasonName());
				orderReport.setPreRefundReason(orderReasonMap.get(orderReasonMap.get(reason).getParentReasonNo()).getReasonName());
			}
		}
	}
}
