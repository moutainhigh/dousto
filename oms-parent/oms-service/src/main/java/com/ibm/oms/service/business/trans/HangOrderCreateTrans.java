package com.ibm.oms.service.business.trans;

import java.util.List;

import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;

public interface HangOrderCreateTrans {
    
    
    //保存挂单
	List<HangOrderMain> saveOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO);
    //查询挂单头
    List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status);
    //查询挂单详情信息
    List<HangOrderMain> queryHangOrderMainDetail(String orderNo);
    //删除挂单
    BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo(String orderNO);
    //修改挂单
    BtcOmsReceiveOrderOutputDTO updateHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO);
    /*
    *//**库存扣减之后的操作**//*
    boolean savePostInventory(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context);
    *//**保存货到付款订单信息**//*
    IntfVerified saveVerified(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context);
    *//**批量保存订单**//*
    void saveBatchProcess(Map<String, OrderMain> omMap, OrderStatusAction action);
    *//**保存同步状态**//*
    public void saveStatusSyncLog(Map<String, OrderMain> omMap);

    *//**订单出库后，发送确认邮件给顾客**//*
    SupportResend querySupportSendEmail(String orderSubNo);
    
    *//**
     * 销售订单传输R3【进入待同步表】
     * @param orderMain
     * @param orderSubNoR
     *//*
    public void saveOrderToR3(OrderMain orderMain,OrderSub orderSub);

    boolean queryAutoAuditCriteria(String orderNo);
*/
	
}
