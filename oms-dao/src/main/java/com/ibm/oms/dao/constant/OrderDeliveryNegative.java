package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderDeliveryNegative {

    /**
     * 退件确认中
     */
    ORDER_CONFIRMING("退件确认中"),
    /**
     * 退件到货中
     */
    ORDER_ON_THE_WAY("退件到货中"),
    /**
     * 退件质检中
     */
    ORDER_INSPECTING("退件质检中"),
    
    /**
     * 质检失败,退货入库失败
     */
    ORDER_INSPECT_FAIL("质检失败"),
    /**
     * 退货已入库
     */
    ORDER_RETURN_SUCCESS("退货已入库");
    
    
    private String code;

    private OrderDeliveryNegative(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
