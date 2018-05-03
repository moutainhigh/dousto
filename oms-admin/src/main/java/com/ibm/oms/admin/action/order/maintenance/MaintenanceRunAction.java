package com.ibm.oms.admin.action.order.maintenance;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * 后台运维Action类
 */

@ParentPackage("admin")
public class MaintenanceRunAction extends BaseAdminAction {
	
	
	@Resource
	private OrderCreateService orderCreateService;
	@Resource
	private OrdiErrOptLogService ordiErrOptLogService;
	
	private OrdiErrOptLog ordiErrOptLog;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846588470340142948L;
	
	public String execute() {
		String msg = orderCreateService.dealErr(ordiErrOptLog.getId());
		setActionMessages(Arrays.asList(msg));
		
		redirectionUrl = "maintenance.action";
		
		return SUCCESS;
	}

	/**
	 * 批量执行异常
	 * @return
	 */
    public String batchRun() {
        String[] ordiErrOptLogIds = this.getParameterValues("ids[]");
        String[] orderSubNos = this.getParameterValues("orderSubNos[]");
        String msg = "";
        for (int i = 0; i < ordiErrOptLogIds.length; i++) {
            try {
                if (null == orderSubNos[i]) {
                    msg += "";
                } else {
                    msg += orderSubNos[i] + ":";
                }
                msg += orderCreateService.dealErr(Long.valueOf(ordiErrOptLogIds[i])) + "\n";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return this.ajaxJsonSuccessMessage(msg);
    }
	
	public OrdiErrOptLog getOrdiErrOptLog() {
		return ordiErrOptLog;
	}

	public void setOrdiErrOptLog(OrdiErrOptLog ordiErrOptLog) {
		this.ordiErrOptLog = ordiErrOptLog;
	}

	
}
