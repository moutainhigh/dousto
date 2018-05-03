package com.ibm.oms.admin.action.order.customer;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.sc.admin.action.BaseAdminAction;

/**
 * 后台Action类 - 后台管理、管理员
 */

@ParentPackage("admin")
public class AllOrderAction extends BaseAdminAction {
	
	
//	@Autowired
//	@Qualifier ( "orderMainService" )  
	OrderMainService orderMainService;
	
	OrderMain order;

	private static final long serialVersionUID = -5383463207248344967L;

	@Override
	public String execute() throws Exception {
		
		System.out.println("order main list");
		//List<OrderMain> orderMainList = orderMainService.getAll();
		
		
		//getRequest().setAttribute("orderMainList", orderMainList);
		
		pager  = orderMainService.findByPager(pager);
		return SUCCESS;
	}
	
	
	
	public String list() throws Exception {
		
		System.out.println("order main list");
		
		pager  = orderMainService.findByPager(pager);
		return SUCCESS;
	}
	
	
	// 删除
	public String delete() {
		orderMainService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}
	
	
	// 添加
		public String add() {
			return INPUT;
		}

		// 编辑
		public String edit() {
			order = orderMainService.load(id);
			return INPUT;
		}
		
		
		
		// 保存
		public String save() {
			System.out.print("id = :" + order.getId());
			orderMainService.save(order);
			//redirectionUrl = "user!list.action";
			List<OrderMain> orderMainList = orderMainService.getAll();
			getRequest().setAttribute("orderMainList", orderMainList);
			return SUCCESS;
		}

		// 更新
		public String update() throws Exception {
			System.out.print("id = :" + order.getId());
			OrderMain persistent = orderMainService.load(order.getId());
			persistent.setBillType(order.getBillType());
			orderMainService.update(persistent);
			//redirectionUrl = "user!list.action";
			
			List<OrderMain> orderMainList = orderMainService.getAll();
			getRequest().setAttribute("orderMainList", orderMainList);
			return SUCCESS;
		}


		public OrderMain getOrder() {
			return order;
		}


		public void setOrder(OrderMain order) {
			this.order = order;
		}
		
		
	
	

	
}