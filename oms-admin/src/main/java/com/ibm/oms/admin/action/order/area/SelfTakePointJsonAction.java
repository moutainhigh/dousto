package com.ibm.oms.admin.action.order.area;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.SelfTakePointTmp;
import com.ibm.oms.service.SelfTakePointTmpService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.admin.action.BaseAdminAction;

@ParentPackage("json-default")
@Result(type = "json")
public class SelfTakePointJsonAction extends BaseAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private SelfTakePointTmpService selfTakePointTmpService;
	@Resource
	private CommonUtilService commonUtilService;
	
	private List<SelfTakePointTmp> selfTakePointTmpList;
	
	private String merchantCode;
	
	private String selfTakePointId;
	
	private String address;

	public String execute() {
		
		selfTakePointTmpList = selfTakePointTmpService.findSelfTakePointTmpList(merchantCode, selfTakePointId);
		
		selfTakePointTmpList = commonUtilService.sortSelfTakePointTmpListbyDisplayName(selfTakePointTmpList);
		return SUCCESS;
	}
	
	public String address() {
		selfTakePointId = this.getParameter("ids");
	    address = selfTakePointTmpService.findPointDetailAddress(selfTakePointId);
		return ajaxJsonSuccessMessage(address);
	}

	public List<SelfTakePointTmp> getSelfTakePointTmpList() {
		return selfTakePointTmpList;
	}
	public void setSelfTakePointTmpList(List<SelfTakePointTmp> selfTakePointTmpList) {
		this.selfTakePointTmpList = selfTakePointTmpList;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getSelfTakePointId() {
		return selfTakePointId;
	}

	public void setSelfTakePointId(String selfTakePointId) {
		this.selfTakePointId = selfTakePointId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
