package com.ibm.oms.service.err;

/**
 * 常量定义
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ErrConst {

    IMS_DEDUCT("IMS_DEDUCT","库存扣减异常"), 
    IMS_CANCEL_DEDUCT("IMS_CANCEL_DEDUCT","库存扣减取消异常"), 
    PROMOADD_FAILED("PROMOADD_FAILED","促销资源处理失败"),
    PROCESS_FAILED("PROCESS_FAILED","订单处理失败"),
    R3_SYNC_FAILED("R3_SYNC_FAILED","R3同步失败"),
    COUPON_RETURN("COUPON_RETURN","订单取消返还购物券"),
    
    SaleAfterOrder_ToWMS("SaleAfterOrder_ToWMS","入库单传输WMS失败"),
    SaleAfterOrder_ToWMS_Cancel("SaleAfterOrder_ToWMS_Cancel","取消入库单传输WMS失败"),
    /**
     * 取消入库单加回积分失败
     */
    SaleAfterOrder_Cancel_ReturnIntegral("SaleAfterOrder_Cancel_ReturnIntegral","取消入库单加回积分失败"),
    /**
     * 取消未审核订单加回MY卡失败
     */
    CancelOrder_ReturnMyCard("CancelOrder_ReturnMyCard","未审核前取消订单加回MY卡失败");
    
    
    private String code;
    private String desc;

    private ErrConst(String code, String desc) {
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
