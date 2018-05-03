package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderAuditPositive {

    /**
     * 审核中
     */
    ORDER_AUDITING("审核中"),
    /**
     * 自动审核成功
     */
    ORDER_AUDIT_AUTO_SUCCESS("自动审核成功"),
    /**
     * 人工审核中
     */
    ORDER_AUDIT_AUTO_FAIL("人工审核中"),
    /**
     * 人工审核成功
     */
    ORDER_MANUAL_AUDIT_SUCCESS("人工审核成功"),
    /**
     * 审核失败
     */
    ORDER_MANUAL_AUDIT_FAILED("人工审核失败");
    
    
    private String code;

    private OrderAuditPositive(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
