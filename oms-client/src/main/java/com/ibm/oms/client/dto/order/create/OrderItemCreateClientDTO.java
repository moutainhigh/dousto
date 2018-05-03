package com.ibm.oms.client.dto.order.create;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pjsong 外部订单明细
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderItemCreateClientDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2252087410270548257L;
	/** 外部订单行号 **/
    String aliasOrderItemNo;
    String aliasOrderNo;
    String aliasOrderSubNo;
    /** 销售数量 **/
    String saleNum;

    /** 销售单位 **/
    String saleUnit;

    /** 销售单价:折前价 **/
    String unitPrice;

    /** 销售单件折扣 **/
    String unitDiscount;
    /** 销售单件折扣 **/
    String itemDiscount;
    /** 单件购物券金额 **/
    String couponAmount;
    /** 购物券总计金额 **/
    String couponTotalMoney;
    /** 折后单价 **/
    String unitDeductedPrice;

    /** 折后总价 **/
    String payAmount;

    /** 返券总金额 **/
    String promoTicketMoney;

    /** 产品组 **/
    String productGroup;

    /** 商品分类 **/
    String productCategory;

    String productCategoryName;
    /** 商品名称 **/
    String commodityName;

    /** 条形码 **/
    String barCode;

    /** 商品重量 **/
    String weight;

    /** 商品编码 **/
    String commodityCode;

    /** SKUNO(物料编号) **/
    String skuNo;

    /** 是否色码款商品 **/
    String productPropertyFlag;

    /** 供应商编码 **/
    String supplierCode;

    /** 合作伙伴id **/
    String partnerNo;

    /** 库区ID **/
    String stockNo;

    /** 促销活动类型：用券(退货不还)，用积分，返券，返积分，返my卡 **/
    String promoType;

    /** 促销活动(团/抢购)ID **/
    String promoId;

    /** 订单行类别 **/
    String orderItemClass;

    /** 赠品行级别赠品所关联的订单行 **/
    String giftOriginItem;

    /** 订单行是否有赠品 **/
    String hasGift;

    /** 促销类型,库存用，商品类型, 1:普通商品，2：活动商品，3：积分商品 **/
    String promotionType;

    /** 促销编码 **/
    String promotionCode;

    /** 产品品牌 **/
    String brand;
    String brandName;
    
//    @NotBlank(message = "productPoint is compulsory, please use '0' if no point to add")
    /** 商品赠送积分 **/
    String productPoint;

    /** 信息来源 **/
    String infoSource;

    /** 广告页 **/
    String adsPage;

    /** 订单行备注 **/
    String orderItemRemark;
    /** 如果属于订单级赠品 **/
    String combineRule;
    String combineName;
    String combineNo;
    /** 组合类型: 普通组合，高级组合，外部系统给。赠品组合，主行+赠品关联订单行 **/
    String combineType;
    String itemSource;
    String itemSnapshot;
    List<OrderPromotionCreateClientDTO> opDTOs;
    //均摊积分，每个订单行均摊的积分
    String  sharePoint;

    public String getAliasOrderItemNo() {
        return aliasOrderItemNo;
    }

    public void setAliasOrderItemNo(String aliasOrderItemNo) {
        this.aliasOrderItemNo = aliasOrderItemNo;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitDiscount() {
        return unitDiscount;
    }

    public void setUnitDiscount(String unitDiscount) {
        this.unitDiscount = unitDiscount;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getUnitDeductedPrice() {
        return unitDeductedPrice;
    }

    public void setUnitDeductedPrice(String unitDeductedPrice) {
        this.unitDeductedPrice = unitDeductedPrice;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPromoTicketMoney() {
        return promoTicketMoney;
    }

    public void setPromoTicketMoney(String promoTicketMoney) {
        this.promoTicketMoney = promoTicketMoney;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getProductPropertyFlag() {
        return productPropertyFlag;
    }

    public void setProductPropertyFlag(String productPropertyFlag) {
        this.productPropertyFlag = productPropertyFlag;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getOrderItemClass() {
        return orderItemClass;
    }

    public void setOrderItemClass(String orderItemClass) {
        this.orderItemClass = orderItemClass;
    }

    public String getGiftOriginItem() {
        return giftOriginItem;
    }

    public void setGiftOriginItem(String giftOriginItem) {
        this.giftOriginItem = giftOriginItem;
    }

    public String getHasGift() {
        return hasGift;
    }

    public void setHasGift(String hasGift) {
        this.hasGift = hasGift;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(String productPoint) {
        this.productPoint = productPoint;
    }

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

    public List<OrderPromotionCreateClientDTO> getOpDTOs() {
        return opDTOs;
    }

    public void setOpDTOs(List<OrderPromotionCreateClientDTO> opDTOs) {
        this.opDTOs = opDTOs;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCombineRule() {
        return combineRule;
    }

    public void setCombineRule(String combineRule) {
        this.combineRule = combineRule;
    }

    public String getCombineName() {
        return combineName;
    }

    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }

    public String getCombineNo() {
        return combineNo;
    }

    public void setCombineNo(String combineNo) {
        this.combineNo = combineNo;
    }

    public String getCombineType() {
        return combineType;
    }

    public void setCombineType(String combineType) {
        this.combineType = combineType;
    }

    public String getAliasOrderNo() {
        return aliasOrderNo;
    }

    public void setAliasOrderNo(String aliasOrderNo) {
        this.aliasOrderNo = aliasOrderNo;
    }

    public String getAliasOrderSubNo() {
        return aliasOrderSubNo;
    }

    public void setAliasOrderSubNo(String aliasOrderSubNo) {
        this.aliasOrderSubNo = aliasOrderSubNo;
    }

    public String getItemSource() {
        return itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource;
    }

    public String getCouponTotalMoney() {
        return couponTotalMoney;
    }

    public void setCouponTotalMoney(String couponTotalMoney) {
        this.couponTotalMoney = couponTotalMoney;
    }

    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemSnapshot() {
        return itemSnapshot;
    }

    public void setItemSnapshot(String itemSnapshot) {
        this.itemSnapshot = itemSnapshot;
    }
    // public String getItemSnapshot() {
    // return itemSnapshot;
    // }
    // public void setItemSnapshot(String itemSnapshot) {
    // this.itemSnapshot = itemSnapshot;
    // }

	public String getSharePoint() {
		return sharePoint;
	}

	public void setSharePoint(String sharePoint) {
		this.sharePoint = sharePoint;
	}
    
}
