package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiaohl 外部订单明细
 */
public class RcOrderItemDTO implements Serializable {

    /** 原销售订单行号 **/
    @NotBlank(message = "order_item_no is compulsory")
    String refOrderItemNo;

    /** 退货数量 **/
    @NotBlank(message = "sale_num is compulsory")
    String saleNum;

    /** SKU_NO(物料编号) **/
    @NotBlank(message = "sku_no is compulsory")
    String skuNo;

    /** 退换货原因 **/
    @NotBlank(message = "reason is compulsory")
    String reason;

    /** 是否为色码款商品 **/
    @NotBlank(message = "product_property_flag is compulsory")
    String productPropertyFlag;

    ExchangeOrderItemDTO exchangeOrderItemDTO;// 色码款商品换货时，选择其他款码的信息

    public String getRefOrderItemNo() {
        return refOrderItemNo;
    }

    public void setRefOrderItemNo(String refOrderItemNo) {
        this.refOrderItemNo = refOrderItemNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getProductPropertyFlag() {
        return productPropertyFlag;
    }

    public void setProductPropertyFlag(String productPropertyFlag) {
        this.productPropertyFlag = productPropertyFlag;
    }

    public ExchangeOrderItemDTO getExchangeOrderItemDTO() {
        return exchangeOrderItemDTO;
    }

    public void setExchangeOrderItemDTO(ExchangeOrderItemDTO exchangeOrderItemDTO) {
        this.exchangeOrderItemDTO = exchangeOrderItemDTO;
    }

}
