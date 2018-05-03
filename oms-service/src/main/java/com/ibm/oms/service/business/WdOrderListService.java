/**
 * 
 */
package com.ibm.oms.service.business;

import com.ibm.oms.intf.intf.WdOrderDTO;

/**
 * 微店 查询订单
 * @author xiaonanxiang
 *
 */
public interface WdOrderListService {
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    /**
     * 根据外部渠道订单号查询
     * @param aliasOrderNo 外部渠道订单号
     * @return
     */
    public WdOrderDTO findOrderList(String aliasOrderNo);
}
