/**
 * 
 */
package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.StatusDict_;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.business.DrawbackService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.sys.User;

/**
 * @author xiaonanxiang
 *
 */
@ParentPackage("admin")
public class DrawbackAction extends AbstractOrderAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
    
    /**
     * 用来审批和取消的子订单号 
     */
    private String saleAfterSubNo;
    
	@Resource
	protected DrawbackService drawbackService;
	@Resource
	private ReturnChangeOrderService returnChangeOrderService;
	@Resource
	private UserUtil userUtil;
    
  
    public String execute() throws Exception {
        /*
         * String columnStr = this.getParameter("column");//URL中的参数 if(StringUtils.isNotEmpty(columnStr)){ columnId =
         * Integer.parseInt(columnStr); }
         */

        //orderSearch = new OrderSearch(orderMain, orderSub, orderItem, orderPay);
        //pager = orderSearchService.findByOrderSearch(column, orderSearch, pager);
    	this.search();
        return "list";
    }



    public String search() {
        // 对下单日期做个处理
        Date orderTimeFrom = orderMain.getOrderTimeFrom();
        Date orderTimeTo = orderMain.getOrderTimeTo();
        
        Date newOrderTimeFrom = null;
        Date newOrderTimeTo = null;

        if (!(orderTimeFrom == null) && !(orderTimeTo == null)) {
            newOrderTimeFrom = getStartDate(orderTimeFrom);
            newOrderTimeTo = getEndDate(orderTimeTo);
            orderMain.setOrderTimeFrom(newOrderTimeFrom);
            orderMain.setOrderTimeTo(newOrderTimeTo);
            
        } else if (!(null == orderTimeFrom)) {
            newOrderTimeFrom = getStartDate(orderTimeFrom);
            orderMain.setOrderTimeFrom(newOrderTimeFrom);
        } else if (!(null == orderTimeTo)) {
            newOrderTimeTo = getEndDate(orderTimeTo);
            orderMain.setOrderTimeTo(newOrderTimeTo);
        }
        
        //orderSearch = new OrderSearch(orderMain, orderSub, orderItem, orderPay);
        orderSearch = new OrderSearch(orderMain, orderSub, orderItem, orderPay,transportArea,distributeAddress,selfTakePoint,includeOrNot,orderPayMode);
        pager = orderSearchService.findByOrderSearch(column, orderSearch, pager);
        return "list";
    }



    public String view(){
    	isDetail = true;
		String orderNo = this.getParameter("orderNo");
		order = orderMainService.findByOrderNo(orderNo);
		
		if( order.getOrderSubs() != null && order.getOrderSubs().size()>0){
			orderSub = order.getOrderSubs().get(0);
		}
		
		this.initOrderInvoice();
		orderCategory = order.getOrderCategory();
		return "return_payment";
	}
    
    /**
     * 更新退款单和运费补款单退款信息
     * @return
     */
    public String updateDrawback() {

        String orderNo = order.getOrderNo();
        OrderMain orderOrg = orderMainService.findByOrderNo(orderNo);
        
        // 退款金额总计
        orderOrg.setTotalPayAmount(order.getTotalPayAmount());
        orderMainService.update(orderOrg);
        
        
        // 往orderPay里面添加页面内容

        // 要删除的orderPay
        String[] deleteOrderPayIds = this
                .getParameterValues("deleteOrderPayIds");
        if (deleteOrderPayIds != null && deleteOrderPayIds.length > 0) {
            for (String id : deleteOrderPayIds) {
                if (StringUtils.isNotEmpty(id)) {
                    orderPayService.delete(Long.valueOf(id));
                }
            }
        }

        // 先刷选一遍orderPay里面添加页面内容
        Iterator<OrderPay> sListIterator = orderPayLists.iterator();
        while (sListIterator.hasNext()) {
            OrderPay orderPay = sListIterator.next();
            if (orderPay == null
                    || orderPay.getPayAmount() == null
                    || orderPay.getPayAmount().compareTo(new BigDecimal(0)) == 0
                    || orderPay.getPayCode() == null
                    || StringUtils.isEmpty(orderPay.getPayCode())) {
                sListIterator.remove();
            }
        }

        for (OrderPay orderPay : orderPayLists) {
            if (null != orderPay.getId()) {
                OrderPay orderPayOrg = orderPayService.get(orderPay.getId());
                orderPayOrg.setPayAmount(orderPay.getPayAmount());
                orderPayOrg.setPayCode(orderPay.getPayCode());
                
                // 设置payName
                if(null != orderPay.getPayCode())
                {
                    PaymentMethod paymentMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
                    if(null != paymentMethod)
                    {
                        orderPayOrg.setPayName(paymentMethod.getName());
                    }
                }
                orderPayService.update(orderPayOrg);
            } else {
                orderPay.setIdOrder(orderOrg.getId());
                orderPay.setOrderNo(orderOrg.getOrderNo());
                orderPay.setOrderMain(orderOrg);
                
                // 设置payName
                if(null != orderPay.getPayCode())
                {
                    PaymentMethod paymentMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
                    if(null != paymentMethod)
                    {
                        orderPay.setPayName(paymentMethod.getName());
                    }
                }
                orderPayService.save(orderPay);
            }
        }

        // redirectionUrl = "refund.action?column=-1";
        redirectionUrl = "refund!view.action?orderNo=" + orderNo;
        return SUCCESS;
    }
    
    
    
    public String returnPayment(){
    	
    	try{
	    	User user = userService.getLoginUser();
	
//	    	drawbackService.returnPayment(order.getOrderNo(),user.getUserName());
	    	
	    	String operator = userUtil.getLoginUserRealName();
	    	ResultDTO result = this.saleAfterOrderTransService.updateApproveRefundOrder(order.getOrderNo()+"01", operator);
	    	if(result.getResult()==CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
                setActionMessages(Arrays.asList("退款成功。"));
            }else{
                setActionMessages(Arrays.asList("退款失败："+result.getResultMessage()));
            }
	    	
	    	redirectionUrl = "drawback.action?column=-1";
    	}catch(Exception e){
    		setActionMessages(Arrays.asList("退款失败。" +e.getMessage()));
    		//
    	}
    	
    	return SUCCESS;
    }
    
    
	/**
	 * 审核
	 * @return
	 */
	public String orderAudit() {
	    //String[] orderSubNos = this.getParameterValues("ids");
	    String[] orderSubNos = this.getParameterValues("ids[]");
	    String operator = userUtil.getLoginUserRealName();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < orderSubNos.length; i++) {
	        sb.append("订单号："+orderSubNos[i]);
	        try {
	            ResultDTO result = this.saleAfterOrderTransService.updateApproveRefundOrder(orderSubNos[i], operator);
	            if(result.getResult()==CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
                    sb.append("订单审核成功!");
                }else{
                    sb.append("订单审核失败!"+result.getResultMessage());
                }
	        } catch (Exception e) {
	            sb.append("订单审核失败!");
	            logger.error("{}", e.getMessage());
	            //this.setActionMessages(Arrays.asList("系统异常：" + e.getMessage()));
	            //return ERROR;
	        }
	    }
	    return ajaxJsonSuccessMessage(sb.toString());
	}
    
    
    private void initOrderInvoice(){
		if(order.getOrderInvoices()!=null && order.getOrderInvoices().size()>0){
			List<OrderInvoice> tmpList = order.getOrderInvoices();
			for (OrderInvoice invoice : tmpList) {
				if(invoice.getIsDeleted()!=1){
					orderInvoice = invoice;
					break;
				}
				
			}
			
		}
	}

    
    
  
    /**
     * 逆向订单处理状态
     */
	public List<StatusDict> getStatusTotalList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_BackOrder_StatusTotal.getCode()));

		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
		
		list.add(null);
        list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_BackOrder_StatusTotal.getCode()));
		return list;
	}

	/**
	 * 逆向订单退款状态
	 */
	public List<StatusDict> getStatusPayList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_BackOrder_StatusReturnMoney.getCode()));

		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
		
		list.add(null);
        list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_BackOrder_StatusReturnMoney.getCode()));
		return list;
	}
