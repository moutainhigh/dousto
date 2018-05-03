package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.StatusDict_;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.sc.model.sys.User;

/**
 * 逆向单列表操作action
 * @author Administrator
 *
 */
@ParentPackage("admin")
public class RefundAction extends AbstractOrderAction {

	private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 1L;

    
    
  
    public String execute() throws Exception {
        /*
         * String columnStr = this.getParameter("column");//URL中的参数 if(StringUtils.isNotEmpty(columnStr)){ columnId =
         * Integer.parseInt(columnStr); }
         */

        /*orderSearch = new OrderSearch(orderMain, orderSub, orderItem, orderPay);
        if(column==null){
        	column = -1;
        }
        pager = orderSearchService.findByOrderSearch(column, orderSearch, pager);*/
    	
    	this.search();
        return "list";
    }



    public String search() {
    	 if(column==null){
         	column = -1;
         }
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
		
		// 设置剩余积分
		setOrderMainIntegral(order.getMemberNo());
		
		// orderSub
		if( order.getOrderSubs() != null && order.getOrderSubs().size()>0){
			orderSub = order.getOrderSubs().get(0);
			
			// 设置省、城市、县区组合的地址
			setOrderSubCombinedAddress(orderSub.getAddressCode());
			
			/*// 设置自提点信息
			String selfFetchAddress = orderSub.getSelfFetchAddress();
			commonUtilService.setSelfTakePointInfo(selfFetchAddress, selfTakePoint);*/
		}
		
		// 关联的原销售单号
	    String orderRelatedOrigin = order.getOrderRelatedOrigin();
	    // 获取原支付方式
	    getOriginalOrderPays(orderRelatedOrigin);
		    
		
		/*if(order.getOrderRetChgItems() != null && order.getOrderRetChgItems().size() > 0)
		{
			List<OrderRetChgItem> orderRetChgItems = order.getOrderRetChgItems();
			for(OrderRetChgItem orderRetChgItem:orderRetChgItems)
			{
				String orderItemNo = orderRetChgItem.getOrderItemNo();
				// 获取色码款属性
				List<ProductProperty> productPropertyList = productPropertyDao
						.findByField(ProductProperty_.orderItemNo,
								orderItemNo);
				orderRetChgItem.setProductPropertys(productPropertyList);
			}
			 
		}*/
		orderCategory = order.getOrderCategory();
		//this.initOrderInvoice();
		
		return "detail";
	}



    
    
	private void initOrderInvoice() {
		if (order.getOrderInvoices() != null
				&& order.getOrderInvoices().size() > 0) {
			List<OrderInvoice> tmpList = order.getOrderInvoices();
			for (OrderInvoice invoice : tmpList) {
				if (invoice.getIsDeleted() != 1) {
					orderInvoice = invoice;
					break;
				}

			}

		}
	}

    
    /**
	 * 审核意向订单
	 * @return
	 */
	public String orderAudit(){
		String  saleAfterSubNos[]= this.getParameterValues("ids[]");
		StringBuffer sb = new StringBuffer();
		for (String saleAfterSubNo : saleAfterSubNos) {
			sb.append("订单号："+saleAfterSubNo);
			try{
				ResultDTO result =	saleAfterOrderTransService.updateApproveSaleAfterOrder(saleAfterSubNo);
				
				if(result.getResult()==CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
					sb.append("订单审核成功!");
				}else{
					sb.append("订单审核失败!"+result.getResultMessage());
				}
				
			}catch(Exception e){
				sb.append("订单审核失败!");
				logger.error("{}", e.getMessage());
				
			}
		}
		
		//setActionMessages(Arrays.asList("审核成功！"));
		//redirectionUrl="refund.action?column=-1&pager.pageNumber=" + pager.getPageNumber()+"&pager.pageSize=" + pager.getPageSize();
		return ajaxJsonSuccessMessage(sb.toString());
	}
	
	/**
	 * 取消意向订单
	 * @return
	 */
	public String orderCancel(){
		String  saleAfterSubNos[]= this.getParameterValues("ids[]") ;
		StringBuffer sb = new StringBuffer();
		
		for (String saleAfterSubNo : saleAfterSubNos) {
			try{
				sb.append("订单号："+saleAfterSubNo);
				ResultDTO result =	saleAfterOrderTransService.updateCancelSaleAfterOrder(saleAfterSubNo);
				if(result.getResult()==CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
					sb.append("订单取消成功!");
				}else{
					sb.append("订单取消失败!"+result.getResultMessage());
				}
			}catch(Exception e){
				sb.append("订单取消失败!");
				logger.error("{}", e.getMessage());
			}
		}
		return ajaxJsonSuccessMessage(sb.toString());
	}
    
