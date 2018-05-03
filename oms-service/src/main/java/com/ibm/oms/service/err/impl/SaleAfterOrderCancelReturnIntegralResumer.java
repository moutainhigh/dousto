package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;

/**
 * 
 * 售后意向单取消补回积分 处理
 * 需退款的意向单审核通过后会扣除积分，再次取消需补回积分 【质检中-->退货取消】
 * @author:xiaohl
 */
@Service("saleAfterOrderCancelReturnIntegralResumer")
public class SaleAfterOrderCancelReturnIntegralResumer implements OrdiErrResumer {

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;

    @Override
    public Boolean match(String errCode, String orderStatus) {
        if (ErrConst.SaleAfterOrder_Cancel_ReturnIntegral.getCode().equals(errCode)) {
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = SaleAfterOrder_Cancel_ReturnIntegral_Success;
        ResultDTO resultDTO = returnChangeOrderService.handelIntegral("sys", orderNo, true, true);// 补回积分
        if (resultDTO.getResult() != CommonConst.Common_Succeed_SUCCESS.getCodeInt()) {
            errMsg = SaleAfterOrder_Cancel_ReturnIntegral_Fail;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }

}
