package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntfSent.class)
public abstract class IntfSent_ {

	public static volatile SingularAttribute<IntfSent, String> msg;
	public static volatile SingularAttribute<IntfSent, String> orderNo;
	public static volatile SingularAttribute<IntfSent, Long> idOrderItem;
	public static volatile SingularAttribute<IntfSent, Date> createTime;
	public static volatile SingularAttribute<IntfSent, Long> retryCount;
	public static volatile SingularAttribute<IntfSent, String> btcOrderNo;
	public static volatile SingularAttribute<IntfSent, Long> succeedFlag;
	public static volatile SingularAttribute<IntfSent, String> orderItemNo;
	public static volatile SingularAttribute<IntfSent, Long> id;
	public static volatile SingularAttribute<IntfSent, String> btcOrderItemNo;
	public static volatile SingularAttribute<IntfSent, String> intfCode;
	public static volatile SingularAttribute<IntfSent, String> orderSubNo;

}

