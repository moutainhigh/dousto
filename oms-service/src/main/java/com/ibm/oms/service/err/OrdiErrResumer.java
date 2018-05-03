package com.ibm.oms.service.err;


public interface OrdiErrResumer {

    String ImsDeductResumer1 = "库存调用成功";
    String ImsDeductResumer2 = "库存调用失败";
    String OrderProcessResumer1 = "处理调用成功";
    String OrderProcessResumer2 = "处理调用失败";
    String PromoAddResumer1 = "促销返券调用成功";;
    String PromoAddResumer2 = "促销返券调用失败";
    String SaleAfterOrder_WMS_Success = "售后意向单传输WMS调用成功";
    String SaleAfterOrder_WMS_Fail = "售后意向单传输WMS调用失败";
    String SaleAfterOrder_WMS_Cancel_Success = "售后意向单传输WMS调用成功";
    String SaleAfterOrder_WMS_Cancel_Fail = "售后意向单传输WMS调用失败";
    String SaleAfterOrder_Cancel_ReturnIntegral_Success = "售后意向单取消补回积分调用成功";
    String SaleAfterOrder_Cancel_ReturnIntegral_Fail = "售后意向单取消补回积分调用失败";
    String CancelOrder_ReturnMyCard_Success = "订单取消加回MY卡调用成功";
    String CancelOrder_ReturnMyCard_Fail = "订单取消加回MY卡调用失败";
    String CancelOrder_ReturnCoupon_Success = "订单取消加回购物券调用成功";
    String CancelOrder_ReturnCoupon_Fail = "订单取消加回购物券调用失败";
    
    /**
     * 是否符合重新执行的条件
     * @param errCode
     * @param orderStatus 
     * @return
     */
    Boolean match(String errCode, String orderStatus);
    /**
     * 启动执行
     * @param errId 
     * @param orderSubNo 
     * @param orderNo
     * @return
     */
    String resume(Long errId, String orderSubNo, String orderNo);
}
