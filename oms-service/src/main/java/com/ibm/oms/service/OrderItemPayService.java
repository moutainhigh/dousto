
package com.ibm.oms.service;

import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:20:35
 * @author:Yong Hong Luo
 */
public interface OrderItemPayService extends BaseService<OrderItemPay,Long>{
	
    /**
     * 根据订单号和支付方式删除
     * @param orderNo,payCode
     * @return 
     */
    public boolean deleteByOrderNoAndPayCode(String orderNo, String payCode);

}
