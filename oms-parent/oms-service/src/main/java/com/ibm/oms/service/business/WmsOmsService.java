package com.ibm.oms.service.business;

import com.ibm.interf.ws.intf.dto.ResponseBean;
import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.sc.service.BaseService;

import java.util.List;

public interface WmsOmsService extends BaseService<OrderReport,Long> {
	/**
	 * @Description: 销售订单退货同步wms接口
	 * @author: mr.kai  
	 * @date: 2018/3/29 16:30
	 * @param: [orderItemNo]
	 * @return: com.ibm.interf.ws.intf.dto.ResponseBean  
	 **/  
	public ResponseBean orderSalesCancel(String orderItemNo);
	/**
	 * @Description: WMS->中台 (批发退货单状态更新通知接口)
	 * @author: mr.kai
	 * @date: 2018/3/12 15:43
	 * @param: [updateReturnPfAsnBeanS]
	 * @return: com.ibm.oms.client.dto.order.ResponseBean
	 **/
	public com.ibm.oms.client.dto.order.ResponseBean updateSalesOrderBatch(UpdateOrderBatchBean updateOrderBatchBean);
	/**
	 * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口）
	 * @author: mr.kai
	 * @date: 2018/3/12 14:55
	 * @param: [updateReturnPfAsnBean]
	 * @return: com.ibm.oms.client.dto.order.ResponseBean
	 **/
	public com.ibm.oms.client.dto.order.ResponseBean updateSalesOrder(UpdateReturnPfAsnBean updateReturnPfAsnBean);
	/**
	 * @Description: WMS-->order 回传单据状态
	 * @author: mr.kai  
	 * @date: 2018/3/12 16:30  
	 * @param:   
	 * @return:   ResponseBean
	 **/
	public com.ibm.oms.client.dto.order.ResponseBean receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO);
	
	/**WMS --> order 发货订单确认（WMS通知已发货）
	 * @param o
	 * @return
	 */
	public com.ibm.oms.client.dto.order.ResponseBean Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain);
	
	
	/**WMS --> order 入库订单确认接口
	 * @param o
	 * @return
	 */
	public com.ibm.oms.client.dto.order.ResponseBean Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain);

	/**
	 * @Description: oms-->wms 销售订单发货通知接口
	 * @author: mr.kai
	 * @date: 2018/3/8 15:18
	 * @param: [orderMainId]
	 * @return: com.ibm.interf.common.ResponseBean
	 **/
	public ResponseBean oms2WmsShipNotify(String orderMainId);

	/**
	 * @Description: oms-->wms 单据取消通知接口
	 * @author: mr.kai
	 * @date: 2018/3/8 15:17
	 * @param: [orderMainId]
	 * @return: com.ibm.interf.common.ResponseBean
	 **/
	public ResponseBean getOms2WmsCancelOrderNotify(String orderMainId);
	
	
	//增加处理类 handle类
	
}
