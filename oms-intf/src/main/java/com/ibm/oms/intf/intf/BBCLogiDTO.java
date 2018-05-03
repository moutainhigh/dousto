package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author pjsong
 * 
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BBCLogiDTO implements Serializable {
    /** 处理成功标志 **/
    @NotBlank(message = "orderSubNo is compulsory")
    String orderSubNo;
    @NotBlank(message = "logisticsOutNo is compulsory")
    String logisticsOutNo;
    @NotBlank(message = "deliveryMerchantName is compulsory")
    String deliveryMerchantName;
    public String getOrderSubNo() {
        return orderSubNo;
    }
    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }
    public String getLogisticsOutNo() {
        return logisticsOutNo;
    }
    public void setLogisticsOutNo(String logisticsOutNo) {
        this.logisticsOutNo = logisticsOutNo;
    }
    public String getDeliveryMerchantName() {
        return deliveryMerchantName;
    }
    public void setDeliveryMerchantName(String deliveryMerchantName) {
        this.deliveryMerchantName = deliveryMerchantName;
    }
    
}
