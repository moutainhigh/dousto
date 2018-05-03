package com.ibm.oms.service.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.StatusActionDict;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.StatusTransDict;
import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.service.StatusActionDictService;
import com.ibm.oms.service.StatusDictService;
import com.ibm.oms.service.StatusTransDictService;
import com.ibm.oms.service.mq.OmsStatusUpdateTopicSender;
import com.ibm.oms.service.mq.WeiDianStatusSender;



/**
 * @author pjsong
 *
 */
@Component
public class StatusUtil {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    OmsStatusUpdateTopicSender omsStatusUpdateTopicSender;
    @Autowired
    WeiDianStatusSender weiDianStatusSender;
    @Autowired
    private StatusDictService sds;
    @Autowired
    private StatusActionDictService sads;
    @Autowired
    private StatusTransDictService stds;
    
    private List<StatusDict> statusList;
    private List<StatusActionDict> statusActionList;
    private List<StatusTransDict> statusTransList;
    //根据状态编码得到状态类型, key:status, value:type
    private Map<String, String> statusToTypeMap = new HashMap<String, String>();
    //根据action得到目标状态集 key:action_no, value:<list:statusCode>
    private Map<String, List<String>> actionToTargetStatusMap = new HashMap<String, List<String>>();
    //根据action得到使用者认定的当前状态 key:action_no, value:pre_status
    private Map<String, String> actionToCurrentStatusMap = new HashMap<String, String>();

    private  Map<String,StatusDict> orderStatusMap = new HashMap<String,StatusDict>(); 
    /**
     * 
     * 功能描述: 初始化
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @PostConstruct
    public void initialize() {
        LOGGER.info("开始加载状态流程字典");
        statusList = sds.getAll();
        statusActionList = sads.getAll();
        statusTransList = stds.getAll();

        // 初始化状态到状态类型map
        for (StatusDict os : statusList) {
            if (statusToTypeMap.containsKey(os.getStatusCode())) {
                continue;
            }
            statusToTypeMap.put(os.getStatusCode(), os.getStatusTypeCode());
            
            orderStatusMap.put(os.getStatusCode(), os);
        }

        // 初始化action编码到目标状态list
        for (StatusTransDict std : statusTransList) {
            List<String> targetList;
            if (actionToTargetStatusMap.containsKey(std.getActionNo())) {
                targetList = actionToTargetStatusMap.get(std.getActionNo());
            } else {
                targetList = new ArrayList<String>();
                actionToTargetStatusMap.put(std.getActionNo(), targetList);

            }
            targetList.add(std.getTargetStatusNo());
        }
        
        //初始化action编码到使用者认为的当前状态
        for (StatusTransDict std : statusTransList) {
            if (actionToCurrentStatusMap.containsKey(std.getActionNo())) {
                continue;
            }
            actionToCurrentStatusMap.put(std.getActionNo(), std.getStatusNo());
        }
    }

    public StatusDictService getSds() {
        return sds;
    }

    public void setSds(StatusDictService sds) {
        this.sds = sds;
    }

    public StatusActionDictService getSads() {
        return sads;
    }

    public void setSads(StatusActionDictService sads) {
        this.sads = sads;
    }

    public StatusTransDictService getStds() {
        return stds;
    }

    public void setStds(StatusTransDictService stds) {
        this.stds = stds;
    }

    public List<StatusDict> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusDict> statusList) {
        this.statusList = statusList;
    }

    public List<StatusActionDict> getStatusActionList() {
        return statusActionList;
    }

    public void setStatusActionList(List<StatusActionDict> statusActionList) {
        this.statusActionList = statusActionList;
    }

    public List<StatusTransDict> getStatusTransList() {
        return statusTransList;
    }

    public void setStatusTransList(List<StatusTransDict> statusTransList) {
        this.statusTransList = statusTransList;
    }

    public Map<String, String> getStatusToTypeMap() {
        return statusToTypeMap;
    }

    public void setStatusToTypeMap(Map<String, String> statusToTypeMap) {
        this.statusToTypeMap = statusToTypeMap;
    }

    public Map<String, List<String>> getActionToTargetStatusMap() {
        return actionToTargetStatusMap;
    }

    public void setActionToTargetStatusMap(Map<String, List<String>> actionToTargetStatusMap) {
        this.actionToTargetStatusMap = actionToTargetStatusMap;
    }

    public Map<String, String> getActionToCurrentStatusMap() {
        return actionToCurrentStatusMap;
    }

    public void setActionToCurrentStatusMap(Map<String, String> actionToCurrentStatusMap) {
        this.actionToCurrentStatusMap = actionToCurrentStatusMap;
    }

	public Map<String, StatusDict> getOrderStatusMap() {
		return orderStatusMap;
	}

	public void setOrderStatusMap(Map<String, StatusDict> orderStatusMap) {
		this.orderStatusMap = orderStatusMap;
	}

    
    /**
     * 发送状态变更消息
     * @param osa
     * @param orderNo
     * @param orderSubNo
     */
    public void updateStatusToTopic(OrderStatusAction osa, String orderNo, String orderSubNo) {
        // 找到目标状态集
        List<String> targetStatusCodes = actionToTargetStatusMap.get(osa.getCode());
        // 逐个写入状态
        for (String targetStatus : targetStatusCodes) {
            // 将状态更新消息发送到主题
            String targetStatusType = statusToTypeMap.get(targetStatus);
            OMSStatusUpdateDTO omsStatusUpdateDTO = new OMSStatusUpdateDTO();
            omsStatusUpdateDTO.setNewStatus(targetStatus);
            StatusDict dic = orderStatusMap.get(targetStatus);
            if (null != dic) {
                omsStatusUpdateDTO.setNewStatusName(dic.getStatusName());
            }
            omsStatusUpdateDTO.setOrderNo(orderNo);
            omsStatusUpdateDTO.setOrderSubNo(orderSubNo);
            omsStatusUpdateDTO.setStatusType(targetStatusType);
            omsStatusUpdateDTO.setUpdateTime(new Date());
            try {
                ObjectMapper objMap = new ObjectMapper();
                String statusStr = objMap.writeValueAsString(omsStatusUpdateDTO);
                LOGGER.info(statusStr);
                omsStatusUpdateTopicSender.send(statusStr);// 发送topic
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            // 将状态更新消息发送给微店(Queue)
//            weiDianStatusSender.send(omsStatusUpdateDTO);
        }
    }
}
