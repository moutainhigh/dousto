package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderRetChgItem_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.ColorSizeInfo;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.saleAfter.SaleAfterOtherService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.sc.model.sys.User;

@ParentPackage("admin")
public class ChangeAction extends AbstractOrderAction {

	private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 1L;
    
	@Resource
	private SaleAfterOrderService saleAfterOrderService;
	@Resource
	private ReturnChangeOrderService returnChangeOrderService;
	@Resource
	private OrderItemService orderItemService;
	@Resource
	private OrderRetChgItemService orderRetChgItemService;
	@Resource
	private SaleAfterOtherService saleAfterOtherService;
    
    public String view() {
    	isModify = true;
        String orderNo = this.getParameter("orderNo");
        order = orderMainService.findByOrderNo(orderNo);
        
        // 设置剩余积分
     	setOrderMainIntegral(order.getMemberNo());

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
            
	        /*// 设置自提点信息
			String selfFetchAddress = orderSub.getSelfFetchAddress();
			commonUtilService.setSelfTakePointInfo(selfFetchAddress, selfTakePoint);*/
        }
        
        orderCategory = order.getOrderCategory();
        return "order_view";
        
        //select * from order_ret_chg_item
    }

 

    public String change() {
    	isCreated = true;
        String orderNo = this.getParameter("orderNo");
        order = orderMainService.findByOrderNo(orderNo);
        
        // 设置剩余积分
     	setOrderMainIntegral(order.getMemberNo());
        
        User user = getUser();
        if (null != user){
          order.setCreatedBy(getUser().getUsername());
        }

        if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
            orderSub = order.getOrderSubs().get(0);
            List<OrderItem> items = orderSub.getOrderItems();
            List<OrderRetChgItem> orderRetChgItemList = null;
            
            // 总共可换数量
            Long totalRemainNum = 0L;
            /*for(OrderItem orderItem:items){
            	orderRetChgItemList = orderRetChgItemService.findByField(OrderRetChgItem_.refOrderItemNo, orderItem.getOrderItemNo());
            	if(null != orderRetChgItemList && orderRetChgItemList.size() > 0){
            		salNum = orderRetChgItemList.get(0).getSaleNum();
            		//orderItem.setRemainNum(remainNum);
            		totalRemainNum += orderItem.getSaleNum();
            		totalRemainNum -= salNum;
            		orderItem.setRemainNum(totalRemainNum);
            		orderItem.setSaleNum(orderItem.getSaleNum() - salNum);
            	}else{
            		orderItem.setRemainNum(orderItem.getSaleNum());
            		totalRemainNum += orderItem.getSaleNum();
            	}
            }*/
            if (null != items && items.size() > 0) {
                for (OrderItem orderItem : items) {
                    if (null != orderItem) {
                        orderRetChgItemList = orderRetChgItemService.findByField(OrderRetChgItem_.refOrderItemNo,
                                orderItem.getOrderItemNo());
                        if (null != orderRetChgItemList && orderRetChgItemList.size() > 0) {
                            // 已换货数量
                            Long chgSalNum = 0L;
                            for (OrderRetChgItem orderRetChgItem : orderRetChgItemList) {
                                // 换货单单号
                                String chgOrderNo = orderRetChgItem.getOrderNo();
                                // 换货单（逆向单）
                                OrderMain chgOrderMain = orderMainService.getByField(OrderMain_.orderNo, chgOrderNo);
                                if (null != chgOrderMain) {
                                    // 如果换货单未取消 且 不是取消入库 且不是逆向订单创建中
                                    if (!OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Cancel.getCode().equals(
                                            chgOrderMain.getStatusConfirm())
                                            && !OrderStatus.RET_ORDER_INSPECTION_CANCEL.getCode().equals(
                                                    chgOrderMain.getStatusTotal())
                                                    && !OrderStatus.RET_ORDER_CREATING.getCode().equals(
                                                            chgOrderMain.getStatusTotal())) {
                                        chgSalNum += orderRetChgItem.getSaleNum();
                                    }
                                }
                            }
                            // 剩余可换货数量
                            Long remainNum = orderItem.getSaleNum() - chgSalNum;
                            orderItem.setRemainNum(remainNum);
                            totalRemainNum += remainNum;
                        } else {
                            orderItem.setRemainNum(orderItem.getSaleNum());
                            totalRemainNum += orderItem.getSaleNum();
                        }
                    }
                }
            }
            orderSub.setTotalRemainNum(totalRemainNum);
            
           /* // 设置省、城市、县区下拉列表的值
     		String addressCode = orderSub.getAddressCode();
     		this.transportAreaCacheService.setStateCityCounty(addressCode, distributeAddress);
     		
     		String shipCat = orderSub.getShipCat();
     		Date finishTime = order.getFinishTime();
     		// 判断生鲜订单是否能退、换货  true:是   false:否   (订单完成后２４小时内可以发起退换货申请,之后不行)
     		boolean isfreshFoodCanRetChg = isfreshFoodCanRetChg(shipCat, finishTime);
     		if(isfreshFoodCanRetChg)
     		{
     		    orderSub.setIsfreshFoodCanRetChg("yes");
     		}
     		else
     		{
     		   orderSub.setIsfreshFoodCanRetChg("no");
     		}*/
        }
        
        return "order";
    }

    public String addChange() {

        String applySource = CommonConst.OrderRetChange_Applysource_Oms.getCode();
        String orderNo = order.getOrderNo();

        String[] ids = this.getParameterValues("orderId");

        OrderMain orderOld = orderMainService.findByOrderNo(orderNo);
        OrderMain retOrder = createRetOrderMain(applySource, orderOld);
        // 把页面上orderMian的东西放入orderOrg
        // 运费要自己输入
        retOrder.setOrderCategory(orderCategory);
        retOrder.setTransportFee(order.getTransportFee());
        retOrder.setDateCreated(order.getDateCreated());
        retOrder.setCreatedBy(order.getCreatedBy());
        retOrder.setRemark(order.getRemark());
        retOrder.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
        // 换货不需退款
        retOrder.setTotalPayAmount(new BigDecimal(0));
        
        retOrder.setClientServiceRemark(order.getClientServiceRemark());
        retOrder.setIfNeedRefund(order.getIfNeedRefund());
        
        OrderSub orderSubOld = orderOld.getOrderSubs().get(0);
        
        OrderSub retSub = createRetOrderSub(orderOld, orderSubOld);
        //将orderSub的页面内容填入
        retSub.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
        retSub.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
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
        retSub.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
		retSub.setOrderSubRelatedOrigin(orderSubOld.getOrderSubNo());
		/*retSub.setIdOrder(orderSubOld.getId());
		retSub.setOrderNo(orderSubOld.getOrderNo());*/
		retSub.setDeliveryMerchantNo(orderSubOld.getDeliveryMerchantNo());
		retSub.setInvoicePrinted(orderSub.getInvoicePrinted());
		/*if(null != selfTakePoint.getId())
		{
		    retSub.setSelfFetchAddress(String.valueOf(selfTakePoint.getId()));
		}*/
		

        List<OrderItem> orderItemListOrg = orderSubOld.getOrderItems();
        Map<Long, OrderItem> orgBean = new HashMap<Long, OrderItem>();
        for (OrderItem item : orderItemListOrg) {
            orgBean.put(item.getId(), item);
        }

        Map<Long, OrderItem> submitBean = new HashMap<Long, OrderItem>();
        
        if(null == orderItemsList || orderItemsList.size() <= 0)
        {
        	this.setActionMessages(Arrays.asList("无订单明细，无法换货！"));
        	return ERROR;
        }
        
        // 用于替换的商品信息(新商品) 从库存接口获取
        Map<Long,CommodityStockInfoDTO> chgItemInfoMap=new HashMap<Long,CommodityStockInfoDTO>();
        for (OrderItem item : orderItemsList) {
        	if(null == item)
        	continue;
            submitBean.put(item.getId(), item);
            
            // 获取商品的skuNo
            //String skuNo = orderItemService.get(item.getId()).getSkuNo();
            
            // 获取色码款选择的skuCode
            String skuNo = this.getParameter("submitSkuCode_" + item.getId());
            if(null == skuNo || skuNo.length() == 0)
            {
            	this.setActionMessages(Arrays.asList("订单" + item.getOrderItemNo() + "的SkuCode为空，无法换货！"));
            	return ERROR;
            }
            
            // 获取库存数量
            Long skuStockNum = 0L; // sku库存
			try {
				//List<CommodityStockInfoDTO> commodityStockInfoDTOs = returnChangeOrderService.getStockInfo(skuNo).getCommodityStockInfo();
				List<CommodityStockInfoDTO> commodityStockInfoDTOs = saleAfterOtherService.getStockInfoBySkuNo(skuNo);
				
				if(null == commodityStockInfoDTOs || commodityStockInfoDTOs.size() <= 0)
				{
					this.setActionMessages(Arrays.asList("系统异常：" + skuNo +"的库存信息为null！"));
					return ERROR;
				}
				for(CommodityStockInfoDTO commodityStockInfoDTO:commodityStockInfoDTOs)
				{
				    // 接口中的skuCode
				    String interFSkuCode = commodityStockInfoDTO.getSkuCode();
				    // 获取skuCode对应的库存数
//				    if(skuNo.equals(interFSkuCode))
//				    {
				        skuStockNum = commodityStockInfoDTO.getSkuStockNum();
				        chgItemInfoMap.put(item.getId(), commodityStockInfoDTO);
//				    }
				}
			} catch (Exception e) {
				logger.error("{}", e);
				this.setActionMessages(Arrays.asList("系统异常：获取库存信息失败！"));
				return ERROR;
			}
            if(item.getSaleNum() > skuStockNum)
            {
            	this.setActionMessages(Arrays.asList("订单" + item.getOrderItemNo() + "的退货数超过库存数:" + skuStockNum + "，无法换货！"));
            	return ERROR;
            }
        }

        BigDecimal totalProductPrice = new BigDecimal(0);
        //BigDecimal totalPayAmount = new BigDecimal(0);
        BigDecimal totalGivePoints = new BigDecimal(0);
        BigDecimal totalWeight = new BigDecimal(0);
        // 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
        
    	List<OrderRetChgItem> chgList = new ArrayList<OrderRetChgItem>();
        for (String id : ids) {
            Long itemId = Long.valueOf(id);
            OrderItem itemOrg = orgBean.get(itemId);
            OrderItem itemSubmit = submitBean.get(itemId);
            
            OrderRetChgItem chgItem = new OrderRetChgItem();
            
            // 设置换货明细的相关信息
            BigDecimal weight = setChangeItemInfo(chgItemInfoMap, itemId, itemOrg, itemSubmit, chgItem, discountTotal, totalPromo);

            chgList.add(chgItem);

            totalProductPrice = totalProductPrice.add(itemOrg.getSaleTotalMoney());
            //totalPayAmount = totalPayAmount.add(itemOrg.getPayAmount());
            if (null != itemOrg.getProductPoint()) {
                totalGivePoints = totalGivePoints.add(itemOrg.getProductPoint());
            }
            totalWeight = totalWeight.add(weight);
        }
        
        retSub.setOrderRetChgItems(chgList);
        
        // 商品总价 折前
        retOrder.setTotalProductPrice(totalProductPrice);
        // 退款金额总计
        //retOrder.setTotalPayAmount(totalPayAmount);
        // 扣回积分总计
        retOrder.setTotalGivePoints(totalGivePoints);
        // 优惠金额总计
        retOrder.setDiscountTotal(discountTotal);
        // 劵使用金额总计
        retOrder.setTotalPromo(totalPromo);
        // 总重量
        retOrder.setWeight(totalWeight);
        try{
           ResultDTO resultDTO = saleAfterOrderService.createOrderRetChange(orderCategory, applySource, retOrder, retSub, null);          
           //int result = resultDTO.getResult();
           String message = resultDTO.getResultMessage();
           setActionMessages(Arrays.asList( message));
           if(!CommonConst.Common_Result_OK.getCode().equals(message))
           {
        	   return ERROR;
           }
           else
           {
               setActionMessages(Arrays.asList("创建换货单成功！订单号：" + retSub.getOrderSubNo()));
           }
        }catch(Exception e){
        	logger.error("addChange {}", e);
        	setActionMessages(Arrays.asList("系统异常：" + e.getMessage()));
        	return ERROR;
        }
        
        redirectionUrl = "refund.action?column=-1";
        return SUCCESS;
    }



    /**
     * 设置换货明细的相关信息
     * @param chgItemInfoMap 选择色码款的信息
     * @param itemId
     * @param itemOrg  从数据库中获取的值
     * @param itemSubmit  从界面上获取的值
     * @param chgItem  最终换货的值
     * @param discountTotal
     * @param totalPromo
     * @return
     */
    private BigDecimal setChangeItemInfo(Map<Long, CommodityStockInfoDTO> chgItemInfoMap, Long itemId,
            OrderItem itemOrg, OrderItem itemSubmit, OrderRetChgItem chgItem, BigDecimal discountTotal, BigDecimal totalPromo) {
        chgItem.setProductPropertyFlag(itemOrg.getProductPropertyFlag()); // 是否为色码款商品标示
        
        // -- begin 出库单需用到 --
        String colorSize1 = "";
        String colorSize2 = "";
        String fontLeft = "";
        String fontRight = "";
        if (null != chgItemInfoMap.get(itemId)) {
            chgItem.setChg_barCode(chgItemInfoMap.get(itemId).getBarCode()); // 条形码
            chgItem.setChg_commodityCode(chgItemInfoMap.get(itemId).getPartNumber()); // 商品code
            chgItem.setChg_skuNo(chgItemInfoMap.get(itemId).getSkuCode()); // skuCode
            // 获取色码款信息
            List<ColorSizeInfo> colorSizeInfoList = chgItemInfoMap.get(itemId).getColorSizeInfos();
            if (null != colorSizeInfoList && (colorSizeInfoList.size() > 0)) {
                fontLeft = " <font color=\"gray\">(";
                if (null != colorSizeInfoList.get(0)) {
                    String colorSizeName1 = colorSizeInfoList.get(0).getColorSizeName();
                    String colorSizeValue1 = colorSizeInfoList.get(0).getColorSizeValueName();
                    colorSize1 = colorSizeName1 + ":" + colorSizeValue1;
                }
                if (colorSizeInfoList.size() > 1 && null != colorSizeInfoList.get(1)) {
                    String colorSizeName2 = colorSizeInfoList.get(1).getColorSizeName();
                    String colorSizeValue2 = colorSizeInfoList.get(1).getColorSizeValueName();
                    colorSize2 = "," + colorSizeName2 + ":" + colorSizeValue2;
                }
                fontRight = ")</font>";
            }
        }
        String commodityNameColorSize = itemOrg.getCommodityName() + fontLeft + colorSize1 + colorSize2 + fontRight;
        // 样例：商品名(颜色:白色,尺码:29) 或 商品名(尺码:29,颜色:白色)
        chgItem.setChg_commodityName(commodityNameColorSize);
        // -- end 出库单需用到 --
        
        chgItem.setAdsPage(commodityNameColorSize);// 存放换货出库单的色码款信息（换货单中显示出库单的色码信息）
        chgItem.setSkuNo(itemOrg.getSkuNo());
        chgItem.setStockNo(itemOrg.getStockNo());
        chgItem.setReason(itemSubmit.getPreRefundReason());
        chgItem.setRemark(orderItem.getRemark());
        chgItem.setRefOrderItemId(itemId);
        chgItem.setRefOrderItemNo(itemOrg.getOrderItemNo());
        //chgItem.setBillType(orderItem.getBillType());
        // retItem.setProductPoint(orderItem.getProductPoint().longValue());

        chgItem.setSaleUnit(itemOrg.getSaleUnit());
        chgItem.setSaleNum(orderItem.getSaleNum());
        chgItem.setUnitPrice(itemOrg.getUnitPrice());
        /*chgItem.setUnitDiscount(itemOrg.getUnitDiscount());
        chgItem.setCouponAmount(itemOrg.getCouponAmount());*/
        chgItem.setWeight(itemOrg.getWeight());
        
        //换货只能修改 原因  和数量 其他不能修改 从页面上取的只能2个值
        // then do the modify
         // itemOrg.setRefundReason(itemSubmit.getRefundReason());
        chgItem.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());

        Long preSaleNum = itemOrg.getSaleNum()==null?1l:itemOrg.getSaleNum();
        // set the amount that refer to caculate
        // 退货数量
        Long saleNum = itemSubmit.getSaleNum()==null?0l:itemSubmit.getSaleNum();
        chgItem.setSaleNum(saleNum);

        //单价不能修改  不是从页面上取 所以不用重新设值
        // 折前单价
         // BigDecimal unitPrice = itemOrg.getUnitPrice()==null?new BigDecimal(0):itemOrg.getUnitPrice();
    
        // 单价优惠金额
        //BigDecimal unitDiscount = itemOrg.getUnitDiscount()==null?new BigDecimal(0):itemOrg.getUnitDiscount();
        // 单价优惠金额从界面获取
        BigDecimal unitDiscount = itemSubmit.getUnitDiscount()==null?new BigDecimal(0):itemSubmit.getUnitDiscount();
  
        // 单价购物券使用金额
        //BigDecimal couponAmount = itemOrg.getCouponAmount()==null?new BigDecimal(0):itemOrg.getCouponAmount();
        // 单价购物券使用金额从界面获取
        BigDecimal couponAmount = itemSubmit.getCouponAmount()==null?new BigDecimal(0):itemSubmit.getCouponAmount();
        
        chgItem.setUnitDiscount(unitDiscount);
        chgItem.setCouponAmount(couponAmount);

        //折后的要重新机选
        // 折后单价
        //BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount).subtract(couponAmount);
        //chgItem.setUnitDeductedPrice(unitDeductedPrice);

       /*   // 折前总金额
        BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
        chgItem.setSaleTotalMoney(saleTotalMoney);*/

        // 优惠金额总价
        BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(saleNum));
        chgItem.setItemDiscount(itemDiscount);

        // 购物券使用金额总价
        BigDecimal couponTotalMoney = couponAmount.multiply(new BigDecimal(saleNum));
        chgItem.setCouponTotalMoney(couponTotalMoney);

        // 折后单价
        BigDecimal unitDeductedPrice = null;
        if(null != itemOrg.getUnitPrice())
        {
            unitDeductedPrice = itemOrg.getUnitPrice().subtract(unitDiscount);
            itemOrg.setUnitDeductedPrice(unitDeductedPrice);
        }
        
        
        // 折后总金额
        if(null != unitDeductedPrice)
        {
            BigDecimal payAmount = unitDeductedPrice.multiply(new BigDecimal(saleNum));
            chgItem.setPayAmount(payAmount);
        }
        

        // 计算总重量
        BigDecimal weightUnit = itemOrg.getWeight().divide(new BigDecimal(saleNum),5,BigDecimal.ROUND_HALF_UP);
        BigDecimal weight = weightUnit.multiply(new BigDecimal(saleNum));
        chgItem.setWeight(weight);
        
        // 总优惠金额
        discountTotal = discountTotal.add(itemDiscount);
        // 总劵使用金额
        totalPromo = totalPromo.add(couponTotalMoney);
        
        return weight;
    }

    private void createOrderRetChange(String orderCategory, String applySource, OrderMain chgOrder,
            OrderSub chgSub, List<OrderItem> orderItemList) {
      /*  OrderMain retOrder = createRetOrderMain(applySource, orderOrg);
        OrderSub retSub = createRetOrderSub(orderOrg, orderSubOrg);
        List<OrderRetChgItem> retList = createRetItem(orderItemList);
        retSub.getOrderRetChgItems().clear();
        retSub.getOrderRetChgItems().addAll(retList);*/
      

    }
    
    private boolean checkArray(String[] arrays,String o){
    	if(null == o)
    		return false;
    	for(String s:arrays){
    		if(o.equalsIgnoreCase(s)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public String updateChange() {

        String orderNo = order.getOrderNo();

        String[] deleteIds = this.getParameterValues("deleteIds");

        OrderMain orderOrg = orderMainService.findByOrderNo(orderNo);
        // 把页面上orderMian的东西放入orderOrg
        // 运费要自己输入
        orderOrg.setTransportFee(order.getTransportFee());
        orderOrg.setMemberNo(order.getMemberNo());
        orderOrg.setDateCreated(order.getDateCreated());
        orderOrg.setRemark(order.getRemark());
        orderOrg.setIfNeedRefund(order.getIfNeedRefund());
        
        orderOrg.setClientServiceRemark(order.getClientServiceRemark());
        
        if(null == orderOrg.getOrderSubs() || orderOrg.getOrderSubs().size()<=0)
        {
        	setActionMessages(Arrays.asList("子订单为空，保存失败！"));
        	return SUCCESS;
        }
        
        	
        OrderSub orderSubOrg = orderOrg.getOrderSubs().get(0);
        //将orderSub的页面内容填入
        orderSubOrg.setDeliveryPriority(orderSub.getDeliveryPriority()); // 入库物流方式二级选择
        orderSubOrg.setProvideAddress(orderSub.getProvideAddress()); // 供应地点
        orderSubOrg.setInvoicePrinted(orderSub.getInvoicePrinted());
        orderSubOrg.setDistributeType(orderSub.getDistributeType());
        orderSubOrg.setLogisticsOutNo(orderSub.getLogisticsOutNo());
        orderSubOrg.setExpressType(orderSub.getExpressType());
        //orderSubOrg.setAddressCode(orderSub.getAddressCode());
        Long addressCode = this.transportAreaCacheService.getAddressCodeByLastGradId(distributeAddress.getState(), distributeAddress.getCity(), distributeAddress.getCounty(), distributeAddress.getStreet());
        orderSubOrg.setAddressCode(String.valueOf(addressCode));
        orderSubOrg.setAddressDetail(orderSub.getAddressDetail());
        orderSubOrg.setPostCode(orderSub.getPostCode());
        orderSubOrg.setUserName(orderSub.getUserName());
        orderSubOrg.setPhoneNum(orderSub.getPhoneNum());
        orderSubOrg.setMobPhoneNum(orderSub.getMobPhoneNum());
       /* if(null != selfTakePoint.getId())
        {
            orderSubOrg.setSelfFetchAddress(String.valueOf(selfTakePoint.getId()));
        }*/
        

        Map<Long, OrderRetChgItem> submitBean = new HashMap<Long, OrderRetChgItem>();
        for (OrderRetChgItem item : orderRetChgItemsList) {
        	if (null != item){
        		submitBean.put(item.getId(), item);
        	}
        }


        BigDecimal totalProductPrice = new BigDecimal(0);
        //BigDecimal totalPayAmount = new BigDecimal(0);
        BigDecimal totalGivePoints = new BigDecimal(0);
        BigDecimal totalWeight = new BigDecimal(0);
        // 总优惠金额
        BigDecimal  discountTotal = new BigDecimal(0);
        // 总劵使用金额
        BigDecimal  totalPromo = new BigDecimal(0);
        
        List<OrderRetChgItem> orderRetChgItemistOrg = orderSubOrg.getOrderRetChgItems();
        if(orderRetChgItemistOrg == null || orderRetChgItemistOrg.isEmpty()){
            logger.error("子订单无订单行{}", orderSubOrg.getOrderSubNo());
            throw new RuntimeException("子订单无订单行");
        }
        for (OrderRetChgItem itemOrg : orderRetChgItemistOrg) {
            
          //如果是被删除的 则不需纳入总的判断        itemOrg:为最后要更新的明细    itemSubmit:为存放从界面上获取的值
        	if(deleteIds !=null && deleteIds.length>0 && (checkArray(deleteIds, itemOrg.getId().toString()))){
        		
        		//这个按照删除的处理
        		saleAfterOrderTransService.updateRetChgDetailItem(orderOrg.getOrderCategory(), itemOrg, SaleAfterOrderTransService.updateDetailFlag_delete);
        		
        		orderRetChgItemistOrg.remove(itemOrg);
        		if(null == orderRetChgItemistOrg || orderRetChgItemistOrg.size() <= 0)
        		{
        			break;
        		}
        		else
        		{
        			continue;
        		}
        	}
        	
        	OrderRetChgItem itemSubmit = submitBean.get(itemOrg.getId());

            //换货只能修改 原因  和数量 其他不能修改 从页面上取的只能2个值
            // then do the modify
            itemOrg.setReason(itemSubmit.getReason());

            Long preSaleNum = itemOrg.getSaleNum()==null?1l:itemOrg.getSaleNum();
            // set the amount that refer to caculate
            // 退货数量
            Long saleNum = itemSubmit.getSaleNum()==null?0l:itemSubmit.getSaleNum();
            itemOrg.setSaleNum(saleNum);

            //单价不能修改  不是从页面上取 所以不用重新设值
            // 折前单价
            BigDecimal unitPrice = itemOrg.getUnitPrice()==null?new BigDecimal(0):itemOrg.getUnitPrice();
     
            // 单价优惠金额
            //BigDecimal unitDiscount = itemOrg.getUnitDiscount()==null?new BigDecimal(0):itemOrg.getUnitDiscount();
            // 优惠金额从页面上获取
            BigDecimal unitDiscount = itemSubmit.getUnitDiscount()==null?new BigDecimal(0):itemSubmit.getUnitDiscount();
  
            // 单价购物券使用金额
            //BigDecimal couponAmount = itemOrg.getCouponAmount()==null?new BigDecimal(0):itemOrg.getCouponAmount();
            // 购物券使用金额从页面上获取
            BigDecimal couponAmount = itemSubmit.getCouponAmount()==null?new BigDecimal(0):itemSubmit.getCouponAmount();
            
            itemOrg.setUnitDiscount(unitDiscount);
            itemOrg.setCouponAmount(couponAmount);
            
            //折后的要重新机选
            // 折后单价
            BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount).subtract(couponAmount);
            itemOrg.setUnitDeductedPrice(unitDeductedPrice);

            // 折前总金额
            BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
            //itemOrg.setSaleTotalMoney(saleTotalMoney);

            // 优惠金额总价
            BigDecimal itemDiscount = unitDiscount.multiply(new BigDecimal(saleNum));
            itemOrg.setItemDiscount(itemDiscount);

            // 购物券使用金额总价
            BigDecimal couponTotalMoney = couponAmount.multiply(new BigDecimal(saleNum));
            itemOrg.setCouponTotalMoney(couponTotalMoney);

            // 折后总金额
            BigDecimal payAmount = unitDeductedPrice.multiply(new BigDecimal(saleNum));
            itemOrg.setPayAmount(payAmount);

            // 计算总重量
            BigDecimal weight = new BigDecimal(0);
            if(preSaleNum>0){
            	BigDecimal weightUnit = itemOrg.getWeight().divide(new BigDecimal(preSaleNum));
                weight = weightUnit.multiply(new BigDecimal(saleNum));
            }
            itemOrg.setWeight(weight);

            //update itemRetChgItem
            //orderRetChgItemService.update(itemOrg);
          //由肖总提供统一更新方法
            saleAfterOrderTransService.updateRetChgDetailItem(orderOrg.getOrderCategory(), itemOrg, SaleAfterOrderTransService.updateDetailFlag_update);

            totalProductPrice = totalProductPrice.add(saleTotalMoney);
            //totalPayAmount = totalPayAmount.add(itemOrg.getPayAmount());
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
        // 扣回积分总计
        orderOrg.setTotalGivePoints(totalGivePoints);
        // 优惠金额总计
        orderOrg.setDiscountTotal(discountTotal);
        // 劵使用金额总计
        orderOrg.setTotalPromo(totalPromo);
        // 总重量
        orderOrg.setWeight(totalWeight);


       // OrderPay orderPayOrg = orderOrg.getOrderPays().get(0);
        //往orderPay里面添加页面内容
        
        //更新订单 子订单 和 orderPay
        orderMainService.update(orderOrg);
        orderSubService.update(orderSubOrg);
       // orderPayService.update(orderPayOrg);
     
        //redirectionUrl = "refund.action?column=-1";
        redirectionUrl = "refund!view.action?orderNo=" + orderNo;
        return SUCCESS;
    }

}