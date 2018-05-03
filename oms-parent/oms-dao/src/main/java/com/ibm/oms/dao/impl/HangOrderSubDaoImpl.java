package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderSubDao;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository("hangOrderSubDao")
public class HangOrderSubDaoImpl extends BaseDaoImpl<HangOrderSub, Long> implements HangOrderSubDao {

	@Override
	public void deleteByIdOrder(long orderId) {
		//删除子挂单
		String sql5 = "DELETE FROM temp_order_sub WHERE ID_ORDER = ?";
		SQLQuery query5 = getSession().createSQLQuery(sql5);
		query5.setParameter(0,orderId);
		query5.executeUpdate();		
	}

	
}
