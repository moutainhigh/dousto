package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.List;


/**
 * @author pjsong
 * B2C的订单传输到OMS
 */
public class ReceiveOrderClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String batchNum;
    List<OrderMainCreateClientDTO> omDTO;
    public List<OrderMainCreateClientDTO> getOmDTO() {
        return omDTO;
    }
    public void setOmDTO(List<OrderMainCreateClientDTO> omDTO) {
        this.omDTO = omDTO;
    }
    public String getBatchNum() {
        return batchNum;
    }
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
    
    
    
}
