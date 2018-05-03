package com.ibm.oms.service.business.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ibm.clerk.dto.input.CalculateBonusInputDto;
import com.ibm.clerk.dto.output.CalculateBonusOutputDto;
import com.ibm.clerk.intf.ClerkBonusHsService;
import com.ibm.common.dto.DefaultOutputDto;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.business.OrderPerformanceService;


@Service("orderPerformanceService")
public class OrderPerformanceServiceImpl implements OrderPerformanceService {

	private final Logger logger = LoggerFactory.getLogger(OrderPerformanceServiceImpl.class);
	@Autowired
	ClerkBonusHsService clerkBonusHsService;
	/* 获取提成
	 * @see com.ibm.oms.service.business.OrderPerformanceService#getOrderPerformance(com.ibm.oms.domain.persist.OrderMain)
	 */
	@Override
	public BigDecimal getOrderPerformance(OrderMain om) {
		CalculateBonusInputDto input = new CalculateBonusInputDto();
		input.setBilltype(String.valueOf(om.getBillType()));
		input.setClerkCode(om.getSalesclerkNo());
		input.setOrderCode(om.getOrderNo());
		input.setOrderType(om.getOrderType());
		input.setSalesAmount(om.getTotalPayAmount());
		input.setMemberCode(om.getMemberNo());
		input.setBonusDate(om.getDateUpdated());
		DefaultOutputDto output=clerkBonusHsService.calculateBonus(input);
		BigDecimal bonus =new BigDecimal(0);
		if("S000000".equals(output.getResponse_code())){
			CalculateBonusOutputDto output2=  (CalculateBonusOutputDto) output.getResponse_data();
      
			 bonus =  output2.getBonus();
		
		}else if("E000030".equals(output.getResponse_code())){
			

			logger.info("===============================计算错误或异常");
		}
		return bonus;
	}
	 
}
