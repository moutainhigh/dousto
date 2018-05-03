package com.ibm.oms.admin.action.order.area;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sc.admin.action.BaseAdminAction;
import com.ibm.sup.rs.bean.TransportAreaBean;
import com.ibm.sup.rs.service.TransportAreaRsService;

/**
 * Description: 地区区域信息
 * @author YanYanZhang
 * Date:   2018年3月27日 
 */
@ParentPackage("admin")
public class AreaAction extends BaseAdminAction<TransportAreaBean>{
	@Resource
	private TransportAreaRsService transportAreaRsService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String findProvinces(){
		List<TransportAreaBean> provinces = new ArrayList<TransportAreaBean>();
		provinces = transportAreaRsService.findProvinces();
		
		String result;
		try {
			result = objectMapper.writeValueAsString(provinces);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result = BLANK;
		}
		return ajaxJson(result);
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
}
