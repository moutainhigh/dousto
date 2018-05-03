package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.client.constant.OrderMainConstClient;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetAdd_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.MerchantBalanceDateService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.business.saleAfter.SaleAfterService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.sys.UserService;

@ParentPackage("admin")
public class ReturnAction extends AbstractOrderAction {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	@Resource
	private OrderRetAddService orderRetAddService;
	@Resource
	private UserService userService;
	@Resource
    private OrderItemService orderItemService;
	@Resource
	private SaleAfterService saleAfterService;
	@Resource
	private MerchantBalanceDateService merchantBalanceDateService;
	
	public String view() {
		isModify = true;
		String orderNo = this.getParameter("orderNo");
		order = orderMainService.findByOrderNo(orderNo);
		
		
		
		// 设置剩余积分
		setOrderMainIntegral(order.getMemberNo());
		
		/*--------- orderSub end------------- */
		if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
			orderSub = order.getOrderSubs().get(0);
			
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
                //this.transportAreaCacheService.setStateCityCounty(addressCode, distributeAddress);
                this.transportAreaCacheService.newSetStateCityCounty(addressCode, distributeAddress);
            }
			
		}
		/*--------- orderSub end------------- */
		
		// 关联的原销售单号
        String orderRelatedOrigin = order.getOrderRelatedOrigin();
        // 获取原支付方式
        getOriginalOrderPays(orderRelatedOrigin);
		
		orderPayLists.clear();
		orderPayLists.addAll(order.getOrderPays());
	    orderCategory = order.getOrderCategory();
		return "order_view";
	}

	public String returnView() {
		
		isCreated = true;
		String orderNo = this.getParameter("orderNo");
		order = orderMainService.findByOrderNo(orderNo);
		//order.setOrderCategory(orderCategory);
		
		// 设置剩余积分
		setOrderMainIntegral(order.getMemberNo());

		User user = userService.getLoginUser();// getUser();
		if (null != user) {
			order.setCreatedBy(user.getUsername());
		}

		if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
			orderSub = order.getOrderSubs().get(0);
			List<OrderItem> items = orderSub.getOrderItems();
			List<OrderRetAdd> orderRetAddList = null;

			// 可退数量
			Long remainNum = 0L;

			// 总共可退数量
			Long totalRemainNum = 0L;
			for (OrderItem orderItem : items) {
				orderRetAddList = orderRetAddService.findByField(
						OrderRetAdd_.orderItemNo, orderItem.getOrderItemNo());
				if (null != orderRetAddList && orderRetAddList.size() > 0) {
					remainNum = orderRetAddList.get(0).getRemainNum();
					orderItem.setRemainNum(remainNum);

					totalRemainNum += remainNum;
				} else {
					orderItem.setRemainNum(orderItem.getSaleNum());
					totalRemainNum += orderItem.getSaleNum();
				}
			}
			orderSub.setTotalRemainNum(totalRemainNum);
			
			// 设置省、城市、县区下拉列表的值
			String addressCode = orderSub.getAddressCode();
			this.transportAreaCacheService.setStateCityCounty(addressCode, distributeAddress);			
		}

		orderPayLists.clear();
		orderPayLists.addAll(order.getOrderPays());
		
		// 获取原支付方式
        order.setOriginalOrderPayList(orderPayLists);
		
		return "order";
	}

	public String addReturn() {

		String applySource = CommonConst.OrderRetChange_Applysource_Oms
				.getCode();
		String orderNo = order.getOrderNo();

		// this is selected by user to decide which to submit
		String[] ids = this.getParameterValues("orderId");

		OrderMain orderOrg = orderMainService.findByOrderNo(orderNo);

		// 把页面上orderMian的东西放入orderOrg
		// 运费要自己输入

		OrderMain retOrder = createRetOrderMain(applySource, orderOrg);
		retOrder.setOrderCategory(orderCategory);
		retOrder.setTransportFee(order.getTransportFee());
		retOrder.setDateCreated(order.getDateCreated());
		retOrder.setCreatedBy(order.getCreatedBy());
		retOrder.setIfNeedRefund(order.getIfNeedRefund());
		retOrder.setRemark(order.getRemark());
		retOrder.setBillType(CommonConst.OrderMain_BillType_Negative
				.getCodeLong());
		retOrder.setClientServiceRemark(order.getClientServiceRemark());
		
		if(OrderMainConstClient.OrderMain_Ordersource_LS.getCode().equals(order.getOrderSource())){
			MerchantBalanceDate date = merchantBalanceDateService.getByMerchantCode(order.getPerformStoreCode());
			if(date != null){
				retOrder.setBalanceDate(date.getSetDate());//结算日期
			}
		}else{
			retOrder.setBalanceDate(order.getDateCreated());//结算日期
		}
		retOrder.setSaleStoreCode(order.getSaleStoreCode());
		retOrder.setPerformStoreCode(order.getPerformStoreCode());
		
		// getOrderRelatedOrigin

		OrderSub orderSubOrg = orderOrg.getOrderSubs().get(0);

		OrderSub retSub = this.createRetOrderSub(orderOrg, orderSubOrg);

		// 将orderSub的页面内容填入
		retSub.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
		retSub.setBolNo(orderSub.getBolNo()); // 门店退款二级选择
		retSub.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
		retSub.setInvoicePrinted(orderSub.getInvoicePrinted());
		retSub.setDistributeType(orderSub.getDistributeType());
		retSub.setLogisticsOutNo(orderSub.getLogisticsOutNo());
		retSub.setExpressType(orderSub.getExpressType());
		//retSub.setAddressCode(orderSub.getAddressCode());
		Long addressCode = this.transportAreaCacheService.getAddressCodeByLastGradId(distributeAddress.getState(), distributeAddress.getCity(), distributeAddress.getCounty(), distributeAddress.getStreet());
		retSub.setAddressCode(String.valueOf(addressCode));
		retSub.setAddressDetail(orderSub.getAddressDetail());
		retSub.setPostCode(orderSub.getPostCode());
		retSub.setUserName(orderSub.getUserName());
		retSub.setPhoneNum(orderSub.getPhoneNum());
		retSub.setMobPhoneNum(orderSub.getMobPhoneNum());
		retSub.setBillType(CommonConst.OrderMain_BillType_Negative
				.getCodeLong());
		/*if(null != selfTakePoint.getId())
		{
		    retSub.setSelfFetchAddress(String.valueOf(selfTakePoint.getId()));//自提点
		}*/

		List<OrderItem> orderItemListOrg = orderSubOrg.getOrderItems();

		List<OrderRetChgItem> retList = new ArrayList<OrderRetChgItem>();

		Map<Long, OrderItem> orgBean = new HashMap<Long, OrderItem>();
		for (OrderItem item : orderItemListOrg) {
			orgBean.put(item.getId(), item);
		}

		Map<Long, OrderItem> submitBean = new HashMap<Long, OrderItem>();
		
		for (OrderItem item : orderItemsList) {
			if (null != item) {
				submitBean.put(item.getId(), item);
			}
		}

		BigDecimal totalProductPrice = new BigDecimal(0);
		BigDecimal totalPayAmount = new BigDecimal(0);
		BigDecimal totalGivePoints = new BigDecimal(0);
		BigDecimal totalWeight = new BigDecimal(0);
		// 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
		for (String id : ids) {
			Long itemId = Long.valueOf(id);
			OrderItem itemOrg1 = orgBean.get(itemId);
            if (null == itemOrg1) {
                continue;
            }
			// OrderItem itemOrg = orgBean.get(itemId);
			OrderItem itemSubmit = submitBean.get(itemId);

			OrderRetChgItem retItem = new OrderRetChgItem();
			retItem.setSkuNo(itemOrg1.getSkuNo());
			retItem.setStockNo(itemOrg1.getStockNo());
			retItem.setReason(itemSubmit.getPreRefundReason());
			retItem.setRemark(itemSubmit.getRemark());
			retItem.setRefOrderItemId(itemId);
			retItem.setRefOrderItemNo(itemOrg1.getOrderItemNo());
			retItem.setBillType(CommonConst.OrderMain_BillType_Negative
					.getCodeLong());

			retItem.setSaleUnit(orderItem.getSaleUnit());

			// 退货数量
			// 退货数量
			Long saleNum = itemSubmit.getSaleNum() == null ? 0l : itemSubmit
					.getSaleNum();

			retItem.setSaleNum(saleNum);

			// 折前单价
			BigDecimal unitPrice = itemSubmit.getUnitPrice() == null ? new BigDecimal(
					0) : itemSubmit.getUnitPrice();
			retItem.setUnitPrice(unitPrice);

			// 单价优惠金额
			/*BigDecimal unitDiscount = itemSubmit.getUnitDiscount() == null ? new BigDecimal(
					0) : itemSubmit.getUnitDiscount();*/
			BigDecimal unitDiscount = itemSubmit.getUnitDiscount() == null ? new BigDecimal(
			        0) : itemSubmit.getUnitDiscount();
			retItem.setUnitDiscount(unitDiscount);

			// 单价购物券使用金额
			BigDecimal couponAmount = itemSubmit.getCouponAmount() == null ? new BigDecimal(
					0) : itemSubmit.getCouponAmount();
			retItem.setCouponAmount(couponAmount);

			// 折后单价
			BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount)
					.subtract(couponAmount);
			retItem.setUnitDeductedPrice(unitDeductedPrice);

			// 优惠金额总价
			BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(
					saleNum));
			retItem.setItemDiscount(itemDiscount);

			// 购物券使用金额总价
			BigDecimal couponTotalMoney = couponAmount.multiply(new BigDecimal(
					saleNum));
			retItem.setCouponTotalMoney(couponTotalMoney);

			BigDecimal payAmount = unitDeductedPrice.multiply(new BigDecimal(
					saleNum));
			retItem.setPayAmount(payAmount);

			retItem.setBillType(CommonConst.OrderMain_BillType_Negative
					.getCodeLong());
			BigDecimal weightUnit = null;
			Long preSaleNum = itemOrg1.getSaleNum() == null ? 1l : itemOrg1
					.getSaleNum();
			try {
				// 计算总重量
                weightUnit = itemOrg1.getWeight().divide(
                        new BigDecimal(preSaleNum), 2, RoundingMode.HALF_UP);
			} catch (Exception e) {
				logger.info("{}", e);
			}
			BigDecimal weight = weightUnit == null? new BigDecimal(0) : weightUnit.multiply(new BigDecimal(saleNum));
			retItem.setWeight(weight);
			retItem.setProductPoint(itemSubmit.getProductPoint());
			retList.add(retItem);

			if(null != itemOrg1.getSaleTotalMoney())
			{
				totalProductPrice = totalProductPrice.add(itemOrg1
						.getSaleTotalMoney());
			}
			
			totalPayAmount = totalPayAmount.add(itemOrg1.getPayAmount());
			if (itemOrg1.getProductPoint() != null) {
				totalGivePoints = totalGivePoints.add(itemOrg1
						.getProductPoint());
			}
			totalWeight = totalWeight.add(itemOrg1.getWeight());
			
			// 总优惠金额
            discountTotal = discountTotal.add(itemDiscount);
            // 总劵使用金额
            totalPromo = totalPromo.add(couponTotalMoney);
		}

		// 商品总价 折前
		retOrder.setTotalProductPrice(totalProductPrice);
		// 退款金额总计
		//retOrder.setTotalPayAmount(totalPayAmount.subtract(order.getTransportFee()));
		retOrder.setTotalPayAmount(order.getTotalPayAmount());
		if(order.getTotalPayAmount().compareTo(new BigDecimal(0)) > 0){
			retOrder.setIfNeedRefund(CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong());
		}else{
			retOrder.setIfNeedRefund(CommonConst.OrderMain_IfNeedRefund_No.getCodeLong());
		}
		// 扣回积分总计
		//retOrder.setTotalGivePoints(totalGivePoints);
		retOrder.setTotalGivePoints(order.getTotalGivePoints());
		// 优惠金额总计
		retOrder.setDiscountTotal(discountTotal);
        // 劵使用金额总计
		retOrder.setTotalPromo(totalPromo);
		// 总重量
		retOrder.setWeight(totalWeight);

		retSub.setOrderRetChgItems(retList);

		// 往orderPay里面添加页面内容
		Iterator<OrderPay> sListIterator = orderPayLists.iterator();
        while (sListIterator.hasNext()) {
            OrderPay orderPay = sListIterator.next();
            if (orderPay == null || orderPay.getPayAmount() == null
                    || orderPay.getPayAmount().compareTo(new BigDecimal(0)) == 0
                    || StringUtils.isBlank(orderPay.getPayCode())) {
                sListIterator.remove();
                continue;
            }

            PaymentMethod paymentMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
            if (null != paymentMethod) {
                orderPay.setPayName(paymentMethod.getName());
            }
        }
		String result = createOrderRetChange(orderCategory, applySource,
				retOrder, retSub, null, orderPayLists);
		if (!result.equals(saleAfterOrderTransService.OK)) {
			this.setActionMessages(Arrays.asList(result));
			return ERROR;
		} else {
            redirectionUrl = "refund.action?column=-1";
            this.setActionMessages(Arrays.asList("创建退货单成功！订单号：" + retSub.getOrderSubNo()));
            return SUCCESS;
		}
	}

	/*
	 * private String createOrderRetChange(String orderCategory, String
	 * applySource, OrderMain orderOrg, OrderSub orderSubOrg, List<OrderItem>
	 * orderItemList,List<OrderPay> orderPayList) { OrderMain retOrder =
	 * createRetOrderMain(applySource, orderOrg); OrderSub retSub =
	 * createRetOrderSub(orderOrg, orderSubOrg); List<OrderRetChgItem> retList =
	 * createRetItem(orderItemList); retSub.getOrderRetChgItems().clear();
	 * retSub.getOrderRetChgItems().addAll(retList); return
	 * saleAfterOrderService.saveOrderRetChange(orderCategory, applySource,
	 * retOrder, retSub, orderPayList);
	 * 
	 * }
	 */

	private String createOrderRetChange(String orderCategory,
			String applySource, OrderMain retOrder, OrderSub retSub,
			List<OrderItem> orderItemList, List<OrderPay> orderPayList) {
		ResultDTO dto = null;
		try {
			dto = saleAfterOrderTransService.saveOrderRetChange(orderCategory,
					applySource, retOrder, retSub, orderPayList);
		} catch (Exception e) {
			logger.error("createOrderRetChange {}", e);
			return "系统异常：" + e.getMessage();
		}
		return dto.getResultMessage();
	}

	private boolean checkArray(String[] arrays, String o) {
		if (null == o)
			return false;
		for (String s : arrays) {
			if (o.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	public String updateReturn() {

		String orderNo = order.getOrderNo();

		String[] deleteIds = this.getParameterValues("deleteIds");

		OrderMain orderOrg = orderMainService.findByOrderNo(orderNo);
		// 把页面上orderMian的东西放入orderOrg
		// 运费要自己输入
		orderOrg.setTransportFee(order.getTransportFee());
		orderOrg.setDateCreated(order.getDateCreated());
		orderOrg.setCreatedBy(order.getCreatedBy());
		orderOrg.setIfNeedRefund(order.getIfNeedRefund());
		orderOrg.setRemark(order.getRemark());

		orderOrg.setClientServiceRemark(order.getClientServiceRemark());

		OrderSub orderSubOrg = orderOrg.getOrderSubs().get(0);
		// 将orderSub的页面内容填入
		orderSubOrg.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
		orderSubOrg.setBolNo(orderSub.getBolNo()); // 门店退款二级选择
		orderSubOrg.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
		orderSubOrg.setInvoicePrinted(orderSub.getInvoicePrinted());
		orderSubOrg.setDistributeType(orderSub.getDistributeType());
		orderSubOrg.setLogisticsOutNo(orderSub.getLogisticsOutNo());
		orderSubOrg.setExpressType(orderSub.getExpressType());
		//orderSubOrg.setAddressCode(orderSub.getAddressCode());
		Long addressCode = this.transportAreaCacheService.getAddressCodeByLastGradId(distributeAddress.getState(), distributeAddress.getCity(), distributeAddress.getCounty(), distributeAddress.getStreet());
		if(addressCode!=null){
			orderSubOrg.setAddressCode(String.valueOf(addressCode));
		}else{
			orderSubOrg.setAddressCode(null);
		}
		orderSubOrg.setAddressDetail(orderSub.getAddressDetail());
		orderSubOrg.setPostCode(orderSub.getPostCode());
		orderSubOrg.setUserName(orderSub.getUserName());
		orderSubOrg.setPhoneNum(orderSub.getPhoneNum());
		orderSubOrg.setMobPhoneNum(orderSub.getMobPhoneNum());
		
		/*if(orderSub.getDistributeType()!=null && OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode().equals(orderSub.getDistributeType())){
			if(selfTakePoint.getId()!=null){
				orderSubOrg.setSelfFetchAddress(String.valueOf(selfTakePoint.getId()));
			}else{
				orderSubOrg.setSelfFetchAddress(null);
			}
		}else{*/
			orderSubOrg.setSelfFetchAddress(null);
//		}

		Map<Long, OrderRetChgItem> submitBean = new HashMap<Long, OrderRetChgItem>();
		for (OrderRetChgItem item : orderRetChgItemsList) {
			if (null != item) {
				submitBean.put(item.getId(), item);
			}
		}

		BigDecimal totalProductPrice = new BigDecimal(0);
		BigDecimal totalPayAmount = new BigDecimal(0);
		BigDecimal totalGivePoints = new BigDecimal(0);
		BigDecimal totalWeight = new BigDecimal(0);
		// 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
		
		List<OrderRetChgItem> orderRetChgItemistOrg = orderSubOrg
				.getOrderRetChgItems();
		
		Iterator<OrderRetChgItem> iterator = orderRetChgItemistOrg.iterator();  
	     while(iterator.hasNext()) {  
	         OrderRetChgItem itemOrg= iterator.next();  
	         if (null == itemOrg) {
                 continue;
             }

             // 如果是被删除的 则不需纳入总的判断   itemOrg:为最后更新的订单明细   itemSubmit：为存放从界面获取的明细
             if (deleteIds != null && deleteIds.length > 0
                     && (checkArray(deleteIds, itemOrg.getId().toString()))) {

                 // 这个按照删除的处理
                 saleAfterOrderTransService.updateRetChgDetailItem(
                         orderOrg.getOrderCategory(), itemOrg,
                         SaleAfterOrderTransService.updateDetailFlag_delete);
                 iterator.remove();
                 
                 
                 /* 删除退货累计表中记录 begin */
                 // 获取原订单明细
                 String refOrderItemNo = itemOrg.getRefOrderItemNo();
                 //OrderItem refOrderItem = orderItemService.getByOrderItemNo(refOrderItemNo);
                 // 查询当前商品的已退货数量
                 OrderRetAdd orderRetAdd = orderRetAddService.getByOrderItemNo(refOrderItemNo);
                 if(null !=orderRetAdd){
                     if(orderRetAdd.getReturnedNum().longValue()==itemOrg.getSaleNum()){
                         //直接删除
                         this.orderRetAddService.delete(orderRetAdd);
                     }else{
                         //更新
                         int newReturnedNum = orderRetAdd.getReturnedNum().intValue() - itemOrg.getSaleNum().intValue();
                         int newRemainNum = orderRetAdd.getRemainNum().intValue() + itemOrg.getSaleNum().intValue();// 计算剩余的可退换数量
                         orderRetAdd.setReturnedNum((long) newReturnedNum);
                         orderRetAdd.setRemainNum((long) newRemainNum);
                         BigDecimal newReturnedMoney = orderRetAdd.getReturnedMoney().subtract(itemOrg.getPayAmount());
                         BigDecimal newRemainMoney = orderRetAdd.getRemainMoney().add(itemOrg.getPayAmount());
                         orderRetAdd.setReturnedMoney(newReturnedMoney);
                         orderRetAdd.setRemainMoney(newRemainMoney);
                         orderRetAddService.update(orderRetAdd);
                     }
                 }
                 
                 /* 删除退货累计表中记录 end */

             }

             OrderRetChgItem itemSubmit = submitBean.get(itemOrg.getId());
             if(null == itemSubmit)
             {
                 continue;
             }

             // then do the modify
             itemOrg.setProductPoint(itemSubmit.getProductPoint());
             itemOrg.setReason(itemSubmit.getReason());

             Long preSaleNum = itemOrg.getSaleNum() == null ? 1l : itemOrg
                     .getSaleNum();

             // set the amount that refer to caculate
             // 退货数量
             // 退货数量
             Long saleNum = itemSubmit.getSaleNum() == null ? 0l
                     : itemSubmit.getSaleNum();
             itemOrg.setSaleNum(saleNum);

             // 折前单价
             BigDecimal unitPrice = itemSubmit.getUnitPrice() == null ? new BigDecimal(
                     0) : itemSubmit.getUnitPrice();
             itemOrg.setUnitPrice(unitPrice);

             // 单价优惠金额
             BigDecimal unitDiscount = itemSubmit.getUnitDiscount() == null ? new BigDecimal(
                     0) : itemSubmit.getUnitDiscount();
             itemOrg.setUnitDiscount(unitDiscount);

             // 单价购物券使用金额
             BigDecimal couponAmount = itemSubmit.getCouponAmount() == null ? new BigDecimal(
                     0) : itemSubmit.getCouponAmount();
             itemOrg.setCouponAmount(couponAmount);

             // 折后单价
             BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount)
                     .subtract(couponAmount);
             itemOrg.setUnitDeductedPrice(unitDeductedPrice);

             // 折前总金额
             BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(
                     saleNum));
             // itemOrg.setSaleTotalMoney(saleTotalMoney);

             // 优惠金额总价
             BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(
                     saleNum));
             itemOrg.setItemDiscount(itemDiscount);

             // 购物券使用金额总价
             BigDecimal couponTotalMoney = couponAmount
                     .multiply(new BigDecimal(saleNum));
             itemOrg.setCouponTotalMoney(couponTotalMoney);

             // 折后总金额
             BigDecimal payAmount = unitDeductedPrice
                     .multiply(new BigDecimal(saleNum));
             itemOrg.setPayAmount(payAmount);

             // 计算总重量
             BigDecimal weight = new BigDecimal(0);
             if (preSaleNum > 0) {
                 BigDecimal weightUnit = itemOrg.getWeight().divide(
                         new BigDecimal(preSaleNum));
                 weight = weightUnit.multiply(new BigDecimal(saleNum));
             }
             itemOrg.setWeight(weight);

             // update itemRetChgItem
             // orderRetChgItemService.update(itemOrg);
             saleAfterOrderTransService.updateRetChgDetailItem(
                     orderOrg.getOrderCategory(), itemOrg,
                     SaleAfterOrderTransService.updateDetailFlag_update);

             totalProductPrice = totalProductPrice.add(saleTotalMoney);
             totalPayAmount = totalPayAmount.add(itemOrg.getPayAmount());
             if (null != itemOrg.getProductPoint()) {
                 totalGivePoints = totalGivePoints.add(itemOrg
                         .getProductPoint());
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
		// orderOrg.setTotalPayAmount(totalPayAmount);
		orderOrg.setTotalPayAmount(order.getTotalPayAmount());
		if(order.getTotalPayAmount().compareTo(new BigDecimal(0)) > 0){
			orderOrg.setIfNeedRefund(CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong());
		}else{
			orderOrg.setIfNeedRefund(CommonConst.OrderMain_IfNeedRefund_No.getCodeLong());
		}
		// 扣回积分总计
		//orderOrg.setTotalGivePoints(totalGivePoints);
		orderOrg.setTotalGivePoints(order.getTotalGivePoints());
		// 优惠金额总计
        orderOrg.setDiscountTotal(discountTotal);
        // 劵使用金额总计
        orderOrg.setTotalPromo(totalPromo);
		// 总重量
		orderOrg.setWeight(totalWeight);

		// 更新订单 子订单 和 orderPay
		orderMainService.update(orderOrg);
		orderSubService.update(orderSubOrg);

		// 往orderPay里面添加页面内容
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

}