//
    public List<StatusDict> getStatusConfirmList() {
        List<StatusDict> list = new ArrayList<StatusDict>();
        list.add(null);
        list.addAll(statusDictService.findByField(StatusDict_.statusCode,
                OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Wait.getCode()));
        list.addAll(statusDictService.findByField(StatusDict_.statusCode,
                OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass.getCode()));
        list.addAll(statusDictService.findByField(StatusDict_.statusCode,
                OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Cancel.getCode()));

        // 根据displayName排序StatusDict对象List集合
        // list = commonUtilService.sortStatusDictListbyDisplayName(list);
        return list;
    }

	//
	public List<StatusDict> getLogisticsStatusList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));

		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
		
		list.add(null);
        list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));
		return list;
	}
//
	public List<StatusDict> getStatusFinishList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(new StatusDict("0", "未完成"));
		list.add(new StatusDict("1", "已完成"));

		// 根据displayName排序StatusDict对象List集合
		list = commonUtilService.sortStatusDictListbyDisplayName(list);
		return list;
	}

	public Boolean getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(Boolean isDetail) {
		this.isDetail = isDetail;
	}

	public String getSaleAfterSubNo() {
		return saleAfterSubNo;
	}

	public void setSaleAfterSubNo(String saleAfterSubNo) {
		this.saleAfterSubNo = saleAfterSubNo;
	}


}
