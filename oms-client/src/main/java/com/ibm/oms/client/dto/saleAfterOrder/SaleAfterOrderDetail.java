package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.List;

/**
 * 售后详情，前端导购端使用
 */
public class SaleAfterOrderDetail implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195808724298655097L;
	
	//接口信息
	private MessageDto msgDto;

	//商品信息
	private List<SaleAfterProductInfo> dto;
	
	private String	type;//售后类型
	private String	title;//标题
	private String	serNo;//售后单号
	private String	orderNo;//订单编号
	private String	memberNo;//会员编号
	private String	buyDate;//购买时间
	private String	applyDate;//申请售后时间
	private String	handleResult;//处理情况
	private String	channel;//购买渠道
	private String handleType;//处理方式
	private String applyReason;//申请原因

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public List<SaleAfterProductInfo> getDto() {
		return dto;
	}

	public void setDto(List<SaleAfterProductInfo> dto) {
		this.dto = dto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
}