package com.ibm.oms.service.business.trans;

import java.math.BigDecimal;
import java.util.Map;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;

public interface OrderCreateTrans {
    
    /**总金额大于2000**/
    BigDecimal totalPayAmountLimit = new BigDecimal(2000);
    /**单品大于1000**/
    BigDecimal itemPayAmountLimit = new BigDecimal(1000);
    /**同一IP当天订单数大于3**/
    int orderCountLimit = 3;
    
    
    boolean saveOrder(BtcOmsReceiveOrderDTO orderReceiveDIO,
            ContextBtcOmsReceiveDTO context);

    /**库存扣减之后的操作**/
    boolean savePostInventory(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context);
    /**保存货到付款订单信息**/
    IntfVerified saveVerified(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context);
    /**批量保存订单**/
    void saveBatchProcess(Map<String, OrderMain> omMap, OrderStatusAction action);
    /**保存同步状态**/
    public void saveStatusSyncLog(Map<String, OrderMain> omMap);

    /**订单出库后，发送确认邮件给顾客**/
    SupportResend querySupportSendEmail(String orderSubNo);
    
    /**
     * 销售订单传输R3【进入待同步表】
     * @param orderMain
     * @param orderSubNoR
     */
    public void saveOrderToR3(OrderMain orderMain,OrderSub orderSub);

    boolean queryAutoAuditCriteria(String orderNo);

}
