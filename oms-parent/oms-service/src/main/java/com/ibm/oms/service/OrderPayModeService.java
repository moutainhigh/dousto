package com.ibm.oms.service;

import java.math.BigDecimal;

import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-05-08
 * 09:28:37
 * 
 * @author:Yong Hong Luo
 */
public interface OrderPayModeService extends BaseService<OrderPayMode, Long> {

    BigDecimal getTotalPayByOrderNo(String orderNo);

}
