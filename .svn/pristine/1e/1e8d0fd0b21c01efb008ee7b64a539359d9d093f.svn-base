package com.ibm.oms.service.business;

import java.util.HashMap;
import java.util.Map;

import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;

/**
 * 提交订单校验服务
 * 返回 Map
 * 如果验证key flag 为true  ，resultkey返回的是验证值
 * 如果验证key flag 为false ，resultkey返回的验证值
 * @author wangchao
 *
 */
public interface SubbmitOrderValidateService {
	
	String MAP_FLAG = "flag";
	String MAP_RESULT = "result";
	String MAP_MESSAGE = "message";
	
	String MESSAGE_SUCCESS = "success";
	String MESSAGE_FAIL = "fail";
	
	String POINT_MESSAGE_OVER_TOTAL = "会员积分大于总积分";
	String POINT_MESSAGE_USE = "会员可使用积分不一致";
	String POINT_MESSAGE_PAY_AMOUT = "可抵用积分不一致";
	
	/**验证会员做大积分积分
	 * @param memberId
	 * @param orgPoint
	 * @return
	 */
	public Map<String,Object> ValidateOrderPoint(String accountName, String orgPoint);
	
	/**验证积分支付金额是否合法
	 * @param memberId
	 * @param orgPoint
	 * @param orgPointPayAmount
	 * @return
	 */
	public Map<String,Object> validateOrederPointTranPayAmount(String accountName, String orgPoint, String orgPointPayAmount);
	
	
	/**验证优惠券 -- 目前运营验证，暂时不需要订单提交的时候再次校验 ，以后如果需要请更新此接口
	 * @param receiveOrderMainDTO
	 * @return
	 */
	//public Map<String,Object> validateOrderCouponCode(ReceiveOrderMainDTO receiveOrderMainDTO);
	
	
	/**验证优惠券  目前运营验证，暂时不需要订单提交的时候再次校验 ，以后如果需要请更新此接口
	 * @param receiveOrderMainDTO
	 * @return
	 */
	//public Map<String,Object> validateOrderCouponCodePayAmount(ReceiveOrderMainDTO receiveOrderMainDTO);
	
	
	/**返回result
	 * @param flag
	 * @param o
	 * @return
	 */
	default Map<String,Object> buildResult(boolean flag , Object o,String message){
		Map<String,Object> reuslt = new HashMap<String,Object>();
		reuslt.put(MAP_FLAG, flag);
		reuslt.put(MAP_RESULT, o);
		reuslt.put(MAP_MESSAGE,message);
		return reuslt;
	}
	
	
	/**取验证的返回flag
	 * @param m
	 * @return
	 */
	default boolean getValidateValue(Map<String,Object> m){
		boolean f = (boolean) m.get(MAP_FLAG);
		return f;
	}
	
	
	/**取验证返回的result
	 * @param m
	 * @return
	 */
	default Object getResultValue(Map<String,Object> m){
		return   m.get(MAP_RESULT);
	}
	
	
	/**获取返回的消息
	 * @param m
	 * @return
	 */
	default String getResultMessage(Map<String,Object> m){
		return  (String) m.get(MAP_MESSAGE);
	}
	
	
	
}
