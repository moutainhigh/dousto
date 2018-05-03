package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderAuditMerchantConfig.class)
public abstract class OrderAuditMerchantConfig_ {

	public static volatile SingularAttribute<OrderAuditMerchantConfig, Boolean> isDelay;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, BigDecimal> minAmount;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Date> updatedTime;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, String> merchantCode;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, String> updatedBy;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Boolean> isIgnoredClientRemark;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Boolean> isApprovedOrderSplit;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Boolean> isIgnoredClientServiceRemark;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Boolean> enabled;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, String> merchantName;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, String> createdBy;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Date> createdTime;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, Integer> id;
	public static volatile SingularAttribute<OrderAuditMerchantConfig, BigDecimal> maxAmount;

}

