package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 王琦琛 on 2018/1/26.
 * 订单支付实体
 */
public class OrderPayClientDTO implements Serializable{
    private String orderNo;//订单号
    private String payOnArrivalPayType;//货到付款支付方式
    private String payCode;//支付方式编码
    private String payName;//支付方式名称
    private BigDecimal payAmount;//支付金额
    private String payTime;//支付时间
    private String bankTypeName;//银行卡名称
    private String bankTypeCode;//银行卡编码

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayOnArrivalPayType() {
        return payOnArrivalPayType;
    }

    public void setPayOnArrivalPayType(String payOnArrivalPayType) {
        this.payOnArrivalPayType = payOnArrivalPayType;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
    }

    public String getBankTypeCode() {
        return bankTypeCode;
    }

    public void setBankTypeCode(String bankTypeCode) {
        this.bankTypeCode = bankTypeCode;
    }

    @Override
    public String toString() {
        return "OrderPayDTO{" +
                "orderNo='" + orderNo + '\'' +
                ", payOnArrivalPayType='" + payOnArrivalPayType + '\'' +
                ", payCode='" + payCode + '\'' +
                ", payName='" + payName + '\'' +
                ", payAmount=" + payAmount +
                ", payTime='" + payTime + '\'' +
                ", bankTypeName='" + bankTypeName + '\'' +
                ", bankTypeCode='" + bankTypeCode + '\'' +
                '}';
    }
}
