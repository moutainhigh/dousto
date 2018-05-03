package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 微店订单信息
 * 
 * 2014-8-12 上午11:30:03
 */
@JsonIgnoreProperties(ignoreUnknown=true) 
public class WdOrderDTO implements Serializable {
    /**
     * app子订单ID
     */
    private Long id;
    /**
     * app主订单ID（对应主订单的id）
     */
    private Long orderId;
    /**
     * 子订单编号
     */
    private String orderListNo;
    /**
     * 部门编号，关联th_bu.id
     */
    private Long buId;
    /**
     * 专柜id
     */
    private Long buShoppeId;
    /**
     * 门店名称
     */
    private String buName;
    /**
     * order_net或者r3中对应的id；中台的主订单ID
     */
    private Long doOrderId;
    /**
     * 返回的id；中台的子订单ID
     */
    private String returnOrderId;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 运费
     */
    private BigDecimal logisticsAmount;
    /**
     * 商品总金额
     */
    private BigDecimal goodsAmount;
    /**
     * 用户可获取多少积分，（只有r3的才有）；中台消费所得积分推送
     */
    private BigDecimal orderIntegral;
    /**
     * 1,实体店2，网上天虹
     */
    private Long orderType;
    /**
     * 支付状态。0未支付 1已经支付
     */
    private Long payStatus;
    /**
     * 支付成功时间
     */
    private Date payTime;
    /**
     * 是否通知对方0未通知，1已经通知
     */
    private Long isNotice;
    /**
     * 通知时间；具体的通知时间
     */
    private Date noticeTime;
    /**
     * 物流状态10已经支付等待发货，50已经出库或者已经备货等待签收，100用户已经签收
     */
    private Long logisticsStatus;
    /**
     * 配送方式：网上天虹/到店自提；   编码
     */
    private String logisticsMode;
    /**
     * 承运人
     */
    private String logisticsCharge;
    /**
     * 物流信息   物流日志
     */
    private String logisticsInfo;
    /**
     * 货运单号
     */
    private String logisticsNumber;
    /**
     * 订单出库时间
     */
    private Date outboundTime;
    /**
     * 自提时间
     */
    private Date pickUpTime;
    /**
     * 订单完成时间
     */
    private Date completeTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 商品类型：0百货商品，1超市商品(中台没有字段存放)
     */
    private Long goodsStyle;
    /**
     * 备注
     */
    private String remark;
    /**
     * 退货状态1已经退货
     */
    private Long cancelStatus;
    private Date cancelTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单的加密key。要求唯一
     */
    private String orderKey;
    /**
     * 子订单积分是否已经成功记录 成功后状态改为1
     */
    private Long scoreStatus;
    /**
     * 是否已开发票，0表示没开\\n\\n，1表示已开发票
     */
    private Long isInvoice;
    /**
     * 开发票备注
     */
    private String invoiceRemark;
    /**
     * 0 配送上门   1到店提货
     */
    private Long isPeisong;
    /**
     * 快递名称
     */
    private String expresName;
    /**
     * 快递单号
     */
    private String expresNo;
    /**
     * 结果信息
     */
    private String message;
    /**
     * 结果是否成功标示 成功:1 失败:0
     */
    private String successFlag;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderListNo() {
        return orderListNo;
    }
    public void setOrderListNo(String orderListNo) {
        this.orderListNo = orderListNo;
    }
    public Long getBuId() {
        return buId;
    }
    public void setBuId(Long buId) {
        this.buId = buId;
    }
    public Long getBuShoppeId() {
        return buShoppeId;
    }
    public void setBuShoppeId(Long buShoppeId) {
        this.buShoppeId = buShoppeId;
    }
    public String getBuName() {
        return buName;
    }
    public void setBuName(String buName) {
        this.buName = buName;
    }
    public Long getDoOrderId() {
        return doOrderId;
    }
    public void setDoOrderId(Long doOrderId) {
        this.doOrderId = doOrderId;
    }
    public String getReturnOrderId() {
        return returnOrderId;
    }
    public void setReturnOrderId(String returnOrderId) {
        this.returnOrderId = returnOrderId;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BigDecimal getLogisticsAmount() {
        return logisticsAmount;
    }
    public void setLogisticsAmount(BigDecimal logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }
    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }
    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }
    public BigDecimal getOrderIntegral() {
        return orderIntegral;
    }
    public void setOrderIntegral(BigDecimal orderIntegral) {
        this.orderIntegral = orderIntegral;
    }
    public Long getOrderType() {
        return orderType;
    }
    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }
    public Long getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    public Long getIsNotice() {
        return isNotice;
    }
    public void setIsNotice(Long isNotice) {
        this.isNotice = isNotice;
    }
    public Date getNoticeTime() {
        return noticeTime;
    }
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }
    public Long getLogisticsStatus() {
        return logisticsStatus;
    }
    public void setLogisticsStatus(Long logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }
    public String getLogisticsMode() {
        return logisticsMode;
    }
    public void setLogisticsMode(String logisticsMode) {
        this.logisticsMode = logisticsMode;
    }
    public String getLogisticsCharge() {
        return logisticsCharge;
    }
    public void setLogisticsCharge(String logisticsCharge) {
        this.logisticsCharge = logisticsCharge;
    }
    public String getLogisticsInfo() {
        return logisticsInfo;
    }
    public void setLogisticsInfo(String logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }
    public String getLogisticsNumber() {
        return logisticsNumber;
    }
    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }
    public Date getOutboundTime() {
        return outboundTime;
    }
    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }
    public Date getPickUpTime() {
        return pickUpTime;
    }
    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
    public Date getCompleteTime() {
        return completeTime;
    }
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Long getGoodsStyle() {
        return goodsStyle;
    }
    public void setGoodsStyle(Long goodsStyle) {
        this.goodsStyle = goodsStyle;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getCancelStatus() {
        return cancelStatus;
    }
    public void setCancelStatus(Long cancelStatus) {
        this.cancelStatus = cancelStatus;
    }
    public Date getCancelTime() {
        return cancelTime;
    }
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getOrderKey() {
        return orderKey;
    }
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }
    public Long getScoreStatus() {
        return scoreStatus;
    }
    public void setScoreStatus(Long scoreStatus) {
        this.scoreStatus = scoreStatus;
    }
    public Long getIsInvoice() {
        return isInvoice;
    }
    public void setIsInvoice(Long isInvoice) {
        this.isInvoice = isInvoice;
    }
    public String getInvoiceRemark() {
        return invoiceRemark;
    }
    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }
    public Long getIsPeisong() {
        return isPeisong;
    }
    public void setIsPeisong(Long isPeisong) {
        this.isPeisong = isPeisong;
    }
    public String getExpresName() {
        return expresName;
    }
    public void setExpresName(String expresName) {
        this.expresName = expresName;
    }
    public String getExpresNo() {
        return expresNo;
    }
    public void setExpresNo(String expresNo) {
        this.expresNo = expresNo;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSuccessFlag() {
        return successFlag;
    }
    public void setSuccessFlag(String successFlag) {
        this.successFlag = successFlag;
    }
}