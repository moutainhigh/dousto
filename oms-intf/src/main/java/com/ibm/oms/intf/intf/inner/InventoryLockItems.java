package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author liucy
 * 
 * 商品行商品锁定
 */
public class InventoryLockItems implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/**定单号**/    
    private String orderNo;
    
    /**行号**/ 
    private String orderItemNo;
    /**SKU编码**/     
    private String skuNo;
    /**商品条码**/      
    private String barCode;
    /**包装数量**/      
    private String packSize;
   /** 锁定件数**/      
    private String pcs;
    /**锁定零数**/      
    private String odd;
    /**锁定数量**/       
    private String saleNum;
    /**备注**/    
    private String remark;
    /**渠道code**/    
    private String channelCode;
    /**活动CODE**/    
    private String promotionCode;
    /**商品类型**/      
    private String prodType;
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getOrderItemNo() {
        return orderItemNo;
    }
    public void setOrderItemNo(String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }
    public String getSkuNo() {
        return skuNo;
    }
    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getPackSize() {
        return packSize;
    }
    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }
    public String getPcs() {
        return pcs;
    }
    public void setPcs(String pcs) {
        this.pcs = pcs;
    }
    public String getOdd() {
        return odd;
    }
    public void setOdd(String odd) {
        this.odd = odd;
    }
    public String getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getProdType() {
        return prodType;
    }
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    
}
