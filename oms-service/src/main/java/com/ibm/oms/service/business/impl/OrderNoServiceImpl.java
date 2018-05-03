package com.ibm.oms.service.business.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ibm.oms.service.business.OrderNoService;

/**
 * @author pjsong
 * 
 */
@Service("orderNoService")
public class OrderNoServiceImpl implements OrderNoService {

    @Override
    public String getOrderNoByItemNo(String orderItemNo) {
        if (orderItemNo == null){// || orderItemNo.length() != orderItemNoLength) {
            return null;
        }
        return orderItemNo.substring(0, orderItemNo.length() - 4);
    } 

    @Override
    public String getOrderNoBySubNo(String orderSubNo) {
        if (orderSubNo == null || (orderSubNo.length() <2)){
            //!= orderSubNoLength && orderSubNo.length() != orderSubNoLengthHistory)) {
            return null;
        }
        return orderSubNo.substring(0, orderSubNo.length() -2);
    }

    @Override
    public String getOrderNoByOrderId(String orderId) {
        if (orderId != null) {
            String ymd = getMonth() + new SimpleDateFormat("dd").format(System.currentTimeMillis());
            if (orderId.length() < orderIdAddOn.length()) {
                return ymd + (orderIdAddOn + orderId).substring(orderId.length());
            } else {
                return ymd + orderId.substring(orderId.length() - orderIdAddOn.length());
            }
        }
        return null;
    }

    @Override
    public String getOrderSubNoByItemNo(String orderItemNo) {
        if (orderItemNo == null){
            return null;
        }
        return orderItemNo.substring(0, orderItemNo.length() -2 );
    }

    @Override
    public String getOrderSubNoByOrderNo(String orderNo, int index) {
        if (StringUtils.isNotEmpty(orderNo)) {
            String indexStr = String.valueOf(index);
            return orderNo + ("00" + index).substring(indexStr.length());// 获取最后两位
        }
        return null;
    }

    @Override
    public String getOrderItemNoBySubOrderNo(String subOrderNo, int index) {
        if (StringUtils.isNotEmpty(subOrderNo)) {
            String indexStr = String.valueOf(index);
            return subOrderNo + ("00" + index).substring(indexStr.length());// 获取最后两位
        }
        return null;
    }

    @Override
    public String getDefaultOrderSubNoByOrderNo(String orderNo) {
        return orderNo + "01";
    }
    
    /**从7月标注10开始，逐月+1**/
    private int getMonth(){
        Calendar currTime = Calendar.getInstance();
// for test purpose        currTime.set(2017, 3, 1);
        Calendar baseTime = Calendar.getInstance();
        baseTime.set(2014, 6, 1);
        return (currTime.get(Calendar.YEAR) - baseTime.get(Calendar.YEAR)) * 12 + currTime.get(Calendar.MONTH) - baseTime.get(Calendar.MONTH) + 10;
    }
}
