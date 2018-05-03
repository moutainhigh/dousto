package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderItemDao;
import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderItemDaoImpl extends BaseDaoImpl<HangOrderItem, Long> implements HangOrderItemDao {

	@Override
	public void deleteByIdOrder(long orderId) {
		//删除挂单行
		String sql4 = "DELETE FROM temp_order_item WHERE ID_ORDER = ?";
		SQLQuery query4 = getSession().createSQLQuery(sql4);
		query4.setParameter(0,orderId);
		query4.executeUpdate();	
	}

}
