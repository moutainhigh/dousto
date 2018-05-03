package com.ibm.oms.admin.action.order.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.oms.service.OrderAuditRegionConfigService;
import com.ibm.sc.admin.action.BaseAdminAction;
import com.ibm.sup.rs.bean.TransportAreaBean;
import com.ibm.sup.rs.service.TransportAreaRsService;

/**
 * Description: 自动审核地区配置  
 * @author YanYanZhang
 * Date:   2018年3月5日 
 */
@ParentPackage("admin")
public class OrderAuditRegionConfigAction extends BaseAdminAction<OrderAuditRegionConfig>{
	/* 属性说明 */
	private static final long serialVersionUID = -2319008247159910426L;
	
	@Autowired
	private OrderAuditRegionConfigService orderAuditRegionConfigService;
	
	@Resource
	private TransportAreaRsService transportAreaRsService;
	
	private List<TransportAreaBean> provinces;
	
	private OrderAuditRegionConfig regionConfig;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String delete() {
		String[] ids = this.getParameterValues("regionIds");
		String result;
		if(null != ids) {
			for(String id : ids) {
				orderAuditRegionConfigService.delete(Integer.valueOf(id));
			}
			result = "删除成功";
		} else {
			result = "数据无效";
		}
		return ajaxJsonSuccessMessage(result);
	}
	
	public String edit(){
		provinces = transportAreaRsService.findProvinces();
		return "edit";
	}
	
	public String createConfig(){
		if(StringUtils.isNotBlank(regionConfig.getCode())) {
			OrderAuditRegionConfig existConfig = orderAuditRegionConfigService.getByField("code", regionConfig.getCode());
			if (null != existConfig) {
				setActionErrors(Arrays.asList("地区已存在"));
				return ERROR;
			} else {
				String operator = userService.getLoginUser().getUserName();
				Date now = new Date();
				regionConfig.setCreatedBy(operator);
				regionConfig.setUpdatedBy(operator);
				regionConfig.setCreatedTime(now);
				regionConfig.setUpdatedTime(now);
				
				orderAuditRegionConfigService.save(regionConfig);
			}
		} else {
			setActionErrors(Arrays.asList("区域不可为空"));
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String findByParentId(){
		String parentId = this.getParameter("parentId");
		
		List<TransportAreaBean> areas = new ArrayList<TransportAreaBean>();
		areas = transportAreaRsService.findByParentId(Long.valueOf(parentId));
		
		String result;
		try {
			result = objectMapper.writeValueAsString(areas);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result = BLANK;
		}
		return ajaxJson(result);
	}

	/**
	 * @return the provinces
	 */
	public List<TransportAreaBean> getProvinces() {
		return provinces;
	}

	/**
	 * @param provinces the provinces to set
	 */
	public void setProvinces(List<TransportAreaBean> provinces) {
		this.provinces = provinces;
	}

	/**
	 * @return the regionConfig
	 */
	public OrderAuditRegionConfig getRegionConfig() {
		return regionConfig;
	}

	/**
	 * @param regionConfig the regionConfig to set
	 */
	public void setRegionConfig(OrderAuditRegionConfig regionConfig) {
		this.regionConfig = regionConfig;
	}
}
