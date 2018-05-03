package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderItem.class)
public abstract class OrderItem_ {

	public static volatile SingularAttribute<OrderItem, OrderSub> orderSub;
	public static volatile SingularAttribute<OrderItem, String> adsPage;
	public static volatile SingularAttribute<OrderItem, String> isUnionBiz;
	public static volatile ListAttribute<OrderItem, OrderRetChange> orderRetChanges;
	public static volatile SingularAttribute<OrderItem, String> productCategoryName;
	public static volatile SingularAttribute<OrderItem, Long> idOrderSub;
	public static volatile SingularAttribute<OrderItem, BigDecimal> promotionPoint;
	public static volatile SingularAttribute<OrderItem, String> saleUnit;
	public static volatile SingularAttribute<OrderItem, BigDecimal> unitDeductedPrice;
	public static volatile SingularAttribute<OrderItem, BigDecimal> couponAmount;
	public static volatile SingularAttribute<OrderItem, BigDecimal> payAmount;
	public static volatile SingularAttribute<OrderItem, String> commodityCode;
	public static volatile SingularAttribute<OrderItem, Long> id;
	public static volatile SingularAttribute<OrderItem, String> brand;
	public static volatile SingularAttribute<OrderItem, String> orderSubNo;
	public static volatile SingularAttribute<OrderItem, String> skuId;
	public static volatile SingularAttribute<OrderItem, BigDecimal> sharePointAmount;
	public static volatile SingularAttribute<OrderItem, BigDecimal> unitPrice;
	public static volatile SingularAttribute<OrderItem, Long> isLowGross;
	public static volatile SingularAttribute<OrderItem, String> brandName;
	public static volatile SingularAttribute<OrderItem, String> orderNo;
	public static volatile SingularAttribute<OrderItem, String> updatedBy;
	public static volatile ListAttribute<OrderItem, ProductProperty> productPropertys;
	public static volatile SingularAttribute<OrderItem, String> promotionCode;
	public static volatile SingularAttribute<OrderItem, BigDecimal> weight;
	public static volatile SingularAttribute<OrderItem, String> inStoreBarCode;
	public static volatile SingularAttribute<OrderItem, Long> saleNum;
	public static volatile SingularAttribute<OrderItem, String> barCode;
	public static volatile SingularAttribute<OrderItem, BigDecimal> pricePoint;
	public static volatile SingularAttribute<OrderItem, String> orderItemNo;
	public static volatile SingularAttribute<OrderItem, String> skuNo;
	public static volatile SingularAttribute<OrderItem, BigDecimal> normalPrice;
	public static volatile SingularAttribute<OrderItem, BigDecimal> saleTotalMoney;
	public static volatile SingularAttribute<OrderItem, String> invoicePrintMerchant;
	public static volatile SingularAttribute<OrderItem, Long> productPropertyFlag;
	public static volatile SingularAttribute<OrderItem, String> merchantNo;
	public static volatile SingularAttribute<OrderItem, BigDecimal> productPoint;
	public static volatile ListAttribute<OrderItem, OrderCombineRelation> orderCombineRelations;
	public static volatile SingularAttribute<OrderItem, String> giftOriginItem;
	public static volatile SingularAttribute<OrderItem, BigDecimal> itemDiscount;
	public static volatile SingularAttribute<OrderItem, String> remark;
	public static volatile SingularAttribute<OrderItem, String> supplierCode;
	public static volatile SingularAttribute<OrderItem, String> productCategory;
	public static volatile SingularAttribute<OrderItem, Long> idOrder;
	public static volatile SingularAttribute<OrderItem, String> promotionName;
	public static volatile SingularAttribute<OrderItem, Date> dateCreated;
	public static volatile SingularAttribute<OrderItem, Long> isDeleted;
	public static volatile SingularAttribute<OrderItem, String> itemStatus;
	public static volatile SingularAttribute<OrderItem, BigDecimal> unitDiscount;
	public static volatile SingularAttribute<OrderItem, String> stockNo;
	public static volatile SingularAttribute<OrderItem, String> aliasOrderSubNo;
	public static volatile SingularAttribute<OrderItem, String> promotionType;
	public static volatile SingularAttribute<OrderItem, String> storeType;
	public static volatile SingularAttribute<OrderItem, String> orderItemRemark;
	public static volatile SingularAttribute<OrderItem, Long> billType;
	public static volatile SingularAttribute<OrderItem, BigDecimal> sharePoint;
	public static volatile ListAttribute<OrderItem, OrderRetAdd> orderRetAdds;
	public static volatile SingularAttribute<OrderItem, String> orderItemClass;
	public static volatile SingularAttribute<OrderItem, Integer> hasGift;
	public static volatile SingularAttribute<OrderItem, String> infoSource;
	public static volatile SingularAttribute<OrderItem, String> warehouseNo;
	public static volatile SingularAttribute<OrderItem, BigDecimal> couponTotalMoney;
	public static volatile SingularAttribute<OrderItem, Date> dateUpdated;
	public static volatile SingularAttribute<OrderItem, String> productGroup;
	public static volatile SingularAttribute<OrderItem, String> createdBy;
	public static volatile SingularAttribute<OrderItem, String> aliasOrderItemNo;
	public static volatile SingularAttribute<OrderItem, String> aliasOrderNo;
	public static volatile SingularAttribute<OrderItem, String> itemSource;
	public static volatile SingularAttribute<OrderItem, BigDecimal> promoTicketMoney;
	public static volatile SingularAttribute<OrderItem, String> commodityName;
	public static volatile SingularAttribute<OrderItem, BigDecimal> inventoryPrice;

}

