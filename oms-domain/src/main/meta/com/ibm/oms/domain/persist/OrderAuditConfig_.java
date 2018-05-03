package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderAuditConfig.class)
public abstract class OrderAuditConfig_ {

	public static volatile SingularAttribute<OrderAuditConfig, Integer> auditCount;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedOrderMerge;
	public static volatile SingularAttribute<OrderAuditConfig, String> interceptSkus;
	public static volatile SingularAttribute<OrderAuditConfig, BigDecimal> minAmount;
	public static volatile SingularAttribute<OrderAuditConfig, Date> updatedTime;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedOrderPromotion;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedPayOnArrival;
	public static volatile SingularAttribute<OrderAuditConfig, String> updatedBy;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isIgnoredClientRemark;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedOrderSplit;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedSingleProduct;
	public static volatile SingularAttribute<OrderAuditConfig, Integer> minutesDelay;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isIgnoredClientServiceRemark;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> enabled;
	public static volatile SingularAttribute<OrderAuditConfig, String> createdBy;
	public static volatile SingularAttribute<OrderAuditConfig, Boolean> isApprovedOrderBarter;
	public static volatile SingularAttribute<OrderAuditConfig, Date> createdTime;
	public static volatile SingularAttribute<OrderAuditConfig, Integer> id;
	public static volatile SingularAttribute<OrderAuditConfig, BigDecimal> maxAmount;

}

