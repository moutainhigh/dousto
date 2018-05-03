package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口 )商品实体类为 (所有)
 * @create: 2018-03-12 14:29
 **/
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
