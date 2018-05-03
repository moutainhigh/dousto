package com.ibm.oms.service.business;

import com.ibm.oms.client.dto.CategoryIntfDTO;
import com.ibm.oms.client.dto.CategorySalesVO;
import com.ibm.oms.client.dto.QueryCategoryDTO;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: 订单对导购接口
 * @create: 2018-03-26 15:44
 **/
public interface OrderGuideService {
    /**
     * @Description: 根据店铺编码查询店铺销售情况
     * @author: mr.kai
     * @date: 2018/3/26 15:41
     * @param: [queryCategoryDTO]
     * @return: CategoryIntfDTO
     **/
    public CategoryIntfDTO queryCategorySales(QueryCategoryDTO queryCategoryDTO);
}
