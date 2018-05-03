package com.ibm.oms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderLogisticsDao;
import com.ibm.oms.dao.intf.OrderSubDao;
import com.ibm.oms.domain.persist.OrderLogistics;
import com.ibm.oms.domain.persist.OrderLogistics_;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.service.OrderLogisticsService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.sc.service.impl.BaseServiceImpl;

import net.sf.json.JSONObject;

/**
 * Description: //模块目的、功能描述
 * 
 * @author Yusl Date: 2018年3月15日
 */

@Service("orderLogisticsService")
public class OrderLogisticsServiceImpl extends BaseServiceImpl<OrderLogistics, Long> implements OrderLogisticsService {

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderSubService orderSubService;

	@Autowired
	private OrderLogisticsDao orderLogisticsDao;

	@Autowired
	private OrderSubDao orderSubDao;

	/*
	 * 添加物流主表信息
	 * 
	 * @see
	 * com.ibm.oms.service.OrderLogisticsService#saveOrderLogistic(java.lang.
	 * String)
	 */
	@Override
	public void saveOrderLogistic(String param) {
		Map map = JSONObject.fromObject(param);
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map lastResult = (Map) map.get("lastResult");
		List data = (List) lastResult.get("data");
		String teackingNumber = (String) lastResult.get("nu");
		Map logisticsMessage = (Map) data.get(0);
		String context = (String) logisticsMessage.get("context");
		Date time = null;
		try {
			time = formatter.parse((String) logisticsMessage.get("time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String orderSubNo = null;
		String orderNo = null;
		List<OrderSub> findByField = orderSubDao.findByField(OrderSub_.logisticsOutNo, teackingNumber);
		if (findByField.size() != 0) {
			orderSubNo = findByField.get(0).getOrderSubNo();
			orderNo = findByField.get(0).getOrderNo();
		}
		List<OrderLogistics> ols = orderLogisticsDao.findByField(OrderLogistics_.trackingNumber, teackingNumber);
		if (ols.size() != 0) {
			OrderLogistics ol = ols.get(0);
			ol.setOrderNo(orderNo);
			ol.setOrderSubNo(orderSubNo);
			ol.setTrackingNumber(teackingNumber);
			ol.setMonitoringStatus((String) map.get("status"));
			ol.setMonitoringMessage((String) map.get("message"));
			ol.setAutoCheck((String) map.get("autoCheck"));
			ol.setComOld((String) map.get("comOld"));
			ol.setComNew((String) map.get("comNew"));
			ol.setState((String) lastResult.get("state"));
			ol.setConditions((String) lastResult.get("condition"));
			ol.setIscheck((String) lastResult.get("ischeck"));
			ol.setCom((String) lastResult.get("com"));
			orderLogisticsDao.save(ol);
		} else {
			OrderLogistics ol2 = new OrderLogistics();

			ol2.setOrderNo(orderNo);
			ol2.setOrderSubNo(orderSubNo);
			ol2.setTrackingNumber(teackingNumber);
			ol2.setMonitoringStatus((String) map.get("status"));
			ol2.setMonitoringMessage((String) map.get("message"));
			ol2.setAutoCheck((String) map.get("autoCheck"));
			ol2.setComOld((String) map.get("comOld"));
			ol2.setComNew((String) map.get("comNew"));
			ol2.setState((String) lastResult.get("state"));
			ol2.setConditions((String) lastResult.get("condition"));
			ol2.setIscheck((String) lastResult.get("ischeck"));
			ol2.setCom((String) lastResult.get("com"));

			orderLogisticsDao.save(ol2);
		}
	}
}
