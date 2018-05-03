package com.ibm.oms.dao.constant;

/**
 * 常量定义
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum CommonConst {
	/**
	 * 配送地址级别： 1 省份级
	 */
	TransportArea_AreaLevel_State("1","省份级"),
	/**
	 * 配送地址级别： 2 城市级
	 */
	TransportArea_AreaLevel_City("2","城市级"),
	/**
	 * 配送地址级别： 3 区县级
	 */
	TransportArea_AreaLevel_County("3","区县级"),
	/**
	 * 配送地址级别： 4 街道级
	 */
	TransportArea_AreaLevel_Street("4","街道级"),
	
	/**
	 * 自营商家编号
	 */
	OrderMain_MerchantNo("00195","自营、联营集货的商家编号"),
	/**
     * 商家编号 国贸云店
     */
    OrderMain_MerchantNo_Yundian_GuoMao("y00124", "国贸云店"),
	
	OrderMain_MerchantType_Bbc("bbc","商家"),
	OrderMain_MerchantType_InvoiceOrg("invoiceOrg","云店"),
	OrderMain_MerchantType_Client("client","供应商"),
	OrderMain_MerchantType_Platform("platform","btc正常订单"),
    /**
     * 退换货类型
     */
    OrderMain_OrderCategory_Sale("sale", "销售"), 
    OrderMain_OrderCategory_Return("ret", "退货"), 
    OrderMain_OrderCategory_Change("chg", "换货"), 
    OrderMain_OrderCategory_ChangeOut("chgOut","换货出库"),
    OrderMain_OrderCategory_Reject("rej", "拒收"),
    OrderMain_OrderCategory_Refund("ref", "退款单(取消已支付订单)"),
    OrderMain_OrderCategory_TransportFee("tsf", "运费补款单"),
    OrderMain_MemberCardLevel1("1", "微卡"), 
    OrderMain_MemberCardLevel2("2", "银卡"), 
    OrderMain_MemberCardLevel3("3", "金卡"), 
    /**
     * 退换货的责任归属
     */
    OrderRetChangeItem_Responsible_Rainbow("1","我方原因"),
    OrderRetChangeItem_Responsible_Customer("2","顾客原因"),
    OrderRetChangeItem_Responsible_Other("3","第三方原因"),

	/**
	 * 未支付：0   
	 */
	OrderPayMode_PayStatus_NoPaid("0","未支付"),
	
	/**
	 * 已支付：1
	 */
	OrderPayMode_PayStatus_HadPaid("1","已支付"),
	
    /**
     * 订单字典:字典类型
     */
    OrderDic_DicType_OrderType("10","订单类型"),
    OrderDic_DicType_OrderSource("20","订单来源"),
    OrderDic_DicType_OrderCategory("30","售后类型"),
    
    /**
     * 自提点类型
     */
    SelfTakePoint_PointTypeId_SelfSale("100","自营"),
    SelfTakePoint_PointTypeId_Cooperate("101","合作"),
    
    /**
     * 物流公司  EXPRESS_COMPLANY
     */
    OptionGroup_Code_EXPRESS_COMPLANY("EXPRESS_COMPLANY","物流公司"),
    
    /**
     * 订单配送时间段 OptionGroupId=1201
     */
    OptionGroup_OptionGroupId_DeliveryDateFlag("1201","订单配送时间段"),
    
    /**
     * 取消订单原因 500000050
     */
    OptionGroup_OptionGroupId_CancelOrderReason("500000050","取消订单原因"),
    
    /**
     * 配送方式
     */
    Option_OptionGroupId("50","配送方式"),

    /**
     * 物流公司
     * 
     */
    LOGISTICS_COMPANYId("901","物流公司"),
    /**
     * 自提商户 450
     */
    Option_OptionGroupId_Merchant("450","自提商户"),
    
    OrderMain_OrderType_GENERAL("GENERAL","实体订单"),
    OrderMain_OrderType_INTEGRAL("INTEGRAL","积分商城订单"),
    OrderMain_OrderType_GIFT_CART("GIFT_CART","礼品订单"),
    OrderMain_OrderType_VIRTUAL("VIRTUAL","虚拟订单"),
    OrderMain_OrderType_BATCH("BATCH","批量订单"),
    
    OrderMain_OrderTime("yyyy-MM-dd HH:mm:ss", "订单日期格式"),
    /**
     * 逆向订单是否需退款
     */
    OrderMain_IfNeedRefund_Yes_Store(2l,"门店退款"),
    OrderMain_IfNeedRefund_Yes(1l,"需退款"),
    OrderMain_IfNeedRefund_No(0l,"无需退款"),
    /**order source**/
    OrderMain_OrderSource_PC("PC","btc order"),
    OrderMain_OrderSource_WAP("WAP","WAP order"),
    OrderMain_OrderSource_WGW("WGW","WGW order"),
    OrderMain_OrderSource_APP("APP","APP order"),
    /**
     * 促销：订单级促销
     */
    OrderPromotion_Promolevel_Order("0", "订单头级"),
    /**
     * 促销：订单行级促销
     */
    OrderPromotion_Promolevel_Item("1", "订单行级"),

    
    /**
     * 是否货到付款
     */
    OrderMain_IfPayOnArrival_Yes("1","是"),
    

    /**
     * (Order Type Positive)正向订单
     */
    OrderMain_BillType_Positive(1l, "正向订单"),
    /**
     * (Order Type Negative)逆向订单
     */
    OrderMain_BillType_Negative(-1l, "逆向订单"),
    
    /**
     * 是否换货
     */
    OrderMain_Is_Barter_Yes(1, "是"),
    OrderMain_Is_Barter_No(0, "否"),
    /**
     * 送货时间
     */
    OrderMain_DeliveryDateFlag_WorkDay("1","仅工作日送货"),
    OrderMain_DeliveryDateFlag_Weekend("2","仅周末和节假日送货"),
    OrderMain_DeliveryDateFlag_AllDay("3","工作日和节假日均可送货"),

    /**
     * 期望到达日期
     */
    /*OrderSub_HopeArrivalTime_Workday_Day(11l, "只工作日送货(双休日、假日不用送)--白天"), 
    OrderSub_HopeArrivalTime_WorkAndRestday_Day(21l,"工作日、双休日与假日均可送货--白天"), 
    OrderSub_HopeArrivalTime_Restday_Day(31l, "只双休日、假日送货(工作日不用送)--白天"),
    OrderSub_HopeArrivalTime_Workday_Night(12l, "只工作日送货(双休日、假日不用送)--晚上"), 
    OrderSub_HopeArrivalTime_WorkAndRestday_Night(22l, "工作日、双休日与假日均可送货--晚上"), 
    OrderSub_HopeArrivalTime_Restday_Night(33l, "只双休日、假日送货(工作日不用送)--晚上"),*/

    /**
     * 深圳
     */
    OrderSub_AddressCode_Shenzhen("31359","深圳"),
    
    /**distribute type**/
    OrderSub_Distribute_Type1("1", "天虹配送"), 
    OrderSub_Distribute_Type2("2","自提"), 
    OrderSub_Distribute_Type7("7", "虚拟商品配送"),
    
    //TODO
    //需要和仓库对接
    OrderSub_DistributeType_Self_Store("D01","本门店配送"),
    OrderSub_DistributeType_Assign_Store("D02","指定门店配送"),
    OrderSub_DistributeType_Third_Party("D03","第三方物流配送"),
    
    //库存方无需出库,第三方配送
    OrderSub_Distribute_Type0("0", "0"), 
