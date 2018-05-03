package com.ibm.oms.client.dto.saleAfterOrder;

/**
 * 退换货信息，前端使用
 */
public class SaleAfterOrderInfoDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195808724298654097L;
	
	//接口信息
	private MessageDto msgDto;

    private String returnNo;//退货单号
    
    private String orderStatusTime;//退换货状态变化时间
    private String orderStatusDesc;//退换货状态变化描述
    
    private String operatorNo;//处理人编号
    private String operatorNm;//处理人名称
    private String operatorRole;//处理人角色

    private String explain;//注意事项
    private String refundLogList;//退款信息
    private String refundMsg;//退款信息提示
    private String returnType;//退货方式选项
    private String refundStatus;//退款状态
    private String refundReason;//退款原因
    private String refundAmount;//退款金额
    private String deductionPoint;//扣除积分
    private String coupion;//优惠券
    //private String deductionPointDesc;//扣除积分描述
    private Long applyCount;//申请件数
    private String applyDate;//申请时间
    private String refundNo;//退款编号
	public String getReturnNo() {
		return returnNo;
	}
	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}
	public String getOrderStatusTime() {
		return orderStatusTime;
	}
	public void setOrderStatusTime(String orderStatusTime) {
		this.orderStatusTime = orderStatusTime;
	}
	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getRefundLogList() {
		return refundLogList;
	}
	public void setRefundLogList(String refundLogList) {
		this.refundLogList = refundLogList;
	}
	public String getRefundMsg() {
		return refundMsg;
	}
	public void setRefundMsg(String refundMsg) {
		this.refundMsg = refundMsg;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getDeductionPoint() {
		return deductionPoint;
	}
	public void setDeductionPoint(String deductionPoint) {
		this.deductionPoint = deductionPoint;
	}
	public String getCoupion() {
		return coupion;
	}
	public void setCoupion(String coupion) {
		this.coupion = coupion;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public MessageDto getMsgDto() {
		return msgDto;
	}
	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}
	public Long getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(Long applyCount) {
		this.applyCount = applyCount;
	}
	public String getOperatorNo() {
		return operatorNo;
	}
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	public String getOperatorNm() {
		return operatorNm;
	}
	public void setOperatorNm(String operatorNm) {
		this.operatorNm = operatorNm;
	}
	public String getOperatorRole() {
		return operatorRole;
	}
	public void setOperatorRole(String operatorRole) {
		this.operatorRole = operatorRole;
	}
}