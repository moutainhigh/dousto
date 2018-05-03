package com.ibm.oms.service.business.saleAfter.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnItemOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderLogisticsDto;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetAdd_;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChange_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.OrdiErrOptLog_;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.constant.SaleAfterOrderErrorConst;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.MerchantBalanceDateService;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
//import com.ibm.oms.service.business.saleAfter.NewOrderPayService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.ProductPropertyService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.saleAfter.NewSaleAfterOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.business.trans.impl.OrderConverter;
import com.ibm.oms.service.pay.dto.MiyaPayOnLineOutputDto;
import com.ibm.oms.service.pay.intf.OnlinePayService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.JaxbUtil;
import com.ibm.oms.service.util.OrderReasonUtil;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.service.sys.UserService;
import com.ibm.sc.util.DateUtil;
import com.ibm.stock.dto.MessageDto;
import com.ibm.stock.dto.SkuStockOperateDto;
import com.ibm.stock.dto.SkuStockOperateHeaderDto;
import com.ibm.stock.dto.SkuStockOperateLineDto;
import com.ibm.stock.intf.StockDeductByOrderService;
import com.ibm.stock.intf.StockLockByOrderService;

/**
 * 退换货实现类
 * 
 * 2018.1.22
 *
 */
@Service("newSaleAfterOrderService")
public class NewSaleAfterOrderServiceImpl implements NewSaleAfterOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetAddService orderRetAddService;
    @Autowired
    OrderRetChangeService orderRetChangeService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Resource
    protected UserService userService;
    @Autowired
    OrderReasonUtil orderReasonUtil;
    @Autowired
    PaymentMethodUtil paymentMethodUtil;
    @Autowired
    TransportAreaCacheService transportAreaCacheService;
    @Autowired
    OrderCombineRelationService orderCombineRelationService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    ProductPropertyService productPropertyService;
    @Autowired
    StockDeductByOrderService stockDeductByOrderService;
    @Autowired
    StockLockByOrderService stockLockByOrderService;
    @Autowired
    OnlinePayService onlinePayService;
    @Autowired
	private MerchantBalanceDateService merchantBalanceDateService;
    @Autowired
    private SaleAfterOrderTransService saleAfterOrderTransService;
    
	private static BeanCopier orderMainCopy = BeanCopier.create(OrderMain.class, OrderMain.class, true); 
	
	private static BeanCopier orderSubCopy = BeanCopier.create(OrderSub.class, OrderSub.class, true); 
	
	private static BeanCopier orderPayCopy = BeanCopier.create(OrderPay.class, OrderPay.class, false); 
	
	private static BeanCopier orderItemCopy = BeanCopier.create(OrderItem.class, OrderItem.class, true); 
	
	/**
	 * 数据封装，设置OrderMain数据
	 * @param applySource 订单来源
	 * @param orderCategory 退货："ret",换货："chg"
	 * @param headerData
	 * @param orderOrg
	 * @return
	 */
	private OrderMain setRetOrderMain(String applySource,String orderCategory,ReturnHeaderOrderFromLasaDto headerData,OrderMain orderOrg){
		
		OrderMain retOrder = new OrderMain();
		if(StringUtils.isNotBlank(headerData.getReturnNo())){
			retOrder.setAliasOrderNo(headerData.getReturnNo());//外部渠道订单号
		}
		retOrder.setOrderSource(applySource);//订单来源
		retOrder.setOrderCategory(orderCategory);//订单大类
		if(StringUtils.isNotBlank(orderOrg.getMemberNo())){
			retOrder.setMemberNo(orderOrg.getMemberNo());//会员代号
		}
		if(StringUtils.isNotBlank(orderOrg.getCustomerName())){
			retOrder.setCustomerName(orderOrg.getCustomerName());//会员名称
		}
		if(StringUtils.isNotBlank(orderOrg.getCustomerPhone())){
			retOrder.setCustomerPhone(orderOrg.getCustomerPhone());//会员手机号码
		}
		if(StringUtils.isNotBlank(headerData.getReturnShopNo())){
			retOrder.setShipStoreCode(headerData.getReturnShopNo());//退货门店编号
		}
		if(StringUtils.isNotBlank(headerData.getReturnSalesNo())){
			retOrder.setSalesclerkNo(headerData.getReturnSalesNo());//退货受理导购员编号
		}else{
			retOrder.setSalesclerkNo(orderOrg.getSalesclerkNo());//下单营业员编号
		}
		if(StringUtils.isNotBlank(orderOrg.getSaleStoreCode())){
			retOrder.setSaleStoreCode(orderOrg.getSaleStoreCode());//下单门店编号
		}
		if(StringUtils.isNotBlank(orderOrg.getSaleCompanyCode())){
			retOrder.setSaleCompanyCode(orderOrg.getSaleCompanyCode());//下单公司编号
		}
		// 商品总价 折前
//		retOrder.setTotalProductPrice(totalProductPrice);
		// 退款金额总计
		if(StringUtils.isNotBlank(headerData.getTotalReturnAmount())){
			retOrder.setTotalPayAmount(new BigDecimal(headerData.getTotalReturnAmount()));
		}
		// 扣回积分总计
		if(StringUtils.isNotBlank(headerData.getBucklebackPoint())){
			retOrder.setTotalGivePoints(new BigDecimal(headerData.getBucklebackPoint()));
		}
		//返还抵现的积分
		if(StringUtils.isNotBlank(headerData.getReturnPoint())){
			retOrder.setTotalPoint(new BigDecimal(headerData.getReturnPoint()));
		}
		// 优惠金额总计
		if(StringUtils.isNotBlank(headerData.getTotalDiscountAmount())){
			retOrder.setDiscountTotal(new BigDecimal(headerData.getTotalDiscountAmount()));
		}
        // 劵使用金额总计
		if(StringUtils.isNotBlank(headerData.getTotalCouponsAmount())){
			retOrder.setTotalPromo(new BigDecimal(headerData.getTotalCouponsAmount()));
		}
				
		retOrder.setDateCreated(DateUtil.getNowDate());
		retOrder.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//单据类型 1：正向订单  ； -1：逆向订单
		retOrder.setOrderRelatedOrigin(orderOrg.getOrderNo());//退/换货关联的原单号
		if("WX".equalsIgnoreCase(applySource)){//微信
			retOrder.setCreatedBy(orderOrg.getMemberNo());
		}else{
			if(StringUtils.isNotBlank(headerData.getReturnSalesNo())){
				retOrder.setCreatedBy(headerData.getReturnSalesNo());//退货受理导购员编号
			}
		}		
		if(StringUtils.isNotBlank(headerData.getIsRefund())){
			retOrder.setIfNeedRefund(Long.valueOf(headerData.getIsRefund()));//是否需退款
		}
		retOrder.setRemark(headerData.getRemark());//
		retOrder.setRefOrderId(orderOrg.getId());
		retOrder.setRefOrderNo(orderOrg.getOrderNo());
		if(StringUtils.isNotBlank(orderOrg.getPerformStoreCode())){
			retOrder.setPerformStoreCode(orderOrg.getPerformStoreCode());
		}
		if(CommonConst.OrderRetChange_Applysource_LS.getCode().equalsIgnoreCase(applySource)){//pos
			//总状态
			retOrder.setStatusTotal(OrderStatus.RET_ORDER_RETURN_FINISHED.getCode());//退换货已完成
			//审核状态
			retOrder.setStatusConfirm(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass.getCode());//已审核
			MerchantBalanceDate date = merchantBalanceDateService.getByMerchantCode(orderOrg.getPerformStoreCode());
			if(date != null){
				retOrder.setBalanceDate(date.getSetDate());//结算日期
			}			
			if(StringUtils.isNotBlank(headerData.getIsRefund()) && "1".equals(headerData.getIsRefund())){
				retOrder.setStatusPay(OrderStatus.RetOrder_PayStatus_Success.getCode());//退款完成
			}else{
				retOrder.setStatusPay(OrderStatus.RetOrder_PayStatus_NoNeedPay.getCode());//无需退款
			}			
			retOrder.setConfirmTime(DateUtil.getNowDate());//审核时间
			if(StringUtils.isNotBlank(headerData.getReturnSalesNo())){//退货受理导购员编号
				retOrder.setConfirmerNo(headerData.getReturnSalesNo());//审核人编码
				retOrder.setFinishUserNo(headerData.getReturnSalesNo());//满足已收已付的最后一步操作人
			}
			retOrder.setFinishTime(DateUtil.getNowDate());//完成时间
		}else{			
			//总状态
			retOrder.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());//退货订单已创建
			//审核状态
			retOrder.setStatusConfirm(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Wait.getCode());//未审核
			
			retOrder.setBalanceDate(DateUtil.getNowDate());//结算日期
			if(StringUtils.isNotBlank(headerData.getIsRefund()) && "1".equals(headerData.getIsRefund())){
				retOrder.setStatusPay(OrderStatus.RetOrder_PayStatus_Paying.getCode());//需退款
			}else{
				retOrder.setStatusPay(OrderStatus.RetOrder_PayStatus_NoNeedPay.getCode());//无需退款
			}
		}
		return retOrder;
	}
	
	/**
	 * 数据封装，设置OrderSub数据
	 * @param orderOrg
	 * @param orderSubOrg
	 * @return
	 */
	private OrderSub setRetOrderSub(ReturnHeaderOrderFromLasaDto headerData,List<ReturnItemOrderFromLasaDto> itemDataList,OrderMain orderOrg,
			OrderSub orderSubOrg) {
		String dateFormate = "yyyy-MM-dd HH:mm:ss";
		OrderSub retSub = new OrderSub();
		retSub.setOrderSubRelatedOrigin(orderSubOrg.getOrderSubNo());//退/换货关联的原子单号
//		retSub.setIdOrder(orderOrg.getId());
//		retSub.setOrderNo(orderOrg.getOrderNo());
		if(StringUtils.isNotBlank(headerData.getReturnNo())){
			retSub.setAliasOrderNo(headerData.getReturnNo());//外部渠道订单号
		}
		retSub.setInvoicePrinted(orderSubOrg.getInvoicePrinted());
		retSub.setDistributeType(headerData.getReturnMode());//退货方式

		retSub.setExpressType(headerData.getExpressType());//快递类型，退换货寄回货物
		retSub.setLogisticsOutNo(headerData.getExpressNo());//快递单号
//		retSub.setAddressCode();
		retSub.setAddressDetail(headerData.getReturnAddr());//退货详细地址
//		retSub.setPostCode(orderSubOrg.getPostCode());
//		retSub.setUserName(orderSubOrg.getUserName());
//		retSub.setPhoneNum(orderSubOrg.getPhoneNum());
//		retSub.setMobPhoneNum(orderSubOrg.getMobPhoneNum());
		retSub.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//单据类型 1：正向订单  ； -1：逆向订单
		if(StringUtils.isNotBlank(headerData.getReturnStartTime())){
			retSub.setRetPreStartTime(DateUtil.getDateFormatByString(headerData.getReturnStartTime(), dateFormate));//退货预约开始时间
		}
		if(StringUtils.isNotBlank(headerData.getReturnEndTime())){
			retSub.setRetPreEndTime(DateUtil.getDateFormatByString(headerData.getReturnEndTime(),dateFormate));//退货预约结束时间
		}		
		retSub.setRetAttachFile(headerData.getAttatch());//附件URL
		if(StringUtils.isNotBlank(headerData.getReturnShopNo())){
			retSub.setStoreNo(headerData.getReturnShopNo());//门店代码
		}		
		return retSub;
	}
	
	/**
	 * 数据封装，设置OrderRetChgItem数据
	 * @param itemDataList
	 * @return
	 */
	private List<OrderRetChgItem> setRetItem(List<ReturnItemOrderFromLasaDto> itemDataList){
		List<OrderRetChgItem> retList = new ArrayList<OrderRetChgItem>();
		if(itemDataList != null && itemDataList.size() > 0){
        	for(ReturnItemOrderFromLasaDto itemDto : itemDataList){		        					        	
				OrderRetChgItem retItem = new OrderRetChgItem();
				retItem.setCommodityCode(itemDto.getProductNo());//商品编码
				retItem.setCommodityName(itemDto.getProductName());//商品名称
				retItem.setSkuNo(itemDto.getProductSKU());
				retItem.setReason(itemDto.getReturnReason());//退换货原因
				retItem.setRefOrderItemNo(itemDto.getRefOrderItemNo());//原销售明细NO
				retItem.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//单据类型 1：正向订单  ； -1：逆向订单
				// 退货数量
				retItem.setSaleNum(new Long(itemDto.getReturnQuantity()));
								
				retItem.setCouponAmount(itemDto.getCouponsAmount());//优惠券金额
				retItem.setUnitPrice(itemDto.getProductAmount());//销售单价 折前价
				retItem.setUnitDeductedPrice(itemDto.getConfirmAmount());//折后单价
				retItem.setUnitDiscount(itemDto.getDiscountAmount());//销售单件折扣
				if(StringUtils.isNotBlank(itemDto.getProductPoint())){
					retItem.setProductPoint(new BigDecimal(itemDto.getProductPoint()));//商品赠送积分
				}
				//retItem.setHasGift(itemDto.getHasGift());//订单行是否有赠品
				
				retList.add(retItem);
        	}       
		}
		return retList;
	}
	
	//优惠券，积分处理
	
	/**
	 * 接口数据校验
	 * 
	 * @param headerData
	 * @param itemDataList
	 * @return
	 */
	private ResultDTO checkDataFromLasa(ReturnHeaderOrderFromLasaDto headerData,List<ReturnItemOrderFromLasaDto> itemDataList){
    	ResultDTO dto = new ResultDTO();
    	
        /** 基本数据校验 start **/
    	if(headerData != null){
	    	/*if (StringUtils.isEmpty(headerData.getReturnNo())) {
	        	dto.setErrorMessage("退换货单号不能为空");
	            return dto; // 验证：退换货单号
	        }*/
	        if (StringUtils.isEmpty(headerData.getReturnReason())) {
	        	dto.setErrorMessage("退换货原因不能为空");
	            return dto; // 验证：退换货原因
	        }
	        if (StringUtils.isEmpty(headerData.getReturnStatus())) {
	        	dto.setErrorMessage("退换货单状态不能为空");
	            return dto; // 验证：退换货单状态
	        }
	        if (StringUtils.isEmpty(headerData.getReturnShopNo())) {
	        	dto.setErrorMessage("退换货门店编号不能为空");
	            return dto; // 验证：退换货门店编号
	        }
	        if (StringUtils.isEmpty(headerData.getOrderSalesNo())) {
	        	dto.setErrorMessage("下单营业员编号不能为空");
	            return dto; // 验证：下单营业员编号
	        }
	        if (StringUtils.isEmpty(headerData.getOrderNo())) {
	        	dto.setErrorMessage("原订单号不能为空");
	            return dto; // 验证：原订单号
	        }
	        if (StringUtils.isEmpty(headerData.getSubmitDate())) {
	        	dto.setErrorMessage("退换货申请时间不能为空");
	            return dto; // 验证：退换货申请时间
	        }
    	}
    	if(itemDataList != null){
    		for(ReturnItemOrderFromLasaDto itemDto : itemDataList){
    			if (StringUtils.isEmpty(itemDto.getRefOrderItemNo())) {
    	        	dto.setErrorMessage("原销售订单明细行No不能为空");
    	            return dto; // 验证：原销售订单明细行No
    	        }
    			if (StringUtils.isEmpty(itemDto.getProductNo())) {
    	        	dto.setErrorMessage("商品编码不能为空");
    	            return dto; // 验证：商品编码
    	        }
    			if (StringUtils.isEmpty(itemDto.getProductSKU())) {
    	        	dto.setErrorMessage("商品SKU不能为空");
    	            return dto; // 验证：商品SKU
    	        }
    			/*if (StringUtils.isEmpty(itemDto.getChgProductSKU())) {
    	        	dto.setErrorMessage("换货商品SKU不能为空");
    	            return dto; // 验证：换货商品SKU
    	        } */   			
    		}
    	}
        return dto;
	}
	
	/**
	 * 创建退换货单，来自浪沙数据
	 * 
	 * @param applySource 订单来源
	 * @param orderCategory  退货："ret",换货："chg"
	 * @param SaleAfterOrderFromLasaDto
	 */
	public ResultDTO receiveSaleAfterOrder(String applySource,String orderCategory,SaleAfterOrderFromLasaDto dto){
		ResultDTO result = new ResultDTO();
		if(dto != null){
			ReturnHeaderOrderFromLasaDto headerData = dto.getHeaderDto();
			List<ReturnItemOrderFromLasaDto> itemDataList = dto.getItemDto();
			//数据校验
			result = checkDataFromLasa(headerData,itemDataList);
			if(result != null && result.getResult() == -1){
				return result;
			}
			//
			if(headerData != null){
				String orderNo = headerData.getOrderNo();			
				OrderMain order = orderMainService.findByOrderNo(orderNo);
				//设置订单主表数据 OrderMain
				OrderMain retOrder = setRetOrderMain(applySource,orderCategory,headerData,order);							
				OrderSub orderSubOrg = new OrderSub();
				List<OrderPay> orderPayLists = new ArrayList<OrderPay>();
				if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
					orderSubOrg = order.getOrderSubs().get(0);
					//设置订单子表数据 OrderSub
					OrderSub retSub = setRetOrderSub(headerData,itemDataList,order,orderSubOrg);
					//设置订单OrderRetChgItem表数据 
			        List<OrderRetChgItem> retList = setRetItem(itemDataList);						
			        retSub.setOrderRetChgItems(retList);
			        if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
						// 往orderPay里面添加内容
				        OrderPay orderPay = new OrderPay();
				        if(StringUtils.isNotBlank(headerData.getTotalReturnAmount())){
				        	orderPay.setPayAmount(new BigDecimal(headerData.getTotalReturnAmount()));
				        }
	                    orderPay.setPayCode(headerData.getRefundTypeCode());//退款方式编码
	                    orderPay.setPayName(headerData.getRefundTypeName());//退款方式名称
	                    orderPayLists.add(orderPay);
			        }

			        //库存变化
			        String stockType = "";//1、调拨单， 2、退库单，3、销售单、4、换货单
			        if("ret".equalsIgnoreCase(orderCategory)){
			        	stockType = "2";
			        }else if("chg".equalsIgnoreCase(orderCategory)){
			        	stockType = "4";
			        }
		           MessageDto msgDto = changeStock("increase",stockType,orderNo,retOrder.getShipStoreCode(),retSub.getOrderRetChgItems(),null);            
		            if(msgDto != null && msgDto.isSuccess()){
		            	result.setResultMessage(this.OK);
		            }else{
		            	result.setResultMessage(this.FAILED);
		            	if(msgDto != null){
		            		result.setErrorMessage(msgDto.getMsg());
		            	}
		            	return result;
		            }
		            //
			        result = saveOrderRetChange(orderCategory,applySource,retOrder,retSub,orderPayLists);			        
				}else{
					result.setErrorMessage("退换货订单信息不全");
				}
			}else{
				result.setErrorMessage("没有退换货信息");
			}
		}
		return result;
	}
	
	/**
	 * 创建退换货单，来自前端
	 * 
	 * @param applySource 订单来源
	 * @param orderCategory 退货："ret",换货："chg"
	 * @param SaleAfterOrderFromLasaDto frontDto
	 */
	public ResultDTO createSaleAfterOrder(String applySource,String orderCategory,SaleAfterOrderFromLasaDto dto){
		/*ResultDTO result = new ResultDTO();
		if(frontDto != null){
			ReturnHeaderOrderFromLasaDto headerDto = frontDto.getHeaderDto();
			if(headerDto != null){			
				OrderMain order = orderMainService.findByOrderNo(headerDto.getOrderNo());//原订单
				OrderSub orderSub = new OrderSub();
				if (order.getOrderSubs() != null && order.getOrderSubs().size() > 0) {
					orderSub = order.getOrderSubs().get(0);
				}
				List<OrderPay> orderPayLists = new ArrayList<OrderPay>();
				orderPayLists.addAll(order.getOrderPays());

				result = saveOrderRetChange(orderCategory,applySource,order,orderSub,orderPayLists);
			}
		}
		return result;*/
		
		
		ResultDTO result = new ResultDTO();
        if (null == dto) {
        	result.setErrorMessage("传入参数为空！");
        	return result;
        }
        ReturnHeaderOrderFromLasaDto headerDto = dto.getHeaderDto();//退货单头数据
        try {
        	if(null == headerDto){
        		result.setErrorMessage("传入参数为空！");
        		return result;
        	}
        	String oldOrderNo = headerDto.getOrderNo();
			OrderMain oldOrder = orderMainService.findByOrderNo(oldOrderNo);
			if(null == oldOrder){
				result.setErrorMessage("原订单不存在！");
				return result;
        	}
        	
            Long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
            // 主订单
            OrderMain newMain = new OrderMain();
/*            newMain.setOrderNo("");
            newMain.setStatusPay("");
            newMain.setStatusConfirm("");*/
            newMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());
            newMain.setOrderRelatedOrigin(headerDto.getOrderNo());// 关联的原订单no
            newMain.setBillType(billType);
            newMain.setBatchNum("");
            newMain.setTransportFee(new BigDecimal(0));            
//            newMain.setIfNeedRefund(CommonConst.CommonBooleanTrueLong.getCodeLong());// 需退款
            newMain.setOrderCategory(orderCategory);//
            newMain.setOrderSource(applySource);//订单来源
            
//            newMain.setRefundType(headerDto.getRefundType());//退款申请（0全部退款,1部分退款） 新
            newMain.setMemberNo(headerDto.getMemberNo());//会员号
            newMain.setSalesclerkNo(oldOrder.getSalesclerkNo());//导购编号
            newMain.setOrderTime(DateUtil.getNowDate());
            newMain.setDateCreated(DateUtil.getNowDate());
            newMain.setPerformStoreCode(oldOrder.getPerformStoreCode());
            newMain.setBalanceDate(DateUtil.getNowDate());
        
            Long newMainId = this.orderMainService.save(newMain);
            String newMainNo = this.orderNoService.getOrderNoByOrderId(String.valueOf(newMainId));
            newMain.setOrderNo(newMainNo);
            this.orderMainService.update(newMain);
            // 子订单            
            if (null == oldOrder.getOrderSubs()) {
            	result.setErrorMessage("原子订单不存在！");
            	return result;
            }
            OrderSub oldOrderSub = oldOrder.getOrderSubs().get(0);
            OrderSub newSub = new OrderSub();
            String newSubNo = this.orderNoService.getOrderSubNoByOrderNo(newMainNo, 1);
            newSub.setOrderSubNo(newSubNo);
            newSub.setIdOrder(newMainId);
            newSub.setOrderNo(newMainNo);
            newSub.setBillType(billType);
            newSub.setOrderSubRelatedOrigin(oldOrderSub.getOrderSubNo());//关联的原子订单no
            newSub.setOrderMain(null);
            newSub.setTransportFee(new BigDecimal(0));
            newSub.setRetAttachFile(headerDto.getAttatch());//上传凭证
            newSub.setLogisticsStatus(oldOrderSub.getLogisticsStatus());//货物状态
            newSub.setDateCreated(DateUtil.getNowDate());
            newSub.setId(null);
            Long newSubId = this.orderSubService.save(newSub);
            // 明细
            List<ReturnItemOrderFromLasaDto> itemDto = dto.getItemDto();//退换货单明细数据
            if(null == itemDto || itemDto.size() <= 0){
        		result.setErrorMessage("传入参数为空！");
        		return result;
        	}
            int count = 0;
//            List<OrderItem> itemList  = oldOrderSub.getOrderItems();
//            if (itemList != null && !itemList.isEmpty()) {
                for (ReturnItemOrderFromLasaDto item : itemDto) {
                    count++;
                    OrderRetChgItem newItem = new OrderRetChgItem();
                    //BeanUtils.copyProperties(newItem, item);
                    String newItemNo = this.orderNoService.getOrderItemNoBySubOrderNo(newSubNo, count);
                    newItem.setIdOrder(newMainId);
                    newItem.setIdOrderSub(newSubId);
                    newItem.setOrderNo(newMainNo);
                    newItem.setOrderSubNo(newSubNo);
                    newItem.setOrderItemNo(newItemNo);
                    newItem.setBillType(billType);
                    newItem.setReason(headerDto.getReturnReason());//退货原因
                    newItem.setRemark(headerDto.getRemark());//退货说明
                    newItem.setSkuNo(item.getProductSKU());//商品SKU
                    newItem.setPayAmount(item.getConfirmAmount());
                    if(StringUtils.isNotBlank(item.getProductPoint())){
                    	newItem.setProductPoint(new BigDecimal(item.getProductPoint()));
                    }
                    newItem.setDateCreated(DateUtil.getNowDate());
                    this.orderRetChgItemService.save(newItem);
                }
//            }
            // 最后：订单状态扭转
            String operator = userUtil.getLoginUserRealName();
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S021020, operator, new Date(), null);
//            orderStatusService.newWriteStatusLog(newMain, newSub, OrderStatusAction.S021020.getCode(), OrderStatusAction.S021020.getCode(), headerDto.getMemberName(), headerDto.getMemberNo(), "会员", new Date(), null);

        } catch (Exception e) {
            logger.info("{}", e);
            result.setErrorMessage("创建退换货单发生异常！"+e.getMessage());
            return result;
        }
        return result;
	}

    /**
     * 创建退、换货单据 （数据组装可参考单元测试：OrderRetChangeServiceImplTest）
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param orderMain 退、换货主订单
     * @param orderSubs 子订单（需包含明细OrderRetChgItem，且明细需设置refOrderItemId、refOrderItemNo）
     * @param orderItem 明细
     * @param applySource 申请来源：线上,订单客服
     * @param orderPay 退款明细
     */
    public ResultDTO  saveOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays) {
    	
    	ResultDTO dto = new ResultDTO();
    	
        /** 基本数据校验 start **/
        if (StringUtils.isEmpty(orderCategory)) {
        	dto.setErrorMessage(NewSaleAfterOrderService.Msg_OrderCategory_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderCategory);
            return dto; // 验证：退换货类型
        }
        if (StringUtils.isEmpty(applySource)) {
        	dto.setErrorMessage(NewSaleAfterOrderService.Msg_ApplySource_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_ApplySource);
            return dto; // 验证：来源
        }
        if (null == orderSub) {
        	dto.setErrorMessage(NewSaleAfterOrderService.Msg_OrderSub_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderSub);
            return dto; // 验证：子订单
        }
        if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            if (null == orderPays || orderPays.size() == 0) {
            	dto.setErrorMessage(NewSaleAfterOrderService.Msg_OrderPay_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderPay);
                return dto; // 验证：退款信息(退货)
            }
        }        
        String refOrderNo = orderMain.getOrderRelatedOrigin(); // 关联的原订单号，即由哪张销售订单所产生的退换货
        if (StringUtils.isEmpty(refOrderNo)) {
        	dto.setErrorMessage(NewSaleAfterOrderService.Msg_RefOrderNo_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderNo);
            return dto;
        }
        if (null == orderSub.getOrderRetChgItems() || orderSub.getOrderRetChgItems().size() == 0) {
        	dto.setErrorMessage(NewSaleAfterOrderService.Msg_OrderRetChgItem_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderRetChgItem);
            return dto; // 验证：退换货明细
        }
        // 验证明细中是否有设置原明细的id、no
        /*for (OrderRetChgItem item : orderSub.getOrderRetChgItems()) {
            if (null==item.getRefOrderItemId()) {
            	dto.setErrorMessage(NewSaleAfterOrderService.Msg_RefOrderItemId_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderItemId);
                return dto;
            }
            if (StringUtils.isEmpty(item.getRefOrderItemNo())) {
            	dto.setErrorMessage(NewSaleAfterOrderService.Msg_RefOrderItemNo_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderItemNo);
                return dto;
            }
        }*/
        /** 基本数据校验 end **/
        
        // 1、添加主订单表
        orderMain.setOrderTime(DateUtil.getNowDate());
        orderMain.setDateCreated(DateUtil.getNowDate());
        orderMain.setOrderCategory(orderCategory); // 退换货类型
        orderMain.setBatchNum("");
        orderMain.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//逆向订单
        orderMain.setOrderType(CommonConst.OrderMain_OrderType_GENERAL.getCode());//订单类型 实体GENERAL
