
package com.ibm.oms.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import com.ibm.oms.client.dto.order.OrderMainClientDTO;
import com.ibm.oms.client.dto.order.OrderQueryClientDTO;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BBCBatchRemarkDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderStatusDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:47
 * @author:Yong Hong Luo
 */
public interface OrderMainService extends BaseService<OrderMain,Long>{

	int updateWithSql();
	
	/**
	 * 根据关联订单号(orderRelatedOrigin)查询主订单，并根据下单时间desc排序
	 * @param orderRelatedOrigin
	 * @return
	 */
	public OrderMain getByOrderRelatedOrigin(String orderRelatedOrigin);
	
	/**
     * 通过销售订单查询逆向订单
     * @param saleId 销售订单号
     * @param orderCategory 意向单类型
     * @return
     */
    public List<OrderMain> getBackOrderBySaleNo(Long saleNo,String orderCategory);
    
    /**
     * 通过子订单号查询主订单
     * @param subOrderId
     * @return
     */
    public OrderMain getByOrderSubNo(String orderSubNo);
    
    /**
     * 通过单号查询主订单
     * @param orderNo
     * @return
     */
    public OrderMain findByOrderNo(String orderNo);
    
    
    /**
     * 通过单号查询退货订单
     * @param orderNo
     * @return
     */
    public OrderMain findByOrderNoForReturn(String orderMainNo);
    
    /**
     * 通过
     * @param orderItemId
     * @return
     */
    public OrderMain getByOrderItemId(Long orderItemId);
    
    /**
     * @param columnId
     * @param pager
     * @return
     */
    public Pager findByColumnId(int columnId, Pager pager);

    /**
     * 查询需要审核的订单
     * Description:
     * @param count 加载数量
     * @return
     */
    public List<OrderMain> findOrdersForAutomaticAudit(int count);
    /**
     * @param orderMain
     * @param pager
     * @return
     */
    //public Pager findByOrderMain(OrderMain orderMain, Pager pager);

    /**
     * @param type
     * @param memberNo
     * @param startTime
     * @return
     */
    int getOMListByTypeAndPeriod(SingularAttribute<OrderMain, String> type, String memberNo, Long startTime);
    
    public List<String> findNoPayOrder(Map<String, Object> params,int count);
    
    /**
     * 根据关联的原销售单号查询
     * @param OriginOrderNo 原销售单号
     * @return
     */
    List<OrderMain> findByOrderRelatedOrigin(String OriginOrderNo);

    List<OrderMain> findNoPayOrderResendMsg();
    /**根据批次号，订单来源判断订单是否已经存在, 存在则返回true**/
    boolean isOrderMainDuplicated(String batchNum, String orderSource);
    
    /**
     * 通过sql语句查询订单
     * @param sql
     * @return List中的object为Map对象
     */
    public List<Object> findOrderMainBySql(String sql);

    List<OrderMain> findFinishedOrder(String date, String endDate);
    
    /**
     * BBC更新订单信息（收货人信息，配送信息）
     * @param bbcUpdateOrderDTO
     * @return
     */
    public CommonOutputDTO bbcUpdateOrderInfo(BBCUpdateOrderDTO bbcUpdateOrderDTO);
    
    /**
     * BBC批量修改客服备注
     * @param bbcBatchRemarkDTO
     * @return
     */
    public CommonOutputDTO bbcBatchUpdateRemarkInfo(BBCBatchRemarkDTO bbcBatchRemarkDTO);
    
    /**
     * BBC修改订单状态与物流状态
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
    public CommonOutputDTO bbcUpdateOrderStatus(BBCUpdateOrderStatusDTO bbcUpdateOrderStatusDTO);

    
    List<OrderMain> findFinishedOrderBeforeOnline() throws ParseException ;

	/**
	 * Description:通过订单状态和状态更新时间查询订单:
	 * @param params
	 * @param count
	 * @throws ParseException 
	 */
    List<String> findOrderByStatusAndDate(Map<String, Object> params, int count) ;

	/**
	 * Description:查询订单详情
	 * @param orderNo
	 * @return
	 */
	OrderMainClientDTO findOrderDetails(String orderNo) throws Exception;

	/**
	 * Description:查询订单 
	 * @param orderQueryDTO
	 * @param pager
	 * @return
	 */
	Pager findOrderList(OrderQueryClientDTO orderQueryDTO, Pager pager)throws Exception;


	/**
	 * Description:
	 * @param orderNo
	 * @return
	 */
	String updateOrderSuspension(String orderNo);

	/**
	 * Description:
	 * @param orderNo
	 * @return
	 */
	String updateOrderRelieveSuspensionon(String orderNo);
	
	/**根据子单号查询会员id
	 * @param subOrderNo
	 * @return
	 */
	public Long getMemberIdBySubOrderNo(String subOrderNo);
	
	/**根据订单id获取总价
	 * @param orderId
	 * @return
	 */
	public BigDecimal getOrderAmountByOrderId(Long orderId);
	
	/**
	 * 自动审核
	 * Description:
	 */
	void automaticAuditOrder();
	
	/**
	 * 退\换货状态调整
	 * 
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public CommonOutputDTO updateSaleAfterOrderStatus(String orderNo, String status);

	/**
     * 获取今天所有订单的会员的导购者id
     * @return
     */
    public List<?> getTodayAllOrderGuiderId();

	/**
	 * Description:通过ORDERNOP查询子订单
	 * @param orderNo
	 * @param ordernoP 
	 * @return
	 */
	public List<OrderMain> findOrderSplitlist(String orderNo, String ordernoP);
	
	/**
	 *  根据子订单号
	 *  修改客服备注
	 *  Description:
	 * 
	 * @param orderSubNo
	 * @param clientServiceRemark
	 */
	public void updateClientServiceRemark(OrderMain orderMain);
	
	
	/**更新订单主表状态
	 * @param orderSubNo
	 * @param payStatus
	 * @param confirmStatus
	 * @param totalStatus
	 */
	public void updateOrderStatus(String orderSubNo, String payStatus, String confirmStatus,String totalStatus);
	
	public OrderMain findOrderForPay(String orderNo);

	/**
	 * Description:
	 * @param params 
	 * @return
	 */
	public List<OrderMain> findNoGetPoint(Map<String, Object> params);
	
}
