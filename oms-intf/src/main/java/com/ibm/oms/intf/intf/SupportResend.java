package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * This class is used to represent available MERCHANTS_BANK in the database.</p>
 * 
 * 
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SupportResend implements Serializable {
    private static final long serialVersionUID = 1L;


    private String type;// 类型（email or sms）

    private String code;// 模板的code

    Map<String,Object> data=new HashMap<String,Object>();//发送消息所用的参数集合

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    
}
