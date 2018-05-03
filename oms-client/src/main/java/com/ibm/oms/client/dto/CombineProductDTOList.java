package com.ibm.oms.client.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;



public class CombineProductDTOList implements Serializable {
    /** 处理成功标志 **/
    @Valid
    Map<String, List<CombineProductDTO>> dtoList;

    public Map<String, List<CombineProductDTO>> getDtoList() {
        return dtoList;
    }

    public void setDtoList(Map<String, List<CombineProductDTO>> dtoList) {
        this.dtoList = dtoList;
    }


    
    
}
