package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderPayDao;
import com.ibm.oms.domain.persist.HangOrderPay;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderPayDaoImpl extends BaseDaoImpl<HangOrderPay, Long> implements HangOrderPayDao {

	@Override
	public void deleteByIdOrder(long orderId) {
		//删除挂单支付
		String sql2 = "DELETE FROM temp_order_pay WHERE ID_ORDER = ?";
		SQLQuery query2 = getSession().createSQLQuery(sql2);
		query2.setParameter(0,orderId);
		query2.executeUpdate();
	}

}