//        orderMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());// 设置默认总状态
        // 获取关联的原销售主订单
        OrderMain relatedOrderMain = this.orderMainService.findByOrderNo(refOrderNo);
        if(null != relatedOrderMain)
        {
        	orderMain.setMemberNo(relatedOrderMain.getMemberNo());//会员编号
        	orderMain.setCustomerName(relatedOrderMain.getCustomerName());
        	orderMain.setMerchantNo(relatedOrderMain.getMerchantNo()); //商家编号
        	orderMain.setMerchantType(relatedOrderMain.getMerchantType());
        }
        long orderId = orderMainService.save(orderMain);
        String orderNo = orderNoService.getOrderNoByOrderId(String.valueOf(orderId)); // 获取订单no：通过规则获取
        orderMain.setOrderNo(orderNo);
        orderMainService.update(orderMain);// 更新orderNo至ordermain表

        // 2、添加子订单表
        int subOrderCount = 1;
        orderSub.setOrderNo(orderNo);
        orderSub.setIdOrder(orderId);
        orderSub.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//逆向订单
        orderSub.setDateCreated(DateUtil.getNowDate());
        String orderSubNo = orderNoService.getOrderSubNoByOrderNo(orderNo, subOrderCount);// 获取子订单单号no
        orderSub.setOrderSubNo(orderSubNo);
        //获取原销售订单的物流商id
        OrderSub refOrderSub = orderSubService.getByField(OrderSub_.orderNo, refOrderNo);
        if(null != refOrderSub){
            orderSub.setDeliveryMerchantNo(refOrderSub.getDeliveryMerchantNo());
            orderSub.setDeliveryMerchantName(refOrderSub.getDeliveryMerchantName());    
        }
        
        long orderSubId = orderSubService.save(orderSub);
        
        List<OrderSub> newOrderSubs = new ArrayList<OrderSub>();
        newOrderSubs.add(orderSub);
        orderMain.setOrderSubs(newOrderSubs);
        // 3、添加明细表
        List<OrderRetChgItem> items = orderSub.getOrderRetChgItems();
        int itemCount = 0;
        for (OrderRetChgItem item : items) {
            itemCount++;
            String itemNo = orderNoService.getOrderItemNoBySubOrderNo(orderSubNo, itemCount);// 获取逆向订单明细no
            item.setOrderItemNo(itemNo);
            item.setOrderNo(orderNo);
            item.setIdOrder(orderId);
            item.setOrderSubNo(orderSubNo);
            item.setIdOrderSub(orderSubId);
            item.setDateCreated(DateUtil.getNowDate());
            //判断skuNo,barCode,skock_no是否为空，为空无法传输WMS
            //OrderItem orderItem = orderItemService.getByField(OrderItem_.orderItemNo, item.getRefOrderItemNo());
            OrderItem orderItem = orderItemService.getByOrderItemNo(item.getRefOrderItemNo());
            if(null!=orderItem){
                if(StringUtils.isEmpty(item.getSkuNo())){
                    item.setSkuNo(orderItem.getSkuNo());
                }
                /*if(StringUtils.isEmpty(item.getSkuId())){
                    item.setSkuId(orderItem.getSkuId());
                }*/
                /*if(StringUtils.isEmpty(item.getBarCode())){
                    item.setBarCode(orderItem.getBarCode());//条形码
                }*/
                if(StringUtils.isEmpty(item.getCommodityName())){
                    item.setCommodityName(orderItem.getCommodityName());//商品名称
                }
               /* if(StringUtils.isEmpty(item.getStockNo())){
                    item.setStockNo(orderItem.getStockNo());//库区
                }*/
                // 退货数量
    			Long saleNum = item.getSaleNum() == null ? 0l : item
    					.getSaleNum();

    			item.setSaleNum(saleNum);
    			
                /*if(null==item.getPayAmount() || item.getPayAmount().compareTo(new BigDecimal(0))==0){
                    item.setPayAmount(orderItem.getUnitPrice().multiply(new BigDecimal(item.getSaleNum())));//订单行应付金额:折后总价
                }*/
                if(null==item.getCommodityCode()){
                    item.setCommodityCode(orderItem.getCommodityCode());//商品编码
                }
                //item.setNormalPrice(orderItem.getNormalPrice()); //原价
                //item.setInventoryPrice(orderItem.getInventoryPrice());//出库成本价 
    			
				// 折前单价
				BigDecimal unitPrice = orderItem.getUnitPrice() == null ? new BigDecimal(
						0) : orderItem.getUnitPrice();
				if(null==item.getUnitPrice() || item.getUnitPrice().compareTo(new BigDecimal(0))==0){			
					item.setUnitPrice(unitPrice);
				}
				// 单价优惠金额
				BigDecimal unitDiscount = orderItem.getUnitDiscount() == null ? new BigDecimal(
				        0) : orderItem.getUnitDiscount();
				if(null==item.getUnitDiscount() || item.getUnitDiscount().compareTo(new BigDecimal(0))==0){					
					item.setUnitDiscount(unitDiscount);
				}
				// 单价购物券使用金额
				BigDecimal couponAmount = orderItem.getCouponAmount() == null ? new BigDecimal(
				        0) : orderItem.getCouponAmount();
				if(null==item.getCouponAmount() || item.getCouponAmount().compareTo(new BigDecimal(0))==0){       
					item.setCouponAmount(couponAmount);
				}
				// 折后单价
				BigDecimal unitDeductedPrice = unitPrice.subtract(unitDiscount)
						.subtract(couponAmount);
				if(null==item.getUnitDeductedPrice() || item.getUnitDeductedPrice().compareTo(new BigDecimal(0))==0){
					item.setUnitDeductedPrice(unitDeductedPrice);
				}
				// 优惠金额总价
				BigDecimal itemDiscount = item.getUnitDiscount().multiply(new BigDecimal(saleNum));
				item.setItemDiscount(itemDiscount);

				// 购物券使用金额总价
				BigDecimal couponTotalMoney = item.getCouponAmount().multiply(new BigDecimal(saleNum));
				item.setCouponTotalMoney(couponTotalMoney);

				//订单行应付金额:折后总价
				BigDecimal payAmount = item.getUnitDeductedPrice().multiply(new BigDecimal(saleNum));
				item.setPayAmount(payAmount);
				
				//商品赠送积分
				if(null==item.getProductPoint() || item.getProductPoint().compareTo(new BigDecimal(0))==0){
					item.setProductPoint(orderItem.getProductPoint());
				}
                /*//判断是否为组合商品
                if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(orderItem.getOrderItemClass())){
                    item.setOrderItemClass(orderItem.getOrderItemClass());
                    List<OrderCombineRelation> list = orderItem.getOrderCombineRelations();
                    for(OrderCombineRelation combineRela:list){
                        try{
                            OrderCombineRelation ocr = new OrderCombineRelation();
                            BeanUtils.copyProperties(ocr,combineRela);
                            ocr.setOrderItemNo(itemNo);
                            ocr.setCombineNo(combineRela.getCombineNo());
                            orderCombineRelationService.save(ocr);
                        }catch(Exception e){
                            logger.info("逆向订单"+orderNo+"copy组合商品明细失败：{}", e.getMessage());
                        }
                    }
                }*/                
            }
            //保存数据
            long orderItemId = orderRetChgItemService.save(item);
            /*//判断是否为色码款商品，写色码款属性表
            if(null!=orderItem){
                item.setProductPropertyFlag(orderItem.getProductPropertyFlag());
                try{
                    if(CommonConst.OrderItem_ProductPropertyFlag_Yes.getCodeLong()==orderItem.getProductPropertyFlag().longValue()){
                        for(ProductProperty propy: orderItem.getProductPropertys()){
                            ProductProperty newPro = new ProductProperty();
                            newPro.setIdOrder(orderId);
                            //newPro.setIdOrderItem(orderItemId);
                            newPro.setOrderNo(orderNo);
                            newPro.setOrderItemNo(itemNo);
                            newPro.setPropertyName(propy.getPropertyName());
                            newPro.setPropertyValue(propy.getPropertyValue());
                            productPropertyService.save(newPro);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    logger.info("退换货单写色码款属性，itemNo："+itemNo+"，异常："+e.getMessage());
                }
            }*/
            
            // 4、添加明细与原订单的关联关系
            OrderRetChange orderRetChange = orderRetChangeService.createOrderRetChange(applySource, orderMain, item);
            Long retChangeId = orderRetChangeService.save(orderRetChange);
            item.setChg_retChangeId(retChangeId);// 出库单用到
            if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
                // 5、累计退货数量
                String result = saleAfterOrderTransService.saverRetOrderRecord(item, orderRetChange);
                if (!result.equals(this.OK)) {
                	dto.setErrorMessage(result);
                	dto.setResultObj(SaleAfterOrderErrorConst.NotEnough_RemainNum);
                    return dto;
                }
            }
        }
        // 5、如果是退货(已付款)，则需保存明细
        if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            //获取原支付方式
            for (OrderPay orderPay : orderPays) {
                	//优惠券是否退回
                	
                	//金额是否设置
                	/*orderPay.setPayAmount();
                	orderPay.setPayCode();*/
                	
                    orderPay.setIdOrder(orderId);
                    orderPay.setOrderNo(orderNo);
                    orderPay.setPayTime(new Date());
                    orderPay.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
                    if(StringUtils.isEmpty(orderPay.getPayName())){
                        //如果名称为空则需根据code获取
                        PaymentMethod pMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
                        if(null!=pMethod){
                            orderPay.setPayName(pMethod.getName());
                        }
                    }
                    orderPayService.save(orderPay);//创建退款信息
             }
        } else if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Change.getCode())) {
            // 换货抵扣(SAP传输用到)
//            this.orderPayService.saveChangePaidByOrderMain(orderMain);
            //创建换货出库单
            String chgOrderNo = createExcOrder(orderMain, orderSub,applySource);
            if(null==chgOrderNo || chgOrderNo.equals("")){
                dto.setErrorMessage(errorMsg_AddExcOrder_Fail);
                dto.setResultObj(SaleAfterOrderErrorConst.Fail_AddExcOrder);
                return dto;
            }
            dto.setResultObj(chgOrderNo);//返回订单号（ 用于出库使用：出库单锁库存、状态流转）
            // 更新换货订单状态扭转（在SaleAfterOrderServiceImpl中统一实现）
            //orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S021020, null, null);
            //将出库单的单号保存到换货单中
            orderMain.setChgOurOrderNo(chgOrderNo);
            this.orderMainService.update(orderMain);
            
            OrderMain chgOrder = orderMainService.findByOrderNo(chgOrderNo);
            if(chgOrder != null){
            	List<OrderItem> chgOrderItem = chgOrder.getOrderItems();
	            //锁库存
	            MessageDto msgDto = changeStock("lock","4",orderNo,orderMain.getShipStoreCode(),null,chgOrderItem);
	            if(msgDto != null && msgDto.isSuccess()){
	            	dto.setResultMessage(this.OK);
	            	if(CommonConst.OrderRetChange_Applysource_LS.getCode().equalsIgnoreCase(applySource)){//pos
	            		//库存扣减
	                    msgDto = changeStock("subtract","4",orderNo,orderMain.getShipStoreCode(),null,chgOrderItem);            
	                    if(msgDto != null && msgDto.isSuccess()){
	                    	dto.setResultMessage(this.OK);
	                    }else{
	                    	dto.setResultMessage(this.FAILED);
	                    	if(msgDto != null){
	                    		dto.setErrorMessage(msgDto.getMsg());
	                    	}
	                    	return dto;
	                    }
	            	}            	
	            }else{
	            	dto.setResultMessage(this.FAILED);
	            	if(msgDto != null){
	            		dto.setErrorMessage(msgDto.getMsg());
	            	}
	            	return dto;
	            }           
            }
        }
        OrderStatusAction orderStatus = OrderStatusAction.S021020;
        if(applySource.equals(CommonConst.OrderRetChange_Applysource_LS.getCode())){
        	orderStatus = OrderStatusAction.S027080;
        }else{
        	orderStatus = OrderStatusAction.S021020;
        }
        // 更新换货订单状态扭转
        String operator = userUtil.getLoginUserRealName();
        orderStatusService.saveProcess(orderSubNo, orderStatus, operator, new Date(), null);
