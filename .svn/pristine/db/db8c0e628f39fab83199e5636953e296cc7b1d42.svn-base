package com.ibm.oms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.dao.intf.OrderStatusLogDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.util.StatusUtil;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:51
 * @author:Yong Hong Luo
 */
@Service("orderStatusLogService")
public class OrderStatusLogServiceImpl extends BaseServiceImpl<OrderStatusLog,Long> implements
		OrderStatusLogService{
    
	private OrderStatusLogDao orderStatusLogDao;
    private OrderMainDao orderMainDao;
    @Autowired
    StatusUtil statusUtil;
    public String getStatusTotalPositive(String orderNo){
//        OrderMain om = orderMainDao.get("orderNo", orderNo);
//        if(om == null){
//            return null;
//        }
//        return om.getStatusTotal();
        return null;
    }
    
    
    
	@Autowired
	public void setOrderStatusLogDao(OrderStatusLogDao orderStatusLogDao) {
	    super.setBaseDao(orderStatusLogDao);
		this.orderStatusLogDao = orderStatusLogDao;
	}


	@Override
	public Pager findOrderStstusLogByOrderNo(String orderNo, int statusType, Pager pager) {
		// TODO Auto-generated method stub
		
		pager = orderStatusLogDao.findOrderStatusLog(orderNo, statusType, pager);
		
		changeOrderStatusCodeToName(pager.getList());
		
		return pager;
	}
	
	/**
	 * 订单状态编码转化状态名称
	 * @param resultList
	 */
	public void changeOrderStatusCodeToName(List<OrderStatusLog> resultList) {
				
		Map<String,StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();
		//String previousStatus = null;
		String currentStatus = null;
		for (OrderStatusLog orderStatusLog: resultList) {
			//previousStatus = orderStatusLog.getPreviousStatus();
			//if(null != orderStatusMap.get(previousStatus)){
			//orderStatusLog.setPreviousStatus(orderStatusMap.get(previousStatus).getStatusName());	
			//}
			currentStatus = orderStatusLog.getCurrentStatus();
			if(null != orderStatusMap.get(currentStatus)){
				orderStatusLog.setRemark(orderStatusMap.get(currentStatus).getStatusName());
			}
		}
	}



	/* (non-Javadoc)
	 * @see com.ibm.oms.service.OrderStatusLogService#writeStatusLog(com.ibm.oms.domain.persist.OrderMain, com.ibm.oms.domain.persist.OrderSub, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public void writeStatusLog(OrderMain om, OrderSub os, String previousStatus, String targetStatus, String operator,
			Date operateTime, String remark) {
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
        orderStatusLogDao.save(orderStatusLog);
		
	}
	
	@Override
	public List<OrderStatusLog> findByOrderNo(String orderNo) {
		List<OrderStatusLog> list = orderStatusLogDao.findByOrderNo(orderNo);
		
		changeOrderStatusCodeToName(list);
		
		return list;
	}
}