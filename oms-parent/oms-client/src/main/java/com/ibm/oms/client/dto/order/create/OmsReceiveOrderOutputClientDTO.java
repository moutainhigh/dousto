package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pjsong
 * 
 */
public class OmsReceiveOrderOutputClientDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 处理成功标志 **/
    List<OmsReceiveOutputClientDTO> mapList;
    String succeed;
    String message;
    public List<OmsReceiveOutputClientDTO> getMapList() {
        if(mapList == null){
            mapList = new ArrayList<OmsReceiveOutputClientDTO>();
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
    
}
