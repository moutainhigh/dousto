
package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:47
 * @author:Yong Hong Luo
 */
public interface OrderSearchService extends BaseService<OrderSearch,Long>{

	
    /**
     * 分页查询
     * @param columnId 菜单列id
     * @param orderSearch 查询对象
     * @param pager 分页对象
     * @return
     */
    public Pager findByOrderSearch(int columnId, OrderSearch orderSearch, Pager pager);
    
    /**
     * 非分页查询
     * @param columnId 菜单列id
     * @param orderSearch 查询对象
     * @return
     */
    List<OrderSearch> findByOrderSearch(int columnId, OrderSearch orderSearch);
}