//    OrderSub_Distribute_TypeR1("1", "客户寄回"), 
//    OrderSub_Distribute_TypeR2("2", "上门取货"), 
//    OrderSub_Distribute_TypeR3("3", "门店代退"), 
    
    /**自提点：分拣中心**/
    OrderSub_selfFetchAddress_RainBow("1400","网上天虹分拣中心"),

    /**
     * 色码款商品标识
     */
    OrderItem_ProductPropertyFlag_Yes(1l, "是色码款商品"), 
    OrderItem_ProductPropertyFlag_No(0l, "不是色码款商品"),
    /**
     * 商品类型
     */
    /*OrderItem_OrderItemClass_Normal("1","实体(普通商品)"),
    OrderItem_OrderItemClass_Present("2","赠品"),
    OrderItem_OrderItemClass_Suite("3","组合商品"),*/
    
    /**
     * 商品类型
     */
    OrderItem_OrderItemClass_Normal("NORM","实体(普通商品)"),
    OrderItem_OrderItemClass_Present("GIFT","赠品"),
    OrderItem_OrderItemClass_Suite("SUITE","组合商品"),
    OrderItem_OrderItemClass_Integral("INTEGRAL","积分商品"),
    
    /**
     * 促销活动类型：1，普通商品 2，活动商品 3，积分商品
     */
    OrderItem_PromotionType_Normal("1","普通商品"),
    OrderItem_PromotionType_Activity("2","活动商品"),
    OrderItem_PromotionType_Integral("3","积分商品"),
    
    /**
     * 集货类型:'0','电商仓库','1','总仓','2','集货','3','商家自发货'
     */
    OrderItem_StoreType_DsStore("0","电商仓库"),
    OrderItem_StoreType_HeadStore("1","总仓"),
    OrderItem_StoreType_Collection("2","集货"),
    OrderItem_StoreType_MerchantSelf("3","商家自发货"),

    /**
     * 退换货明细关联中的来源：前台B2C
     */
    OrderRetChange_Applysource_B2c("b2c", "b2c"),

    /**
     * 退换货明细关联中的来源：OMS订单中台
     */
    OrderRetChange_Applysource_Oms("oms", "oms"),
    
    /**
     * 退换货明细关联中的来源：浪莎
     */
    OrderRetChange_Applysource_LS("LS", "LS"),
    
    /**
     * 退换货明细关联中的来源：百胜
     */
    OrderRetChange_Applysource_BS("BS", "BS"),
    
    /**
     * 同步R3接口定义I-OMS-R3-01
     */
    OrderStatusSyncLog_SyncScene_OrderItem("I-OMS-R3-01","订单商品明细"),
    /**
     * 同步R3接口定义I-OMS-R3-02
     */
