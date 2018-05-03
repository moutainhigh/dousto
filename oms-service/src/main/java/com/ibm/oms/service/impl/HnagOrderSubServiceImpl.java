package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.dao.intf.HangOrderSubDao;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.service.HangOrderSubService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;


@Service("hangOrderSubService")
public class HnagOrderSubServiceImpl extends BaseServiceImpl<HangOrderSub, Long> implements HangOrderSubService {

	@Autowired
	private HangOrderSubDao hangOrderSubDao;

	@Autowired
    public void setHangOrderSubDao(HangOrderSubDao hangOrderSubDao) {
        super.setBaseDao(hangOrderSubDao);
    }

	@Override
	public void deleteByIdOrder(long orderId) {
		hangOrderSubDao.deleteByIdOrder(orderId);
	}
}
