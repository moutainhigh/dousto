package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderItemVirtualDao;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderItemVirtual_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:46
 * @author:Yong Hong Luo
 */
@Repository("orderItemVirtualDao")
public class OrderItemVirtualDaoImpl extends BaseDaoImpl<OrderItemVirtual,Long> implements OrderItemVirtualDao{

	@Override
	public List<OrderItemVirtual> getByOrdeNo(String orderNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderItemVirtual> c = cb.createQuery(OrderItemVirtual.class);
        Root<OrderItemVirtual> root = c.from(OrderItemVirtual.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderItemVirtual_.orderNo), orderNo);
        c.where(equal01);
        return super.findByCriteria(c);
	}
       
	
}
