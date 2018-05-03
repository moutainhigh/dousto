package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.List;

/**
 * 退货单列表信息
 */
public class ReturnHeaderOrderFromLasaDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2345606702987654097L;

	//接口信息
	private MessageDto msgDto;
	
    private String returnNo;//退货单号
    private String returnType;//退货类型
    private String returnReason;//退货原因
    private String returnMode;//退货方式
    private String returnStatus;//退货单状态
    private String returnStatusName;//退货单状态名称
    private String returnShopNo;//退货门店编号
    private String orderSalesNo;//下单营业员编号
    private String returnSalesNo;//退货受理导购员编号
    private String returnStartTime;//退货预约开始时间
    private String returnEndTime;//退货预约结束时间
    private String returnAddrProvince;//退货地址省
    private String returnAddrCity;//退货地址市
    private String returnAddrTown;//退货地址区
    private String returnAddr;//退货详细地址
    private String receiverName;//接收人姓名
    private String receiverPhone;//接收人电话
    private String memberNo;//会员代号
    private String totalReturnAmount;//退款金额合计
    private String memberAccountName;//会员账户姓名
    private String orderNo;//原订单号
    private String wmsID;//库存ID
    private String memberName;//会员名称
    private String submitDate;//退换货申请时间
    private String remark;//备注
    private String attatch;//附件
    private String totalDiscountAmount;//订单折扣总金额
    private String totalCouponsAmount;//订单优惠券总金额
    private String expressNo;//快递单号
    private String channelSource;//渠道来源
    
    private String isRefund;//是否退款
    private String bucklebackPoint;//扣回赠送的总积分
    private String returnPoint;//返还抵现的积分
    private String refundTypeCode;//退款方式编码
    private String refundTypeName;//退款方式名称
    private String receiverPost;//收货人邮编
    private String expressType;//快递类型

    private String refundType;//退款申请（0全部退款,1部分退款） 新
    
    private List<String> imgList;//上传凭证

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnShopNo() {
        return returnShopNo;
    }

    public void setReturnShopNo(String returnShopNo) {
        this.returnShopNo = returnShopNo;
    }

    public String getOrderSalesNo() {
        return orderSalesNo;
    }

    public void setOrderSalesNo(String orderSalesNo) {
        this.orderSalesNo = orderSalesNo;
    }

    public String getReturnSalesNo() {
        return returnSalesNo;
    }

    public void setReturnSalesNo(String returnSalesNo) {
        this.returnSalesNo = returnSalesNo;
    }

    public String getReturnStartTime() {
        return returnStartTime;
    }

    public void setReturnStartTime(String returnStartTime) {
        this.returnStartTime = returnStartTime;
    }

    public String getReturnEndTime() {
        return returnEndTime;
    }

    public void setReturnEndTime(String returnEndTime) {
        this.returnEndTime = returnEndTime;
    }

    public String getReturnAddrProvince() {
        return returnAddrProvince;
    }

    public void setReturnAddrProvince(String returnAddrProvince) {
        this.returnAddrProvince = returnAddrProvince;
    }

    public String getReturnAddrCity() {
        return returnAddrCity;
    }

    public void setReturnAddrCity(String returnAddrCity) {
        this.returnAddrCity = returnAddrCity;
    }

    public String getReturnAddrTown() {
        return returnAddrTown;
    }

    public void setReturnAddrTown(String returnAddrTown) {
        this.returnAddrTown = returnAddrTown;
    }

    public String getReturnAddr() {
        return returnAddr;
    }

    public void setReturnAddr(String returnAddr) {
        this.returnAddr = returnAddr;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getTotalReturnAmount() {
        return totalReturnAmount;
    }

    public void setTotalReturnAmount(String totalReturnAmount) {
        this.totalReturnAmount = totalReturnAmount;
    }

    public String getMemberAccountName() {
        return memberAccountName;
    }

    public void setMemberAccountName(String memberAccountName) {
        this.memberAccountName = memberAccountName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWmsID() {
        return wmsID;
    }

    public void setWmsID(String wmsID) {
        this.wmsID = wmsID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttatch() {
        return attatch;
    }

    public void setAttatch(String attatch) {
        this.attatch = attatch;
    }

    public String getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(String totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public String getTotalCouponsAmount() {
        return totalCouponsAmount;
    }

    public void setTotalCouponsAmount(String totalCouponsAmount) {
        this.totalCouponsAmount = totalCouponsAmount;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(String channelSource) {
        this.channelSource = channelSource;
    }

    @Override
    public String toString() {
        return "ReturnHeader{" +
                "returnNo='" + returnNo + '\'' +
                ", returnType='" + returnType + '\'' +
                ", returnReason='" + returnReason + '\'' +
                ", returnMode='" + returnMode + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", returnShopNo='" + returnShopNo + '\'' +
                ", orderSalesNo='" + orderSalesNo + '\'' +
                ", returnSalesNo='" + returnSalesNo + '\'' +
                ", returnStartTime='" + returnStartTime + '\'' +
                ", returnEndTime='" + returnEndTime + '\'' +
                ", returnAddrProvince='" + returnAddrProvince + '\'' +
                ", returnAddrCity='" + returnAddrCity + '\'' +
                ", returnAddrTown='" + returnAddrTown + '\'' +
                ", returnAddr='" + returnAddr + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", totalReturnAmount=" + totalReturnAmount +
                ", memberAccountName='" + memberAccountName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", wmsID='" + wmsID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", submitDate='" + submitDate + '\'' +
                ", remark='" + remark + '\'' +
                ", attatch='" + attatch + '\'' +
                ", totalDiscountAmount=" + totalDiscountAmount +
                ", totalCouponsAmount=" + totalCouponsAmount +
                ", expressNo='" + expressNo + '\'' +
                ", channelSource='" + channelSource + '\'' +               
                ", isRefund='" + isRefund + '\'' +
                ", bucklebackPoint='" + bucklebackPoint + '\'' +
                ", returnPoint='" + returnPoint + '\'' +
                ", refundTypeCode='" + refundTypeCode + '\'' +
                ", refundTypeName='" + refundTypeName + '\'' +
                ", receiverPost='" + receiverPost + '\'' +
                ", expressType='" + expressType + '\'' +
                ", refundType='" + refundType + '\'' +               
                '}';
    }

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public String getBucklebackPoint() {
		return bucklebackPoint;
	}

	public void setBucklebackPoint(String bucklebackPoint) {
		this.bucklebackPoint = bucklebackPoint;
	}

	public String getReturnPoint() {
		return returnPoint;
	}

	public void setReturnPoint(String returnPoint) {
		this.returnPoint = returnPoint;
	}

	public String getRefundTypeCode() {
		return refundTypeCode;
	}

	public void setRefundTypeCode(String refundTypeCode) {
		this.refundTypeCode = refundTypeCode;
	}

	public String getRefundTypeName() {
		return refundTypeName;
	}

	public void setRefundTypeName(String refundTypeName) {
		this.refundTypeName = refundTypeName;
	}

	public String getReceiverPost() {
		return receiverPost;
	}

	public void setReceiverPost(String receiverPost) {
		this.receiverPost = receiverPost;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public String getReturnStatusName() {
		return returnStatusName;
	}

	public void setReturnStatusName(String returnStatusName) {
		this.returnStatusName = returnStatusName;
	}
}