package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderItemDao;
import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.service.HangOrderItemService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("hangOrderItemService")
public class HangOrderItemServiceImpl extends BaseServiceImpl<HangOrderItem, Long> implements HangOrderItemService {

	@Autowired
	private HangOrderItemDao hangOrderItemDao;
	
	@Autowired
    public void setHangOrderItemDao(HangOrderItemDao hangOrderItemDao) {
        super.setBaseDao(hangOrderItemDao);
    }

	@Override
	public void deleteByIdOrder(long orderId) {
		hangOrderItemDao.deleteByIdOrder(orderId);
	}
}
