package com.ibm.oms.service;

import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.sc.service.BaseService;

/**
 * Description: 订单审核配置类
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
public interface OrderAuditConfigService extends BaseService<OrderAuditConfig, Integer>{
	void updateConfig(OrderAuditConfig orderAuditConfig);
}
