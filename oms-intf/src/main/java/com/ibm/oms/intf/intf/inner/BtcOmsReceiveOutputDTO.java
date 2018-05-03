package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author pjsong
 * 
 */
public class BtcOmsReceiveOutputDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String idOrder;
    String orderNo;
    String btcOrderNo;
    /**key:btcSubNo, value:omsId, omsNo**/
    HashMap<String, BtcOmsReceiveDetailDTO> subMap = new HashMap<String, BtcOmsReceiveDetailDTO>();
    /**key:btcItemNo, value:omsId, omsNo**/
    HashMap<String, BtcOmsReceiveDetailDTO> itemMap = new HashMap<String, BtcOmsReceiveDetailDTO>();
    /**key:btcItemNo, value:omsId, omsNo**/
    HashMap<String, BtcOmsReceiveDetailDTO> itemVirtualMap = new HashMap<String, BtcOmsReceiveDetailDTO>();

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

    public HashMap<String, BtcOmsReceiveDetailDTO> getSubMap() {
        if(subMap == null){
            subMap = new HashMap<String, BtcOmsReceiveDetailDTO>();
        }
        return subMap;
    }


    public HashMap<String, BtcOmsReceiveDetailDTO> getItemMap() {
        if(itemMap == null){
            itemMap = new HashMap<String, BtcOmsReceiveDetailDTO>();
        }
        return itemMap;
    }

    public HashMap<String, BtcOmsReceiveDetailDTO> getItemVirtualMap() {
        return itemVirtualMap;
    }

    public void setItemVirtualMap(HashMap<String, BtcOmsReceiveDetailDTO> itemVirtualMap) {
        this.itemVirtualMap = itemVirtualMap;
    }
    
    
    
}
