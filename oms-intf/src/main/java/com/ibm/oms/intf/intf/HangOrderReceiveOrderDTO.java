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
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;


/**
 * @author pjsong
 * POS的订单保存到挂单
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class HangOrderReceiveOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**下单批次号**/
    String batchNum;
    @NotNull(message = "HangOrderMainDTO List is null")
    @NotEmpty(message="At least one HangOrderMain is required")
    @Valid
    List<HangOrderMainDTO> omDTO;
    
    private String succeed;
    private String message;
    
    
   
	public String getSucceed() {
		return succeed;
	}
	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getBatchNum() {
        return batchNum;
    }
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
	public List<HangOrderMainDTO> getOmDTO() {
		return omDTO;
	}
	public void setOmDTO(List<HangOrderMainDTO> omDTO) {
		this.omDTO = omDTO;
	}
    
}
