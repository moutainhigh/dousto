package com.ibm.oms.domain.persist;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderCategory.class)
public abstract class OrderCategory_ {

	public static volatile SingularAttribute<OrderCategory, Long> treeLevel;
	public static volatile SingularAttribute<OrderCategory, Long> catalogId;
	public static volatile SingularAttribute<OrderCategory, Long> isDeleted;
	public static volatile SingularAttribute<OrderCategory, String> name;
	public static volatile SingularAttribute<OrderCategory, Long> id;
	public static volatile SingularAttribute<OrderCategory, Long> parentId;

}

