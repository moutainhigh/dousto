package com.ibm.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderItemVirtualDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:46
 * @author:Yong Hong Luo
 */
@Service("orderItemVirtualService")
public class OrderItemVirtualServiceImpl extends BaseServiceImpl<OrderItemVirtual,Long> implements
		OrderItemVirtualService{
    
	private OrderItemVirtualDao orderItemVirtualDao;
    
	@Autowired
	public void setOrderItemVirtualDao(OrderItemVirtualDao orderItemVirtualDao) {
	    super.setBaseDao(orderItemVirtualDao);
		this.orderItemVirtualDao = orderItemVirtualDao;
	}
	
    public List<OrderItemVirtual> getByOrdeNo(String orderNo) {
        return this.orderItemVirtualDao.getByOrdeNo(orderNo);
    }
}
