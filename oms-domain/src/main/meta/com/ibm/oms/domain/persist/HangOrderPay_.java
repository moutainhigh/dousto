package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HangOrderPay.class)
public abstract class HangOrderPay_ {

	public static volatile SingularAttribute<HangOrderPay, String> orderNo;
	public static volatile SingularAttribute<HangOrderPay, String> updatedBy;
	public static volatile SingularAttribute<HangOrderPay, Date> payTime;
	public static volatile SingularAttribute<HangOrderPay, HangOrderMain> hangOrderMain;
	public static volatile SingularAttribute<HangOrderPay, Long> billType;
	public static volatile SingularAttribute<HangOrderPay, String> remark;
	public static volatile SingularAttribute<HangOrderPay, String> operatorName;
	public static volatile SingularAttribute<HangOrderPay, String> cardNo;
	public static volatile SingularAttribute<HangOrderPay, Date> dateUpdated;
	public static volatile SingularAttribute<HangOrderPay, String> serialNo;
	public static volatile SingularAttribute<HangOrderPay, String> payNo;
	public static volatile SingularAttribute<HangOrderPay, Long> idOrder;
	public static volatile SingularAttribute<HangOrderPay, BigDecimal> payAmount;
	public static volatile SingularAttribute<HangOrderPay, Date> dateCreated;
	public static volatile SingularAttribute<HangOrderPay, Long> isDeleted;
	public static volatile SingularAttribute<HangOrderPay, String> createdBy;
	public static volatile SingularAttribute<HangOrderPay, String> bankTypeCode;
	public static volatile SingularAttribute<HangOrderPay, String> bankTypeName;
	public static volatile SingularAttribute<HangOrderPay, Long> id;
	public static volatile SingularAttribute<HangOrderPay, String> payCode;
	public static volatile SingularAttribute<HangOrderPay, String> payName;

}

