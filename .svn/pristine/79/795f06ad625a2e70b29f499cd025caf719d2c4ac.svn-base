
package com.ibm.oms.service;

import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.intf.intf.inner.CombineProductDTO;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:19:15
 * @author:Yong Hong Luo
 */
public interface OrderCombineRelationService extends BaseService<OrderCombineRelation,Long>{
	/**
	 * @param params
	 * @return
	 */
	OrderCombineRelation findByFields(Map<String, Object> params);

    /**
     * @param combineMapList
     * @param codeItemMap
     */
    void saveBatch(Map<String, List<CombineProductDTO>> combineMapList, Map<String, OrderItem> codeItemMap);

    List<OrderCombineRelation> findByOrderItemNoAndCombineNo(String orderItemNo, String combineNo);
}
