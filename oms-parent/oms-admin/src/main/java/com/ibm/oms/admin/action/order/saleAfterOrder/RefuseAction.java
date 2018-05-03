package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.sc.model.payment.PaymentMethod;

@ParentPackage("admin")
public class RefuseAction extends AbstractOrderAction {

    private static final long serialVersionUID = 1L;
    
    private boolean hasOrderPay = false;
    
    
    public String view() {
        isModify = true;
        String orderNo = this.getParameter("orderNo");
        order = orderMainService.findByOrderNo(orderNo);

        // 设置剩余积分
        setOrderMainIntegral(order.getMemberNo());

        if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
            orderSub = order.getOrderSubs().get(0);
            if (orderSub != null) {
                String partnerId = selfTakePointTmpService.findPointDeliverPartnerId(orderSub.getSelfFetchAddress());
                if (null != orderSub.getSelfFetchAddress()) {
                    selfTakePoint.setId(Long.valueOf(orderSub.getSelfFetchAddress()));
                    selfTakePoint.setAddress(selfTakePointTmpService.findPointDetailAddress(orderSub
                            .getSelfFetchAddress()));
                }
                if (null != partnerId && !"".equals(partnerId)) {
                    selfTakePoint.setPointDeliverPartnerId(Long.valueOf(partnerId));
                }

                // 订单已审核时，界面客户信息中的配送地区取省、城市、县区组合的地址（combinedAddress）
                if (OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass.getCode()
                        .equals(order.getStatusConfirm())) {
                    // 设置省、城市、县区组合的地址
                    setOrderSubCombinedAddress(orderSub.getAddressCode());
                }
                // 订单未审核时，取下拉列表的值
                else {
                    // 设置省、城市、县区下拉列表的值
                    String addressCode = orderSub.getAddressCode();
                    this.transportAreaCacheService.setStateCityCounty(addressCode, distributeAddress);
                }
            }
        }

        // if("0420".equalsIgnoreCase(order.getStatusPay())){
        hasOrderPay = true;
        orderPayLists.clear();
        orderPayLists.addAll(order.getOrderPays());
        // }
        // isEdit = "1";
        orderCategory = order.getOrderCategory();

        // 关联的原销售单号
        String orderRelatedOrigin = order.getOrderRelatedOrigin();
        // 获取原支付方式
        getOriginalOrderPays(orderRelatedOrigin);

