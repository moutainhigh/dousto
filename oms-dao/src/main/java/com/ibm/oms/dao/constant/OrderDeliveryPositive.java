package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderDeliveryPositive {

    /**
     * 订单下达仓库中
     */
    ORDER_WAY_TO_INVENTORY("订单下达仓库中"),
    /**
     * 分配库存中
     */
    ORDER_ALLOCATING_INVENTORY("分配库存中"),
    /**
     * 检货中
     */
    ORDER_PICKING("检货中"),
    
    /**
     * 打包中
     */
    ORDER_PACKING("打包中"),
    /**
     * 装箱中
     */
    ORDER_LOADING("装箱中"),
    /**
     * 物流接单中
     */
    ORDER_LOGISTICS_ACCEPTING("物流接单中"),
    /**
     * 揽收中
     */
    ORDER_LOGISTICS_FETCHING("揽收中"),
    /**
     * 签收中
     */
    ORDER_ACCEPT_SIGNING("签收中"),
    /**
     * 签收完成
     */
    ORDER_ACCEPTED("签收完成"),
    /**
     * 拒收
     */
    ORDER_REJECTED("拒收");
    
    
    private String code;

    private OrderDeliveryPositive(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
