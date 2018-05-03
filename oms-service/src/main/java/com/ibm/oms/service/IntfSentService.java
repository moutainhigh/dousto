
package com.ibm.oms.service;

import java.util.Map;

import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:18:20
 * @author:Yong Hong Luo
 */
public interface IntfSentService extends BaseService<IntfSent,Long>{
	public Pager getPagerByMap(Map<String, String> map ,Pager pager);
}
