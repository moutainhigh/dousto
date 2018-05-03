package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;

/**
 * 
 * 售后意向单取消后传输WMS 处理【暂不调用，因为WMS传输失败意向单不会被取消】
 * @description:因为取消意向单传输WMS失败后流程不会往下走，so这里进行意向单取消操作【质检中-->退货取消】
 * 
 * @author:xiaohl
 */
@Service("saleAfterOrderToWMSCancelResumer")
public class SaleAfterOrderToWMSCancelResumer implements OrdiErrResumer {

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;

    @Override
    public Boolean match(String errCode, String orderStatus) {
        if (ErrConst.SaleAfterOrder_ToWMS_Cancel.getCode().equals(errCode) && OrderStatus.RET_ORDER_INSPECTION.getCode().equals(orderStatus)) {
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = this.SaleAfterOrder_WMS_Cancel_Success;
        //取消意向单【质检中-->退货取消】
        ResultDTO resultDTO = saleAfterOrderTransService.updateCancelSaleAfterOrder(orderSubNo);
        if(resultDTO.getResult()==CommonConst.Common_Succeed_FAIL.getCodeInt()){
            errMsg = SaleAfterOrder_WMS_Cancel_Fail;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }

}
