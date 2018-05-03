package com.ibm.oms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderInvoiceDao;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderInvoice_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.sc.dao.impl.BaseDaoImpl;


/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:19:38
 * 
 * @author:xiaohl
 */
@Repository("orderInvoiceDao")
public class OrderInvoiceDaoImpl extends BaseDaoImpl<OrderInvoice, Long> implements OrderInvoiceDao {

    public List<OrderInvoice> getByOrdeNo(String orderNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderInvoice> c = cb.createQuery(OrderInvoice.class);
        Root<OrderInvoice> root = c.from(OrderInvoice.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderInvoice_.orderNo), orderNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }

    public List<OrderInvoice> getByOrdeSubNo(String orderSubNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderInvoice> c = cb.createQuery(OrderInvoice.class);
        Root<OrderInvoice> root = c.from(OrderInvoice.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderInvoice_.orderSubNo), orderSubNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }

    public OrderInvoice getByOrderInvoiceNo(String invoiceNo) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderInvoice> c = cb.createQuery(OrderInvoice.class);
        Root<OrderInvoice> root = c.from(OrderInvoice.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderInvoice_.invoiceNum), invoiceNo);
        c.where(equal01);
        return super.getByCriteria(c);
    }

}
