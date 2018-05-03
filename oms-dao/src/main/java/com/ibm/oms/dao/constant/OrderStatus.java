package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum OrderStatus {

    /**
     * 状态类型：销售订单总状态
     */
    StatusTypeCode_SaleOrder_StatusTotal("01","销售订单总状态"),
    /**
     * 状态类型：销售订单支付状态
     */
    StatusTypeCode_SaleOrder_StatusPay("04","销售订单支付状态"),
    /**
     * 状态类型：销售订单物流状态
     */
    StatusTypeCode_SaleOrder_StatusLogistics("06","销售订单物流状态"),
    /**
     * 状态类型：订单审核状态
     */
    StatusTypeCode_SaleOrder_StatusReturnMoney("08","订单审核状态"),
    /**
     * 状态类型：逆向订单物流状态
     */
    StatusTypeCode_BackOrder_StatusTotal("02","逆向订单总状态"),
    /**
     * 状态类型：逆向订单退款状态
     */
    StatusTypeCode_BackOrder_StatusReturnMoney("05","逆向订单退款状态"),
    /**
     * 状态类型：换货状态
     */
//    StatusTypeCode_BackOrder_StatusReturnTotal("03","逆向订单换货总状态"),
    
    /**
     * 销售订单审核状态：待审核
     */
    StatusTypeCode_Order_StatusConfirm_Wait("0801","待审核"),
    /**
     * 销售订单审核状态：自动审核成功
     */
    StatusTypeCode_Order_StatusConfirm_AutoPass("0802","自动审核成功"),
    /**
     * 销售订单审核状态：人工审核成功
     */
    StatusTypeCode_Order_StatusConfirm_Pass("0804","人工审核成功"),
    /**
     * 销售订单审核状态：人工审核取消
     */
    StatusTypeCode_Order_StatusConfirm_Cancel("0805","人工审核取消"),
    
    
    /**
     * 逆向订单审核状态：已取消
     */
    StatusTypeCode_refundOrder_StatusConfirm_Cancel("0808","已取消"),
    /**
     * 逆向订单审核状态：已审核
     */
    StatusTypeCode_refundOrder_StatusConfirm_Pass("0807","已审核"),
    /**
     * 逆向订单审核状态：未审核
     */
    StatusTypeCode_refundOrder_StatusConfirm_Wait("0806","未审核"),
    
    
    
    /**
     * 销售订单创建中
     */
    ORDER_CREATING("0110","订单创建中"),
    /**
     * 锁定库存失败
     */
    ORDER_LOCK_FAIL("0111","锁定库存失败"),
    /**
     * 销售订单处理中
     */
    ORDER_PROCESSING("0120","订单处理中"),
    /**
     * 销售订单处理失败
     */
    ORDER_PROCESS_FAILED("0121","订单处理失败"),
    /**
     * 销售订单支付中
     */
    ORDER_PAYING("0130","订单支付中"),
    /**
     * 销售订单未支付取消
     */
    ORDER_PAY_CANCELLED("0131","订单未支付取消"),
    /**
     * 销售订单审核中
     */
    ORDER_AUDITING("0140","订单审核中"),
    /**
     * 订单人工审核中
     */
    ORDER_AUDITING_MANUAL("0141","订单人工审核中"),
    /**
     * 销售订单人工审核失败
     */
    ORDER_AUDIT_MANUAL_FAILED("0142","人工取消订单"),
    
    /**
     * 订单挂起
     */
    ORDER_SUSPENSION("0143","订单挂起"),
    /**
     * 销售订单库存扣减中
     */
    ORDER_INVENTORY_DEDUCTING("0150","库存扣减中"),
    /**
     * 销售订单库存扣减失败
     */
    ORDER_INVENTORY_DEDUCT_FAILED("0151","订单库存扣减失败"),
    /**
     * 销售订单已取消
     */
    ORDER_PAID_CANCEL("0153","订单已取消"),
    /**
     * 销售订单已生效待发货
     */
    ORDER_VALIDATED("0160","订单已生效待发货"),
    /**
     * 销售订单已发货
     */
    ORDER_SENT("0170","在线支付订单已发货"),
    /**
     * 货到付款订单已发货
     */
    ORDER_POD_SENT("0171","货到付款订单已发货"),
    /**
     * 销售订单已收已付
     */
    ORDER_ACCEPTED_PAID("0180","订单已收已付"),
    
    /**
     * 销售订单拒收退货
     */
    ORDER_REJECTED("0181","订单拒收退货"),
//增加已完成的状态 YUSL 2018/1/30
    /**
     * 销售订单完成
     */
    ORDER_FINISH("0190","订单已完成"),
    /**
     * 退货订单已创建
     */
    RET_ORDER_CREATING("0210", "逆向订单创建中"),
    /**
     * 退货订单审核中
     */
    RET_ORDER_AUDITING("0220", "退换货审核中"),
    /**
     * 退货订单审核失败
     */
    RET_ORDER_AUDIT_FAILED("0221", "订单审核失败"),
    /**
     * 客户寄回中
     */
    RET_ORDER_POSTING("0231", "订单客户寄回中"),
    /**
     * 订单上门取件中
     */
    RET_ORDER_FETCHING("0232", "订单上门取件中"),
    /**
     * 退货订单门店代退中
     */
    RET_ORDER_STORE_RECEIVE("0233", "订单门店代退中"),
    /**
     * 退货订单退货质检中
     */
    RET_ORDER_INSPECTION("0240", "质检中"),
    /**
     * 退货订单取消入库
     */
    RET_ORDER_INSPECTION_CANCEL("0241", "取消入库"),
    /**
     * 退货订单退货入库中
     */
    RET_ORDER_ON_THE_WAY("0250", "订单退货入库中"),

    /**
     * 退货订单质检失败
     */
    RET_ORDER_INSPECT_FAIL("0260", "订单质检失败"),
    /**
     * 退货订单质检失败
     */
    RET_ORDER_INSPECT_FAIL_RETURN("0261", "质检失败不入库"),
    /**
     * 退货订单退款中
     */
    RET_ORDER_REFUNDING("0270", "订单退款中"),
    /**
     * 门店退货入库中
     */
    RET_ORDER_STORE_ON_THE_WAY("0275", "门店退货入库中"),
    /**
     * 退换货订单已完成
     */
    RET_ORDER_RETURN_FINISHED("0280", "退换货已完成"),
    
    /**
     * 门店退换货拒绝
     */
    RET_ORDER_STORE_REJECT("0276","门店拒绝"),
    
    /**
     * 逆向订单无效
     */
    RET_ORDER_INVALID("0279","逆向订单无效"),
    
    
    /**销售订单物流状态**/
    Order_LogisticsStatus_Warehouse("0601","订单下达仓库中"),
    Order_LogisticsStatus_Inventory("0602","订单分配库存完成"),
    Order_LogisticsStatus_Pick("0603","订单拣货完成"),
    Order_LogisticsStatus_Pack("0604","订单装箱完成"),
    Order_LogisticsStatus_Collect("0605","订单已出库"),
    Order_LogisticsStatus_Send("0606","已揽收派送中"),
    Order_LogisticsStatus_StoreAccept("0607","门店已接收"),
    Order_LogisticsStatus_SendOrder("0608","已派单收货中"),
    Order_LogisticsStatus_SignFinish("0610","签收完成"),
    Order_LogisticsStatus_Reject("0611","拒收"),
    Order_LogisticsStatus_Cancel("0620","未出库已取消"),
    
    
    
    /**销售订单支付状态**/
    Order_PayStatus_Paying("0410","待支付"),
    Order_PayStatus_Success("0420","支付完成"),
    Order_PayStatus_Cancelled("0430","支付取消"),
    Order_PayStatus_Cash_Paying("0450","货到付款未支付"),
    Order_PayStatus_Cash_Success("0460","货到付款已支付"),
    Order_PayStatus_Cash_Noreturn("0470","货到付款无需退款"),
    
    /**销售订单审核状态**/
    Order_ConfirmStatus_Confirming("0801","待审核"),
    Order_ConfirmStatus_AutoSuccess("0802","自动审核成功"),
    Order_ConfirmStatus_ManConfirming("0803","人工审核中"),
    Order_ConfirmStatus_ManSuccess("0804","人工审核成功"),
    Order_ConfirmStatus_ManCancel("0805","人工审核取消"),
    
    /**退货订单退款状态**/
    RetOrder_PayStatus_Paying("0510","需退款"),
    RetOrder_PayStatus_NoNeedPay("0520","无需退款"),
    RetOrder_PayStatus_Success("0530","退款完成");
    
    
    
    
    
//    /**
//     * 换货订单创建中
//     */
//    CHG_ORDER_CREATING("0310", "换货订单创建中"),
//    /**
//     * 换货订单审核中
//     */
//    CHG_ORDER_AUDITING("0320", "换货审核中"),
//    /**
//     * 换货订单人工取消
//     */
//    CHG_ORDER_AUDIT_FAILED("0321", "换货订单审核失败"),
//    /**
//     * 换货订单旧货入库中
//     */
//    CHG_ORDER_ON_THE_WAY("0331", "旧货入库中"),
//    /**
//     * 换货订单完成
//     */
//    CHG_ORDER_FINISHED("0332", "换货已完成");
    
    
    
   
    
    
    private String code;
    private String desc;

    private OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }
    
    public static List<OrderStatus>  getAll(){  
		List<OrderStatus> allList = new ArrayList<OrderStatus>();
		allList.add(null);
        for (OrderStatus s : OrderStatus.values()){  
            allList.add(s);
        }    
	    return allList;
	} 
    
    public static String getDesc(String code){
    	 String desc = null;
    	 if (code == null) return null;
    	 for (OrderStatus s : OrderStatus.values()){  
             if (code.equalsIgnoreCase(s.getCode())){
            	 desc = s.getDesc();
            	 break;
             }
         }   
    	return desc;
    }
    
    public static List<OrderStatus>  getOrderStatusMap(){  
		List<OrderStatus> allList = new ArrayList<OrderStatus>();
		allList.add(null);
        for (OrderStatus s : OrderStatus.values()){  
            allList.add(s);
        }    
	    return allList;
	} 
    
    public static boolean isLastStatus(String targetStatus){
        return targetStatus.matches("01((31)|(53)|(80)|(81))");
    }
    
    /**
     * 校验是否为逆向订单的结束状态
     * @param targetStatus
     * @return
     */
    public static boolean isLastStatusNegative(String targetStatus){
        return targetStatus.matches("02((21)|(41)|(61)|(80))");
    }
    
    public static Map<String,OrderStatus> getStatusMap(){  
        Map<String,OrderStatus> statusMap = new HashMap<String,OrderStatus>(); 
        for (OrderStatus s : OrderStatus.values()){  
        	statusMap.put(s.getCode(), s);
        }  
	    return statusMap;
	}   

}
