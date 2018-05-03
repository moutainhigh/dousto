package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusMainOrderService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.StatusUtil;

@Service("orderStatusMainOrderService")
public class OrderStatusMainOrderServiceImpl  implements OrderStatusMainOrderService{
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderNoService orderNoService;
	@Autowired
    OrderMainService orderMainService;
	@Autowired
    OrderItemService orderItemService;
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	CommonUtilService commonUtilService; 
	@Autowired 
	StatusUtil statusUtil;
	
	@Override
	public boolean updateMainOrderStatus(String splitOrderNo) {
		//拆分单号
		//String orderNo = orderNoService.getOrderNoByItemNo(orderItemNo);
		Map<String,Object> splitOrderItemStatusCodes = new HashMap<String,Object>();
		OrderMain om = orderMainService.findByOrderNo(splitOrderNo);
		//1、判断是否是拆分单，只有拆分单的状态改变才会影响主订单的状态
		if(isSplitOrder(splitOrderNo)){
			commonUtilService.logInfoObjectToJson("OrderStatusMainOrderServiceImpl-->updateMainOrderStatus", logger, splitOrderNo+"是拆分单");
			//获取被拆分单单号(原单号)
			String originalOrderNo = om.getOrderNoP();
		//2、找到被拆分单的所有拆分单item的状态，寻找在途等级优先级最低的状态码 更新到主订单
			List<OrderItem> splitOrderItems = orderItemService.getByOrdeNo(splitOrderNo);
			String originalOrderStatusCode = om.getStatusTotal();
			commonUtilService.logInfoObjectToJson("OrderStatusMainOrderServiceImpl-->updateMainOrderStatus-->currentOrdreItemStauts", logger, originalOrderStatusCode);
		//3、将拆分单状态放到map中 key：orderItemNo value：statusvalue
			for(OrderItem so : splitOrderItems){
				splitOrderItemStatusCodes.put(so.getOrderItemNo(), so.getItemStatus());
			}
			commonUtilService.logInfoObjectToJson("OrderStatusMainOrderServiceImpl-->updateMainOrderStatus-->splitOrderItemStatus集合", logger, splitOrderItemStatusCodes);
		//4、获取拆分单状态是否更新主订单状态-总状态
	    if(isUpdateMainOrderStatus(originalOrderStatusCode,splitOrderItemStatusCodes)){
	    	//4.1 true 更新主状态
	    	OrderMain originalOm = orderMainService.findByOrderNo(originalOrderNo);
	    	originalOm.setStatusTotal(originalOrderStatusCode);
	    	orderMainService.update(originalOm);
	    	return false;
	    }
			
		}else{
			commonUtilService.logInfoObjectToJson("OrderStatusMainOrderServiceImpl-->updateMainOrderStatus", logger, splitOrderNo+"不是拆分单");
			//1.1 非拆分单不做任何操作
			return true;
		}
		
		//判断此订单
		return false;
	}
	
	
	/**获取拆分单状态是否更新主订单状态-总状态 
	 * @param currentOrdreItemStauts
	 * @param splitOrderItemStatus
	 * @return
	 */
	private boolean isUpdateMainOrderStatus(String originalOrderStatusCode, Map<String, Object> splitOrderItemStatusCode) {
		List<StatusDict> orgdicts = statusUtil.getStatusList();
		//获取当前currentOrdreMainStautsCode对应的优先级
		Integer currentOrdreMainStautsPriority = null;
		for(StatusDict s : orgdicts){
			if(originalOrderStatusCode.equals(s.getStatusCode())){
				currentOrdreMainStautsPriority = s.getStatusPriority();
			}
		}
		if(null == currentOrdreMainStautsPriority){
			commonUtilService.logInfoObjectToJson("OrderStatusMainOrderServiceImpl-->isUpdateMainOrderStatus", logger, originalOrderStatusCode+"splitOrderItemStatusPriority优先级不存在StatusDict");
			return false;
		}
		//1、如果是收货状态必须全被更新为收货
		List<StatusDict> dictsTargets = splitOrderItemStatusPriority(splitOrderItemStatusCode,orgdicts);
		Integer splitOrderItemStatusPriority = dictsTargets.get(0).getStatusPriority();
		
		//算法  当前主订单得状态优先级和他对应拆分单最低得优先级对比
		//相等 则不更新 返回false   小于则更新返回true 
		//算法包含：被拆分单的所有拆分单item的状态为已收货的时候，主状态才能更新为已收货
		if(currentOrdreMainStautsPriority.compareTo(splitOrderItemStatusPriority) < 0){
			return true;
		}
		return false;
	}

	/**判断是否是拆分单
	 * @param orderNo
	 * @return
	 */
	@Override
	public boolean isSplitOrder(String orderNo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * 拆分单status 优先级,退货暂时不考虑
	 * @param splitOrderItemStatus
	 * @return
	 */
	private List<StatusDict> splitOrderItemStatusPriority(Map<String, Object> splitOrderItemStatusCodes,List<StatusDict> orgdicts) {
		List<StatusDict> dictsTarget = new ArrayList<StatusDict>();
		// 组装splitOrderItem得行状态code和对应得优先级
		for (Map.Entry<String, Object> entry : splitOrderItemStatusCodes.entrySet()) {
			String splitOrderItemStatus = (String) entry.getValue();
			for (StatusDict d : orgdicts) {
				if (d.getStatusCode().equals(splitOrderItemStatus)) {
					dictsTarget.add(d);
				}
			}
		}
		// 按照优先级正序排列
		Collections.sort(dictsTarget, new Comparator<StatusDict>() {
			public int compare(StatusDict arg0, StatusDict arg1) {
				return arg1.getStatusPriority().compareTo(arg1.getStatusPriority());
			}
		});

		return dictsTarget;
	}
	
	
	

}
