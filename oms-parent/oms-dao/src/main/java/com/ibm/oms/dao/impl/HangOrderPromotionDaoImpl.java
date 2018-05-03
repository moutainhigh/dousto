package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderPromotionDao;
import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderPromotionDaoImpl extends BaseDaoImpl<HangOrderPromotion, Long> implements HangOrderPromotionDao {

	@Override
	public void deleteByIdOrder(long orderId) {
		//删除挂单促销实体
		String sql1 = "DELETE FROM temp_order_promotion WHERE ID_ORDER = ?";
		SQLQuery query1 = getSession().createSQLQuery(sql1);
		query1.setParameter(0,orderId);
		query1.executeUpdate();	
	}

}
