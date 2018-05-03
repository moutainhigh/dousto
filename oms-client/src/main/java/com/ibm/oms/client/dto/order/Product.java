package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口 )商品实体类为 (单个)
 * @create: 2018-03-12 14:32
 **/
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skuCode;//商品编码
    private String normalQuantity;//数量
    private String defectiveQuantity;//数量预留字段
    private String boxNo;//箱码
    private String logisticsProviderCode;//物流公司代码（暂时只支持传入不做处理以主单为准）
    private String shippingOrderNo;//运单号（暂时只支持传入不做处理以主单为准）
    private String weight;//称重重量（暂时只支持传入不做处理以主单为准）
    private String volume;//称重体积（暂时只支持传入不做处理以主单为准）

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getNormalQuantity() {
        return normalQuantity;
    }

    public void setNormalQuantity(String normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    public String getDefectiveQuantity() {
        return defectiveQuantity;
    }

    public void setDefectiveQuantity(String defectiveQuantity) {
        this.defectiveQuantity = defectiveQuantity;
    }

    public String getLogisticsProviderCode() {
        return logisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode) {
        this.logisticsProviderCode = logisticsProviderCode;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
