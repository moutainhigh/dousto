package com.ibm.oms.intf.intf;

import java.io.Serializable;

/**
 * @author pjsong
 * 
 */
public class OmsMemberAuditOutputDTO implements Serializable {
    String code;
    String message;
    String memberId;
    Boolean blackListIncluded;
    Boolean priviledgedMember;
    String blackListedType;
    String type;
    String value;
    String success;
    Boolean isPriorityOrder;
	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Boolean getBlackListIncluded() {
        return blackListIncluded;
    }

    public void setBlackListIncluded(Boolean blackListIncluded) {
        this.blackListIncluded = blackListIncluded;
    }

    public Boolean getPriviledgedMember() {
        return priviledgedMember;
    }

    public void setPriviledgedMember(Boolean priviledgedMember) {
        this.priviledgedMember = priviledgedMember;
    }

    public String getBlackListedType() {
		return blackListedType;
	}

	public void setBlackListedType(String blackListedType) {
		this.blackListedType = blackListedType;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Boolean getIsPriorityOrder() {
        return isPriorityOrder;
    }

    public void setIsPriorityOrder(Boolean isPriorityOrder) {
        this.isPriorityOrder = isPriorityOrder;
    }
    
}
