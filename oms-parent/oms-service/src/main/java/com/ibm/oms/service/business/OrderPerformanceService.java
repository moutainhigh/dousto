package com.ibm.oms.service.business;

import java.math.BigDecimal;

import com.ibm.common.dto.DefaultOutputDto;
import com.ibm.oms.domain.persist.OrderMain;

/**
 * 订单相关业绩计算接口
 * 1、订单销售员业绩计算
 * @author wangchao
 *
 */
public interface OrderPerformanceService {

	public BigDecimal getOrderPerformance(OrderMain om);
}
