package com.ibm.oms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderAuditMerchantConfigDao;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.oms.service.OrderAuditMerchantConfigService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * Description: 订单自动审核配置
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
@Service("orderAuditMerchantConfigService")
public class OrderAuditMerchantConfigServiceImpl extends BaseServiceImpl<OrderAuditMerchantConfig, Integer> implements OrderAuditMerchantConfigService{
	private OrderAuditMerchantConfigDao orderAuditMerchantConfigDao;

	@Autowired
	public void setOrderAuditMerchantConfigDao(OrderAuditMerchantConfigDao orderAuditMerchantConfigDao) {
		super.setBaseDao(orderAuditMerchantConfigDao);
		this.orderAuditMerchantConfigDao = orderAuditMerchantConfigDao;
	}

	@Override
	public void batchUpdateMerchantConfig(List<OrderAuditMerchantConfig> merchantConfigs, String operator) {
		Date now = new Date();
		for(OrderAuditMerchantConfig merchantConfig : merchantConfigs) {
			OrderAuditMerchantConfig oldMerchantConfig = orderAuditMerchantConfigDao.get(merchantConfig.getId());
			merchantConfig.setCreatedBy(oldMerchantConfig.getCreatedBy());
			merchantConfig.setCreatedTime(oldMerchantConfig.getCreatedTime());
			merchantConfig.setUpdatedTime(now);
			merchantConfig.setUpdatedBy(operator);
		}
		
		orderAuditMerchantConfigDao.update(merchantConfigs);
	}
}
