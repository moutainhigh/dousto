package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderPay.class)
public abstract class OrderPay_ {

	public static volatile SingularAttribute<OrderPay, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderPay, String> orderNo;
	public static volatile SingularAttribute<OrderPay, String> updatedBy;
	public static volatile SingularAttribute<OrderPay, Date> payTime;
	public static volatile SingularAttribute<OrderPay, Long> billType;
	public static volatile SingularAttribute<OrderPay, String> remark;
	public static volatile SingularAttribute<OrderPay, String> operatorName;
	public static volatile SingularAttribute<OrderPay, String> cardNo;
	public static volatile SingularAttribute<OrderPay, Date> dateUpdated;
	public static volatile SingularAttribute<OrderPay, String> serialNo;
	public static volatile SingularAttribute<OrderPay, String> payNo;
	public static volatile SingularAttribute<OrderPay, Long> idOrder;
	public static volatile SingularAttribute<OrderPay, BigDecimal> payAmount;
	public static volatile SingularAttribute<OrderPay, Date> dateCreated;
	public static volatile SingularAttribute<OrderPay, Long> isDeleted;
	public static volatile SingularAttribute<OrderPay, String> createdBy;
	public static volatile SingularAttribute<OrderPay, String> bankTypeCode;
	public static volatile SingularAttribute<OrderPay, String> bankTypeName;
	public static volatile SingularAttribute<OrderPay, Long> id;
	public static volatile SingularAttribute<OrderPay, String> payCode;
	public static volatile SingularAttribute<OrderPay, String> payName;

}

