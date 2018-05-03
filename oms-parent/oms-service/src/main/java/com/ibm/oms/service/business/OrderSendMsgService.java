package com.ibm.oms.service.business;

public interface OrderSendMsgService {

    /**
     * 未按时出库的订单给顾客发送短信
     */
    void sendMsgInStoreOrder();

}
