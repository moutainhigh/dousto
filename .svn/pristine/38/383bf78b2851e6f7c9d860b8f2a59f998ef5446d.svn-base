package com.ibm.oms.service.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.OrderStatusType;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusMainOrderService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.mq.OmsStatusUpdateTopicSender;
import com.ibm.oms.service.mq.WeiDianStatusSender;
import com.ibm.oms.service.util.StatusUtil;

/**
 * @author pjsong
 * 
 */
@Service("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderStatusLogService orderStatusLogService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    StatusUtil statusUtil;
    @Autowired
    OmsStatusUpdateTopicSender omsStatusUpdateTopicSender;
    @Autowired
    WeiDianStatusSender weiDianStatusSender;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    OrderStatusMainOrderService orderStatusMainOrderService;
    
    
    @Override
    public boolean saveProcess(String orderSubNo, OrderStatusAction osa, String operator, Date operateTime, String remark) {
        //根据状态编码得到状态类型, key:status, value:type
        Map<String, String> statusToTypeMap = statusUtil.getStatusToTypeMap();
        //需获取状态名称
        Map<String,StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();
        //根据action得到目标状态集 key:action_no, value:<list:statusCode>
        Map<String, List<String>> actionToTargetStatusMap = statusUtil.getActionToTargetStatusMap();
        //根据action得到使用者认定的当前状态 key:action_no, value:pre_status
        Map<String, String> actionToCurrentStatusMap = statusUtil.getActionToCurrentStatusMap();
        String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
        OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
        OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNo);
        
        String previousStatus = actionToCurrentStatusMap.get(osa.getCode());
        String previousStatusType = statusToTypeMap.get(previousStatus);
        //实际当前状态与使用者传入action不一致
        String statusOfDB = getPreviousOrderStatus(om, os, previousStatusType);
        if(!previousStatus.equals(statusOfDB)){
        	logger.info("OrderStatusServiceImpl-->saveProcess-->[实际当前状态：{}]与[使用者传入的action:{}不一致]",statusOfDB,previousStatus);
            return false;
        }
        
        //找到目标状态集
        List<String> targetStatusCodes = actionToTargetStatusMap.get(osa.getCode());
        Date currDate = new Date();
        // 逐个写入状态
        for (String targetStatus : targetStatusCodes) {
            String targetStatusType = statusToTypeMap.get(targetStatus);
            // 写状态日志表
            writeStatusLog(om, os, previousStatus, targetStatusType, targetStatus, operator, operateTime, remark);
            // 需要更新OrderMain的状态类型,总状态，审核，支付
            boolean orderStatusType = OrderStatusType.Order_Status_Positive.getCode().equals(targetStatusType)
                    || OrderStatusType.Order_Status_Negative.getCode().equals(targetStatusType);
            boolean orderAuditStatusType = OrderStatusType.Order_Audit.getCode().equals(targetStatusType);
            boolean orderPayStatusType = OrderStatusType.Order_Pay_Positive.getCode().equals(targetStatusType)
                    || OrderStatusType.Order_Pay_Negative.getCode().equals(targetStatusType);
            boolean orderDeliveryType = OrderStatusType.Order_Delivery.getCode().equals(targetStatusType);
            if (orderStatusType) {
                om.setStatusTotal(targetStatus);
            } else if (orderAuditStatusType) {
                om.setStatusConfirm(targetStatus);
            } else if (orderPayStatusType) {
                om.setStatusPay(targetStatus);
            } else if (orderDeliveryType) {
                // 需要更新OrderSub的状态类型
                if (os != null) {
                    os.setLogisticsStatus(targetStatus);
                }
            }
            if (OrderStatus.isLastStatus(targetStatus)) {
                om.setFinishTime(new Date());
            }else if (OrderStatus.isLastStatusNegative(targetStatus)) {
                om.setFinishTime(new Date());
            }
            om.setUpdatedBy(operator);
            om.setDateUpdated(currDate);
            orderMainService.update(om);
            //同步修改orderItem状态
            List<OrderItem> orderItems = orderItemService.getByOrdeNo(om.getOrderNo());
            for (OrderItem orderItem : orderItems) {
            	//logger.info("OrderStatusServiceImpl-->同步修改子订单状态-->[omsStatusUpdateDTO:{}]",statusStr);
            	orderItem.setItemStatus(targetStatus);
            	orderItemService.update(orderItem);
            }
            //如果是拆分单更新所有item状态以后，需要同步更新OriginOrderMain状态
            if(orderStatusMainOrderService.isSplitOrder(orderNo)){
            	orderStatusMainOrderService.updateMainOrderStatus(orderNo);
            }
            
            
            
            if (os != null) {
                os.setDateUpdated(currDate);
                orderSubService.update(os);
            }
            
            //这两种状态变更需要手动发送状态变更，调用StatusUtil.updateStatusToTopic()
            if(OrderStatusAction.S011030.equals(osa) || OrderStatusAction.S013020.equals(osa)|| OrderStatusAction.S011020.equals(osa)){
                continue;
            }
            //将状态更新消息发送到主题
            OMSStatusUpdateDTO omsStatusUpdateDTO = new OMSStatusUpdateDTO();
            omsStatusUpdateDTO.setNewStatus(targetStatus);
            StatusDict dic = orderStatusMap.get(targetStatus);
            if(null!=dic){
                omsStatusUpdateDTO.setNewStatusName(dic.getStatusName());
            }
            omsStatusUpdateDTO.setOrderNo(orderNo);
            omsStatusUpdateDTO.setOrderSubNo(orderSubNo);
            omsStatusUpdateDTO.setStatusType(targetStatusType);
            omsStatusUpdateDTO.setUpdateTime(new Date());
            try{
                ObjectMapper objMap = new ObjectMapper();
                String statusStr = objMap.writeValueAsString(omsStatusUpdateDTO);
                logger.info(statusStr);
                logger.info("OrderStatusServiceImpl-->omsStatusUpdateDTO-->[omsStatusUpdateDTO:{}]",statusStr);
//                omsStatusUpdateTopicSender.send(statusStr);//发送topic
            }catch(Exception e){
                logger.info(e.getMessage());
            }
            //将状态更新消息发送给微店(Queue) 
//            weiDianStatusSender.send(omsStatusUpdateDTO);
        }
        
        return true;
    }

    
    private void writeStatusLog(OrderMain om, OrderSub os, String previousStatus, String targetStatusTypes, String targetStatus, String operator, Date operateTime, String remark){
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setIdOrder(om.getId());
        if(os!=null){
        	orderStatusLog.setIdOrderSub(os.getId());
            orderStatusLog.setOrderSubNo(os.getOrderSubNo());
        }
        orderStatusLog.setOrderNo(om.getOrderNo());
        orderStatusLog.setCreatedBy("writeStatusLog");
        orderStatusLog.setPreviousStatus(previousStatus);
        orderStatusLog.setCurrentStatus(targetStatus);
        orderStatusLog.setDateCreated(new Date());
        orderStatusLog.setDateUpdated(new Date());
        orderStatusLog.setOperateTime(operateTime == null?new Date():operateTime);
        orderStatusLog.setOperator(StringUtils.isBlank(operator)? "statusService_process":operator);
        if(StringUtils.isNotBlank(remark)){
            orderStatusLog.setRemark(remark);
        }
        orderStatusLogService.save(orderStatusLog);
    }

    /**
     * 根据子订单号和状态类型，获取当前的状态
     * @param orderSubNo 
     * @param ost orderStatusType
     * @return **/
    @Override
    public String getPreviousOrderStatus(OrderMain om, OrderSub os, String ost){
        //当前总状态
        if(OrderStatusType.Order_Status_Positive.getCode().equals(ost) || OrderStatusType.Order_Status_Negative.getCode().equals(ost)){
            if(om != null){
                return om.getStatusTotal();
            }
        }
      //当前支付状态
        if(OrderStatusType.Order_Pay_Positive.getCode().equals(ost) || OrderStatusType.Order_Pay_Negative.getCode().equals(ost)){
            if(om != null){
                return om.getStatusPay();
            }
        }
        //当前审核状态
        if(OrderStatusType.Order_Audit.getCode().equals(ost)){
            if(om != null){
                return om.getStatusConfirm();
            }
        }
        //当前物流状态
        if(OrderStatusType.Order_Delivery.getCode().equals(ost)){
            if(os != null){
                return os.getLogisticsStatus();
            }
        }
        return null;
    }
    


}
