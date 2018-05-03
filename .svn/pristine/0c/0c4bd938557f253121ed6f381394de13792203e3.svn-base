/**
 * 
 */
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.SelfTakePointTmp;

/**
 * @author xiaonanxiang
 *
 */
public interface SelfTakePointTmpService {
	/**
	 * 根据pointDeliverPartnerId（商户code）查询自提点
	 * @param pointDeliverPartnerId 商户code
	 * @param id 自提点id
	 * @return
	 */
	List<SelfTakePointTmp> findSelfTakePointTmpList(String pointDeliverPartnerId,String id);
	
	String findPointDeliverPartnerId(String id) ;
	public String findPointDetailAddress(String id);
}
