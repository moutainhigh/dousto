package com.ibm.oms.domain.persist;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HangOrderInvoice.class)
public abstract class HangOrderInvoice_ {

	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceAdditionInfo;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceBankName;
	public static volatile SingularAttribute<HangOrderInvoice, String> remark;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceAddress;
	public static volatile SingularAttribute<HangOrderInvoice, Long> idOrderSub;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceCompany;
	public static volatile SingularAttribute<HangOrderInvoice, Long> idOrder;
	public static volatile SingularAttribute<HangOrderInvoice, Date> dateCreated;
	public static volatile SingularAttribute<HangOrderInvoice, Long> isDeleted;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceType;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceTelephone;
	public static volatile SingularAttribute<HangOrderInvoice, Long> id;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceToName;
	public static volatile SingularAttribute<HangOrderInvoice, String> orderSubNo;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceContent;
	public static volatile SingularAttribute<HangOrderInvoice, String> orderSource;
	public static volatile SingularAttribute<HangOrderInvoice, String> orderNo;
	public static volatile SingularAttribute<HangOrderInvoice, String> updatedBy;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceBankAccount;
	public static volatile SingularAttribute<HangOrderInvoice, HangOrderMain> hangOrderMain;
	public static volatile SingularAttribute<HangOrderInvoice, Long> billType;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceNum;
	public static volatile SingularAttribute<HangOrderInvoice, Date> dateUpdated;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceTaxpayer;
	public static volatile SingularAttribute<HangOrderInvoice, String> registryAddress;
	public static volatile SingularAttribute<HangOrderInvoice, HangOrderSub> hangOrderSub;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceToTelephone;
	public static volatile SingularAttribute<HangOrderInvoice, String> invoiceHead;
	public static volatile SingularAttribute<HangOrderInvoice, String> createdBy;
	public static volatile SingularAttribute<HangOrderInvoice, Long> aliasOrderNo;

}

