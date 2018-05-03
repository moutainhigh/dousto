package com.ibm.oms.admin.action.order.area;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.sc.admin.action.BaseAdminAction;

@ParentPackage("json-default")
@Result(type = "json")
public class StreetJsonAction extends BaseAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String countyId;

	private String selectName;
	
	@Resource
	private TransportAreaCacheService transportAreaCacheService;


	private List<AreaBean> list = new ArrayList<AreaBean>();

	public String execute() {
		
		list = transportAreaCacheService.findOtherList(countyId, selectName);
		
		return SUCCESS;

	}

	public List<AreaBean> getList() {
		return list;
	}

	public void setList(List<AreaBean> list) {
		this.list = list;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

}
