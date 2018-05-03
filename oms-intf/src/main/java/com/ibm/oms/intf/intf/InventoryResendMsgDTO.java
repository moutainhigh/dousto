package com.ibm.oms.intf.intf;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liucy
 * 
 * 商品行商品出库成本价
 */
public class InventoryResendMsgDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 订单行号 **/
	private String orderNo;
	/**手机**/
	private String mobilephone;
	private String skuCode;
	/**库位**/
	private String channelCode;
	/**数量**/
	private String promotionCode;
	/**备注**/
	private String productType;
	private String invoker;
	
	public String getOrderNo() {
        return orderNo;
    }


    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getMobilephone() {
        return mobilephone;
    }


    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }


    public String getSkuCode() {
        return skuCode;
    }


    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }


    public String getChannelCode() {
        return channelCode;
    }


    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }


    public String getPromotionCode() {
        return promotionCode;
    }


    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }


    public String getProductType() {
        return productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }


    public static long getSerialversionuid() {
		return serialVersionUID;
	}


    public String getInvoker() {
        return invoker;
    }


    public void setInvoker(String invoker) {
        this.invoker = invoker;
    }   
    
    
}
