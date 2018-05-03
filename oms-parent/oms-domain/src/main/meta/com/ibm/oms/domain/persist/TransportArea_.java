package com.ibm.oms.domain.persist;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransportArea.class)
public abstract class TransportArea_ extends com.ibm.sc.model.base.VersionEntity_ {

	public static volatile SingularAttribute<TransportArea, Boolean> isAvailable;
	public static volatile SingularAttribute<TransportArea, String> areaCode;
	public static volatile SingularAttribute<TransportArea, String> areaName;
	public static volatile SingularAttribute<TransportArea, String> areaAbbreviation;
	public static volatile SingularAttribute<TransportArea, Long> areaSequence;
	public static volatile SingularAttribute<TransportArea, Long> areaLevel;
	public static volatile SingularAttribute<TransportArea, Long> id;
	public static volatile SingularAttribute<TransportArea, Long> parentId;

}

