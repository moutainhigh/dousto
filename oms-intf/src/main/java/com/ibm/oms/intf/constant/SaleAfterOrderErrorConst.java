package com.ibm.oms.intf.constant;

/**
 * 售后意向单异常时需用到的枚举类
 */
public enum SaleAfterOrderErrorConst {

    Empty_OrderCategory("100", "退换货类型不能为空"), 
    Empty_ApplySource("101", "申请来源类型不能为空"), 
    Empty_OrderMain("102", "主订单不能为空"), 
    Empty_OrderSub("103", "子订单不能为空"), 
    Empty_OrderRetChgItem("104", "退换货明细不能为空"), 
    Empty_OrderPay("105", "退款信息不能为空"), 
    Empty_OrderPay_JS("106", "拒收单已付款，退款信息不能为空"), 
    Empty_RefOrderNo("107", "关联的原订单号不能为空"), // 即由哪张销售订单所产生的退换货
    Empty_RefOrderItemId("108", "关联的原明细ID不能为空"), 
    Empty_RefOrderItemNo("109", "关联的原明细No不能为空"),
    
    NotEnough_RemainNum("110", "可剩余退货数量小于当前的退货数量"), 
    NotEnough_RemainMoney("111", "可剩余退款金额小于当前的退款金额"),
    
    Fail_AddExcOrder("112","换货出库单添加失败"),
    Fail_AddExcOrder_InventoryLock("113","换货出库单添加失败：锁定库存不成功");

    private String code;
    private String desc;

    private SaleAfterOrderErrorConst(String code, String desc) {
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
