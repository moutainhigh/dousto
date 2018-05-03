package com.ibm.oms.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.StatusActionDictDao;
import com.ibm.oms.domain.persist.StatusActionDict;
import com.ibm.oms.service.StatusActionDictService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-04-04 10:46:59
 * @author:Yong Hong Luo
 */
@Service("statusActionDictService")
public class StatusActionDictServiceImpl extends BaseServiceImpl<StatusActionDict,Long> implements
		StatusActionDictService{
    
	private StatusActionDictDao statusActionDictDao;
    
	@Autowired
	public void setStatusActionDictDao(StatusActionDictDao statusActionDictDao) {
	    super.setBaseDao(statusActionDictDao);
		this.statusActionDictDao = statusActionDictDao;
	}
}
