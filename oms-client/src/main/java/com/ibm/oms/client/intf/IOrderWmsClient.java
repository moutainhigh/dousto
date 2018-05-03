package com.ibm.oms.client.intf;

import com.ibm.oms.client.dto.order.*;

import java.util.List;

/**
 * 订单对WMS接口
 *
 * @author ChaoWang
 */
public interface IOrderWmsClient{
    /**
     * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口）批量
     * @author: mr.kai  
     * @date: 2018/3/12 15:43  
     * @param: [updateReturnPfAsnBeanS]  
     * @return: com.ibm.oms.client.dto.order.ResponseBean
     **/  
    public ResponseBean updateSalesOrderBatch(UpdateOrderBatchBean updateOrderBatchBean);
    /**
     * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口）
     * @author: mr.kai
     * @date: 2018/3/12 14:55
     * @param: [updateReturnPfAsnBean]
     * @return: com.ibm.oms.client.dto.order.ResponseBean
     **/
    public ResponseBean updateSalesOrder(UpdateReturnPfAsnBean updateReturnPfAsnBean);
    /**
     * @Description: 回传单据状态
     * @author: mr.kai
     * @date: 2018/3/7 15:04
     * @param: [o]
     * @return: java.lang.Object
     **/
    public ResponseBean receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO);

    /**
     * @Description: 发货订单确认（WMS通知已发货）
     * @author: mr.kai
     * @date: 2018/3/7 15:05
     * @param: [o]
     * @return: java.lang.Object
     **/
    public ResponseBean Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain);

    /**
     * @Description: 入库订单确认接口
     * @author: mr.kai
     * @date: 2018/3/7 15:05
     * @param: [o]
     * @return: java.lang.Object
     **/
    public ResponseBean Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain);
}
