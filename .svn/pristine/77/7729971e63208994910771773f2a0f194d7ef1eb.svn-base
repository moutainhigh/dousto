package com.ibm.oms.service.pay.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="data")
public class PayOnLineBDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String B1;//商户订单号 商户侧生成的订单号，不可重复
	private String B2;//退款单号
	private String B3;//手机小票标题 顾客手机小票显示的标题
	private String B4;//金额 单位分，1 分为 1,1 元为 100 
    private String B5;//商品信息 商品信息
    private String B6;//花呗分期信息 格式为分期数:值;卖家承担手续费百分比: 值。 hb_fq_num 可选，最大长度 5，使用花呗分期 要进行的分期数 hb_fq_seller_percent 可选，最大长度 3 使 用花呗分期需要卖家承担的手续费比例的百 分值，传入 100 代表 100% hb_fq_num:10;hb_fq_seller_percent:100 
    private String B7;//支付宝不可优惠金 额
    private String B8;//支付宝禁用渠道
    private String B11;//openid/userid 微信 openid 或支付宝 userid。支付宝必填， 微信 openid 和 sub_openid 不能同时为空， JSAPI、WXA 必传 
    private String B12;//sub_openid 微信 sub_openid, JSAPI、WXA 必传 
    private String B13;//通知地址 接收异步通知回调地址
    private String B14;//用户 ip 用户终端 ip。微信 H5、APP 支付必填 
    private String B15;//整单流水号
    private String B16;//商户微信 APPID
    private String B17;//订单开始时间，格式 yyyy-MM-dd HH:mm:ss 
    private String B18;//订单过期时间，格式 yyyy-MM-dd HH:mm:ss
    
    @XmlElement(name = "B1") 
	public String getB1() {
		return B1;
	}
	public void setB1(String b1) {
		B1 = b1;
	}
	@XmlElement(name = "B2") 
	public String getB2() {
		return B2;
	}
	public void setB2(String b2) {
		B2 = b2;
	}
	@XmlElement(name = "B3") 
	public String getB3() {
		return B3;
	}
	public void setB3(String b3) {
		B3 = b3;
	}
	 @XmlElement(name = "B4") 
	public String getB4() {
		return B4;
	}
	public void setB4(String b4) {
		B4 = b4;
	}
	 @XmlElement(name = "B5") 
	public String getB5() {
		return B5;
	}
	public void setB5(String b5) {
		B5 = b5;
	}
	@XmlElement(name = "B13") 
	public String getB13() {
		return B13;
	}
	public void setB13(String b13) {
		B13 = b13;
	}
	@XmlElement(name = "B11") 
	public String getB11() {
		return B11;
	}
	public void setB11(String b11) {
		B11 = b11;
	}
	@XmlElement(name = "B12")
	public String getB12() {
		return B12;
	}
	public void setB12(String b12) {
		B12 = b12;
	}
	@XmlElement(name = "B14")
	public String getB14() {
		return B14;
	}
	public void setB14(String b14) {
		B14 = b14;
	}
	@XmlElement(name = "B16")
	public String getB16() {
		return B16;
	}
	public void setB16(String b16) {
		B16 = b16;
	}
	@XmlElement(name = "B17")
	public String getB17() {
		return B17;
	}
	public void setB17(String b17) {
		B17 = b17;
	}
	@XmlElement(name = "B18")
	public String getB18() {
		return B18;
	}
	public void setB18(String b18) {
		B18 = b18;
	}
	@XmlElement(name = "B6")
	public String getB6() {
		return B6;
	}
	public void setB6(String b6) {
		B6 = b6;
	}
	@XmlElement(name = "B7")
	public String getB7() {
		return B7;
	}
	public void setB7(String b7) {
		B7 = b7;
	}
	@XmlElement(name = "B8")
	public String getB8() {
		return B8;
	}
	public void setB8(String b8) {
		B8 = b8;
	}
	@XmlElement(name = "B15")
	public String getB15() {
		return B15;
	}
	public void setB15(String b15) {
		B15 = b15;
	}
	
}
