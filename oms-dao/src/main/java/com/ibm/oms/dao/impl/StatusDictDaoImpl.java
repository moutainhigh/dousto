package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.StatusDictDao;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.StatusDict_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-04-04 10:47:20
 * @author:Yong Hong Luo
 */
@Repository("statusDictDao")
public class StatusDictDaoImpl extends BaseDaoImpl<StatusDict,Long> implements StatusDictDao{

	@Override
	public List<StatusDict> findStatusDictByStatusTypeCode(String statusTypeCode) {
		
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<StatusDict> criteriaQuery = criteriaBuilder.createQuery(StatusDict.class);
		
		Root<StatusDict> statusDict = criteriaQuery.from(StatusDict.class);
		
		Predicate condition = criteriaBuilder.equal(statusDict.get(StatusDict_.statusTypeCode), statusTypeCode);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(statusDict.get(StatusDict_.statusCode)));
		
		criteriaQuery.where(condition);
		
		return findByCriteria(criteriaQuery);
	}
       
	
}
