package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.StatusDictDao;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.service.StatusDictService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-04-04 10:47:20
 * 
 * @author:Yong Hong Luo
 */
@Service("statusDictService")
public class StatusDictServiceImpl extends BaseServiceImpl<StatusDict, Long> implements StatusDictService {

    private StatusDictDao statusDictDao;

    @Autowired
    public void setStatusDictDao(StatusDictDao statusDictDao) {
        super.setBaseDao(statusDictDao);
        this.statusDictDao = statusDictDao;
    }
}
