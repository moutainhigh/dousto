package com.ibm.oms.dao.constant;

/**
 * 订单状态类型枚举类<br>
 */
public enum OrderStatusType {

	/**
	 * OSP(Order Status Positive)正向订单状态
	 */
	Order_Status_Positive("01","销售订单总状态"),
	/**
	 * OSN(Order Status Negative)逆向订单状态
	 */
	Order_Status_Negative("02","退货总状态"),

	/**
	 * Order_Status_Change 换货总状态
	 */
	Order_Status_Change("03","换货总状态"),
	/**
	 * OPP(Order_Pay Positive)换货总状态
	 */
	Order_Pay_Positive("04","销售订单支付状态"),

	/**
	 * Order_Pay_Negative退货订单退款状态
	 */
	Order_Pay_Negative("05","退货订单退款状态"),

	/**
	 * ODP(Order Delivery Positive)
	 */
	Order_Delivery("06","销售订单物流状态"),

	/**
	 * OAN(Order Audit Negative) 订单审核状态
	 */
	Order_Audit("08","订单审核状态");

    private String code;
    private String desc;

    private OrderStatusType(String code, String desc) {
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
