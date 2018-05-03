package com.ibm.oms.service.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.shipping.SelfTakePoint_;
import com.ibm.sc.service.shipping.SelfTakePointService;

public class SelfTakePointUtil {
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	 
	 /**
	  * 自营
	  */
	 private static final Long POINTTYPEID_SELFSALE = 100L;
	 
    @Autowired
    private SelfTakePointService selfTakePointService;
    
    private Map<String,SelfTakePoint> selfTakePointMap = new HashMap<String,SelfTakePoint>(); 
    
    
    
    /**
     * 所有自提点
     */
    private List<SelfTakePoint> allSelfTakePoint; 
    
    /**
     * 自营点
     */
    private List<SelfTakePoint> selfSalePoint;
    
    
    
	@PostConstruct
	public void initialize() {
		logger.info("SelfTakePointUtil --> initialize --> start");
		List<SelfTakePoint> pointList = selfTakePointService.getAll();

		allSelfTakePoint = pointList;

		for (SelfTakePoint selfTakePoint : pointList) {
			// if(selfTakePoint.getIsAviable() &&!selfTakePoint.getDeleted()){
			selfTakePointMap.put(selfTakePoint.getId() + "", selfTakePoint);
			
			// }
		}

		// 自营点
		selfSalePoint = selfTakePointService.findByField(SelfTakePoint_.pointTypeId, POINTTYPEID_SELFSALE);
		logger.info("SelfTakePointUtil --> initialize  -->  end");

	}

	public Map<String, SelfTakePoint> getSelfTakePointMap() {
		return selfTakePointMap;
	}

	public void setSelfTakePointMap(Map<String, SelfTakePoint> selfTakePointMap) {
		this.selfTakePointMap = selfTakePointMap;
	}

	public SelfTakePointService getSelfTakePointService() {
		return selfTakePointService;
	}

	public void setSelfTakePointService(SelfTakePointService selfTakePointService) {
		this.selfTakePointService = selfTakePointService;
	}

	public List<SelfTakePoint> getAllSelfTakePoint() {
		return allSelfTakePoint;
	}

	public void setAllSelfTakePoint(List<SelfTakePoint> allSelfTakePoint) {
		this.allSelfTakePoint = allSelfTakePoint;
	}

	public List<SelfTakePoint> getSelfSalePoint() {
		return selfSalePoint;
	}

	public void setSelfSalePoint(List<SelfTakePoint> selfSalePoint) {
		this.selfSalePoint = selfSalePoint;
	}

}
