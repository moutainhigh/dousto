package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HangOrderPayMode.class)
public abstract class HangOrderPayMode_ {

	public static volatile SingularAttribute<HangOrderPayMode, String> orderNo;
	public static volatile SingularAttribute<HangOrderPayMode, String> updatedBy;
	public static volatile SingularAttribute<HangOrderPayMode, String> payModeCode;
	public static volatile SingularAttribute<HangOrderPayMode, String> remark;
	public static volatile SingularAttribute<HangOrderPayMode, Date> dateUpdated;
	public static volatile SingularAttribute<HangOrderPayMode, Long> idOrder;
	public static volatile SingularAttribute<HangOrderPayMode, BigDecimal> payAmount;
	public static volatile SingularAttribute<HangOrderPayMode, Date> dateCreated;
	public static volatile SingularAttribute<HangOrderPayMode, Long> isDeleted;
	public static volatile SingularAttribute<HangOrderPayMode, String> createdBy;
	public static volatile SingularAttribute<HangOrderPayMode, String> payModeName;
	public static volatile SingularAttribute<HangOrderPayMode, Long> id;
	public static volatile SingularAttribute<HangOrderPayMode, Long> payStatus;

}

