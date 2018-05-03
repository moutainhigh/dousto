package com.ibm.oms.dao.constant;

/**
 * 退货订单类型,见order_ret_change表字段
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderReturnChangeType {

    /**
     * 换货
     */
    CHANGE("换货"),
    
    
    /**
     * 退货
     */
    RETURN("退货"),
    
    RETURN_OVER_ORDER("已收已付");
    
    
    
    
    private String code;

    private OrderReturnChangeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
