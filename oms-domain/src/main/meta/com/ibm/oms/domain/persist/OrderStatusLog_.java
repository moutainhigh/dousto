package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderStatusLog.class)
public abstract class OrderStatusLog_ {

	public static volatile SingularAttribute<OrderStatusLog, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderStatusLog, String> orderNo;
	public static volatile SingularAttribute<OrderStatusLog, String> updatedBy;
	public static volatile SingularAttribute<OrderStatusLog, OrderSub> orderSub;
	public static volatile SingularAttribute<OrderStatusLog, String> currentStatus;
	public static volatile SingularAttribute<OrderStatusLog, Date> operateTime;
	public static volatile SingularAttribute<OrderStatusLog, String> operatorNo;
	public static volatile SingularAttribute<OrderStatusLog, String> remark;
	public static volatile SingularAttribute<OrderStatusLog, Long> idOrderSub;
	public static volatile SingularAttribute<OrderStatusLog, String> operator;
	public static volatile SingularAttribute<OrderStatusLog, Date> dateUpdated;
	public static volatile SingularAttribute<OrderStatusLog, Long> idOrder;
	public static volatile SingularAttribute<OrderStatusLog, Date> dateCreated;
	public static volatile SingularAttribute<OrderStatusLog, Long> isDeleted;
	public static volatile SingularAttribute<OrderStatusLog, String> createdBy;
	public static volatile SingularAttribute<OrderStatusLog, Long> id;
	public static volatile SingularAttribute<OrderStatusLog, String> orderSubNo;
	public static volatile SingularAttribute<OrderStatusLog, String> operatorRole;
	public static volatile SingularAttribute<OrderStatusLog, String> previousStatus;

}

