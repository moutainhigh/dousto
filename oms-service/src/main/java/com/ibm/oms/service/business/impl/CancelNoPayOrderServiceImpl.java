package com.ibm.oms.service.business.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.CancelNoPayOrderService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;

@Service("cancelNoPayOrderService")
public class CancelNoPayOrderServiceImpl implements CancelNoPayOrderService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderMainService orderMainService;

	@Resource
	SaleAfterOrderTransService saleAfterOrderTransService;

	@Resource
	SaleAfterOrderService saleAfterOrderService;
	
	@Autowired
	OrderNoService orderNoService;
	
    @Autowired
    OrderCreateService orderCreateService;

	@Override
	public void cancelNoPayOrder(int count) {
		logger.debug("cancelNoPayOrder start");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusPay", OrderStatus.Order_PayStatus_Paying.getCode());
		params.put("statusTotal", OrderStatus.ORDER_PAYING.getCode());
		params.put("ifPayOnArrival", 1L);
		//修改开始时间为半小时前x YUSL 2018/1/29
		params.put("startDate", getDate());
		
		// Date startDate
		List<OrderMain> mainList = orderMainService.findNoPayOrder(params,
				count);
		for (OrderMain orderMain : mainList) {

			String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo());
			
			try{
			
				saleAfterOrderService.cancelOrder(orderSubNo, CancelOrderConst.CancelOrder_Scene_Customer);
				 // 解锁接口【saveCancelOrder中实现】
	            /*List<OrderMain> orderMainList = new ArrayList<OrderMain>();
	            orderMainList.add(orderMain);
	            orderCreateService.inventoryUnLock(orderMainList);*/

			}catch(Exception e){
				logger.error("订单取消失败, {}", e);
			}
		}
		logger.debug("cancelNoPayOrder end");
	}

	
	   /**
	 * Description:
	 * @return
	 */
	//获取半小时前的时间YUSL 2018/1/29
	private Date getDate() {
		long currentTime = System.currentTimeMillis() ;

		currentTime -=30*60*1000;

		Date date=new Date(currentTime);
			Calendar now =Calendar.getInstance();
			now.setTime(date);
		return now.getTime();
	}


	@Override
	    public void remindMsgSent() {
	        logger.debug("remindMsgSent start");
	        List<OrderMain> mainList = orderMainService.findNoPayOrderResendMsg();
	        if(mainList == null || mainList.isEmpty()){
	            return;
	        }
	        for (OrderMain orderMain : mainList) {
	            String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo());
	            try{
	                orderCreateService.supportResend24Hours(orderSubNo);
	                orderMain.setRemindSent(1l);
	                orderMainService.update(orderMain);
	            }catch(Exception e){
	                logger.error("未支付订单提醒短信发送失败, {}", e);
	            }
	        }
	        logger.debug("remindMsgSent end");
	    }
	   

	
}
