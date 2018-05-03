package com.ibm.oms.client.dto.order.create.refactor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ReceiveOrderPromotionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

    // all  --out
	private Long promotionId;
	
	private String promotionCode;
	// 促销等级，ORDER/ITEM/OTHER
	private String promotionLevel;
	// 促销类型  ProductPoint  单品送积分
	private String promotionType;
	// 状态，AVAILABLE/OCCUPIED
	private String status;
	// 订单赠品限购数量
	private Integer orderGiftsMaxCount;
	// 单品赠品池数量
	private Integer singleGiftsMaxCount;
	// 优惠金额
	private BigDecimal amount;
	// 促销信息
	private String promotionInfo;
	// 促销信息链接
	private String url;
	// 商户ID
	private String merchantId;
	// 享受当前优惠的ItemId
	private List<Long> cartItemIds;
	
	
	
	
}
