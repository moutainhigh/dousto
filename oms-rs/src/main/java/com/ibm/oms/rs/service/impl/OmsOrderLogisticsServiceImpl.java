package com.ibm.oms.rs.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ibm.oms.rs.service.OmsOrderLogisticsService;
import com.ibm.oms.service.OrderLogisticsMessageService;
import com.ibm.oms.service.OrderLogisticsService;
import com.ibm.oms.service.util.MD5Util;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: //模块目的、功能描述
 * 
 * @author Yusl Date: 2018年3月19日
 */
@Component("omsOrderLogisticsService")
public class OmsOrderLogisticsServiceImpl extends BaseRsServiceImpl implements OmsOrderLogisticsService {

	private final Logger logger = LoggerFactory.getLogger(OmsOrderLogisticsServiceImpl.class);

	@Autowired
	OrderLogisticsMessageService orderLogisticsMessageService;

	@Autowired
	OrderLogisticsService orderLogisticsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.rs.service.OmsOrderLogisticsService#createOrderLogistics(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	@POST
	@Path("/createOrderLogistics")
	public String createOrderLogistics(String param, String sign) {

		logger.info("{}接收参数：sign={}", sign);
		logger.info("{}接收参数：param={}", param);
		String key = MD5Util.MD5Encode(param, "UTF-8");
		if (1 == 1) {
			orderLogisticsMessageService.saveOrderLogisticsMessage(param);
			orderLogisticsService.saveOrderLogistic(param);
			Map ret = new HashMap();
			ret.put("result", "true");
			ret.put("returnCode", "200");
			ret.put("message", "成功");
			return JSONObject.toJSONString(ret);
		} else {

			Map ret = new HashMap();
			ret.put("result", "false");
			ret.put("returnCode", "300");
			ret.put("message", "失败，密钥对比错误");

			return JSONObject.toJSONString(ret);
		}

	}

}
