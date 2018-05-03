/**
 * 
 */
package com.ibm.oms.service.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.service.OrderReasonService;

/**
 * 售后原因
 * @author xiaohl
 *
 */
@Service
public class OrderReasonUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	OrderReasonService orderReasonService;
	
	private List<OrderReason> orderReasonList;
	
	private Map<String,OrderReason> orderReasonMap = new HashMap<String,OrderReason>(); 
	
	@PostConstruct
	public void initialize() {
		logger.info("OrderReasonUtil --> initialize --> start");
		orderReasonList = orderReasonService.getAll();
		
		for(OrderReason reason:orderReasonList)
		{
		    orderReasonMap.put(reason.getReasonNo(), reason);
		}
		
		logger.info("OrderReasonUtil --> initialize  -->  end");
	}

    public List<OrderReason> getOrderReasonList() {
        return orderReasonList;
    }

    public void setOrderReasonList(List<OrderReason> orderReasonList) {
        this.orderReasonList = orderReasonList;
    }

    public Map<String, OrderReason> getOrderReasonMap() {
        return orderReasonMap;
    }

    public void setOrderReasonMap(Map<String, OrderReason> orderReasonMap) {
        this.orderReasonMap = orderReasonMap;
    }


}
