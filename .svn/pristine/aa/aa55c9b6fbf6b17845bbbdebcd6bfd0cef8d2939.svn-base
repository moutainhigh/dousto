package com.ibm.oms.service.intf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.inner.OrderItemSnapShotDTO;


/**
 * @author pjsong
 * B2C的订单传输到OMS处理上下文
 */
public class ContextBtcOmsReceiveDTO {
    String batchNum;
    Map<String, OrderMain> omListMap;
    /**key订单号,Value子订单Map**/
    Map<String, List<OrderSub>> osListMap; 
    /**key订单号,Value订单行Map**/
    Map<String, List<OrderItem>> oiListMap;
    /**key订单号,Value虚拟订单行Map**/
    Map<String, List<OrderItemVirtual>> oivListMap;
    /**key子订单号,Value订单行Map**/
    Map<String, List<OrderItem>> osiListMap;
    boolean createSuccessFlag = false;
    boolean isPayOnArrival = false;
    /**key:　commodityCode,Value: detail**/
    Map<String, List<OrderCombineRelation>> combineItems;
    OrderItemSnapShotDTO snapShot;
    String msg = "";
    boolean isTotalPaid = false;
    public Map<String, OrderMain> getOmMap() {
        if(omListMap == null){
            omListMap = new HashMap<String, OrderMain>();
        }
        return omListMap;
    }


    public Map<String, List<OrderSub>> getOsListMap() {
        if(osListMap == null){
            osListMap = new HashMap<String, List<OrderSub>>();
        }
        return osListMap;
    }


    public Map<String, List<OrderItem>> getOiListMap() {
        if(oiListMap == null){
            oiListMap = new HashMap<String, List<OrderItem>>();
        }
        return oiListMap;
    }

    public Map<String, List<OrderItem>> getOsiListMap() {
        if(osiListMap == null){
            osiListMap = new HashMap<String, List<OrderItem>>();
        }
        return osiListMap;
    }



    public Map<String, List<OrderItemVirtual>> getOivListMap() {
        if(oivListMap == null){
            oivListMap = new HashMap<String, List<OrderItemVirtual>>();
        }
        return oivListMap;
    }

    public void setPayOnArrival(boolean isPayOnArrival) {
        this.isPayOnArrival = isPayOnArrival;
    }


    public boolean isCreateSuccessFlag() {
        return createSuccessFlag;
    }

    public boolean isPayOnArrival() {
        return isPayOnArrival;
    }


    public void setCreateSuccessFlag(boolean createSuccessFlag) {
        this.createSuccessFlag = createSuccessFlag;
    }

    /**
     * 根据单号从上下文中得到所有行
     * @param orderNo
     * @return
     */
    public List<OrderItem> getOrderItemsByOrderNo(String orderNo){
        Map<String,List<OrderItem>> oiListMap = getOiListMap();
        return oiListMap.get(orderNo);
    }
    /**
     * 根据子单号从上下文中得到所有实体行
     * @param orderSubNo
     * @return
     */
    public List<OrderItem> getOrderItemsByOrderSubNo(String orderSubNo){
        Map<String,List<OrderItem>> osiListMap = getOsiListMap();
        return osiListMap.get(orderSubNo);
    }
    /**
     * 根据子单号从上下文中得到所有虚拟行
     * @param orderSubNo
     * @return
     */
    public List<OrderItemVirtual> getOrderItemsVirtualByOrderSubNo(String orderSubNo){
        Map<String,List<OrderItemVirtual>> osivListMap = getOivListMap();
        return osivListMap.get(orderSubNo);
    }

    public OrderMain getOrderMainByOrderNo(String orderNo) {
        return omListMap.get(orderNo);
    }


    public Map<String, List<OrderCombineRelation>> getCombineItems() {
        if(combineItems == null){
            combineItems = new HashMap<String, List<OrderCombineRelation>>();
        }
        return combineItems;
    }

    public List<OrderCombineRelation> getAllCombineItems(){
        Map<String, List<OrderCombineRelation>> map = getCombineItems();
        List<OrderCombineRelation> ocList = new ArrayList<OrderCombineRelation>();
        for(List<OrderCombineRelation> mapOIList:map.values()){
            ocList.addAll(mapOIList);
        }
        return ocList;
    }


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


    public OrderItemSnapShotDTO getSnapShot() {
        return snapShot;
    }


    public void setSnapShot(OrderItemSnapShotDTO snapShot) {
        if (snapShot == null) {
            this.snapShot = snapShot;
        }
    }


    public String getBatchNum() {
        return batchNum;
    }


    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }


    public boolean isTotalPaid() {
        return isTotalPaid;
    }


    public void setTotalPaid(boolean isTotalPaid) {
        this.isTotalPaid = isTotalPaid;
    }
    
    
    
}
