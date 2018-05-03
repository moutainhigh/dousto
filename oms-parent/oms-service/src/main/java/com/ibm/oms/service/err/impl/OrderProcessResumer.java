package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;
/**
 * 
 * 订单处理
 * @author:Yong Hong Luo
 */
@Service("orderProcessResumer")
public class OrderProcessResumer implements OrdiErrResumer{
    
    @Autowired 
    OrderCreateService orderCreateService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    OrderStatusService orderStatusService;
    
    @Override
    public Boolean match(String errCode, String orderStatus) {
        if(ErrConst.PROCESS_FAILED.getCode().equals(errCode) && OrderStatus.ORDER_PROCESS_FAILED.getCode().equals(orderStatus)){
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
        String errMsg = OrderProcessResumer1;
        //当前状态为0121，先改回到0120
        if (OrderStatus.ORDER_PROCESS_FAILED.getCode().equals(om.getStatusTotal())) {
            orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012120, "", null, null);
        }
        orderCreateService.callSingleProcess(orderNo);
        if(OrderStatus.ORDER_PROCESS_FAILED.getCode().equals(om.getStatusTotal())){
            errMsg = OrderProcessResumer2;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }
    

}
