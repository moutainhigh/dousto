package com.ibm.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderRetAddDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-24 11:55:38
 * 
 * @author:Yong Hong Luo
 */
@Service("orderRetAddService")
public class OrderRetAddServiceImpl extends BaseServiceImpl<OrderRetAdd, Long> implements OrderRetAddService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private OrderRetAddDao orderRetAddDao;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    public void setOrderRetAddDao(OrderRetAddDao orderRetAddDao) {
        super.setBaseDao(orderRetAddDao);
        this.orderRetAddDao = orderRetAddDao;
    }

    public List<OrderRetAdd> getByOrderNo(String orderNo) {
        return this.orderRetAddDao.getByOrderNo(orderNo);
    }

    public OrderRetAdd getByOrderItemNo(String orderItemNo) {
        return this.orderRetAddDao.getByOrderItemNo(orderItemNo);
    }

}
