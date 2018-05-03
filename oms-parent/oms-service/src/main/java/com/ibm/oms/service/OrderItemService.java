package com.ibm.oms.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer. Creation date:2014-03-14 04:20:01
 * 
 * @author:Yong Hong Luo
 */
public interface OrderItemService extends BaseService<OrderItem, Long> {

	/**
	 * 通过主订单号获取明细
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getByOrdeNo(String orderNo);

	/**
	 * 通过子订单号获取明细
	 * 
	 * @param orderSubNo
	 * @return
	 */
	public List<OrderItem> getByOrdeSubNo(String orderSubNo);

	/**
	 * 通过明细单号获取
	 * 
	 * @param orderItemNo
	 * @return
	 */
	public OrderItem getByOrderItemNo(String orderItemNo);

	OrderItem findByFields(Map<String, Object> params);

	/**
	 * 校验是否包含指定集货类型的商品
	 * 
	 * @param orderSubNo
	 *            子订单号
	 * @param storeType
	 *            集货类型：CommonConst类中有枚举(OrderItem_StoreType_DsStore)
	 * @return
	 */
	public boolean checkStoreTypeByOrderSubNo(String orderSubNo, String storeType);

	/**
	 * Description:
	 * 
	 * @param Integer
	 * @return
	 */
	public Long findSalesByCommodityCode(String string);

	/**
	 * 为orderMain.orderItems行项目价格积分pricePoint，与促销积分promotionPoint赋值 Description:
	 * 
	 * @param orderMain.totalPayAmount,orderItems
	 * @return 所有行总积分
	 */
	public BigDecimal getTotalPoint(OrderMain orderMain, List<OrderItem> orderItems, InfCart infCart);

	/**
	 *  根据子订单号
	 *  修改发货仓库
	 *  Description:
	 * 
	 * @param orderSubNo
	 * @param wareHouseNo
	 */
	public void updateWareHouseNoBySubNo(String orderSubNo, String wareHouseNo);
}
