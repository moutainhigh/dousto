package com.ibm.oms.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderRetChangeDao;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChange_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAO
 * 
 * Creation date:2014-03-24 11:55:38
 * 
 * @author:Yong Hong Luo
 */
@Repository("orderRetChangeDao")
public class OrderRetChangeDaoImpl extends BaseDaoImpl<OrderRetChange, Long>
		implements OrderRetChangeDao {

	public Pager findByReturnType(String orderCategory, Pager pager) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderRetChange> c = cb.createQuery(OrderRetChange.class);
		Root<OrderRetChange> root = c.from(OrderRetChange.class);
        c.select(root);
		if(StringUtils.isNotEmpty(orderCategory)){
		    Predicate equal02 = cb.equal(root.get(OrderRetChange_.applyType),orderCategory);
	        c.where(equal02);
		}
		return super.findByPager(c, pager);
	}
}
