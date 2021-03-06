package com.ibm.oms.intf.constant;

/**
 * 外部接口传参数需用到的枚举类<br>
 */
public enum OrderMainConst {
    
    /**
     * 商家编号 国贸云店
     */
    OrderMain_MerchantNo_Yundian_GuoMao("y00124", "国贸云店"),

    /**
     * 主订单：订单来源
     */
    @Deprecated
    OrderMain_Ordersource_B2c("PC", "B2C前台"),
    @Deprecated
    OrderMain_Ordersource_App("APP", "手机App"),
    @Deprecated
    OrderMain_Ordersource_Wap("WAP", "手机WAP"),
    @Deprecated
    OrderMain_Ordersource_Wgw("WGW", "微购物"),
    @Deprecated
    OrderMain_Ordersource_Wph("THXD", "天虹小店"),
    @Deprecated
    OrderMain_Ordersource_Jd("JD", "京东"),
    
    OrderMain_Ordersource_LS("LS", "浪莎"),
    OrderMain_Ordersource_BS("BS","百胜"),
    OrderMain_Ordersource_WX("WX","微信前端"),
    OrderMain_Ordersource_DG("DG","导购APP"),
    
    /**
     * 主订单:退款申请类型
     */
    OrderMain_RefundType_All("All", "全部退款"),
    OrderMain_RefundType_Part("Part", "部分退款"),
    
    /**
     * 退换货类型
     */
    OrderMain_OrderCategory_Sale("sale", "销售"), 
    /**ret, 退货**/
    OrderMain_OrderCategory_Return("ret", "退货"), 
    /**chg, 换货**/
    OrderMain_OrderCategory_Change("chg", "换货"), 
    OrderMain_OrderCategory_ChangeOut("chgOut","换货出库"),
    OrderMain_OrderCategory_Reject("rej", "拒收"),
    OrderMain_OrderCategory_Refund("ref", "退款单(取消订单)"),
    OrderMain_OrderCategory_TransportFee("tsf", "运费补款单"),
    
   
    /**
     * 订单类型
     */
    OrderMain_OrderType_General("GENERAL","普通订单"),
    @Deprecated
    OrderMain_OrderType_Gift("GIFT_CART","礼卡订单"),
    @Deprecated
    OrderMain_OrderType_Virtual("VIRTUAL","虚拟订单"),
    @Deprecated
    OrderMain_OrderType_Batch("BATCH","批量订单"),
    @Deprecated
    OrderMain_OrderType_Integral("INTEGRAL","积分订单"),
    OrderMain_OrderType_Valet("VALET","代客下单"),
    //预约，
    OrderMain_OrderType_Reservation("RESERVATION","预约订单"),
    //周期购
    OrderMain_OrderType_Period("PERIOD","周期购"),
    OrderMain_OrderType_Presale("Presale","预售订单"),
    OrderMain_OrderType_Problem("Problem","问题订单"),
    OrderMain_OrderType_Out_Stock("OutStock","缺货订单"),
    
    /**
     * 入库方式
     */
    //OrderSub_DistributeType_PickFromDoor("2","上门取货"),
    OrderSub_DistributeType_CustomerSend("1","客户寄回"),
    //OrderSub_DistributeType_ReturnStore("3","门店代退"),
    OrderSub_DistributeType_ReturnLogistics("4","物流返回"),
    
    /**
     * 入库方式 门店代退二级选择
     */
    OrderSub_DistributeType_Level2_ReturnStore("1","门店寄回"),
    OrderSub_DistributeType_Level2_ReturnStoreLogistics("2","物流到店取回"),
    OrderSub_DistributeType_Level2_NeedReturnOriginal_Yes("3","原商品需取回"),
    OrderSub_DistributeType_Level2_NeedReturnOriginal_No("4","原商品无需取回"),
    
    /**
     * 配送方式,使用CommonConst, use CommonConst instead
     */
    @Deprecated
    OrderSub_DistributeType_SelfTake("2","自提"),
    /**
     * 配送方式,使用CommonConst, use CommonConst instead
     */
    @Deprecated
    OrderSub_DistributeType_NormalDeliver("1","天虹配送"),
    /**
     * 配送方式,使用CommonConst, use CommonConst instead
     */
    @Deprecated
    OrderSub_DistributeType_VirtualDeliver("7","虚拟配送"),
    
    OrderSub_DistributeType_Self_Store("1","本门店配送"),
    
    OrderSub_DistributeType_Assign_Store("2","指定门店配送"),
    //线上，奇门都是第三方配送
    OrderSub_DistributeType_Third_Party("3","第三方物流配送"),
    
    OrderItem_OrderItemClass_NORM("NORM","普通"),
    OrderItem_OrderItemClass_GIFT("GIFT","赠品"),
    OrderItem_OrderItemClass_SUITE("SUITE","组合套件"),
    
    
    
    
    /**
     * 发票类型：0.普通发票1.增值税发票2.无需发票
     */
    OrderInvoice_InvoiceType_Normal("0","普通发票"),
    OrderInvoice_InvoiceType_Tax("1","增值税发票"),
    OrderInvoice_InvoiceType_NoNeed("2","无需发票"),
   
    
    /**
     * 是否需要发票
     *
     */
    
    ORDERINVOICE_INVOICETYPE_NONEED_NO("0","无需发票"),
    ORDERINVOICE_INVOICETYPE_NONEED_YES("1","需发票"),
    
    
    /**
     *  是否拆单 0否 1是 
     */
    ORDERMAIN_IS_SPLIT_NO("0","否"),
    ORDERMAIN_IS_SPLIT_YES("1","被拆分单"),
    ORDERMAIN_IS_SPLIT_NEW_ORDER("2","拆分单"),
    
    
    /**
     *  订单是否有赠品
     */
    OrderItem_OrderItemClass_GIFT_YES("1","是"),
    OrderItem_OrderItemClass_GIFT_NO("0","不");
    
    private String code;
    private String desc;

    private OrderMainConst(String code, String desc) {
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

