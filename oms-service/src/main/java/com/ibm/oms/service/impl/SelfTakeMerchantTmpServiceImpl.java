/**
 * 
 */
package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.SelfTakeMerchantTmp;
import com.ibm.oms.service.SelfTakeMerchantTmpService;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.sc.model.sys.Option;

/**
 * @author xiaonanxiang
 *
 */
@Service("selfTakeMerchantTmpService")
public class SelfTakeMerchantTmpServiceImpl implements SelfTakeMerchantTmpService {
	
	@Autowired
	private SelfTakeMerchantUtil selfTakeMerchantUtil;
	

	@Override
	public List<SelfTakeMerchantTmp> findTakeMerchantTmpList(String code) {
		List<SelfTakeMerchantTmp> list = new ArrayList<SelfTakeMerchantTmp>();
        List<Option> options = selfTakeMerchantUtil.getSelfTakeMerchantList();
        for (Option option : options) {
        	SelfTakeMerchantTmp selfTakeMerchant = new SelfTakeMerchantTmp();
        	selfTakeMerchant.setId(option.getId() + "");
        	selfTakeMerchant.setOptionGroupId(String.valueOf(option.getOptionGroupId()));
        	selfTakeMerchant.setCode(option.getCode());
        	selfTakeMerchant.setName(option.getName());
            if (String.valueOf(option.getCode()).equals(code)) {
            	selfTakeMerchant.setChecked(true);
            } else {
            	selfTakeMerchant.setChecked(false);
            }
            list.add(selfTakeMerchant);
        }
        return list;
	}

	
}
