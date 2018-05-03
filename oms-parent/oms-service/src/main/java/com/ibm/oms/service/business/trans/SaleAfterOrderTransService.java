package com.ibm.oms.service.business.trans;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderDeliveryType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;

/**
 * 售后意向单操作类
 * 
 * @author xiaohl
 * 
 */
public interface SaleAfterOrderTransService {

    public final String OK = "OK";
    public final String FAILED = "FAILED";
    public final String Msg_OrderCategory_Empty = "退换货类型不能为空";
    public final String Msg_ApplySource_Empty = "申请来源类型不能为空";
    public final String Msg_OrderMain_Empty = "主订单不能为空";
    public final String Msg_OrderSub_Empty = "子订单不能为空";
    public final String Msg_OrderRetChgItem_Empty = "退换货明细不能为空";
    public final String Msg_OrderPay_Empty = "退款信息不能为空";
    public final String Msg_OrderPay_Empty_JS = "拒收单已付款，退款信息不能为空";
    public final String Msg_NeedPay_Empty_JS = "原订单已支付，拒收单必须选择需退款";
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
    
    public final BigDecimal transportFeeOrder_ReturnAmount = new BigDecimal(10);//运费补款单的金额【请勿修改】
    public final BigDecimal transportFeeOrder_ReturnAmount_OutArea = new BigDecimal(15);//外区域运费补款单的金额【请勿修改】
    
   

    /**
     * 创建退、换、拒收货单据
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param applySource : B2C,订单客服
     * @param orderMain 退、换货主订单
     * @param orderSub 子订单
     * @param orderPay 退款明细
     */
    public ResultDTO saveOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays);

    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param items
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetail(String orderCategory, List<OrderRetChgItem> items,String flag);

    /**
     * 保存换货记录（剩余可退记录）
     * 
     * @param rcItem
     * @param retChange
     */
    public String saverRetOrderRecord(OrderRetChgItem rcItem, OrderRetChange retChange);

    
    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param item
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetailItem(String orderCategory, OrderRetChgItem item,String flag);
    
    /**
     * 审核意向单
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateApproveSaleAfterOrder(String orderSubNoR);
    
    /** 
     * 取消意向单,置无效
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateCancelSaleAfterOrder(String orderSubNoR);
    
    /**
     * 取消确认
     * 
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateCancelConfirm(String orderSubNoR);
    
    
    /**
     * 快速入库
     * 
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateFastStorage(String orderSubNoR);
   
    /** 
     * BBC取消意向单
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateBbcCancelSaleAfterOrder(String orderSubNoR,String operateName);
    
    /**
     * BBC订单已收货
     * @param saleAfterSubNo
     * @param operateName
     * @return 
     */
    public CommonOutputDTO bbcOrderStoreReceive(String saleAfterSubNo,String operateName);
    
    /**
     * BBC退货订单审核
     * @param saleAfterSubNo
     * @param operateName
     * @return 
     */
    public CommonOutputDTO bbcReturnOrderAudit(String returnSubNo,String operateName);

    
    /**
     * 判断订单是否包含指定的支付方式
     * @param orderMain
     * @param payCode
     * @return
     */
    public boolean checkOrderPayType(OrderMain orderMain,String payCode);
    
    /**
     * 审批退款单
     * @param orderSubNoR
     * @param operator
     * @return
     */
    public ResultDTO updateApproveRefundOrder(String orderSubNoR,String operator);
    
    /**
     * 售后意向单传输R3【进入待同步表】
     * @param orderSubNoR
     */
    public void saveSaleAfterOrderToR3(String orderSubNoR);
    
    /**
     * 查看是否已创建拒收订单
     * @param orderNo
     * @return 如果创建,返回true;未创建，返回false
     */
    boolean isHadCreateRefuseOrders(String orderNo);
    
    /**
     * 创建运费补款单
     * @param orderSubNoR 逆向单的子订单号
     * @return
     */
    /**
     * 创建运费补款单
     * @param orderMainR 逆向单的主订单
     * @param orderSubNoR 逆向单的子订单号
     * @return
     */
    public ResultDTO saveTransportFeeOrder(OrderMain orderMainR, String orderSubNoR);

    public ResultDTO doSaveCancelOrder(String orderSubNo, CancelOrderConst cancelSceneEnum);
    /**
     * 取消订单（中台，定时）
     * 
     * @param orderSubNo
     * @param cancelSceneEnum
     * @return
     */  
    public ResultDTO doSaveCancelOrderByService(String orderSubNo, CancelOrderConst cancelSceneEnum);
    /**
     * 退款单
     * 
     * @param orderMain
     * @param orderSub
     * @return
     */
    public OrderSub saveRefundOrder(OrderMain orderMain, OrderSub orderSub);
    
    /**
     * 检查换货入库单、出库单是否都已经完成,如果都完成则同时写入R3待同步表
     * @param order 
     * @param chgFlag 入库单标识==》true:order是入库单， false:order是出库单
     * @return
     */
    public void saveChgOrderToR3(OrderMain order,boolean chgFlag);
    
}