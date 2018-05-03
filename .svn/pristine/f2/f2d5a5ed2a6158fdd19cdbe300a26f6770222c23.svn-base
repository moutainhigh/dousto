package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ibm.oms.service.business.SubbmitOrderCalculateService;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.promo.dto.promotion.InfCartAdjust;
import com.ibm.promo.dto.promotion.InfCartItem;

@Service("subbmitOrderCalculateService")
public class SubbmitOrderCalculateServiceImpl extends SubbmitOrderCommon implements SubbmitOrderCalculateService {
	@Override
	public BigDecimal shareCalculate(InfCart calculateAftercart, InfCartItem cartItem, BigDecimal target, String flag) {
		BigDecimal totalAmount = null;
		BigDecimal itemaAmount =  null;
		//目标均摊对象为0，则均摊数为0
		if(target.compareTo(new BigDecimal(DEFAULT_BLANTA_MOUNT))==0){
			return new BigDecimal(DEFAULT_BLANTA_MOUNT);
		}
		
		//优惠券和促销得均摊
		if(PROMOTIONLEVEL_COUPON.equals(flag) || PROMOTIONLEVEL_ORDER.equals(flag)){
			//被均摊的金额如果=0。00 怎返回0.00
			//1、要判断此商品是否满足订单级别
			//List<Long> adjustSkuIds = new ArrayList<Long>();
			Map<String,Object> results = buildAdjustMap(calculateAftercart, calculateAftercart.getCartAdjustList());
			
			List<Long> orderSkuids = (List<Long>)results.get(PROMOTIONLEVEL_ORDER);
			BigDecimal orderTotolPrice = (BigDecimal) results.get(PROMOTIONLEVEL_ORDER_AMOUNT);
			orderTotolPrice = orderTotolPrice==null ? BigDecimal.ZERO : orderTotolPrice;
			List<Long> couponSkuids = (List<Long>)results.get(PROMOTIONLEVEL_COUPON);
			BigDecimal couponTotolPrice = (BigDecimal) results.get(PROMOTIONLEVEL_OCOUPON_AMOUNT);
			couponTotolPrice = couponTotolPrice==null ? BigDecimal.ZERO : couponTotolPrice;
			List<Long> totalSkuIds =  new ArrayList<Long>();
			totalSkuIds.addAll(orderSkuids);
			totalSkuIds.addAll(couponSkuids);
			totalAmount = orderTotolPrice.add(couponTotolPrice);
			itemaAmount = BigDecimal.ZERO;
			//此item得sku是否在优惠得里，不在则为0
			for(Long skuId : totalSkuIds){
				if(skuId == cartItem.getSkuId()){
					itemaAmount = cartItem.getAmount() == null ? cartItem.getPrice() : cartItem.getAmount();
				}
			}
			BigDecimal base =itemaAmount.divide(totalAmount,BIGDECIMAL_DEFAULT_SCALE,BIGDECIMAL_DEFAULT_ROUNDINGMODE);
			return target.multiply(base);
		}
		//积分均摊
		if(PROMOTIONLEVEL_POINT.equals(flag)){
			//calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPiont())
			itemaAmount = cartItem.getAmount()==null?cartItem.getPrice():cartItem.getAmount();
			totalAmount = calculateAftercart.getTotalAmount();
			BigDecimal base =itemaAmount.divide(totalAmount,BIGDECIMAL_DEFAULT_SCALE,BIGDECIMAL_DEFAULT_ROUNDINGMODE);
			//target = 订单级别消耗得总积分
			return target.multiply(base);
		}
		//积分抵扣均摊
		if(PROMOTIONLEVEL_POINT_AMOUNT.equals(flag)){
			//calculateAftercart, cartItem, commonUtilService.string2BigDecimal(receiveOrderMainDTO.getTotalPiont())
			itemaAmount = cartItem.getAmount()==null?cartItem.getPrice():cartItem.getAmount();
			totalAmount = calculateAftercart.getTotalAmount();
			BigDecimal base =itemaAmount.divide(totalAmount,BIGDECIMAL_DEFAULT_SCALE,BIGDECIMAL_DEFAULT_ROUNDINGMODE);
			//target = 订单级别抵扣得总积分
			return target.multiply(base);
		}
		return  null;
		
	}
	
	
	/**
	 * 构建优惠Map
	 * 
	 * @param calculateAftercart
	 * @param infCartAdjusts
	 * @return
	 */
	private Map<String, Object> buildAdjustMap(InfCart calculateAftercart, List<InfCartAdjust> infCartAdjusts) {
		Map<String, Object> results = new HashMap<String, Object>();
		if (CollectionUtils.isNotEmpty(infCartAdjusts)) {
			for (InfCartAdjust ia : infCartAdjusts) {
				// 参加订单折扣得sku
				if (PROMOTIONLEVEL_ORDER.equals(ia)) {
					// 享受当前优惠的SKU
					List<Long> skuIds = ia.getCartItemIds();
					results.put(PROMOTIONLEVEL_ORDER, skuIds);
					// 找到对应商品得折后价格
					BigDecimal totolPrice = BigDecimal.ZERO;
					for (Long skuId : skuIds) {
						for (InfCartItem it : calculateAftercart.getCartItemList()) {
							if (skuId == it.getSkuId()) {
								// 计算订单折扣得skus得总价格
								totolPrice = it.getAmount() == null ? totolPrice.add(it.getPrice())
										: totolPrice.add(it.getAmount());
							}

						}
					}
					results.put(PROMOTIONLEVEL_ORDER_AMOUNT, totolPrice.toString());

				}

				// 参加优惠券得sku
				if (PROMOTIONLEVEL_COUPON.equals(ia)) {
					// 享受当前优惠的SKU
					List<Long> skuIds = ia.getCartItemIds();
					results.put(PROMOTIONLEVEL_COUPON, skuIds);
					// 找到对应商品得折后价格
					BigDecimal totolPrice = BigDecimal.ZERO;
					for (Long skuId : skuIds) {
						for (InfCartItem it : calculateAftercart.getCartItemList()) {
							if (skuId == it.getSkuId()) {
								// 计算订单折扣得skus得总价格
								totolPrice = it.getAmount() == null ? totolPrice.add(it.getPrice())
										: totolPrice.add(it.getAmount());
							}

						}
					}
					results.put(PROMOTIONLEVEL_OCOUPON_AMOUNT, totolPrice.toString());

				}

			}

		}
		return results;
	}

}
