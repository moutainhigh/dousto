package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.intf.OrderSubDao;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetAdd_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:53
 * 
 * @author:Yong Hong Luo
 */
@Service("orderSubService")
public class OrderSubServiceImpl extends BaseServiceImpl<OrderSub, Long> implements OrderSubService {

    private OrderSubDao orderSubDao;

    @Autowired
    public void setOrderSubDao(OrderSubDao orderSubDao) {
        super.setBaseDao(orderSubDao);
        this.orderSubDao = orderSubDao;
    }

    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderInvoiceService orderInvoiceService;
    @Autowired
    OrderRetAddService orderRetAddService; 

    /**
     * 通过主订单号获取子订单
     * 
     * @param orderMainNo
     */
    public List<OrderSub> getByOrderMainNo(String orderMainNo) {
        List<OrderSub> orderSubList = this.orderSubDao.getByOrderMainNo(orderMainNo);
        for (OrderSub sub : orderSubList) {
            loadOrderSubOthes(sub);
        }
        return orderSubList;
    }
    
    
    /**
     * 通过退款订单号获取子订单 但是增加判断item能否退款功能（退货用）
     * 
     * @param orderMainNo
     */
    public List<OrderSub> getByOrderMainNoForReturn(String orderMainNo) {
        List<OrderSub> orderSubList = this.orderSubDao.getByOrderMainNo(orderMainNo);
        for (OrderSub sub : orderSubList) {
        	loadOrderSubOthesForReturn(sub);
        }
        return orderSubList;
    }
    
    
    /**
     * 加载订单的其他信息(退货用)
     * 
     * @param orderSub
     */
    public void loadOrderSubOthesForReturn(OrderSub orderSub) {
        if (null == orderSub) {
            return;
        }
        String orderSubNo = orderSub.getOrderSubNo();
        
        //其实这个是在创建逆向订单 所以取的时候都从正向订单取
        List<OrderItem> orderItemList = orderItemService.getByOrdeSubNo(orderSubNo);
        
        Long totalRemainNum = 0l;
        for(OrderItem orderItem: orderItemList){
        	orderItem.setRemainNum(0l);
        	OrderRetAdd orderRetAdd = orderRetAddService.getByField(OrderRetAdd_.orderItemNo, orderItem.getOrderItemNo());
        	if (null != orderRetAdd && null != orderRetAdd.getRemainNum()){
        		orderItem.setRemainNum(orderRetAdd.getRemainNum());
        		totalRemainNum += orderRetAdd.getRemainNum();
        	}
        }
        orderSub.setTotalRemainNum(totalRemainNum);
	    orderSub.setOrderItems(orderItemList); 
        // 加载发票信息
        orderSub.setOrderInvoices(orderInvoiceService.getByOrdeSubNo(orderSubNo));
        // ......

    }

    /**
     * 加载订单的其他信息
     * 
     * @param orderSub
     */
    public void loadOrderSubOthes(OrderSub orderSub) { 
        if (null == orderSub) {
            return;
        }
        String orderSubNo = orderSub.getOrderSubNo();
        
        // 加载正向订单明细
        List<OrderItem> orderItemList = orderItemService.getByOrdeSubNo(orderSubNo);
        if ( null != orderSub.getBillType()){
	        if (new Long(CommonConst.OrderMain_BillType_Positive.getCodeLong()).equals(orderSub.getBillType())) { // 正向订单
				if (null != orderItemList && orderItemList.size() > 0) {

					Long totalRemainNum = 0l;
					for (OrderItem orderItem : orderItemList) {
						orderItem.setRemainNum(0l);
						OrderRetAdd orderRetAdd = orderRetAddService
								.getByField(OrderRetAdd_.orderItemNo,
										orderItem.getOrderItemNo());
						if (null != orderRetAdd
								&& null != orderRetAdd.getRemainNum()) {
							orderItem.setRemainNum(orderRetAdd.getRemainNum());
							totalRemainNum += orderRetAdd.getRemainNum();
						}
					}
					orderSub.setTotalRemainNum(totalRemainNum);
					orderSub.setOrderItems(orderItemList);
				}
	        } else if (new Long(CommonConst.OrderMain_BillType_Negative.getCodeLong()).equals(orderSub.getBillType())) { // 逆向订单
	            // 加载逆向订单明细
	        	List<OrderRetChgItem> orderRetChgItemList = orderRetChgItemService.getByOrdeSubNo(orderSubNo);
	            Long totalRemainNum = 0l;
	            for(OrderRetChgItem orderRetChgItem: orderRetChgItemList){
	            	orderRetChgItem.setRemainNum(0l);
	            	OrderRetAdd orderRetAdd = orderRetAddService.getByField(OrderRetAdd_.orderItemNo, orderRetChgItem.getRefOrderItemNo());
	            	if (null != orderRetAdd && null != orderRetAdd.getRemainNum()){
	            		orderRetChgItem.setRemainNum(orderRetAdd.getRemainNum());
	            		totalRemainNum += orderRetAdd.getRemainNum();
	            	}
	            }
	            orderSub.setTotalRemainNum(totalRemainNum);
	            orderSub.setOrderRetChgItems(orderRetChgItemList);
	        }
        }
        orderSub.setOrderItems(orderItemList);
        // 加载发票信息
        orderSub.setOrderInvoices(orderInvoiceService.getByOrdeSubNo(orderSubNo));
        // ......

    }

    /**
     * 通过子订单号获取
     * 
     * @param orderSubNo
     */
    public OrderSub getByOrderSubNo(String orderSubNo) {
        OrderSub orderSub = this.orderSubDao.getByOrderSubNo(orderSubNo);
        loadOrderSubOthes(orderSub);
        return orderSub;
    }

    /**
     * 通过子订单获取明细的所有实际支付金额汇总
     * 
     * @param orderSub
     */
    public BigDecimal getPayAmountByOrderSubNo(OrderSub orderSub) {
        BigDecimal amountTotal = new BigDecimal(0);
        if (null == orderSub) {
            return amountTotal;
        }
        List<OrderItem> items = orderSub.getOrderItems();
        if (null == items || items.size() == 0) {
            items = this.orderItemService.getByOrdeSubNo(orderSub.getOrderSubNo());
        }
        for (OrderItem item : items) {
            BigDecimal amount = item.getPayAmount();
            amountTotal = amountTotal.add(amount);
        }
        return amountTotal;
    }

}
