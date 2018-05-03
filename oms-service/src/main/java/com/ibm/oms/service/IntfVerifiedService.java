
package com.ibm.oms.service;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:18:41
 * @author:Yong Hong Luo
 */
public interface IntfVerifiedService extends BaseService<IntfVerified,Long>{

    List<IntfVerified> findByCriteriaPredicate(String intfCode, int count, String processFlag);
	
}
