package com.ibm.oms.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderItemDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:01
 * @author:Yong Hong Luo
 */
@Repository("orderItemDao")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem,Long> implements OrderItemDao{
       
    public List<OrderItem> getByOrdeNo(String orderNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderItem> c = cb.createQuery(OrderItem.class);
        Root<OrderItem> root = c.from(OrderItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderItem_.orderNo), orderNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }
    
    public List<OrderItem> getByOrdeSubNo(String orderSubNo){
    	
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderItem> c = cb.createQuery(OrderItem.class);
        Root<OrderItem> root = c.from(OrderItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderItem_.orderSubNo), orderSubNo);
        c.where(equal01);
        return super.findByCriteria(c);
    }
    
    public OrderItem  getByOrderItemNo(String orderItemNo){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderItem> c = cb.createQuery(OrderItem.class);
        Root<OrderItem> root = c.from(OrderItem.class);
        c.select(root);
        Predicate equal01 = cb.equal(root.get(OrderItem_.orderItemNo), orderItemNo);
        c.where(equal01);
        return super.getByCriteria(c);
    }
    
    /**
     * @param idOrder
     * @return
     */
    @Override
    public boolean isOrderNoContainLowGross(Long idOrder) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderItem> c = cb.createQuery(OrderItem.class);
        Root<OrderItem> root = c.from(OrderItem.class);
        c.select(root);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate lowGross = cb.equal(root.get(OrderItem_.isLowGross), 1l);
        predicates.add(lowGross);
        
        Predicate orderNoP = cb.equal(root.get(OrderItem_.idOrder), idOrder.toString());
        predicates.add(orderNoP);
        c.where(predicates.toArray(new Predicate[0]));
        List<OrderItem> oiList = super.findByCriteria(c);
        return oiList != null && oiList.size() > 0;
    }

	/* (non-Javadoc)
	 * @see com.ibm.oms.dao.intf.OrderItemDao#findSalesByCommodityCode(java.lang.String)
	 */
	@Override
	public Long findSalesByCommodityCode(String commodityCode) {
		String sql ="SELECT " +
                " order_sales.sales - order_return_sales.r_sales AS sales " +
                "FROM " +
                " ( " +
                "  SELECT " +
                "   count(0) AS sales " +
                "  FROM " +
                "   order_item oi " +
                "  LEFT JOIN order_main om ON oi.order_no = om.order_no " +
                "  WHERE " +
                "   om.STATUS_TOTAL = \"0190\" " +
                "  AND oi.COMMODITY_CODE = '" +commodityCode+"'"+
                " ) AS order_sales, " +
                " ( " +
                "  SELECT " +
                "   count(0) AS r_sales " +
                "  FROM " +
                "   order_ret_chg_item orci " +
                "  LEFT JOIN order_main orm ON orm.order_no = orci.order_no " +
                "  WHERE " +
                "   orm.STATUS_TOTAL = \"0280\" " +
                "  AND orci.COMMODITY_CODE =  '" + commodityCode+"'"+
                " ) AS order_return_sales ";
		SQLQuery recordQuery = getSession().createSQLQuery(sql);
        BigInteger bigInteger = (BigInteger)recordQuery.list().get(0);
        if(bigInteger!=null){
            Long result = bigInteger.longValue();
            return result;
        }
        return null;
	}

}
