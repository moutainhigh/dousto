package com.ibm.oms.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderOperateLogDao;
import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.domain.persist.OrderOperateLog_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:48
 * @author:Yong Hong Luo
 */
@Repository("orderOperateLogDao")
public class OrderOperateLogDaoImpl extends BaseDaoImpl<OrderOperateLog,Long> implements OrderOperateLogDao{
	public Pager getPagerByMap(Map<String, String> map, Pager pager) {
		 CriteriaBuilder cb = getCriteriaBuilder();
		 CriteriaQuery<OrderOperateLog> c = cb.createQuery(OrderOperateLog.class);
		 Root<OrderOperateLog> root = c.from(OrderOperateLog.class);
	     c.select(root);
	     
	     List<Predicate> predicates = new ArrayList<Predicate>();
	     
	     for (Map.Entry<String, String> entry : map.entrySet()) {
	    	  if(StringUtils.isNotBlank(entry.getValue())){
		    	   Path x = root.get(entry.getKey());
		    	   predicates.add(cb.equal(x, entry.getValue()));
	    	  }
	     }

	     if(predicates.isEmpty()){
	    	 return pager;
	     }

	     
	     c.where(cb.and(predicates.toArray(new Predicate[0])));
	     return super.findByPager(c, pager);
	}
	
	public Pager findPageOrderOperateLog(OrderOperateLog log ,Map<String, String> map ,Pager pager){
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderOperateLog> c = cb.createQuery(OrderOperateLog.class);
		Root<OrderOperateLog> root = c.from(OrderOperateLog.class);
		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(!StringUtils.isBlank((log.getOrderNo()))){
		    Predicate equal = cb.equal(root.get(OrderOperateLog_.orderNo), log.getOrderNo());//订单号
		    predicates.add(equal);
		}
		
		if(!StringUtils.isBlank((log.getOrderSubNo()))){
		    Predicate equal = cb.equal(root.get(OrderOperateLog_.orderSubNo), log.getOrderSubNo());//订单号
		    predicates.add(equal);
		}
		
		if(!StringUtils.isBlank((log.getOrderItemNo()))){
		    Predicate equal = cb.equal(root.get(OrderOperateLog_.orderItemNo), log.getOrderItemNo());//订单号
		    predicates.add(equal);
		}
		
		if(null != map)
		{
			String strStartDate =map.get("strStartDate");
			
			String strEndDate =map.get("strEndDate");
			
			if(!StringUtils.isBlank((strStartDate))){
				try {
					Date startDate = DateUtils.convertStringToGeneralDateTime(strStartDate+" 00:00:00");
				
					Predicate equal = cb.greaterThanOrEqualTo(root.get(OrderOperateLog_.dateCreated),startDate);
				    predicates.add(equal);
				} catch (ParseException e) {
					
				}
			}
			
			if(!StringUtils.isBlank((strEndDate))){
				try {
					Date endDate = DateUtils.convertStringToGeneralDateTime(strEndDate+" 23:59:59");
				
					Predicate equal = cb.lessThanOrEqualTo(root.get(OrderOperateLog_.dateCreated),endDate);
					predicates.add(equal);
				} catch (ParseException e) {
					
				}
			}
		}
		
		c.where(predicates.toArray(new Predicate[0]));
		c.orderBy(cb.desc(root.get(OrderOperateLog_.id)));
		
		return super.findByPager(c, pager);
	}

	
}
