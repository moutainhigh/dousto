package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderAuditRegionConfigDao;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.oms.service.OrderAuditRegionConfigService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * Description: 审核配置自动审核配置
 * @author YanYanZhang
 * Date:   2018年3月5日 
 */
@Service("orderAuditRegionConfigService")
public class OrderAuditRegionConfigServiceImpl extends BaseServiceImpl<OrderAuditRegionConfig, Integer> implements OrderAuditRegionConfigService{
	private OrderAuditRegionConfigDao orderAuditRegionConfigDao;

	@Autowired
	public void setOrderAuditRegionConfigDao(OrderAuditRegionConfigDao orderAuditRegionConfigDao) {
		this.orderAuditRegionConfigDao = orderAuditRegionConfigDao;
		super.setBaseDao(orderAuditRegionConfigDao);
	}
	
}
