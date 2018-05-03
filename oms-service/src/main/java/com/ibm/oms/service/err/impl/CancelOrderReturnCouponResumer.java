package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;

/**
 * 
    取消订单返还购物券
 */
@Service("cancelOrderReturnCouponResumer")
public class CancelOrderReturnCouponResumer implements OrdiErrResumer {

    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    OrderMainService orderMainService;

    @Override
    public Boolean match(String errCode, String orderStatus) {
        boolean isCancelStatus = orderStatus.equals(OrderStatus.ORDER_PAID_CANCEL.getCode()) 
                ||orderStatus.equals(OrderStatus.ORDER_PAY_CANCELLED.getCode());
        if (ErrConst.COUPON_RETURN.getCode().equals(errCode) && isCancelStatus) {
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = CancelOrder_ReturnCoupon_Fail;
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        if(null==orderMain){
            return errMsg + String.format("单号:%s 未找到", orderNo);
        }
        boolean ret = orderCreateService.returnCouponForCancel(orderNo);// 补回积分
        if (ret) {
            errMsg = CancelOrder_ReturnCoupon_Success;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }

}
