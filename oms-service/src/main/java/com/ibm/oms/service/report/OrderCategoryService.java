package com.ibm.oms.service.report;

import java.util.List;

import com.ibm.oms.domain.persist.OrderCategory;
import com.ibm.sc.service.BaseService;

public interface OrderCategoryService extends BaseService<OrderCategory, Long> {

    /**
     * 查询一级分类
     * 
     * @param categorylevelOneId 一级分类ID
     * @return
     */
    List<OrderCategory> findCategoryLevelOneList(Long treelevel_One);
    
    /**
     * 根据parentId查询子分类
     * 
     * @param parentid 父节点
     * @return
     */
    List<OrderCategory> findCategoryByParentId(Long parentId);
}
