/**
 * 
 */
package com.ibm.oms.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.shipping.SelfTakePoint_;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.shipping.SelfTakePointService;
import com.ibm.sc.service.sys.OptionService;

/**
 * @author xiaonanxiang
 *
 */
@Service
public class SelfTakeMerchantUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OptionService optionService;
	@Autowired
	private SelfTakePointService selfTakePointService;
	
	private List<Option> selfTakeMerchantList = new ArrayList<Option>();
	
	private Map<String,List<SelfTakePoint>> selfTakePointMap = new HashMap<String,List<SelfTakePoint>>();
	
	@PostConstruct
	public void initialize() {
		logger.info("SelfTakeMerchantUtil --> initialize --> start");
		selfTakeMerchantList = optionService.findByGroupId(Long.valueOf(CommonConst.Option_OptionGroupId_Merchant.getCode()));
		
		List<SelfTakePoint> selfTakePoints = null;
		for(Option option:selfTakeMerchantList)
		{
			// 根据商户code查询自提点
			selfTakePoints = selfTakePointService.findByField(SelfTakePoint_.pointDeliverPartnerId, option.getCode());
			selfTakePointMap.put(option.getCode(), selfTakePoints);
		}
		logger.info("SelfTakeMerchantUtil --> initialize  -->  end");

	}

	
	/**
	 * 根据自提点id获取自提商户
	 * @param selfTakePointId 自提点id
	 * @return 商户对应的code为：SelfTakePoint.pointDeliverPartnerId <br/>
	 * 		      商户对应的name为：SelfTakePoint.pointDeliverPartner
	 */
	public SelfTakePoint getMerchantBySelfTakePointId(Long selfTakePointId){
		SelfTakePoint merchant = null;
		if(null == selfTakePointId)
		{
			return merchant;
		}
		SelfTakePoint selfTakePoint =selfTakePointService.get(selfTakePointId);
		if(null == selfTakePoint)
		{
			return merchant;
		}
		merchant = selfTakePoint;
		
		return merchant;
	}
	
	
	
	public List<Option> getSelfTakeMerchantList() {
		return selfTakeMerchantList;
	}

	public void setSelfTakeMerchantList(List<Option> selfTakeMerchantList) {
		this.selfTakeMerchantList = selfTakeMerchantList;
	}

	public Map<String, List<SelfTakePoint>> getSelfTakePointMap() {
		return selfTakePointMap;
	}

	public void setSelfTakePointMap(
			Map<String, List<SelfTakePoint>> selfTakePointMap) {
		this.selfTakePointMap = selfTakePointMap;
	}

}
