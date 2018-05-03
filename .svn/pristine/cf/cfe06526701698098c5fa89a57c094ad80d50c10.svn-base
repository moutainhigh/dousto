package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author pjsong
 * 
 */
public class OmsReceiveOutputClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String idOrder;
    String orderNo;
    String btcOrderNo;
    /**key:btcSubNo, value:omsId, omsNo**/
    HashMap<String, OmsReceiveDetailClientDTO> subMap = new HashMap<String, OmsReceiveDetailClientDTO>();
    /**key:btcItemNo, value:omsId, omsNo**/
    HashMap<String, OmsReceiveDetailClientDTO> itemMap = new HashMap<String, OmsReceiveDetailClientDTO>();
    /**key:btcItemNo, value:omsId, omsNo**/
    HashMap<String, OmsReceiveDetailClientDTO> itemVirtualMap = new HashMap<String, OmsReceiveDetailClientDTO>();

    boolean isPayOnArrival;
    /** 处理成功标志 **/
    String code;
    String msg;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isPayOnArrival() {
        return isPayOnArrival;
    }

    public void setPayOnArrival(boolean isPayOnArrival) {
        this.isPayOnArrival = isPayOnArrival;
    }

    public String getBtcOrderNo() {
        return btcOrderNo;
    }

    public void setBtcOrderNo(String btcOrderNo) {
        this.btcOrderNo = btcOrderNo;
    }

    public HashMap<String, OmsReceiveDetailClientDTO> getSubMap() {
        if(subMap == null){
            subMap = new HashMap<String, OmsReceiveDetailClientDTO>();
        }
        return subMap;
    }


    public HashMap<String, OmsReceiveDetailClientDTO> getItemMap() {
        if(itemMap == null){
            itemMap = new HashMap<String, OmsReceiveDetailClientDTO>();
        }
        return itemMap;
    }

    public HashMap<String, OmsReceiveDetailClientDTO> getItemVirtualMap() {
        return itemVirtualMap;
    }

    public void setItemVirtualMap(HashMap<String, OmsReceiveDetailClientDTO> itemVirtualMap) {
        this.itemVirtualMap = itemVirtualMap;
    }
    
    
    
}
