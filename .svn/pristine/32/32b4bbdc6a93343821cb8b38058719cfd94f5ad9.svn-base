package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.order.create.refactor.InvoiceDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderItemDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.client.dto.order.create.refactor.RecipientInformationDTO;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.dao.constant.PromotionLevel;
import com.ibm.oms.intf.constant.OrderItemConst;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.OrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPayDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.business.SubbmitOrderCalculateService;
import com.ibm.oms.service.business.SubbmitOrderConfigService;
import com.ibm.oms.service.business.SubbmitOrderService;
import com.ibm.oms.service.business.SubbmitOrderValidateService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.promo.dto.GenericResult;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.promo.dto.promotion.InfCartAdjust;
import com.ibm.promo.dto.promotion.InfCartItem;
import com.ibm.promo.intf.OrderPromotionCalculateService;


@Service("subbmitOrderService")
public class SubbmitOrderServiceImpl extends SubbmitOrderCommon  implements SubbmitOrderService,SubbmitOrderConfigService<ReceiveOrderMainDTO>{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderPromotionCalculateService orderPromotionCalculateServices;
	@Autowired
	SubbmitOrderValidateService orderValidateService;
	@Autowired
	CommonUtilService commonUtilService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	SubbmitOrderCalculateService subbmitOrderCalculateService;
	@Value("#{settings['subbmitValidate.status.default']}")
	String switchStatus;
	
	
	@Override
	public InfCart omsReceiveOrderDTOGeneraceInfCartParam(ReceiveOrderMainDTO receiveOrderMainDTO) {
		InfCart cart =  new InfCart();
		//bean 复制
		BeanUtils.copyProperties(receiveOrderMainDTO, cart);
		
		cart.setStoreId(commonUtilService.StrToLong(receiveOrderMainDTO.getStoreId()));
		cart.setZoneId(commonUtilService.StrToLong(receiveOrderMainDTO.getZoneId()));
		cart.setMemberId(commonUtilService.StrToLong(receiveOrderMainDTO.getMemberId()));
		cart.setChannelId(commonUtilService.StrToLong(receiveOrderMainDTO.getChannelId()));
		
		//组装购物车bean
		List<InfCartItem> cartItemList  = new ArrayList<InfCartItem>();
		List<ReceiveOrderItemDTO> receiveOrderItemDTOs = receiveOrderMainDTO.getReceiveOrderItemDTOs();
		for(ReceiveOrderItemDTO r : receiveOrderItemDTOs){
			InfCartItem  item =  new InfCartItem();
			BeanUtils.copyProperties(r, item);
			receiveOrderItemCopy2CartItem(r, item);
			//设置绑定商品
			settingBundleItemList(r);
			cartItemList.add(item);
		}
		cart.setCartItemList(cartItemList);
		return cart;
	}

	/**receiveOrderItemCopy 拷贝为Item额外参数
	 * @param r
	 * @param item
	 */
	private void receiveOrderItemCopy2CartItem(ReceiveOrderItemDTO r, InfCartItem item) {
		item.setSkuId(commonUtilService.StrToLong(r.getSkuId()));
		item.setPrice(commonUtilService.string2BigDecimal(r.getPrice()));
		item.setPromotionId(commonUtilService.StrToLong(r.getPromotionId()));
		item.setProductId(commonUtilService.StrToLong(r.getProductId()));
	}

	@Override
	public Map<String,Object> validateSubbmitOrder(ReceiveOrderMainDTO receiveOrderMainDTO) {
		InfCart cart = omsReceiveOrderDTOGeneraceInfCartParam(receiveOrderMainDTO);
		commonUtilService.logInfoObjectToJson("subbmitOrder-->shoppingcart-params",logger, cart);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> resulProductorIsSaler = orderValidateService.validateProductorIsSaler(receiveOrderMainDTO);		if(isOpenValidate()){
			//商品上下架判断
			if( !orderValidateService.getValidateValue(resulProductorIsSaler)){
				return resulProductorIsSaler;
			}
			
		}
		
		GenericResult<InfCart> resultCart =orderPromotionCalculateServices.calculateShoppingCart(cart);
		if(!resultCart.isSuccess()){
			result.put(SubbmitOrderValidateService.MAP_FLAG, false);
			result.put(SubbmitOrderValidateService.MAP_MESSAGE, String.format("调用orderPromotionCalculateServices.calculateShoppingCart -->error[message:%s,code:%s]", resultCart.getMessage(),resultCart.getErrorCode()));
			return result;
		}	
		
		InfCart calculateAftercart = resultCart.getObject();
		//没有促销信息 需要重新覆盖信息
		//buildingInfCart(calculateAftercart);
		
		commonUtilService.logInfoObjectToJson("subbmitOrder-->shoppingcart-return",logger, calculateAftercart);
		//1、先验证提交订单页面积分和优惠券是否正确   2、调用价格方法验证总价钱
		result  = validate(calculateAftercart,receiveOrderMainDTO);
		return result;
	}

//	private void buildingInfCart(InfCart calculateAftercart) {
//		List<InfCartItem> cartItemList = calculateAftercart.getCartItemList();
//		for(InfCartItem item : cartItemList){
//			if(item.isValid()){
//				//设置折后单价 = 商品单价
//				item.setPromotionPrice(item.getPrice());
//				//设置商品折扣金额=0
//				item.setAdjust(BigDecimal.ZERO);
//				//设置折后总金额
//				item.setAmount(item.getPrice().multiply(new BigDecimal(item.getCount())));
//			}
//		}
//	}

