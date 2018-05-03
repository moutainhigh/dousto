package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-03-24
 * 11:55:38
 * 
 * @author:Yong Hong Luo
 */
public interface OrderRetAddService extends BaseService<OrderRetAdd, Long> {

    /**
     * 通过订单号获取
     * 
     * @param orderNo
     * @return
     */
    public List<OrderRetAdd> getByOrderNo(String orderNo);

    /**
     * 通过订单明细号获取
     * 
     * @param orderItemNo
     * @return
     */
    public OrderRetAdd getByOrderItemNo(String orderItemNo);

}
