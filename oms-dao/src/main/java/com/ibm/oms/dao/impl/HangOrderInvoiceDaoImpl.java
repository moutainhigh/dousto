package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderInvoiceDao;
import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderInvoiceDaoImpl extends BaseDaoImpl<HangOrderInvoice, Long> implements HangOrderInvoiceDao {

	@Override
	public void deleteByIdOrder(long orderId) {
		//删除发票
		String sql3 = "DELETE FROM temp_order_invoice WHERE ID_ORDER = ?";
		SQLQuery query3 = getSession().createSQLQuery(sql3);
		query3.setParameter(0,orderId);
		query3.executeUpdate();
	}

}
