package com.ibm.oms.dao.constant;

/**
 * 
 * 订单积分操作的枚举类
 * 
 * @author wangchao
 *
 */
public enum PointOperator {
	POINT_DIRECTIONOPT_ADD("0", "增加积分"), 
	POINT_DIRECTIONOPT_SUB("1", "扣减积分"),
	POINT_TRANSACTION_TYPE_SALE_ADD("1","交易后增加的积分"), 
	POINT_TRANSACTION_TYPE_SALE_SUB("2", "交易减掉的积分"),
	POINT_TRANSACTION_TYPE_SALE_EXCHANGE("3", "积分兑换消耗的   ");

	private String code;
	private String message;

	private PointOperator(String code, String message) {
		this.code = code;
		this.message = message;
	}

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

}