//        orderStatusService.newWriteStatusLog(orderMain, orderSub, OrderStatusAction.S021020.getCode(), orderStatus.getCode(), operator, null, "SYS", new Date(), null);
        dto.setResultMessage(this.OK);
        dto.setOrderNo(orderNo);
        dto.setOrderSubNo(orderSubNo);
        return dto;
    }
    
    /**
     * 库存增加，扣减
     * 
     * @param flg 增加"increase" 扣减"subtract" 锁库存"lock"
     * @param orderType 1、调拨单， 2、退库单，3、销售单、4、换货单
     * @param orderNo
     * @param shopCode
     * @param items
     * @return
     */
    public MessageDto changeStock(String flg,String orderType,String orderNo,String shopCode,List<OrderRetChgItem> items,List<OrderItem> orderItems){
    	MessageDto msgDto = new MessageDto();
    	
    	SkuStockOperateDto stockDto = new SkuStockOperateDto();
        List<SkuStockOperateHeaderDto> listHeaders = new ArrayList<SkuStockOperateHeaderDto>();
        SkuStockOperateHeaderDto stockHeaderDto = new SkuStockOperateHeaderDto();
        stockHeaderDto.setOrderNo(orderNo);//订单号
        stockHeaderDto.setOrderType(orderType);//1、调拨单， 2、退库单，3、销售单、4、换货单
        stockHeaderDto.setShopCode(shopCode);//ClientConstant.ESTORE_WAREHOUSE_CODE
        stockHeaderDto.setCreator("oms");//

        List<SkuStockOperateLineDto> listLines = new ArrayList<SkuStockOperateLineDto>();
        
        if("increase".equalsIgnoreCase(flg)){       
	        for (OrderRetChgItem item : items) {          
	            SkuStockOperateLineDto stockLineDto = new SkuStockOperateLineDto();
	            if(item.getSaleNum() != null){
	            	stockLineDto.setQty(item.getSaleNum().toString());//数量
	            }
	            //.setSkuCode(item.getSkuNo());//商品SKU
	            stockLineDto.setSkuCode(item.getSkuNo());
	            stockLineDto.setItemNo(item.getRefOrderItemNo());
	            stockLineDto.setStockType("1");//1:正常
	            listLines.add(stockLineDto);
	        }
        }else{
        	for (OrderItem item : orderItems) {          
	            SkuStockOperateLineDto stockLineDto = new SkuStockOperateLineDto();
	            if(item.getSaleNum() != null){
	            	stockLineDto.setQty(item.getSaleNum().toString());//数量
	            }
	            stockLineDto.setSkuCode(item.getSkuNo());//商品SKU
	            stockLineDto.setItemNo(item.getRefOrderItemNo());
	            stockLineDto.setStockType("1");//1:正常
	            listLines.add(stockLineDto);
	        }
        }
        stockHeaderDto.setListLines(listLines);
        listHeaders.add(stockHeaderDto);
        stockDto.setListHeaders(listHeaders);
        if("increase".equalsIgnoreCase(flg)){
        	msgDto = stockDeductByOrderService.stockIncrease(stockDto);//调用库存接口，库存增加
        }else if("subtract".equalsIgnoreCase(flg)){
        	 //if(msgDto != null && msgDto.isSuccess()){
        		 msgDto = stockDeductByOrderService.stockSubtract(stockDto);//调用库存接口，库存减少
             //}        	
        }else if("lock".equalsIgnoreCase(flg)){
        	msgDto = stockLockByOrderService.stockFrozenLock(stockDto);//调用库存接口，锁库存
        }
    	return msgDto;
    }
    
    /**
     * 创建换货出库单
     * 
     * @param chgOrder 换货意向单的主订单
     * @param chgOrderSub 换货意向单的子订单
     */
    private String createExcOrder(OrderMain chgOrder, OrderSub chgOrderSub,String applySource) {
    	OrderMain main = new OrderMain();
        try {
            String srcOrderNo = chgOrder.getOrderRelatedOrigin();// OMS原销售订单号
            OrderMain srcMain = this.orderMainService.findByOrderNo(srcOrderNo);
            OrderSub srcSub = srcMain.getOrderSubs().get(0); 
            // 主订单
            main.setOrderSource(srcMain.getOrderSource());
            main.setOrderType(srcMain.getOrderType());
            //main.setMerchantType(srcMain.getMerchantType());
            //main.setMerchantNo(srcMain.getMerchantNo());
            main.setMemberNo(srcMain.getMemberNo());
            main.setCustomerName(srcMain.getCustomerName());
            main.setCustomerPhone(srcMain.getCustomerPhone());
            //main.setMemberCardLevel(srcMain.getMemberCardLevel());
            //main.setMerchantNo(srcMain.getMerchantNo()); //商家编号
            main.setShipStoreCode(srcMain.getShipStoreCode());//（收货，发货）门店编号
            main.setPerformStoreCode(srcMain.getShipStoreCode());//业绩归属门店编号
            //main.setMerchantType(srcMain.getMerchantType());//商家类型
            //main.setDeliveryDateFlag(srcMain.getDeliveryDateFlag());
            //main.setDeliveryTimeFlag(srcMain.getDeliveryTimeFlag());
            main.setOrderTime(DateUtil.getNowDate());// 产生时间
            main.setDateCreated(DateUtil.getNowDate());
            //main.setReceiveAreaId(srcMain.getReceiveAreaId());//收货区域ID编码
            //main.setWeight(chgOrder.getWeight());//商品重量
            main.setTotalProductPrice(chgOrder.getTotalProductPrice());//商品总价，折前
            main.setClientRemark(chgOrder.getClientRemark());//顾客备注
            main.setClientServiceRemark(chgOrder.getClientServiceRemark());//后台客服留言
            
            main.setTotalPayAmount(chgOrder.getTotalPayAmount());//人民币总计
            main.setDiscountTotal(chgOrder.getDiscountTotal());//折扣金额
            
            //main.setTransportFee(chgOrder.getTransportFee());
            main.setOrderRelatedOrigin(chgOrder.getOrderNo());// 这里关联换货意向单的单号
            main.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
            main.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode());// 换货出库单
            
            OrderStatusAction orderStatus = OrderStatusAction.S021020;
            if(applySource.equals(CommonConst.OrderRetChange_Applysource_LS.getCode())){
            	orderStatus = OrderStatusAction.S027080;
            }            
            main.setStatusTotal(orderStatus.getCode()); // 设置默认状态
            
            main.setStatusPay(OrderStatus.Order_PayStatus_Success.getCode());//支付完成状态
            
            Long orderId = orderMainService.save(main);
            String orderNo = orderNoService.getOrderNoByOrderId(String.valueOf(orderId));
            main.setOrderNo(orderNo);
            orderMainService.update(main);

            // 子订单
            OrderSub sub = new OrderSub();
            sub.setIdOrder(orderId);
            sub.setOrderNo(orderNo);
            //sub.setDistributeType(OrderMainConst.OrderSub_DistributeType_NormalDeliver.getCode());// 换货出库单默认为天虹配送
            sub.setDistributeType(srcSub.getDistributeType());//默认获取原销售订单的入库方式
            sub.setDeliveryMerchantNo(srcSub.getDeliveryMerchantNo());//默认获取原销售订单的物流商ID
            sub.setDeliveryMerchantName(srcSub.getDeliveryMerchantName());//默认获取原销售订单的物流商名称
            //sub.setCheckCode(srcSub.getCheckCode()); //验证码与原销售订单相同
            /*if(null != srcSub.getSelfFetchAddress())
            {
                sub.setSelfFetchAddress(srcSub.getSelfFetchAddress());//自提点id
            }*/
            sub.setOrderSubRelatedOrigin(chgOrderSub.getOrderSubNo());//关联的原子订单号
           
            if(null==chgOrderSub.getChgOutUserName() || "".equals(chgOrderSub.getChgOutUserName())){
            	 sub.setUserName(chgOrderSub.getUserName());//收货人姓名
            }else{
            	 sub.setUserName(chgOrderSub.getChgOutUserName());
            }
            sub.setPhoneNum(chgOrderSub.getPhoneNum());
           /* if(null==chgOrderSub.getChgOutAddressCode() || "".equals(chgOrderSub.getChgOutAddressCode())){
            	 sub.setAddressCode(chgOrderSub.getAddressCode());//收货人地址信息编码
            }else{
            	 sub.setAddressCode(chgOrderSub.getChgOutAddressCode());
            }*/
            if(null==chgOrderSub.getChgOutmobPhoneNum() || "".equals(chgOrderSub.getChgOutmobPhoneNum())){
            	 sub.setMobPhoneNum(chgOrderSub.getMobPhoneNum());
		    }else{
		         sub.setMobPhoneNum(chgOrderSub.getChgOutmobPhoneNum());
		    }
            if(null==chgOrderSub.getChgOutAddressDetail() || "".equals(chgOrderSub.getChgOutAddressDetail())){
            	 sub.setAddressDetail(chgOrderSub.getAddressDetail());//收货人具体地址
		    }else{
		    	 sub.setAddressDetail(chgOrderSub.getChgOutAddressDetail());
		    }
            //sub.setDeliveryPriority(chgOrderSub.getDeliveryPriority());//换货：是否需取回原商品  配送优先级
            //sub.setTransportFee(main.getTransportFee());
            sub.setDateCreated(DateUtil.getNowDate());
            sub.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
            String orderSubNo = this.orderNoService.getOrderSubNoByOrderNo(orderNo, 1);
            sub.setOrderSubNo(orderSubNo);
            long orderSubId = this.orderSubService.save(sub);

            // 支付方式:换货抵扣（其中包含运费处理）
//            this.orderPayService.saveChangePaidByOrderMain(main);

            // 出库单明细
            OrderItem newItem;
            int count = 0;
            for (OrderRetChgItem item : chgOrderSub.getOrderRetChgItems()) {
                count++;
                newItem = new OrderItem();
                String srcItemNo = item.getRefOrderItemNo();// 原OMS明细行号
                //OrderItem srcOrderItem = this.orderItemService.getByOrderItemNo(srcItemNo); // 原OMS明细
                /*try {
                	orderItemCopy.copy(srcOrderItem, newItem,  new OrderConverter());
                    //BeanUtils.copyProperties(newItem, srcOrderItem);// 复制原明细行的属性
                } catch (Exception e) {
                    throw new RuntimeException("出库单明细copy属性异常");
                }*/
                newItem.setSaleNum(item.getSaleNum());
                newItem.setPayAmount(item.getPayAmount());
//                newItem.setSaleTotalMoney(item.getUnitPrice().multiply(new BigDecimal(item.getSaleNum())));//折前总金额（单价*数量）
                
//                newItem.setPromotionType(srcOrderItem.getPromotionType());
                newItem.setOrderNo(orderNo);
                newItem.setIdOrder(orderId);
                newItem.setOrderSubNo(orderSubNo);
                newItem.setIdOrderSub(orderSubId);
                newItem.setPromotionType(CommonConst.OrderItem_PromotionType_Normal.getCode());//出库单的促销类型都默认为普通商品
                newItem.setPromotionCode(null);
                String itemNo = orderNoService.getOrderItemNoBySubOrderNo(orderSubNo, count);
                newItem.setOrderItemNo(itemNo);
               /* if (null != item.getProductPropertyFlag()
                        && item.getProductPropertyFlag() == CommonConst.OrderItem_ProductPropertyFlag_Yes.getCodeLong()) {*/
                    // 色码款商品换不同款码
//                    newItem.setBarCode(item.getChg_barCode());
//                    newItem.setCommodityCode(item.getChg_commodityCode());//外部系统用的商品编码
                    newItem.setCommodityName(item.getChg_commodityName());//商品名称
                    newItem.setSkuNo(item.getChg_skuNo());
               /* }*/
                //newItem.setIsUnionBiz(srcOrderItem.getIsUnionBiz()); //自营联营
//                newItem.setNormalPrice(srcOrderItem.getNormalPrice()); //原价
//                newItem.setInventoryPrice(srcOrderItem.getInventoryPrice());//出库成本价
                
                /*//判断是否为组合商品
                if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(newItem.getOrderItemClass())){
                    logger.info("换货出库单"+orderNo+"，构建组合明细starting...");
                    List<OrderCombineRelation> list = srcOrderItem.getOrderCombineRelations();
                    for(OrderCombineRelation combineRela:list){
                        try{
                            OrderCombineRelation ocr = new OrderCombineRelation();
                            BeanUtils.copyProperties(ocr,combineRela);
                            ocr.setOrderItemNo(itemNo);
                            ocr.setCombineNo(combineRela.getCombineNo());
                            orderCombineRelationService.save(ocr);
                        }catch(Exception e){
                            logger.info("换货出库单"+orderNo+"copy组合商品明细失败：{}", e.getMessage());
                        }
                    }
                }*/
                Long orderItemId = this.orderItemService.save(newItem);

                // 更新换货的明细关联（将换货出库单的信息更新至order_ret_change中）
                Long retChangeId = item.getChg_retChangeId();
                OrderRetChange retChg = this.orderRetChangeService.get(retChangeId);
                if (null != retChg) {
                    retChg.setIdNewOrder(orderId);
                    retChg.setIdNewOrderSub(orderSubId);
                    retChg.setIdNewOrderItem(orderItemId);
                    retChg.setNewOrderNo(orderNo);
                    retChg.setNewOrderSubNo(orderSubNo);
                    retChg.setNewOrderItemNo(itemNo);
                    this.orderRetChangeService.update(retChg);
                }                
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
            throw new RuntimeException(errorMsg_AddExcOrder_Fail);
        }
        return main.getOrderNo();
    }
    
    /**
     * 取消库存扣减失败的订单，删除异常记录
     * @param orderSubNo
     */
    private void deleteOrderErrorLogForInventoryFail(String orderSubNo){
        if(StringUtils.isBlank(orderSubNo)){
            return;
        }
        try{
            OrdiErrOptLog ordiErrOptLog = ordiErrOptLogService.getByField(OrdiErrOptLog_.orderSubNo, orderSubNo);
            if(null!=ordiErrOptLog){
                ordiErrOptLogService.delete(ordiErrOptLog);
            }
        }catch(Exception e){
            logger.info("取消库存扣减失败订单，删除异常记录exception：", e);
        }
    }    
   
    /**
     * 退款单
     * 
     * @param orderMain
     * @param orderSub
     * @return
     */
    public OrderSub saveRefundOrder(OrderMain orderMain, OrderSub orderSub) {
        OrderSub newSub = null;
        if (null == orderMain || null == orderSub) {
            return null;
        }
        try {
            Long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
            // 主订单
            OrderMain newMain = new OrderMain();
            orderMainCopy.copy(orderMain, newMain, new OrderConverter());
            newMain.setOrderNo("");
            newMain.setStatusPay("");
            newMain.setStatusConfirm("");
            newMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());
            newMain.setOrderRelatedOrigin(orderMain.getOrderNo());// 关联的原订单no
            newMain.setBillType(billType);
            newMain.setBatchNum("");
            newMain.setTransportFee(new BigDecimal(0));
            
            newMain.setIfNeedRefund(CommonConst.CommonBooleanTrueLong.getCodeLong());// 需退款
            newMain.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_Refund.getCode());// 退款单
        
            Long newMainId = this.orderMainService.save(newMain);
            String newMainNo = this.orderNoService.getOrderNoByOrderId(String.valueOf(newMainId));
            newMain.setOrderNo(newMainNo);
            this.orderMainService.update(newMain);
            // 子订单
            newSub = new OrderSub();
            orderSubCopy.copy(orderSub, newSub,  new OrderConverter());
            String newSubNo = this.orderNoService.getOrderSubNoByOrderNo(newMainNo, 1);
            newSub.setOrderSubNo(newSubNo);
            newSub.setIdOrder(newMainId);
            newSub.setOrderNo(newMainNo);
            newSub.setBillType(billType);
            newSub.setOrderSubRelatedOrigin(orderSub.getOrderSubNo());// 关联的原子订单no
            newSub.setOrderMain(null);
            newSub.setTransportFee(new BigDecimal(0));
            newSub.setId(null);
            Long newSubId = this.orderSubService.save(newSub);
            // 明细
            int count = 0;
            List<OrderItem> itemList  = orderSub.getOrderItems();
            if (itemList != null && !itemList.isEmpty()) {
                for (OrderItem item : itemList) {
                    count++;
                    OrderRetChgItem newItem = new OrderRetChgItem();
                    BeanUtils.copyProperties(newItem, item);
                    //CopyUtil.Copy(newItem, item);
                    String newItemNo = this.orderNoService.getOrderItemNoBySubOrderNo(newSubNo, count);
                    newItem.setIdOrder(newMainId);
                    newItem.setIdOrderSub(newSubId);
                    newItem.setOrderNo(newMainNo);
                    newItem.setOrderSubNo(newSubNo);
                    newItem.setOrderItemNo(newItemNo);
                    newItem.setBillType(billType);
                    this.orderRetChgItemService.save(newItem);
                }
            }
            List<OrderPay> opList = orderMain.getOrderPays();
            if (opList != null && !opList.isEmpty()) {
                for (OrderPay pay : orderMain.getOrderPays()) {
                    if(pay.getPayCode().equals(PayMode.COUPON.getCode())){
                        continue; //购物券不作为退款
                    }
                    OrderPay retPay = new OrderPay();
                    orderPayCopy.copy(pay, retPay, null);
                    retPay.setId(null);
                    retPay.setOrderNo(newMainNo);
                    //retPay.setIsPrePay("");
                    retPay.setIdOrder(newMainId);
                    retPay.setPayTime(null);
                    retPay.setOrderMain(null);
                    this.orderPayService.save(retPay);
                }
            }
            // 最后：订单状态扭转
            logger.info(orderSub.getIdOrder()+"");
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S021020, null, null, null);
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S022070, null, null, null);//对于取消订单的退款单，直接进入退款中
            logger.info(orderSub.getIdOrder()+"");
        } catch (Exception e) {
            logger.info("{}", e);
            return null;
        }
        return newSub;
    }

    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param items
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetail(String orderCategory, List<OrderRetChgItem> items, String flag) {
        if (StringUtils.isEmpty(orderCategory) || StringUtils.isEmpty(flag) || null == items || items.size() == 0) {
            return;
        }
        for (OrderRetChgItem item : items) {
            if (flag.equals(this.updateDetailFlag_delete)) {
                this.orderRetChgItemService.delete(item);
            } else if (flag.equals(this.updateDetailFlag_update)) {
                this.orderRetChgItemService.update(item);
            }
            // 如果是退货，则更新累计退货数量
            if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
                BigDecimal saleNum = new BigDecimal(item.getSaleNum());
                BigDecimal payAmount = item.getPayAmount();
                String refOrderItemNo = item.getRefOrderItemNo();
                if (StringUtils.isEmpty(refOrderItemNo)) {
                    // 如果原销售明细no为空，则从明细关联表中获取
                    OrderRetChange orderRetChange = this.orderRetChangeService.getByField(
                            OrderRetChange_.retOrderItemNo, item.getOrderItemNo());
                    if (null == orderRetChange) {
                        return;
                    }
                    refOrderItemNo = orderRetChange.getOldOrderItemNo();
                }

                OrderRetAdd orderRetAdd = this.orderRetAddService.getByField(OrderRetAdd_.orderItemNo, refOrderItemNo);
                if (null == orderRetAdd) {
                    return;
                }
                BigDecimal newRemainNum = (new BigDecimal(orderRetAdd.getRemainNum())).add(saleNum);
                BigDecimal newReturnedNum = (new BigDecimal(orderRetAdd.getReturnedNum())).subtract(saleNum);
                BigDecimal newRemainMoney = (orderRetAdd.getRemainMoney()).add(payAmount);
                BigDecimal newReturnedMoney = (orderRetAdd.getReturnedMoney()).subtract(payAmount);
                orderRetAdd.setRemainNum(newRemainNum.longValue());
                orderRetAdd.setReturnedNum(newReturnedNum.longValue());
                orderRetAdd.setRemainMoney(newRemainMoney);
                orderRetAdd.setReturnedMoney(newReturnedMoney);
                this.orderRetAddService.update(orderRetAdd);
            }
        }
    }

    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param items
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetailItem(String orderCategory, OrderRetChgItem item, String flag) {
        if (StringUtils.isEmpty(orderCategory) || StringUtils.isEmpty(flag) || null == item) {
            return;
        }
        if (flag.equals(this.updateDetailFlag_delete)) {
            this.orderRetChgItemService.delete(item);
        } else if (flag.equals(this.updateDetailFlag_update)) {
            this.orderRetChgItemService.update(item);
        }
        // 如果是退货，则更新累计退货数量
        if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            BigDecimal saleNum = new BigDecimal(item.getSaleNum());
            BigDecimal payAmount = item.getPayAmount();
            OrderRetChange orderRetChange = this.orderRetChangeService.getByField(OrderRetChange_.retOrderItemNo,
                    item.getOrderItemNo());
            if (null == orderRetChange) {
                return;
            }
            OrderRetAdd orderRetAdd = this.orderRetAddService.getByField(OrderRetAdd_.orderItemNo,
                    orderRetChange.getOldOrderNo());
            if (null == orderRetAdd) {
                return;
            }
            BigDecimal newRemainNum = (new BigDecimal(orderRetAdd.getRemainNum())).add(saleNum);
            BigDecimal newReturnedNum = (new BigDecimal(orderRetAdd.getReturnedNum())).subtract(saleNum);
            BigDecimal newRemainMoney = (orderRetAdd.getRemainMoney()).add(payAmount);
            BigDecimal newReturnedMoney = (orderRetAdd.getReturnedMoney()).subtract(payAmount);
            orderRetAdd.setRemainNum(newRemainNum.longValue());
            orderRetAdd.setReturnedNum(newReturnedNum.longValue());
            orderRetAdd.setRemainMoney(newRemainMoney);
            orderRetAdd.setReturnedMoney(newReturnedMoney);
            this.orderRetAddService.update(orderRetAdd);
        }

    }
    	
    /**
     * 判断是否包含指定的支付方式
     *  payCode 支付方式code
     */
	public boolean checkOrderPayType(OrderMain orderMain,String payCode){
		boolean flag = false;
		if(null==orderMain) return flag;
		if(payCode.isEmpty()) return flag;
		List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderMain.getOrderNo());
		if (opList != null && !opList.isEmpty()) {
			for (OrderPay op : opList) {
			    if(payCode.equals(op.getPayCode())){
			    	flag = true;
			    	break;
			    }
			} 
		}
		return flag;
	}
	
	/**
	 * 判断是否全部由MY卡支付(剔除购物券)
	 * @param orderNo
	 * @return
	 */
	public boolean checkPayAllByMyCard(OrderMain orderMain){
	    boolean flag = false;
	    if(null==orderMain) return flag;
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderMain.getOrderNo());
        if (opList != null && !opList.isEmpty()) {
            boolean onLinePayFlag = false; //在线支付标识
            boolean myCardFlag = false; //MY卡支付标识
            boolean hasPaidFlag = OrderStatus.Order_PayStatus_Success.getCode().equals(orderMain.getStatusPay()); //在线支付完成
            for (OrderPay op : opList) {
                if(PayMode.COUPON.getCode().equals(op.getPayCode())){
                    continue;
                }else if(PayMode.CARD.getCode().equals(op.getPayCode())){ //MY卡支付
                    myCardFlag = true;
                }else{
                    //其他的支付方式
                    onLinePayFlag = true;
                }
            }
            if(hasPaidFlag && myCardFlag && !onLinePayFlag){
                flag = true;
            }
        }
        return flag;
	}
	
	/**
	 * 审核退款单
	 */
	public ResultDTO updateApproveRefundOrder(String orderSubNoR,String operator){
	    ResultDTO resultDTO = new ResultDTO();
		String orderNoR = this.orderNoService.getOrderNoBySubNo(orderSubNoR);
		OrderMain orderMainR = this.orderMainService.getByField(OrderMain_.orderNo, orderNoR);
		if(null != orderMainR)
		{
		    // 设置审核人
	        //orderMainR.setConfirmerName(operator);
		}else{
            resultDTO.setErrorMessage(String.format("updateApprovedRefundOrder 订单未找到, 单号:%s", orderSubNoR));
            return resultDTO;
		}
		
		// 判断是否需退maycard
		/*if(checkOrderPayType(orderMainR,PayMode.CARD.getCode())){
			ResultDTO myCardDTO = returnChangeOrderService.returnMyCard(orderMainR.getOrderNo(),operator, orderMainR,false);
			if(resultDTO.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
			    resultDTO.setErrorMessage(myCardDTO.getResultMessage());
				return resultDTO;
			}
		}*/
		
		// 状态扭转
		boolean finishFlag = false;//完成标识
		/*if(new Long(CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong()).equals(orderMainR.getIfNeedRefund())){
		    boolean updateStatusFlag1 = orderStatusService.saveProcess(orderSubNoR,OrderStatusAction.S027075, operator, new Date(), null);
		}else{*/
		    boolean updateStatusFlag2 = orderStatusService.saveProcess(orderSubNoR,OrderStatusAction.S027080, operator, new Date(), null);    
		    if(updateStatusFlag2){
		        finishFlag = true;
		    }
		//}
		logger.info("审核退款单:"+orderMainR.getOrderNo()+",结束标识:"+finishFlag);
		if(finishFlag){
		    //退、换进入待同步SAP队列
	        String category = orderMainR.getOrderCategory();
	        if(category.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())||category.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())
	                || category.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())){
	            saveSaleAfterOrderToSAP(orderSubNoR);	           
	        }
		}
		return resultDTO;
	}
	
	/**
	 * 售后意向单传输SAP【进入待同步表】
	 * @param orderMain
	 * @param orderSubNoR
	 */
	public void saveSaleAfterOrderToSAP(String orderSubNoR){
	    try{
	        if(null==orderSubNoR){
	            return;
	        }
	        String orderNoR = orderNoService.getOrderNoBySubNo(orderSubNoR);
	        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo, orderNoR);
	        if(null==orderMainR || commonUtilService.longToBoolean(orderMainR.getBillType())){
	            return;
	        }
	        logger.info("意向单:{} 写入待同步表 starting...",orderMainR.getOrderNo());
	        String orderCategory = orderMainR.getOrderCategory();
	        if(OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderCategory)){
	            //拒收则把原订单当作取消传输至SAP,前提是原订单有支付明细
	            String srcOrderNo = orderMainR.getOrderRelatedOrigin();
	            String srcOrderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(srcOrderNo);
	            List<OrderPay> srcPayList = orderPayService.findByField(OrderPay_.orderNo, srcOrderNo);
	            if(null!=srcPayList && srcPayList.size()>0){
	                Long srcOrderId = srcPayList.get(0).getIdOrder();
	                OrderMain om = orderMainService.get(srcOrderId);
	                //支付明细【充预收：原销售订单】
	                orderStatusSyncLogService.saveAndcreate(om, null, 
	                        CommonConst.OrderStatusSyncLog_SyncScene_Cancel.getCode());
	            }
	        } else if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory)){
	            //换货需判断出库单是否已完成，换货的入库单、出库单需一起传输SAP
	            saveChgOrderToSAP(orderMainR,true);
	        }else{
	            logger.info("退货意向单:{} 写入待同步表...",orderMainR.getOrderNo());
                //("I-OMS-R3-01","订单明细"),
                orderStatusSyncLogService.saveAndcreate(orderMainR, null,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
                //"I-OMS-R3-02","支付明细"
//                orderStatusSyncLogService.saveAndcreate(orderMainR, null, 
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());

	        }
	    }catch(Exception e){
	        logger.info("意向单 "+orderSubNoR+"同步R3异常：{}", e);
	    }
	}
	
	/**
	 * 检查换货入库单、出库单是否都已经完成,如果都完成则同时写入SAP待同步表
	 * @param order 
	 * @param chgFlag 入库单标识==》true:order是入库单， false:order是出库单
	 * @return
	 */
	public void saveChgOrderToSAP(OrderMain order,boolean chgFlag){
	    boolean flag = false; //完成标识
	    if(null == order){
	        return;
	    }
	    OrderMain chgOrderMain = null; //换货入库单
	    OrderMain chgOutOrderMain = null; //换货出库单
	    if(chgFlag){
	        chgOrderMain = order;
	        //入库单
	        if(OrderStatus.RET_ORDER_RETURN_FINISHED.getCode().equals(order.getStatusTotal())){
	            //获取出库单
	            String chgOutOrderNo = order.getChgOurOrderNo();//出库单的单号
	            OrderMain chgOutOrder = orderMainService.getByField(OrderMain_.orderNo, chgOutOrderNo);
	            if(null!=chgOutOrder && OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(chgOutOrder.getStatusTotal())){
	                chgOutOrderMain = chgOutOrder;
	                flag = true;
	            }
	            logger.info("换货入库单完成："+order.getOrderNo()+",对应出库单"+chgOutOrderNo+"完成状态："+flag);
	        }
	    }else{
	        chgOutOrderMain = order;
	        //出库单
	        if(OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(order.getStatusTotal())){
	            //获取入库单
	            String chgOrderNo = order.getOrderRelatedOrigin(); //入库单的单号
	            OrderMain chgOrder = orderMainService.getByField(OrderMain_.orderNo, chgOrderNo);
	            if(null!=chgOrder && OrderStatus.RET_ORDER_RETURN_FINISHED.getCode().equals(chgOrder.getStatusTotal())){
	                chgOrderMain = chgOrder;
	                flag = true;
	            }
	            logger.info("换货出库单完成："+order.getOrderNo()+",对应入库单"+chgOrderNo+"完成状态："+flag);
	        }
	    }
	    if(flag){
	        //出库单、入库单一起写SAP待同步表
	        if(null!=chgOrderMain && null!=chgOutOrderMain){
	            //一、入库单
	            OrderSub chgOrderSub = orderSubService.getByField(OrderSub_.orderNo, chgOrderMain.getOrderNo());
                orderStatusSyncLogService.saveAndcreate(chgOrderMain, chgOrderSub,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode()); //("I-OMS-R3-01","订单明细"),
//                orderStatusSyncLogService.saveAndcreate(chgOrderMain, null, 
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());//"I-OMS-R3-02","支付明细"
	            //二、出库单
                OrderSub chgOutOrderSub = orderSubService.getByField(OrderSub_.orderNo, chgOutOrderMain.getOrderNo());
                orderStatusSyncLogService.saveAndcreate(chgOutOrderMain, chgOutOrderSub,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
//                orderStatusSyncLogService.saveAndcreate(chgOutOrderMain,chgOutOrderSub,
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
	        }
	    }
	}
	
	/**
	 * 更新退货单状态
	 * 
	 * @param
	 * @return
	 */
	public CommonOutputDTO updateSaleAfterOrderStatus(String orderNo,String status){		
		return orderMainService.updateSaleAfterOrderStatus(orderNo, status);
	}
	
	
	public ResultDTO createOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays) {
    	
        //添加意向订单
    	ResultDTO dto = saveOrderRetChange(orderCategory, applySource, orderMain, orderSub, orderPays);
        
    	//换货意向单：需对出库单进行库存锁定
    	/*if(orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())){
    	    if(dto.getResultObj()!=null && (dto.getResultObj() instanceof String)){
    	        String orderNo = (String)dto.getResultObj(); //换货出库单的主订单号
                //锁定库存（换货出库单）
                InventoryLockOutputDTO output = orderCreateService.inventoryLock(orderNo); 
                if(NumberUtils.toInt(output.getReturn_status())>=0){
                    String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
                    // 更新订单状态扭转
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S011020, null, null, null);
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012040, null, null, null);
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S014041, null, null, null);
                    //因为S011020会将支付状态修改为货到付款未支付，这里需根据是否有物流费用来修改支付状态
                    OrderMain mainOut = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                    if (null==mainOut.getTransportFee() || mainOut.getTransportFee().doubleValue() <= 0) {
                        mainOut.setStatusPay(OrderStatus.Order_PayStatus_Success.getCode());
                        orderMainService.update(mainOut);
                    }
                }else{
                    //锁库存失败
                    dto.setErrorMessage(CommonConstService.SimPrefix + output.getReturn_msg());
                }
            }else{
                dto.setErrorMessage(errorMsg_AddExcOrder_Fail);//出库单单号为空
            }
    	}*/
    	
        return dto;
    }
	
	/**
	 * 修改售后申请
	 * 
	 * @param headerDto
	 * @return
	 */
	public CommonOutputDTO updateSaleAfterOrderApply(ReturnHeaderOrderFromLasaDto headerDto){
		CommonOutputDTO msgDto = new CommonOutputDTO();
		try {
			//修改主订单信息
			OrderMain orderOrg = orderMainService.findByOrderNo(headerDto.getReturnNo());
			if(orderOrg == null){
				msgDto.setCode("1");
				msgDto.setMsg("订单:"+headerDto.getOrderNo()+"不存在！");
		        return msgDto;
			}
			if(StringUtils.isNotBlank(headerDto.getTotalReturnAmount())){
				orderOrg.setTotalPayAmount(new BigDecimal(headerDto.getTotalReturnAmount()));//退款金额
			}
			//修改子订单信息
			OrderSub orderSubOrg = orderOrg.getOrderSubs().get(0);
			if(orderSubOrg == null){
				msgDto.setCode("1");
				msgDto.setMsg("订单:"+headerDto.getOrderNo()+" 子订单不存在！");
		        return msgDto;
			}
			orderSubOrg.setRetAttachFile(headerDto.getAttatch());//退货凭据
			
			List<OrderRetChgItem> orderRetChgItemistOrg = orderSubOrg
					.getOrderRetChgItems();		
			if(orderRetChgItemistOrg == null){
				msgDto.setCode("1");
				msgDto.setMsg("订单:"+headerDto.getOrderNo()+" 退换货明细订单不存在！");
		        return msgDto;
			}
			for(OrderRetChgItem item :  orderRetChgItemistOrg){
				item.setReason(headerDto.getReturnReason());//退货原因
				item.setRemark(headerDto.getRemark());//退货说明
			}
			orderSubOrg.setOrderRetChgItems(orderRetChgItemistOrg);
			// 更新订单 子订单
			orderMainService.update(orderOrg);
			orderSubService.update(orderSubOrg);
			
			msgDto.setCode("0");
			msgDto.setMsg("售后申请修改成功！");
			return msgDto;		
		} catch (Exception e) {
			e.printStackTrace();
			msgDto.setCode("1");
			msgDto.setMsg(e.getMessage());
		    return msgDto;
		}		
	}
	
	/**
	 * 创建退款单
	 * 
	 * @param dto
	 * @return
	 */
	public ResultDTO createRefundOrder(SaleAfterOrderFromLasaDto dto){
		ResultDTO result = new ResultDTO();
        if (null == dto) {
        	result.setErrorMessage("传入参数为空！");
        	return result;
        }
        ReturnHeaderOrderFromLasaDto headerDto = dto.getHeaderDto();//退款单头数据
        try {
        	if(null == headerDto){
        		result.setErrorMessage("传入参数为空！");
        		return result;
        	}
        	String oldOrderNo = headerDto.getOrderNo();
			OrderMain oldOrder = orderMainService.findByOrderNo(oldOrderNo);
			if(null == oldOrder){
				result.setErrorMessage("原订单不存在！");
				return result;
        	}
        	
            Long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
            // 主订单
            OrderMain newMain = new OrderMain();
/*            newMain.setOrderNo("");
            newMain.setStatusPay("");
            newMain.setStatusConfirm("");*/
            newMain.setOrderTime(DateUtil.getNowDate());
            newMain.setDateCreated(DateUtil.getNowDate());
            newMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());
            newMain.setOrderRelatedOrigin(headerDto.getOrderNo());// 关联的原订单no
            newMain.setBillType(billType);
            newMain.setBatchNum("");
            newMain.setTransportFee(new BigDecimal(0));            
            newMain.setIfNeedRefund(CommonConst.CommonBooleanTrueLong.getCodeLong());// 需退款
            newMain.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_Refund.getCode());// 退款单
            newMain.setOrderSource(CommonConst.OrderRetChange_Applysource_B2c.getCode());//订单来源
            
            newMain.setRefundType(headerDto.getRefundType());//退款申请（0全部退款,1部分退款） 新
            newMain.setMemberNo(headerDto.getMemberNo());//会员号
            newMain.setSalesclerkNo(oldOrder.getSalesclerkNo());//导购编号
        
            Long newMainId = this.orderMainService.save(newMain);
            String newMainNo = this.orderNoService.getOrderNoByOrderId(String.valueOf(newMainId));
            newMain.setOrderNo(newMainNo);
            this.orderMainService.update(newMain);
            // 子订单            
            if (null == oldOrder.getOrderSubs()) {
            	result.setErrorMessage("原子订单不存在！");
            	return result;
            }
            OrderSub oldOrderSub = oldOrder.getOrderSubs().get(0);
            OrderSub newSub = new OrderSub();
            String newSubNo = this.orderNoService.getOrderSubNoByOrderNo(newMainNo, 1);
            newSub.setOrderSubNo(newSubNo);
            newSub.setIdOrder(newMainId);
            newSub.setOrderNo(newMainNo);
            newSub.setBillType(billType);
            newSub.setOrderSubRelatedOrigin(oldOrderSub.getOrderSubNo());//关联的原子订单no
            newSub.setOrderMain(null);
            newSub.setTransportFee(new BigDecimal(0));
            newSub.setRetAttachFile(headerDto.getAttatch());//上传凭证
            newSub.setDateCreated(DateUtil.getNowDate());
            newSub.setId(null);
            Long newSubId = this.orderSubService.save(newSub);
            // 明细
            List<ReturnItemOrderFromLasaDto> itemDto = dto.getItemDto();//退款单明细数据
            if(null == itemDto || itemDto.size() <= 0){
        		result.setErrorMessage("传入参数为空！");
        		return result;
        	}
            int count = 0;
