package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ibm.oms.intf.intf.inner.CombineProductDTO;

/**
 * @author pjsong
 * 
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
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
