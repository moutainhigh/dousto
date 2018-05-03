package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderRetAdd.class)
public abstract class OrderRetAdd_ {

	public static volatile SingularAttribute<OrderRetAdd, Long> returnedNum;
	public static volatile SingularAttribute<OrderRetAdd, Long> remainNum;
	public static volatile SingularAttribute<OrderRetAdd, String> orderNo;
	public static volatile SingularAttribute<OrderRetAdd, String> updatedBy;
	public static volatile SingularAttribute<OrderRetAdd, String> remark;
	public static volatile SingularAttribute<OrderRetAdd, BigDecimal> remainMoney;
	public static volatile SingularAttribute<OrderRetAdd, Date> dateUpdated;
	public static volatile SingularAttribute<OrderRetAdd, Long> idOrder;
	public static volatile SingularAttribute<OrderRetAdd, Date> dateCreated;
	public static volatile SingularAttribute<OrderRetAdd, Long> isDeleted;
	public static volatile SingularAttribute<OrderRetAdd, Long> idOrderItem;
	public static volatile SingularAttribute<OrderRetAdd, String> createdBy;
	public static volatile SingularAttribute<OrderRetAdd, BigDecimal> returnedMoney;
	public static volatile SingularAttribute<OrderRetAdd, String> orderItemNo;
	public static volatile SingularAttribute<OrderRetAdd, Long> id;

}

