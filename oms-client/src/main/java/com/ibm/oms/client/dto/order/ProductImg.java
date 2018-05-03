package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月27日 
 */
public class ProductImg implements Serializable{

	
	/* 属性说明 */
	private static final long serialVersionUID = 1L;

	private String productName;
	private String productID;
	private List productImgUrl;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public List getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(List productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

}
