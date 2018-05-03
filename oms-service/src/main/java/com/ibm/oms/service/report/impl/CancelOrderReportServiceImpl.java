package com.ibm.oms.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.report.CancelOrderReportDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.CancelOrderReportService;
import com.ibm.oms.service.util.SelfTakePointUtil;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
public class CancelOrderReportServiceImpl extends BaseServiceImpl<OrderReport, Long> implements CancelOrderReportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	protected CancelOrderReportDao cancelOrderReportDao;	
	@Autowired
	protected SelfTakePointUtil selfTakePointUtil;	
	@Autowired
	CommonReportUtilService commonReportUtilService;
	
	@Override
	public Pager<?> findByCancelOrderReport(OrderReport orderReport, Pager<?> pager) {
		try{
			// 获取用来搜索的transportAreaId
			getTransportAreaQueryId(orderReport);
			pager = cancelOrderReportDao.findByOrderReport(orderReport, pager);
			List<OrderReport> list = (List<OrderReport>) pager.getList();
			setSelfTakePointName(list);
			changeOrderStatusCodeToName(list);
			changeCodeToName(list);
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return pager;
	}

	@Override
	public List<OrderReport> findByCancelOrderReport(OrderReport orderReport) {
		List<OrderReport> list = new ArrayList<OrderReport>();
		try{
			// 获取用来搜索的transportAreaId
			getTransportAreaQueryId(orderReport);
			list = cancelOrderReportDao.findByOrderReport(orderReport);
			setSelfTakePointName(list);
			changeOrderStatusCodeToName(list);
			changeCodeToName(list);
		}catch (Exception e) {
			logger.info("{}", e);
		}
		return list;
	}

	/**
	 * 获取用来搜索的transportAreaId
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
	
	/**
	 * 循环获取自提点名称
	 * @param resultList
	 */
	public void setSelfTakePointName(List<OrderReport> resultList){
		
		Long selfTakePointId = null;
		String pointName = "";
		SelfTakePoint selfTakePoint = null;
		Map<String,SelfTakePoint> selfTakePointMap = selfTakePointUtil.getSelfTakePointMap();
		
		for(OrderReport orderReport : resultList) {
			selfTakePointId = orderReport.getSelfTakePointId();
			if(null == orderReport.getSelfTakePointId()){
				continue;
			}
			selfTakePoint = selfTakePointMap.get(selfTakePointId.toString());
			if(null != selfTakePoint) {
				pointName = selfTakePointMap.get(selfTakePointId.toString()).getPointName();
				orderReport.setPointName(pointName);
			}
		}
	}
	
	/**
	 * 订单状态编码转化状态名称
	 * @param resultList
	 */
	public void changeOrderStatusCodeToName(List<OrderReport> resultList) {
				
		Map<String,OrderStatus> orderStatusMap = OrderStatus.getStatusMap();
		String statusConfirm = null;
		String statusPay = null;
		for (OrderReport orderReport: resultList) {
			statusConfirm = orderReport.getStatusConfirm();
			if(null != orderStatusMap.get(statusConfirm)){
				orderReport.setStatusConfirm(orderStatusMap.get(statusConfirm).getDesc());	
			}
			statusPay = orderReport.getStatusPay();
			if(null != orderStatusMap.get(statusPay)){
				orderReport.setStatusPay(orderStatusMap.get(statusPay).getDesc());	
			}
		}
	}
	
	/**
	 * 获取名称（订单类型、订单来源）
	 * @param resultList
	 */
	public void changeCodeToName(List<OrderReport> resultList) {
		Map<String, OrderDic> orderTypeMap = commonReportUtilService.getOrderTypeMap();
		Map<String, OrderDic> orderSourceMap = commonReportUtilService.getOrderSourceMap();		
		Map<String, Option> memberVipLevelMap = commonReportUtilService.getMemberVipLevelMap();
		String orderType = null;
		String orderSource = null;
		String memberVipLevel = null;
		for (OrderReport orderReport : resultList) {
			// 获取订单类型名称
			orderType = orderReport.getOrderType();
			if(null != orderTypeMap.get(orderType)){
				orderReport.setOrderType(orderTypeMap.get(orderType).getDicName());
			}
			// 获取订单来源名称
			orderSource = orderReport.getOrderSource();
			if(null != orderSourceMap.get(orderSource)){
				orderReport.setOrderSource(orderSourceMap.get(orderSource).getDicName());
			}
			// 获取会员等级
			memberVipLevel = orderReport.getVipLevel();
			if(null != memberVipLevelMap.get(memberVipLevel)){
				orderReport.setVipLevel(memberVipLevelMap.get(memberVipLevel).getName());
			}
		}
	}
}
