package com.ibm.oms.service.pay.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PayOnLineGoodsDto implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private String goodsId;//商品ID
     private String goodsName;//商品名称
     private String price;//价格
     private String quantity;//数量
     
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
     
}
