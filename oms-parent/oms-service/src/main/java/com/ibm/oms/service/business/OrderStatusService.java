
package com.ibm.oms.service.business;

import java.util.Date;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;

/**
 * @author pjsong
 *
 */
public interface OrderStatusService {

	/**处理状态流转, 如果有关联订单状态更新，使用总状态ISP,ISN两种状态类型。
	 * @param orderSubNo 子订单号
	 * @param osa 
	 * @param operator 
	 * @param operateTime 
	 * @param remark 
	 * @return true, false
	 */
    boolean saveProcess(String orderSubNo, OrderStatusAction osa, String operator, Date operateTime, String remark);

    String getPreviousOrderStatus(OrderMain om, OrderSub os, String ost);

    public void newWriteStatusLog(OrderMain om, OrderSub os, String previousStatus, String targetStatus, String operator,String operatorNo,String operatorRole, Date operateTime, String remark);
	
    /**
     * 更新状态
     * 
     * @param orderSubNo
     * @param osa
     * @param operator
     * @param operateTime
     * @param remark
     * @return
     */
    public boolean updateStatus(String orderSubNo, OrderStatusAction osa, String operator, Date operateTime, String remark);
}