//    OrderStatusSyncLog_SyncScene_Pay("I-OMS-R3-02","订单支付明细"),
    /**
     * 同步R3接口定义I-OMS-R3-03
     */
    OrderStatusSyncLog_SyncScene_PrePay("I-OMS-R3-03","预收(现金支付、购物券、在线支付)"),
    /**
     * 同步R3接口定义I-OMS-R3-04
     */
    OrderStatusSyncLog_SyncScene_Cancel("I-OMS-R3-04","取消(充预收)"),
    OrderStatusSyncLog_TargetSys_R3("R3","R3"),
    /**Y已同步**/
    OrderStatusSyncLog_SyncFlag_Yes("Y","已同步"),
    /**F同步失败**/
    OrderStatusSyncLog_SyncFlag_Fail("F","同步失败"),
    /**P同步中**/
    OrderStatusSyncLog_SyncFlag_Processing("P","同步中"),
    /**N待同步**/
    OrderStatusSyncLog_SyncFlag_NeedProcess("N","待同步"),
    /**E待同步数据异常**/
    OrderStatusSyncLog_SyncFlag_FailedPreProcess("E","待同步数据异常"),
    
    /**
     * 退款申请
     */
    Order_Refund_Type_All("0","全部退款"),
    Order_Refund_Type_P("1","部分退款"),

    
    /**
     * 取消订单应用场景
     */
    CancelOrder_Scene_Customer("1","顾客取消(未审核)"),
    CancelOrder_Scene_Saler("2","客服审核时直接取消"),
    CancelOrder_Scene_VALIDATED("3","未出库客服取消"),
    
    /**
     * 异常订单操作类型
     */
    OrdiErrOptLog_OperateType_Send("1","补送"),
    OrdiErrOptLog_OperateType_CancelOrder("2","取消"),
    OrdiErrOptLog_OperateType_Refund("3","补发退款"),
    
    
    IntfReceived_Succeed_SUCCESS(1L,"成功"),
    IntfReceived_Succeed_FAIL(-1L,"失败"),
    
    CommonBooleanFalse("0", "否"),
    CommonBooleanTrue("1", "是"),
    CommonBooleanDefault("-1", "缺省值"),
    
    CommonBooleanFlaseLong(0l, "否"),
    CommonBooleanTrueLong(1l, "是"),
    
    Common_Succeed_SUCCESS(1, "成功"),
    Common_Succeed_FAIL(-1, "失败"),
    
    /**
     * 处理成功 OK
     */
    Common_Result_OK("OK","成功"),
    
    /**
     * 处理失败 FAIL
     */
    Common_Result_FAIL("FAIL","失败"),
    
    OrderPay_DateSource_Btc("btc", "是"),
    OrderPay_DateSource_Bbc("bbc", "是"), 
    OrderSub_ShipCat_fastFood("990","生鲜商品运输"),
    OrderSub_ShipCat_Applicance("991","大家电"),
    OrderSub_ShipCat_Other("992","其它"),
	
    BlackList_spiteRise("spiteRise","炒货"),
    BlackList_spiteIndent("spiteIndent","恶意下单拒收"),
    BlackList_spiteComment("spiteComment","恶评");
    
    private String code;
    private int codeInt;
    private String desc;
    private long codeLong;

    private CommonConst(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private CommonConst(int codeInt, String desc) {
        this.codeInt = codeInt;
        this.desc = desc;
    }

    private CommonConst(long codeLong, String desc) {
        this.codeLong = codeLong;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public int getCodeInt() {
        return this.codeInt;
    }

    public long getCodeLong() {
        return codeLong;
    }

    public String getDesc() {
        return desc;
    }
    
}
