package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;

/**
 * 
 * 取消未审核的订单后加回MY卡失败 处理
 * 
 * @author:xiaohl
 */
@Service("cancelOrderReturnMyCardResumer")
public class CancelOrderReturnMyCardResumer implements OrdiErrResumer {

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    OrderMainService orderMainService;

    @Override
    public Boolean match(String errCode, String orderStatus) {
        if (ErrConst.CancelOrder_ReturnMyCard.getCode().equals(errCode) && orderStatus.equals(OrderStatus.ORDER_PAID_CANCEL.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = CancelOrder_ReturnMyCard_Success;
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        if(null==orderMain){
            return CancelOrder_ReturnMyCard_Fail;
        }
        ResultDTO resultDTO = returnChangeOrderService.returnMyCard(orderNo, "", orderMain, true);// 补回积分
        if (resultDTO.getResult() != CommonConst.Common_Succeed_SUCCESS.getCodeInt()) {
            errMsg = CancelOrder_ReturnMyCard_Fail;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }

}