        return "order_view";
    }
  

    public String refuse() {
        isCreated = true;
        String orderNo = this.getParameter("orderNo");
        order = orderMainService.findByOrderNo(orderNo);
        //orderMainService.detach(order);

        // 设置剩余积分
        setOrderMainIntegral(order.getMemberNo());

        if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
            orderSub = order.getOrderSubs().get(0);

            // 设置省、城市、县区组合的地址
            setOrderSubCombinedAddress(orderSub.getAddressCode());
            // 设置省、城市、县区下拉列表的值
            String addressCode = orderSub.getAddressCode();
            this.transportAreaCacheService.setStateCityCounty(addressCode, distributeAddress);
        }

        // 如果支付完成
        if (OrderStatus.Order_PayStatus_Success.getCode().equalsIgnoreCase(order.getStatusPay())) {
            hasOrderPay = true;
            orderPayLists.clear();
            orderPayLists.addAll(order.getOrderPays());
        }

        // 查看是否已创建拒收订单
        boolean hadCreateRefuse = saleAfterOrderTransService.isHadCreateRefuseOrders(orderNo);
        order.setHadCreateRefuse(hadCreateRefuse);

        // 获取原支付方式
        order.setOriginalOrderPayList(orderPayLists);

        return "order";
    }

    public String addRefuse() {
    	
        String applySource = CommonConst.OrderRetChange_Applysource_Oms.getCode();
        String orderNo = order.getOrderNo();
        
        // 查看是否已创建拒收订单
        boolean isHadCreate = saleAfterOrderTransService.isHadCreateRefuseOrders(orderNo);
        if(isHadCreate)
        {
        	this.setActionMessages(Arrays.asList("已有拒收订单，不能再创建拒收单！"));
        	return ERROR;
        }

        OrderMain orderOrg1 = orderMainService.findByOrderNo(orderNo);
        
        OrderMain retOrder = createRetOrderMain( applySource,orderOrg1);
        // 把页面上orderMian的东西放入orderOrg
        // 运费要自己输入
        retOrder.setOrderCategory(orderCategory);
        retOrder.setTransportFee(order.getTransportFee());
        retOrder.setDateCreated(order.getDateCreated());
        retOrder.setCreatedBy(order.getCreatedBy());
        retOrder.setRemark(order.getRemark());
        retOrder.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
        
        retOrder.setClientServiceRemark(order.getClientServiceRemark());
        
        retOrder.setIfNeedRefund(order.getIfNeedRefund());
        
        OrderSub orderSubOrg1 = orderOrg1.getOrderSubs().get(0);
        
    	OrderSub retSub = this.createRetOrderSub(orderOrg1, orderSubOrg1);
        //将orderSub的页面内容填入
    	retSub.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
    	retSub.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
    	retSub.setDistributeType(orderSub.getDistributeType());
    	retSub.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
    	Long pointId = selfTakePoint.getId();
    	if(pointId!=null){
    		retSub.setSelfFetchAddress(String.valueOf(pointId));
    	}
    	

        Map<Long, OrderItem> submitBean = new HashMap<Long, OrderItem>();
        for (OrderItem item : orderItemsList) {
            submitBean.put(item.getId(), item);
        }
        List<OrderRetChgItem> rejList = new ArrayList<OrderRetChgItem>();
       /* BigDecimal totalProductPrice = new BigDecimal(0);
        BigDecimal totalPayAmount = new BigDecimal(0);
        BigDecimal totalGivePoints = new BigDecimal(0);
        BigDecimal totalWeight = new BigDecimal(0);*/
        // 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
        List<OrderItem> orderItemListOrg = orderSubOrg1.getOrderItems();
        for (OrderItem itemOrg : orderItemListOrg) {
        	OrderRetChgItem rejItem = new OrderRetChgItem();
        	
        	rejItem.setStockNo(itemOrg.getStockNo());
        	rejItem.setSkuNo(itemOrg.getSkuNo());
        	rejItem.setReason(itemOrg.getRefundReason());
        	rejItem.setRemark(itemOrg.getRemark());
        	rejItem.setRefOrderItemId(itemOrg.getId());
        	rejItem.setRefOrderItemNo(itemOrg.getOrderItemNo());
        	rejItem.setBillType(itemOrg.getBillType());
			// retItem.setProductPoint(orderItem.getProductPoint().longValue());

        	rejItem.setSaleUnit(itemOrg.getSaleUnit());
        	rejItem.setSaleNum(itemOrg.getSaleNum());
        	rejItem.setUnitPrice(itemOrg.getUnitPrice());
        	/*rejItem.setUnitDiscount(itemOrg.getUnitDiscount());
        	rejItem.setCouponAmount(itemOrg.getCouponAmount());*/
        	//rejItem.setUnitDeductedPrice(itemOrg.getUnitDeductedPrice());

        	rejItem.setItemDiscount(itemOrg.getItemDiscount());
        	rejItem.setCouponTotalMoney(itemOrg.getCouponTotalMoney());
        	rejItem.setPayAmount(itemOrg.getPayAmount());

        	rejItem.setWeight(itemOrg.getWeight());
        	
        	OrderItem itemSubmit = submitBean.get(itemOrg.getId());
        	
        	//1 拒收是整单拒收  所以只允许填入原因  和 后台写入固定的bill type  从页面上取的只有一个值
        	//itemOrg.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
        	//itemOrg.setRefundReason(itemSubmit.getRefundReason());
        	rejItem.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
        	rejItem.setReason(itemSubmit.getRefundReason());
        	
        	//2 以下值 只需要从原订单取得 不需要从页面取得  而且从源数据库取得后 仅仅为临时计算用 
             // set the amount that refer to caculate
             // 退货数量
             Long saleNum = itemOrg.getSaleNum()==null?0l:itemOrg.getSaleNum();

             // 折前单价
             BigDecimal unitPrice = itemOrg.getUnitPrice()==null?new BigDecimal(0):itemOrg.getUnitPrice();
            
             
             // 单价优惠金额
             //BigDecimal unitDiscount = itemOrg.getUnitDiscount()==null?new BigDecimal(0):itemOrg.getUnitDiscount();
             // 单价优惠金额从界面获取
             BigDecimal unitDiscount = itemSubmit.getUnitDiscount()==null?new BigDecimal(0):itemSubmit.getUnitDiscount();
   
             
             // 单价购物券使用金额
             //BigDecimal couponAmount = itemOrg.getCouponAmount()==null?new BigDecimal(0):itemOrg.getCouponAmount();
             // 单价购物券使用金额从界面获取
             BigDecimal couponAmount = itemSubmit.getCouponAmount()==null?new BigDecimal(0):itemSubmit.getCouponAmount();

             rejItem.setUnitDiscount(unitDiscount);
             rejItem.setCouponAmount(couponAmount);
             
              //以下折后和总结是重新计算一遍
             // 折后单价
             BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount).subtract(couponAmount);
             rejItem.setUnitDeductedPrice(unitDeductedPrice);

           /*  // 折前总金额
             BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
             rejItem.setSaleTotalMoney(saleTotalMoney);*/

             // 单价优惠总金额
             BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(saleNum));
             //rejItem.setItemDiscount(itemOrg.getItemDiscount());
             rejItem.setItemDiscount(itemDiscount);

             // 单价购物券使用总金额
             BigDecimal couponTotalMoney = couponAmount.multiply(new BigDecimal(saleNum));
             //rejItem.setCouponTotalMoney(itemOrg.getCouponAmount());
             rejItem.setCouponTotalMoney(couponTotalMoney);

             // 折后总金额
             BigDecimal payAmount = unitDeductedPrice.multiply(new BigDecimal(saleNum));
             rejItem.setPayAmount(payAmount);//rejItem.setPayAmount(itemOrg.getPayAmount());
             
         /*    
             totalProductPrice = totalProductPrice.add(itemOrg.getSaleTotalMoney());
             totalPayAmount = totalPayAmount.add(itemOrg.getPayAmount());
             if (null != itemOrg.getProductPoint()) {
                 totalGivePoints = totalGivePoints.add(itemOrg.getProductPoint());
             }
             totalWeight = totalWeight.add(itemOrg.getWeight());  */
             
             // 总优惠金额
             discountTotal = discountTotal.add(itemDiscount);
             // 总劵使用金额
             totalPromo = totalPromo.add(couponTotalMoney);
             
             rejList.add(rejItem);
        }
        // 商品总价 折前
        retOrder.setTotalProductPrice(orderOrg1.getTotalProductPrice());
        // 退款金额总计
        //retOrder.setTotalPayAmount(orderOrg1.getTotalPayAmount());
        retOrder.setTotalPayAmount(order.getTotalPayAmount());
        // 扣回积分总计
        retOrder.setTotalGivePoints(orderOrg1.getTotalGivePoints());
        // 优惠金额总计
        retOrder.setDiscountTotal(discountTotal);
        // 劵使用金额总计
        retOrder.setTotalPromo(totalPromo);
        // 总重量
        retOrder.setWeight(orderOrg1.getWeight());
        retSub.setOrderRetChgItems(rejList);
        
        // 如果原订单已支付
        if(null != orderOrg1.getStatusPay() && orderOrg1.getStatusPay().equals(OrderStatus.Order_PayStatus_Success.getCode()))
        {
        	hasOrderPay = true;
        }
        
        if(hasOrderPay){
        	 // 往orderPay里面添加页面内容  
            Iterator<OrderPay> sListIterator = orderPayLists.iterator();  
            while(sListIterator.hasNext()){  
             	OrderPay orderPay = sListIterator.next();  
                if (orderPay == null || orderPay.getPayAmount() == null
                        || orderPay.getPayAmount().compareTo(new BigDecimal(0)) == 0 
                        || StringUtils.isEmpty(orderPay.getPayCode())) {
                    sListIterator.remove();
                    continue;
                }

                // 设置payName

                PaymentMethod paymentMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
                if (null != paymentMethod) {
                    orderPay.setPayName(paymentMethod.getName());
                }
            }
            ResultDTO resultDTO = saleAfterOrderTransService.saveOrderRetChange(orderCategory, applySource, retOrder, retSub, orderPayLists);
            if(!CommonConst.Common_Result_OK.getCode().equals(resultDTO.getResultMessage()))
            {
            	this.setActionMessages(Arrays.asList(resultDTO.getResultMessage()));
            	return ERROR;
            }
            // createOrderRetChange(orderCategory, applySource, retOrder, orderSubOrg1, orderItemListOrg, orderPayLists);
        }else{
        	ResultDTO resultDTO = saleAfterOrderTransService.saveOrderRetChange(orderCategory, applySource, retOrder, retSub, null);
            if(!CommonConst.Common_Result_OK.getCode().equals(resultDTO.getResultMessage()))
            {
            	this.setActionMessages(Arrays.asList(resultDTO.getResultMessage()));
            	return ERROR;
            }
        	// createOrderRetChange(orderCategory, applySource, retOrder, orderSubOrg1, orderItemListOrg,null);
        }
        redirectionUrl = "refund.action?column=-1";
        this.setActionMessages(Arrays.asList("创建拒收单成功！订单号：" + retSub.getOrderSubNo()));
        return SUCCESS;
    }

    private void createOrderRetChange(String orderCategory, String applySource, OrderMain orderOrg,
            OrderSub orderSubOrg, List<OrderItem> orderItemList, List<OrderPay> orderPayList) {
        OrderMain retOrder = createRetOrderMain(applySource, orderOrg);
        OrderSub retSub = createRetOrderSub(orderOrg, orderSubOrg);
        List<OrderRetChgItem> retList = createRetItem(orderItemList);
        retSub.getOrderRetChgItems().clear();
        retSub.getOrderRetChgItems().addAll(retList);
        saleAfterOrderTransService.saveOrderRetChange(orderCategory, applySource, retOrder, retSub, orderPayList);

    }
    
    
    
    public String updateRefuse() {

        String orderNo = order.getOrderNo();


        OrderMain orderOrg = orderMainService.findByOrderNo(orderNo);
        // 把页面上orderMian的东西放入orderOrg
        // 运费要自己输入
        orderOrg.setTransportFee(order.getTransportFee());
        orderOrg.setDateCreated(order.getDateCreated());
        orderOrg.setCreatedBy(order.getCreatedBy());
        orderOrg.setRemark(order.getRemark());
        
        orderOrg.setIfNeedRefund(order.getIfNeedRefund());
        
        orderOrg.setClientServiceRemark(order.getClientServiceRemark());
        
        
        OrderSub orderSubOrg = orderOrg.getOrderSubs().get(0);
        //将orderSub的页面内容填入
        orderSubOrg.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
        orderSubOrg.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
        orderSubOrg.setDistributeType(orderSub.getDistributeType());
        orderSubOrg.setInvoicePrinted(orderSub.getInvoicePrinted());
        if(null != selfTakePoint.getId())
        {
            orderSubOrg.setSelfFetchAddress(String.valueOf(selfTakePoint.getId()));
        }
        
 
        List<OrderRetChgItem> orderRetChgItemistOrg = orderSubOrg.getOrderRetChgItems();
        Map<Long, OrderRetChgItem> orgBean = new HashMap<Long, OrderRetChgItem>();
        for (OrderRetChgItem item : orderRetChgItemistOrg) {
            orgBean.put(item.getId(), item);
        }
        
        
        BigDecimal totalProductPrice = new BigDecimal(0);
        BigDecimal totalPayAmount = new BigDecimal(0);
        BigDecimal totalGivePoints = new BigDecimal(0);
        BigDecimal totalWeight = new BigDecimal(0);
        // 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
        for (OrderRetChgItem itemSubmit : orderRetChgItemsList) {
        	OrderRetChgItem itemOrg = orgBean.get(itemSubmit.getId());
        	
        	//1 拒收是整单拒收  所以只允许填入原因  和 后台写入固定的bill type  从页面上取的只有一个值
        	//itemOrg.setReason(itemOrg.getReason());
        	itemOrg.setReason(itemSubmit.getReason());
        	
        	//2 以下值 只需要从原订单取得 不需要从页面取得  而且从源数据库取得后 仅仅为临时计算用 
             // set the amount that refer to caculate
             // 退货数量
             Long saleNum = itemOrg.getSaleNum()==null?0l:itemOrg.getSaleNum();

             // 折前单价
             BigDecimal unitPrice = itemOrg.getUnitPrice()==null?new BigDecimal(0):itemOrg.getUnitPrice();
            
             
             // 单价优惠金额
             BigDecimal unitDiscount = itemSubmit.getUnitDiscount()==null?new BigDecimal(0):itemSubmit.getUnitDiscount();
   
             
             // 单价购物券使用金额
             BigDecimal couponAmount = itemSubmit.getCouponAmount()==null?new BigDecimal(0):itemSubmit.getCouponAmount();

             itemOrg.setUnitDiscount(unitDiscount);
             itemOrg.setCouponAmount(couponAmount);
             
              //以下折后和总结是重新计算一遍
             // 折后单价
             BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount).subtract(couponAmount);
             itemOrg.setUnitDeductedPrice(unitDeductedPrice);

             // 折前总金额
             BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
             //itemOrg.setSaleTotalMoney(saleTotalMoney);

             // 单价优惠总金额
             BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(saleNum));
             itemOrg.setItemDiscount(itemDiscount);

             // 单价购物券使用总金额
             BigDecimal couponTotalMoney = couponAmount.multiply(new BigDecimal(saleNum));
             itemOrg.setCouponTotalMoney(couponTotalMoney);

             // 折后总金额
             BigDecimal payAmount = unitDeductedPrice.multiply(new BigDecimal(saleNum));
             itemOrg.setPayAmount(payAmount);
             
           //update itemRetChgItem
             //orderRetChgItemService.update(itemOrg);
             //由肖总提供统一更新方法
             saleAfterOrderTransService.updateRetChgDetailItem(orderOrg.getOrderCategory(), itemOrg, SaleAfterOrderTransService.updateDetailFlag_update);
             
             totalProductPrice = totalProductPrice.add(saleTotalMoney);
             totalPayAmount = totalPayAmount.add(itemOrg.getPayAmount());
             if (null != itemOrg.getProductPoint()) {
                 totalGivePoints = totalGivePoints.add(itemOrg.getProductPoint());
             }
             totalWeight = totalWeight.add(itemOrg.getWeight());    
             
             // 总优惠金额
             discountTotal = discountTotal.add(itemDiscount);
             // 总劵使用金额
             totalPromo = totalPromo.add(couponTotalMoney);
        }
        // 商品总价 折前
        orderOrg.setTotalProductPrice(totalProductPrice);
        // 退款金额总计
        //orderOrg.setTotalPayAmount(totalPayAmount);
        orderOrg.setTotalPayAmount(order.getTotalPayAmount());
        // 扣回积分总计
        orderOrg.setTotalGivePoints(totalGivePoints);
        // 优惠金额总计
        orderOrg.setDiscountTotal(discountTotal);
        // 劵使用金额总计
        orderOrg.setTotalPromo(totalPromo);
        // 总重量
        orderOrg.setWeight(totalWeight);

        
       //更新订单 子订单 和 orderPay
        orderMainService.update(orderOrg);
        orderSubService.update(orderSubOrg);

       // OrderPay orderPayOrg = orderOrg.getOrderPays().get(0);
        //往orderPay里面添加页面内容
       // if(hasOrderPay){
        	//要删除的orderPay
            String[] deleteOrderPayIds = this.getParameterValues("deleteOrderPayIds");
            if(deleteOrderPayIds!=null && deleteOrderPayIds.length>0 ){
    	        for(String id: deleteOrderPayIds){
    	        	if (StringUtils.isNotEmpty(id)){
    	        		orderPayService.delete(Long.valueOf(id));
    	        	}
    	        }
            }
            
         // 先刷选一遍orderPay里面添加页面内容  
            Iterator<OrderPay> sListIterator = orderPayLists.iterator();  
            while(sListIterator.hasNext()){  
             	OrderPay orderPay = sListIterator.next();  
             	if (orderPay==null||orderPay.getPayAmount()==null || orderPay.getPayAmount().compareTo(new BigDecimal(0))==0 || orderPay.getPayCode() == null || StringUtils.isEmpty( orderPay.getPayCode())){
             		sListIterator.remove();
             	}
            }
            
            for(OrderPay orderPay:orderPayLists){
            	if(null !=orderPay.getId()){
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
            	}else{
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
       // }
        
        //redirectionUrl = "refund.action?column=-1";
        redirectionUrl = "refund!view.action?orderNo=" + orderNo;
        return SUCCESS;
    }


	public boolean isHasOrderPay() {
		return hasOrderPay;
	}


	public void setHasOrderPay(boolean hasOrderPay) {
		this.hasOrderPay = hasOrderPay;
	}
}