package com.ibm.oms.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderItemPayDao;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:35
 * @author:Yong Hong Luo
 */
@Repository("orderItemPayDao")
public class OrderItemPayDaoImpl extends BaseDaoImpl<OrderItemPay,Long> implements OrderItemPayDao{

	@Override
	public int deleteByOrderNoAndPayCode(String orderNo, String payCode) {
		
		String sql = "update OrderItemPay set isDeleted = ? where orderNo = ? and payCode = ?";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, 1L);
		query.setParameter(2, orderNo);
		query.setParameter(3, payCode);
		return query.executeUpdate();
	}     
	
}
