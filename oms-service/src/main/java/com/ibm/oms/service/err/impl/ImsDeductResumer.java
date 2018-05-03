package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumer;
/**
 * 
 * 库存扣减
 * @author:Yong Hong Luo
 */
@Service("imsDeductResumer")
public class ImsDeductResumer implements OrdiErrResumer{
    
    @Autowired 
    OrderCreateService orderCreateService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Override
    public Boolean match(String errCode, String orderStatus) {
        if((ErrConst.IMS_DEDUCT.getCode().equals(errCode)||ErrConst.IMS_CANCEL_DEDUCT.getCode().equals(errCode)) && OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(orderStatus)){
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = ImsDeductResumer1;
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        if (!orderCreateService.inventoryDeduct(orderNo)) {
            errMsg = ImsDeductResumer2;
        }else{
            orderCreateService.supportSendEmail(orderNo);
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }
    

}
