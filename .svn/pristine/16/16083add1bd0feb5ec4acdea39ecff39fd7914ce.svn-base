package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author angqc
 * 订单促销信息(挂单)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HangOrderPromotionDTO implements Serializable {
	
	/**优惠级别**/
	String promoLevel;
	
	/**订单号/行号**/
	String idOrderItem;
	
	/**促销规则编码, 用券没有促销规则编码**/
	String promoNo;
	
	/**促销规则名称**/
	String promoName;
	
	/**促销类型  用券(退货不还)，用积分，返券，返积分，返my卡**/
	String promoType;
	
	/**返券批次号**/
	String ticketBundleNo;
	
	/**券号**/
	String ticketNo;
	
	/**总计券/卡金额**/
    @DecimalMin(value="0.0", message = "ticketAmount should bigger than 0.0")
	String ticketAmount;
	
	/**总计积分数量**/
    @DecimalMin(value="0.0", message = "pointCount should bigger than 0.0")
	String pointCount;
	
	/**会员号**/
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
