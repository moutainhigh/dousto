package com.ibm.oms.client.constant;

public enum InterfaceConst {
	COMMON_RESPONSE_SUCCESS("true", "成功"),
	COMMON_RESPONSE_FAIL("false", "失败"),
	COMMON_RESPONSE_FAIL_CODE("9999", "失败"),
	COMMON_RESPONSE_SUCCESS_CODE("200","成功"),
	
	COMMON_RECIEVE_SUCCESS("SUCCESS", "成功"),
	COMMON_RECIEVE_FAIL("FAILURE", "失败");
	
	
	private String code;
    private String desc;

    private InterfaceConst(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }
}
