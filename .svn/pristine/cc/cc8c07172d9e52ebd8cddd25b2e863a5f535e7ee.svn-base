package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @author pjsong 主订单
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderMainDTO extends OrderMainExtDTO implements Serializable {
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

    /** 订单类型 **/
    @NotBlank(message = "order_type is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String orderType;

    /** 订单来源系统 **/
    @NotBlank(message = "order_source is compulsory")
    @Length(max = 32, message = "order_source: length must be less than 32")
    //@Pattern(regexp = "(PC)|(APP)|(WAP)|(WGW)|(THXD)|(JD)")
    String orderSource;

    /** 渠道订单号 **/
    @Length(max = 32, message = "orderSourceNo: length must be less than 32")
    String sourceOrderNo; // 渠道订单号(外部订单通过B2C对接到中台)

    /** 外部订单号 **/
    @Length(max = 32, message = "alias_order_no: length must be less than 32")
    String aliasOrderNo;

    /** 商家类型 **/
    @NotBlank(message = "merchant_type is compulsory")
    @Length(max = 32, message = "merchant_type: length must be less than 32")
    String merchantType;

    /** 商家编号 **/
    //@NotBlank(message = "merchant_no is compulsory")
    @Length(max = 32, message = "merchant_no: length must be less than 32")
    String merchantNo;
    
    @Length(max = 32, message = "merchant_no: length must be less than 32")
    String shopNo;

    /** 收货单是否显示价格 **/
    String ifShowPrice;

    /** 送货前是否电话确认 **/
    String needConfirm;

    /** 送货时间 **/
    // @NotNull(message = "delivery_time_flag is compulsory")
    // @NotBlank(message = "delivery_time_flag is compulsory")
    String deliveryTimeFlag;

    /** 送货日期 **/
    String deliveryDateFlag;

    /** 订单产生时间 dateTime formatted as yyyy-MM-dd HH:mm:ss **/
    @NotBlank(message = "order_time is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
    String orderTime;

    /** 商品重量 **/
    String weight;

    /** 商品总价,折前 **/
    @NotBlank(message = "total_product_price is compulsory")
    @DecimalMin(value = "0.0", message = "totalProductPrice should bigger than 0.0")
    String totalProductPrice;

    /** 总价折扣优惠总金额，订单折扣优惠 **/
    @DecimalMin(value = "0.0", message = "discountTotal should bigger than 0.0")
    String discountTotal;

    /** 订单用券金额 **/
    @DecimalMin(value = "0.0", message = "totalPromo should bigger than 0.0")
    String totalPromo;

    /** 支付金额的汇总 **/
    @NotBlank(message = "total_pay_amount is compulsory")
    //测试以后加上
    //@DecimalMin(value = "0.01", message = "totalPayAmount should bigger than 0.01")
    String totalPayAmount;

    /** 赠送总积分 **/
    String totalGivePoints;
    
    //4.16 add
    /** 订单用积分 **/
    String totalPoint;
    //4.16 add
    /** 订单用积分抵扣金额 **/
    String totalPointAmount;

    /** 是否货到付款 **/
    @NotBlank(message = "if_pay_on_arrival is compulsory")
    @Range(min = 0, max = 1, message = "if_pay_on_arrival: value must be between 0 and 1")
    String ifPayOnArrival;

    /** 收货区域ID编码 **/
    @Length(max = 32, message = "receive_area_id: length must be less than 32")
    String receiveAreaId;

    /** 运费总额 | 物流费用 **/
    @DecimalMin(value = "0.0", message = "transportFee should bigger than 0.0")
    String transportFee;

    /** 运费优惠 **/
    @DecimalMin(value = "0.0", message = "discountTransport should bigger than 0.0")
    String discountTransport;

    /** 备注 **/
    @Length(max = 255, message = "remark: length must be less than 255")
    String clientRemark;

    /** 会员当前对应rowid **/
    @NotBlank(message = "member_no is compulsory")
    @Length(max = 32, message = "member_no: length must be less than 32")
    String memberNo;

    /** 会员当前对应rowid **/
    @NotBlank(message = "memberVipCardLevel is compulsory")
    @Length(max = 32, message = "memberVipCardLevel: length must be less than 32")
    String memberVipCardLevel;

    /** 会员姓名 **/
    @Length(max = 32, message = "customer_name: length must be less than 32")
    String customerName;

    /** 会员手机号码 **/
    @Length(max = 16, message = "customer_phone: length must be less than 16")
    String customerPhone;

    String customerEmail;
    String ip;
    /** 是否需要发票 **/
    @NotBlank(message = "need_invoice is compulsory")
    @Range(min = 0, max = 1, message = "need_invoice: value must be between 0 and 1")
    String needInvoice;
    
    /** 商品总数  20180323 **/
    @NotNull(message = "totalProductCount is compulsory")
    Integer totalProductCount;
    
    /** 订单金额总计(包含积分支付 部分,包含了优惠卷的部分) 20180323 **/
    //@NotBlank(message = "totalProductCount is compulsory")
    String totalOrderAmount;
    
    
    /** 子订单 **/
    // @NotNull(message = "osDTOs is compulsory")
    // @NotBlank(message = "osDTOs is compulsory")
    @Valid
    List<OrderSubDTO> osDTOs;

    /** 订单级促销 **/
    @Valid
    List<OrderPromotionDTO> opDTOs;

    /** 订单支付 **/
    @Valid
    List<OrderPayDTO> orderPayDTOs;
    
    
    /** 订单属性 **/
    //2018/03/13 add wch
    //@NotBlank(message = "BILL_TYPE is compulsory")
    @Length(max = 32, message = "order_type: length must be less than 32")
    String billType;
    
    //@NotBlank(message = "regionCode is compulsory")
    String regionCode;
    
    
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

    public List<OrderSubDTO> getOsDTOs() {
        return osDTOs;
    }

    public void setOsDTOs(List<OrderSubDTO> osDTOs) {
        this.osDTOs = osDTOs;
    }

    // public List<OrderItemGiftInfoDTO> getOigiDTOs() {
    // return oigiDTOs;
    // }
    //
    // public void setOigiDTOs(List<OrderItemGiftInfoDTO> oigiDTOs) {
    // this.oigiDTOs = oigiDTOs;
    // }

    public List<OrderPromotionDTO> getOpDTOs() {
        return opDTOs;
    }

    public void setOpDTOs(List<OrderPromotionDTO> opDTOs) {
        this.opDTOs = opDTOs;
    }

    public List<OrderPayDTO> getOrderPayDTOs() {
        return orderPayDTOs;
    }

    public void setOrderPayDTOs(List<OrderPayDTO> orderPayDTOs) {
        this.orderPayDTOs = orderPayDTOs;
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

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getTotalPointAmount() {
		return totalPointAmount;
	}

	public void setTotalPointAmount(String totalPointAmount) {
		this.totalPointAmount = totalPointAmount;
	}

	/**
	 * @return the shopNo
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * @param shopNo the shopNo to set
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	
	
	
	
    
}
