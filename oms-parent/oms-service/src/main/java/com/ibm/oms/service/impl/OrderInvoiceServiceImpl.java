package com.ibm.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderInvoiceDao;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:19:38
 * 
 * @author:Yong Hong Luo
 */
@Service("orderInvoiceService")
public class OrderInvoiceServiceImpl extends BaseServiceImpl<OrderInvoice, Long> implements OrderInvoiceService {

    private OrderInvoiceDao orderInvoiceDao;

    @Autowired
    public void setOrderInvoiceDao(OrderInvoiceDao orderInvoiceDao) {
        super.setBaseDao(orderInvoiceDao);
        this.orderInvoiceDao = orderInvoiceDao;
    }

    public List<OrderInvoice> getByOrdeNo(String orderNo) {
        return this.orderInvoiceDao.getByOrdeNo(orderNo);
    }

    public List<OrderInvoice> getByOrdeSubNo(String orderSubNo) {
        return this.orderInvoiceDao.getByOrdeSubNo(orderSubNo);
    }

    public OrderInvoice getByOrderInvoiceNo(String invoiceNo) {
        return this.orderInvoiceDao.getByOrderInvoiceNo(invoiceNo);
    }

}
