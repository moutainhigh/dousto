package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.member.dto.bundle.ResultBean;
import com.ibm.member.intf.PointsCommonAccountHsService;
import com.ibm.member.result.HessianResult;
import com.ibm.member.result.MemberPointsResult;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderItemDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.service.business.SubbmitOrderValidateService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.product.intf.ProductMarketOrderClientService;

@Service("subbmitOrderValidateService")
public class SubbmitOrderValidateServiceImpl implements SubbmitOrderValidateService {
	@Autowired
	PointsCommonAccountHsService pointsCommonAccountHsService;
	@Autowired
	CommonUtilService commonUtilService;
	@Autowired
	ProductMarketOrderClientService productMarketOrderClientService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Map<String, Object> validateOrderPoint(String accountName, String orgPoint) {
		Map<String, Object> result =  new HashMap<String, Object>();
		
		if(BigDecimal.ZERO.compareTo(new BigDecimal(orgPoint))==0){
			result.put(MAP_FLAG, true);
			result.put(MAP_MESSAGE, POINT_MESSAGE_NONE);
			logger.info("subbmitOrder-->getMemberUsePoints-->{}",POINT_MESSAGE_NONE);
			return result;
		}
		
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
		
		if(BigDecimal.ZERO.compareTo(new BigDecimal(orgPointPayAmount))==0){
			result.put(MAP_FLAG, true);
			result.put(MAP_MESSAGE, POINT_MESSAGE_NONE);
			logger.info("subbmitOrder-->getMemberUsePoints-->{}",POINT_MESSAGE_NONE_PAYAMOUNT);
			return result;
		}
		
		ResultBean memberResult = pointsCommonAccountHsService.getMemberPoints(accountName);
		//响应成功
		if(200 == memberResult.getCode()){
			MemberPointsResult mr = (MemberPointsResult) memberResult.getData();
			if(Integer.valueOf(orgPoint) > Integer.valueOf(mr.getUsePoint())){
				result.put(MAP_FLAG, false);
				result.put(MAP_MESSAGE, POINT_MESSAGE_USE);
				result.put(MAP_RESULT, mr.getPoint());
				return result;
			}
			
			HessianResult hr = pointsCommonAccountHsService.checkPoint(accountName, orgPoint);
			
			if(!"S000000".equals(hr.getResponse_code())){
				result.put(MAP_FLAG, false);
				result.put(MAP_MESSAGE, String.format("error:SubbmitOrderValidateServiceImpl调用pointsCommonAccountHsService.checkPoint失败-->reason:%s", hr.getResponse_msg()));
				result.put(MAP_RESULT, hr.getResponse_data());
				return result;
			}
			MemberPointsResult mr1  = (MemberPointsResult) hr.getResponse_data();
			if(new BigDecimal(mr1.getDeductibleAomount()).compareTo(new BigDecimal(orgPointPayAmount)) !=0 ){
				result.put(MAP_FLAG, false);
				result.put(MAP_MESSAGE, POINT_MESSAGE_PAY_AMOUNT + String.format("error:[前端积分抵用金额：%s][后端计算积分可抵用金额:%s]", orgPointPayAmount,mr1.getDeductibleAomount()));
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
			result.put(MAP_MESSAGE, String.format("error:SubbmitOrderValidateServiceImpl调用pointsCommonAccountHsService.getMemberPoints失败-->reason:%s", memberResult.getMsg()));
			result.put(MAP_RESULT, memberResult.getData());
			return result;
		}
	}

	@Override
	public Map<String, Object> validateProductorIsSaler(ReceiveOrderMainDTO rm) {
		String channelId = rm.getChannelId();
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = true;
		//获取商品list
		//只要有一个下架，则验证不通过
		List<ReceiveOrderItemDTO> ris = rm.getReceiveOrderItemDTOs();
		String message = "";
		for(ReceiveOrderItemDTO ri : ris){
			boolean f = productMarketOrderClientService.searchProductMarket(Long.valueOf(ri.getProductId()), Long.valueOf(channelId));
			if(!f){
				flag = false;
				message = message + ri.getCommodityName()+ ",";
			}
		}
		if(!flag){
			logger.info("{} 下架", message);
			result.put(MAP_MESSAGE, String.format("%s 下架", message));
			result.put(MAP_FLAG, flag);
		}
		result.put(MAP_FLAG, flag);
		return result;
	}
}
