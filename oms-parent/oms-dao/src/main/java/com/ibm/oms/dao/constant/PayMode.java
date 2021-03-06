package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pjsong
 *
 */
public enum PayMode {
	/**
     * 
     *      */
	CASH_PAY_ON_ARRIVE(11l, "50300", "现金货到付款"),
	CARD_PAY_ON_ARRIVE(12l, "307", "银行卡货到付款"),
	RBCARD_PAY_ON_ARRIVE(13l, "50301", "天虹购物卡货到付款"),

	ONLINE(2l, "830","网银在线"),
	   /**
     * 
     */
	COUPON(3l, "301","优惠券"),
    /**
     * 
     */
	CARD(4l, "303","MY卡"),
	INTEGRAL(5l, "50800","积分支付的金额"),
	POINT(5l, "50900","支付用的积分"),//不保存在orderPay表中
	WAIT(999l,"9999","待回调接口更新");//支付成功后回调此接口
	
	private final String code;
	private final String name;
	private final Long id;
	private PayMode(Long id, String code,  String name) {
	    this.id = id;
		this.code = code;
		this.name = name;
	}
	
	

    public Long getId() {
        return id;
    }



    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
//
//    public static boolean isPayOnArrival(String targetPayCode){
//        return CASH_PAY_ON_ARRIVE.getCode().equals(targetPayCode) 
//                || CARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode) 
//                || RBCARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode);
//    }
    
    public static String  getPayName(String targetPayCode){
    	if(CASH_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "现金";
    	}else if(CARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "银行卡";
    	}else if(RBCARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "天虹卡";//0711饶
    	}else{
    		return "货到付款";
    	}
    }
    
    public static String  convertR3PayCode(String targetPayCode){
    	if(CASH_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "cash";
    	}else if(CARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "bankCard";
    	}else if(RBCARD_PAY_ON_ARRIVE.getCode().equals(targetPayCode)){
    		return "天虹卡";
    	}else{
    		return "网银在线支付";
    	}
    }



}
