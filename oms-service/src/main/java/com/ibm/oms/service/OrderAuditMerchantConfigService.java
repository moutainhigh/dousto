package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.sc.service.BaseService;

/**
 * Description: 订单审核店铺配置
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
public interface OrderAuditMerchantConfigService extends BaseService<OrderAuditMerchantConfig, Integer>{
	/**
	 * 批量修改店铺自动审核配置
	 * Description:
	 * @param merchantConfigs
	 * @param operator
	 */
	void batchUpdateMerchantConfig(List<OrderAuditMerchantConfig> merchantConfigs, String operator);
}
