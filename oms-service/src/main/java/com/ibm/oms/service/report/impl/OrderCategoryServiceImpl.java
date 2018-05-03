package com.ibm.oms.service.report.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.oms.dao.report.OrderCategoryDao;
import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.oms.service.report.OrderCategoryService;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("orderCategoryService")
public class OrderCategoryServiceImpl extends BaseServiceImpl<OrderCategory, Long> implements OrderCategoryService {

    @Resource
    private OrderCategoryDao orderCategoryDao;

    /**
     * 查询商品运营一级分类
     * @param treelevel_One 一级分类
     * @return
     */  
	@Override
	public List<OrderCategory> findCategoryLevelOneList(Long treelevel_One) {		
		// 获取商品运营一级分类
		return orderCategoryDao.findByTreelevel(treelevel_One);		
	}

	@Override
	public List<OrderCategory> findCategoryByParentId(Long parentId) {
		// 根据父ID获取商品分类
		return orderCategoryDao.findByParentId(parentId);		
	}
}
