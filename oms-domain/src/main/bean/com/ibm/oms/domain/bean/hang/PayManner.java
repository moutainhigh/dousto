package com.ibm.oms.domain.bean.hang;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 订单支付方式
 * @create: 2018-03-20 14:31
 **/
public class PayManner implements Serializable {
    private static final long serialVersionUID = 1L;
    //付款方式代号 使用一个节点，多行付款记录
    private String payCode;
    //付款方式金额
    private String paymoney;

    public PayManner() {
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }
}
