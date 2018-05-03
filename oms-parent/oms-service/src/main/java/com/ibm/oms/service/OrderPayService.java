package com.ibm.oms.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-03-14
 * 04:20:49
 * 
 * @author:Yong Hong Luo
 */
public interface OrderPayService extends BaseService<OrderPay, Long> {

    public List<OrderPay> getByOrderMainNo(String orderMainNo);

    /**
     * 保存逆向订单的换货抵扣支付方式(换货)
     * 
     * @param orderMain
     */
    public void saveChangePaidByOrderMain(OrderMain orderMain);
    
    void update(Collection<OrderPay> objs) ;

    public BigDecimal gettotalPaiedByOrderNo(String orderNo);

}
