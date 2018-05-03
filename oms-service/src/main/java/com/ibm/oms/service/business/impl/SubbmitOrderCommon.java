package com.ibm.oms.service.business.impl;

import java.math.RoundingMode;

public class SubbmitOrderCommon {
	public  final String  DEFAULT_BLANTA_MOUNT = "0.00";
	//参与订单级别优惠key
	public  final String  PROMOTIONLEVEL_ORDER = "ORDER";
	//参与订单级别商品得折后总价
	public  final String  PROMOTIONLEVEL_ORDER_AMOUNT = "ORDER_AMOUT";
	//参与优惠券优惠key
	public  final String  PROMOTIONLEVEL_COUPON = "COUPON";
	//参与优惠券商品得优惠总价
	public  final String  PROMOTIONLEVEL_OCOUPON_AMOUNT = "COUPON_AMOUT";
	
	public  final String  PROMOTIONLEVEL_ITEM = "ITEM";
	public  final String  PROMOTIONLEVEL_ITEM_AMOUNT = "ITEM_AMOUT";
	
	//积分均摊flag
	public  final String  PROMOTIONLEVEL_POINT= "POINT";
	//积分均摊总额flag
	public  final String  PROMOTIONLEVEL_POINT_AMOUNT= "POINT_AMOUNT";
	//默认保留精度
	public final Integer BIGDECIMAL_DEFAULT_SCALE = 2;
	//默认保留进位制
	public final RoundingMode BIGDECIMAL_DEFAULT_ROUNDINGMODE = RoundingMode.HALF_UP;
	
	public final String SUBBMIT_VALIDATE_SWITCH_OPEN="open";
	
}
