package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantBalanceDateLog.class)
public abstract class MerchantBalanceDateLog_ {

	public static volatile SingularAttribute<MerchantBalanceDateLog, String> merchantCode;
	public static volatile SingularAttribute<MerchantBalanceDateLog, Date> balanceDate;
	public static volatile SingularAttribute<MerchantBalanceDateLog, Date> outCreatedTime;
	public static volatile SingularAttribute<MerchantBalanceDateLog, Date> createdTime;
	public static volatile SingularAttribute<MerchantBalanceDateLog, Integer> id;

}

