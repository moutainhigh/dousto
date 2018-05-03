package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderItem Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:01
 * @author:Yong Hong Luo
 */
public interface OrderItemDao extends BaseDao<OrderItem,Long>{
	
    /**
     * 通过主订单号获取明细
     * @param orderNo
     * @return
     */
    public List<OrderItem> getByOrdeNo(String orderNo);

    /**
     * 通过子订单号获取明细
     * @param orderSubNo
     * @return
     */
    public List<OrderItem> getByOrdeSubNo(String orderSubNo);
    
    /**
     * 通过明细单号获取
     * @param orderItemNo
     * @return
     */
    public OrderItem  getByOrderItemNo(String orderItemNo);

    public boolean isOrderNoContainLowGross(Long id);

	/**
	 * Description:通过货号获取商品
	 * @param commodityCode
	 * @return
	 */
	public Long findSalesByCommodityCode(String commodityCode);

}
