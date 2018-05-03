package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderCombineRelationDao;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:19:15
 * @author:Yong Hong Luo
 */
@Repository("orderCombineRelationDao")
public class OrderCombineRelationDaoImpl extends BaseDaoImpl<OrderCombineRelation,Long> implements OrderCombineRelationDao{

    @Override
    public List<OrderCombineRelation> findByOrderItemNoAndCombineNo(String orderItemNo, String combineNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderCombineRelation> c = cb.createQuery(OrderCombineRelation.class);
        Root<OrderCombineRelation> root = c.from(OrderCombineRelation.class);
        c.select(root);
        Predicate orderItemNoP = cb.equal(root.get(OrderCombineRelation_.orderItemNo), orderItemNo);// 订单号
        Predicate combineNoP = cb.equal(root.get(OrderCombineRelation_.combineNo), combineNo);// 订单号
        c.where(orderItemNoP, combineNoP);
        return super.findByCriteria(c);
    }
}
