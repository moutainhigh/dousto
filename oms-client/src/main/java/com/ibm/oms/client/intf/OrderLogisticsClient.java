package com.ibm.oms.client.intf;

import java.util.List;
import java.util.Map;

import com.ibm.oms.client.dto.order.OrderLogisticsMessageClientDTO;
import com.ibm.oms.client.dto.order.OrderLogisticsSubscriptionClientDTO;
import com.ibm.oms.client.dto.order.OrderSplitLogisticsClientDTO;
import com.ibm.oms.client.dto.order.UpdateReturnPfAsnBean;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月1日 
 */
public interface OrderLogisticsClient {


    public List<OrderSplitLogisticsClientDTO> findOrderLogisticsByOrderNo(String orderNo);

    
    public List<OrderLogisticsMessageClientDTO> findOrderLogisticsMessageByOrderNo(String orderId);
    /**
     * @Description: 快递100回调接口
     * @author: yusl  
     * @date: 2018/3/15 15:43  
     * @param: [String]  
     * @return: String
     **/  

    public String createOrderLogisticsMessage(String param, String sign);
    
    /**
     * @Description: 快递100订阅接口
     * @author: yusl  
     * @date: 2018/3/15 15:43  
     * @param: [String]  
     * @return: String
     **/  
	String createOrderLogistics(UpdateReturnPfAsnBean upd);
}
