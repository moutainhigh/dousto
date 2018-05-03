package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;


/**
 * @author pjsong
 * B2C的订单传输到OMS
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties
public class BtcOmsReceiveOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**btc下单批次号**/
    //@NotEmpty(message="batchNum is required")
    String batchNum;
    @NotNull(message = "OrderMainDTO List is null")
    @NotEmpty(message="At least one OrderMain is required")
    @Valid
    List<OrderMainDTO> omDTO;
    public List<OrderMainDTO> getOmDTO() {
        return omDTO;
    }
    public void setOmDTO(List<OrderMainDTO> omDTO) {
        this.omDTO = omDTO;
    }
    public String getBatchNum() {
        return batchNum;
    }
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
    
    
    
}
