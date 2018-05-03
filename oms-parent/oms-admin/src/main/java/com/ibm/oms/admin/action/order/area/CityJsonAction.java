package com.ibm.oms.admin.action.order.area;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.admin.action.BaseAdminAction;

@ParentPackage("json-default")
@Result(type = "json")
public class CityJsonAction extends BaseAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7183059768648721889L;

	@Resource
	private TransportAreaCacheService transportAreaCacheService;
	@Resource
	private CommonUtilService commonUtilService;

	private String stateAbbr;

	private String selectName;

	private List<AreaBean> list = new ArrayList<AreaBean>();

	public String execute() {
		
		list=transportAreaCacheService.findOtherList(stateAbbr,selectName);
		
		// 根据name排序AreaBean对象List集合
		list = commonUtilService.sortAreaBeanListbyDisplayName(list);

		return SUCCESS;
	}

	public String getStateAbbr() {
		return stateAbbr;
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public List<AreaBean> getList() {
		return list;
	}

	public void setList(List<AreaBean> list) {
		this.list = list;
	}
}
