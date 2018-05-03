package com.ibm.oms.dao.intf;


import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.domain.bean.hang.OrderBean;
import com.ibm.oms.domain.bean.hang.OrderCanceBean;
import com.ibm.oms.domain.bean.hang.StoreInf;
import com.ibm.oms.domain.persist.*;
import com.ibm.sc.dao.BaseDao;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: Wms接口调用dao层
 * @create: 2018-03-08 10:51
 **/
public interface WmsOmsOrderDao extends BaseDao<OrderReport, Long>{
    /**
     * @Description: 查询wms需要的订单数据
     * @author: mr.kai  
     * @date: 2018/4/2 11:26  
     * @param: [orderNo]  
     * @return: com.ibm.oms.client.dto.order.OrderSalesCancel  
     **/  
    public OrderSalesCancel orderSalesCancel(String orderNo);
    /**
     * @Description: 更新订单状态
     * @author: mr.kai  
     * @date: 2018/3/23 14:35
     * @param: [salesOrderStatus]  
     * @return: java.lang.Integer  
     **/  
    public Integer updateOrderMainOrderStatus(SalesOrderStatus salesOrderStatus);
    /**
     * @Description: 批发退货单接口数据保存
     * @author: mr.kai  
     * @date: 2018/3/26 9:00
     * @param: [wholesaleReturnProducts]  
     * @return: void  
     **/  
    public void insertWholesaleReturnProductBatch(List<WholesaleReturnProduct> wholesaleReturnProducts);
    /**
     * @Description: 批量插入批发退货单商品
     * @author: mr.kai
     * @date: 2018/3/21 17:20  
     * @param: [wholesaleCaseCodes]  
     * @return: void  
     **/  
    public void insertWholesaleCaseCodeBatch(List<WholesaleCaseCode> wholesaleCaseCodes);
    /**
     * @Description: 保存批发退货单状态
     * @author: mr.kai  
     * @date: 2018/3/21 15:34
     * @param: [wholesaleOrderStatus]  
     * @return: java.lang.Long  
     **/  
    public Long insertWholesaleOrderStatus(WholesaleOrderStatus wholesaleOrderStatus);
    /**
     * @Description: 根据订单号查询订单状态记录数
     * @author: mr.kai  
     * @date: 2018/3/16 16:36  
     * @param: [salesOrderStatus]  
     * @return: java.lang.Long  
     **/  
    public Long querySalesOrderStatusCount(SalesOrderStatus salesOrderStatus);
    /**
     * @Description: 保存Oms->中台订单状态
     * @author: mr.kai
     * @date: 2018/3/16 16:38
     * @param: [salesOrderStatus]
     * @return: java.lang.Long
     **/
    public Long insertSalesOrderStatus(SalesOrderStatus salesOrderStatus);
    /**
     * @Description: 修改行子订单的仓库信息
     * @author: mr.kai  
     * @date: 2018/3/14 9:57  
     * @param: [storeInf]  
     * @return: java.lang.Integer  
     **/  
    public Integer upadteOrderSubByOrderNo(StoreInf storeInf);
    /**
     * @Description: 修改主订单的仓库信息
     * @author: mr.kai  
     * @date: 2018/3/14 9:57  
     * @param: [storeInf]  
     * @return: java.lang.Integer  
     **/  
    public Integer upadteOrderMainByOrderNo(StoreInf storeInf);
    /**
     * @Description: 修改行订单的仓库信息
     * @author: mr.kai
     * @date: 2018/3/14 10:31
     * @param: [storeInf]  
     * @return: java.lang.Integer  
     **/  
    public Integer upadteOrderItemByOrderNo(StoreInf storeInf);
    /**
     * WMS-->order 回传单据状态
     * @param o
     * @return
     */
    public Integer receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO);

    /**WMS --> order 发货订单确认（WMS通知已发货）
     * @param o
     * @return
     */
    public Integer Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain);


    /**WMS --> order 入库订单确认接口
     * @param o
     * @return
     */
    public Integer Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain);

    /**
     * oms-->wms    销售订单发货通知接口
     * @param o
     * @return
     */
    public OrderBean getOms2WmsShipNotify(String orderMainId);

    /**
     * oms-->wms
     * 单据取消通知接口
     * @param
     * @return
     */
    public OrderCanceBean getOms2WmsCancelOrderNotify(String OrderMainId);
}
