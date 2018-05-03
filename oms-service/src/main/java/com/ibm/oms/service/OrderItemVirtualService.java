
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:46
 * @author:Yong Hong Luo
 */
public interface OrderItemVirtualService extends BaseService<OrderItemVirtual,Long>{
	
	public List<OrderItemVirtual> getByOrdeNo(String orderNo);
	
}
