package com.ibm.oms.dao.impl;

import com.ibm.oms.client.dto.CategoryMoney;
import com.ibm.oms.client.dto.CategorySalesVO;
import com.ibm.oms.client.dto.QueryCategoryDTO;
import com.ibm.oms.dao.intf.OrderGuideDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: 销售商家统计DAO层
 * @create: 2018-03-26 16:26
 **/
@Repository("orderGuideDao")
public class OrderGuideDaoImpl extends BaseDaoImpl<OrderDic, Long> implements OrderGuideDao {
    @Override
    public List<CategoryMoney> queryCategoryMoney(QueryCategoryDTO queryCategoryDTO) {
        String sql = "SELECT " +
                " sum(oi.PAY_AMOUNT), " +
                " DATE_FORMAT(om.FINISH_TIME, '%Y') " +
                "FROM " +
                " order_item oi, " +
                " ( " +
                "  SELECT " +
                "   om.ORDER_NO, " +
                "   om.FINISH_TIME " +
                "  FROM " +
                "   order_main om " +
                "  WHERE " +
                "   om.SALE_STORE_CODE = '"+queryCategoryDTO.getShopNo() +"' " +
                "  AND om.FINISH_TIME IS NOT NULL " +
                "  AND om.STATUS_TOTAL = '0180' " +
                " ) om " +
                "WHERE " +
                " oi.order_no = om.order_no " +
                "GROUP BY " +
                " DATE_FORMAT(om.FINISH_TIME, '%Y')";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        List<CategoryMoney> categoryMoneys = initCategoryMoney(list);
        return categoryMoneys;
    }

    @Override
    public List<CategorySalesVO> queryCategorySales(QueryCategoryDTO queryCategoryDTO) {
        String sql = "SELECT " +
                " os.PRODUCT_CATEGORY_NAME AS attrName, " +
                " oa.total AS totalCount, " +
                " os.count AS count, " +
                " os.count / oa.total AS percent " +
                "FROM " +
                " ( " +
                "  SELECT " +
                "   oi.PRODUCT_CATEGORY_NAME, " +
                "   count(0) AS count, " +
                "   om.SALE_STORE_CODE " +
                "  FROM " +
                "   order_item oi, " +
                "   ( " +
                "    SELECT " +
                "     ORDER_NO, " +
                "     SALE_STORE_CODE " +
                "    FROM " +
                "     order_main om " +
                "    WHERE " +
                "     om.SALE_STORE_CODE = '"+queryCategoryDTO.getShopNo() +"' " +
                "   ) om " +
                "  WHERE " +
                "   oi.order_no = om.order_no " +
                "  GROUP BY " +
                "   oi.PRODUCT_CATEGORY_NAME " +
                " ) os " +
                "LEFT JOIN ( " +
                " SELECT " +
                "  count(0) AS total, " +
                "  om.SALE_STORE_CODE " +
                " FROM " +
                "  order_item oi, " +
                "  ( " +
                "   SELECT " +
                "    ORDER_NO, " +
                "    SALE_STORE_CODE " +
                "   FROM " +
                "    order_main om " +
                "   WHERE " +
                "    om.SALE_STORE_CODE = '"+queryCategoryDTO.getShopNo() +"' " +
                "  ) om " +
                " WHERE " +
                "  oi.order_no = om.order_no " +
                ") oa ON oa.SALE_STORE_CODE = os.SALE_STORE_CODE";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        List<CategorySalesVO> categorySalesVOs = initCategorySales(list);
        return categorySalesVOs;
    }
    List<CategorySalesVO> initCategorySales(List list){
        List<CategorySalesVO> categorySalesVOs = new ArrayList<CategorySalesVO>();
        for(Object objectArray : list){
            CategorySalesVO categorySalesVO = new CategorySalesVO();
            Object[] array = (Object[])objectArray;
            categorySalesVO.setAttrName(array[0]==null?"":array[0].toString());
            categorySalesVO.setTotalCount(array[1] == null?"0":array[1].toString());
            categorySalesVO.setCount(array[2] == null?"0": array[2].toString());
            categorySalesVO.setPercent(array[3] == null?new BigDecimal(0):(BigDecimal) array[3]);
            categorySalesVOs.add(categorySalesVO);
        }
        return categorySalesVOs;

    }
    List<CategoryMoney> initCategoryMoney(List list){
        List<CategoryMoney> categoryMoneys = new ArrayList<CategoryMoney>();
        for(Object objectArray : list){
            CategoryMoney categoryMoney = new CategoryMoney();
            Object[] array = (Object[])objectArray;
            categoryMoney.setTotalCount(array[0] == null?new BigDecimal(0):(BigDecimal) array[0]);
            categoryMoney.setAttrName(array[1]==null?"":array[1].toString());
            categoryMoneys.add(categoryMoney);
        }
        return categoryMoneys;
    }
}
