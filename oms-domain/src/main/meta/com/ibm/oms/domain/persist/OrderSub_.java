package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderSub.class)
public abstract class OrderSub_ {

	public static volatile ListAttribute<OrderSub, OrderRetChange> orderRetChangesRet;
	public static volatile ListAttribute<OrderSub, OrderRetChange> orderRetChangesNew;
	public static volatile SingularAttribute<OrderSub, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderSub, String> logisticsOutNo;
	public static volatile ListAttribute<OrderSub, OrderRetChange> orderRetChangesOld;
	public static volatile ListAttribute<OrderSub, OrderCooperator> orderCooperators;
	public static volatile SingularAttribute<OrderSub, String> phoneNum;
	public static volatile SingularAttribute<OrderSub, String> distributeType;
	public static volatile SingularAttribute<OrderSub, String> addressDetail;
	public static volatile SingularAttribute<OrderSub, Long> hopeArrivalTime;
	public static volatile SingularAttribute<OrderSub, String> shipCat;
	public static volatile SingularAttribute<OrderSub, Long> id;
	public static volatile SingularAttribute<OrderSub, String> orderSubNo;
	public static volatile SingularAttribute<OrderSub, String> deliveryRemark;
	public static volatile SingularAttribute<OrderSub, String> orderNo;
	public static volatile SingularAttribute<OrderSub, String> updatedBy;
	public static volatile SingularAttribute<OrderSub, String> expressType;
	public static volatile SingularAttribute<OrderSub, Long> invoicePrinted;
	public static volatile SingularAttribute<OrderSub, String> deliveryMerchantNo;
	public static volatile SingularAttribute<OrderSub, String> checkCode;
	public static volatile SingularAttribute<OrderSub, String> mobPhoneNum;
	public static volatile SingularAttribute<OrderSub, String> storeNo;
	public static volatile SingularAttribute<OrderSub, String> orderSubRelatedOrigin;
	public static volatile ListAttribute<OrderSub, OrderStatusLog> orderStatusLogs;
	public static volatile SingularAttribute<OrderSub, String> postCode;
	public static volatile SingularAttribute<OrderSub, Date> outStoreTime;
	public static volatile SingularAttribute<OrderSub, String> provideAddress;
	public static volatile SingularAttribute<OrderSub, BigDecimal> transportFee;
	public static volatile SingularAttribute<OrderSub, String> packageDetail;
	public static volatile ListAttribute<OrderSub, OrderRetChgItem> orderRetChgItems;
	public static volatile ListAttribute<OrderSub, OrderItem> orderItems;
	public static volatile SingularAttribute<OrderSub, Long> idOrder;
	public static volatile SingularAttribute<OrderSub, Date> dateCreated;
	public static volatile SingularAttribute<OrderSub, Long> isDeleted;
	public static volatile ListAttribute<OrderSub, OrderInvoice> orderInvoices;
	public static volatile SingularAttribute<OrderSub, Date> signOffTime;
	public static volatile SingularAttribute<OrderSub, String> deliveryPriority;
	public static volatile SingularAttribute<OrderSub, String> email;
	public static volatile SingularAttribute<OrderSub, String> aliasOrderSubNo;
	public static volatile SingularAttribute<OrderSub, String> addressCode;
	public static volatile SingularAttribute<OrderSub, Date> pickTime;
	public static volatile ListAttribute<OrderSub, OrderInvoicePrint> orderInvoicePrints;
	public static volatile SingularAttribute<OrderSub, Long> billType;
	public static volatile SingularAttribute<OrderSub, String> deliveryMerchantName;
	public static volatile SingularAttribute<OrderSub, String> userName;
	public static volatile SingularAttribute<OrderSub, Date> dateUpdated;
	public static volatile SingularAttribute<OrderSub, String> logistics_description;
	public static volatile ListAttribute<OrderSub, OrderStatusSyncLog> orderStatusSyncLogs;
	public static volatile SingularAttribute<OrderSub, String> signer_name;
	public static volatile SingularAttribute<OrderSub, String> selfFetchAddress;
	public static volatile SingularAttribute<OrderSub, String> createdBy;
	public static volatile SingularAttribute<OrderSub, String> bolNo;
	public static volatile SingularAttribute<OrderSub, String> aliasOrderNo;
	public static volatile SingularAttribute<OrderSub, String> logisticsStatus;

}

