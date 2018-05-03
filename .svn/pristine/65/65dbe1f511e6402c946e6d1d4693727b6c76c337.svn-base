package com.ibm.oms.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrdiErrOptLogDao;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.OrdiErrOptLog_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:54
 * @author:Yong Hong Luo
 */
@Repository("ordiErrOptLogDao")
public class OrdiErrOptLogDaoImpl extends BaseDaoImpl<OrdiErrOptLog,Long> implements OrdiErrOptLogDao{

	@Override
	public Pager getPagerByMap(Map<String, String> map, Pager pager) {
		 CriteriaBuilder cb = getCriteriaBuilder();
		 CriteriaQuery<OrdiErrOptLog> c = cb.createQuery(OrdiErrOptLog.class);
		 Root<OrdiErrOptLog> root = c.from(OrdiErrOptLog.class);
	     c.select(root);
	     
	     List<Predicate> predicates = new ArrayList<Predicate>();
	     
	    String orderNo = map.get("orderNo");
	    
	    if(!StringUtils.isBlank((orderNo))){
		    Predicate equal = cb.equal(root.get(OrdiErrOptLog_.orderNo), orderNo);//订单号
		    predicates.add(equal);
		}
		
		String orderSubNo =  map.get("orderSubNo");
	    if(!StringUtils.isBlank((orderSubNo))){
		    Predicate equal = cb.equal(root.get(OrdiErrOptLog_.orderSubNo), orderSubNo);//子订单号
		    predicates.add(equal);
		}
		
		String orderItemNo =  map.get("orderItemNo");
	    if(!StringUtils.isBlank((orderItemNo))){
		    Predicate equal = cb.equal(root.get(OrdiErrOptLog_.orderItemNo), orderItemNo);//行订单号
		    predicates.add(equal);
		}
	    String errorCode =  map.get("errorCode");
	    if(!StringUtils.isBlank((errorCode))){
	        Predicate equal = cb.equal(root.get(OrdiErrOptLog_.errorCode), errorCode);//错误码
	        predicates.add(equal);
	    }
	     
	     String strStartDate =map.get("strStartDate");
			
		 String strEndDate =map.get("strEndDate");
		
		 if(!StringUtils.isBlank((strStartDate))){
			try {
				Date startDate = DateUtils.convertStringToGeneralDateTime(strStartDate+" 00:00:00");
				Predicate equal = cb.greaterThanOrEqualTo(root.get(OrdiErrOptLog_.operateTime),startDate);
				predicates.add(equal);
			} catch (ParseException e) {
				
			}
		 }
		
		 if(!StringUtils.isBlank((strEndDate))){
			try {
				Date endDate = DateUtils.convertStringToGeneralDateTime(strEndDate+" 23:59:59");
				Predicate equal = cb.lessThanOrEqualTo(root.get(OrdiErrOptLog_.operateTime),endDate);
				predicates.add(equal);
			} catch (ParseException e) {
				
			}
		 }

	     c.where(cb.and(predicates.toArray(new Predicate[0])));
	     c.orderBy(cb.desc(root.get(OrdiErrOptLog_.id)));  
	     return super.findByPager(c, pager);
	}
}
