package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ibm.oms.intf.intf.inner.TransportComp;

/**
 * 
 * I-oms-logistics-01 outputDTO
 * 
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LogisticsDTO implements Serializable {
    String status;
    String message;
    TransportComp transportComp;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public TransportComp getTransportComp() {
        return transportComp;
    }
    public void setTransportComp(TransportComp transportComp) {
        this.transportComp = transportComp;
    }
    
}
