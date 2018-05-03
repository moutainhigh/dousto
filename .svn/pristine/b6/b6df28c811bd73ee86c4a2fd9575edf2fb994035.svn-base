package com.ibm.oms.service.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.service.OrderDicService;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.sys.OptionService;

@Component
public class CommonReportUtilService {

	@Resource
	protected OrderDicService orderDicService;
	@Resource
	protected OptionService optionService;
	@Resource
	protected OrderReasonService orderReasonService;
	@Resource
	protected CommonCacheUtil commonCacheUtil;
	
	/**
	 * 获取订单来源列表
	 * 
	 * @return
	 */
	public Map<String, OrderDic> getOrderSourceMap() {
		Map<String, OrderDic> orderSourceMap = new HashMap<String, OrderDic>();
		//List<OrderDic> orderDicList= orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderSource.getCode());
		List<OrderDic> orderDicList= commonCacheUtil.getOrderSourceList();
		if(null != orderDicList && !orderDicList.isEmpty()){
			for(OrderDic orderDic : orderDicList){
			    if(null == orderDic)
			        continue;
				orderSourceMap.put(orderDic.getDicCode(), orderDic);
			}
		}
		return orderSourceMap;
	}
	/**
	 * 获取订单来源列表
	 * 
	 * @return
	 */
	public Map<String, OrderDic> getOrderTypeMap() {
		Map<String, OrderDic> orderTypeMap = new HashMap<String, OrderDic>();
		//List<OrderDic> orderDicList= orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderType.getCode());
		List<OrderDic> orderDicList= commonCacheUtil.getOrderTypeList();
		if(null != orderDicList && !orderDicList.isEmpty()){
			for(OrderDic orderDic : orderDicList){
			    if(null == orderDic)
			        continue;
				orderTypeMap.put(orderDic.getDicCode(), orderDic);
			}
		}
		return orderTypeMap;
	}
	
	/**
	 * 获得配送方式列表
	 * 
	 * @return
	 */
	public Map<String, Option> getDistributeTypeMap() {
		Map<String, Option> distributeTypeMap = new HashMap<String, Option>();
		//List<Option> distributeTypeList = optionService.findByField(Option_.optionGroupId,CommonConst.Option_OptionGroupId.getCode());
		List<Option> distributeTypeList = commonCacheUtil.getDistributeTypeList();
		if(null != distributeTypeList && !distributeTypeList.isEmpty()){
			for(Option option : distributeTypeList){
			    if(null == option)
			        continue;
				distributeTypeMap.put(option.getCode(), option);
			}
		}		
		return distributeTypeMap;
	}
	
	/**
	 * 获得会员等级
	 * 
	 * @return
	 */
	public Map<String, Option> getMemberVipLevelMap() {
		Map<String, Option> memberVipLevelMap = new HashMap<String, Option>();
		Option level_One = new Option();
		level_One.setCode("1");
		level_One.setName("微卡");
		memberVipLevelMap.put(level_One.getCode(),level_One);
		Option level_Two = new Option();
		level_Two.setCode("2");
		level_Two.setName("银卡");
		memberVipLevelMap.put(level_Two.getCode(),level_Two);
		Option level_Three = new Option();
		level_Three.setCode("3");
		level_Three.setName("金卡");
		memberVipLevelMap.put(level_Three.getCode(),level_Three);
		return memberVipLevelMap;
	}
	
	/**
	 * 获取退换货原因列表
	 * 
	 * @return
	 */
	public Map<String, OrderReason> getRefundReasonMap() {
		Map<String, OrderReason> reasonMap = new HashMap<String, OrderReason>();
		List<OrderReason> listReason = orderReasonService.getAll();
		for(OrderReason orderReason :listReason) {
			reasonMap.put(orderReason.getReasonNo(), orderReason);
		}
		return reasonMap;
	}
	
}