//            List<OrderItem> itemList  = oldOrderSub.getOrderItems();
//            if (itemList != null && !itemList.isEmpty()) {
                for (ReturnItemOrderFromLasaDto item : itemDto) {
                    count++;
                    OrderRetChgItem newItem = new OrderRetChgItem();
                    //BeanUtils.copyProperties(newItem, item);
                    String newItemNo = this.orderNoService.getOrderItemNoBySubOrderNo(newSubNo, count);
                    newItem.setIdOrder(newMainId);
                    newItem.setIdOrderSub(newSubId);
                    newItem.setOrderNo(newMainNo);
                    newItem.setOrderSubNo(newSubNo);
                    newItem.setOrderItemNo(newItemNo);
                    newItem.setBillType(billType);
                    newItem.setReason(headerDto.getReturnReason());//退款原因
                    newItem.setRemark(headerDto.getRemark());//退款说明
                    newItem.setSkuNo(item.getProductSKU());//商品SKU
                    newItem.setPayAmount(item.getConfirmAmount());
                    newItem.setDateCreated(DateUtil.getNowDate());
                    if(StringUtils.isNotBlank(item.getProductPoint())){
                    	newItem.setProductPoint(new BigDecimal(item.getProductPoint()));
                    }                                     
                    this.orderRetChgItemService.save(newItem);
                }
