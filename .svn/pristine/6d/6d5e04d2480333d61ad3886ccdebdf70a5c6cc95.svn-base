package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderSubDao;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:53
 * @author:Yong Hong Luo
 */
@Repository("orderSubDao")
public class OrderSubDaoImpl extends BaseDaoImpl<OrderSub,Long> implements OrderSubDao{
       
    public List<OrderSub> getByOrderMainNo(String orderMainNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderSub> c = cb.createQuery(OrderSub.class);
        Root<OrderSub> root = c.from(OrderSub.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderSub_.orderNo), orderMainNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }
    
    public OrderSub getByOrderSubNo(String orderSubNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderSub> c = cb.createQuery(OrderSub.class);
        Root<OrderSub> root = c.from(OrderSub.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderSub_.orderSubNo), orderSubNo);
        c.where(equal01);
        return super.getByCriteria(c);
    }
    
}
