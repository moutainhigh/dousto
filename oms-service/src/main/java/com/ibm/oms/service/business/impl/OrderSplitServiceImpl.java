package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.OrderSplitDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderSplitService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.util.DateUtils;

@Service("orderSplitService")
public class OrderSplitServiceImpl implements OrderSplitService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderMainService orderMainService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired 
	OrderItemService orderItemService;
	@Autowired 
	OrderNoService orderNoService;
	@Autowired
	CommonUtilService commonUtilService;
	@Autowired
	OrderPromotionService orderPromotionService;
	@Autowired
	@Qualifier("OrderCreateSplit")
	OrderCreateService orderCreateService;
	
	
	
	
	@Override
	public boolean handleOrderSplit(String subOrderNo, String splitType,String assignSku) throws Exception {
		List<OrderItem> orderItems = getOrderItemByOrderSubNo(subOrderNo);
		List<OrderSplitDTO> orderSplitDTOs = dosplit(orderItems, splitType, assignSku);
		for(OrderSplitDTO dto : orderSplitDTOs){
			createSplitNewOrder(dto);
		}
		//拆单成功后更新原单状态为已拆单
		OrderMain om = getOrderMainByOrderSubNo(subOrderNo);
		om.setIsSplit(Integer.valueOf(OrderMainConst.ORDERMAIN_IS_SPLIT_YES.getCode()));
		orderMainService.update(om);
		return true;
	}
	/**
	 * 根据子订单号获取OrderItem
	 * @param subOrderNo
	 * @return
	 */
	private List<OrderItem> getOrderItemByOrderSubNo(String orderSubNo){
		List<OrderItem> orderItems = orderItemService.getByOrdeSubNo(orderSubNo);
		return orderItems;
	}
	//根据sku分类拆分 
	//1、获取不同的sku，
	//2、创建不同的住订单号
	//3、确认每个新单内容
	private List<OrderSplitDTO> dosplit(List<OrderItem> items, String splitType, String assignSku) {
		List<OrderSplitDTO> orderSplitList = new ArrayList<OrderSplitDTO>();
		if(SPLITTYPE_SKU.equals(splitType)){
			//订单里每个item的sku不可重复
			//Map<String, OrderSplitDTO> orderSplitMap = new HashMap<String, OrderSplitDTO>();
			for (OrderItem item : items) {
				OrderSplitDTO orderSplitDTO = new OrderSplitDTO();
				List<Long> orderItemids = new ArrayList<Long>();
				orderItemids.add(item.getId());
				orderSplitDTO.setOrderItemids(orderItemids);
				orderSplitDTO.setOrderMainId(item.getIdOrder());
				orderSplitDTO.setOrderSubId(item.getIdOrderSub());
				orderSplitList.add(orderSplitDTO);
			}
			return orderSplitList;
		}
		
		if(StringUtils.isEmpty(assignSku)){
			logger.info("assignSku is null items:{}" + items.toString());
			return orderSplitList;
		}
		//按照指定sku拆分一单，其余的算作一单
		List<Long> assignOrderItemid = new ArrayList<Long>();
		List<Long> otherOrderItemid = new ArrayList<Long>();
		for (OrderItem item : items){
			//寻找指定sku对应的orderItemid
			if(item.getSkuNo().equals(assignSku)){
				assignOrderItemid.add(item.getId());
				continue;
			}
			otherOrderItemid.add(item.getId());
			
		}
		
		OrderSplitDTO dto1 = new OrderSplitDTO();
		dto1.setOrderItemids(otherOrderItemid);
		dto1.setOrderMainId(items.get(0).getIdOrder());
		dto1.setOrderSubId(items.get(0).getIdOrderSub());
		orderSplitList.add(dto1);
		
		OrderSplitDTO dto2 = new OrderSplitDTO();
		dto2.setOrderItemids(otherOrderItemid);
		dto2.setOrderMainId(items.get(0).getIdOrder());
		dto2.setOrderSubId(items.get(0).getIdOrderSub());
		orderSplitList.add(dto2);
		
		return orderSplitList;
	}
	
	
	
	private boolean createSplitNewOrder(OrderSplitDTO dto){
		//1、保存主订单
		//2、写子订单
		//3、以及订单item
		OrderMain omOld =  orderMainService.get(dto.getOrderMainId());
		OrderMain om = new OrderMain();
		//重新计算ordermain
		//1、生成新的orderMainid
		om.setStatusTotal(OrderStatus.ORDER_CREATING.getCode());
		BeanUtils.copyProperties(omOld, om); 
		//根据item重新计算
		//查找需要计算的item的集合
		//1、商品重量
		List<OrderItem> items =  new ArrayList<OrderItem>();
		for(Long itemid : dto.getOrderItemids()){
			items.add(orderItemService.get(itemid));
		}
		//商品重量
		BigDecimal totalWeight = new BigDecimal(0);
		//商品总价,折前
		BigDecimal totalProductPrice = new BigDecimal(0);;
		//总价折扣优惠总金额
		BigDecimal totaldiscount = new BigDecimal(0);;
		//订单用券金额
		BigDecimal totalPromo = new BigDecimal(0);
		//订单用积分
		BigDecimal totalPiont = new BigDecimal(0);
		//订单用积分金额  -- 待定
		BigDecimal totalPiontAmount = new BigDecimal(0);
		//支付金额 
		BigDecimal totalPayAmount = new BigDecimal(0);
		//赠送总积分
		BigDecimal totalGivePoints = new BigDecimal(0);
		
		for(OrderItem item : items ){
			//销售数量
			BigDecimal saleNum = commonUtilService.string2BigDecimal(commonUtilService.Long2Str(item.getSaleNum()));
			BigDecimal weight = item.getWeight();
			totalWeight = totalWeight.add(weight.multiply(saleNum));
			//销售单价 折扣前的
			BigDecimal productPrice = item.getUnitPrice();
			totalProductPrice = totalProductPrice.add(productPrice.multiply(saleNum));
			//totaldiscount
			//订单行级折扣
			BigDecimal itemDiscoun = item.getItemDiscount();
			//订单行应付金额
			BigDecimal payAmount = item.getPayAmount();
			//总价折扣优惠总金额 = 商品总价-（订单行应付金额-订单行级折扣）
			totaldiscount = totaldiscount.add(totalProductPrice.subtract(payAmount.subtract(itemDiscoun)));
			//订单用券金额
			totalPromo = totalPromo.add(item.getCouponAmount());
			//订单用积分
			totalPiont = totalPiont.add(item.getSharePoint());
			//支付金额 = 订单行应付金额-订单行级折扣
			totalPayAmount = totalPayAmount.add(payAmount.subtract(itemDiscoun));
			
		}
		//更新内容
		om.setWeight(totalWeight);
		om.setTotalProductPrice(totalProductPrice);
		om.setDiscountTotal(totaldiscount);
		om.setTotalPromo(totalPromo);
		om.setTotalPoint(totalPiont);
		om.setTotalPayAmount(totalPayAmount);
		//设置父id和父orderNo
		om.setOrderNoP(omOld.getOrderNo());
		om.setIdOrderP(omOld.getId());
		//设置订单为分单产生的新订单拆分单 2
		om.setIsSplit(Integer.valueOf(OrderMainConst.ORDERMAIN_IS_SPLIT_NEW_ORDER.getCode()));
		//设置订单审核状态
		om.setStatusConfirm(omOld.getStatusConfirm());
		//设置订单支付状态
		om.setStatusPay(omOld.getStatusPay());
		//设置订单总状态
		om.setStatusTotal(omOld.getStatusTotal());
		//日结算时间	
		om.setBalanceDate(omOld.getBalanceDate());
		//获取子订单内容
		OrderSub os = orderSubService.get(dto.getOrderSubId());
		OrderSub nos = new OrderSub();
		BeanUtils.copyProperties(os, nos);
		//组建OrderItem
		List<OrderItem> orderItems =  new ArrayList<OrderItem>();
		for( Long orderItemId : dto.getOrderItemids()){
			OrderItem orderItem = orderItemService.get(orderItemId);
			orderItems.add(orderItem);
		}
		BtcOmsReceiveOrderDTO btcOmsReceiveOrderDTO =  transferReceiveOrderDTO(om,nos,orderItems);
		orderCreateService.createOrder(btcOmsReceiveOrderDTO);
		return true;
	}


	private BtcOmsReceiveOrderDTO transferReceiveOrderDTO(OrderMain om, OrderSub nos, List<OrderItem> orderItems) {
		BtcOmsReceiveOrderDTO receiveOrderDTO =  new BtcOmsReceiveOrderDTO();
		OrderMainDTO orderMainDTO =  new OrderMainDTO();
		List<OrderMainDTO> orderMainDTOs = new ArrayList<OrderMainDTO>();
		//beancopy
		BeanUtils.copyProperties(om, orderMainDTO);
		//目前E3的订单状态是已支付
		om.setStatusTotal(OrderStatus.Order_PayStatus_Success.getCode());
		//设置父orderNo
		orderMainDTO.setOrderNoP(om.getOrderNo());
		//设置父orderid
		orderMainDTO.setIdOrderP(om.getId());
        //运费
        orderMainDTO.setDiscountTransport(commonUtilService.bigDecimal2String(om.getDiscountTotal()));
        orderMainDTO.setNeedConfirm(commonUtilService.Long2Str(om.getNeedConfirm()));
        orderMainDTO.setNeedInvoice(commonUtilService.Long2Str(om.getNeedInvoice()));
        orderMainDTO.setTotalGivePoints(commonUtilService.bigDecimal2String(om.getTotalGivePoints()));
        orderMainDTO.setTotalPayAmount(commonUtilService.bigDecimal2String(om.getTotalPayAmount()));
        orderMainDTO.setTotalProductPrice(commonUtilService.bigDecimal2String(om.getTotalProductPrice()));
        orderMainDTO.setTotalPromo(commonUtilService.bigDecimal2String(om.getTotalPromo()));
        orderMainDTO.setTransportFee(commonUtilService.bigDecimal2String(om.getTransportFee()));
        orderMainDTO.setWeight(commonUtilService.bigDecimal2String(om.getWeight()));
        orderMainDTO.setIfShowPrice(commonUtilService.Long2Str(om.getIfShowPrice()));
        orderMainDTO.setDiscountTotal(commonUtilService.bigDecimal2String(om.getDiscountTotal()));
        
        //构建orderSubDTO
        List<OrderSubDTO> osDTOs = new ArrayList<OrderSubDTO>();
        OrderSubDTO osi = buildOrderSubDTO(nos, orderItems);
        osDTOs.add(osi);
        orderMainDTO.setOsDTOs(osDTOs);
        //订单创建时间
        orderMainDTO.setOrderTime(DateUtils.formatGeneralDateTimeFormat(new Date()));
        //是否是货到付款
        orderMainDTO.setIfPayOnArrival(commonUtilService.Long2Str(om.getIfPayOnArrival()));
        //vip会员等级
        if(StringUtils.isEmpty(om.getMemberCardLevel())){
        	 orderMainDTO.setMemberVipCardLevel(MEMBERVIPCARDLEVEL_DEFAULT);
        }else{
        	 orderMainDTO.setMemberVipCardLevel(om.getMemberCardLevel());
        }
        //构建订单级促销 
        //buildOrderPromotion(om, orderMainDTO);
        //订单支付 -- 拆单暂时不拆订单支付
        orderMainDTOs.add(orderMainDTO);
        receiveOrderDTO.setOmDTO(orderMainDTOs);
        return receiveOrderDTO;
	}


	private OrderSubDTO buildOrderSubDTO(OrderSub nos, List<OrderItem> orderItems) {
		OrderSubDTO osi = new OrderSubDTO();
        BeanUtils.copyProperties(nos, osi);
        osi.setHopeArrivalTime(commonUtilService.Long2Str(nos.getHopeArrivalTime()));
        osi.setTransportFee(commonUtilService.bigDecimal2String(nos.getTransportFee()));
        //发票信息的构建
        //订单项的构建
        List<OrderItemDTO> oiDTOs = new ArrayList<OrderItemDTO>();
        for(OrderItem orderItem : orderItems ){
        	OrderItemDTO orderItemDTO = new OrderItemDTO();
        	BeanUtils.copyProperties(orderItem, orderItemDTO);
        	// 折前单价
            String unitPrice = commonUtilService.bigDecimal2String(orderItem.getUnitPrice());
            orderItemDTO.setUnitPrice(unitPrice);
            // 单件折后价
            String unitDeductedPrice = commonUtilService.bigDecimal2String(orderItem.getUnitDeductedPrice());
            orderItemDTO.setUnitDeductedPrice(unitDeductedPrice);
            String saleNum = commonUtilService.Long2Str(orderItem.getSaleNum());
            orderItemDTO.setSaleNum(saleNum);
            //BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
            // 单件购物券金额:B2C算好
            orderItemDTO.setCouponAmount(commonUtilService.bigDecimal2String(orderItem.getCouponAmount()));
            // 使用优惠券金额的汇总，作为发票打印里"折扣"部分
            orderItemDTO.setCouponTotalMoney(commonUtilService.bigDecimal2String(orderItem.getCouponTotalMoney()));
            // 行折扣金额
            String itemDiscount = commonUtilService.bigDecimal2String(orderItem.getItemDiscount());
            orderItemDTO.setItemDiscount(itemDiscount);
            // 折扣总金额，按比例算出
            // oi.setDiscountTotalMoney(om.getDiscountOrder().multiply(ratio).add(itemDiscount));
            orderItemDTO.setHasGift(orderItem.getHasGift());
            // 订单行应付金额:折后总价
            orderItemDTO.setPayAmount(commonUtilService.bigDecimal2String(orderItem.getPayAmount()));
            // 返券总金额:该行返券金额的汇总
            orderItemDTO.setPromoTicketMoney(commonUtilService.bigDecimal2String(orderItem.getPromoTicketMoney()));
            // 折后单价,此单价为用户实际支付使用的单价，退款的参考
            orderItemDTO.setUnitDeductedPrice(commonUtilService.bigDecimal2String(orderItem.getUnitDeductedPrice()));
            // 销售单件折扣:B2C算好
            orderItemDTO.setUnitDiscount(commonUtilService.bigDecimal2String(orderItem.getUnitDiscount()));
            orderItemDTO.setWeight(commonUtilService.bigDecimal2String(orderItem.getWeight()));
            orderItemDTO.setPayAmount(commonUtilService.bigDecimal2String(orderItem.getPayAmount()));
            orderItemDTO.setProductPoint(commonUtilService.bigDecimal2String(orderItem.getProductPoint()));
            // /** 是否色码款商品, 0不含商品扩展属性，1包含扩展属性 **/
            orderItemDTO.setProductPropertyFlag(commonUtilService.Long2Str(orderItem.getProductPropertyFlag()));
            oiDTOs.add(orderItemDTO);
            //构建订单行级别List<OrderPromotionDTO>
            //buildOrderItemPromotion(orderItem);
            osi.setOiDTOs(oiDTOs);
        }
		return osi;
	}


	private void buildOrderItemPromotion(OrderItem orderItem) {
		List<OrderPromotion>  OrderItemPromotions = orderPromotionService.findByOrderNoAndLevel(orderItem.getOrderNo(), CommonConst.OrderPromotion_Promolevel_Item.getCode());
		List<OrderPromotionDTO> opsDTO = new ArrayList<OrderPromotionDTO>();
		for(OrderPromotion orderPromotion : OrderItemPromotions){
			OrderPromotionDTO opDTO = new OrderPromotionDTO();
			BeanUtils.copyProperties(orderPromotion,opDTO);
			opDTO.setPointCount(commonUtilService.bigDecimal2String(orderPromotion.getPointCount()));
		    opDTO.setTicketAmount(commonUtilService.bigDecimal2String(orderPromotion.getTicketAmount()));
		    opsDTO.add(opDTO);
		}
	}


	private void buildOrderPromotion(OrderMain om, OrderMainDTO orderMainDTO) {
		List<OrderPromotion>  orderPromotions = orderPromotionService.findByOrderNoAndLevel(om.getOrderNo(), CommonConst.OrderPromotion_Promolevel_Order.getCode());
        List<OrderPromotionDTO> opsOrderDTO =  new ArrayList<OrderPromotionDTO>();
        for(OrderPromotion orderPromotion : orderPromotions){
        	OrderPromotionDTO opDTO = new OrderPromotionDTO();
        	BeanUtils.copyProperties(orderPromotion,opDTO);
        	opDTO.setPointCount(commonUtilService.bigDecimal2String(orderPromotion.getPointCount()));
            opDTO.setTicketAmount(commonUtilService.bigDecimal2String(orderPromotion.getTicketAmount()));
            opsOrderDTO.add(opDTO);
        }
        orderMainDTO.setOpDTOs(opsOrderDTO);
	}
	/**
	 * 根据子订单号获取OrderMain
	 * @param subOrderNo
	 * @return
	 */
	private OrderMain getOrderMainByOrderSubNo(String orderSubNo){
		
		return orderMainService.getByOrderSubNo(orderSubNo);
	}

}
