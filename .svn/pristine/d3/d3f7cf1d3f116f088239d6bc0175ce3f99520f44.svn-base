package com.ibm.oms.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.report.ServiceGoodsOrderReportDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.ServiceGoodsReportService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("serviceGoodsReportService")
public class ServiceGoodsReportServiceImpl extends BaseServiceImpl<OrderReport, Long> implements ServiceGoodsReportService  {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	ServiceGoodsOrderReportDao serviceGoodsOrderReportDao;
	@Autowired
	CommonReportUtilService commonReportUtilService;
	
	@Override
	public Pager<?> findByServiceOrderReport(OrderReport orderReport,
			Pager<?> pager) {
		try {
			pager = serviceGoodsOrderReportDao.findByServiceOrderReport(orderReport, pager);
			List<OrderReport> list = (List<OrderReport>) pager.getList();
			changeOrderStatusCode2Name(list);
			changeCodeToName(list);
		} catch (Exception e) {
			logger.info("{}", e);
		}

		return pager;
	}

	@Override
	public List<OrderReport> findByServiceOrderReport(OrderReport orderReport) {
		List<OrderReport> list = new ArrayList<OrderReport>();
		try {
			// 获取用来搜索的transportAreaId
			list = serviceGoodsOrderReportDao.findByServiceOrderReport(orderReport);
			changeOrderStatusCode2Name(list);
			changeCodeToName(list);
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return list;
	}

	/**
	 * 转换OrderStatus的code为name
	 * 
	 * @param resultList
	 */
	public void changeOrderStatusCode2Name(List<OrderReport> resultList) {
		// 订单状态
		String statusTotal = null;
		// 审核状态
		String statusConfirm = null;
		// 支付状态
		String statusPay = null;
		// 物流状态
		String logisticsStatus = null;
		List<OrderStatus> orderStatuss = null;

		for (OrderReport orderReport : resultList) {
			statusTotal = orderReport.getStatusTotal();
			statusConfirm = orderReport.getStatusConfirm();
			statusPay = orderReport.getStatusPay();
			logisticsStatus = orderReport.getLogisticsStatus();
			// 获取所有状态
			orderStatuss = OrderStatus.getAll();
			for (OrderStatus orderStatus : orderStatuss) {
				if (null == orderStatus)
					continue;
				if (null != statusTotal) {
					if (statusTotal.equals(orderStatus.getCode())) {
						orderReport.setStatusTotal(orderStatus.getDesc());
					}
				}
				if (null != statusConfirm) {
					if (statusConfirm.equals(orderStatus.getCode())) {
						orderReport.setStatusConfirm(orderStatus.getDesc());
					}
				}
				if (null != statusPay) {
					if (statusPay.equals(orderStatus.getCode())) {
						orderReport.setStatusPay(orderStatus.getDesc());
					}
				}
				if (null != logisticsStatus) {
					if (logisticsStatus.equals(orderStatus.getCode())) {
						orderReport.setLogisticsStatus(orderStatus.getDesc());
					}
				}
			}
		}
	}
	public void changeCodeToName(List<OrderReport> resultList) {
		Map<String, OrderDic> orderSourceMap = commonReportUtilService.getOrderSourceMap();
		Map<String, Option> distributeTypeMap = commonReportUtilService.getDistributeTypeMap();
		String orderSource = null;
		String distributeType = null;
		for (OrderReport orderReport : resultList) {
			// 获取订单来源名称
			orderSource = orderReport.getOrderSource();
			if(null != orderSourceMap.get(orderSource)){
				orderReport.setOrderSource(orderSourceMap.get(orderSource).getDicName());
			}
			// 获取配送方式名称
			distributeType = orderReport.getDistributeType();
			if(null != distributeTypeMap.get(distributeType)){
				orderReport.setDistributeType(distributeTypeMap.get(distributeType).getName());
			}
		}
	}
}
