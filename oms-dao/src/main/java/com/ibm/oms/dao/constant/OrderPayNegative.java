package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderPayNegative {

    /**
     * 待退款
     */
    ORDER_REFUNDING("待退款"),
    /**
     * 无需退款
     */
    ORDER_NONEED_REFUND("无需退款"),
    /**
     * 退款完成
     */
    ORDER_REFUND_SUCCESS("退款完成");
//    /**
//     * 退款失败
//     */
//    ORDER_REFUND_FAILED("退款失败");
   
    
    private String code;

    private OrderPayNegative(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
