package com.ibm.oms.intf.intf;

import java.io.Serializable;

/**
 * @author pjsong
 * 
 */
public class OmsMemberAuditDTO implements Serializable {
    String memberNo;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }


}
