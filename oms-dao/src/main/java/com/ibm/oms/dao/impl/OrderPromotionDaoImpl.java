package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderPromotionDao;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.domain.persist.OrderPromotion_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-24 11:55:37
 * @author:Yong Hong Luo
 */
@Repository("orderPromotionDao")
public class OrderPromotionDaoImpl extends BaseDaoImpl<OrderPromotion,Long> implements OrderPromotionDao{
       
	@Override
	public List<OrderPromotion> findByOrderNoAndPromoLevel(String orderNo, String promoLevel){
	CriteriaBuilder cb = getCriteriaBuilder();
	CriteriaQuery<OrderPromotion> cq = cb.createQuery(OrderPromotion.class);
	Root<OrderPromotion> root = cq.from(OrderPromotion.class);
	Predicate orderNoP = cb.equal(root.get(OrderPromotion_.orderNo), orderNo);
	Predicate promoLevelP = cb.equal(root.get(OrderPromotion_.promoLevel), promoLevel);
	cq.where(new Predicate[]{orderNoP, promoLevelP});
	return super.findByCriteria(cq);
	}
}
