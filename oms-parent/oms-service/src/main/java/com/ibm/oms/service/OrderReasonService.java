package com.ibm.oms.service;

import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and persistence layer. Creation date:2014-04-16
 * 03:07:06
 * 
 * @author:Yong Hong Luo
 */
public interface OrderReasonService extends BaseService<OrderReason, Long> {

    public static final String rootReasonNo = "0"; // 一级原因的id
    public static final Long isEffected = 1l; // 生效

    /**
     * 获取售后原因的列表(包含一、二级列表)
     */
    public Map<OrderReason, List<OrderReason>> getReasonMap();

}
