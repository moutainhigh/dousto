package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderMain Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:47
 * @author:Yong Hong Luo
 */
public interface OrderSearchDao extends BaseDao<OrderSearch,Long>{

	
	/**
	 * 分页查询
	 * @param columnId
	 * @param orderSearch
	 * @param pager
	 * @param statusPayOther
	 * @param statusTotalOther
	 * @return
	 */
	public Pager findByOrderSearch(int columnId,OrderSearch orderSearch, Pager pager, List<String> statusPayOther, List<String> statusTotalOther);
	
	
	/**
	 * 非分页查询
	 * @param columnId
	 * @param orderSearch
	 * @param statusPayOther
	 * @param statusTotalOther
	 * @return
	 */
	public List<OrderSearch> findByOrderSearch(int columnId,OrderSearch orderSearch, List<String> statusPayOther, List<String> statusTotalOther);
	
}
