package com.ibm.oms.admin.action.order.intf;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;


/**
 * @author lizc
 *查询接口明细action
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class OrderIntfDetailAction extends AbstractOrderAction {

	private static final long serialVersionUID = 1L;
	

	private IntfReceived intfReceived;
	
	private IntfSent intfSent;
	
	@Resource
	private IntfReceivedService intfReceivedService;
	
	@Resource
	private IntfSentService intfSentService;
	

	public String execute() throws Exception {
		
		
	    Long id=Long.valueOf(this.getParameter("id"));
		String intfType =this.getParameter("intfType");
		if("1".equals(intfType)){
			intfReceived = intfReceivedService.get(id);
			return "order_intf_rdetail";
			
		}else{
			intfSent = intfSentService.get(id);
			return "order_intf_sdetail";
		}
		
	}
	
	public List<IntfSentConst>  getSentList(){
		return IntfSentConst.getAll();
	}
	
	
	public List<IntfReceiveConst>  getReceiveList(){  
		return IntfReceiveConst.getAll();
	}

	public IntfReceived getIntfReceived() {
		return intfReceived;
	}

	public void setIntfReceived(IntfReceived intfReceived) {
		this.intfReceived = intfReceived;
	}

	public IntfSent getIntfSent() {
		return intfSent;
	}

	public void setIntfSent(IntfSent intfSent) {
		this.intfSent = intfSent;
	}
	
}