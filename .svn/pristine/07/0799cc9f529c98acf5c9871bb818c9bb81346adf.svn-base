package com.ibm.oms.dao.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.sc.dao.BaseDao;

public interface OrderCategoryDao extends BaseDao<OrderCategory, Long> {

	/**
	 * 根据目录级别查商品分类
	 * @param categoryLevel
	 * @return
	 */
	public List<OrderCategory> findByTreelevel(Long categoryLevel);
	
	/**
	 * 根据父节点查询商品分类
	 * @param parentId
	 * @return
	 */
	public List<OrderCategory> findByParentId(Long parentId);
}
