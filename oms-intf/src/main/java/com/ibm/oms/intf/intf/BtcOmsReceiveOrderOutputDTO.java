package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;

/**
 * @author pjsong
 * 
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class BtcOmsReceiveOrderOutputDTO implements Serializable {
    /** 处理成功标志 **/
    private List<BtcOmsReceiveOutputDTO> mapList;
    String succeed;
    String message;
    public List<BtcOmsReceiveOutputDTO> getMapList() {
        if(mapList == null){
            mapList = new ArrayList<BtcOmsReceiveOutputDTO>();
        }
        return mapList;
    }
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
	/**
	 * @param mapList the mapList to set
	 */
	public void setMapList(List<BtcOmsReceiveOutputDTO> mapList) {
		this.mapList = mapList;
	}
    
}
