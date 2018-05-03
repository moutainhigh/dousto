package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SalesOrderStatus.class)
public abstract class SalesOrderStatus_ {

	public static volatile SingularAttribute<SalesOrderStatus, String> volume;
	public static volatile SingularAttribute<SalesOrderStatus, String> note;
	public static volatile SingularAttribute<SalesOrderStatus, String> orderNo;
	public static volatile SingularAttribute<SalesOrderStatus, Date> operatorTime;
	public static volatile SingularAttribute<SalesOrderStatus, String> shippingOrderNo;
	public static volatile SingularAttribute<SalesOrderStatus, String> logisticsProviderCode;
	public static volatile SingularAttribute<SalesOrderStatus, String> orderStatus;
	public static volatile SingularAttribute<SalesOrderStatus, Integer> weight;
	public static volatile SingularAttribute<SalesOrderStatus, Long> id;
	public static volatile SingularAttribute<SalesOrderStatus, String> exceptionCode;
	public static volatile SingularAttribute<SalesOrderStatus, String> operator;

}

