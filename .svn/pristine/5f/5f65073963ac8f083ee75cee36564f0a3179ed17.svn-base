package com.ibm.oms.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.client.intf.OrderMainServiceRpc;
import com.ibm.oms.dao.intf.OrderLogisticsMessageDao;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderStatusLog_;
import com.ibm.oms.domain.persist.OrderLogisticsMessage;
import com.ibm.oms.domain.persist.OrderLogisticsMessage_;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月1日 
 */

@Repository("orderLogisticsMessageDao")
public class OrderLogisticsMessageDaoImpl extends BaseDaoImpl<OrderLogisticsMessage, Long> implements OrderLogisticsMessageDao{

	/* 通过ORDERNO查询物流信息
	 * @see com.ibm.oms.dao.intf.orderLogisticsMessageDao#findOrderLogisticsMessageByOrderNo(java.lang.String)
	 */
	@Override
	public List<OrderLogisticsMessage> findOrderLogisticsMessageByOrderSubNo(String orderSubNo) {

        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderLogisticsMessage> c = cb.createQuery(OrderLogisticsMessage.class);
        Root<OrderLogisticsMessage> root = c.from(OrderLogisticsMessage.class);
        c.select(root);
        List<Predicate> terms = new ArrayList<Predicate>();
        Predicate equal01 = cb.equal(root.get("orderSubNo"), orderSubNo);
        terms.add(equal01);
        c.where(terms.toArray(new Predicate[0]));

		
        return findByCriteria(c);
		
	}

	/* (non-Javadoc)
	 * @see com.ibm.oms.dao.intf.OrderLogisticsMessageDao#saveOrderLogisticsMessage(java.util.Map)
	 */
	@Override
	public void saveOrderLogisticsMessage(Map map) {
			
		Map lastResult =(Map) map.get("lastResult");	
		
		
		List data = (List) lastResult.get("data");	
		String teackingNumber =(String) lastResult.get("nu");
		Map logisticsMessage = (Map) data.get(0);
		
		String context = (String) logisticsMessage.get("context");
		Date  time = (Date) logisticsMessage.get("time");
	
	
	}

	/* 通过单号查询物流信息 按时间排序
	 * @see com.ibm.oms.dao.intf.OrderLogisticsMessageDao#findOrderLogisticsMessageByTeackingNumber()
	 */
	@Override
	public List<OrderLogisticsMessage> findOrderLogisticsMessageByTeackingNumber(String teackingNumber) {
		   CriteriaBuilder cb = getCriteriaBuilder();
	        CriteriaQuery<OrderLogisticsMessage> c = cb.createQuery(OrderLogisticsMessage.class);
	        Root<OrderLogisticsMessage> root = c.from(OrderLogisticsMessage.class);
	        c.orderBy(cb.desc(root.get(OrderLogisticsMessage_.wmsTime)));
	        c.select(root);
	        List<Predicate> terms = new ArrayList<Predicate>();
	        Predicate equal01 = cb.equal(root.get(OrderLogisticsMessage_.teackingNumber), teackingNumber);;
	        terms.add(equal01);
	        c.where(terms.toArray(new Predicate[0]));
	        return findByCriteria(c);
	}

}
