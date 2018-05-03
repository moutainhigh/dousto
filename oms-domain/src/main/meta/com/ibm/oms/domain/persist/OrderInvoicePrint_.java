package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderInvoicePrint.class)
public abstract class OrderInvoicePrint_ {

	public static volatile SingularAttribute<OrderInvoicePrint, String> invoiceTrader;
	public static volatile SingularAttribute<OrderInvoicePrint, OrderMain> orderMain;
	public static volatile SingularAttribute<OrderInvoicePrint, OrderSub> orderSub;
	public static volatile SingularAttribute<OrderInvoicePrint, BigDecimal> QUANTITY;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoicePrintPersonName;
	public static volatile SingularAttribute<OrderInvoicePrint, Long> markForDelete;
	public static volatile SingularAttribute<OrderInvoicePrint, String> remark;
	public static volatile SingularAttribute<OrderInvoicePrint, Long> idOrderSub;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoicePrintPersonNo;
	public static volatile SingularAttribute<OrderInvoicePrint, Long> idOrder;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoicePrintShop;
	public static volatile SingularAttribute<OrderInvoicePrint, BigDecimal> AMOUNT;
	public static volatile SingularAttribute<OrderInvoicePrint, Long> id;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoiceNo;
	public static volatile SingularAttribute<OrderInvoicePrint, String> taxNo;
	public static volatile SingularAttribute<OrderInvoicePrint, String> orderSubNo;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoicePrintType;
	public static volatile SingularAttribute<OrderInvoicePrint, BigDecimal> unitPrice;
	public static volatile SingularAttribute<OrderInvoicePrint, String> orderNo;
	public static volatile SingularAttribute<OrderInvoicePrint, Date> invoicePrintTime;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoiceProject;
	public static volatile SingularAttribute<OrderInvoicePrint, Long> invoicePrintCount;
	public static volatile SingularAttribute<OrderInvoicePrint, BigDecimal> totalAmount;
	public static volatile SingularAttribute<OrderInvoicePrint, String> createBy;
	public static volatile SingularAttribute<OrderInvoicePrint, String> invoiceHead;
	public static volatile SingularAttribute<OrderInvoicePrint, Date> createTime;
	public static volatile SingularAttribute<OrderInvoicePrint, String> CASHIER;
	public static volatile SingularAttribute<OrderInvoicePrint, String> lastUpdateBy;
	public static volatile SingularAttribute<OrderInvoicePrint, Date> lastUpdateTime;

}

