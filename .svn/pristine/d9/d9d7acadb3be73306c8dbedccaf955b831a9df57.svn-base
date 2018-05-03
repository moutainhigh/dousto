package com.ibm.oms.admin.action.order.sms;

import java.util.Arrays;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.intf.intf.InventoryResendMsgOutputDTO;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.CommonConstService;


/**
 * @author
 *虚拟订单发短信
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class SmsSendVirtualAction extends AbstractOrderAction {

	private static final long serialVersionUID = 1L;
	
    @Autowired
    OrderNoService orderNoService;
    
    @Autowired
    OrderCreateService orderCreateService;
	

	public String execute() throws Exception {
		try {
			String itemNo = this.getParameter("itemNo");
			InventoryResendMsgOutputDTO dto =	orderCreateService.inventoryResendMsg(itemNo);
			if(dto != null && !CommonConstService.PROCESS_FAILED.equals(dto.getCode())){
			    setActionMessages(Arrays.asList("短信发送成功。"));
			}else if(dto == null){
			    setActionMessages(Arrays.asList("接口返回异常"));
			}else{
			    setActionMessages(Arrays.asList(dto.getMessage()));
			}
		}catch(Exception e){
			setActionMessages(Arrays.asList("短信发送失败。"));
		}
		return SUCCESS;
	}
	
	
}