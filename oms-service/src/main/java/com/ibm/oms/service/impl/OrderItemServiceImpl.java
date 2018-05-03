package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.intf.OrderItemDao;
import com.ibm.oms.dao.intf.ProductPropertyDao;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.oms.domain.persist.ProductProperty_;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.product.intf.CategoryClientService;
import com.ibm.product.intf.SkuClientService;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.promo.dto.promotion.InfCartAdjust;
import com.ibm.promo.dto.promotion.InfCartItem;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:01
 * 
 * @author:Yong Hong Luo
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long> implements OrderItemService {
	//订单促销级别属性订单
	private static final String PROMOTION_LEVEL_ORDER = "ORDER";
	
	//订单促销类型积分
	private static final String PROMOTION_TYPE_POINT = "ProductPoint";
	
	private OrderItemDao orderItemDao;

	@Autowired
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		super.setBaseDao(orderItemDao);
		this.orderItemDao = orderItemDao;
	}

	@Autowired
	private OrderCombineRelationService orderCombineRelationService;
	@Autowired
	private ProductPropertyDao productPropertyDao;

	@Autowired
	private CategoryClientService categoryClientService;
	
	@Autowired
	private SkuClientService skuClientService;
	
	@Resource
    private UserUtil userUtil;
	
	public List<OrderItem> getByOrdeNo(String orderNo) {
		return this.orderItemDao.getByOrdeNo(orderNo);
	}

	/**
	 * 根据子订单号查找明细
	 */
	public List<OrderItem> getByOrdeSubNo(String orderSubNo) {
		List<OrderItem> list = this.orderItemDao.getByOrdeSubNo(orderSubNo);
		for (OrderItem item : list) {
			this.loadOrderSubOthes(item);
		}
		return list;
	}

	public OrderItem getByOrderItemNo(String orderItemNo) {
		OrderItem item = this.orderItemDao.getByOrderItemNo(orderItemNo);
		loadOrderSubOthes(item);
		return item;
	}

	/**
	 * 加载明细的其他信息
	 * 
	 * @param orderSub
	 */
	public void loadOrderSubOthes(OrderItem orderItem) {
		if (null == orderItem) {
			return;
		}
		// 判断是否为组合商品
		if (CommonConst.OrderItem_OrderItemClass_Suite.getCode().equals(orderItem.getOrderItemClass())) {
			// 获取组合商品明细
			/*
			 * List<OrderCombineRelation> list =
			 * this.orderCombineRelationService.getList(OrderCombineRelation_.
			 * combineNo, orderItem.getCommodityCode());
			 */
			List<OrderCombineRelation> list = this.orderCombineRelationService.getList(OrderCombineRelation_.orderItemNo, orderItem.getOrderItemNo());
			orderItem.setOrderCombineRelations(list);
		}

		if (null == orderItem.getOrderItemNo()) {
			return;
		}
		// 获取色码款属性
		List<ProductProperty> productPropertyList = productPropertyDao.findByField(ProductProperty_.orderItemNo, orderItem.getOrderItemNo());
		orderItem.setProductPropertys(productPropertyList);
	}

	@Override
	public OrderItem findByFields(Map<String, Object> params) {
		List<OrderItem> comList = orderItemDao.findByFields(params);
		return (comList.isEmpty() ? null : comList.get(0));
	}

	/**
	 * 校验是否包含指定集货类型的商品
	 * 
	 * @param orderSubNo
	 *            子订单号
	 * @param storeType
	 *            集货类型：CommonConst类中有枚举(OrderItem_StoreType_DsStore)
	 * @return
	 */
	public boolean checkStoreTypeByOrderSubNo(String orderSubNo, String storeType) {
		boolean flag = false;
		if (StringUtils.isBlank(orderSubNo) || StringUtils.isBlank(storeType)) {
			return false;
		}
		List<OrderItem> items = findByField(OrderItem_.orderSubNo, orderSubNo);
		for (OrderItem item : items) {
			if (null != item.getStoreType() && storeType.equals(item.getStoreType())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.service.OrderItemService#findSalesByCommodityCode(java.lang.
	 * String)
	 */
	@Override
	public Long findSalesByCommodityCode(String commodityCode) {
		return orderItemDao.findSalesByCommodityCode(commodityCode);
	}

	@Override
	public BigDecimal getTotalPoint(OrderMain orderMain, List<OrderItem> orderItems, InfCart infCart) {
		BigDecimal totalPoint = BigDecimal.valueOf(0);
		
		//获取订单级促销赠送积分
		BigDecimal orderPromotionPoint = BigDecimal.valueOf(0);
		for(InfCartAdjust infCartAdjust : infCart.getCartAdjustList()) {
			if(PROMOTION_LEVEL_ORDER.equals(infCartAdjust.getPromotionLevel()) && PROMOTION_TYPE_POINT.equals(infCartAdjust.getPromotionType()) && StringUtils.isNotBlank(infCartAdjust.getPoint())) {
				orderPromotionPoint = new BigDecimal(infCartAdjust.getPoint());
				break;
			}
		}
		
		//初始化促销积分数据  key为sku 
		Map<String, BigDecimal> promotionPoints = new HashMap<String, BigDecimal>();
		List<InfCartItem> cartItemList = infCart.getCartItemList();
		for (InfCartItem infcartItem : cartItemList) {
			//todo 待促销增加SkuCode后打开
			//promotionPoints.put(infcartItem.getSkuCode(), BigDecimal.valueOf(infcartItem.getPoint()==null?0l:infcartItem.getPoint()));
		}
		
		//为商品行添加积分
		for (OrderItem item : orderItems) {
			BigDecimal thresholdPrice = this.getThresholdBySku(item.getSkuNo());
			
			BigDecimal itemTotalPoint = BigDecimal.valueOf(0);
			
			if (null != thresholdPrice) {
				// 商品单价是否大于阈值
				if (item.getUnitDeductedPrice().compareTo(thresholdPrice) >= 0) {
					item.setPricePoint(item.getPayAmount());
					totalPoint = totalPoint.add(item.getPayAmount());
					itemTotalPoint = itemTotalPoint.add(item.getPayAmount());
				}
			}

			// 添加促销商品积分
			BigDecimal itemPromotionPoint = BigDecimal.valueOf(0);
			itemPromotionPoint = promotionPoints.get(item.getSkuNo());
			totalPoint = totalPoint.add(itemPromotionPoint);

			// 添加促销订单均摊积分
			if (orderMain.getTotalPayAmount() != null && orderMain.getTotalPayAmount().intValue() != 0) {
				itemPromotionPoint = itemPromotionPoint.add(orderPromotionPoint.multiply(item.getPayAmount().divide(orderMain.getTotalPayAmount())));
			}

			item.setPromotionPoint(itemPromotionPoint);
			item.setProductPoint(itemTotalPoint.add(itemPromotionPoint));
			
		}
		totalPoint = totalPoint.add(orderPromotionPoint);
		return totalPoint;
	}
	
	@Override
	public void updateWareHouseNoBySubNo(String orderSubNo, String wareHouseNo) {
		List<OrderItem> orderItems = orderItemDao.findByField("orderSubNo", orderSubNo);
		
		Date now = new Date();
		for(OrderItem orderItem : orderItems) {
			orderItem.setUpdatedBy(userUtil.getLoginUserRealName());
			orderItem.setDateUpdated(now);
			orderItem.setWarehouseNo(wareHouseNo);
		}
		
		orderItemDao.update(orderItems);
	}


	/**
	 * 通过sku查詢 价格阈值
	 * 如果阈值没有设置默认返回null
	 * @param sku
	 * @return
	 */
	private BigDecimal getThresholdBySku(String sku) {
		String value = categoryClientService.findPointThresholdValueBySkuCode(sku);
		return value == null ? null : new BigDecimal(value);
	}
}
