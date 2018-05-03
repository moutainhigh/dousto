package com.ibm.oms.admin.action.order.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.oms.service.OrderAuditMerchantConfigService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年2月27日 
 */
@ParentPackage("admin")
public class OrderAuditMerchantConfigAction extends BaseAdminAction<OrderAuditConfig>{
	
	/* 属性说明 */
	private static final long serialVersionUID = 5783136152377318386L;

	@Autowired
	private OrderAuditMerchantConfigService orderAuditMerchantConfigService;
	
	private OrderAuditMerchantConfig orderAuditMerchantConfig;
	
	private List<OrderAuditMerchantConfig> merchantConfigs;
	
	public String batchUpdateMerchantConfig(){
		if (null == merchantConfigs) {
			return ERROR;
		} else {
			orderAuditMerchantConfigService.batchUpdateMerchantConfig(merchantConfigs, userService.getLoginUser().getUserName());
			return SUCCESS;
		}
	}
	
	public String edit(){
		return "edit";
	}
	
	public String delete() {
		String[] ids = this.getParameterValues("merchantIds[]");
		String result;
		if(null != ids) {
			for(String id : ids) {
				orderAuditMerchantConfigService.delete(Integer.valueOf(id));
			}
			result = "删除成功";
		} else {
			result = "数据无效";
		}
		return ajaxJsonSuccessMessage(result);
	}
	
	public String create(){
		if (null == orderAuditMerchantConfig || StringUtils.isBlank(orderAuditMerchantConfig.getMerchantCode()) || StringUtils.isBlank(orderAuditMerchantConfig.getMerchantName())) {
			setActionErrors(Arrays.asList("缺少必填项"));
			return ERROR;
		} else {
			if(orderAuditMerchantConfigService.isExist("merchantCode", orderAuditMerchantConfig.getMerchantCode())){
				setActionErrors(Arrays.asList("店铺已存在"));
				return ERROR;
			}
			
			String userName = userService.getLoginUser().getUserName();
			Date now = new Date();
			
			orderAuditMerchantConfig.setCreatedBy(userName);
			orderAuditMerchantConfig.setUpdatedBy(userName);
			orderAuditMerchantConfig.setCreatedTime(now);
			orderAuditMerchantConfig.setUpdatedTime(now);
			
			orderAuditMerchantConfigService.save(orderAuditMerchantConfig);
			
			return SUCCESS;
		}
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
	 * @return the orderAuditMerchantConfig
	 */
	public OrderAuditMerchantConfig getOrderAuditMerchantConfig() {
		return orderAuditMerchantConfig;
	}

	/**
	 * @param orderAuditMerchantConfig the orderAuditMerchantConfig to set
	 */
	public void setOrderAuditMerchantConfig(OrderAuditMerchantConfig orderAuditMerchantConfig) {
		this.orderAuditMerchantConfig = orderAuditMerchantConfig;
	}

}
