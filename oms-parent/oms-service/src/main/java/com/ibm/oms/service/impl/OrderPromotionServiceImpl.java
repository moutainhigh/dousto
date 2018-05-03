package com.ibm.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderPromotionDao;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-24 11:55:37
 * @author:Yong Hong Luo
 */
@Service("orderPromotionService")
public class OrderPromotionServiceImpl extends BaseServiceImpl<OrderPromotion,Long> implements
		OrderPromotionService{
    
	private OrderPromotionDao orderPromotionDao;
    
	@Autowired
	public void setOrderPromotionDao(OrderPromotionDao orderPromotionDao) {
	    super.setBaseDao(orderPromotionDao);
		this.orderPromotionDao = orderPromotionDao;
	}
	
	@Override
	public List<OrderPromotion> findByOrderNoAndLevel(String orderNo, String promoLevel){
	    
	    return orderPromotionDao.findByOrderNoAndPromoLevel(orderNo, promoLevel);
	}
	
	
}
