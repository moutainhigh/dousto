package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderPromotion Data Access Object (DAO) interface.
 * Creation date:2014-03-24 11:55:37
 * @author:Yong Hong Luo
 */
public interface OrderPromotionDao extends BaseDao<OrderPromotion,Long>{

    List<OrderPromotion> findByOrderNoAndPromoLevel(String orderNo, String promoLevel);
	
}
