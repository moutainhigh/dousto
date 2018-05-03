package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderPayDao;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:49
 * @author:Yong Hong Luo
 */
@Repository("orderPayDao")
public class OrderPayDaoImpl extends BaseDaoImpl<OrderPay,Long> implements OrderPayDao{
       
    public List<OrderPay> getByOrderMainNo(String orderMainNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderPay> c = cb.createQuery(OrderPay.class);
        Root<OrderPay> root = c.from(OrderPay.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderPay_.orderNo), orderMainNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }
}
