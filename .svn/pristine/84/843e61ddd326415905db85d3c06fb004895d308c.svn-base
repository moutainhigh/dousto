package com.ibm.oms.service.business.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.ModifyOrderService;
import com.ibm.oms.service.util.CommonConstService;

@Service("modifyOrderService")
public class ModifyOrderServiceImpl  implements ModifyOrderService {

	@Autowired
	IntfReceivedService intfReceivedService;

	@Autowired
	OrderSubService orderSubService;
	
	@Autowired
	OrderOperateLogService orderOperateLogService;
	
	@Override
	public CommonOutputDTO updateOrderSub(OrderSub orderSub) {
		
		CommonOutputDTO output = new CommonOutputDTO();
		
		// 更新子订单信息
		String orderSubNo = orderSub.getOrderSubNo();	
		OrderSub oldOrderSub = orderSubService.getByOrderSubNo(orderSubNo);		
		oldOrderSub.setUserName(orderSub.getUserName());
		oldOrderSub.setPhoneNum(orderSub.getPhoneNum());
		oldOrderSub.setMobPhoneNum(orderSub.getMobPhoneNum());
		oldOrderSub.setAddressDetail(orderSub.getAddressDetail());
		oldOrderSub.setHopeArrivalTime(orderSub.getHopeArrivalTime());
		oldOrderSub.setPickTime(orderSub.getPickTime());
		oldOrderSub.setDateUpdated(new Date());		
		orderSubService.save(oldOrderSub);
		
		saveOrderOperateLog(oldOrderSub,orderSub);
		
		// 保存接口调用日志
		output.setCode(CommonConstService.OK);
		output.setMsg("订单修改成功：" + orderSubNo);
		
		return output;
	}
	
	private void saveOrderOperateLog(OrderSub oldOrderSub, OrderSub newOrderSub) {
		
		OrderOperateLog orderOperateLog = new OrderOperateLog();
		
		// 创建log信息
		orderOperateLog.setIdOrder(oldOrderSub.getIdOrder());
		orderOperateLog.setOrderNo(oldOrderSub.getOrderNo());
		orderOperateLog.setIdOrderSub(oldOrderSub.getId());
		orderOperateLog.setOrderSubNo(oldOrderSub.getOrderSubNo());
		orderOperateLog.setBillType(oldOrderSub.getBillType());	
		orderOperateLog.setDateCreated(new Date());
		orderOperateLog.setDateUpdated(new Date());
		orderOperateLog.setIsDeleted(1L);
		
		// 收件人是否修改
		if(!oldOrderSub.getUserName().equals(newOrderSub.getUserName())){
			orderOperateLog.setOldData(oldOrderSub.getUserName());
			orderOperateLog.setNewData(newOrderSub.getUserName());
			orderOperateLogService.save(orderOperateLog);
		}
		
		// 电话号码是否修改
		if(!oldOrderSub.getPhoneNum().equals(newOrderSub.getPhoneNum())){
			orderOperateLog.setOldData(oldOrderSub.getPhoneNum());
			orderOperateLog.setNewData(newOrderSub.getPhoneNum());
			orderOperateLogService.save(orderOperateLog);
		}
		
		// 手机号码是都修改
		if(!oldOrderSub.getMobPhoneNum().equals(newOrderSub.getMobPhoneNum())){
			orderOperateLog.setOldData(oldOrderSub.getMobPhoneNum());
			orderOperateLog.setNewData(newOrderSub.getMobPhoneNum());
			orderOperateLogService.save(orderOperateLog);
		}
		
		// 详细地址是否修改
		if(!oldOrderSub.getAddressDetail().equals(newOrderSub.getAddressDetail())){
			orderOperateLog.setOldData(oldOrderSub.getAddressDetail());
			orderOperateLog.setNewData(newOrderSub.getAddressDetail());
			orderOperateLogService.save(orderOperateLog);
		}
		
		// 希望送达日期是否修改
		if(!oldOrderSub.getHopeArrivalTime().equals(newOrderSub.getHopeArrivalTime())){
			orderOperateLog.setOldData(oldOrderSub.getHopeArrivalTime().toString());
			orderOperateLog.setNewData(newOrderSub.getHopeArrivalTime().toString());
			orderOperateLogService.save(orderOperateLog);
		}
		// 上门自取日期是否修改
		if(!oldOrderSub.getPickTime().equals(newOrderSub.getPickTime())){
			orderOperateLog.setOldData(oldOrderSub.getPickTime().toString());
			orderOperateLog.setNewData(newOrderSub.getPickTime().toString());
			orderOperateLogService.save(orderOperateLog);
		}	
	}
}