	/**
	 * 门店已收获(门店代退)
	 * @return
	 */
	public String orderStoreReceive(){
	    String  saleAfterSubNos[]= this.getParameterValues("ids[]") ;
        StringBuffer sb = new StringBuffer();
        User user = this.userService.getLoginUser();
        if(user==null){
            return ajaxJsonSuccessMessage("请先登录！");
        }
        if(null == saleAfterSubNos || saleAfterSubNos.length <= 0)
        {
            this.setActionMessages(Arrays.asList("传到后台的子订单号为空！"));
            return ERROR;
        }
        for (String saleAfterSubNo : saleAfterSubNos) {
            try{
                String orderNo = this.orderNoService.getOrderNoBySubNo(saleAfterSubNo);
                OrderSub sub = orderSubService.getByField(OrderSub_.orderSubNo, saleAfterSubNo);
                OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                if(null!=sub && null!=orderMain){
                    //1、验证是门店代退
                    /*if(!OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode().equals(sub.getDistributeType())){
                       continue; 
                    }*/
                    //2、判断是门店退款还是网天退款(流程不一样)
                    OrderStatusAction action = null;
                    Long refundType = orderMain.getIfNeedRefund();
                    if(null!=refundType && refundType.longValue()==CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong()){
                        action = OrderStatusAction.S023370;//门店退款
                    }else if(null!=refundType && refundType.longValue()==CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong()){
//                        action = OrderStatusAction.S023340;//网天退款
                        action = OrderStatusAction.S023370;//网天退款【门店收获后财务直接退款】
                    }else if(OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderMain.getOrderCategory()) && !commonUtilService.checkIfNeedRefund(refundType)){
                        action = OrderStatusAction.S023340;//拒收、无需退款
                    }
                    if(null!=action){
                        boolean flag = orderStatusService.saveProcess(saleAfterSubNo, action, null, new Date(), null);    
                    }
                }
            }catch(Exception e){
                sb.append("订单取消失败!");
                logger.error("{}", e.getMessage());
            }
        }
        return ajaxJsonSuccessMessage(sb.toString());
	}
	
	/**
	 * 门店退款(门店代退)
	 * @return
	 */
	public String orderStoreRefund(){
	    String  saleAfterSubNos[]= this.getParameterValues("ids[]") ;
        StringBuffer sb = new StringBuffer();
        User user = this.userService.getLoginUser();
        if(user==null){
            return ajaxJsonSuccessMessage("请先登录！");
        }
        for (String saleAfterSubNo : saleAfterSubNos) {
            try{
                String orderNo = this.orderNoService.getOrderNoBySubNo(saleAfterSubNo);
                OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                if(null!=orderMain){
                    //判断是否为门店退款
                    Long refundType = orderMain.getIfNeedRefund();
                    if(null!=refundType && refundType.longValue()==CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong()){
                        boolean flag = orderStatusService.saveProcess(saleAfterSubNo, OrderStatusAction.S027075, null, new Date(), null);
                        logger.info("退货单,"+saleAfterSubNo+"门店退款结果:"+flag);
                    }
                }
            }catch(Exception e){
                sb.append("订单取消失败!");
                logger.error("{}", e.getMessage());
            }
        }
	    return ajaxJsonSuccessMessage(sb.toString());
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
        // list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_BackOrder_StatusReturnMoney.getCode()));

        // 根据displayName排序StatusDict对象List集合
        // list = commonUtilService.sortStatusDictListbyDisplayName(list);

        list.add(null);
        list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_BackOrder_StatusReturnMoney
                .getCode()));
        return list;
    }

    /**
     * 审核状态
     */
	public List<StatusDict> getStatusConfirmList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
                OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Wait
                        .getCode()));
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
                OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass
                        .getCode()));
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
				OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Cancel
						.getCode()));

		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
		return list;
	}

	/**
	 * 物流状态
	 */
    public List<StatusDict> getLogisticsStatusList() {
        List<StatusDict> list = new ArrayList<StatusDict>();
        // list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));

        // 根据displayName排序StatusDict对象List集合
        // list = commonUtilService.sortStatusDictListbyDisplayName(list);
        list.add(null);
        list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics
                .getCode()));

        return list;
    }
//
    public List<StatusDict> getStatusFinishList() {
        List<StatusDict> list = new ArrayList<StatusDict>();
        list.add(null);
        list.add(new StatusDict("0", "未完成"));
        list.add(new StatusDict("1", "已完成"));
        return list;
    }

}