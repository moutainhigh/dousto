package com.ibm.oms.service.err.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.err.OrdiErrResumerVisitor;

/**
 * @author pjsong
 *
 */
@Service("ordiErrResumerVisitor")
public class OrdiErrResumerVisitorImpl implements OrdiErrResumerVisitor{
    @Autowired
    ImsDeductResumer imsDeductResumer;
    @Autowired
    PromoAddResumer promoAddResumer;
    @Autowired
    OrderProcessResumer orderProcessResumer;
    @Autowired
    SaleAfterOrderCancelReturnIntegralResumer saleAfterOrderCancelReturnIntegralResumer;
    @Autowired
    SaleAfterOrderToWMSCancelResumer saleAfterOrderToWMSCancelResumer;
    @Autowired
    SaleAfterOrderToWMSResumer saleAfterOrderToWMSResumer;
    @Autowired
    CancelOrderReturnMyCardResumer cancelOrderReturnMyCardResumer;
    @Autowired
    CancelOrderReturnCouponResumer cancelOrderReturnCouponResumer;
    
    @Override
    public String visit(Long id, OrderSub os, OrderMain om, String errCode) {
        String orderSubNo = os.getOrderSubNo();
        String orderNo = om.getOrderNo();
        String status = om.getStatusTotal();
        if(imsDeductResumer.match(errCode, status)){
            return imsDeductResumer.resume(id, orderSubNo, orderNo);
        }
        if(promoAddResumer.match(errCode, status)){
            return promoAddResumer.resume(id, orderSubNo, orderNo);
        }
        if(orderProcessResumer.match(errCode, status)){
            return orderProcessResumer.resume(id, orderSubNo, orderNo);
        }
        if(saleAfterOrderToWMSResumer.match(errCode, status)){
            return saleAfterOrderToWMSResumer.resume(id, orderSubNo, orderNo);
        }
        if(saleAfterOrderCancelReturnIntegralResumer.match(errCode, status)){
            return saleAfterOrderCancelReturnIntegralResumer.resume(id, orderSubNo, orderNo);
        }
        if(cancelOrderReturnMyCardResumer.match(errCode, status)){
            return cancelOrderReturnMyCardResumer.resume(id, orderSubNo, orderNo);
        }
        if(cancelOrderReturnCouponResumer.match(errCode, status)){
            return cancelOrderReturnCouponResumer.resume(id, orderSubNo, orderNo);
        }
        return null;
    }


    

}
