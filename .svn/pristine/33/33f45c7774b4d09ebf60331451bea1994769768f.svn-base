package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.dao.intf.HangOrderPromotionDao;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.oms.service.HangOrderMainService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("hangOrderMainService")
public class HangOrderMainSerivceImpl extends BaseServiceImpl<HangOrderMain, Long> implements HangOrderMainService {

	@Autowired
	private HangOrderMainDao hangOrderMainDao;

	@Autowired
	public void setHangOrderMainDao(HangOrderMainDao hangOrderMainDao) {
		super.setBaseDao(hangOrderMainDao);
	}

	@Override
	public List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status) {
		// TODO Auto-generated method stub
		return hangOrderMainDao.queryHangOrderMain(shopNo, startDate, endDate, status);
	}

	@Override
	public List<HangOrderMain> queryHangOrderMainDetail(String orderNo) {
		// TODO Auto-generated method stub
		return hangOrderMainDao.queryHangOrderMainDetail(orderNo);
	}

	@Override
	public int deleteHangOrderByOrderNo(String orderNo) {
		int count = 1;
		try {
			hangOrderMainDao.deleteHangOrderByOrderNo(orderNo);
		} catch (Exception e) {
			// TODO: handle exception
			count = 0;
		}
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public void updateHangOrder(HangOrderMain hangOrderMain) {
		hangOrderMainDao.updateHangOrder(hangOrderMain);
	}

	@Override
	public long queryIdByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return hangOrderMainDao.queryIdByOrderNo(orderNo);
	}
}