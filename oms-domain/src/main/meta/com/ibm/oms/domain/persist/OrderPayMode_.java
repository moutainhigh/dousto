package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderPayMode.class)
public abstract class OrderPayMode_ {

	public static volatile SingularAttribute<OrderPayMode, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderPayMode, String> orderNo;
	public static volatile SingularAttribute<OrderPayMode, String> updatedBy;
	public static volatile SingularAttribute<OrderPayMode, String> payModeCode;
	public static volatile SingularAttribute<OrderPayMode, String> remark;
	public static volatile SingularAttribute<OrderPayMode, Date> dateUpdated;
	public static volatile SingularAttribute<OrderPayMode, Long> idOrder;
	public static volatile SingularAttribute<OrderPayMode, BigDecimal> payAmount;
	public static volatile SingularAttribute<OrderPayMode, Date> dateCreated;
	public static volatile SingularAttribute<OrderPayMode, Long> isDeleted;
	public static volatile SingularAttribute<OrderPayMode, String> createdBy;
	public static volatile SingularAttribute<OrderPayMode, String> payModeName;
	public static volatile SingularAttribute<OrderPayMode, Long> id;
	public static volatile SingularAttribute<OrderPayMode, Long> payStatus;

}

