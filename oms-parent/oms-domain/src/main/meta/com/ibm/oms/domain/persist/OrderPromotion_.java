package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderPromotion.class)
public abstract class OrderPromotion_ {

	public static volatile SingularAttribute<OrderPromotion, String> financialSupplierCode;
	public static volatile SingularAttribute<OrderPromotion, String> promoNo;
	public static volatile SingularAttribute<OrderPromotion, String> orderNo;
	public static volatile SingularAttribute<OrderPromotion, String> updatedBy;
	public static volatile SingularAttribute<OrderPromotion, String> promoType;
	public static volatile SingularAttribute<OrderPromotion, String> remark;
	public static volatile SingularAttribute<OrderPromotion, Date> dateUpdated;
	public static volatile SingularAttribute<OrderPromotion, String> promoName;
	public static volatile SingularAttribute<OrderPromotion, String> memberNo;
	public static volatile SingularAttribute<OrderPromotion, Long> idOrder;
	public static volatile SingularAttribute<OrderPromotion, BigDecimal> ticketAmount;
	public static volatile SingularAttribute<OrderPromotion, BigDecimal> pointCount;
	public static volatile SingularAttribute<OrderPromotion, Date> dateCreated;
	public static volatile SingularAttribute<OrderPromotion, String> ticketNo;
	public static volatile SingularAttribute<OrderPromotion, Long> isDeleted;
	public static volatile SingularAttribute<OrderPromotion, Long> idOrderItem;
	public static volatile SingularAttribute<OrderPromotion, String> createdBy;
	public static volatile SingularAttribute<OrderPromotion, String> orderItemNo;
	public static volatile SingularAttribute<OrderPromotion, String> promoLevel;
	public static volatile SingularAttribute<OrderPromotion, Long> id;
	public static volatile SingularAttribute<OrderPromotion, String> ticketBundleNo;

}

