package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 奇门订单行项目实体类
 * @create: 2018-04-26 15:30
 **/
public class OrderItemQmDTO implements Serializable {
    private String itemCode;// String 可选 IW02商品编码（上游系 有
    private String itemId;// String 可选 IW02商品ID 有
    private String itemName;// String 可选 衣服商品名称 有
    private Integer actualQty;// Number 可选 1000商品数量 有
    private String skuProperty;// String 可选 abc商品属性 无
    private String purchasePrice;// String 可选 20采购价
    private String retailPrice;// String 可选 20零售价
    private String amount;// String 必须 20金额 有
    private String styleCode;// String 必须 IW02款编号
    private String styleName;// String 可选 XX款名
    private String colorCode;// String 必须 1002颜色编号
    private String colorName;// String 可选 红色颜色名称
    private String sizeCode;// String 必须 1001尺寸编号
    private String sizeName;// String 可选 XX尺寸名称
    private String discount;// String 必须 1折扣 有
    private String stdprice;// String 必须 100标准价
    private String orderId;// String 可选 100000224121232111订
    private String extendProps;// String 可选 扩展信息扩展属性

    public OrderItemQmDTO() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getActualQty() {
        return actualQty;
    }

    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public String getSkuProperty() {
        return skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
        this.skuProperty = skuProperty;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStdprice() {
        return stdprice;
    }

    public void setStdprice(String stdprice) {
        this.stdprice = stdprice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtendProps() {
        return extendProps;
    }

    public void setExtendProps(String extendProps) {
        this.extendProps = extendProps;
    }
}
