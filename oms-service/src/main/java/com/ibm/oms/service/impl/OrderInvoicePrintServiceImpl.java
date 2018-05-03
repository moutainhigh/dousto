package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderInvoicePrintDao;
import com.ibm.oms.domain.persist.OrderInvoicePrint;
import com.ibm.oms.service.OrderInvoicePrintService;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:19:49
 * @author:Yong Hong Luo
 */
@Service("orderInvoicePrintService")
public class OrderInvoicePrintServiceImpl extends BaseServiceImpl<OrderInvoicePrint,Long> implements
		OrderInvoicePrintService{
    
	private OrderInvoicePrintDao orderInvoicePrintDao;
    
	@Autowired
	public void setOrderInvoicePrintDao(OrderInvoicePrintDao orderInvoicePrintDao) {
	    super.setBaseDao(orderInvoicePrintDao);
		this.orderInvoicePrintDao = orderInvoicePrintDao;
	}
}
