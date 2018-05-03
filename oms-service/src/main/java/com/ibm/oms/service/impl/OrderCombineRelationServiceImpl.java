package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderCombineRelationDao;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.intf.intf.inner.CombineProductDTO;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:19:15
 * @author:Yong Hong Luo
 */
@Service("orderCombineRelationService")
public class OrderCombineRelationServiceImpl extends BaseServiceImpl<OrderCombineRelation,Long> implements
		OrderCombineRelationService{
    
	private OrderCombineRelationDao orderCombineRelationDao;
    
	@Autowired
	public void setOrderCombineRelationDao(OrderCombineRelationDao orderCombineRelationDao) {
	    super.setBaseDao(orderCombineRelationDao);
		this.orderCombineRelationDao = orderCombineRelationDao;
	}
	
	@Override
	public void saveBatch(Map<String, List<CombineProductDTO>> combineMapList, Map<String, OrderItem> codeItemMap){
        for (String combineNo : combineMapList.keySet()) {
            List<CombineProductDTO> combList = combineMapList.get(combineNo);
            // 实体信息写入上下文
            for (CombineProductDTO cpDTO : combList) {
                OrderCombineRelation ocr = new OrderCombineRelation();
                BeanUtils.copyProperties(cpDTO, ocr);
                ocr.setOrderItemNo(codeItemMap.get(combineNo).getOrderItemNo());
                ocr.setIdOrderItem(codeItemMap.get(combineNo).getId());
                ocr.setCombineNo(combineNo);
                save(ocr);
            }
        }
	}
	
	@Override
	public OrderCombineRelation findByFields(Map<String, Object> params){
		List<OrderCombineRelation> comList =  orderCombineRelationDao.findByFields(params);
		return (comList.isEmpty()?null:comList.get(0)) ;
	}
	
	@Override
	public List<OrderCombineRelation> findByOrderItemNoAndCombineNo(String orderItemNo, String combineNo){
	   List<OrderCombineRelation> comList =  orderCombineRelationDao.findByOrderItemNoAndCombineNo(orderItemNo, combineNo);
	   return comList;
	}
}
