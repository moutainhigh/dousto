package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.dao.intf.HangOrderPromotionDao;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.oms.service.HangOrderPromotionService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;


@Service("hangOrderPromotionService")
public class HangOrderPromotionServiceImpl extends BaseServiceImpl<HangOrderPromotion, Long> implements HangOrderPromotionService {

	@Autowired
	private HangOrderPromotionDao hangOrderPromotionDao;
	
	@Autowired
    public void setHangOrderPromotionDao(HangOrderPromotionDao hangOrderPromotionDao) {
        super.setBaseDao(hangOrderPromotionDao);
    }

	@Override
	public void deleteByIdOrder(long orderId) {
		hangOrderPromotionDao.deleteByIdOrder(orderId);
		
	}

}
