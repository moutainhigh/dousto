package com.ibm.oms.dao.report.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.report.OrderCategoryDao;
import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.oms.domain.persist.OrderCategory_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

@Repository("orderCategoryDao")
public class OrderCategoryDaoImpl extends BaseDaoImpl<OrderCategory, Long> implements OrderCategoryDao {
	
	// 删除标识
    public static final Long deleted_flag = 1L;
    
    // 商品运营目录
    public static final Long catalog_Type_Operate = 2L;
    
	@Override
	public List<OrderCategory> findByTreelevel(Long categoryLevel) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();		
		CriteriaQuery<OrderCategory> criteriaQuery = criteriaBuilder.createQuery(OrderCategory.class);	
		Root<OrderCategory> orderCategory = criteriaQuery.from(OrderCategory.class);		
		List<Predicate> terms = new ArrayList<Predicate>();
		Predicate categoryType = criteriaBuilder.equal(orderCategory.get(OrderCategory_.catalogId), catalog_Type_Operate);
		terms.add(categoryType);
		Predicate condition = criteriaBuilder.equal(orderCategory.get(OrderCategory_.treeLevel), categoryLevel);
		terms.add(condition);
		Predicate notDeleted = criteriaBuilder.notEqual(orderCategory.get(OrderCategory_.isDeleted), deleted_flag);
		terms.add(notDeleted);
		criteriaQuery.where(terms.toArray(new Predicate[0]));		
		criteriaQuery.orderBy(criteriaBuilder.asc(orderCategory.get(OrderCategory_.id)));
		
		return findByCriteria(criteriaQuery);
	}

	@Override
	public List<OrderCategory> findByParentId(Long parentId) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();		
		CriteriaQuery<OrderCategory> criteriaQuery = criteriaBuilder.createQuery(OrderCategory.class);	
		Root<OrderCategory> orderCategory = criteriaQuery.from(OrderCategory.class);		
		List<Predicate> terms = new ArrayList<Predicate>();
		Predicate categoryType = criteriaBuilder.equal(orderCategory.get(OrderCategory_.catalogId), catalog_Type_Operate);
		terms.add(categoryType);
		Predicate condition = criteriaBuilder.equal(orderCategory.get(OrderCategory_.parentId), parentId);
		terms.add(condition);
		Predicate notDeleted = criteriaBuilder.notEqual(orderCategory.get(OrderCategory_.isDeleted), deleted_flag);
		terms.add(notDeleted);
		criteriaQuery.where(terms.toArray(new Predicate[0]));		
		criteriaQuery.orderBy(criteriaBuilder.asc(orderCategory.get(OrderCategory_.id)));
		
		return findByCriteria(criteriaQuery);
	}
}
