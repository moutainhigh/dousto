package com.ibm.sc.oms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.order.create.ReceiveOrderClientDTO;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.OrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPayDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.sc.util.CommonUtil;
import com.ibm.sc.util.DateUtils;

public class GenerateBtcOmsReceiveOrderDTOJson {
	static GenerateBtcOmsReceiveOrderDTOJson rd = new GenerateBtcOmsReceiveOrderDTOJson();
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		BtcOmsReceiveOrderDTO bd = new BtcOmsReceiveOrderDTO();
		bd.setBatchNum("test-201801018-SHO14041010900");
		List<OrderMainDTO> mainDTOs = new ArrayList<OrderMainDTO>();
		bd.setOmDTO(mainDTOs);
		OrderMainDTO mainDTO = buildOrdermainDTO();
		
		mainDTOs.add(mainDTO);
		bd.setOmDTO(mainDTOs);
		ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
        	mapper.setSerializationInclusion(Include.NON_EMPTY);  
            itemSnapshotStr = mapper.writeValueAsString(bd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);
        ReceiveOrderClientDTO rc = mapper.readValue(itemSnapshotStr.trim(), ReceiveOrderClientDTO.class);
	}
	
	public static BtcOmsReceiveOrderDTO buildBtcOmsReceiveOrderDTO(){
		BtcOmsReceiveOrderDTO bd = new BtcOmsReceiveOrderDTO();
		bd.setBatchNum("test-201801218-SHO140420110199");
		List<OrderMainDTO> mainDTOs = new ArrayList<OrderMainDTO>();
		bd.setOmDTO(mainDTOs);
		OrderMainDTO mainDTO = buildOrdermainDTO();
		
		mainDTOs.add(mainDTO);
		bd.setOmDTO(mainDTOs);
		ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
            itemSnapshotStr = mapper.writeValueAsString(bd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);
        return bd;
	}
	
		
	public static OrderMainDTO buildOrdermainDTO() {
		OrderMain om;
		OrderSub sub;
		int i = 1;
		OrderMainDTO m = new OrderMainDTO();
		//代客下单
		m.setOrderType(OrderMainConst.OrderMain_OrderType_Valet.getCode());
		//m.setOrderSource(OrderMainConst.OrderMain_Ordersource_LS.getCode());
		m.setOrderSource(OrderMainConst.OrderMain_Ordersource_WX.getCode());
		m.setMerchantType("门店商家");
		m.setMerchantNo(objectTOString(i) + "test" + new Date().getTime());
		m.setIfShowPrice("0");
		m.setNeedConfirm("0");
		m.setOrderTime(DateUtils.formatGeneralDateTimeFormat(new Date()));
		m.setWeight("1.05");
		// 商品总价,折前
		m.setTotalProductPrice("105.00");
		// 总价折扣优惠总金额（订单级折扣优惠+单品级折扣优惠）
		m.setDiscountTotal("0.00");
		m.setTotalPromo("0.00");
		m.setTotalPayAmount("105.00");
		m.setTotalGivePoints("0");
		m.setIfPayOnArrival("0");
		m.setTransportFee("0.00");
		m.setClientRemark("test-clinet-" + i);
		m.setMemberNo(i + "");
		m.setMemberVipCardLevel(i + "");
		m.setCustomerName("Test" + i);
		m.setCustomerPhone("Test" + i + "phone");
		List<OrderSubDTO> osDTOs = buildOrderSubDTOList();
		m.setOsDTOs(osDTOs);
		List<OrderPromotionDTO> opDTOs = buildOPDTOS(CommonConst.OrderPromotion_Promolevel_Order
				.getCode());
		m.setOpDTOs(opDTOs);
		m.setNeedInvoice("0");
		List<OrderPayDTO>   orderPayDTOs = buildOrderPayDTO();
		m.setOrderPayDTOs(orderPayDTOs);
		m.setTotalProductCount(4);
		return m;
	}



	public static List<OrderSubDTO> buildOrderSubDTOList() {
		List<OrderSubDTO> list = new ArrayList<OrderSubDTO>();
		OrderSubDTO osub = buildOrderSubDTO();
		list.add(osub);
		return list;
	}

	public static OrderSubDTO buildOrderSubDTO() {
		OrderSubDTO s = new OrderSubDTO();
		s.setDeliveryMerchantNo("1100");
		// 1天虹配送,2自提,7虚拟配送
		s.setDistributeType("D01");
		s.setLogisticsOutNo(null);
		s.setHopeArrivalTime(null);
		s.setDeliveryPriority("0");
		s.setTransportFee(null);
		s.setProvideAddress(null);
		s.setSelfFetchAddress(null);
		s.setPickTime(null);
		s.setStoreNo(1 + "");
		s.setDeliveryRemark("Test-deliveryRemark");
		s.setPackageDetail("装箱清单文本 ");
		s.setAliasOrderNo(null);
		s.setAliasOrderSubNo("10077926");
		s.setUserName("赵文霞");
		s.setPhoneNum("86099616");
		s.setMobPhoneNum("13823283608");
		s.setPostCode("116011");
		s.setEmail("test@mail.com");
		s.setAddressCode("100005");
		s.setAddressDetail("汇雅苑4栋504");
		OrderInvoiceDTO invoice = buildInvoice();
		s.setOrderInvoice(invoice);
		s.setShippingOrderNo("Shipping1234509");
		List<OrderItemDTO> OrderItems = buildOrderItemList();
		s.setOiDTOs(OrderItems);
		return s;
	}

	/**
	 * @return
	 */
	public static List<OrderItemDTO> buildOrderItemList() {
		// 子订单号 ：132900673701
		List<OrderItemDTO> items = new ArrayList<OrderItemDTO>();
		OrderItemDTO it = item1();
		OrderItemDTO it1 = item2();
		items.add(it);
		items.add(it1);
		return items;
	}

	private static OrderItemDTO item1() {
		OrderItemDTO it = new OrderItemDTO();
		it.setAliasOrderItemNo("Testitem1");
		it.setAliasOrderNo(null);
		it.setAliasOrderSubNo(null);
		it.setSaleNum("1");
		it.setSaleUnit("kg");
		it.setUnitPrice("35.00");
		it.setUnitDiscount("0.00");
		/** 销售单件折扣 **/
		it.setItemDiscount("0.00");
		it.setCouponAmount("0.00");
		/** 单件购物券金额 **/
		it.setCouponTotalMoney("0.00");
		/** 折后单价,此单价为用户实际支付使用的单价，退款的参考 **/
		it.setUnitDeductedPrice("35.00");
		/** 订单行应付金额:折后总价（包含券支付） **/
		it.setPayAmount("35.00");
		/** 返券总金额:该行返券金额的汇总，提供结算数据等。 **/
		it.setPromoTicketMoney(null);
		it.setProductGroup(null);
		/** 商品分类:运营目录最末级分类 **/
		it.setProductCategory("YYML_SPSX_4969");

		it.setProductCategoryName(null);
		/** 商品名称 **/
		it.setCommodityName("正合隆黑加仑葡萄干(350g)");
		/** 条形码 **/
		it.setBarCode("060380");

		it.setWeight("0.35");
		/** 商品编码 **/
		it.setCommodityCode("P2120700172138S");
		/** SKUNO(物料编号) **/
		it.setSkuNo("P2120700172138S1");
		/** 是否色码款商品, 0不含商品扩展属性，1包含扩展属性 **/
		it.setProductPropertyFlag("0");
		it.setSupplierCode("800452");

		/** 合作伙伴id **/
		it.setPartnerNo(null);
		/** 库区ID **/
		it.setStockNo("001950908100");
		it.setPromoType("1");
		/** 促销活动(团/抢购)ID **/
		it.setPromoId(null);
		/** 订单行类别：NORM :实体 GIFT:赠品 SUITE: 套件(组合商品) INTEGRAL : 积分 **/
		it.setOrderItemClass("NORM");
		/** 赠品行级别赠品所关联的订单行 **/
		it.setGiftOriginItem(null);
		it.setHasGift(0);
		/** 促销类型,库存用，商品类型, 1:普通商品，2：活动商品，3：积分商品 **/
		it.setPromotionType("1");
		/** 促销编码 **/
		it.setPromotionCode(null);
		/** 产品品牌 **/
		it.setBrand(null);
		it.setBrandName(null);
		/** 商品赠送积分 **/
		it.setProductPoint("0.00");
		/** 信息来源 **/
		it.setInfoSource(null);
		/** 广告页 **/
		it.setAdsPage(null);
		/** 订单行备注 **/
		it.setOrderItemRemark("订单行备注");
		
		it.setCombineRule("11");
		it.setCombineType("11");
		it.setOpDTOs(buildOPDTOS(CommonConst.OrderPromotion_Promolevel_Item
				.getCode()));
		
		return it;
	}
	
	
	private static OrderItemDTO item2() {
		OrderItemDTO it = new OrderItemDTO();
		it.setAliasOrderItemNo("Testitem2");
		it.setAliasOrderNo(null);
		it.setAliasOrderSubNo(null);
		it.setSaleNum("2");
		it.setSaleUnit("kg");
		it.setUnitPrice("35.00");
		it.setUnitDiscount("0.00");
		/** 销售单件折扣 **/
		it.setItemDiscount("0.00");
		it.setCouponAmount("0.00");
		/** 单件购物券金额 **/
		it.setCouponTotalMoney("0.00");
		/** 折后单价,此单价为用户实际支付使用的单价，退款的参考 **/
		it.setUnitDeductedPrice("35.00");
		/** 订单行应付金额:折后总价（包含券支付） **/
		it.setPayAmount("35.00");
		/** 返券总金额:该行返券金额的汇总，提供结算数据等。 **/
		it.setPromoTicketMoney(null);
		it.setProductGroup(null);
		/** 商品分类:运营目录最末级分类 **/
		it.setProductCategory("YYML_SPSX_4969");

		it.setProductCategoryName(null);
		/** 商品名称 **/
		it.setCommodityName("正合隆黑加仑葡萄干(350g)-item2");
		/** 条形码 **/
		it.setBarCode("06038002");

		it.setWeight("0.35");
		/** 商品编码 **/
		it.setCommodityCode("P2120700172138S02");
		/** SKUNO(物料编号) **/
		it.setSkuNo("P2120700172138S2");
		/** 是否色码款商品, 0不含商品扩展属性，1包含扩展属性 **/
		it.setProductPropertyFlag("0");
		it.setSupplierCode("800452");

		/** 合作伙伴id **/
		it.setPartnerNo(null);
		/** 库区ID **/
		it.setStockNo("00195090810002");
		it.setPromoType("1");
		/** 促销活动(团/抢购)ID **/
		it.setPromoId(null);
		/** 订单行类别：NORM :实体 GIFT:赠品 SUITE: 套件(组合商品) INTEGRAL : 积分 **/
		it.setOrderItemClass("NORM");
		/** 赠品行级别赠品所关联的订单行 **/
		it.setGiftOriginItem(null);
		it.setHasGift(0);
		/** 促销类型,库存用，商品类型, 1:普通商品，2：活动商品，3：积分商品 **/
		it.setPromotionType("1");
		/** 促销编码 **/
		it.setPromotionCode(null);
		/** 产品品牌 **/
		it.setBrand(null);
		it.setBrandName(null);
		/** 商品赠送积分 **/
		it.setProductPoint("0.00");
		/** 信息来源 **/
		it.setInfoSource(null);
		/** 广告页 **/
		it.setAdsPage(null);
		/** 订单行备注 **/
		it.setOrderItemRemark("订单行备注");
		
		it.setCombineRule("11");
		it.setCombineType("11");
		it.setOpDTOs(buildOPDTOS(CommonConst.OrderPromotion_Promolevel_Item
				.getCode()));
		return it;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<OrderPromotionDTO> buildOPDTOS(String level) {
		List<OrderPromotionDTO> opList = new ArrayList<OrderPromotionDTO>();
		OrderPromotionDTO op = new OrderPromotionDTO();
		op.setIdOrderItem("11");
		op.setMemberNo("11");
		op.setPointCount("11");
		op.setPromoLevel(level);
		op.setPromoName("11");
		op.setPromoNo("11");
		op.setPromoType("11");
		op.setTicketAmount("11");
		op.setTicketBundleNo("11");
		op.setTicketNo("11");
		opList.add(op);
		return opList;
	}

	public static OrderInvoiceDTO buildInvoice() {
		OrderInvoiceDTO oinvoiceDTO = new OrderInvoiceDTO();
		oinvoiceDTO.setInvoiceAddress("11");
		oinvoiceDTO.setInvoiceBankName("11");
		oinvoiceDTO.setInvoiceCategory("11");
		oinvoiceDTO.setInvoiceCompany("11");
		oinvoiceDTO.setInvoiceContent("11");
		oinvoiceDTO.setInvoiceHead("11");
		oinvoiceDTO.setInvoiceNum(CommonUtil.getRandomString(10));
		oinvoiceDTO.setInvoiceTaxpayer("11");
		oinvoiceDTO.setInvoiceTelephone("11");
		oinvoiceDTO.setInvoiceToName("11");
		oinvoiceDTO.setInvoiceToTelephone("11");
		oinvoiceDTO.setInvoiceType("11");
		oinvoiceDTO.setRegistryAddress("11");
		return oinvoiceDTO;
	}

	public static String objectTOString(Object o) {
		return String.valueOf(o);
	}
	
	
	 private static List<OrderPayDTO> buildOrderPayDTO() {
	        List<OrderPayDTO> opList = new ArrayList<OrderPayDTO>();
	        OrderPayDTO op = new OrderPayDTO();
	        op.setBankTypeCode("11");
	        op.setBankTypeName("11");
	        op.setOrderNo("11");
	        op.setPayAmount("11");
	        op.setPayCode("11");
	        op.setPayName("11");
	        op.setPayOnArrivalPayType("11");
	        op.setPayTime(DateUtils.formatGeneralDateTimeFormat(new Date()));
	        opList.add(op);
	        return opList;
	    }
	
	

}
