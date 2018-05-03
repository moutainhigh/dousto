package com.ibm.oms.admin.action.order.saleAfterOrder;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.sc.admin.action.BaseAdminAction;


@ParentPackage("json-default")
@Result(type = "json")
public class RefundDescAction extends BaseAdminAction {
	
	private String desc;
	
	private String code;

	public String getDescription(){
		desc = OrderStatus.getDesc(code);
		return SUCCESS;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
		
}