package com.ibm.oms.dao.constant;

/**
 * 退货订单类型,见order_ret_change表字段
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ReturnOrderType {

    /**
     * 未支付取消
     */
    CANCEL_BEFORE_PAY("未支付取消"),
    /**
     * 审核失败
     */
   AUDIT_FAIL("审核失败"),
    /**
     * 拒收
     */
    REJECTION("拒收"),
    /**
     * 已收完成
     */
    RECEIVED_FINISHED("已收完成"),
    /**
     * 处理异常
     */
    PROCESS_FAILED("处理异常");

    
    private String code;

    private ReturnOrderType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
