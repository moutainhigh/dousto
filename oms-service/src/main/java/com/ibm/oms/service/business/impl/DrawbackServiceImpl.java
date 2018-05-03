package com.ibm.oms.service.business.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.business.DrawbackService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.util.CommonUtilService;

@Service("drawbackService")
public class DrawbackServiceImpl  implements DrawbackService {
	private Logger logger =  LoggerFactory.getLogger(getClass());
	
	@Resource
	protected OrderMainService orderMainService;
	
    @Autowired
    OrderPayService orderPayService;
    
    @Autowired
    CommonUtilService commonUtilService;
    
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    
    @Autowired
    OrderStatusService orderStatusService;
    
    @Autowired
    OrderNoService orderNoService;
    
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;

	@Override
	public void returnPayment(String orderNo, String operator ) {
		OrderMain orderMain = orderMainService.findByOrderNo(orderNo);
		String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
		
		//调用统一方法
		ResultDTO result = saleAfterOrderTransService.updateApproveRefundOrder(orderSubNo,operator);
		
		/*boolean isStatus =  this.orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S027080, null, null, null);
        
        if(!isStatus){
        	throw new BusinessException("订单状态不对，无法退款处理.");
        }

        try{
        	returnChangeOrderService.returnMyCard(orderNo, operator, orderMain,false);
        }catch(Exception e){
        	throw new BusinessException("调会员接口失败.");
        }*/
		
	}
	

}
