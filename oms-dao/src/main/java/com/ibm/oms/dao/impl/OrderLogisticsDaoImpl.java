package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderLogisticsDao;
import com.ibm.oms.domain.persist.OrderLogistics;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月15日 
 */

@Repository("orderLogisticsDao")
public class OrderLogisticsDaoImpl extends BaseDaoImpl<OrderLogistics, Long> implements OrderLogisticsDao{

	
}
