package com.ibm.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.IntfVerifiedDao;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:18:41
 * @author:Yong Hong Luo
 */
@Service("intfVerifiedService")
public class IntfVerifiedServiceImpl extends BaseServiceImpl<IntfVerified,Long> implements
		IntfVerifiedService{
    
	private IntfVerifiedDao intfVerifiedDao;
    
	@Autowired
	public void setIntfVerifiedDao(IntfVerifiedDao intfVerifiedDao) {
	    super.setBaseDao(intfVerifiedDao);
		this.intfVerifiedDao = intfVerifiedDao;
	}

    @Override
    public List<IntfVerified> findByCriteriaPredicate(String intfCode, int count, String processFlag) {
        return intfVerifiedDao.findByIntfCodeAndCount(intfCode, count, processFlag);
    }
}
