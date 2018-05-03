package com.ibm.sc.oms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.HangOrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.HangOrderItemDTO;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPayDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.HangOrderSubDTO;
import com.ibm.oms.intf.intf.inner.OrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPayDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.sc.util.CommonUtil;
import com.ibm.sc.util.DateUtils;

public class GenerateOmsReceiveHangOrderDTOJson {
	static GenerateOmsReceiveHangOrderDTOJson rd = new GenerateOmsReceiveHangOrderDTOJson();
	public static void main(String[] args) {
		HangOrderReceiveOrderDTO bd = new HangOrderReceiveOrderDTO();
		
		bd.setBatchNum("test-20180228-SHO140410109971");
		List<HangOrderMainDTO> mainDTOs = new ArrayList<HangOrderMainDTO>();
		bd.setOmDTO(mainDTOs);
		HangOrderMainDTO mainDTO = buildHangOrdermainDTO();
		
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
	}
	
	public static HangOrderReceiveOrderDTO buildBtcOmsReceiveOrderDTO(){
		HangOrderReceiveOrderDTO bd = new HangOrderReceiveOrderDTO();
		bd.setBatchNum("test-201801018-SHO140410109992");
		List<HangOrderMainDTO> mainDTOs = new ArrayList<HangOrderMainDTO>();
		bd.setOmDTO(mainDTOs);
		HangOrderMainDTO mainDTO = buildHangOrdermainDTO();
		
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
	
		
	public static HangOrderMainDTO buildHangOrdermainDTO() {
		HangOrderMain hom;
		HangOrderSub hsub;

		int i = 1;
		HangOrderMainDTO m = new HangOrderMainDTO();
		m.setOrderNo("test");
		m.setOrderType(OrderMainConst.OrderMain_OrderType_General.getCode());
		m.setOrderSource(OrderMainConst.OrderMain_Ordersource_B2c.getCode());
		m.setIsSuspension("1");
		m.setMemberNo(objectTOString(i) + "test" + new Date().getTime());
		m.setTotalProductCount("2");
		m.setDiscountTotal("2.50");
		m.setTotalOrderAmount("235.50");
		m.setTotalPromo("10.20");
		m.setTotalPayAmount("250.00");
		m.setPrepaidAmount("10");
		m.setWmsID("1");
		m.setShopNo("1");
		m.setSalespersonNo("007");
		m.setOrderTime(DateUtils.formatGeneralDateTimeFormat(new Date()));
		m.setBillType("1");
		

		List<HangOrderSubDTO> osDTOs = buildHangOrderSubDTOList();
		m.setOsDTOs(osDTOs);
		List<HangOrderPromotionDTO> opDTOs = buildHangOPDTOS(CommonConst.OrderPromotion_Promolevel_Order.getCode());
		m.setOpDTOs(opDTOs);
		m.setNeedInvoice("0");
		List<HangOrderPayDTO>   orderPayDTOs = buildHangOrderPayDTO();
		m.setOrderPayDTOs(orderPayDTOs);
		return m;
	}



	public static List<HangOrderSubDTO> buildHangOrderSubDTOList() {
		List<HangOrderSubDTO> list = new ArrayList<HangOrderSubDTO>();
		HangOrderSubDTO osub = buildHangOrderSubDTO();
		list.add(osub);
		return list;
	}

	public static HangOrderSubDTO buildHangOrderSubDTO() {
		HangOrderSubDTO s = new HangOrderSubDTO();
		s.setOrderNo("test-hang-001");
		s.setOrderSubNo("test-sub-001");
		s.setDeliveryMerchantNo("1100");
		// 1天虹配送,2自提,7虚拟配送
		s.setDistributeType("1");
		/*s.setLogisticsOutNo(null);*/
//		s.setHopeArrivalTime(null);
		s.setDeliveryPriority("0");
		s.setTransportFee("10");
		s.setProvideAddress("温州");
		s.setSelfFetchAddress("温州");
		s.setPickTime("2018-2-7");
		s.setStoreNo("1");
		s.setDeliveryRemark("Test-deliveryRemark");
		s.setAliasOrderSubNo("10077926");
		s.setUserName("赵文霞");
		s.setPhoneNum("86099616");
		s.setMobPhoneNum("13823283608");
		s.setPostCode("116011");
		s.setEmail("test@mail.com");
		s.setAddressCode("100005");
		s.setAddressDetail("汇雅苑4栋504");
		
		
		HangOrderInvoiceDTO invoice = buildHangInvoice();
		s.setHangOrderInvoice(invoice);
		
		List<HangOrderItemDTO> OrderItems = buildHangOrderItemList();
		s.setOiDTOs(OrderItems);
		return s;
	}

	/**
	 * @return
	 */
	public static List<HangOrderItemDTO> buildHangOrderItemList() {
		// 子订单号 ：132900673701
		List<HangOrderItemDTO> items = new ArrayList<HangOrderItemDTO>();
		HangOrderItemDTO it = new HangOrderItemDTO();
		it.setOrderNo("test-hang-001");
		it.setOrderItemNo("test-item-001");
		it.setOrderItemType("行项目类型");
		it.setAliasOrderItemNo("Test1239801");
		it.setAliasOrderNo(null);
		it.setAliasOrderSubNo(null);
		/** 商品编码 **/
		it.setCommodityCode("P2120700172138S");
		/** SKUNO(物料编号) **/
		it.setSkuNo("P2120700172138S1");
		/** 条形码 **/
		it.setBarCode("060380");
		/** 商品名称 **/
		it.setCommodityName("正合隆黑加仑葡萄干(350g)");
		/** 商品分类:运营目录最末级分类 **/
		it.setProductCategory("YYML_SPSX_4969");
		it.setSupplierCode("800452");
		it.setSaleNum("10");
		it.setUnitPrice("35.00");
		/** 折后单价,此单价为用户实际支付使用的单价，退款的参考 **/
		it.setUnitDeductedPrice("35.00");
		it.setCouponAmount("0.00");
		it.setUnitDiscount("0.00");
		/** 订单行应付金额:折后总价（包含券支付） **/
		it.setPayAmount("35.00");
		/** 库区ID **/
		it.setStockNo("001950908100");
		it.setWarehouseNo("001950908100");
		it.setAllasProductNo("001950908100");
		it.setHasGift(1);
		it.setPromotionType("1");
		it.setPromotionCode("1");
		items.add(it);
		return items;
	}

	public static List<HangOrderPromotionDTO> buildHangOPDTOS(String level) {
		List<HangOrderPromotionDTO> opList = new ArrayList<HangOrderPromotionDTO>();
		HangOrderPromotionDTO op = new HangOrderPromotionDTO();
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

	public static HangOrderInvoiceDTO buildHangInvoice() {
		HangOrderInvoiceDTO oinvoiceDTO = new HangOrderInvoiceDTO();
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
	
	
	 private static List<HangOrderPayDTO> buildHangOrderPayDTO() {
	        List<HangOrderPayDTO> opList = new ArrayList<HangOrderPayDTO>();
	        HangOrderPayDTO op = new HangOrderPayDTO();
	        op.setOrderNo("");
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
