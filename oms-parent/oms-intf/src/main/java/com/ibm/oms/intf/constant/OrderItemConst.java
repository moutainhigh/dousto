package com.ibm.oms.intf.constant;

/**
 * 外部接口传参数需用到的枚举类<br>
 */
public enum OrderItemConst {
    
	//订单行类别：NORM :实体 GIFT:赠品 SUITE: 套件(组合商品) INTEGRAL : 积分
	OrderItem_OrderItemClass_NORM("NORM","实体"),
	OrderItem_OrderItemClass_GIFT("GIFT","赠品"),
	OrderItem_OrderItemClass_SUITE("SUITE","套件(组合商品)"),
	OrderItem_OrderItemClass_INTEGRAL("INTEGRAL","积分"),
	
	/**
     *  订单是否有赠品
     */
    OrderItem_OrderItemClass_GIFT_YES("1","是"),
    OrderItem_OrderItemClass_GIFT_NO("0","不");
    
    private String code;
    private String desc;

    private OrderItemConst(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }

}

