
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-24 11:55:37
 * @author:Yong Hong Luo
 */
public interface OrderPromotionService extends BaseService<OrderPromotion,Long>{

    List<OrderPromotion> findByOrderNoAndLevel(String orderNo, String promoLevel);
	
}
