package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderInvoicePrint;
import com.ibm.sc.dao.BaseDao;

/**
 * OrderInvoice Data Access Object (DAO) interface. Creation date:2014-03-14 04:19:38
 * 
 * @author:Yong Hong Luo
 */
public interface OrderInvoiceDao extends BaseDao<OrderInvoice, Long> {

    /**
     * 通过主订单号获取明细
     * @param orderNo
     * @return
     */
    public List<OrderInvoice> getByOrdeNo(String orderNo);

    /**
     * 通过子订单号获取明细
     * @param orderSubNo
     * @return
     */
    public List<OrderInvoice> getByOrdeSubNo(String orderSubNo);
    
    /**
     * 通过发票号码获取
     * @param orderItemNo
     * @return
     */
    public OrderInvoice  getByOrderInvoiceNo(String invoiceNo);
}
