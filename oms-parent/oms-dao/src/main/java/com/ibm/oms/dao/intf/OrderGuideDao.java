package com.ibm.oms.dao.intf;

import com.ibm.oms.client.dto.CategoryMoney;
import com.ibm.oms.client.dto.CategorySalesVO;
import com.ibm.oms.client.dto.QueryCategoryDTO;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.sc.dao.BaseDao;
import com.ibm.sc.dao.impl.BaseDaoImpl;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: ${DESCRIPTION}
 * @create: 2018-03-26 15:58
 **/
public interface OrderGuideDao extends BaseDao<OrderDic, Long> {
    /**
     * @Description: 根据会员编码查询会员情况
     * @author: mr.kai  
     * @date: 2018/3/26 19:46
     * @param: [queryCategoryDTO]  
     * @return: java.util.List<com.ibm.oms.client.dto.CategorySalesVO>  
     **/  
    public List<CategorySalesVO> queryCategorySales(QueryCategoryDTO queryCategoryDTO);
    /**
     * @Description: 查询会员每年的购买情况
     * @author: mr.kai
     * @date: 2018/3/26 20:02
     * @param: [queryCategoryDTO]
     * @return: java.util.List<com.ibm.oms.client.dto.CategoryMoney>
     **/
    public List<CategoryMoney> queryCategoryMoney(QueryCategoryDTO queryCategoryDTO);
}
