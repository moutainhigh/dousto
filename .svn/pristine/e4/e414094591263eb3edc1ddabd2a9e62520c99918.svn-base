/**
 * 
 */
package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.SelfTakePointTmp;
import com.ibm.oms.service.SelfTakePointTmpService;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.sc.model.shipping.SelfTakePoint;

/**
 * @author xiaonanxiang
 *
 */
@Service("selfTakePointTmpService")
public class SelfTakePointTmpServiceImpl implements SelfTakePointTmpService {
	
	@Autowired
	private SelfTakeMerchantUtil selfTakeMerchantUtil;
	

	/* (non-Javadoc)
	 * @see com.ibm.sc.service.oms.SelfTakePointTmpService#findSelfTakePointTmpList()
	 */
	@Override
	public List<SelfTakePointTmp> findSelfTakePointTmpList(String pointDeliverPartnerId,String id) {
		List<SelfTakePointTmp> list = new ArrayList<SelfTakePointTmp>();
		if(null == pointDeliverPartnerId || "".equals(pointDeliverPartnerId))
			return list;
		// 根据pointDeliverPartnerId获取自提点
		Map<String,List<SelfTakePoint>> selfTakePointMap = selfTakeMerchantUtil.getSelfTakePointMap();
		List<SelfTakePoint> selfTakePoints = selfTakePointMap.get(pointDeliverPartnerId);
        for (SelfTakePoint selfTakePoint : selfTakePoints) {
        	SelfTakePointTmp selfTakePointTmp = new SelfTakePointTmp();
        	selfTakePointTmp.setId(selfTakePoint.getId() + "");
        	selfTakePointTmp.setPointName(selfTakePoint.getPointName());
        	selfTakePointTmp.setDetailAddress(selfTakePoint.getProvince()+selfTakePoint.getCity()+selfTakePoint.getAddress());
            if (String.valueOf(selfTakePoint.getId()).equals(id)) {
            	selfTakePointTmp.setChecked(true);
            } else {
            	selfTakePointTmp.setChecked(false);
            }
            list.add(selfTakePointTmp);
        }
        return list;
	}
	
	@Override
	public String findPointDeliverPartnerId(String id) {
		if(StringUtils.isBlank(id)||"null".equals(id)){
			return "";
		}
		// 根据pointDeliverPartnerId获取自提点
		Map<String,List<SelfTakePoint>> selfTakePointMap = selfTakeMerchantUtil.getSelfTakePointMap();
		
		for (Map.Entry<String, List<SelfTakePoint>> entry : selfTakePointMap.entrySet()) {
			   List<SelfTakePoint> pointList =  entry.getValue();
			   for (SelfTakePoint selfTakePoint : pointList) {
				   if(id.equals(String.valueOf(selfTakePoint.getId()))){
					   return entry.getKey();
				   }
			   }
	    }
		
		return "";
		
		

		/*List<SelfTakePoint> selfTakePoints = selfTakePointMap.get(pointDeliverPartnerId);
        for (SelfTakePoint selfTakePoint : selfTakePoints) {
        	SelfTakePointTmp selfTakePointTmp = new SelfTakePointTmp();
        	selfTakePointTmp.setId(selfTakePoint.getId() + "");
        	selfTakePointTmp.setPointName(selfTakePoint.getPointName());
            if (String.valueOf(selfTakePoint.getId()).equals(id)) {
            	selfTakePointTmp.setChecked(true);
            } else {
            	selfTakePointTmp.setChecked(false);
            }
            list.add(selfTakePointTmp);
        }
        return list;*/
	}
	
	@Override
	public String findPointDetailAddress(String id) {
		if(StringUtils.isBlank(id)){
			return "";
		}
		// 根据pointDeliverPartnerId获取自提点
		Map<String,List<SelfTakePoint>> selfTakePointMap = selfTakeMerchantUtil.getSelfTakePointMap();
		
		for (Map.Entry<String, List<SelfTakePoint>> entry : selfTakePointMap.entrySet()) {
			   List<SelfTakePoint> pointList =  entry.getValue();
			   for (SelfTakePoint selfTakePoint : pointList) {
				   if(id.equals(String.valueOf(selfTakePoint.getId()))){
					   return selfTakePoint.getProvince()+selfTakePoint.getCity()+selfTakePoint.getAddress();
				   }
			   }
	    }
		
		return "";
	}


}
