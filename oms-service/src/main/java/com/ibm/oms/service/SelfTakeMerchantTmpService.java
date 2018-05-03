/**
 * 
 */
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.SelfTakeMerchantTmp;

/**
 * @author xiaonanxiang
 * 
 */
public interface SelfTakeMerchantTmpService {

   

    /**
     * @param code 
     * @return
     */
    List<SelfTakeMerchantTmp> findTakeMerchantTmpList(String code);

}
