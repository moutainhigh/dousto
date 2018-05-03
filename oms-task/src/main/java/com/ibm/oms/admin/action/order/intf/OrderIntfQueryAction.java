package com.ibm.oms.admin.action.order.intf;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;


/**
 * @author lizc
 *接口查询action
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class OrderIntfQueryAction extends AbstractOrderAction {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> map;
	
	@Resource
	private IntfReceivedService intfReceivedService;
	
	@Resource
	private IntfSentService intfSentService;
	

	public String execute() throws Exception {
	
		String intfType = map.get("intfType");
		map.remove("intfType");
		if("1".equals(intfType)){
			pager = intfReceivedService.getPagerByMap(map, pager);
		}else{
			pager = intfSentService.getPagerByMap(map, pager);
		}
		
		map.put("intfType", intfType);
		return "order_intf_list";
	}
	
	public List<IntfSentConst>  getSentList(){
		return IntfSentConst.getAll();
	}
	
	
	public List<IntfReceiveConst>  getReceiveList(){  
		return IntfReceiveConst.getAll();
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	
}