/**
 * 
 */
package com.ibm.oms.rs.service.impl;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.WdOrderDTO;
import com.ibm.oms.rs.service.OmsQueryOrderInfoService;
import com.ibm.oms.service.business.WdOrderListService;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;

/**
 * 微店 查询订单信息
 * @author xiaonanxiang
 *
 */
@Component("omsQueryOrderInfoService")
public class OmsQueryOrderInfoServiceImpl extends BaseRsServiceImpl implements OmsQueryOrderInfoService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    WdOrderListService wdOrderListService;
    
    @Override
    public WdOrderDTO wdOmsQueryOrderInfoDTO(String aliasOrderNo) {
        WdOrderDTO wdOrderDTO = new WdOrderDTO();
        wdOrderDTO = wdOrderListService.findOrderList(aliasOrderNo);
        return wdOrderDTO;
    }

}
