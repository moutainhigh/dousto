package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderRetChangeDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-24 11:55:38 销售订单与逆向订单的明细关联 操作类
 * 
 * @author:xiaohl
 */
@Service("orderRetChangeService")
public class OrderRetChangeServiceImpl extends BaseServiceImpl<OrderRetChange, Long> implements OrderRetChangeService {

    private OrderRetChangeDao orderRetChangeDao;

    @Autowired
    public void setOrderRetChangeDao(OrderRetChangeDao orderRetChangeDao) {
        super.setBaseDao(orderRetChangeDao);
        this.orderRetChangeDao = orderRetChangeDao;
    }

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetAddService orderRetAddService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderStatusService orderStatusService;

    /**
     * 通过退换货类型查询
     */
    public Pager findByReturnType(String orderCategory, Pager pager) {
        return this.orderRetChangeDao.findByReturnType(orderCategory, pager);
    }

    /**
     * 构建明细之间的关联关系数据
     * 
     * @param applySource
     * @param changeType
     * @param returnType
     * @param retOrderMain
     * @param retItem
     * @return
     * 
     */
    public OrderRetChange createOrderRetChange(String applySource, OrderMain retOrderMain, OrderRetChgItem retItem) {
        OrderRetChange retChange = new OrderRetChange();
        retChange.setIdOldOrder(retOrderMain.getRefOrderId());
        retChange.setOldOrderNo(retOrderMain.getRefOrderNo());// OMS原销售订单
        retChange.setIdOldOrderItem(retItem.getRefOrderItemId());
        retChange.setOldOrderItemNo(retItem.getRefOrderItemNo());// OMS原销售订单明细
        retChange.setIdRetOrder(retOrderMain.getId());
        retChange.setRetOrderNo(retOrderMain.getOrderNo());// 退换货订单
        retChange.setIdRetOrderItem(retItem.getId());
        retChange.setRetOrderItemNo(retItem.getOrderItemNo());// 退换货的明细
        retChange.setApplyType(retOrderMain.getOrderCategory());// 退换货类型
        retChange.setApplySource(applySource);// 来源：B2C、OMS
        retChange.setOrderSource(retOrderMain.getOrderSource());
        return retChange;
    }

}
