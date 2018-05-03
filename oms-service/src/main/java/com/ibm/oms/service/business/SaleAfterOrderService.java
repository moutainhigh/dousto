package com.ibm.oms.service.business;

import java.util.List;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderDeliveryType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.ResultDTO;

/**
 * 售后意向单操作类
 * 
 * @author xiaohl
 * 
 */
public interface SaleAfterOrderService {

    public final String OK = "OK";
    public final String FAILED = "FAILED";
    public final String Msg_OrderCategory_Empty = "退换货类型不能为空";
    public final String Msg_ApplySource_Empty = "申请来源类型不能为空";
    public final String Msg_OrderMain_Empty = "主订单不能为空";
    public final String Msg_OrderSub_Empty = "子订单不能为空";
    public final String Msg_OrderRetChgItem_Empty = "退换货明细不能为空";
    public final String Msg_OrderPay_Empty = "退款信息不能为空";
    public final String Msg_OrderPay_Empty_JS = "拒收单已付款，退款信息不能为空";
    public final String Msg_RefOrderNo_Empty = "关联的原订单号不能为空";// 即由哪张销售订单所产生的退换货
    public final String Msg_RefOrderItemId_Empty = "关联的原明细ID不能为空";
    public final String Msg_RefOrderItemNo_Empty = "关联的原明细No不能为空";

    public final String errorMsgRemainNum = "可剩余退货数量小于当前的退货数量";
    public final String errorMsgRemainMoney = "可剩余退款金额小于当前的退款金额";

    public final String errorMsg_AddExcOrder_Fail = "换货出库单添加失败";
    public final String errorMsg_AddExcOrder_Fail_InventoryLock = "换货出库单添加失败：锁定库存不成功";

    // 换货出库单的配送方式(默认为天虹配送)
    public final String ExcOrder_DeliveryMode = OrderDeliveryType.RAINBOW_SEND.getCode();
    // 更新意向单明细的标识
    public final String updateDetailFlag_delete = "delete";
    public final String updateDetailFlag_update = "update";
    // 审核/取消意向单状态的标识
    public final String updateConfirmStatus_confirm = "1"; //审核通过
    public final String updateConfirmStatus_cancel = "0"; //取消

    /**
     * 创建退、换、拒收货单据
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param applySource : B2C,订单客服
     * @param orderMain 退、换货主订单
     * @param orderSub 子订单
     * @param orderPay 退款明细
     */
    public ResultDTO createOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays);

    /**
     * @param orderSubNo
     * @param cancelorderSceneCustomer
     */
    public ResultDTO cancelOrder(String orderSubNo, CancelOrderConst cancelScene);

}