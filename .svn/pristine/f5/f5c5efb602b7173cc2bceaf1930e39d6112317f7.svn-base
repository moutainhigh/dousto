package com.ibm.oms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.order.OrderLogisticsMessageClientDTO;
import com.ibm.oms.dao.constant.LogisticsMessage;
import com.ibm.oms.dao.intf.OrderLogisticsMessageDao;
import com.ibm.oms.dao.intf.OrderSubDao;
import com.ibm.oms.domain.persist.OrderLogisticsMessage;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.service.OrderLogisticsMessageService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.sc.service.impl.BaseServiceImpl;

import net.sf.json.JSONObject;

/**
 * Description: //模块目的、功能描述
 * 
 * @author Yusl Date: 2018年3月1日
 */

@Service("orderLogisticsMessageService")
public class OrderLogisticsMessageServiceImpl extends BaseServiceImpl<OrderLogisticsMessage, Long> implements OrderLogisticsMessageService {

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderSubService orderSubService;

	@Autowired
	private OrderSubDao orderSubDao;
	@Autowired
	private OrderLogisticsMessageDao orderLogisticsMessageDao;

	/*
	 * 查询物流信息
	 * 
	 * @see com.ibm.oms.service.OrderLogisticsMessageService#
	 * findOrderLogisticsMessageByOrderSubNo(java.lang.String)
	 */
	@Override
	public List<OrderLogisticsMessageClientDTO> findOrderLogisticsMessageByOrderSubNo(String OrderNo) {
		// TODO Auto-generated method stub
		List<OrderLogisticsMessage> OrderLogisticsMessages = new ArrayList<OrderLogisticsMessage>();

		List<OrderLogisticsMessageClientDTO> OrderLogisticsMessageClientDTOs = new ArrayList<OrderLogisticsMessageClientDTO>();
		OrderLogisticsMessages = orderLogisticsMessageDao.findOrderLogisticsMessageByOrderSubNo(OrderNo);

		for (OrderLogisticsMessage orderLogisticsMessage : OrderLogisticsMessages) {
			OrderLogisticsMessageClientDTO olm = new OrderLogisticsMessageClientDTO();
			olm.setOrderNo(orderLogisticsMessage.getOrderSubNo());
			olm.setWmsDesc(orderLogisticsMessage.getWmsDesc());
			olm.setWmsTime(orderLogisticsMessage.getWmsTime());
			olm.setTeackingNumber(orderLogisticsMessage.getTeackingNumber());
			olm.setState(LogisticsMessage.getDesc(orderLogisticsMessage.getState()));
			OrderLogisticsMessageClientDTOs.add(olm);
		}
		return OrderLogisticsMessageClientDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.service.OrderLogisticsMessageService#
	 * saveOrderLogisticsMessage(java.util.Map)
	 */
	@Override
	public void saveOrderLogisticsMessage(String param) {
		System.out.println("json============="+param);
		Map map = JSONObject.fromObject(param);
		Map lastResult = (Map) map.get("lastResult");
		List data = (List) lastResult.get("data");
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String teackingNumber = (String) lastResult.get("nu");
		Date newtime = null;
		List<OrderLogisticsMessage> olms = orderLogisticsMessageDao.findOrderLogisticsMessageByTeackingNumber(teackingNumber);
		if (olms.size() != 0) {
			newtime = olms.get(0).getWmsTime();
		}
		for (Object object : data) {

			OrderLogisticsMessage olm = new OrderLogisticsMessage();
			Map logisticsMessage = (Map) object;
			String context = (String) logisticsMessage.get("context");
			Date time = null;
			try {
				time = formatter.parse((String) logisticsMessage.get("time"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String status = (String) logisticsMessage.get("status");
			List<OrderSub> findByField = orderSubDao.findByField(OrderSub_.logisticsOutNo, teackingNumber);
			if (findByField.size() != 0) {
				String orderSubNo = findByField.get(0).getOrderSubNo();
				String orderNo = findByField.get(0).getOrderNo();
				olm.setOrderNo(orderNo);
				olm.setOrderSubNo(orderSubNo);
			}
			olm.setTeackingNumber(teackingNumber);
			olm.setWmsDesc(context);
			olm.setWmsTime(time);
			olm.setStatus(status);
			olm.setState((String) lastResult.get("state"));
			if (newtime != null) {
				if (time.getTime() > newtime.getTime()) {
					orderLogisticsMessageDao.save(olm);
				}
			} else {

				orderLogisticsMessageDao.save(olm);
			}
		}

	}

	/* 通过运单号查询物流信息
	 * @see com.ibm.oms.service.OrderLogisticsMessageService#findOrderLogisticsMessageByTeackingNumber(java.lang.String)
	 */
	@Override
	public List<OrderLogisticsMessage> findOrderLogisticsMessageByTeackingNumber(String TeackingNumber) {
		// TODO Auto-generated method stub
		return orderLogisticsMessageDao.findOrderLogisticsMessageByTeackingNumber(TeackingNumber);
	}

}
