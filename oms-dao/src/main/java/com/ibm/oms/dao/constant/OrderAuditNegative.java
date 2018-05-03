package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderAuditNegative {

    /**
     * 审核中
     */
    ORDER_AUDITING("审核中"),
    /**
     * 审核成功
     */
    ORDER_AUDIT_SUCCESS("审核成功"),
    /**
     * 审核失败
     */
    ORDER_AUDIT_FAILED("审核失败");
    
    
    private String code;

    private OrderAuditNegative(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
