package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 奇门传入商品信息
 * @create: 2018-04-09 15:41
 **/
public class QmOrderItemDTO  implements Serializable {
    private String itemName; //商品名称
    private Long singlePrice;//商品价格
    private Long itemCount;//商品数量

    public QmOrderItemDTO() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Long singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }
}
