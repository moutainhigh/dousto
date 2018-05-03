package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong 主订单
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderMainCreateClientDTO extends OrderMainExtCreateClientDTO implements Serializable {
    // @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    // @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    // @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    // @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    // @Size(max, min) 被注释的元素的大小必须在指定的范围内
    // @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
    // @Past 被注释的元素必须是一个过去的日期
    // @Future 被注释的元素必须是一个将来的日期
    // @Pattern(value) 被注释的元素必须符合指定的正则表达式
    // @NotNull/@Null 被注释的元素必须是非空或空
    // @Email 被注释的元素必须是电子邮箱地址
    // @Length 被注释的字符串的大小必须在指定的范围内
    // @NotEmpty 被注释的字符串的必须非空
    // @Range 被注释的元素必须在合适的范围内

    /**
	 * 
	 */
	private static final long serialVersionUID = -3792059010208531335L;

	/** 订单类型 **/
    String orderType;

    /** 订单来源系统 **/
    String orderSource;

    /** 渠道订单号 **/
    String sourceOrderNo; // 渠道订单号(外部订单通过B2C对接到中台)

    /** 外部订单号 **/
    String aliasOrderNo;

    /** 商家类型 **/
    String merchantType;

    /** 商家编号 **/
    String merchantNo;

    /** 收货单是否显示价格 **/
    String ifShowPrice;

    /** 送货前是否电话确认 **/
    String needConfirm;

    /** 送货时间 **/
    String deliveryTimeFlag;

    /** 送货日期 **/
    String deliveryDateFlag;

    /** 订单产生时间 dateTime formatted as yyyy-MM-dd HH:mm:ss **/
    String orderTime;

    /** 商品重量 **/
    String weight;

    /** 商品总价,折前 **/
    String totalProductPrice;

    /** 总价折扣优惠总金额，订单折扣优惠 **/
    String discountTotal;

    /** 订单用券金额 **/
    String totalPromo;

    /** 支付金额的汇总 **/
    String totalPayAmount;

    /** 赠送总积分 **/
    String totalGivePoints;

    /** 是否货到付款 **/
    String ifPayOnArrival;

    /** 收货区域ID编码 **/
    String receiveAreaId;

    /** 运费总额 | 物流费用 **/
    String transportFee;

    /** 运费优惠 **/
    String discountTransport;

    /** 备注 **/
    String clientRemark;

    /** 会员当前对应rowid **/
    String memberNo;
    /** 会员当前对应rowid **/
    String memberVipCardLevel;

    /** 会员姓名 **/
    String customerName;

    /** 会员手机号码 **/
    String customerPhone;

    String customerEmail;
    String ip;
    /** 是否需要发票 **/
    String needInvoice;
    //商品总数
    Integer totalProductCount;
	/** 子订单 **/
    // @NotNull(message = "osDTOs is compulsory")
    // @NotBlank(message = "osDTOs is compulsory")
    List<OrderSubCreateClientDTO> osDTOs;

    /** 订单级促销 **/
    List<OrderPromotionCreateClientDTO> opDTOs;

    /** 订单支付 **/
    List<OrderPayCreateClientDTO> orderPayDTOs;
    /** 区域code **/
    private String zoneId;
    	
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    // public Long getAliasOrderNoAsLong() {
    // return NumberUtils.isNumber(aliasOrderNo)?null:Long.parseLong(aliasOrderNo);
    // }
    public String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    // public Long getIfShowPriceAsLong() {
    // return NumberUtils.isNumber(ifShowPrice)?null:Long.parseLong(ifShowPrice);
    // }
    public String getIfShowPrice() {
        return ifShowPrice;
    }

    public void setIfShowPrice(String ifShowPrice) {
        this.ifShowPrice = ifShowPrice;
    }

    // public Long getNeedConfirmAsLong() {
    // return NumberUtils.isNumber(needConfirm)?null:Long.parseLong(needConfirm);
    // }
    public String getNeedConfirm() {
        return needConfirm;
    }

    public void setNeedConfirm(String needConfirm) {
        this.needConfirm = needConfirm;
    }

    public String getDeliveryTimeFlag() {
        return deliveryTimeFlag;
    }

    public void setDeliveryTimeFlag(String deliveryTimeFlag) {
        this.deliveryTimeFlag = deliveryTimeFlag;
    }

    public String getDeliveryDateFlag() {
        return deliveryDateFlag;
    }

    public void setDeliveryDateFlag(String deliveryDateFlag) {
        this.deliveryDateFlag = deliveryDateFlag;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    // public BigDecimal getWeightAsBigDecimal() {
    // return NumberUtils.isNumber(weight)?null:new BigDecimal(weight);
    // }
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    // public BigDecimal getTotalProductPriceAsBigDecimal() {
    // return NumberUtils.isNumber(totalProductPrice)?null:new BigDecimal(totalProductPrice);
    // }
    public String getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(String totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    // public BigDecimal getDiscountTotalAsBigDecimal() {
    // return NumberUtils.isNumber(discountTotal)?null:new BigDecimal(discountTotal);
    // }

    public String getTotalPromo() {
        return totalPromo;
    }

    public String getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
    }

    public void setTotalPromo(String totalPromo) {
        this.totalPromo = totalPromo;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getTotalGivePoints() {
        return totalGivePoints;
    }

    public void setTotalGivePoints(String totalGivePoints) {
        this.totalGivePoints = totalGivePoints;
    }

    public String getIfPayOnArrival() {
        return ifPayOnArrival;
    }

    public void setIfPayOnArrival(String ifPayOnArrival) {
        this.ifPayOnArrival = ifPayOnArrival;
    }

    public String getReceiveAreaId() {
        return receiveAreaId;
    }

    public void setReceiveAreaId(String receiveAreaId) {
        this.receiveAreaId = receiveAreaId;
    }

    // public BigDecimal getTransportFeeAsLong() {
    // return NumberUtils.isNumber(transportFee)?null:new BigDecimal(transportFee);
    // }

    public String getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(String transportFee) {
        this.transportFee = transportFee;
    }

    // public BigDecimal getDiscountTransportAsBigDecimal() {
    // return NumberUtils.isNumber(discountTransport)?null:new BigDecimal(discountTransport);
    // }
    public String getDiscountTransport() {
        return discountTransport;
    }

    public void setDiscountTransport(String discountTransport) {
        this.discountTransport = discountTransport;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(String needInvoice) {
        this.needInvoice = needInvoice;
    }

    public List<OrderSubCreateClientDTO> getOsDTOs() {
        return osDTOs;
    }

    public void setOsDTOs(List<OrderSubCreateClientDTO> osDTOs) {
        this.osDTOs = osDTOs;
    }

    // public List<OrderItemGiftInfoDTO> getOigiDTOs() {
    // return oigiDTOs;
    // }
    //
    // public void setOigiDTOs(List<OrderItemGiftInfoDTO> oigiDTOs) {
    // this.oigiDTOs = oigiDTOs;
    // }

    public List<OrderPromotionCreateClientDTO> getOpDTOs() {
        return opDTOs;
    }

    public void setOpDTOs(List<OrderPromotionCreateClientDTO> opDTOs) {
        this.opDTOs = opDTOs;
    }

  

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClientRemark() {
        return clientRemark;
    }

    public void setClientRemark(String clientRemark) {
        this.clientRemark = clientRemark;
    }

    public String getMemberVipCardLevel() {
        return memberVipCardLevel;
    }

    public void setMemberVipCardLevel(String memberVipCardLevel) {
        this.memberVipCardLevel = memberVipCardLevel;
    }

    public String getSourceOrderNo() {
        return sourceOrderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo;
    }
    public Integer getTotalProductCount() {
 		return totalProductCount;
 	}

 	public void setTotalProductCount(Integer totalProductCount) {
 		this.totalProductCount = totalProductCount;
 	}

	public List<OrderPayCreateClientDTO> getOrderPayDTOs() {
		return orderPayDTOs;
	}

	public void setOrderPayDTOs(List<OrderPayCreateClientDTO> orderPayDTOs) {
		this.orderPayDTOs = orderPayDTOs;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
    
}
