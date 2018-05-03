package com.ibm.oms.admin.action.order.maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.oms.admin.action.order.ExportExcelAction;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.service.OrdiErrOptLogService;

/**
 * 后台运维Action类
 */

@ParentPackage("admin")
public class MaintenanceExcelAction extends ExportExcelAction {
	
	@Resource
	private OrdiErrOptLogService ordiErrOptLogService;
	
	private Map<String, String> map;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846588470340142948L;

	 public String getFileName() {    
		 return "maintenance.xls";
	 }

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	public String[] getTitiles(){
		return new String[] {"id", "OMS行订单号", "OMS订单号", "来源系统","外部订单号",
				"外部订单行号","实体商品订单行","订单行状态","异常类型","异常编码 ",
				"异常描述 "};
	}
	
	public List<String[]> getDataList(){
		List<String[]> dataList = new ArrayList<String[]>();
		List<OrdiErrOptLog> errLogList = (List<OrdiErrOptLog>)ordiErrOptLogService.findByFields(map);
		
		for (OrdiErrOptLog c : errLogList) {

			String[] rec = new String[11];
			rec[0] = c.getId()+"";
			rec[1] = "\t"+c.getOrderItemNo();
			rec[2] = "\t"+c.getOrderNo();
			rec[3] = c.getOrderSource();
			rec[4] = "\t"+c.getAliasOrderNo();
			rec[5] = "\t"+c.getAliasOrderItemNo();
			rec[6]= "\t"+c.getOrderItemClass();				
			rec[7] = c.getOrderStatus();
			rec[8] = c.getErrorType();
			rec[9] = c.getErrorCode();
			rec[10] = c.getErrorDesc();
			
			dataList.add(rec);
		}
		
		return dataList;
	}
}
