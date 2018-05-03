package com.ibm.oms.service;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-03-24
 * 11:55:38
 * 
 * @author:Yong Hong Luo
 */
public interface OrderRetChangeService extends BaseService<OrderRetChange, Long> {

    /**
     * 根据退货类型查询 逆向订单
     * 
     * @param returnType 逆向类型
     * @param returnType 退货类型
     * @param pager
     * @return
     */
    public Pager findByReturnType(String orderCategory, Pager pager);

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
    public OrderRetChange createOrderRetChange(String applySource, OrderMain retOrderMain, OrderRetChgItem retItem);

}
