package com.ibm.oms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.domain.persist.TransportArea_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-04-01 11:43:10
 * @author:Yong Hong Luo
 * @param <T>
 */
@Repository("transportAreaDao")
public class TransportAreaDaoImpl<T> extends BaseDaoImpl<TransportArea,Long> implements TransportAreaDao{

	@Override
	public List<TransportArea> findByName(String name) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		Predicate condition = criteriaBuilder.equal(employee.get(TransportArea_.areaName), name);
		
		criteriaQuery.where(condition);
		
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findByCode(String code) {
		// TODO Auto-generated method stub
		
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		Predicate condition = criteriaBuilder.equal(employee.get(TransportArea_.areaCode), code);
		
		criteriaQuery.where(condition);
		
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findShow() {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(employee.get(TransportArea_.id)));
		//criteriaQuery.groupBy(criteriaBuilder.equals(employee.get(TransportArea_.areaLevel)));
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findByParentId(Long parent_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		Predicate condition = criteriaBuilder.equal(employee.get(TransportArea_.parentId), parent_id);

		criteriaQuery.where(condition);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(employee.get(TransportArea_.id)));
		
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findByTreelevel(String areaLevel) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		Predicate condition = criteriaBuilder.equal(employee.get(TransportArea_.areaLevel), areaLevel);

		criteriaQuery.where(condition);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(employee.get(TransportArea_.id)));
		
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findById(Long id) {
		CriteriaBuilder criteriaBuilder =getCriteriaBuilder();
		
		CriteriaQuery<TransportArea> criteriaQuery = criteriaBuilder.createQuery(TransportArea.class);
		
		Root<TransportArea> employee = criteriaQuery.from(TransportArea.class);
		
		Predicate condition = criteriaBuilder.equal(employee.get(TransportArea_.id), id);
		
		criteriaQuery.where(condition);

		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<TransportArea> findByLevel(Long level) {
		CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<TransportArea> c = cb.createQuery(TransportArea.class);
    	Root<TransportArea> root = c.from(TransportArea.class);
    	c.select(root);
    	List<Predicate> predicates = new ArrayList<Predicate>();
    	Predicate predicate = cb.equal(root.get(TransportArea_.areaLevel), level);
    	predicates.add(predicate);
    	c.where(predicates.toArray(new Predicate[0]));
    	c.orderBy(cb.asc(root.get(TransportArea_.areaSequence)));
    	c.orderBy(cb.asc(root.get(TransportArea_.areaName)));
    	List<TransportArea> list = findByCriteria(c);
		return list;
	}

	@Override
	public TransportArea getTransportAreaByIdAndAreaLevel(Long id,Long areaLevel) {
		CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<TransportArea> c = cb.createQuery(TransportArea.class);
    	Root<TransportArea> root = c.from(TransportArea.class);
    	c.select(root);
    	List<Predicate> predicates = new ArrayList<Predicate>();
    	Predicate predicate1 = cb.equal(root.get(TransportArea_.id), id);
    	Predicate predicate2 = cb.equal(root.get(TransportArea_.areaLevel), areaLevel);
    	predicates.add(predicate1);
    	predicates.add(predicate2);
    	c.where(predicates.toArray(new Predicate[0]));
    	c.orderBy(cb.asc(root.get(TransportArea_.areaLevel)));
    	c.orderBy(cb.asc(root.get(TransportArea_.id)));
    	TransportArea transportArea = this.getByCriteria(c);
		return transportArea;
	}

	@Override
	public TransportArea getTransportAreaByParentIdAndAreaLevel(Long parentId,
			Long areaLevel) {
		CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<TransportArea> c = cb.createQuery(TransportArea.class);
    	Root<TransportArea> root = c.from(TransportArea.class);
    	c.select(root);
    	List<Predicate> predicates = new ArrayList<Predicate>();
    	Predicate predicate1 = cb.equal(root.get(TransportArea_.parentId), parentId);
    	Predicate predicate2 = cb.equal(root.get(TransportArea_.areaLevel), areaLevel);
    	predicates.add(predicate1);
    	predicates.add(predicate2);
    	c.where(predicates.toArray(new Predicate[0]));
    	c.orderBy(cb.asc(root.get(TransportArea_.areaLevel)));
    	c.orderBy(cb.asc(root.get(TransportArea_.parentId)));
    	TransportArea transportArea = this.getByCriteria(c);
		return transportArea;
	}

}
