package com.ibm.oms.admin.action.order.sms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;


/**
 * @author
 *虚拟订单发短信查询action
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class SmsVirtualQueryAction extends AbstractOrderAction {

	private static final long serialVersionUID = 1L;
	
    @Autowired
    OrderNoService orderNoService;
    
    @Autowired
    OrderCreateService orderCreateService;
	

	public String execute() throws Exception {
		orderMain.setOrderType(CommonConst.OrderMain_OrderType_VIRTUAL.getCode());
		orderSearch = new OrderSearch(orderMain, orderSub, orderItem, orderPay,transportArea,distributeAddress,selfTakePoint,includeOrNot,orderPayMode);
		pager = orderSearchService.findByOrderSearch(column, orderSearch, pager);
		
		// 设置订单页面标题
		super.setOrderColumnTitle(column);
		return "sms_virtual_list";
	}
	
	
}