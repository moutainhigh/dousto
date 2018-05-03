package com.ibm.oms.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderAuditConfigDao;
import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.oms.service.OrderAuditConfigService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * Description: 订单自动审核配置
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
@Service("orderAuditConfigService")
public class OrderAuditConfigServiceImpl extends BaseServiceImpl<OrderAuditConfig, Integer> implements OrderAuditConfigService{
	
	private OrderAuditConfigDao orderAuditConfigDao;
    
	@Autowired
	public void setOrderAuditConfigDao(OrderAuditConfigDao orderAuditConfigDao) {
		super.setBaseDao(orderAuditConfigDao);
		this.orderAuditConfigDao = orderAuditConfigDao;	
	}

	@Override
	public void updateConfig(OrderAuditConfig orderAuditConfig) {
		OrderAuditConfig oldOrderAuditConfig = orderAuditConfigDao.get(orderAuditConfig.getId());
		orderAuditConfig.setCreatedBy(oldOrderAuditConfig.getCreatedBy());
		orderAuditConfig.setCreatedTime(oldOrderAuditConfig.getCreatedTime());
		orderAuditConfig.setUpdatedTime(new Date());
		
		orderAuditConfigDao.update(orderAuditConfig);
	}
}
