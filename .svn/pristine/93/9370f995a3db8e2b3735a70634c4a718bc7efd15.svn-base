package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.bean.hang.PayManner;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrder;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrderItem;
import com.ibm.oms.domain.persist.SendSapIntef;
import com.ibm.oms.domain.persist.StatusTransDict;
import com.ibm.sc.dao.BaseDao;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: ${DESCRIPTION}
 * @create: 2018-03-19 16:58
 **/
public interface SynSalesReceiptsOrderSapDao extends BaseDao<StatusTransDict,Long> {
    /**
     * @Description: 查询出上一天有成交订单的店铺
     * @author: mr.kai
     * @date: 2018/3/20 14:59
     * @param: []
     * @return: java.util.List<com.ibm.oms.domain.bean.hang.SalesReceiptsOrder>
     **/
    List<SalesReceiptsOrder> querySalesReceiptsOrder();
    /**
     * @Description: 根据商铺编码查询出来商铺成交的所有商品的价格
     * @author: mr.kai  
     * @date: 2018/3/20 15:14  
     * @param: [cusno]  
     * @return: java.util.List<com.ibm.oms.domain.bean.hang.SalesReceiptsOrderItem>  
     **/  
    List<SalesReceiptsOrderItem> querySalesReceiptsOrderItem(SalesReceiptsOrder salesReceiptsOrder);
    /**
     * @Description: 根据商铺编码查询出来商铺逆向的所有商品的价格
     * @author: mr.kai
     * @date: 2018/3/20 15:14
     * @param: [cusno]
     * @return: java.util.List<com.ibm.oms.domain.bean.hang.SalesReceiptsOrderItem>
     **/
    List<SalesReceiptsOrderItem> queryReturnSalesReceiptsOrderItem(SalesReceiptsOrder salesReceiptsOrder);
    /**
     * @Description: 更新订单表send_sap字段
     * @author: mr.kai  
     * @date: 2018/3/23 11:07
     * @param: []  
     * @return: java.lang.Integer  
     **/  
    Integer updateOrderMain();
    /**
     * @Description: 向sap推送数据
     * @author: mr.kai  
     * @date: 2018/3/24 10:06  
     * @param: [sendSapIntef]  
     * @return: java.lang.Long  
     **/  
    Long insertSendSapIntf(SendSapIntef sendSapIntef);
}
