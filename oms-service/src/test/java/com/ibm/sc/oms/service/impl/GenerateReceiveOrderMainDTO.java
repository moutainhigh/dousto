package com.ibm.sc.oms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderItemDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.client.dto.order.create.refactor.RecipientInformationDTO;
import com.ibm.sc.util.DateUtils;

public class GenerateReceiveOrderMainDTO {
	ReceiveOrderMainDTO rom =  new ReceiveOrderMainDTO();
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
        	mapper.setSerializationInclusion(Include.NON_EMPTY);  
            itemSnapshotStr = mapper.writeValueAsString(buildReceiveOrderMainDTO());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);
	}
	
	
	public static ReceiveOrderMainDTO buildReceiveOrderMainDTO(){
		ReceiveOrderMainDTO r =  new ReceiveOrderMainDTO();
		r.setAliasOrderNo(null);
		r.setOrderType("normal");
		r.setOrderSource("WX");
		r.setMemberNo("123");
		r.setSalespersonNo("NO1111111");
		r.setNeedInvoice("1");
		r.setIfPayOnArrival("0");
		r.setOrderTime(DateUtils.formatGeneralDateTimeFormat(new Date()));
		r.setSaleStoreCode("saleStoreCode123");
		r.setShipStoreCode("shipStoreCode1234");
		/** 折扣总额 **/
	    r.setDiscountTotal("990.00");
	    /** 订单金额总计 **/
	    r.setTotalOrderAmount("10.00");
	    r.setTotalPayAmount("10.00");
		r.setTotalProductCount("1");
		r.setTotalProductPrice("1000.00");
		//r.setTotalPromo("");
		r.setTotalPoint("0");;
		r.setTotalPointAmount("0");
		r.setCouponsNo("X20180312");
		r.setMemberId("2709");
		r.setStoreId("1111");
		r.setChannelId("1");
		r.setZoneId("331100");
		List<ReceiveOrderItemDTO> roi = buildReceiveOrderItem();
		r.setReceiveOrderItemDTOs(roi);
		RecipientInformationDTO f = new RecipientInformationDTO();
		f.setUserName("test1");
		f.setPhoneNum("0411-99189");
		f.setMobPhoneNum("13317717219987");
		f.setPostCode("116000");
		f.setEmail("hao00@163.com");
		f.setAddressCode("10019");
		r.setRecipientInformationDTO(f);
		return r;
	}

	private static List<ReceiveOrderItemDTO> buildReceiveOrderItem() {
		List<ReceiveOrderItemDTO> roi =  new ArrayList<ReceiveOrderItemDTO>();
		ReceiveOrderItemDTO rid = new ReceiveOrderItemDTO();
		rid.setItemType("SINGLE");
		rid.setProductId("60786");
		rid.setActivityCode("14");
		rid.setSkuId("60786");
		rid.setPrice("100.00");
		rid.setCount(1);
		
		List<ReceiveOrderItemDTO> bundleItemList = new ArrayList<ReceiveOrderItemDTO>();
		ReceiveOrderItemDTO rid1 = new ReceiveOrderItemDTO();
		rid1.setItemType("GIFT");
		rid1.setProductId("60786");
		rid1.setSkuId("60786");
		rid1.setPrice("100");
		rid1.setCount(1);
		bundleItemList.add(rid1);
		rid.setBundleItemList(bundleItemList);
		
		ReceiveOrderItemDTO rid2 = new ReceiveOrderItemDTO();
		rid2.setItemType("SINGLE");
		rid2.setProductId("60786");
		rid2.setActivityCode("14");
		rid2.setSkuId("60786");
		rid2.setPrice("100.00");
		rid2.setCount(1);
		
		
		roi.add(rid);
		roi.add(rid2);
		return roi;
	}
}
