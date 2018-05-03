package com.ibm.oms.service.util;


/**
 * 常量定义
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommonConstService {
	public static final String OK = "OK";
	public static final Long OKLong = 1l;
	public static final Long FAILEDLong = -1l;
	public static final Long WAITLong = 0l;
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public static final String BTC_CREATE_FAILED = "order create failed";
	public static final String INVENTORY_RESEND_FAILED = "库存锁定失败";
    public static final String BTC_CREATE_OK = "order create ok";

    public static final String PROCESS_WAIT = "0";
    public static final String PROCESS_PREFAILED = "-3";
    public static final String PROCESS_FAILED = "-1";
    public static final String PROCESSING = "-2";
    public static final String PROCESS_SUCCEED = "1";
    public static final String SUCCEED_LowerCase = "success";
    public static final Long BOOLEAN_TRUE = 1L;
    public static final Long BOOLEAN_FALSE = 0L;
    public static final String BBC_Operate = "I-BBC-OMS-01";
    
    public static final String WMS_LogiStatus_00 = "00";
    public static final String WMS_LogiStatus_40 = "40";
    public static final String WMS_LogiStatus_50 = "50";
    public static final String WMS_LogiStatus_60 = "60";
    public static final String WMS_LogiStatus_62 = "62";
    public static final String WMS_LogiStatus_63 = "63";
    public static final String WMS_LogiStatus_80 = "80"; 
    
    public static final String TMS_LogiStatus_Get_Success = "GET_SUCCESS";
    public static final String TMS_LogiStatus_Send_Order = "SEND_ORDER";
    public static final String TMS_LogiStatus_Keep_House = "KEEP_HOUSE";
    public static final String TMS_LogiStatus_Sign_Success = "SIGN_SUCCESS";
    public static final String TMS_LogiStatus_Sign_Fail = "SIGN_FAIL";
    
    /** 传输至TMS **/
    public static final String TMS_TYPE_OS = "os";// 销售单
    public static final String TMS_TYPE_HH = "hh";// 换货单
    public static final String TMS_TYPE_TH = "th";// 退货单
    public static final String TMS_TYPE_JS = "js";// 拒收单
    /** 下传给TMS的订单标识 0：正常订单 1：电器订单  **/
    public static final int TMS_FLAG_NORMAL = 0;//正常订单

    public static final String SimPrefix = "库存锁定接口返回信息：";
    public static final String Mem03Prefix = "会员资源扣减接口返回信息：";
    public static final String SUPPORT_RESEND_FAILED = "向运营系统重发短信失败";
    //会员my卡帐户类型
    public static final String MEMCARD = "savingsAccount";
    public static final String MEMPOINT = "integralRebateAccount";
    public static final String Promo01Prefix = "购物券扣减接口返回信息：";
    

}
