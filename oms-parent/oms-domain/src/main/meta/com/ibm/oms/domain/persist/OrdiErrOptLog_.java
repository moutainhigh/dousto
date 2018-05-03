package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdiErrOptLog.class)
public abstract class OrdiErrOptLog_ {

	public static volatile SingularAttribute<OrdiErrOptLog, BigDecimal> operateCount;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderSource;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderNo;
	public static volatile SingularAttribute<OrdiErrOptLog, String> errorDesc;
	public static volatile SingularAttribute<OrdiErrOptLog, String> errorType;
	public static volatile SingularAttribute<OrdiErrOptLog, Date> operateTime;
	public static volatile SingularAttribute<OrdiErrOptLog, Long> resultCode;
	public static volatile SingularAttribute<OrdiErrOptLog, String> operateType;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderStatus;
	public static volatile SingularAttribute<OrdiErrOptLog, String> errorCode;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderItemClass;
	public static volatile SingularAttribute<OrdiErrOptLog, String> resultDesc;
	public static volatile SingularAttribute<OrdiErrOptLog, String> operator;
	public static volatile SingularAttribute<OrdiErrOptLog, String> operateIp;
	public static volatile SingularAttribute<OrdiErrOptLog, String> aliasOrderItemNo;
	public static volatile SingularAttribute<OrdiErrOptLog, String> operateRemark;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderItemNo;
	public static volatile SingularAttribute<OrdiErrOptLog, String> aliasOrderNo;
	public static volatile SingularAttribute<OrdiErrOptLog, Long> id;
	public static volatile SingularAttribute<OrdiErrOptLog, String> orderSubNo;

}

