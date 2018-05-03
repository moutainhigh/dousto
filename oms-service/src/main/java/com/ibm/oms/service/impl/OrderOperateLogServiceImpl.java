package com.ibm.oms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderOperateLogDao;
import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:48
 * @author:Yong Hong Luo
 */
@Service("orderOperateLogService")
public class OrderOperateLogServiceImpl extends BaseServiceImpl<OrderOperateLog,Long> implements
		OrderOperateLogService{
    
	private OrderOperateLogDao orderOperateLogDao;
    
	@Autowired
	public void setOrderOperateLogDao(OrderOperateLogDao orderOperateLogDao) {
	    super.setBaseDao(orderOperateLogDao);
		this.orderOperateLogDao = orderOperateLogDao;
	}

	@Override
	public Pager getPagerByMap(Map<String, String> map, Pager pager) {
		return orderOperateLogDao.getPagerByMap(map, pager);
	}
	
	@Override
	public Pager findPagerOrderOperateLog(OrderOperateLog log ,Map<String, String> map,Pager pager){
		return orderOperateLogDao.findPageOrderOperateLog(log,map, pager);
	}
}
