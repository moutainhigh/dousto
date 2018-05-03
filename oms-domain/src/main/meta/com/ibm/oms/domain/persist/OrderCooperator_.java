package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderCooperator.class)
public abstract class OrderCooperator_ {

	public static volatile SingularAttribute<OrderCooperator, String> PARTNER;
	public static volatile SingularAttribute<OrderCooperator, String> addressCode;
	public static volatile SingularAttribute<OrderCooperator, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderCooperator, String> orderNo;
	public static volatile SingularAttribute<OrderCooperator, String> updatedBy;
	public static volatile SingularAttribute<OrderCooperator, OrderSub> orderSub;
	public static volatile SingularAttribute<OrderCooperator, Long> billType;
	public static volatile SingularAttribute<OrderCooperator, String> phoneNum;
	public static volatile SingularAttribute<OrderCooperator, String> remark;
	public static volatile SingularAttribute<OrderCooperator, Long> idOrderSub;
	public static volatile SingularAttribute<OrderCooperator, Long> logisticsOrderNo;
	public static volatile SingularAttribute<OrderCooperator, String> mobPhoneNum;
	public static volatile SingularAttribute<OrderCooperator, Date> dateUpdated;
	public static volatile SingularAttribute<OrderCooperator, Long> idOrder;
	public static volatile SingularAttribute<OrderCooperator, String> addressDetail;
	public static volatile SingularAttribute<OrderCooperator, Date> dateCreated;
	public static volatile SingularAttribute<OrderCooperator, Long> isDeleted;
	public static volatile SingularAttribute<OrderCooperator, String> createdBy;
	public static volatile SingularAttribute<OrderCooperator, Long> logisticsNo;
	public static volatile SingularAttribute<OrderCooperator, String> postCode;
	public static volatile SingularAttribute<OrderCooperator, Long> id;
	public static volatile SingularAttribute<OrderCooperator, String> orderSubNo;

}

