package com.ibm.oms.admin.action.order.intf;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;


/**
 * @author 
 *进入接口查询页面action
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class OrderIntfAction extends AbstractOrderAction {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
	
		return "list";
	}
	
	public List<IntfSentConst>  getSentList(){
		return IntfSentConst.getAll();
	}
	
	
	public List<IntfReceiveConst>  getReceiveList(){  
		return IntfReceiveConst.getAll();
	}
	
}