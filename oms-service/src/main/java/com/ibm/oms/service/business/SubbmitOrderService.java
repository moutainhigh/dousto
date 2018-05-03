package com.ibm.oms.service.business;

import java.util.Map;

import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.promo.dto.promotion.InfCart;

/**
 * @author wangchao
 *
 */
public interface SubbmitOrderService {
	
	/**
	 *  OmsDto-->产生Infcart
	 * @param btcOmsReceiveOrderDTO
	 * @return
	 */
	public InfCart omsReceiveOrderDTOGeneraceInfCartParam(ReceiveOrderMainDTO receiveOrderMainDTO);
	
	
	/** 
	 * 验证订单提交
	 * @param receiveOrderMainDTO
	 * @return  验证成功 返回map result 结果存的是calculateAftercart
	 */
	public Map<String,Object> validateSubbmitOrder(ReceiveOrderMainDTO receiveOrderMainDTO);
	
	
	/**转换成transBtcOmsReceiveOrderDTO
	 * @param infCart
	 * @param userPoint
	 * @param pointDiscountAmount
	 * @return
	 * <br>
	 * 返回成功 - resultkey 存transBtcOmsReceiveOrderDTO
	 */
	public Map<String,Object> transBtcOmsReceiveOrderDTO(ReceiveOrderMainDTO receiveOrderMainDTO,InfCart calculateAftercart);
	
	/**提交订单
	 * @param receiveOrderMainDTO
	 * @return
	 */
	public Map<String,Object> handleSubbmitOrder(ReceiveOrderMainDTO receiveOrderMainDTO);
	
	
	
	
	/**根据会员id 获取会有信息
	 * @param memberId
	 * @return
	 */
	//public String getMemberInfo(String memberId);
	
	
	/**根据skuId获取
	 * @param SkuId
	 * @return
	 */
	//public String productInfoBySkuId(String SkuId);
	
	
}
