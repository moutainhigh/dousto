package com.ibm.oms.service.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderConfirmReceiptService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.sys.UserService;
import com.ibm.sc.util.DateUtils;

/**
 * Description: //定时任务确认收货
 * 
 * @author Yusl Date: 2018年1月30日
 */

@Service("orderConfirmReceiptService")
public class OrderConfirmReceiptServiceImpl implements OrderConfirmReceiptService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderMainService orderMainService;

	@Resource
	protected UserService userService;
	@Resource
	SaleAfterOrderTransService saleAfterOrderTransService;

	@Resource
	SaleAfterOrderService saleAfterOrderService;

	@Autowired
	OrderStatusService orderStatusService;

	@Autowired
	OrderNoService orderNoService;

	@Autowired
	OrderCreateService orderCreateService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.service.business.OrderConfirmReceiptService#
	 * orderConfirmReceipt(int)
	 */
	// 定时任务 已发货未确认收货的订单 确认收货
	@Override
	public void orderConfirmReceipt(int count) {

		logger.debug("orderConfirmReceipt start");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", DateUtils.getDate(-7));
		params.put("statusCode", "0170");
		List<String > mainList = orderMainService.findOrderByStatusAndDate(params, count);

		OrderStatusAction orderStatusAction = null;
		for (String values : mainList) {

			OrderMain orderMain=orderMainService.findByOrderNo(values);
			String loginUserName = "";// 当前登陆者
			User user = userService.getLoginUser();
			if (null != user) {
				loginUserName = user.getUserName();
				if (StringUtils.isEmpty(loginUserName)) {
					loginUserName = String.valueOf(user.getId());
				}
			}

			String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo());
			try {

				boolean isStatus = this.orderStatusService.saveProcess(orderSubNo, orderStatusAction.S017080, loginUserName, new Date(), null);
			} catch (Exception e) {
				
				logger.error("订单自动确认失败, {}", e);
			}
		}


		logger.debug("orderConfirmReceipt end");
	}
	

	private boolean isNull(Object obj){
		return obj==null?true:false;
	}
}
