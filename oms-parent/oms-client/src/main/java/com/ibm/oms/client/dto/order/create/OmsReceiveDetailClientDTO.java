package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;

public class OmsReceiveDetailClientDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String omsNo;
    String omsId;
    
    
    public OmsReceiveDetailClientDTO() {
        super();
    }
    public OmsReceiveDetailClientDTO(String omsNo, String omsId) {
        super();
        this.omsNo = omsNo;
        this.omsId = omsId;
    }
    public String getOmsNo() {
        return omsNo;
    }
    public void setOmsNo(String omsNo) {
        this.omsNo = omsNo;
    }
    public String getOmsId() {
        return omsId;
    }
    public void setOmsId(String omsId) {
        this.omsId = omsId;
    }
    
}
