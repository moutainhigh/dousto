package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;

public class BtcOmsReceiveDetailDTO implements Serializable{
    String omsNo;
    String omsId;
    
    
    public BtcOmsReceiveDetailDTO() {
        super();
    }
    public BtcOmsReceiveDetailDTO(String omsNo, String omsId) {
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
