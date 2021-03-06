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

import com.ibm.oms.dao.intf.IntfReceivedDao;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfReceived_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
@Repository("intfReceivedDao")
public class IntfReceivedDaoImpl extends BaseDaoImpl<IntfReceived,Long> implements IntfReceivedDao{
       
	@Override
	public Pager getPagerByMap(Map<String, String> map, Pager pager) {
		
		String intfCode = map.get("intfCode");
		
		String orderNo =  map.get("orderNo");
		String orderSubNo =  map.get("orderSubNo");
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<IntfReceived> c = cb.createQuery(IntfReceived.class);
		Root<IntfReceived> root = c.from(IntfReceived.class);
		c.select(root);
		 List<Predicate> predicates = new ArrayList<Predicate>();
		if(!StringUtils.isBlank((intfCode))){
		    Predicate equal = cb.equal(root.get(IntfReceived_.intfCode), intfCode);//订单号
		   // c.where(equal);
		    predicates.add(equal);
		}
		
		String strStartDate =map.get("strStartDate");
		
		String strEndDate =map.get("strEndDate");
		
		if(!StringUtils.isBlank((strStartDate))){
			try {
				Date startDate = DateUtils.convertStringToGeneralDateTime(strStartDate+" 00:00:00");
			
				Predicate equal = cb.greaterThanOrEqualTo(root.get(IntfReceived_.createTime),startDate);
				 predicates.add(equal);
			} catch (ParseException e) {
				
			}
		}
		
		if(!StringUtils.isBlank((strEndDate))){
			try {
				Date endDate = DateUtils.convertStringToGeneralDateTime(strEndDate+" 23:59:59");
			
				Predicate equal = cb.lessThanOrEqualTo(root.get(IntfReceived_.createTime),endDate);
				 predicates.add(equal);
			} catch (ParseException e) {
				
			}
		}
		
		if(!StringUtils.isBlank((orderNo))){
			Predicate equal = cb.like(root.get(IntfReceived_.msg),"%"+orderNo+"%");
			predicates.add(equal);
		}
		if(!StringUtils.isBlank((orderSubNo))){
		    Predicate equal = cb.like(root.get(IntfReceived_.msg),"%"+orderSubNo+"%");
		    predicates.add(equal);
		}
		
		 c.where(predicates.toArray(new Predicate[0]));
		
		return super.findByPager(c, pager);
	}
}
