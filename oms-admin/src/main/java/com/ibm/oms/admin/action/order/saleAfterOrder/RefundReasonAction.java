package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.sc.admin.action.BaseAdminAction;


@ParentPackage("json-default")
@Result(type = "json")
public class RefundReasonAction extends BaseAdminAction {

	@Resource
	OrderReasonService orderReasonService;
	
	private String preRefundReason;
	
	private List<OrderReason> refundReasonList = new ArrayList<OrderReason>();
	
	
	 
	public String getRefundReason(){
		 refundReasonList.clear();
		 refundReasonList.add(null);
		 if (null == preRefundReason){
			 return SUCCESS;
		 }
		 Map<OrderReason, List<OrderReason>> map = orderReasonService.getReasonMap();
		 for(Map.Entry<OrderReason, List<OrderReason>> entry: map.entrySet()){    
				 if(preRefundReason.equalsIgnoreCase(entry.getKey().getReasonNo())){
					 refundReasonList = entry.getValue();
				 }
		}   
		return SUCCESS;
	}

	public String getPreRefundReason() {
		return preRefundReason;
	}

	public void setPreRefundReason(String preRefundReason) {
		this.preRefundReason = preRefundReason;
	}

	public void setRefundReasonList(List<OrderReason> refundReasonList) {
		this.refundReasonList = refundReasonList;
	}

	public List<OrderReason> getRefundReasonList() {
		return refundReasonList;
	}
	
	
	 

	
		
	 
	 
		
}