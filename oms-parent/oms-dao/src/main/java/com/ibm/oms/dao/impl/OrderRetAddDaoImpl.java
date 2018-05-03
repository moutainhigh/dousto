package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderRetAddDao;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetAdd_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-24 11:55:38
 * @author:Yong Hong Luo
 */
@Repository("orderRetAddDao")
public class OrderRetAddDaoImpl extends BaseDaoImpl<OrderRetAdd,Long> implements OrderRetAddDao{
       
    /**
     * 通过订单号获取
     * @param orderNo
     * @return
     */
    public List<OrderRetAdd> getByOrderNo(String orderNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderRetAdd> c = cb.createQuery(OrderRetAdd.class);
        Root<OrderRetAdd> root = c.from(OrderRetAdd.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderRetAdd_.orderNo), orderNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }
    
    /**
     * 通过订单明细号获取
     * @param OrderRetAddNo
     * @return
     */
    public OrderRetAdd getByOrderItemNo(String orderItemNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderRetAdd> c = cb.createQuery(OrderRetAdd.class);
        Root<OrderRetAdd> root = c.from(OrderRetAdd.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderRetAdd_.orderItemNo), orderItemNo);
        c.where(equal01);
        return super.getByCriteria(c); 
    }
}
