package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HangOrderPromotion.class)
public abstract class HangOrderPromotion_ {

	public static volatile SingularAttribute<HangOrderPromotion, String> financialSupplierCode;
	public static volatile SingularAttribute<HangOrderPromotion, String> promoNo;
	public static volatile SingularAttribute<HangOrderPromotion, String> orderNo;
	public static volatile SingularAttribute<HangOrderPromotion, String> updatedBy;
	public static volatile SingularAttribute<HangOrderPromotion, HangOrderMain> hangOrderMain;
	public static volatile SingularAttribute<HangOrderPromotion, String> promoType;
	public static volatile SingularAttribute<HangOrderPromotion, String> remark;
	public static volatile SingularAttribute<HangOrderPromotion, Date> dateUpdated;
	public static volatile SingularAttribute<HangOrderPromotion, String> promoName;
	public static volatile SingularAttribute<HangOrderPromotion, String> memberNo;
	public static volatile SingularAttribute<HangOrderPromotion, Long> idOrder;
	public static volatile SingularAttribute<HangOrderPromotion, BigDecimal> ticketAmount;
	public static volatile SingularAttribute<HangOrderPromotion, Integer> pointCount;
	public static volatile SingularAttribute<HangOrderPromotion, Date> dateCreated;
	public static volatile SingularAttribute<HangOrderPromotion, String> ticketNo;
	public static volatile SingularAttribute<HangOrderPromotion, Integer> isDeleted;
	public static volatile SingularAttribute<HangOrderPromotion, Long> idOrderItem;
	public static volatile SingularAttribute<HangOrderPromotion, String> createdBy;
	public static volatile SingularAttribute<HangOrderPromotion, String> orderItemNo;
	public static volatile SingularAttribute<HangOrderPromotion, String> promoLevel;
	public static volatile SingularAttribute<HangOrderPromotion, Long> id;
	public static volatile SingularAttribute<HangOrderPromotion, String> ticketBundleNo;

}

