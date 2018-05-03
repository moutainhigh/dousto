package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
/**
 * OrderRetChange Data Access Object (DAO) interface.
 * Creation date:2014-03-24 11:55:38
 * @author:Yong Hong Luo
 */
public interface OrderRetChangeDao extends BaseDao<OrderRetChange,Long>{
	
    /**
     * 根据退货类型查询 逆向订单
     * @param returnType 逆向类型
     * @param returnType 退货类型
     * @param pager
     * @return
     */
    public Pager findByReturnType(String orderCategory, Pager pager);

}
