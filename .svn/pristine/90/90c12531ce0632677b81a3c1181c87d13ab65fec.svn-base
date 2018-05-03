package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付信息
 *
 * 2014-4-25 下午03:35:50
 */
public class TmsPayDTOElement implements Serializable{
    private double money;//金额
    private int paymode;//支付方式，1现金支付，2银行卡支付，3天虹卡支付
    
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public int getPaymode() {
        return paymode;
    }
    public void setPaymode(int paymode) {
        this.paymode = paymode;
    }
    
}
