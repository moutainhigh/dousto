package com.ibm.oms.service.intf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.OrderMain;

/**
 * @author xiaohl B2C的售后意向单传输到OMS处理上下文
 */
public class ContextBtcOmsRetChgDTO {

    /** BtcOmsReturnChangeDTO ：主订单 **/
    Long orderId; // OMS新创建订单的ID
    String orderNo; // OMS新创建订单的no
    OrderMain omsSrcOrderMain; // OMS原订单

    /** RcOrderSubDTO：子订单 **/
    Long orderSubId; // OMS新创建子订单的ID
    String orderSubNo; // OMS新创建子订单的no
    BigDecimal totalReturnAmount; // 退款金额
    BigDecimal totalWeight; // 商品总重
    BigDecimal totalProductPrice; // 商品总金额

    /** RcOrderSubDTO：明细 **/
    Map<String, ContextBtcOmsRetChgItemDTO> contextItemMap;// 明细的扩展信息（key:srcItemNo,value:ContextBtcOmsRetChgItemDTO）

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderMain getOmsSrcOrderMain() {
        return omsSrcOrderMain;
    }

    public void setOmsSrcOrderMain(OrderMain omsSrcOrderMain) {
        this.omsSrcOrderMain = omsSrcOrderMain;
    }

    public Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public BigDecimal getTotalReturnAmount() {
        return totalReturnAmount;
    }

    public void setTotalReturnAmount(BigDecimal totalReturnAmount) {
        this.totalReturnAmount = totalReturnAmount;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public Map<String, ContextBtcOmsRetChgItemDTO> getContextItemMap() {
        return contextItemMap;
    }

    public void setContextItemMap(Map<String, ContextBtcOmsRetChgItemDTO> contextItemMap) {
        this.contextItemMap = contextItemMap;
    }

}
