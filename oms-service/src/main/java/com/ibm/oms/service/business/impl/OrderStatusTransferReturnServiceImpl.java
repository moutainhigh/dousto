package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.order.OrderSplitTransferReturnClientDTO;
import com.ibm.oms.client.dto.order.OrderSplitTransferReturnDetailClientDTO;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusTransferReturnService;
import com.ibm.oms.service.util.CommonUtilService;

@Service("orderStatusTransferReturnService")
public class OrderStatusTransferReturnServiceImpl implements OrderStatusTransferReturnService{
	
	@Autowired
	OrderMainService orderMainService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired 
	OrderItemService orderItemService;
	@Autowired 
	OrderNoService orderNoService;
	@Autowired
	CommonUtilService commonUtilService;
	public static String ORDER_STATUS = "DELIVERED";
	
	@Override
	public OrderSplitTransferReturnClientDTO handleOrderStatusTransferReturn(String orderSubNo) {
		OrderSplitTransferReturnClientDTO od = new OrderSplitTransferReturnClientDTO();
		OrderMain orderMain  = orderMainService.getByOrderSubNo(orderSubNo);
		OrderSub oderSub = orderSubService.getByOrderSubNo(orderSubNo);
		List<OrderItem> items = orderItemService.getByOrdeSubNo(orderSubNo);
		//根据接口要求 目前只事set必填字段，其余非必填字段 待xiahui确认后set 03/07/2018
		od.setOrderCode(orderMain.getAliasOrderNo());
		od.setOrderStatus(ORDER_STATUS);
		od.setShippingOrderNo(oderSub.getShippingOrderNo());
		od.setLogisticsProviderCode(oderSub.getDeliveryMerchantNo());
		List<OrderSplitTransferReturnDetailClientDTO> transferReturnDetails = new  ArrayList<OrderSplitTransferReturnDetailClientDTO>();
		for(OrderItem it : items){
			OrderSplitTransferReturnDetailClientDTO dto  = new OrderSplitTransferReturnDetailClientDTO();
			dto.setSkuCode(it.getSkuNo());
			dto.setNormalQuantity(Integer.valueOf(commonUtilService.Long2Str(it.getSaleNum())));
			//dto.setDefectiveQuantity(Integer.valueOf(commonUtilService.Long2Str(it.getSaleNum())));
			transferReturnDetails.add(dto);
		}
		od.setTransferReturnDetails(transferReturnDetails);
		//保存interface send
		commonUtilService.writeIntfSentJson(orderMain.getOrderNo(), oderSub.getOrderSubNo(), IntfReceiveConst.ORDER_STATUS_RETURN_BS.getCode(), od);
		return od;
	}

}
