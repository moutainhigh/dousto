package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderRetChgItemDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderRetChgItem_;
import com.ibm.sc.dao.impl.BaseDaoImpl;


/**
 * DAOģʽ,
 * 
 * Creation date:2014-04-01 05:12:40
 * 
 * @author:Yong Hong Luo
 */
@Repository("orderRetChgItemDao")
public class OrderRetChgItemDaoImpl extends BaseDaoImpl<OrderRetChgItem, Long> implements OrderRetChgItemDao {
    /**
     * 通过主订单号获取明细
     * 
     * @param orderNo
     * @return
     */
    public List<OrderRetChgItem> getByOrdeNo(String orderNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderRetChgItem> c = cb.createQuery(OrderRetChgItem.class);
        Root<OrderRetChgItem> root = c.from(OrderRetChgItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderRetChgItem_.orderNo), orderNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }

    /**
     * 通过子订单号获取明细
     * 
     * @param orderSubNo
     * @return
     */
    public List<OrderRetChgItem> getByOrdeSubNo(String orderSubNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderRetChgItem> c = cb.createQuery(OrderRetChgItem.class);
        Root<OrderRetChgItem> root = c.from(OrderRetChgItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderRetChgItem_.orderSubNo), orderSubNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }

    /**
     * 通过明细单号获取
     * 
     * @param orderItemNo
     * @return
     */
    public OrderRetChgItem getByOrderRetChgItemNo(String retChgItemNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderRetChgItem> c = cb.createQuery(OrderRetChgItem.class);
        Root<OrderRetChgItem> root = c.from(OrderRetChgItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderRetChgItem_.orderItemNo), retChgItemNo);
        c.where(equal01);
        return super.getByCriteria(c);
    }

}
