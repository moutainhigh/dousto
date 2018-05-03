package com.ibm.oms.service.intf;

import java.math.BigDecimal;

import com.ibm.oms.domain.persist.OrderMain;

/**
 * @author xiaohl B2C的售后意向单传输到OMS处理上下文
 */
public class ContextBtcOmsRetChgItemDTO {

    /** RcOrderItemDTO **/
    String srcItemNo;// oms原明细no

    BigDecimal payAmount;
    BigDecimal weight;
    Long refOrderItemId; // oms原明细行id
    Long retChangeId; // 退换货的明细关联的记录id
    String productCategory; //商品分类code
    String brand; 
    String productCategoryName;
    String brandName;

    public String getSrcItemNo() {
        return srcItemNo;
    }

    public void setSrcItemNo(String srcItemNo) {
        this.srcItemNo = srcItemNo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getRefOrderItemId() {
        return refOrderItemId;
    }

    public void setRefOrderItemId(Long refOrderItemId) {
        this.refOrderItemId = refOrderItemId;
    }

    public Long getRetChangeId() {
        return retChangeId;
    }

    public void setRetChangeId(Long retChangeId) {
        this.retChangeId = retChangeId;
    }

}
