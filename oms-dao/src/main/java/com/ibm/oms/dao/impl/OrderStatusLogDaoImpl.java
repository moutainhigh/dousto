package com.ibm.oms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderStatusLogDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderStatusLog_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:51
 * @author:Yong Hong Luo
 */
@Repository("orderStatusLogDao")
public class OrderStatusLogDaoImpl extends BaseDaoImpl<OrderStatusLog,Long> implements OrderStatusLogDao{
	
	private static final int status_type_main = 1;
	
	private static final int status_type_logistics = 2;
	
	private static final int status_type_third = 3;
	
	@Override
	public Pager findOrderStatusLog(String orderNo, int statusType, Pager pager) {
		
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderStatusLog> c = cb.createQuery(OrderStatusLog.class);
		Root<OrderStatusLog> root = c.from(OrderStatusLog.class);
		c.select(root);
        List<Predicate> terms = new ArrayList<Predicate>();
		Predicate equal = cb.equal(root.get(OrderStatusLog_.orderNo), orderNo);
		terms.add(equal);
		Predicate item = null;
		if(statusType == status_type_main){
			item = cb.like(root.get(OrderStatusLog_.currentStatus), "01%");
		} else if (statusType == status_type_logistics) {
			item = cb.like(root.get(OrderStatusLog_.currentStatus), "06%");
		} else {
			item = cb.equal(root.get(OrderStatusLog_.createdBy), "ThirdTmsLog");
		}
		terms.add(item);
		c.where(terms.toArray(new Predicate[0]));
		c.orderBy(cb.asc(root.get(OrderStatusLog_.dateCreated)));  
		return super.findByPager(c, pager);
	}	
	

	@Override
    public OrderStatusLog findSuspensionByOrderNo(String orderNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderStatusLog> c = cb.createQuery(OrderStatusLog.class);
        Root<OrderStatusLog> root = c.from(OrderStatusLog.class);
        c.orderBy(cb.desc(root.get(OrderStatusLog_.operateTime)));
        c.select(root);
        List<Predicate> terms = new ArrayList<Predicate>();
        Predicate equal01 = cb.equal(root.get(OrderStatusLog_.orderNo), orderNo);;

        Predicate equal02 = cb.equal(root.get(OrderStatusLog_.currentStatus), "0143");;

        
        terms.add(equal01);
        terms.add(equal02);
        c.where(terms.toArray(new Predicate[0]));
        return super.getByCriteria(c);
    }
	
	
	@Override
	public List<OrderStatusLog> findByOrderNo(String orderNo) {
		
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<OrderStatusLog> criteriaQuery = criteriaBuilder.createQuery(OrderStatusLog.class);
		
		Root<OrderStatusLog> root = criteriaQuery.from(OrderStatusLog.class);
		
		Predicate condition = criteriaBuilder.equal(root.get(OrderStatusLog_.orderNo), orderNo);
		
		criteriaQuery.where(condition);
		
		return findByCriteria(criteriaQuery);
	}
}