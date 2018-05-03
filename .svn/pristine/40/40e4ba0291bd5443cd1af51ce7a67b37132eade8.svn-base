package com.ibm.oms.rs.service.impl;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.rs.service.OmsOrderInfoService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;

/**
 * @author pjsong
 * 
 */
@Component("omsOrderInfoService")
public class OmsOrderInfoServiceImpl extends BaseRsServiceImpl  implements OmsOrderInfoService{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    OrderMainService orderMainService;
    @Override
    @GET
    @Path("/orderInfo")
    public TmsOrderDTO btcOmsReceiveOrderDTO(String orderSubNo) {
        TmsOrderDTO output = new TmsOrderDTO();
        return output;
    }

}
