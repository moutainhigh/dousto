package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pjsong
 *
 */
public enum PayType {
	Order_PayStatus_Paying("0410","待支付","待支付"),
	Order_PayStatus_Cash_Paying("0450","货到付款未支付","货到付款未支付"),
	/**
     * 
     */
	PAY_ON_ARRIVE("1", "货到付款", "货到付款支持以下三种方式1、使用天虹购物卡(包括延时消费卡），不能开具发票（电器商品除外）2、现金3、银行卡"),
	/**
     * 
     */
	SITE_GIFT("2", "网站赠送", "网站赠送"),
	/**
     * 
     */
	POST_EXPRESS("10", "邮局汇款", "邮局汇款"),
	/**
	 * 
	 */
	BANK_ELECTRONIC("11","银行电汇","银行电汇"),
	/**
	 * 
	 */
	SUPER_CAT("12","超级猫支付","超级猫支付"),
	/**
	 * 
	 */
	PINGAN_PRIVATE_BANK("13","平安银行专供支付","平安银行专供支付"),
	/**
	 * 
	 */
	ALIPAY("200","支付宝支付","支付宝支付"),
	/**
	 * 
	 */
	YEEPAY("201","易宝支付","易宝支付"),
	/**
	 * 
	 */
	CNCARD("202","云网支付","云网支付"),
	/**
	 * 
	 */
	ICBC("203","工商银行支付","工商银行支付"),
//	SY("204","首信易支付接口","首信易支付接口"),
	/**
	 * 
	 */
	POINT("300","积分支付","积分支付"),
	/**
	 * 
	 */
	COUPON("301","购物券支付","购物券支付"),
	/**
	 * 
	 */
	GROUP_CARD("302","团购卡支付","团购卡支付"),
	/**
	 * 
	 */
	MYCARD("303","my卡支付","虹卡支付"),
//	("304","买赠卡支付","买赠卡支付"),
	/**
	 * 
	 */
	TIANHONG_GIFT("306","天虹赠卡支付","天虹赠卡支付"),
	/**
	 * 
	 */
	BANK_CARD("307","银行卡支付","银行卡支付"),
	/**
	 * 
	 */
	CCB("820","建设银行支付","建设银行支付"),
	/**
	 * 
	 */
	ONLINE("830","网银在线支付","银联在线支付"),
	/**
	 * 
	 */
	CHINA_UNION_BANK("850","中国银联支付","中国银联支付"),
	/**
	 * 
	 */
	PINGAN_BANK("860","平安银行支付","平安银行支付"),
	/**
	 * vip卡
	 */
	TIANHONG_CARD("880","天虹卡支付","天虹卡支付"),
//	("881","跨店退款","跨店退款"),
//	("882","销售预收","销售预收"),
	/**
	 * 
	 */
	DIRECT_TENPAY("50100","财付通支付","财付通支付 "),
	/**
	 * 
	 */
	WEIXIN("50101","微信支付","微信支付 "),
	/**
	 * 
	 */
	CASH("50300","现金支付","现金支付"),
	
	/**
	 * 购物卡支付
	 */
	SHOPPING_CARD("50301","购物卡支付","购物卡支付"),
	
	/**
	 * 
	 */
	CMB("50400","招商银行支付","招商银行支付"),
	/**
	 * 
	 */
	ALIPAY_ONLINE("50500","支付宝网银支付","支付宝网银支付"),
	/**
	 * 
	 */
	CHANGE_PAID("50600","换货抵扣","换货意向单的原商品价格抵扣出库订单的商品价格"),
	/**
	 * 
	 */
	CHANGE_POINT("50700","换货抵扣积分","换货意向单的原商品积分抵扣新的订单商品积分"),
	/**
	 * 
	 */
	CMB_GATE("50800","招商银行网关支付","招商银行网关支付"),
	/**
	 * 
	 */
	BOC("50900","交通银行支付接口","交通银行支付接口"),
	/**
	 * 
	 */
	ALIPAY_WAP("51000","支付宝WAP支付","支付宝WAP支付"),
	/**
	 * 
	 */
	ALIPAY_SCAN("52000","支付宝扫码支付","支付宝扫码支付"),
	

	/**
	 *积分支付 
	 */
	INTEGERAL_PAY("50800","积分支付","积分支付"),
	
	ALIPAY_CHINA_BANK("215","支付宝-中国银行","支付宝-中国银行"),
	ALIPAY_NONGYE_BANK("219","支付宝-中国农业银行","支付宝-中国农业银行"),
	ALIPAY_PUDONG_BANK("220","支付宝-浦东发展银行","支付宝-浦东发展银行"),
	ALIPAY_XINGYE_BANK("221","支付宝-兴业银行","支付宝-兴业银行"),
	ALIPAY_GUANGDONG_BANK("222","支付宝-广东发展银行","支付宝-广东发展银行"),
	ALIPAY_SHENZHENG_BANK("223","支付宝-深圳发展银行","支付宝-深圳发展银行"),
	ALIPAY_MINSHENG_BANK("224","支付宝-中国民生银行","支付宝-中国民生银行"),
	ALIPAY_ZHONGXIN_BANK("226","支付宝-中信银行","支付宝-中信银行"),
	ALIPAY_GUANGDA_BANK("228","支付宝-中国光大银行","支付宝-中国光大银行"),
	ALIPAY_YOUZHENG_BANK("234","支付宝-中国邮政储蓄","支付宝-中国邮政储蓄"),
	ALIPAY_BEIJING_BANK("249","支付宝-北京银行","支付宝-北京银行");
	
	private final String id;
	private final String desc;
	private final String payName;

	private PayType(String id,  String payName,String desc) {
		this.id = id;
		this.desc = desc;
		this.payName = payName;
	}

	public String getId(){
	    return this.id;
	}
	
	public String getPayName(){
        return this.payName;
    }
	
	public String getDesc() {
		return desc;
	}
	
	

	public static List<PayType>  getAll(){  
		List<PayType> allList = new ArrayList<PayType>();
		allList.add(null);
        for (PayType s : PayType.values()){  
            allList.add(s);
        }    
	    return allList;
	}  



}
