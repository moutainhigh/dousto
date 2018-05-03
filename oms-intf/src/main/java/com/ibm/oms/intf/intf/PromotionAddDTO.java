package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 订单完成后，促销返券、返积分、MY卡等
 * 
 * @author xiaohl
 * 
 */
public class PromotionAddDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1846389444868782644L;
	private String memberNo;
    private List<Map<String, String>> promotionList; // Map的结构： keyName: promotionCode，keyName：promotionType
    public static final String keyName_promotionCode = "promotionCode"; // 促销编码的key
    public static final String keyName_promotionType = "promotionType"; // 促销类型的key

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public List<Map<String, String>> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Map<String, String>> promotionList) {
        this.promotionList = promotionList;
    }
    
    public static void main(String[] args) {
    	PromotionAddDTO pa =  new PromotionAddDTO();
    	pa.setMemberNo("test1234");
    	ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        Map<String, String> promotion = new HashMap<String, String>();
        promotion.put(keyName_promotionCode, keyName_promotionCode);
        promotion.put(keyName_promotionType, keyName_promotionType);
        List<Map<String, String>> promotionList = new ArrayList<Map<String,String>>();
        promotionList.add(promotion);
        pa.setPromotionList(promotionList);
        try {
            itemSnapshotStr = mapper.writeValueAsString(pa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);

	}
    
    
}
