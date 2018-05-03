package com.ibm.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.intf.OrderRetChgItemDao;
import com.ibm.oms.dao.intf.ProductPropertyDao;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.oms.domain.persist.ProductProperty_;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-04-01 05:12:40
 * 
 * @author:Yong Hong Luo
 */
@Service("orderRetChgItemService")
public class OrderRetChgItemServiceImpl extends BaseServiceImpl<OrderRetChgItem, Long> implements
        OrderRetChgItemService {

    
    private OrderRetChgItemDao orderRetChgItemDao;

    @Autowired
    public void setOrderRetChgItemDao(OrderRetChgItemDao orderRetChgItemDao) {
        super.setBaseDao(orderRetChgItemDao);
        this.orderRetChgItemDao = orderRetChgItemDao;
    }

    @Autowired
    OrderCombineRelationService orderCombineRelationService;
    @Autowired
    ProductPropertyDao productPropertyDao;

    public List<OrderRetChgItem> getByOrdeNo(String orderNo) {
        return this.orderRetChgItemDao.getByOrdeNo(orderNo);
    }

    /**
     * 查询售后明细
     */
    public List<OrderRetChgItem> getByOrdeSubNo(String orderSubNo) {
        List<OrderRetChgItem> list = this.orderRetChgItemDao.getByOrdeSubNo(orderSubNo);
        for (OrderRetChgItem item : list) {
            this.loadOthes(item);
        }
        return list;
    }

    public OrderRetChgItem getByOrderRetChgItemNo(String retChgItemNo) {
        return this.orderRetChgItemDao.getByOrderRetChgItemNo(retChgItemNo);
    }

    /**
     * 加载明细的其他信息
     * 
     * @param orderSub
     */
    public void loadOthes(OrderRetChgItem item) {
        if (null == item) {
            return;
        }
        // 判断是否为组合商品
        if (CommonConst.OrderItem_OrderItemClass_Suite.getCode().equals(item.getOrderItemClass())) {
            // 获取组合商品明细
            /*List<OrderCombineRelation> list = this.orderCombineRelationService.getList(OrderCombineRelation_.combineNo,
                    item.getCommodityCode());*/
            List<OrderCombineRelation> list = this.orderCombineRelationService.getList(OrderCombineRelation_.orderItemNo,
                    item.getOrderItemNo());
            item.setOrderCombineRelations(list);
        }
        // 获取色码款属性
        List<ProductProperty> productPropertyList = productPropertyDao.findByField(ProductProperty_.orderItemNo,item.getOrderItemNo());
        item.setProductPropertys(productPropertyList);
    }

}
