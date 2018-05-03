package com.ibm.oms.service.business;

import java.math.BigDecimal;

import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.promo.dto.promotion.InfCartItem;

/**
 * 计算服务类
 * @author wangchao
 *
 */
public interface SubbmitOrderCalculateService {

	
	
	/**均摊计算
	 * 积分均摊
	 * 优惠券均摊
	 * 折扣均摊
	 * @param calculateAftercart
	 * @param cartItem
	 * @param target
	 * @param receiveOrderMainDTO
	 * @param flag
	 * @return
	 */
	public BigDecimal shareCalculate(InfCart calculateAftercart,InfCartItem cartItem,BigDecimal target,String flag);
}
