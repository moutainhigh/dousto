package com.ibm.oms.client.dto.saleAfterOrder;

/**
 * 接口处理信息
 */
public class MessageDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195606702961529097L;

	private String success = "1";//0：成功       1：失败

	private String msg = "";
	
	private String orderNo;//订单号

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSuccess() {
		return success;
	}
}