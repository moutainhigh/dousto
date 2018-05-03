package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.sc.dao.BaseDao;

/**
 * OrderRetAdd Data Access Object (DAO) interface. Creation date:2014-03-24 11:55:38
 * 
 * @author:Yong Hong Luo
 */
public interface OrderRetAddDao extends BaseDao<OrderRetAdd, Long> {

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
