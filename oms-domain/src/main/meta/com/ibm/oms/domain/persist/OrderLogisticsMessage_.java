package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderLogisticsMessage.class)
public abstract class OrderLogisticsMessage_ {

	public static volatile SingularAttribute<OrderLogisticsMessage, String> orderNo;
	public static volatile SingularAttribute<OrderLogisticsMessage, String> wmsDesc;
	public static volatile SingularAttribute<OrderLogisticsMessage, String> teackingNumber;
	public static volatile SingularAttribute<OrderLogisticsMessage, Date> wmsTime;
	public static volatile SingularAttribute<OrderLogisticsMessage, Long> id;
	public static volatile SingularAttribute<OrderLogisticsMessage, String> state;
	public static volatile SingularAttribute<OrderLogisticsMessage, String> orderSubNo;
	public static volatile SingularAttribute<OrderLogisticsMessage, String> status;

}

