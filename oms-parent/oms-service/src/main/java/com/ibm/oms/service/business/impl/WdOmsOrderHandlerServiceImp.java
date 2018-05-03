/**
 * 
 */
package com.ibm.oms.service.business.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.WdOmsOrderHandlerService;
import com.ibm.oms.service.util.StatusUtil;

/**
 * @author xiaonanxiang
 *
 */
@Component("wdOmsOrderHandlerService")
public class WdOmsOrderHandlerServiceImp implements WdOmsOrderHandlerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private OrderSubService orderSubService;
    @Resource
    private IntfReceivedService intfReceivedService;
    @Resource
    private StatusUtil statusUtil;
    
    /**
     * 根据子订单号查询订单状态
     * @param orderSubNo 子订单号
     * @param statusType 状态类型  "01":"销售订单总状态","02":"逆向订单总状态","04":"销售订单支付状态",
     *                   "05":"逆向订单退款状态","06","销售订单物流状态" 
     * @return
     */
    @Override
    public OMSStatusUpdateDTO queryOrderStatus(String orderSubNo,String statusType) {
        OMSStatusUpdateDTO dto = new OMSStatusUpdateDTO();
        OrderMain orderMain = orderMainService.getByOrderSubNo(orderSubNo);
        if(null == orderMain)
        {
            dto.setReturnCode(FAIL);
            dto.setReturnMsg(ORDER_MAIN_NULL);
            logger.error(ORDER_MAIN_NULL);
            return dto;
        }
        // 销售订单总状态
        if(ORDER_STATUS_TOTAL.equals(statusType))
        {
            Map<String, StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();
            String statusName = orderStatusMap.get(orderMain.getStatusTotal()).getDisplayName();
            dto.setNewStatus(orderMain.getStatusTotal());
            dto.setNewStatusName(statusName);
        }
        // 销售订单物流状态
        else if(ORDER_STATUS_LOGISTICS.equals(statusType))
        {
            OrderSub orderSub = orderSubService.getByOrderSubNo(orderSubNo);
            Map<String, StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();
            String statusName = null;
            if(null != orderSub.getLogisticsStatus())
            {
                statusName = orderStatusMap.get(orderSub.getLogisticsStatus()).getDisplayName();
            }
            dto.setNewStatus(orderSub.getLogisticsStatus());
            dto.setNewStatusName(statusName);
        }
        else
        {
            dto.setReturnCode(FAIL);
            dto.setReturnMsg("目前只提供销售订单总状态和销售订单物流状态，其它类型的状态还未提供，需要时请联系提供方！");
            logger.error("目前只提供销售订单总状态和销售订单物流状态，其它类型的状态还未提供，需要时请联系提供方！");
            return dto;
        }
        dto.setOrderNo(orderMain.getOrderNo());
        dto.setOrderSubNo(orderSubNo);
        dto.setStatusType(statusType);
        dto.setUpdateTime(new Date()); 
        dto.setReturnCode(SUCCESS);
        dto.setReturnMsg(OK);
        return dto;
    }
}
