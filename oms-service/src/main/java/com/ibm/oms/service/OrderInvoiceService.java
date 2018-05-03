package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-03-14
 * 04:19:38
 * 
 * @author:Yong Hong Luo
 */
public interface OrderInvoiceService extends BaseService<OrderInvoice, Long> {
    /**
     * 通过主订单号获取明细
     * 
     * @param orderNo
     * @return
     */
    public List<OrderInvoice> getByOrdeNo(String orderNo);

    /**
     * 通过子订单号获取明细
     * 
     * @param orderSubNo
     * @return
     */
    public List<OrderInvoice> getByOrdeSubNo(String orderSubNo);

    /**
     * 通过发票号码获取
     * 
     * @param orderItemNo
     * @return
     */
    public OrderInvoice getByOrderInvoiceNo(String invoiceNo);
}
