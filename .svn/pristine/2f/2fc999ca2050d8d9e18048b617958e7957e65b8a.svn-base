package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-04-01
 * 05:12:40
 * 
 * @author:xiaohl
 */
public interface OrderRetChgItemService extends BaseService<OrderRetChgItem, Long> {
    /**
     * 通过主订单号获取明细
     * 
     * @param orderNo
     * @return
     */
    public List<OrderRetChgItem> getByOrdeNo(String orderNo);

    /**
     * 通过子订单号获取明细
     * 
     * @param orderSubNo
     * @return
     */
    public List<OrderRetChgItem> getByOrdeSubNo(String orderSubNo);

    /**
     * 通过明细单号获取
     * 
     * @param orderItemNo
     * @return
     */
    public OrderRetChgItem getByOrderRetChgItemNo(String retChgItemNo);
}
