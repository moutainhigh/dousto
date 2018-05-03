package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;

/**
 * 
 * 售后意向单传输WMS 处理
 * 
 * @author:xiaohl
 */
@Service("saleAfterOrderToWMSResumer")
public class SaleAfterOrderToWMSResumer implements OrdiErrResumer {

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;

    @Override
    public Boolean match(String errCode, String orderStatus) {
        if (ErrConst.SaleAfterOrder_ToWMS.getCode().equals(errCode)) {
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = SaleAfterOrder_WMS_Success;
        boolean result = returnChangeOrderService.sendOmsToWmsSaleAfterOrder(orderSubNo);//传输WMS
        if(!result){
            errMsg = SaleAfterOrder_WMS_Fail;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }

}
