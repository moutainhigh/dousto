package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS->中台销售订单状态更新 行订单类(单个)
 * @create: 2018-03-12 16:08
 **/
public class Item  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skuCode;//商品编码
    private String normalQuantity;//数量
    private String defectiveQuantity;//数量预留字段
    public String getSkuCode() {
        return skuCode;
    }
    public String getNormalQuantity() {
        return normalQuantity;
    }
    public String getDefectiveQuantity() {
        return defectiveQuantity;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public void setNormalQuantity(String normalQuantity) {
        this.normalQuantity = normalQuantity;
    }
    public void setDefectiveQuantity(String defectiveQuantity) {
        this.defectiveQuantity = defectiveQuantity;
    }
}
