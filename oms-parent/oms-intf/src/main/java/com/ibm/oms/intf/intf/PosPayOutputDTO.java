package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author pjsong
 * 
 */
@XmlType
@XmlRootElement(name = "CommonOutputDTO")  
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PosPayOutputDTO implements Serializable {
    /** 处理成功标志 **/
    String code;
    BigDecimal totalPayAmount;

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }
    
}
