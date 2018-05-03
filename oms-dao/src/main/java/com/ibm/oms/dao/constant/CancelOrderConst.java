package com.ibm.oms.dao.constant;

/**
 * 取消订单应用场景 常量定义
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum CancelOrderConst {

    /**
     * 顾客取消(未审核)
     */
    CancelOrder_Scene_Customer("1","顾客取消(未审核)"),
    /**
     * 客服审核时直接取消
     */
    CancelOrder_Scene_Saler("2","客服审核时直接取消"),
    /**
     * 未出库客服取消
     */
    CancelOrder_Scene_VALIDATED("3","未出库客服取消"),
    /**
     * 库存扣减失败取消
     */
    CancelOrder_Scene_InventoryFail("4","库存扣减失败取消");
    
    private String code;
    private String desc;

    private CancelOrderConst(String code, String desc) {
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
