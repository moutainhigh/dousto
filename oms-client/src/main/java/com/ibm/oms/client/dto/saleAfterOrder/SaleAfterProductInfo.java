package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.List;

/**
 * 退换货商品信息，前端导购端使用
 */
public class SaleAfterProductInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195808724298654097L;
	
	//接口信息
	private MessageDto msgDto;

	private String productNo;//货号
	private String buyPrice;//购买价格
	private String productSKU;//SKU
	
	private String desc;//其他描述
	private List<String> imgList;//图片（数组）

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public String getProductSKU() {
		return productSKU;
	}

	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}	
}