package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderOperateLog.class)
public abstract class OrderOperateLog_ {

	public static volatile SingularAttribute<OrderOperateLog, String> reason;
	public static volatile SingularAttribute<OrderOperateLog, String> orderSource;
	public static volatile SingularAttribute<OrderOperateLog, String> orderNo;
	public static volatile SingularAttribute<OrderOperateLog, String> updatedBy;
	public static volatile SingularAttribute<OrderOperateLog, Long> billType;
	public static volatile SingularAttribute<OrderOperateLog, String> IP;
	public static volatile SingularAttribute<OrderOperateLog, Long> idOrderSub;
	public static volatile SingularAttribute<OrderOperateLog, String> content;
	public static volatile SingularAttribute<OrderOperateLog, String> operator;
	public static volatile SingularAttribute<OrderOperateLog, Date> dateUpdated;
	public static volatile SingularAttribute<OrderOperateLog, String> oldData;
	public static volatile SingularAttribute<OrderOperateLog, Long> idOrder;
	public static volatile SingularAttribute<OrderOperateLog, Date> dateCreated;
	public static volatile SingularAttribute<OrderOperateLog, Long> isDeleted;
	public static volatile SingularAttribute<OrderOperateLog, Long> idOrderItem;
	public static volatile SingularAttribute<OrderOperateLog, String> createdBy;
	public static volatile SingularAttribute<OrderOperateLog, String> orderItemNo;
	public static volatile SingularAttribute<OrderOperateLog, Long> id;
	public static volatile SingularAttribute<OrderOperateLog, Long> aliasOrderId;
	public static volatile SingularAttribute<OrderOperateLog, String> orderSubNo;
	public static volatile SingularAttribute<OrderOperateLog, String> newData;

}

