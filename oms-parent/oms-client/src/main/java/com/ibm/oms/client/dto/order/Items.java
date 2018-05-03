package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: WMS->中台销售订单状态更新 行订单类(多个)
 * @create: 2018-03-12 16:07
 **/
public class Items implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Item> item;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
