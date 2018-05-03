/**
 * 
 */
package com.ibm.oms.rs.service.impl;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.rs.service.WdQueryOrderStatusService;
import com.ibm.oms.service.business.WdOmsOrderHandlerService;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;

/**
 * 微店 查询订单状态
 * @author xiaonanxiang
 *
 */
@Component("wdQueryOrderStatusService")
public class WdQueryOrderStatusServiceImpl extends BaseRsServiceImpl implements WdQueryOrderStatusService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    WdOmsOrderHandlerService wdOmsOrderHandlerService;
    
    /**
     * 根据子订单号查询订单状态
     * @param orderSubNo 子订单号
     * @param statusType 状态类型  "01":"销售订单总状态","02":"逆向订单总状态","04":"销售订单支付状态",
     *                   "05":"逆向订单退款状态","06","销售订单物流状态" 
     * @return
     */
    @Override
    @GET
    @Path("/QueryOrderStatus")
    public OMSStatusUpdateDTO queryOrderStatusDTO(@FormParam("orderSubNo") String orderSubNo,@FormParam("statusType") String statusType) {
        OMSStatusUpdateDTO omsStatusUpdateDTO = new OMSStatusUpdateDTO();
        try {
            omsStatusUpdateDTO = wdOmsOrderHandlerService.queryOrderStatus(orderSubNo, statusType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("WdQueryOrderStatusServiceImpl-->exception, {}", e);
        }
        return omsStatusUpdateDTO;
    }

}