	@Override
	public Map<String, Object> transBtcOmsReceiveOrderDTO(ReceiveOrderMainDTO receiveOrderMainDTO,
			InfCart calculateAftercart) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 设置默认值
		conifigDefaultValue(receiveOrderMainDTO);
		BtcOmsReceiveOrderDTO bro = new BtcOmsReceiveOrderDTO();
		// 1、组装ordermain
		OrderMainDTO omd = new OrderMainDTO();
		omd = buildBtcOrderMain(receiveOrderMainDTO, calculateAftercart, omd);
		// 2、组装orderSub
		OrderSubDTO osd = new OrderSubDTO();
		osd = buildBtcOrderSubDTO(receiveOrderMainDTO);
		// 3、组装OrderItemDTO
		List<OrderItemDTO> oid = new ArrayList<OrderItemDTO>();
		oid = buildOrderItemDTO(receiveOrderMainDTO, calculateAftercart);
		BtcOmsReceiveOrderDTO bors = new BtcOmsReceiveOrderDTO();
		// 组装BtcOmsReceiveOrderDTO
		bors = buildBtcOmsReceiveOrderDTO(omd, osd, oid);
		commonUtilService.logInfoObjectToJson("subbmitOrder-->transBtcOmsReceiveOrderDTO-ReceiveOrderDTO", logger, bors);
		result.put(orderValidateService.MAP_FLAG, true);
		result.put(orderValidateService.MAP_RESULT, bors);
		result.put(orderValidateService.MAP_MESSAGE, orderValidateService.MESSAGE_SUCCESS);
		return result;

	}
	
	
	
	

	/**组装BtcOmsReceiveOrderDTO
	 * @param omd
	 * @param osd
	 * @param oid
	 * @return
	 */
	private BtcOmsReceiveOrderDTO buildBtcOmsReceiveOrderDTO(OrderMainDTO omd, OrderSubDTO osd, List<OrderItemDTO> oid) {
		BtcOmsReceiveOrderDTO bors = new BtcOmsReceiveOrderDTO();
		List<OrderMainDTO> omDTO = new ArrayList<OrderMainDTO>();
		List<OrderSubDTO> osDTOs = new ArrayList<OrderSubDTO>();
		
		//设置suborder中的订单行
		osd.setOiDTOs(oid);
		//将suborder加入到子订单集合中
		osDTOs.add(osd);
		//设置OrderMainDTO中的subOrder
		omd.setOsDTOs(osDTOs);
		//促销信息 分为整单优惠 item 优惠
		//TODO
		//支付信息 --  支付回调中设置
		//将subOrder加入到OrderMainDTO中
		omDTO.add(omd);
		//设置入参的BtcOmsReceiveOrderDTO
		bors.setOmDTO(omDTO);
		return bors;
	}

	/**3、组装OrderItemDTO
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @return
	 */
	private List<OrderItemDTO> buildOrderItemDTO(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart) {
		List<OrderItemDTO> items =new ArrayList<OrderItemDTO>();
		List<ReceiveOrderItemDTO> receiveOrderItemDTOs = receiveOrderMainDTO.getReceiveOrderItemDTOs();
		List<InfCartItem> cartItemList = calculateAftercart.getCartItemList();
		for(ReceiveOrderItemDTO ri : receiveOrderItemDTOs){
			for(InfCartItem cartItem : cartItemList){
				//商品的id相等，同有一件商品
				if(ri.getSkuId().equals(String.valueOf(cartItem.getSkuId()))){
					//单品
					//判断单品下面是否有赠品
					if("SINGLE".equals(cartItem.getItemType())){
						buildingSingleItem(receiveOrderMainDTO, calculateAftercart, items, ri, cartItem);
					}
					//赠品
					//赠品不会参加促销活动
					if("GIFT".equals(cartItem.getItemType())){
						buildingGiftItem(receiveOrderMainDTO, calculateAftercart, items,ri, cartItem);
					}
					
					//组合商品
					if("BUNDLE".equals(cartItem.getItemType())){
						buildingBundleItem(receiveOrderMainDTO, calculateAftercart, items, ri, cartItem);
					}
					
				}
			}
		}
		return items;
	}

	private void buildingBundleItem(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart,
			List<OrderItemDTO> items, ReceiveOrderItemDTO ri, InfCartItem cartItem) {
		OrderItemDTO oid = settingCommonItemInfo(receiveOrderMainDTO, calculateAftercart, ri, cartItem);
		//产品组是以组的主产品skucode分，先这么实现，待测试更正
		oid.setProductGroup(oid.getSkuNo());
		//添加组合商品
		if(CollectionUtils.isNotEmpty(ri.getBundleItemList())){
			List<ReceiveOrderItemDTO> itemExt = ri.getBundleItemList();
			for(ReceiveOrderItemDTO iex : itemExt){
				//加入绑定商品
				OrderItemDTO oidex =  new OrderItemDTO();
				BeanUtils.copyProperties(iex, oidex);
				productProTranOrderItem(oidex);
				oidex.setProductGroup(oid.getSkuNo());
				//查询赠品信息
				oidex.setOrderItemClass(OrderItemConst.OrderItem_OrderItemClass_SUITE.getCode());
				oidex.setHasGift(Integer.valueOf(OrderItemConst.OrderItem_OrderItemClass_GIFT_NO.getCode()));
				items.add(oidex);
			}
			
		}
		oid.setOrderItemClass(OrderItemConst.OrderItem_OrderItemClass_SUITE.getCode());
		oid.setHasGift(Integer.valueOf(OrderItemConst.OrderItem_OrderItemClass_GIFT_NO.getCode()));
		items.add(oid);
	}

	private void buildingGiftItem(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart,
			List<OrderItemDTO> items, ReceiveOrderItemDTO ri, InfCartItem cartItem) {
		//计算订单行信息
		OrderItemDTO oid = settingCommonItemInfo(receiveOrderMainDTO, calculateAftercart, ri, cartItem);
		oid.setHasGift(Integer.valueOf(OrderMainConst.OrderItem_OrderItemClass_GIFT_NO.getCode()));
		oid.setOrderItemClass(OrderItemConst.OrderItem_OrderItemClass_GIFT.getCode());
		oid.setPromotionCode(cartItem.getPromotionCode());
		items.add(oid);
		//促销活动类型暂时不支持返回
	}

	private void buildingSingleItem(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart,
			List<OrderItemDTO> items, ReceiveOrderItemDTO ri, InfCartItem cartItem) {
		OrderItemDTO oid = settingCommonItemInfo(receiveOrderMainDTO, calculateAftercart, ri, cartItem);
		boolean hasGif = false;
		if(CollectionUtils.isNotEmpty(ri.getBundleItemList())){
			List<ReceiveOrderItemDTO> itemExt = ri.getBundleItemList();
			for(ReceiveOrderItemDTO iex : itemExt){
				//赠品 todo 待确认后增添
				if("GIFT".equals(iex.getItemType())){
					//计算订单行信息
					 OrderItemDTO oidex =  new OrderItemDTO();
					 BeanUtils.copyProperties(iex, oidex);
					 productProTranOrderItem(oidex);
					//查询赠品信息
					oidex.setOrderItemClass(OrderItemConst.OrderItem_OrderItemClass_GIFT.getCode());
					oidex.setHasGift(Integer.valueOf(OrderItemConst.OrderItem_OrderItemClass_GIFT_NO.getCode()));
					items.add(oidex);
					hasGif = true;
				}
			}
			
		}
		//是否有赠品
		oid.setHasGift( hasGif ? Integer.valueOf(OrderItemConst.OrderItem_OrderItemClass_GIFT_YES.getCode()):Integer.valueOf(OrderItemConst.OrderItem_OrderItemClass_GIFT_NO.getCode()));	
		//订单行类别
		oid.setOrderItemClass(OrderItemConst.OrderItem_OrderItemClass_NORM.getCode());
		//******************促销相关
		//含有促销资源
		if(StringUtils.isNotEmpty(cartItem.getPromotionCode())){
			//促销活动类型
			List<InfCartAdjust> itemAdjustList = cartItem.getItemAdjustList();
			String promotionType = itemAdjustList.get(0).getPromotionType();
			oid.setPromoType(promotionType);
			//促销编码
			String promotionCode = cartItem.getPromotionCode();
			oid.setPromotionCode(promotionCode);
			//oid.setPromoTicketMoney(promoTicketMoney);
			//hasGift	订单行是否有赠品
			//TODO
		}
		items.add(oid);
	}

	private OrderItemDTO settingCommonItemInfo(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart,
			ReceiveOrderItemDTO ri, InfCartItem cartItem) {
		//计算订单行信息
		OrderItemDTO oid =  new OrderItemDTO();
		//beans 商品行信息 bean copy
		BeanUtils.copyProperties(ri, oid);
		//查询商品数据库更新信息
		productProTranOrderItem(oid);
		//商品数量
		oid.setSaleNum(String.valueOf(cartItem.getCount()));
		//设置促销信息-行级促销
		List<OrderPromotionDTO> opts = buildPromotions(cartItem.getItemAdjustList(),receiveOrderMainDTO.getMemberNo());
		oid.setOpDTOs(opts);
		//销售单价:折前单价
		oid.setUnitPrice(commonUtilService.bigDecimal2String(cartItem.getPrice()));
		//unitDeductedPrice	商品折后单价
		oid.setUnitDeductedPrice(commonUtilService.bigDecimal2String(cartItem.getPromotionPrice()));
		//couponAmount	优惠券金额 行级别均摊 -->0419 update 在保存订单支付的时候进行统计算 参照 OrderCreateTransAbstract-->saveOrderPay-->orderSplitPayMent.splitPayMent
		//BigDecimal totalPromo = commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPromo());
		//优惠券行级别均摊 --服务接口提供
		//BigDecimal shareTotalPromo = shareCalculate(calculateAftercart, cartItem,  totalPromo);
		//oid.setCouponAmount(commonUtilService.bigDecimal2String(shareTotalPromo));
		//pointDiscountAmount 均摊积分金额
		//BigDecimal pointDiscountAmount = shareCalculate(calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getPointDiscountAmount()));
		//couponAmount	积分抵扣 行级别均摊 -->0419 update 在保存订单支付的时候进行统计算 参照 OrderCreateTransAbstract-->saveOrderPay-->orderSplitPayMent.splitPayMent
		//BigDecimal pointDiscountAmount = subbmitOrderCalculateService.shareCalculate(calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPointAmount()), PROMOTIONLEVEL_POINT_AMOUNT);
		//oid.setPointDiscountAmount(commonUtilService.bigDecimal2String(pointDiscountAmount));
		//sharePoint 均摊积分积分抵扣 行级别均摊 -->0419 update 在保存订单支付的时候进行统计算 参照 OrderCreateTransAbstract-->saveOrderPay-->orderSplitPayMent.splitPayMent
		//BigDecimal sharePoint = shareCalculate(calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPiont()));
		//BigDecimal sharePoint = subbmitOrderCalculateService.shareCalculate(calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPiont()), PROMOTIONLEVEL_POINT);
		//oid.setSharePoint(commonUtilService.bigDecimal2String(sharePoint));
		//unitDiscount	折扣金额	
		oid.setUnitDiscount(commonUtilService.bigDecimal2String(cartItem.getAdjust()));
		//payAmount	行项目应付均摊金额	= 行优惠总额 - 均摊得积分总额 ？-->在保存订单得时候进行行项目得均摊
		BigDecimal payAmount = shareCalculate(calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPayAmount()));
		oid.setPayAmount(commonUtilService.bigDecimal2String(payAmount.setScale(BIGDECIMAL_DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP)));
		//促销活动类型暂时不支持返回
		//oid.setPromotionCode(cartItem.getPromotionCode());
		//增加赠品信息
		//ORDER_ITEM_CLASS 订单行类别：NORM :实体 GIFT:赠品 SUITE: 套件(组合商品) INTEGRAL : 积分
		//折前总金额(销售单价x销售数量)
		oid.setSaleTotalMoney(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
		//折后总后金额(复)
		oid.setSaleTotalMoneyDiscount(cartItem.getAmount());
		return oid;
	}

	
	/**设置订单行级促销信息
	 * @param itemAdjustList
	 * @param oid
	 * @param memberNo
	 */
	private List<OrderPromotionDTO> buildPromotions(List<InfCartAdjust> itemAdjustList, String memberNo) {
		List<OrderPromotionDTO> opts = new ArrayList<OrderPromotionDTO>();
		if(CollectionUtils.isEmpty(itemAdjustList)){
			return opts;
		}else{
			for(InfCartAdjust ic :itemAdjustList ){
				OrderPromotionDTO od =  new OrderPromotionDTO();
				// /**优惠级别**/
				if(PromotionLevel.Promotion_Level_ITEM.getName().equals(ic.getPromotionLevel())){
					od.setPromoLevel(PromotionLevel.Promotion_Level_ITEM.getCode());
				}
				if(PromotionLevel.Promotion_Level_ORDER.getName().equals(ic.getPromotionLevel())){
					od.setPromoLevel(PromotionLevel.Promotion_Level_ORDER.getCode());
				}
				//促销规则编码, 用券没有促销规则编码
				od.setPromoNo(ic.getPromotionCode());
				//促销规则名称 待定--需要促销返回
				//od.setPromoName(promoName);
				//用券(退货不还)，用积分，返券，返积分  促销类型
				od.setPromoType(ic.getPromotionType());
				//返券批次号 暂无
				//od.setTicketBundleNo(ticketBundleNo);
				//券号暂无
				//od.setTicketNo(ticketNo);
				//总计券
				if(ic.getAmount()!=null){
					od.setTicketAmount(commonUtilService.bigDecimal2String(ic.getAmount()));
				}
				
				if(StringUtils.isNotEmpty(ic.getPoint())){
					od.setPointCount(ic.getPoint());
				}
				od.setMemberNo(memberNo);
				od.setPromoName(ic.getPromotionInfo());
				opts.add(od);
			}
		}
		return opts;
		
	}

	/**2、组装orderSub
	 * 自提待定
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @param omd 
	 * @return
	 */
	private OrderSubDTO buildBtcOrderSubDTO(ReceiveOrderMainDTO receiveOrderMainDTO) {
		OrderSubDTO osd = new OrderSubDTO(); 
		RecipientInformationDTO r = receiveOrderMainDTO.getRecipientInformationDTO(); 
		//收件人信息copy
		BeanUtils.copyProperties(r, osd);
		//发票信息copy
		if(OrderMainConst.ORDERINVOICE_INVOICETYPE_NONEED_YES.getCode().equals(receiveOrderMainDTO.getNeedInvoice())){
			InvoiceDTO invoiceDTO = receiveOrderMainDTO.getInvoiceDTO();
			OrderInvoiceDTO oid = new OrderInvoiceDTO();
			BeanUtils.copyProperties(invoiceDTO, oid);
			osd.setOrderInvoice(oid);
		};
		//门店代码
		//分为线下和线上都用一个shopNo字段值，
		osd.setStoreNo(StringUtils.trimToNull(receiveOrderMainDTO.getSaleStoreCode()));	
		//运费 = 运费总额-运费优惠	
		String transportFee =  calculateTransportFee(receiveOrderMainDTO);
		String discountTransport = calculateDiscountTransport(receiveOrderMainDTO);
		BigDecimal totaltransportFee = commonUtilService.string2BigDecimal(transportFee).subtract(commonUtilService.string2BigDecimal(discountTransport));
		osd.setTransportFee(commonUtilService.bigDecimal2String(totaltransportFee));
		osd.setDistributeType(receiveOrderMainDTO.getDistributeType());
		return osd;
	}

	/**计算赠送的积分
	 * @param totalGivePoints
	 * @return
	 */
	private String calculateTotalGivePoints(ReceiveOrderMainDTO receiveOrderMainDTO) {
		//orderItemService.getTotalPoint(orderMain, orderItems)
		return null;
	}

	/**
	 * 订单实际应付金额总计 (订单总额-积分抵扣-运费运费-运费优惠)
	 * 
	 * @param totalOrderAmount
	 * @param transportFee
	 * @param discountTransport
	 * @param pointDiscountAmount 
	 * @return
	 */
	private String calculateTotalPayAmount(String totalOrderAmount, String transportFee, String discountTransport, String pointDiscountAmount) {
		BigDecimal totalPayAmount = commonUtilService.string2BigDecimal(totalOrderAmount)
				.subtract(commonUtilService.string2BigDecimal(transportFee))
				.subtract(commonUtilService.string2BigDecimal(discountTransport))
				.subtract(commonUtilService.string2BigDecimal(pointDiscountAmount));
		return commonUtilService.bigDecimal2String(totalPayAmount);
	}

	/**计算运费优惠
	 * @param receiveOrderMainDTO
	 * @return
	 */
	private String calculateDiscountTransport(ReceiveOrderMainDTO receiveOrderMainDTO) {
		return receiveOrderMainDTO.getDiscountTransport();
	}

	/**计算运费
	 * @param receiveOrderMainDTO
	 * @return
	 */
	private String calculateTransportFee(ReceiveOrderMainDTO receiveOrderMainDTO) {
		//运费目前默认设置为0元
		return receiveOrderMainDTO.getTransportFee();
	}

	/**计算优惠券总金额 
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @return
	 */
	private String calculatetotalPromo(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart) {
		//Map<String, Object> result =  orderValidateService.validateOrderCouponCode(receiveOrderMainDTO);
		//String resultValue =  null;
		//if(orderValidateService.getValidateValue(result)){
		//resultValue = orderValidateService.getResultValue(result);
		//}
		if(!isOpenValidate()){
			return receiveOrderMainDTO.getTotalPromo().toString();
		}
		return calculateAftercart.getCouponAmount().toString();
	}

	/** 
	 *  订单金额总计(包含积分支付 部分,包含了优惠卷的部分)20180323
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @return
	 */
	private String calculateTotalOrderAmount(InfCart calculateAftercart,String pointDiscountAmount) {
		BigDecimal totalOrderAmount = new BigDecimal(0);
		BigDecimal totalAmount = calculateAftercart.getTotalAmount();
		//totalOrderAmount = totalAmount.subtract(commonUtilService.string2BigDecimal(pointDiscountAmount));
		return commonUtilService.bigDecimal2String(totalAmount);
	}

	/**折扣总额
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @return
	 */
	private String calculateDiscountTotal(InfCart calculateAftercart) {
		String totalOrderAmount = commonUtilService.bigDecimal2String(calculateAftercart.getTotalAdjust());
		return totalOrderAmount;
	}

	/**计算商品总价
	 * @param receiveOrderMainDTO
	 * @param calculateAftercart
	 * @return
	 */
	private String calculateTotalProductCount(ReceiveOrderMainDTO receiveOrderMainDTO, InfCart calculateAftercart) {
//		List<InfCartItem> cartItemList =  calculateAftercart.getCartItemList();
//		BigDecimal totalProductPrice = new BigDecimal(0);
//		for(InfCartItem it : cartItemList){
//			totalProductPrice = it.getPrice().multiply(commonUtilService.string2BigDecimal(String.valueOf(it.getCount())));
//		}
		return commonUtilService.bigDecimal2String(calculateAftercart.getTotalProduct());
	}

	/**计算商品总数量
	 * @param receiveOrderMainDTO
	 * @return
	 */
	private Integer calculateTotalProductCount(ReceiveOrderMainDTO receiveOrderMainDTO) {
//		List<ReceiveOrderItemDTO> itemDTOs   = receiveOrderMainDTO.getReceiveOrderItemDTOs();
//		Integer i = 0;
//		for(ReceiveOrderItemDTO r : itemDTOs){
//			i = i + r.getCount();
//		}
		return Integer.valueOf(receiveOrderMainDTO.getTotalProductCount());
	}

	private Map<String,Object> valiatePointCoupon(ReceiveOrderMainDTO receiveOrderMainDTO) {
		Map<String,Object> result = null;
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		boolean b4 = false;
		String message = "";
		boolean flag = true;
		result = orderValidateService.validateOrderPoint(receiveOrderMainDTO.getMemberNo(), receiveOrderMainDTO.getTotalPoint());
		 //1、校验积分是否有效
		 b1 = orderValidateService.getValidateValue(result);
		 //错误返回错误信息
		 if(!b1){
			 flag = b1;
			 message = message + ";" + orderValidateService.getResultMessage(result);
			 
		 }
		 //2、校验积分兑换的金额是否正确
		 result = orderValidateService.validateOrederPointTranPayAmount(receiveOrderMainDTO.getMemberNo(), receiveOrderMainDTO.getTotalPoint(), receiveOrderMainDTO.getTotalPointAmount());
		 b2 = orderValidateService.getValidateValue(result);
		 if(!b2){
			 flag = b2;
			 message = message + ";" + orderValidateService.getResultMessage(result);
		 }
		//3、校验证优惠劵是否有效
		//result = orderValidateService.validateOrderCouponCode(receiveOrderMainDTO);
		//b3 = orderValidateService.getValidateValue(result);
		//if(b3){
		//flag = b3;
		//message = message + ";" + orderValidateService.getResultMessage(result);
		//}
		//4、校验优惠劵金额是否有效
		//result = orderValidateService.validateOrderCouponCodePayAmount(receiveOrderMainDTO);
		//b4 =orderValidateService.getValidateValue(orderValidateService.validateOrderCouponCodePayAmount(receiveOrderMainDTO));
		//if(b4){
		//	flag = b4;
		//	message = message + ";" + orderValidateService.getResultMessage(result);
		//}
		//5、验证通过
		result = orderValidateService.buildResult(flag, null,message);
		return result;
	}
	
	
	
	/**
	 * @param calculateAftercart
	 * @param receiveOrderMainDTO
	 * @return  验证成功 返回map result 结果存的是calculateAftercart
	 */
	private Map<String,Object> validate(InfCart calculateAftercart, ReceiveOrderMainDTO receiveOrderMainDTO) {
		Map<String,Object> result =  new HashMap<String,Object>();
		//1、先验证提交订单页面积分和优惠券是否正确
		Map<String,Object> b = valiatePointCoupon(receiveOrderMainDTO);
		result = b;
		// 2、调用价格方法验证总价钱
		if(orderValidateService.getValidateValue(result)){
			//订单支付总价=订单总价钱-积分价格
			BigDecimal orderAmount = calculateAftercart.getTotalAmount();
			BigDecimal totalPayAmount = orderAmount.subtract(commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPointAmount()));
			if(0 == (commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPayAmount()).compareTo(totalPayAmount))){
				return result =  orderValidateService.buildResult(true, calculateAftercart, orderValidateService.MESSAGE_SUCCESS);
			
			}else{
				//失败信息组装
				result =  orderValidateService.buildResult(false, 
						  calculateAftercart, 
						  String.format("%s ： [计算总金额：%s][前端金额:%s]",  
								  		orderValidateService.MESSAGE_FAIL,commonUtilService.bigDecimal2String(totalPayAmount),receiveOrderMainDTO.getTotalPayAmount()));
			}
		}
		
		return result ;
	}
	
	// 设置绑定商品
	private void settingBundleItemList(ReceiveOrderItemDTO r) {
		List<InfCartItem> bundleItemList = new ArrayList<InfCartItem>();
		List<ReceiveOrderItemDTO> bundles = r.getBundleItemList();
		if (CollectionUtils.isNotEmpty(bundles)) {
			InfCartItem item1 = new InfCartItem();
			for (ReceiveOrderItemDTO rd : bundles) {
				BeanUtils.copyProperties(rd, item1);
				receiveOrderItemCopy2CartItem(rd, item1);
				bundleItemList.add(item1);
			}
		}
	}
	
	/**计算商品总价 运费，运费优惠第一迭代都是0
	 * @param ReceiveOrderMainDTO
	 * @return
	 */
	@Override
	public ReceiveOrderMainDTO conifigDefaultValue(ReceiveOrderMainDTO t) {
		String transportFee = t.getTransportFee();
		String discountTransport = t.getDiscountTransport();
		if(StringUtils.isEmpty(transportFee)){
			t.setTransportFee(DEFAULT_BLANTA_MOUNT);
		}
		if(StringUtils.isEmpty(discountTransport)){
			t.setDiscountTransport(DEFAULT_BLANTA_MOUNT);;
		}
		
		return t;
	}

	/**1、组装ordermain
	 * @param ro
	 * @param calculateAftercart
	 * @param omd
	 * @return 
	 */
	private OrderMainDTO buildBtcOrderMain(ReceiveOrderMainDTO ro, InfCart calculateAftercart,OrderMainDTO omd) {
		//1、组装ordermain
		//2、bean复制
		BeanUtils.copyProperties(ro, omd);
		omd.setNeedInvoice(ro.getNeedInvoice());
		omd.setSalesclerkNo(ro.getSalespersonNo());
		// TODO 业绩归属门店如何计算
		omd.setPerformStoreCode(ro.getShopNo());
		omd.setSaleStoreCode(ro.getSaleStoreCode());
		//价格计算
		//商品数量总计
		Integer totalProductCount = calculateTotalProductCount(ro);
		omd.setTotalProductCount(totalProductCount);
		//折前商品金额统计 = 购物车里得商品总计
		String  totalProductPrice = calculateTotalProductCount(ro,calculateAftercart);
		omd.setTotalProductPrice(totalProductPrice);
		//折扣总额
		String discountTotal = calculateDiscountTotal(calculateAftercart);
		omd.setDiscountTotal(discountTotal);
		//积分抵现金额
		String pointDiscountAmount = calculatePointDiscountAmount(ro);
		//优惠卷总额 - 直接从calculateAftercart取值 -- 待购物车返回用卷金额 couponAmount
		String totalPromo = calculatetotalPromo(ro,calculateAftercart);
		
		omd.setTotalPromo(totalPromo);
		//订单金额总计(包含了优惠卷的部分,积分优惠的部分)
		String totalOrderAmount = null;
		totalOrderAmount = calculateTotalOrderAmount(calculateAftercart,pointDiscountAmount);
		omd.setTotalOrderAmount(totalOrderAmount);
		//运费优惠
		String discountTransport = calculateDiscountTransport(ro);
		omd.setDiscountTransport(discountTransport);
		//运费总额 | 物流费用
		String transportFee = calculateTransportFee(ro);
		omd.setTransportFee(transportFee);
		//订单实际应付金额总计 = 订单金额总计(包含扣除优惠券得金额？) - 运费总额 + 运费优惠 - 低分抵扣金额
		String totalPayAmount = calculateTotalPayAmount(totalOrderAmount,transportFee,discountTransport,pointDiscountAmount);
		omd.setTotalPayAmount(totalPayAmount);
		//赠送总积分 = 创建订单后    最后调用orderItemService.getTotalPoint(orderMain, orderItems)更新
		//String totalGivePoints =  calculateTotalGivePoints(receiveOrderMainDTO);
		//设置促销信息
		List<OrderPromotionDTO> opDTOs = buildPromotions(calculateAftercart.getCartAdjustList(), ro.getMemberNo());
		omd.setOpDTOs(opDTOs);
		//设置支付信息 ，订单创建的时候只记录积分支付和优惠券支付信息，其他的待订单接口返回记录
		String couponsNo = ro.getCouponsNo();
		List<OrderPayDTO> orderPayDTOs = buildOrderPay(omd,couponsNo);
		omd.setOrderPayDTOs(orderPayDTOs);
		//传递购物车计算后参数
		omd.setInfCart(calculateAftercart);
		//区域编码与接口字段名不一致需要单独映射
		omd.setRegionCode(ro.getZoneId());
		//总共用积分设置赋值
		//omd.setTotalPoint(new BigDecimal(ro.getTotalPoint()));
		return  omd;
	}
	
	private List<OrderPayDTO> buildOrderPay(OrderMainDTO omd, String couponsNo) {
		List<OrderPayDTO> pays = new ArrayList<OrderPayDTO>();
		
		
		//优惠卷支付 现阶段没有orderId和orderNo待校验成功订单保存成功后赋值
		if(StringUtils.isNotEmpty(omd.getTotalPromo()) && StringUtils.isNotEmpty(couponsNo) ){
			OrderPayDTO op =  new OrderPayDTO();
			op.setPayAmount(omd.getTotalPromo());
			op.setPayCode(PayMode.COUPON.getCode());
			op.setPayName(PayMode.COUPON.getName());
			op.setCardNo(couponsNo);
			pays.add(op);
		}
		//积分支付 现阶段没有orderId和orderNo待校验成功订单保存成功后赋值
		if(StringUtils.isNotEmpty(omd.getTotalPointAmount()) && new BigDecimal(omd.getTotalPointAmount()).compareTo(BigDecimal.ZERO) > 0){
			OrderPayDTO op =  new OrderPayDTO();
			op.setPayAmount(omd.getTotalPointAmount());
			op.setPayCode(PayMode.INTEGRAL.getCode());
			op.setPayName(PayMode.INTEGRAL.getName());
			pays.add(op);
		}
		//支付的积分 现阶段没有orderId和orderNo待校验成功订单保存成功后赋值
		if(StringUtils.isNotEmpty(omd.getTotalPoint()) && new BigDecimal(omd.getTotalPoint()).compareTo(BigDecimal.ZERO) > 0){
			OrderPayDTO op =  new OrderPayDTO();
			op.setPayAmount(omd.getTotalPoint());
			op.setPayCode(PayMode.POINT.getCode());
			op.setPayName(PayMode.POINT.getName());
			pays.add(op);
		}
		//调用接口的支付
		OrderPayDTO op =  new OrderPayDTO();
		op.setPayAmount(omd.getTotalPayAmount());
		op.setPayCode(PayMode.WAIT.getCode());
		op.setPayName(PayMode.WAIT.getName());
		pays.add(op);
		return pays;
	}

	/**积分抵现金额
	 * @param receiveOrderMainDTO
	 * @return
	 */
	private String calculatePointDiscountAmount(ReceiveOrderMainDTO receiveOrderMainDTO) {
		if(StringUtils.isEmpty(receiveOrderMainDTO.getTotalPointAmount())){
			return DEFAULT_BLANTA_MOUNT;
		}
		return receiveOrderMainDTO.getTotalPointAmount();
	}

	//均摊
	/**均摊
	 * @param calculateAftercart
	 * @param cartItem
	 * @param target
	 * @return
	 */
	private BigDecimal shareCalculate(InfCart calculateAftercart,InfCartItem cartItem,BigDecimal target){
		//被均摊的金额如果=0。00 怎返回0.00
		if(target.compareTo(new BigDecimal(DEFAULT_BLANTA_MOUNT))==0){
			return new BigDecimal(DEFAULT_BLANTA_MOUNT);
		}
		BigDecimal amount = null;
		amount = cartItem.getAmount()==null?cartItem.getPrice():cartItem.getAmount();
		//BigDecimal base =amount.divide(calculateAftercart.getTotalAmount());
		BigDecimal base = amount.divide(calculateAftercart.getTotalAmount(), BIGDECIMAL_DEFAULT_SCALE, BIGDECIMAL_DEFAULT_ROUNDINGMODE);
		return target.multiply(base);
		
	}
	@Override
	public Map<String, Object> handleSubbmitOrder(ReceiveOrderMainDTO receiveOrderMainDTO) {
		 Map<String,Object> validateResult = validateSubbmitOrder(receiveOrderMainDTO);
		 commonUtilService.logInfoObjectToJson("subbmitOrder-->handleSubbmitOrder-validateResult", logger, validateResult);
		 //
		 if(isOpenValidate()){
			 if(orderValidateService.getValidateValue(validateResult)){
				 InfCart calculateAftercart = (InfCart) orderValidateService.getResultValue(validateResult);
				 Map<String,Object> result = transBtcOmsReceiveOrderDTO(receiveOrderMainDTO, calculateAftercart);
				 commonUtilService.logInfoObjectToJson("subbmitOrder-->handleSubbmitOrder-result", logger, result);
				 return result;
			 }
		 //关闭验证 	 
		 }else{
			 if(!orderValidateService.getValidateValue(validateResult)){
				 return validateResult;
			 }
			 InfCart calculateAftercart = (InfCart) orderValidateService.getResultValue(validateResult);
			 if(calculateAftercart.getTotalAmount().compareTo(BigDecimal.ZERO)==0){
				 calculateAftercart.setTotalAmount(new BigDecimal("1.00"));
			 }
			 Map<String,Object> result = transBtcOmsReceiveOrderDTO(receiveOrderMainDTO, calculateAftercart);
			 commonUtilService.logInfoObjectToJson("subbmitOrder-->handleSubbmitOrder-result", logger, result);
			 return result;
		 }
		 
		return validateResult;
	}
	

	private boolean isOpenValidate(){
		if(SUBBMIT_VALIDATE_SWITCH_OPEN.equals(switchStatus)){
			return true;
		}
		//默认也为打开
		if(StringUtils.isEmpty(switchStatus)){
			return true;
		}
		return false;
		
	}

	
	
}
