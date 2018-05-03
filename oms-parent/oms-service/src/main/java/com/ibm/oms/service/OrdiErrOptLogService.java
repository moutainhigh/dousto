package com.ibm.oms.service;

import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-03-14
 * 04:20:54
 * 
 * @author:Yong Hong Luo
 */
public interface OrdiErrOptLogService extends BaseService<OrdiErrOptLog, Long> {

    Pager getPagerByMap(Map<String, String> map, Pager pager);

    List<OrdiErrOptLog> findByFields(Map<String, String> map);
    
    public void processSaleAfterOrderToWms(int count);

}