//            }
            List<OrderPay> opList = oldOrder.getOrderPays();
            if (opList != null && !opList.isEmpty()) {
                for (OrderPay pay : oldOrder.getOrderPays()) {
                    if(pay.getPayCode().equals(PayMode.COUPON.getCode())){
                        continue; //购物券不作为退款
                    }
                    OrderPay retPay = new OrderPay();
                    orderPayCopy.copy(pay, retPay, null);
                    retPay.setId(null);
                    retPay.setOrderNo(newMainNo);
                    //retPay.setIsPrePay("");
                    retPay.setIdOrder(newMainId);
                    retPay.setPayTime(null);
                    retPay.setOrderMain(null);
                    this.orderPayService.save(retPay);
                }
            }
            // 最后：订单状态扭转
//            logger.info(orderSub.getIdOrder()+"");
            String operator = userUtil.getLoginUserRealName();
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S021020, operator, new Date(), null);
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S022070, "系统", new Date(), null);//退款单，直接进入退款中
//            logger.info(orderSub.getIdOrder()+"");
            return result;
        } catch (Exception e) {
            logger.info("{}", e);
            result.setErrorMessage("创建退款单发生异常！"+e.getMessage());
            return result;
        }       
	}
	
	/**
	 * 审核退款单
	 */
	public ResultDTO approveRefundOrder(String orderNo){
	    ResultDTO resultDTO = new ResultDTO();
	    String operator = userUtil.getLoginUserRealName();
		OrderMain orderMainR = orderMainService.findByOrderNo(orderNo);
		if(null != orderMainR)
		{
		    // 设置审核人
	        orderMainR.setConfirmerName(operator);
		}else{
            resultDTO.setErrorMessage(String.format("approveRefundOrder 订单未找到, 单号:%s", orderNo));
            return resultDTO;
		}
		
		// 状态扭转
		boolean finishFlag = false;//完成标识
		
		OrderSub orderSub = orderMainR.getOrderSubs().get(0);
		String orderSubNoR = orderSub.getOrderSubNo();
	    boolean updateStatusFlag2 = orderStatusService.saveProcess(orderSubNoR,OrderStatusAction.S027080, operator, new Date(), null);    
	    if(updateStatusFlag2){
	        finishFlag = true;
	    }

		logger.info("审核退款单:"+orderMainR.getOrderNo()+",结束标识:"+finishFlag);
		
		//调用退款接口
		
				
		if(finishFlag){
		    //退、换进入待同步SAP队列
	        String category = orderMainR.getOrderCategory();
	        if(category.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())||category.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())
	                || category.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())){
	            saveSaleAfterOrderToSAP(orderSubNoR);           
	        }
		}
		return resultDTO;
	}
	
	/**
	 * 退换货单审批通过后，提交退换货单物流信息
	 * 
	 * @param dto
	 * @return
	 */
	public ResultDTO submitReturnLogisticsInfo(SaleAfterOrderLogisticsDto dto){
		ResultDTO resultDTO = new ResultDTO();
		if(null == dto){
			 resultDTO.setErrorMessage("submitReturnLogisticsInfo 前端数据为空");
	         return resultDTO;
		}
		try{
			OrderMain orderMainR = orderMainService.findByOrderNo(dto.getReturnNo());
			if(null != orderMainR){
				// 子订单            
	            if (null == orderMainR.getOrderSubs()) {
	            	resultDTO.setErrorMessage("子订单不存在！");
	            	return resultDTO;
	            }
	            OrderSub orderSub = orderMainR.getOrderSubs().get(0);
	            orderSub.setDateUpdated(DateUtil.getNowDate());
	            orderSub.setAddressDetail(dto.getReturnAddr());
	            orderSub.setExpressType(dto.getExpressType());
	            orderSub.setShippingOrderNo(dto.getExpressNo());
	            orderSub.setPhoneNum(dto.getReceiverPhone());
	            orderSub.setUserName(dto.getReceiverName());
	            orderSub.setLogisticsFileUrl(dto.getLogisticsFileUrl());//退货物流上传的凭证
	            orderSub.setProvidePhone(dto.getPhoneNum());//联系电话
	            orderSubService.update(orderSub);  
	            resultDTO.setResultMessage(this.OK);
	            resultDTO.setOrderNo(dto.getReturnNo());
	            resultDTO.setOrderSubNo(orderSub.getOrderSubNo());
			}else{
	            resultDTO.setErrorMessage(String.format("submitReturnLogisticsInfo 订单未找到, 单号:%s", dto.getReturnNo()));
	            return resultDTO;
			}			
			return resultDTO;
		}catch(Exception e){
			resultDTO.setErrorMessage("submitReturnLogisticsInfo 更新退换货单发生异常！"+e.getMessage());
			return resultDTO;
		}
	}
	
	/**
	 * 退款接口
	 * 
	 * @param orderMain
	 * @return
	 */
	public ResultDTO refundPay(OrderMain orderMain){
		ResultDTO resultDTO = new ResultDTO();
		String orderNo = orderMain.getOrderNo();
		try{			
			//JSON.toJSONString(payA);
			String jsonObj = "{'A2':'miya','A3':'F004','A4':'0001','A5':'0001','B1':'2015051201016','B2':'2015051201016','B4':'1'}";
			String returnStr = onlinePayService.refundPay(jsonObj);
			MiyaPayOnLineOutputDto payC = JaxbUtil.converyToJavaBean(returnStr,MiyaPayOnLineOutputDto.class);
			
			return resultDTO;
		}catch(Exception e){
			resultDTO.setErrorMessage(String.format("refundPay 退款失败, 退款单号:%s", orderNo));
            return resultDTO;
		}		
	}
}