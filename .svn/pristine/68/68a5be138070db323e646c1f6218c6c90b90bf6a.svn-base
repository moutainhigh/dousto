package com.ibm.oms.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * @author wangchao
 * 订单切面 
 */
@Component
@Aspect
public class OrderAspect {
	@Autowired
	CommonUtilService commonUtilService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Before("execution(* com.ibm.oms..OrderClientImpl.CreateOrder(..)) || execution(* com.ibm.oms..OrderClientImpl.tempHangOrderCreateNew(..))")
	public void saveIntfReceive(JoinPoint point){
		//logger.info("OrderAspect -->调用了 OrderClientImpl.CreateOrder");
		//commonUtilService.logInfoObjectToJson("", logger, mainDTO);
		//commonUtilService.saveIntfReceivedJson(null, null, String.format(IntfReceiveConst.OMS_RECEIVE.getCode(), mainDTO.getOrderSource()), mainDTO, null);
		Object[] args = point.getArgs();
		if(args[0].getClass() == ReceiveOrderMainDTO.class){
			ReceiveOrderMainDTO mainDTO = (ReceiveOrderMainDTO) args[0];
			commonUtilService.saveIntfReceivedJson(null, null, String.format(IntfReceiveConst.OMS_RECEIVE.getCode(), mainDTO.getOrderSource()), mainDTO, null);
		};
	}
	
	
}
