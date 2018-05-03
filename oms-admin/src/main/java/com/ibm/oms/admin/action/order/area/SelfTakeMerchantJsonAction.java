package com.ibm.oms.admin.action.order.area;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.SelfTakeMerchantTmp;
import com.ibm.oms.service.SelfTakeMerchantTmpService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * 查询商户
 * 
 * @author xiaonanxiang
 * 
 */
@ParentPackage("json-default")
@Result(type = "json")
public class SelfTakeMerchantJsonAction extends BaseAdminAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Resource
	private SelfTakeMerchantTmpService selfTakeMerchantTmpService;
	@Resource
	private CommonUtilService commonUtilService;
	
	
	private List<SelfTakeMerchantTmp> selfTakeMerchantTmpList = new ArrayList<SelfTakeMerchantTmp>();
	
	private String code;
	
	public String execute() {
		
		selfTakeMerchantTmpList = selfTakeMerchantTmpService.findTakeMerchantTmpList(code);
		
		// 根据name排序SelfTakeMerchantTmp对象List集合
		selfTakeMerchantTmpList = commonUtilService.sortSelfTakeMerchantTmpListbyDisplayName(selfTakeMerchantTmpList);
		
		return SUCCESS;
	}


	

	public List<SelfTakeMerchantTmp> getSelfTakeMerchantTmpList() {
		return selfTakeMerchantTmpList;
	}

	public void setSelfTakeMerchantTmpList(
			List<SelfTakeMerchantTmp> selfTakeMerchantTmpList) {
		this.selfTakeMerchantTmpList = selfTakeMerchantTmpList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
