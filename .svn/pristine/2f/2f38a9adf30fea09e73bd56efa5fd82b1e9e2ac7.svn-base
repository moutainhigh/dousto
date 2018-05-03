package com.ibm.oms.admin.action.order.config;

import java.util.Arrays;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.oms.service.OrderAuditConfigService;
import com.ibm.oms.service.OrderAuditMerchantConfigService;
import com.ibm.oms.service.OrderAuditRegionConfigService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * Description: 订单自动审核配置  
 * @author YanYanZhang
 * Date:   2018年2月27日 
 */
@ParentPackage("admin")
public class OrderAuditConfigAction extends BaseAdminAction<OrderAuditConfig>{
	/* 属性说明 */
	private static final long serialVersionUID = -824019131020747262L;
	
	@Autowired
	private OrderAuditConfigService orderAuditConfigService;
	
	@Autowired
	private OrderAuditMerchantConfigService orderAuditMerchantConfigService;
	
	@Autowired
	private OrderAuditRegionConfigService orderAuditRegionConfigService;
	
	private OrderAuditConfig orderAuditConfig;
	
	private List<OrderAuditMerchantConfig> merchantConfigs;
	
	private List<OrderAuditRegionConfig> auditRegionConfigs;
	
	public String view(){
		orderAuditConfig = orderAuditConfigService.getAll().get(0);
		merchantConfigs = orderAuditMerchantConfigService.getAll();
		auditRegionConfigs = orderAuditRegionConfigService.getAll();
		
		return "view";
	}
	
	public String update(){
		if (null == orderAuditConfig.getId()){
			return ERROR;
		} else {
			orderAuditConfig.setUpdatedBy(userService.getLoginUser() == null ? "" : userService.getLoginUser().getUserName());
			orderAuditConfigService.updateConfig(orderAuditConfig);
			setActionMessages(Arrays.asList("保存成功！"));
			return SUCCESS;
		}
	}
	
	/**
	 * @return the orderAuditConfig
	 */
	public OrderAuditConfig getOrderAuditConfig() {
		return orderAuditConfig;
	}

	/**
	 * @param orderAuditConfig the orderAuditConfig to set
	 */
	public void setOrderAuditConfig(OrderAuditConfig orderAuditConfig) {
		this.orderAuditConfig = orderAuditConfig;
	}

	/**
	 * @return the merchantConfigs
	 */
	public List<OrderAuditMerchantConfig> getMerchantConfigs() {
		return merchantConfigs;
	}

	/**
	 * @param merchantConfigs the merchantConfigs to set
	 */
	public void setMerchantConfigs(List<OrderAuditMerchantConfig> merchantConfigs) {
		this.merchantConfigs = merchantConfigs;
	}

	/**
	 * @return the auditRegionConfigs
	 */
	public List<OrderAuditRegionConfig> getAuditRegionConfigs() {
		return auditRegionConfigs;
	}

	/**
	 * @param auditRegionConfigs the auditRegionConfigs to set
	 */
	public void setAuditRegionConfigs(List<OrderAuditRegionConfig> auditRegionConfigs) {
		this.auditRegionConfigs = auditRegionConfigs;
	}

}
