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
 * 促销资源处理
 * @author:Yong Hong Luo
 */
@Service("promoAddResumer")
public class PromoAddResumer implements OrdiErrResumer{
    
    @Autowired 
    OrderCreateService orderCreateService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Override
    public Boolean match(String errCode, String orderStatus) {
        if(ErrConst.PROMOADD_FAILED.getCode().equals(errCode) && OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(orderStatus)){
            return true;
        }
        return false;
    }

    @Override
    public String resume(Long errId, String orderSubNo, String orderNo) {
        String errMsg = PromoAddResumer1;
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        if(!orderCreateService.promoResourceAdd(om.getBatchNum(), orderNo)){
            errMsg = PromoAddResumer2;
        }
        ordiErrOptLogService.delete(errId);
        return errMsg;
    }
    

}
