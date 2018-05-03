package com.ibm.oms.rs.service.hessian.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.oms.client.intf.OrderSearchServiceRpc;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.sc.bean.Pager;

/**
 * Description: 订单查询实现类
 * 
 * @author YanYanZhang Date: 2018年1月19日
 */
@Service("orderSearchServiceRpc")
public class OrderSearchServiceRpcImpl implements OrderSearchServiceRpc {

	@Resource
	OrderSearchService oderSearchService;

//	@Override
//	public Pager<OrderSearch> queryOrderSearchList(Pager<OrderSearch> pager, OrderSearch orderSearch) {
//		//0代表全部订单查询
//		return oderSearchService.findByOrderSearch(0, orderSearch, pager);
//	}

}
