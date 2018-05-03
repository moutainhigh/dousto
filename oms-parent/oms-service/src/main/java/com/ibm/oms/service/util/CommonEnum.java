package com.ibm.oms.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.oms.dao.constant.CommonConst;

/**
 * 抽取CommonConst中的送货选项
 *
 */
@Component
public class CommonEnum {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    private List<CommonConst> deliveryTimeList= new ArrayList<CommonConst>();
    
    @PostConstruct
    public void init(){
    	deliveryTimeList = getSpecialList("OrderSub_HopeArrivalTime");
    }
    
    public List<CommonConst> getSpecialList(String startEnum){
    	List<CommonConst> tempList= new ArrayList<CommonConst>();
    	CommonConst [] c = CommonConst.values();
    	for (CommonConst commonConst : c) {
    			if(commonConst.toString().startsWith(startEnum)){
    				tempList.add(commonConst);
    			}
    	}
    	
    	return tempList;
    }

	public List<CommonConst> getDeliveryTimeList() {
		return deliveryTimeList;
	}

	public void setDeliveryTimeList(List<CommonConst> deliveryTimeList) {
		this.deliveryTimeList = deliveryTimeList;
	}
    
    

}
