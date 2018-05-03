package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ibm.oms.intf.intf.inner.OmsMemberRefundInnerDTO;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OmsMemberRefundDTO implements Serializable {
    Long memberId;
    String orderNo;
    ArrayList<OmsMemberRefundInnerDTO> refundDTOList;
    String operator;

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public ArrayList<OmsMemberRefundInnerDTO> getRefundDTOList() {
        return refundDTOList;
    }

    public void setRefundDTOList(ArrayList<OmsMemberRefundInnerDTO> refundDTOList) {
        this.refundDTOList = refundDTOList;
    }
}
