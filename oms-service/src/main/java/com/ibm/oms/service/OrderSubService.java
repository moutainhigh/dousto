
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:53
 * @author:Yong Hong Luo
 */
public interface OrderSubService extends BaseService<OrderSub,Long>{
	
    public List<OrderSub> getByOrderMainNo(String orderMainNo);
    
    public OrderSub getByOrderSubNo(String orderSubNo);
    
    public List<OrderSub> getByOrderMainNoForReturn(String orderMainNo);
}
