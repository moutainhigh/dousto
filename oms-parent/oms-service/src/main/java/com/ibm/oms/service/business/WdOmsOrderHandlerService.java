/**
 * 
 */
package com.ibm.oms.service.business;

import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;

/**
 * 微店订单操作业务
 * @author xiaonanxiang
 *
 */
public interface WdOmsOrderHandlerService {
    /**
     * 销售订单总状态
     */
    public static final String ORDER_STATUS_TOTAL = "01";
    /**
     * 销售订单物流状态
     */
    public static final String ORDER_STATUS_LOGISTICS = "06";
    
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String ORDER_MAIN_NULL = "订单不存在！";
    public static final String OK = "调用成功！";
    
    /**
     * 根据子订单号查询订单状态
     * @param orderSubNo 子订单号
     * @param statusType 状态类型  "01":"销售订单总状态","02":"逆向订单总状态","04":"销售订单支付状态",
     *                   "05":"逆向订单退款状态","06","销售订单物流状态"
     * @return
     */
    public OMSStatusUpdateDTO queryOrderStatus(String orderSubNo,String statusType);
}
