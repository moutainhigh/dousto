package com.ibm.oms.client.intf;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.oms.client.dto.CategoryIntfDTO;
import com.ibm.oms.client.dto.CategorySalesVO;
import com.ibm.oms.client.dto.QueryCategoryDTO;
import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.client.dto.order.create.OmsReceiveOrderOutputClientDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderOutDTO;
import com.ibm.oms.client.dto.result.HessianResult;
import com.ibm.sc.bean.Pager;



/**
 * 订单对外的接口
 * @author ChaoWang
 *
 */
public interface IOrderClient {
	public QmCreateOrderResponseBean qmCreateOrder (OrderQmDTO orderQmDTO)throws Exception;
	/**
	 * @Description: 奇们创建订单接口老接口,废弃
	 * @author: mr.kai
	 * @date: 2018/4/9 16:07  
	 * @param: [qmOrderMainDTO]  
	 * @return: com.ibm.oms.client.dto.order.QmResponseBean  
	 **/  
	public QmResponseBean createQmOrder(QmOrderMainDTO qmOrderMainDTO);
	/**
	 * @Description: 根据店铺编码查询店铺销售情况
	 * @author: mr.kai
	 * @date: 2018/3/26 15:46
	 * @param: [queryCategoryDTO]  
	 * @return: CategoryIntfDTO
	 **/  
	public CategoryIntfDTO queryCategorySales(QueryCategoryDTO queryCategoryDTO);

	
	
	
	public HessianResult<OmsReceiveOrderOutputClientDTO> CreateOrder(ReceiveOrderMainDTO mainDTO);
	
	
	/**创建订单
	 * @param orderHeader json格式
	 * @return
	 */
	public String createOrder(String receiveOrderClientDTO);
	
	/**修改订单
	 * @param orders json格式
	 * @return
	 */
	public String updateOrder(String orders);
	
	
	/**删除订单
	 * @param orders json格式
	 * @return
	 */
	public String delOrder(String orders);
	
	
	/**查询订单列表
	 * @param orders json格式
	 * @return
	 * @throws Exception 
	 */
	public Pager<OrderMainClientDTO> findOrderList(OrderQueryClientDTO OrderQueryDTO, Pager pager) throws Exception;
	
	
	/**	
	 * @param orders json格式
	 * @return
	 * @throws Exception 
	 */
	public OrderMainClientDTO findOrderDetails(String OrderNo) throws Exception;
	
	
	/**订单状态修改
	 * @param orders json格式
	 * @return
	 */
	public String updateOrderStatus(String orders);
	
	
	/**计算订单结算价格
	 * @param orders json格式
	 * @return
	 */
	public String calculationOrdePrice(String orders);
	
	
	/**订单挂起
	 * @param orders json格式
	 * @return
	 */
	public String orderHang(String orders);

	/**订单挂起查询
	 * @param orders json格式
	 * @return
	 */
	public String orderHangSearch(String orders);
	
	
	/**订单收货地址查询
	 * @param orders json格式
	 * @return
	 */
	public String orderReceiptAddressSearch(String orders);
	
	/**拆单回传物流单号接口   中台->E3
	 * @param orders json格式
	 * @return
	 */
	public String returnWMSNo(String orders);
	
	/**退/换货单创建
	 * @param orders json格式
	 * @return
	 */
	public String createReturnOrder(String orders);
	
	
	/**退/换货单列表查询
	 * @param orders json格式
	 * @return
	 */
	public String findReturnOrderList(String orders);
	
	
	/**退/换货单详情查询
	 * @param orders json格式
	 * @return
	 */
	public String findReturnOrderDetails(String orders);

	/**退/换货单状态确认接口
	 * @param orders json格式
	 * @return
	 */
	public String findReturnOrderStatus(String orders);
	
	
	/**退换货原因
	 * @param orders json格式
	 * @return
	 */
	public String returnReason(String orders);
	
	
	/**退款记录信息
	 * @param orders json格式
	 * @return
	 */
	public String findRefundHistoryInfo(String orders);
	
	
	/**退/换货单数查询
	 * @param orders json格式
	 * @return
	 */
	public String findReturnOrderCount(String orders);
	
	
	/**撤销退货
	 * @param orders json格式
	 * @return
	 */
	public String cancelReturnOrder(String orders);
	
	
	/**物流信息
	 * @param orders json格式
	 * @return
	 */
	public String findWmsHistoryInfo(String orders);


	public void OrderSuspension(String orderNo);
	
	
	/**挂单临时表创建 LS-C-48
	 * @param orders json格式
	 * @return
	 */
	@Deprecated
	public ReceiveHangOrderOutputDTO TempHangOrderCreate(OrderHeaderClientDTO orderHeaderClientDTO);
	
	/**挂单临时表创建 LS-C-48
	 * @param orders json格式
	 * @return
	 */
	public HessianResult<OmsReceiveOrderOutputClientDTO> tempHangOrderCreateNew(ReceiveOrderMainDTO mainDTO);
	
	
	/**挂单临时表修改
	 * @param orders json格式
	 * @return
	 */
	public ReceiveHangOrderOutputDTO TempHangOrderUpdate(OrderHeaderClientDTO orderHeaderClientDTO);
	
	/**挂单临时表删除
	 * @param orders json格式
	 * @return
	 */
	public ReceiveHangOrderOutputDTO TempHangOrderDelete(String orderNo);
	
	/**挂单临时表头查询
	 * @param orders json格式
	 * @return
	 */
	public List<OrderHeaderClientDTO> TempHangOrderQueryHeader(String shopNo,String startDate,String endDate,String status);
	
	/**挂单临时表详细查询
	 * @param orders json格式
	 * @return
	 */
	public List<OrderHeaderClientDTO> TempHangOrderQUeryDetail(String orderNo);
	
	
	//################################### 促销使用##########
	/**根据sub订单号查询会员id
	 * @param subOrderNo
	 * @return
	 */
	public Long getMemberIdBySubOrderNo(String subOrderNo);

    /** 根据订单号查询订单金额
     * Description:
     * @param orderId
     * @return
     */
    public BigDecimal getOrderAmountByOrderId(Long orderId);
    
    //################################### 促销使用end ##########
    
    /**
     * 拆单回传接口 -- 回传给百胜
     * @return
     */
    public Object splitOrderTransferReturn(String orderSplitTransferReturnDTO);
    
    /**
     * 获取今天所有订单的会员的导购者id
     * @return
     */
    public List<?> getTodayAllOrderGuiderId();

    /**
     * 通过货号获取销量
     * @return 
     */
    public Long findOrderItemSales(String productNo);

    /**
     * 通过订单号取消订单
     * @return 
     */
    public Long  cancelOrderByOrderNo(String orderNo);
}
