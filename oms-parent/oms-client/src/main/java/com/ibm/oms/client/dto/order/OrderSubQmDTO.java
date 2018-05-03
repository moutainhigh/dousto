package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 奇门接收orderSub实体类
 * @create: 2018-04-27 13:35
 **/
public class OrderSubQmDTO implements Serializable{
    private OrderSubQm receiverInfo;

    public OrderSubQmDTO() {

    }

    public OrderSubQm getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(OrderSubQm receiverInfo) {
        this.receiverInfo = receiverInfo;
    }
}
