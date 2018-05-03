package com.ibm.oms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.IntfVerifiedDao;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.IntfVerified_;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:18:41
 * @author:Yong Hong Luo
 */
@Repository("intfVerifiedDao")
public class IntfVerifiedDaoImpl extends BaseDaoImpl<IntfVerified,Long> implements IntfVerifiedDao{
       @Override
    public List<IntfVerified> findByIntfCodeAndCount(String intfCode, int count, String processFlag){
         CriteriaBuilder cb = getCriteriaBuilder();
         CriteriaQuery<IntfVerified> c = cb.createQuery(IntfVerified.class);
         Root<IntfVerified> root = c.from(IntfVerified.class);
         c.select(root);
         List<Predicate> predicates = new ArrayList<Predicate>();
         Predicate equal01 = cb.equal(root.get(IntfVerified_.intfCode), intfCode);
         Predicate equal02 = cb.equal(root.get(IntfVerified_.processFlag), processFlag);
         predicates.add(equal02);
         predicates.add(equal01);
         c.where(predicates.toArray(new Predicate[0]));
         return super.findByCriteria(c, 0, count);
       }
	
}
