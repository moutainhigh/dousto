package com.ibm.oms.intf.intf;

import java.io.Serializable;
/**订单取消时，如果有使用购物券，返给人家**/
public class CouponReturnDTO implements Serializable {
    String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    
    
}
