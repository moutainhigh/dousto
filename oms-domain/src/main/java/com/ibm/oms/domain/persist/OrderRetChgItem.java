package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * 
 * This class is used to represent available ORDER_RET_CHG_ITEM in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_RET_CHG_ITEM")
public class OrderRetChgItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_ret_chg_item", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_ret_chg_item_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_ret_chg_item")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ID_ORDER_SUB")
    private java.lang.Long idOrderSub;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    @Column(name = "ORDER_ITEM_NO")
    private java.lang.String orderItemNo;
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.Long aliasOrderNo;
    @Column(name = "ALIAS_ORDER_ITEM_NO")
    private java.lang.Long aliasOrderItemNo;
    @Column(name = "ITEM_SOURCE")
    private java.lang.String itemSource;
    @Column(name = "SALE_NUM")
    private java.lang.Long saleNum;
    @Column(name = "SALE_UNIT")
    private java.lang.String saleUnit;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "UNIT_DISCOUNT")
    private BigDecimal unitDiscount;
    @Column(name = "ITEM_DISCOUNT")
    private BigDecimal itemDiscount;
    @Column(name = "COUPON_AMOUNT")
    private BigDecimal couponAmount;
    @Column(name = "UNIT_DEDUCTED_PRICE")
    private BigDecimal unitDeductedPrice;
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;
    @Column(name = "PROMO_TICKET_MONEY")
    private BigDecimal promoTicketMoney;
    @Column(name = "PRODUCT_GROUP")
    private java.lang.String productGroup;
    @Column(name = "PRODUCT_CATEGORY")
    private java.lang.String productCategory;
    @Column(name = "COMMODITY_NAME")
    private java.lang.String commodityName;
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Column(name = "COMMODITY_CODE")
    private java.lang.String commodityCode;
    @Column(name = "BAR_CODE")
    private java.lang.String barCode;
    @Column(name = "INSTORE_BAR_CODE")
    private java.lang.String inStoreBarCode;
    @Column(name = "SKU_NO")
    private java.lang.String skuNo;
    @Column(name = "PRODUCT_PROPERTY_FLAG")
    private java.lang.Long productPropertyFlag;
    @Column(name = "SUPPLIER_CODE")
    private java.lang.String supplierCode;
    @Column(name = "MERCHANT_NO")
    private java.lang.String merchantNo;
    @Column(name = "STOCK_NO")
    private java.lang.String stockNo;
    @Column(name = "PROMO_TYPE")
    private java.lang.String promoType;
    @Column(name = "PROMO_ID")
    private java.lang.String promoId;
    @Column(name = "INVENTORY_PRICE")
    private BigDecimal inventoryPrice;
    @Column(name = "ORDER_ITEM_CLASS")
    private java.lang.String orderItemClass;
    @Column(name = "GIFT_TYPE")
    private java.lang.String giftType;
    @Column(name = "HAS_GIFT")
    private java.lang.Long hasGift;
    @Column(name = "PROMOTION_TYPE")
    private java.lang.String promotionType;
    @Column(name = "PROMOTION_CODE")
    private java.lang.String promotionCode;
    @Column(name = "BRAND")
    private java.lang.String brand;
    @Column(name = "PRODUCT_POINT")
    private BigDecimal productPoint;
    @Column(name = "INFO_SOURCE")
    private java.lang.String infoSource;
    @Column(name = "ADS_PAGE")
    private java.lang.String adsPage;
    @Column(name = "INVOICE_PRINT_MERCHANT")
    private java.lang.String invoicePrintMerchant;
    @Column(name = "COUPON_TOTAL_MONEY")
    private BigDecimal couponTotalMoney;
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;
    @Column(name = "ORDER_ITEM_REMARK")
    private java.lang.String orderItemRemark;
    @Column(name = "REMARK")
    private java.lang.String remark;
    @Column(name = "IS_DELETED")
    private java.lang.Long isDeleted;
    @Column(name = "CREATED_BY")
    private java.lang.String createdBy;
    @Column(name = "UPDATED_BY")
    private java.lang.String updatedBy;
    @Column(name = "DATE_CREATED")
    private java.util.Date dateCreated;
    @Column(name = "DATE_UPDATED")
    private java.util.Date dateUpdated;
    @Column(name = "REASON")
    private java.lang.String reason;
    @Column(name = "REF_ORDER_ITEM_NO")
    private String refOrderItemNo; // 关联的原明细no
    @Column(name = "BRAND_NAME")
    private java.lang.String brandName;
    @Column(name = "PRODUCT_CATEGORY_NAME")
    private java.lang.String productCategoryName;
    @Column(name = "SKU_ID")
    private java.lang.String skuId;
    @Column(name = "IS_UNION_BIZ")
    private java.lang.String isUnionBiz; //自营、联营
    @Column(name = "NORMAL_PRICE")
    private BigDecimal normalPrice; //原价

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER_SUB", insertable = false, updatable = false)
    private OrderSub orderSub;

    @Transient
    private List<OrderCombineRelation> orderCombineRelations = new ArrayList<OrderCombineRelation>();
    @Transient
    private List<ProductProperty> productPropertys = new ArrayList<ProductProperty>();

    // 业务逻辑中使用:退换货用到
    @Transient
    private Long refOrderItemId; // 关联的原明细id

    // 换货商品信息(色码款换货)
    @Transient
    private Long chg_retChangeId; // 换货单与原销售单的明细关联ID，出库单需用到
    @Transient
    private String chg_skuNo;
    @Transient
    private String chg_barCode;
    @Transient
    private String chg_commodityCode;
    @Transient
    private String chg_commodityName;

    // 如果退货可退数量
    @Transient
    private java.lang.Long remainNum;

    /**
     * default constructor
     */
    public OrderRetChgItem() {
    }

    /**
     * The value of the idOrderSub association.
     * 
     * @return java.lang.Long Return the value of the idOrderSub column.
     */
    public java.lang.Long getIdOrderSub() {
        return idOrderSub;
    }

    /**
     * Set the value of the idOrderSub.
     * 
     * @param idOrderSub
     */
    public void setIdOrderSub(java.lang.Long newIdOrderSub) {
        this.idOrderSub = newIdOrderSub;
    }

    /**
     * The value of the orderNo association.
     * 
     * @return java.lang.String Return the value of the orderNo column.
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value of the orderNo.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String newOrderNo) {
        this.orderNo = newOrderNo;
    }

    /**
     * The value of the orderSubNo association.
     * 
     * @return java.lang.String Return the value of the orderSubNo column.
     */
    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    /**
     * Set the value of the orderSubNo.
     * 
     * @param orderSubNo
     */
    public void setOrderSubNo(java.lang.String newOrderSubNo) {
        this.orderSubNo = newOrderSubNo;
    }

    /**
     * The value of the orderItemNo association.
     * 
     * @return java.lang.String Return the value of the orderItemNo column.
     */
    public java.lang.String getOrderItemNo() {
        return orderItemNo;
    }

    /**
     * Set the value of the orderItemNo.
     * 
     * @param orderItemNo
     */
    public void setOrderItemNo(java.lang.String newOrderItemNo) {
        this.orderItemNo = newOrderItemNo;
    }

    /**
     * The value of the aliasOrderNo association.
     * 
     * @return java.lang.Long Return the value of the aliasOrderNo column.
     */
    public java.lang.Long getAliasOrderNo() {
        return aliasOrderNo;
    }

    /**
     * Set the value of the aliasOrderNo.
     * 
     * @param aliasOrderNo
     */
    public void setAliasOrderNo(java.lang.Long newAliasOrderNo) {
        this.aliasOrderNo = newAliasOrderNo;
    }

    /**
     * The value of the aliasOrderItemNo association.
     * 
     * @return java.lang.Long Return the value of the aliasOrderItemNo column.
     */
    public java.lang.Long getAliasOrderItemNo() {
        return aliasOrderItemNo;
    }

    /**
     * Set the value of the aliasOrderItemNo.
     * 
     * @param aliasOrderItemNo
     */
    public void setAliasOrderItemNo(java.lang.Long newAliasOrderItemNo) {
        this.aliasOrderItemNo = newAliasOrderItemNo;
    }

    /**
     * The value of the itemSource association.
     * 
     * @return java.lang.String Return the value of the itemSource column.
     */
    public java.lang.String getItemSource() {
        return itemSource;
    }

    /**
     * Set the value of the itemSource.
     * 
     * @param itemSource
     */
    public void setItemSource(java.lang.String newItemSource) {
        this.itemSource = newItemSource;
    }

    /**
     * The value of the saleUnit association.
     * 
     * @return java.lang.String Return the value of the saleUnit column.
     */
    public java.lang.String getSaleUnit() {
        return saleUnit;
    }

    /**
     * Set the value of the saleUnit.
     * 
     * @param saleUnit
     */
    public void setSaleUnit(java.lang.String newSaleUnit) {
        this.saleUnit = newSaleUnit;
    }

    /**
     * The value of the productGroup association.
     * 
     * @return java.lang.String Return the value of the productGroup column.
     */
    public java.lang.String getProductGroup() {
        return productGroup;
    }

    /**
     * Set the value of the productGroup.
     * 
     * @param productGroup
     */
    public void setProductGroup(java.lang.String newProductGroup) {
        this.productGroup = newProductGroup;
    }

    /**
     * The value of the productCategory association.
     * 
     * @return java.lang.String Return the value of the productCategory column.
     */
    public java.lang.String getProductCategory() {
        return productCategory;
    }

    /**
     * Set the value of the productCategory.
     * 
     * @param productCategory
     */
    public void setProductCategory(java.lang.String newProductCategory) {
        this.productCategory = newProductCategory;
    }

    /**
     * The value of the commodityName association.
     * 
     * @return java.lang.String Return the value of the commodityName column.
     */
    public java.lang.String getCommodityName() {
        return commodityName;
    }

    /**
     * Set the value of the commodityName.
     * 
     * @param commodityName
     */
    public void setCommodityName(java.lang.String newCommodityName) {
        this.commodityName = newCommodityName;
    }

    /**
     * The value of the commodityCode association.
     * 
     * @return java.lang.String Return the value of the commodityCode column.
     */
    public java.lang.String getCommodityCode() {
        return commodityCode;
    }

    /**
     * Set the value of the commodityCode.
     * 
     * @param commodityCode
     */
    public void setCommodityCode(java.lang.String newCommodityCode) {
        this.commodityCode = newCommodityCode;
    }

    /**
     * The value of the barCode association.
     * 
     * @return java.lang.String Return the value of the barCode column.
     */
    public java.lang.String getBarCode() {
        return barCode;
    }

    /**
     * Set the value of the barCode.
     * 
     * @param barCode
     */
    public void setBarCode(java.lang.String newBarCode) {
        this.barCode = newBarCode;
    }

    /**
     * The value of the skuNo association.
     * 
     * @return java.lang.String Return the value of the skuNo column.
     */
    public java.lang.String getSkuNo() {
        return skuNo;
    }

    /**
     * Set the value of the skuNo.
     * 
     * @param skuNo
     */
    public void setSkuNo(java.lang.String newSkuNo) {
        this.skuNo = newSkuNo;
    }

    /**
     * The value of the productPropertyFlag association.
     * 
     * @return java.lang.Long Return the value of the productPropertyFlag column.
     */
    public java.lang.Long getProductPropertyFlag() {
        return productPropertyFlag;
    }

    /**
     * Set the value of the productPropertyFlag.
     * 
     * @param productPropertyFlag
     */
    public void setProductPropertyFlag(java.lang.Long newProductPropertyFlag) {
        this.productPropertyFlag = newProductPropertyFlag;
    }

    /**
     * The value of the supplierCode association.
     * 
     * @return java.lang.String Return the value of the supplierCode column.
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Set the value of the supplierCode.
     * 
     * @param supplierCode
     */
    public void setSupplierCode(java.lang.String newSupplierCode) {
        this.supplierCode = newSupplierCode;
    }

    public java.lang.String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(java.lang.String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * The value of the stockNo association.
     * 
     * @return java.lang.String Return the value of the stockNo column.
     */
    public java.lang.String getStockNo() {
        return stockNo;
    }

    /**
     * Set the value of the stockNo.
     * 
     * @param stockNo
     */
    public void setStockNo(java.lang.String newStockNo) {
        this.stockNo = newStockNo;
    }

    /**
     * The value of the promoType association.
     * 
     * @return java.lang.String Return the value of the promoType column.
     */
    public java.lang.String getPromoType() {
        return promoType;
    }

    /**
     * Set the value of the promoType.
     * 
     * @param promoType
     */
    public void setPromoType(java.lang.String newPromoType) {
        this.promoType = newPromoType;
    }

    /**
     * The value of the promoId association.
     * 
     * @return java.lang.String Return the value of the promoId column.
     */
    public java.lang.String getPromoId() {
        return promoId;
    }

    /**
     * Set the value of the promoId.
     * 
     * @param promoId
     */
    public void setPromoId(java.lang.String newPromoId) {
        this.promoId = newPromoId;
    }

    /**
     * The value of the orderItemClass association.
     * 
     * @return java.lang.String Return the value of the orderItemClass column.
     */
    public java.lang.String getOrderItemClass() {
        return orderItemClass;
    }

    /**
     * Set the value of the orderItemClass.
     * 
     * @param orderItemClass
     */
    public void setOrderItemClass(java.lang.String newOrderItemClass) {
        this.orderItemClass = newOrderItemClass;
    }

    /**
     * The value of the giftType association.
     * 
     * @return java.lang.String Return the value of the giftType column.
     */
    public java.lang.String getGiftType() {
        return giftType;
    }

    /**
     * Set the value of the giftType.
     * 
     * @param giftType
     */
    public void setGiftType(java.lang.String newGiftType) {
        this.giftType = newGiftType;
    }

    /**
     * The value of the hasGift association.
     * 
     * @return java.lang.Long Return the value of the hasGift column.
     */
    public java.lang.Long getHasGift() {
        return hasGift;
    }

    /**
     * Set the value of the hasGift.
     * 
     * @param hasGift
     */
    public void setHasGift(java.lang.Long newHasGift) {
        this.hasGift = newHasGift;
    }

    /**
     * The value of the promotionType association.
     * 
     * @return java.lang.String Return the value of the promotionType column.
     */
    public java.lang.String getPromotionType() {
        return promotionType;
    }

    /**
     * Set the value of the promotionType.
     * 
     * @param promotionType
     */
    public void setPromotionType(java.lang.String newPromotionType) {
        this.promotionType = newPromotionType;
    }

    /**
     * The value of the promotionCode association.
     * 
     * @return java.lang.String Return the value of the promotionCode column.
     */
    public java.lang.String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Set the value of the promotionCode.
     * 
     * @param promotionCode
     */
    public void setPromotionCode(java.lang.String newPromotionCode) {
        this.promotionCode = newPromotionCode;
    }

    /**
     * The value of the brand association.
     * 
     * @return java.lang.String Return the value of the brand column.
     */
    public java.lang.String getBrand() {
        return brand;
    }

    /**
     * Set the value of the brand.
     * 
     * @param brand
     */
    public void setBrand(java.lang.String newBrand) {
        this.brand = newBrand;
    }

    /**
     * The value of the productPoint association.
     * 
     * @return java.lang.Long Return the value of the productPoint column.
     */
    public BigDecimal getProductPoint() {
        return productPoint;
    }

    /**
     * Set the value of the productPoint.
     * 
     * @param productPoint
     */
    public void setProductPoint(BigDecimal newProductPoint) {
        this.productPoint = newProductPoint;
    }

    /**
     * The value of the infoSource association.
     * 
     * @return java.lang.String Return the value of the infoSource column.
     */
    public java.lang.String getInfoSource() {
        return infoSource;
    }

    /**
     * Set the value of the infoSource.
     * 
     * @param infoSource
     */
    public void setInfoSource(java.lang.String newInfoSource) {
        this.infoSource = newInfoSource;
    }

    /**
     * The value of the adsPage association.
     * 
     * @return java.lang.String Return the value of the adsPage column.
     */
    public java.lang.String getAdsPage() {
        return adsPage;
    }

    /**
     * Set the value of the adsPage.
     * 
     * @param adsPage
     */
    public void setAdsPage(java.lang.String newAdsPage) {
        this.adsPage = newAdsPage;
    }

    /**
     * The value of the invoicePrintMerchant association.
     * 
     * @return java.lang.String Return the value of the invoicePrintMerchant column.
     */
    public java.lang.String getInvoicePrintMerchant() {
        return invoicePrintMerchant;
    }

    /**
     * Set the value of the invoicePrintMerchant.
     * 
     * @param invoicePrintMerchant
     */
    public void setInvoicePrintMerchant(java.lang.String newInvoicePrintMerchant) {
        this.invoicePrintMerchant = newInvoicePrintMerchant;
    }

    /**
     * The value of the billType association.
     * 
     * @return java.lang.Long Return the value of the billType column.
     */
    public java.lang.Long getBillType() {
        return billType;
    }

    /**
     * Set the value of the billType.
     * 
     * @param billType
     */
    public void setBillType(java.lang.Long newBillType) {
        this.billType = newBillType;
    }

    /**
     * The value of the orderItemRemark association.
     * 
     * @return java.lang.String Return the value of the orderItemRemark column.
     */
    public java.lang.String getOrderItemRemark() {
        return orderItemRemark;
    }

    /**
     * Set the value of the orderItemRemark.
     * 
     * @param orderItemRemark
     */
    public void setOrderItemRemark(java.lang.String newOrderItemRemark) {
        this.orderItemRemark = newOrderItemRemark;
    }

    /**
     * The value of the remark association.
     * 
     * @return java.lang.String Return the value of the remark column.
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * Set the value of the remark.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String newRemark) {
        this.remark = newRemark;
    }

    /**
     * The value of the isDeleted association.
     * 
     * @return java.lang.Long Return the value of the isDeleted column.
     */
    public java.lang.Long getIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of the isDeleted.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(java.lang.Long newIsDeleted) {
        this.isDeleted = newIsDeleted;
    }

    /**
     * The value of the createdBy association.
     * 
     * @return java.lang.String Return the value of the createdBy column.
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of the createdBy.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    /**
     * Set the value of the updatedBy.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
    }

    /**
     * The value of the dateCreated association.
     * 
     * @return java.util.Date Return the value of the dateCreated column.
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the value of the dateCreated.
     * 
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date newDateCreated) {
        this.dateCreated = newDateCreated;
    }

    /**
     * The value of the dateUpdated association.
     * 
     * @return java.util.Date Return the value of the dateUpdated column.
     */
    public java.util.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Set the value of the dateUpdated.
     * 
     * @param dateUpdated
     */
    public void setDateUpdated(java.util.Date newDateUpdated) {
        this.dateUpdated = newDateUpdated;
    }

    /**
     * The value of the reason association.
     * 
     * @return java.lang.String Return the value of the reason column.
     */
    public java.lang.String getReason() {
        return reason;
    }

    /**
     * Set the value of the reason.
     * 
     * @param reason
     */
    public void setReason(java.lang.String newReason) {
        this.reason = newReason;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(java.lang.Long idOrder) {
        this.idOrder = idOrder;
    }

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public Long getRefOrderItemId() {
        return refOrderItemId;
    }

    public void setRefOrderItemId(Long refOrderItemId) {
        this.refOrderItemId = refOrderItemId;
    }

    public String getRefOrderItemNo() {
        return refOrderItemNo;
    }

    public void setRefOrderItemNo(String refOrderItemNo) {
        this.refOrderItemNo = refOrderItemNo;
    }

    public java.lang.Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(java.lang.Long saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitDiscount() {
        return unitDiscount;
    }

    public void setUnitDiscount(BigDecimal unitDiscount) {
        this.unitDiscount = unitDiscount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getUnitDeductedPrice() {
        return unitDeductedPrice;
    }

    public void setUnitDeductedPrice(BigDecimal unitDeductedPrice) {
        this.unitDeductedPrice = unitDeductedPrice;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPromoTicketMoney() {
        return promoTicketMoney;
    }

    public void setPromoTicketMoney(BigDecimal promoTicketMoney) {
        this.promoTicketMoney = promoTicketMoney;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getInventoryPrice() {
        return inventoryPrice;
    }

    public void setInventoryPrice(BigDecimal inventoryPrice) {
        this.inventoryPrice = inventoryPrice;
    }

    public BigDecimal getCouponTotalMoney() {
        return couponTotalMoney;
    }

    public void setCouponTotalMoney(BigDecimal couponTotalMoney) {
        this.couponTotalMoney = couponTotalMoney;
    }

    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }

    public BigDecimal getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(BigDecimal itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public OrderSub getOrderSub() {
        return orderSub;
    }

    public void setOrderSub(OrderSub orderSub) {
        this.orderSub = orderSub;
    }

    public String getChg_skuNo() {
        return chg_skuNo;
    }

    public void setChg_skuNo(String chg_skuNo) {
        this.chg_skuNo = chg_skuNo;
    }

    public String getChg_barCode() {
        return chg_barCode;
    }

    public void setChg_barCode(String chg_barCode) {
        this.chg_barCode = chg_barCode;
    }

    public String getChg_commodityCode() {
        return chg_commodityCode;
    }

    public void setChg_commodityCode(String chg_commodityCode) {
        this.chg_commodityCode = chg_commodityCode;
    }

    public String getChg_commodityName() {
        return chg_commodityName;
    }

    public void setChg_commodityName(String chg_commodityName) {
        this.chg_commodityName = chg_commodityName;
    }

    public Long getChg_retChangeId() {
        return chg_retChangeId;
    }

    public void setChg_retChangeId(Long chg_retChangeId) {
        this.chg_retChangeId = chg_retChangeId;
    }

    public List<OrderCombineRelation> getOrderCombineRelations() {
        return orderCombineRelations;
    }

    public void setOrderCombineRelations(List<OrderCombineRelation> orderCombineRelations) {
        this.orderCombineRelations = orderCombineRelations;
    }

    public java.lang.Long getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(java.lang.Long remainNum) {
        this.remainNum = remainNum;
    }

    public java.lang.String getBrandName() {
        return brandName;
    }

    public void setBrandName(java.lang.String brandName) {
        this.brandName = brandName;
    }

    public java.lang.String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(java.lang.String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public java.lang.String getInStoreBarCode() {
        return inStoreBarCode;
    }

    public void setInStoreBarCode(java.lang.String inStoreBarCode) {
        this.inStoreBarCode = inStoreBarCode;
    }

    public List<ProductProperty> getProductPropertys() {
        return productPropertys;
    }

    public void setProductPropertys(List<ProductProperty> productPropertys) {
        this.productPropertys = productPropertys;
    }

    public java.lang.String getSkuId() {
        return skuId;
    }

    public void setSkuId(java.lang.String skuId) {
        this.skuId = skuId;
    }

    public java.lang.String getIsUnionBiz() {
        return isUnionBiz;
    }

    public void setIsUnionBiz(java.lang.String isUnionBiz) {
        this.isUnionBiz = isUnionBiz;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    
}
