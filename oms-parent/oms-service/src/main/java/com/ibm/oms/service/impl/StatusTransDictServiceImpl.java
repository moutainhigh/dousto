package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.StatusTransDictDao;
import com.ibm.oms.domain.persist.StatusTransDict;
import com.ibm.oms.service.StatusTransDictService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-04-04 10:47:31
 * 
 * @author:Yong Hong Luo
 */
@Service("statusTransDictService")
public class StatusTransDictServiceImpl extends BaseServiceImpl<StatusTransDict, Long> implements
        StatusTransDictService {

    private StatusTransDictDao statusTransDictDao;

    @Autowired
    public void setStatusTransDictDao(StatusTransDictDao statusTransDictDao) {
        super.setBaseDao(statusTransDictDao);
        this.statusTransDictDao = statusTransDictDao;
    }
}
