package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;


/**
 * 促销返券
 * @author pjsong
 *
 */
public class PromotionAddCouponDTO implements Serializable{
    
    private String memberNo;
    private List<String> promotionId;
    public String getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
    public List<String> getPromotionId() {
        return promotionId;
    }
    public void setPromotionId(List<String> promotionId) {
        this.promotionId = promotionId;
    }
    
    
}
