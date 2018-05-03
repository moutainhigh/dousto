package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohl
 * 
 */
public enum OrderDeliveryType {
    
    /**
     * 
     */
    RAINBOW_SEND("1", "天虹配送", "天虹配送");

    private final String code;
    private final String desc;
    private final String payName;

    private OrderDeliveryType(String code, String payName, String desc) {
        this.code = code;
        this.desc = desc;
        this.payName = payName;
    }

    public String getCode() {
        return this.code;
    }

    public String getPayName() {
        return this.payName;
    }

    public String getDesc() {
        return desc;
    }
    
    public static List<OrderDeliveryType>  getAll(){  
		List<OrderDeliveryType> allList = new ArrayList<OrderDeliveryType>();
		allList.add(null);
        for (OrderDeliveryType s : OrderDeliveryType.values()){  
            allList.add(s);
        }    
	    return allList;
	}  
}
