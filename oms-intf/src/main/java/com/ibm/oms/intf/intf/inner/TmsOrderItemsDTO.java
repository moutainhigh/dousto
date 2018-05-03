package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.ibm.oms.intf.intf.inner.OrderItemTms;
/**
 * 订单信息
 *
 * 2014-4-28 上午11:58:03
 */
public class TmsOrderItemsDTO implements Serializable{
    private ArrayList<OrderItemTms> item;

    public ArrayList<OrderItemTms> getItem() {
        return item;
    }

    public void setItem(ArrayList<OrderItemTms> items) {
        this.item = items;
    }

}
