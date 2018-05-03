package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

/**
 * @author xiaohl 色码款换货的明细，比如黄色换蓝色
 */
public class ExchangeOrderItemDTO implements Serializable {

    /** SKU_NO(物料编号) **/
    @Length(max = 32, message = "sku_no: length must be less than 32")
    String skuNo;

    /** 商品条形码 **/
    @Length(max = 18, message = "bar_code: length must be less than 18")
    String barCode;

    /** 商品编号 **/
    @Length(max = 18, message = "commodity_code: length must be less than 18")
    String commodityCode;

    /** 商品名称 **/
    @Length(max = 128, message = "commodity_name: length must be less than 128")
    String commodityName;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

}
