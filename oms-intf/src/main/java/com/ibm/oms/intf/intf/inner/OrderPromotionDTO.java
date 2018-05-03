package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong
 * 订单促销信息
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class OrderPromotionDTO implements Serializable {
	
	/**优惠级别**/
    @NotNull(message = "promoLevel is compulsory")
    @NotBlank(message = "promoLevel is compulsory")
    @Length(max = 10,message = "promoLevel: length must be less than 10")
	String promoLevel;
	
	/**订单号/行号**/
    @Length(max = 24,message = "idOrderItem: length must be less than 24")
	String idOrderItem;
	
	/**促销规则编码, 用券没有促销规则编码**/
//    @NotNull(message = "promoNo is compulsory")
//    @NotBlank(message = "promoNo is compulsory")
    @Length(max = 32,message = "promoNo: length must be less than 32")
	String promoNo;
	
	/**促销规则名称**/
    @NotNull(message = "promoName is compulsory")
    @NotBlank(message = "promoName is compulsory")
    @Length(max = 42,message = "promoName: 汉字不超过 42")
	String promoName;
	
	/**用券(退货不还)，用积分，返券，返积分，返my卡**/
    @NotNull(message = "promoType is compulsory")
    @NotBlank(message = "promoType is compulsory")
    @Length(max = 32,message = "promoType: length must be less than 32")
	String promoType;
	
	/**返券批次号**/
    //@NotNull(message = "ticketBundleNo is compulsory")
    //@NotBlank(message = "ticketBundleNo is compulsory")
    @Length(max = 32,message = "ticketBundleNo: length must be less than 32")
	String ticketBundleNo;
	
	/**券号**/
    //@NotNull(message = "ticketNo is compulsory")
    //@NotBlank(message = "ticketNo is compulsory")
    @Length(max = 32,message = "ticketNo: length must be less than 32")
	String ticketNo;
	
	/**总计券/卡金额**/
    @DecimalMin(value="0.0", message = "ticketAmount should bigger than 0.0")
	String ticketAmount;
	
	/**总计积分数量**/
    @DecimalMin(value="0.0", message = "pointCount should bigger than 0.0")
	String pointCount;
	
	/**会员号**/
    @NotNull(message = "memberNo is compulsory")
    @NotBlank(message = "memberNo is compulsory")
    @Length(max = 32,message = "memberNo: length must be less than 32")
	String memberNo;

	public String getPromoLevel() {
		return promoLevel;
	}
	public void setPromoLevel(String promoLevel) {
		this.promoLevel = promoLevel;
	}
	public String getIdOrderItem() {
		return idOrderItem;
	}
	public void setIdOrderItem(String idOrderItem) {
		this.idOrderItem = idOrderItem;
	}
	public String getPromoNo() {
		return promoNo;
	}
	public void setPromoNo(String promoNo) {
		this.promoNo = promoNo;
	}
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public String getPromoType() {
		return promoType;
	}
	public void setPromoType(String promoType) {
		this.promoType = promoType;
	}
	public String getTicketBundleNo() {
		return ticketBundleNo;
	}
	public void setTicketBundleNo(String ticketBundleNo) {
		this.ticketBundleNo = ticketBundleNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public String getPointCount() {
		return pointCount;
	}
	public void setPointCount(String pointCount) {
		this.pointCount = pointCount;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

}
