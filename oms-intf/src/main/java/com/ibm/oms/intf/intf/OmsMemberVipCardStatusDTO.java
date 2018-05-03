package com.ibm.oms.intf.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ibm.sc.enums.Displayable;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public enum OmsMemberVipCardStatusDTO implements Displayable {
    normal("正常"), pastdue(" 过期"), freeze("冻结");
    private String display;

    public String display() {
        return display;
    }

    private OmsMemberVipCardStatusDTO(String display) {
        this.display = display;
    }
}
