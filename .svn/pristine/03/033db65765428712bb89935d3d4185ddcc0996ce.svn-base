package com.ibm.oms.service.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.member.dto.bundle.ResultBean;
import com.ibm.member.intf.PointsCommonAccountHsService;
import com.ibm.member.result.HessianResult;
import com.ibm.member.result.MemberPointsResult;
import com.ibm.oms.service.business.SubbmitOrderValidateService;
import com.ibm.oms.service.util.CommonUtilService;

@Service("subbmitOrderValidateService")
public class SubbmitOrderValidateServiceImpl implements SubbmitOrderValidateService {
	@Autowired
	PointsCommonAccountHsService pointsCommonAccountHsService;
	@Autowired
	CommonUtilService commonUtilService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Map<String, Object> ValidateOrderPoint(String accountName, String orgPoint) {
		Map<String, Object> result =  new HashMap<String, Object>();
		HessianResult pointResult =pointsCommonAccountHsService.getMemberUsePoints(accountName);
		String responseCode = pointResult.getResponse_code();
		commonUtilService.logInfoObjectToJson("subbmitOrder-->getMemberUsePoints", logger, pointResult);
		//响应成功
		if("S000000".equals(responseCode)){
			Integer points = (Integer) pointResult.getResponse_data();
			if(Integer.valueOf(orgPoint) <= points){
				result.put(MAP_FLAG, true);
				result.put(MAP_MESSAGE, pointResult.getResponse_msg());
				result.put(MAP_RESULT, pointResult.getResponse_data());
				return result;
			}
			result.put(MAP_FLAG, false);
			result.put(MAP_MESSAGE, POINT_MESSAGE_OVER_TOTAL);
			result.put(MAP_RESULT, pointResult.getResponse_data());
			return result;
		//响应失败
		}else{
			result.put(MAP_FLAG, false);
			result.put(MAP_MESSAGE, pointResult.getResponse_msg());
			result.put(MAP_RESULT, pointResult.getResponse_data());
			return result;
		}
	}

	@Override
	public Map<String, Object> validateOrederPointTranPayAmount(String accountName, String orgPoint,String orgPointPayAmount) {
		Map<String, Object> result =  new HashMap<String, Object>();
		ResultBean memberResult = pointsCommonAccountHsService.getMemberPoints(accountName);
		//响应成功
		if(200 == memberResult.getCode()){
			MemberPointsResult mr = (MemberPointsResult) memberResult.getData();
			if(!mr.getPoint().equals(orgPoint)){
				result.put(MAP_FLAG, false);
				result.put(MAP_MESSAGE, POINT_MESSAGE_USE);
				result.put(MAP_RESULT, mr.getPoint());
				return result;
			}
			if(!mr.getDeductibleAomount().equals(orgPointPayAmount)){
				result.put(MAP_FLAG, false);
				result.put(MAP_MESSAGE, POINT_MESSAGE_PAY_AMOUT);
				result.put(MAP_RESULT,mr.getDeductibleAomount());
				return result;
			}
			result.put(MAP_FLAG, true);
			result.put(MAP_MESSAGE, MESSAGE_SUCCESS);
			result.put(MAP_RESULT,mr.getDeductibleAomount());
			return result;
		//响应失败
		}else{
			result.put(MAP_FLAG, false);
			result.put(MAP_MESSAGE, memberResult.getMsg());
			result.put(MAP_RESULT, memberResult.getData());
			return result;
		}
	}

//	@Override
//	public Map<String, Object> validateOrderCouponCode(ReceiveOrderMainDTO receiveOrderMainDTO) {
//		Map<String, Object> result =  new HashMap<String, Object>();
//		result.put(MAP_FLAG, true);
//		result.put(MAP_MESSAGE,MESSAGE_SUCCESS);
//		return result;
//	}

//	@Override
//	public Map<String, Object> validateOrderCouponCodePayAmount(ReceiveOrderMainDTO receiveOrderMainDTO) {
//		Map<String, Object> result =  new HashMap<String, Object>();
//		result.put(MAP_FLAG, true);
//		result.put(MAP_MESSAGE,MESSAGE_SUCCESS);
//		return result;
//	}

}
