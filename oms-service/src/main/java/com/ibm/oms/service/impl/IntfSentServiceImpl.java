package com.ibm.oms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.IntfSentDao;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.service.IntfSentService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:18:20
 * @author:Yong Hong Luo
 */
@Service("intfSentService")
public class IntfSentServiceImpl extends BaseServiceImpl<IntfSent,Long> implements
		IntfSentService{
    
	private IntfSentDao intfSentDao;
    
	@Autowired
	public void setIntfSentDao(IntfSentDao intfSentDao) {
	    super.setBaseDao(intfSentDao);
		this.intfSentDao = intfSentDao;
	}
	
    public Pager getPagerByMap(Map<String, String> map ,Pager pager){
		return intfSentDao.getPagerByMap(map, pager);
		
	}
}
