package com.ibm.oms.dao.intf;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import javax.persistence.criteria.Predicate;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderMain Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:47
 * @author:Yong Hong Luo
 */
public interface OrderMainDao extends BaseDao<OrderMain,Long>{

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
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderMain findByOrderNo(String orderNo);
	
	/**
	 * @param orderItemId
	 * @return
	 */
	public OrderMain getByOrderItemId(Long orderItemId);
	
	
	/**
	 * 
	 * @param orderMain
	 * @param pager
	 * @return
	 */
	public Pager findByOrderMain(OrderMain orderMain, Pager pager, List<String> statusPayOther, List<String> statusTotalOther);


    /**
     * @param type
     * @param memberNo
     * @param startTime
     * @return
     */
    List<OrderMain> getOMListByTypeAndPeriod(SingularAttribute<OrderMain, String> type, String memberNo, Long startTime);
    
    public List<String> findNoPayOrder(Map<String, Object> params,int count) ;

    public List<OrderMain> findNoPayOrderResendMsg();
    
    
    /**是否重复订单
     * 2018/03/12 更新为校验订单号是否重复，
     * 新系统暂时没有批次的概念，所以去掉批次号的校验
     * **/
    List<OrderMain> getOMListByBatchNumAndSource(String batchNum, String orderSource);
    
    /**
     * 通过sql语句查询订单
     * @param sql
     * @return List中的object为Map对象
     */
    public List<Object> findOrderMainBySql(String sql);

    public List<OrderMain> findFinishedOrder(String date, String endDate) throws ParseException;

    public List<OrderMain> findFinishedOrderBeforeOnline() throws ParseException;
    
    /**
	 * 根据订单状态查询
	 * Description:
	 * @param statusTotal
	 * @param count 查询数量
	 * @return
	 */
	public List<OrderMain> findbyStatusTotal(String statusTotal, int count);
	

	/**
	 * 通过订单状态和状态更新时间查询订单:
	 * @param params
	 * @param count
	 * @return
	 * @throws ParseException 
	 */
	public List<String> findOrderByStatusAndDate(Map<String, Object> params, int count) ;


	/**
	 * Description:查询订单列表
	 * @param om
	 * @param pager
	 * @param status 
	 * @return
	 */
	public Pager findOrderList(OrderMain orderMain, Pager pager);
	
	BigDecimal getOrderAmountByOrderId(Long orderId);

	Long getMemberIdByOrderNo(String orderNo);
	
	/**根据子单号查询会员id
	 * @param subOrderNo
	 * @return Long
	 */
	Long getMemberIdBySubOrderNo(String subOrderNo);

	/**
	 * Description:通过会员号获取订单
	 * @param memberNo
	 * @return List<OrderMain>
	 */
	public List<OrderMain> findOrderByMemberNo(String memberNo);

	/**
	 * 查询逆向订单
	 * 
	 * @param orderCategory 意向单类型
	 * @param 
	 * @return
	 */
	public List<OrderMain> getSaleAfterMainOrderList(String orderCategory,String shopNo,String startDate,String endDate,String status,String channelSource) throws ParseException;
	/**
     * 获取今天所有订单的会员的导购者id
     * @return
     */
    public List<?> getTodayAllOrderGuiderId();

	/**
	 * Description:
	 * @param orderNo
	 * @return
	 */
	public List<OrderMain> findOrderSplitlist(String orderNo,String ordernoP);

	/**
	 * Description:
	 * @param orderNo
	 * @return
	 */
	public List<?> findOrderForPay(String orderNo);

	/**
	 * Description:
	 * @param params
	 * @return
	 */
	public List<OrderMain> findNoGetPoint(Map<String, Object> params);
}