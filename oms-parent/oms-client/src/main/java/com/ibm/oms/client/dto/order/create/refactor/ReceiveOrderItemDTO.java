package com.ibm.oms.client.dto.order.create.refactor;

import java.io.Serializable;
import java.util.List;

/**
 * 订单项实体类
 * @author wangchao
 *
 */
public class ReceiveOrderItemDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7726226812703032828L;
	//*********--组成购物车InfCart** 前台传递
	//item类型，SINGLE,GIFT,BUNDLE
	public String itemType;
	//商品id
	public String productId;
	// 活动ID  专区促销商品必传
	public String activityCode;
	public String skuId;
	//促销前单价 
	public String price;
	private Integer count;
	// item关联的促销活动id,如捆绑销售活动ID，抢购/团购/赠品活动ID等 --out
	private String promotionId;
	List<ReceiveOrderItemDTO> bundleItemList;
	//*********--组成购物车InfCart** 前台传递 end
	
	//*********--商品相关** 前台传递 start
	//商品编码
	public String commodityCode;
	//SKU码
	public String skuNo;
	//条形码
	public String barCode;
	//商品名
	public String commodityName;
	//商品分类
	public String productCategory;
	//供应商编码
	public String supplierCode;
	//商品数量
	public String saleNum;
	//销售单位 
	public String saleUnit;
	//优惠后单价
	public String unitDeductedPrice;
	//折扣金额
	public String unitDiscount;
	//信息来源
	public String infoSource;
	//广告页
	public String adsPage;
	//订单行备注
	public String orderItemRemark;
	//重量
	public String weight;
	//商品属性标志
	public String productPropertyFlag;
	
	//*********--商品相关** 前台传递 end

	
	public String getInfoSource() {
		return infoSource;
	}
	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}
	public String getAdsPage() {
		return adsPage;
	}
	public void setAdsPage(String adsPage) {
		this.adsPage = adsPage;
	}
	public String getOrderItemRemark() {
		return orderItemRemark;
	}
	public void setOrderItemRemark(String orderItemRemark) {
		this.orderItemRemark = orderItemRemark;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<ReceiveOrderItemDTO> getBundleItemList() {
		return bundleItemList;
	}
	public void setBundleItemList(List<ReceiveOrderItemDTO> bundleItemList) {
		this.bundleItemList = bundleItemList;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
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
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getProductPropertyFlag() {
		return productPropertyFlag;
	}
	public void setProductPropertyFlag(String productPropertyFlag) {
		this.productPropertyFlag = productPropertyFlag;
	}
	
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getSaleUnit() {
		return saleUnit;
	}
	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}
	public String getUnitDeductedPrice() {
		return unitDeductedPrice;
	}
	public void setUnitDeductedPrice(String unitDeductedPrice) {
		this.unitDeductedPrice = unitDeductedPrice;
	}
	public String getUnitDiscount() {
		return unitDiscount;
	}
	public void setUnitDiscount(String unitDiscount) {
		this.unitDiscount = unitDiscount;
	}
	
}
