package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WholesaleOrderStatus.class)
public abstract class WholesaleOrderStatus_ {

	public static volatile SingularAttribute<WholesaleOrderStatus, String> wareHouseCode;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> note;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> callBack;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> asnCode;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> operator;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> isAsnFinished;
	public static volatile SingularAttribute<WholesaleOrderStatus, Date> operatorTime;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> shippingOrderNo;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> logisticsProviderCode;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> wmsBillCode;
	public static volatile SingularAttribute<WholesaleOrderStatus, Long> id;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> asnStatus;
	public static volatile SingularAttribute<WholesaleOrderStatus, String> exceptionCode;

}

