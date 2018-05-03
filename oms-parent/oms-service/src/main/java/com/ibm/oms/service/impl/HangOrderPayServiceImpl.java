package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderPayDao;
import com.ibm.oms.dao.intf.HangOrderPromotionDao;
import com.ibm.oms.domain.persist.HangOrderPay;
import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.oms.service.HangOrderPayService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;


@Service("hangOrderPayService")
public class HangOrderPayServiceImpl extends BaseServiceImpl<HangOrderPay, Long> implements HangOrderPayService {
 
	@Autowired
	private HangOrderPayDao hangOrderPayDao;
	
	@Autowired
    public void setHangOrderPayDao(HangOrderPayDao hangOrderPayDao) {
        super.setBaseDao(hangOrderPayDao);
    }

	@Override
	public void deleteByIdOrder(long orderId) {
		hangOrderPayDao.deleteByIdOrder(orderId);
		
	}

}
