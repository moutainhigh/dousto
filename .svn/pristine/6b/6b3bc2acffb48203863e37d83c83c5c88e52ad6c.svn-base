package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderItemPay Data Access Object (DAO) interface.
 * Creation date:2014-03-14 04:20:35
 * @author:Yong Hong Luo
 */
public interface OrderItemPayDao extends BaseDao<OrderItemPay,Long>{
	
    /**
     * 根据订单号和支付方式删除
     * @param orderNo,payCode
     * @return 
     */
    public int deleteByOrderNoAndPayCode(String orderNo, String payCode);
}
