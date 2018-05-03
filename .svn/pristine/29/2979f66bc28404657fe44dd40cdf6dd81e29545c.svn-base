package com.ibm.oms.service.business.impl;

import com.ibm.oms.client.dto.CategoryIntfDTO;
import com.ibm.oms.client.dto.CategoryMoney;
import com.ibm.oms.client.dto.CategorySalesVO;
import com.ibm.oms.client.dto.QueryCategoryDTO;
import com.ibm.oms.dao.intf.OrderGuideDao;
import com.ibm.oms.service.business.OrderGuideService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: 订单对导购接口实现
 * @create: 2018-03-26 15:46
 **/
@Service("orderGuideService")
public class OrderGuideServiceImpl implements OrderGuideService {
    @Autowired
    OrderGuideDao orderGuideDao;
    @Override
    public CategoryIntfDTO queryCategorySales(QueryCategoryDTO queryCategoryDTO) {
        CategoryIntfDTO categoryIntfVO = new CategoryIntfDTO();
        List<CategorySalesVO> categorySalesVOs =  orderGuideDao.queryCategorySales(queryCategoryDTO);
        List<CategoryMoney> categoryMonies = orderGuideDao.queryCategoryMoney(queryCategoryDTO);
        categoryIntfVO.setCategoryMoneyList(categoryMonies);
        categoryIntfVO.setCategorySalesVOList(categorySalesVOs);
        JSONObject js = JSONObject.fromObject(categoryIntfVO);
        System.out.println("categoryIntfVO===>  "+ js.toString());
        return categoryIntfVO;
    }
}
